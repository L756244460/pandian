package cn.datasset.yipandian.client.mapper;

import java.util.List;

import cn.datasset.yipandian.client.model.user.UserDTO;
import cn.datasset.yipandian.client.model.user.UserPO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author: Created by lzl
 */
@Mapper
public interface UserMapper extends BaseMapper<UserPO> {
    IPage<UserPO> authorizationList(@Param("page") Page<Object> objectPage, @Param("userDTO") UserDTO userDTO);

    int enableAuthorization(@Param("ids") List<String> ids, @Param("updateUserId") String updateUserId);

    int disableAuthorization(@Param("ids") List<String> ids, @Param("updateUserId") String updateUserId);
}
