package cn.datasset.yipandian.client.service;

import java.util.List;

import cn.datasset.yipandian.client.common.ResultVO;
import cn.datasset.yipandian.client.model.user.UserDTO;

/**
 * @author lizhaolong
 * @create 2023-02-24 09:40
 */
public interface UserService {
    ResultVO authorizationList(UserDTO userDTO);

    ResultVO enableAuthorization(List<String> ids);

    ResultVO disableAuthorization(List<String> ids);
}
