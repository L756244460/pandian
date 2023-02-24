package cn.datasset.yipandian.client.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import cn.datasset.yipandian.client.code.ErrorCode;
import cn.datasset.yipandian.client.common.ResultVO;
import cn.datasset.yipandian.client.model.user.UserDTO;
import cn.datasset.yipandian.client.model.user.UserPO;
import cn.datasset.yipandian.client.service.LoginService;
import cn.datasset.yipandian.client.service.UserInfoService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
     * @param userPO
     */
    @PostMapping("/register")
    public ResultVO register(@RequestBody UserPO userPO){
        if(StringUtils.isEmpty(userPO.getPhoneNumber())||StringUtils.isEmpty(userPO.getPassword())){
            return ResultVO.fail(ErrorCode.PARAM_NULL);
        }
        return loginService.register(userPO);
    }


    /**
     * 用户登录
     */
    @PostMapping("/login")
    public ResultVO login(@RequestBody UserDTO dto, HttpServletResponse response){
        if(StringUtils.isEmpty(dto.getPhoneNumber())||StringUtils.isEmpty(dto.getPassword())){
            return ResultVO.fail(ErrorCode.PARAM_NULL);
        }
        return loginService.login(dto,response);
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
