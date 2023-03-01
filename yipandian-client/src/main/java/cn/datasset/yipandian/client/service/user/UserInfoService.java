package cn.datasset.yipandian.client.service.user;

import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;

import cn.datasset.yipandian.client.interceptor.UserInfoInterceptor;
import cn.datasset.yipandian.client.mapper.TenantMapper;
import cn.datasset.yipandian.client.model.user.UserInfo;
import cn.datasset.yipandian.client.model.user.TenantPO;
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

    private final static String USER_INFO_KEY = "ypd_tenantid_info_";

    @Resource
    RedisUtils redisUtils;

    @Resource
    private TenantMapper tenantMapper;

    public synchronized TenantPO getUserInfo() {
        UserInfo userInfo = UserInfoInterceptor.userInfoThreadLocal.get();
        String userId = userInfo.getUserId();
        String key = USER_INFO_KEY + userId;
        String resStr = redisUtils.getValueByKey(key);
        // log.debug("用户信息： key {}, 缓存查询结果：{}", key, resStr);
        if (StringUtils.isNotEmpty(resStr)) {
            return JSON.parseObject(resStr, new TypeReference<TenantPO>() {
            });
        } else {
            TenantPO tenantPO = selectUserInfo();
            // log.debug("用户信息首次调用 存储缓存： key {}, 存储结果：{}", key, JSON.toJSONString(userInfo));
            redisUtils.setValueByKey(key, JSON.toJSONString(tenantPO), 8, TimeUnit.HOURS);
            return tenantPO;
        }
    }

    public TenantPO selectUserInfo() {
        UserInfo userInfo = UserInfoInterceptor.userInfoThreadLocal.get();
        String userId = userInfo.getUserId();
        return selectZcyUser(userId);
    }

    public TenantPO selectZcyUser(String userId) {
        TenantPO tenantPO = tenantMapper.selectOne(new QueryWrapper<TenantPO>().eq("id", userId));
        log.debug("用户登陆校验： userid {}, 调用结果{}", userId, JSON.toJSONString(tenantPO));
        log.debug("用户信息首次调用 当前用户基本信息： userId {}, 查询结果：{}", userId, JSON.toJSONString(tenantPO));
        return tenantPO;
    }

    public boolean userExist(String userId) {
        String key = USER_ID_KEY + userId;
        String resStr = redisUtils.getValueByKey(key);
        if (StringUtils.isNotBlank(resStr)) {
            return Boolean.valueOf(resStr);
        }
        boolean userExist = false;
        TenantPO ypdTenantPO = selectZcyUser(userId);
        if (null != ypdTenantPO) {
            userExist = true;
        }
        redisUtils.setValueByKey(key, String.valueOf(userExist), 8, TimeUnit.HOURS);
        return userExist;

    }
}
