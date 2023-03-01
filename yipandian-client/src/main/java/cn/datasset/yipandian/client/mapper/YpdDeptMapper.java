package cn.datasset.yipandian.client.mapper;

import java.util.List;

import cn.datasset.yipandian.client.model.dept.YpdDeptPO;
import cn.datasset.yipandian.client.model.dept.YpdDeptVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author lzl
 * @version 1.0
 * @date 2021/11/1 20:20
 */
@Mapper
public interface YpdDeptMapper extends BaseMapper<YpdDeptPO> {
    YpdDeptVO getDepTreeByTenantCode(String tenantCode);

    YpdDeptVO getDepUserTreeByTenantCode(String tenantCode);

    List<YpdDeptVO> getDepTreeByParentId(@Param("tenantCode")String tenantCode,@Param("parentId")String parentId);

    YpdDeptVO getDepUserTreeByParentId(@Param("tenantCode")String tenantCode,@Param("parentId")String parentId);

    List<YpdDeptPO> selectByUserId(String userId);

    List<YpdDeptPO> getUserByDeptId(String deptId);

    void saveBatch(@Param("list") List<YpdDeptPO> list);
}
