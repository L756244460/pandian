package cn.datasset.yipandian.client.mapper;

import java.util.List;

import cn.datasset.yipandian.client.model.user.TenantDTO;
import cn.datasset.yipandian.client.model.user.TenantPO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author: Created by lzl
 */
@Mapper
public interface TenantMapper extends BaseMapper<TenantPO> {
    IPage<TenantPO> authorizationList(@Param("page") IPage<Object> objectPage, @Param("tenantDTO") TenantDTO tenantDTO);

    int enableAuthorization(@Param("ids") List<String> ids, @Param("updateUserId") String updateUserId);

    int disableAuthorization(@Param("ids") List<String> ids, @Param("updateUserId") String updateUserId);
}
