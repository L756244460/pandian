package cn.datasset.yipandian.client.service;

import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;

import cn.datasset.yipandian.client.interceptor.UserInfoInterceptor;
import cn.datasset.yipandian.client.mapper.UserMapper;
import cn.datasset.yipandian.client.model.user.UserInfo;
import cn.datasset.yipandian.client.model.user.UserPO;
import cn.datasset.yipandian.client.util.RedisUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

/**
 * @author lzl
 * @version 1.0
 */
@Slf4j
@Service
public class UserInfoService {

    private final static String USER_ID_KEY = "ypd_login_userid_";

    private final static String USER_INFO_KEY = "ypd_userid_info_";

    @Resource
    RedisUtils redisUtils;

    @Resource
    private UserMapper userMapper;

    public synchronized UserPO getUserInfo() {
        UserInfo userInfo = UserInfoInterceptor.userInfoThreadLocal.get();
        String userId = userInfo.getUserId();
        String key = USER_INFO_KEY + userId;
        String resStr = redisUtils.getValueByKey(key);
        // log.debug("用户信息： key {}, 缓存查询结果：{}", key, resStr);
        if (StringUtils.isNotEmpty(resStr)) {
            return JSON.parseObject(resStr, new TypeReference<UserPO>() {
            });
        } else {
            UserPO userPO = selectUserInfo();
            // log.debug("用户信息首次调用 存储缓存： key {}, 存储结果：{}", key, JSON.toJSONString(userInfo));
            redisUtils.setValueByKey(key, JSON.toJSONString(userPO), 8, TimeUnit.HOURS);
            return userPO;
        }
    }

    public UserPO selectUserInfo() {
        UserInfo userInfo = UserInfoInterceptor.userInfoThreadLocal.get();
        String userId = userInfo.getUserId();
        return selectZcyUser(userId);
    }

    public UserPO selectZcyUser(String userId) {
        UserPO userPO = userMapper.selectOne(new QueryWrapper<UserPO>().eq("id", userId));
        log.debug("用户登陆校验： userid {}, 调用结果{}", userId, JSON.toJSONString(userPO));
        log.debug("用户信息首次调用 当前用户基本信息： userId {}, 查询结果：{}", userId, JSON.toJSONString(userPO));
        return userPO;
    }

    public boolean userExist(String userId) {
        String key = USER_ID_KEY + userId;
        String resStr = redisUtils.getValueByKey(key);
        if (StringUtils.isNotBlank(resStr)) {
            return Boolean.valueOf(resStr);
        }
        boolean userExist = false;
        UserPO ypdUserPO = selectZcyUser(userId);
        if (null != ypdUserPO) {
            userExist = true;
        }
        redisUtils.setValueByKey(key, String.valueOf(userExist), 8, TimeUnit.HOURS);
        return userExist;

    }
}
