package cn.datasset.yipandian.client.service.impl;

import java.util.List;

import javax.annotation.Resource;

import cn.datasset.yipandian.client.common.ResultVO;
import cn.datasset.yipandian.client.mapper.UserMapper;
import cn.datasset.yipandian.client.model.user.UserDTO;
import cn.datasset.yipandian.client.model.user.UserPO;
import cn.datasset.yipandian.client.service.UserService;
import cn.datasset.yipandian.client.util.UserSession;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author lizhaolong
 * @create 2023-02-24 09:41
 */
@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Resource
    UserSession userSession;

    @Resource
    private UserMapper userMapper;

    @Override
    public ResultVO authorizationList(UserDTO userDTO) {

        IPage<UserPO> assetStockPage = userMapper.authorizationList(new Page<>(userDTO.getCurrent(), userDTO.getPageSize()), userDTO);

        return ResultVO.success(assetStockPage);
    }

    @Override
    public ResultVO enableAuthorization(List<String> ids) {
        String updateUserId = userSession.getuserId();
        if (userMapper.enableAuthorization(ids,updateUserId) > 0){
             return ResultVO.success();
         }
        return ResultVO.fail();
    }

    @Override
    public ResultVO disableAuthorization(List<String> ids) {
        String updateUserId = userSession.getuserId();
        if (userMapper.disableAuthorization(ids,updateUserId) > 0){
            return ResultVO.success();
        }
        return ResultVO.fail();
    }
}
