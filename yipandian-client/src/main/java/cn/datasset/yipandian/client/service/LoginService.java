package cn.datasset.yipandian.client.service;

import javax.servlet.http.HttpServletResponse;

import cn.datasset.yipandian.client.common.ResultVO;
import cn.datasset.yipandian.client.model.user.UserDTO;
import cn.datasset.yipandian.client.model.user.UserPO;

/**
 * @author: Created by zhoujian
 * @Date: 2021/12/6 14:29
 */
public interface LoginService {
    ResultVO login(UserDTO dto, HttpServletResponse response);

    ResultVO logout();

    ResultVO register(UserPO userPO);
}
