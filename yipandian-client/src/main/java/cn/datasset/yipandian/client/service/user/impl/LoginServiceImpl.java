package cn.datasset.yipandian.client.service.user.impl;

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
import cn.datasset.yipandian.client.mapper.TenantMapper;
import cn.datasset.yipandian.client.model.user.TenantDTO;
import cn.datasset.yipandian.client.model.user.TenantPO;
import cn.datasset.yipandian.client.model.user.TenantTokenInfo;
import cn.datasset.yipandian.client.service.user.LoginService;
import cn.datasset.yipandian.client.service.user.UserInfoService;
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
    private TenantMapper tenantMapper;

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
    public ResultVO register(TenantPO tenantPO) {
        //Base64解密
        String password = new String(Base64.getDecoder().decode(tenantPO.getPassword()), StandardCharsets.UTF_8);
        //DES加密
        password = DESUtils.encrpt(desKey,password);
        TenantPO user = tenantMapper.selectOne(new QueryWrapper<TenantPO>()
            .eq("phone_number", tenantPO.getPhoneNumber())
            .eq("password", password)
            .eq("status",0));
        if (null!=user){
            return ResultVO.fail(ErrorCode.REGISTER_NOTNULL);
        }
        tenantPO.setPassword(password);
        tenantPO.setCreateTime(new Date());
        tenantPO.setCreateUser("123");
        tenantPO.setRegisterTime(new Date());
        if (tenantMapper.insert(tenantPO) > 0){
            return ResultVO.success();
        }
        return ResultVO.fail();
    }

    @Override
    public ResultVO login(TenantDTO dto, HttpServletResponse response) {
        //判断账号状态
        TenantPO tenantPO = tenantMapper.selectOne(new QueryWrapper<TenantPO>()
            .eq("phone_number", dto.getPhoneNumber()));
        if (tenantPO.getStatus()==1){
            return ResultVO.fail(ErrorCode.NUMBER_NOTLOGIN);
        }
        //Base64解密
        String password = new String(Base64.getDecoder().decode(dto.getPassword()), StandardCharsets.UTF_8);
        //DES加密
        password = DESUtils.encrpt(desKey,password);
        TenantPO user = tenantMapper.selectOne(new QueryWrapper<TenantPO>()
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

        TenantTokenInfo userInfo = new TenantTokenInfo().setAccessToken(token)
                .setUserId(user.getId().toString())
                .setName(user.getUnitName())
                .setPhoneNumber(user.getPhoneNumber())
                .setAdmin(user.getType());
        return ResultVO.success(userInfo);
    }

    //生成token信息
    private String signToken(String id) {
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
        TenantPO tenantPO = userInfoService.getUserInfo();
        String key="ypd_admin_token_"+ tenantPO.getId();
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
