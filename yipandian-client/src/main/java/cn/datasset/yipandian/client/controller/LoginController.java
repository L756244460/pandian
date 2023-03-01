package cn.datasset.yipandian.client.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import cn.datasset.yipandian.client.code.ErrorCode;
import cn.datasset.yipandian.client.common.ResultVO;
import cn.datasset.yipandian.client.model.user.TenantDTO;
import cn.datasset.yipandian.client.model.user.TenantPO;
import cn.datasset.yipandian.client.service.user.LoginService;
import cn.datasset.yipandian.client.service.user.UserInfoService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *     登录controller
 * </p>
 * @author: Created by zhoujian
 * @Date: 2021/12/2 11:38
 */
@RestController
public class LoginController {


    @Resource
    LoginService loginService;


    @Resource
    UserInfoService userInfoService;
    /**
     * 用户授权/注册
     * @param tenantPO
     */
    @PostMapping("/register")
    public ResultVO register(@RequestBody TenantPO tenantPO){
        if(StringUtils.isEmpty(tenantPO.getPhoneNumber())||StringUtils.isEmpty(tenantPO.getPassword())){
            return ResultVO.fail(ErrorCode.PARAM_NULL);
        }
        return loginService.register(tenantPO);
    }


    /**
     * 用户登录
     */
    @PostMapping("/login")
    public ResultVO login(@RequestParam String phoneNumber,@RequestParam String password, HttpServletResponse response){
        if(StringUtils.isEmpty(phoneNumber)||StringUtils.isEmpty(password)){
            return ResultVO.fail(ErrorCode.PARAM_NULL);
        }
        TenantDTO tenantDTO =new TenantDTO();
        tenantDTO.setPassword(password);
        tenantDTO.setPhoneNumber(phoneNumber);
        return loginService.login(tenantDTO,response);
    }

    /**
     * 获取当前登录的用户信息
     */
    @GetMapping("/getUserInfo")
    public ResultVO getUserInfo(){
        return ResultVO.success(userInfoService.getUserInfo());
    }

    /**
     * 登出当前用户
     */
    @GetMapping("/logout")
    public ResultVO logout(){
        return loginService.logout();
    }
}
