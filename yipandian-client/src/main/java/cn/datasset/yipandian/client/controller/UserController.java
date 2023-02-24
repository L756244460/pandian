package cn.datasset.yipandian.client.controller;

import java.util.List;

import javax.annotation.Resource;

import cn.datasset.yipandian.client.common.ResultVO;
import cn.datasset.yipandian.client.model.user.UserDTO;
import cn.datasset.yipandian.client.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lizhaolong
 * @create 2023-02-24 09:35
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    /**
     * 授权管理查询
     */
    @PostMapping("/authorizationList")
    public ResultVO authorizationList(@RequestBody UserDTO userDTO){
        return userService.authorizationList(userDTO);
    }

    /**
     * 启用授权
     */
    @PostMapping("/enableAuthorization")
    public ResultVO enableAuthorization(@RequestBody List<String> ids){
        return userService.enableAuthorization(ids);
    }

    /**
     * 禁用授权
     */
    @PostMapping("/disableAuthorization")
    public ResultVO disableAuthorization(@RequestBody List<String> ids){
        return userService.disableAuthorization(ids);
    }




}
