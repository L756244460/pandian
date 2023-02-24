package cn.datasset.yipandian.client.service.impl;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import cn.datasset.yipandian.client.code.ErrorCode;
import cn.datasset.yipandian.client.common.ResultVO;
import cn.datasset.yipandian.client.mapper.UserMapper;
import cn.datasset.yipandian.client.model.user.UserDTO;
import cn.datasset.yipandian.client.model.user.UserPO;
import cn.datasset.yipandian.client.model.user.UserTokenInfo;
import cn.datasset.yipandian.client.service.LoginService;
import cn.datasset.yipandian.client.service.UserInfoService;
import cn.datasset.yipandian.client.util.DESUtils;
import cn.datasset.yipandian.client.util.RedisUtils;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * @author: Created by lzl
 * @Date: 2021/12/6 14:29
 */
@Service
@Slf4j
public class LoginServiceImpl implements LoginService {

    @Resource
    private UserMapper userMapper;

    @Value("${deskey}")
    private String desKey;

    @Value("${token.expire-time}")
    private Integer expire_time;

    @Value("${token.secret}")
    private String token_secret;
    @Resource
    RedisUtils redisUtils;
    @Resource
    UserInfoService userInfoService;

    @Override
    public ResultVO register(UserPO userPO) {
        //Base64解密
        String password = new String(Base64.getDecoder().decode(userPO.getPassword()), StandardCharsets.UTF_8);
        //DES加密
        password = DESUtils.encrpt(desKey,password);
        UserPO user = userMapper.selectOne(new QueryWrapper<UserPO>()
            .eq("phone_number", userPO.getPhoneNumber())
            .eq("password", password)
            .eq("status",0));
        if (null!=user){
            return ResultVO.fail(ErrorCode.REGISTER_NOTNULL);
        }
        userPO.setPassword(password);
        userPO.setCreateTime(new Date());
        userPO.setCreateUser("123");
        userPO.setRegisterTime(new Date());
        if (userMapper.insert(userPO) > 0){
            return ResultVO.success();
        }
        return ResultVO.fail();
    }

    @Override
    public ResultVO login(UserDTO dto, HttpServletResponse response) {
        //Base64解密
        String password = new String(Base64.getDecoder().decode(dto.getPassword()), StandardCharsets.UTF_8);
        //DES加密
        password = DESUtils.encrpt(desKey,password);
        UserPO user = userMapper.selectOne(new QueryWrapper<UserPO>()
                .eq("phone_number", dto.getPhoneNumber())
                .eq("password", password)
                .eq("status",0));
        if (null==user){
            return ResultVO.fail(ErrorCode.LOGIN_INCORRECT);
        }
        String token = signToken(user.getId());
        //设置token白名单
        String key="ypd_admin_token_"+user.getId();
        redisUtils.setValueByKey(key,token,expire_time, TimeUnit.HOURS);

        UserTokenInfo userInfo = new UserTokenInfo().setAccessToken(token)
                .setUserId(user.getId().toString())
                .setName(user.getUnitName())
                .setPhoneNumber(user.getPhoneNumber())
                .setAdmin(user.getType() == 1);
        return ResultVO.success(userInfo);
    }

    //生成token信息
    private String signToken(Long id) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.HOUR,expire_time);
        Date date = calendar.getTime();
        Algorithm algorithm = Algorithm.HMAC256(token_secret);
        HashMap<String, Object> header = new HashMap<>();
        header.put("typ","JWT");
        header.put("alg","HS256");
        return JWT.create()
                .withHeader(header)
                .withClaim("userId", id)
                .withExpiresAt(date)
                .sign(algorithm);
    }


    @Override
    public ResultVO logout() {
        UserPO userPO = userInfoService.getUserInfo();
        String key="ypd_admin_token_"+userPO.getId();
        redisUtils.delete(key);
        return ResultVO.success();
    }

    /**
     * 密码校验
     * @param password
     * @return
     */
    private boolean verifyPwd(String password){
        Pattern pattern = Pattern.compile("^(?![^a-zA-Z]+$)(?!\\\\D+$).{8,16}$");
        return pattern.matcher(password).matches();
    }
}
