package cn.datasset.yipandian.client.mapper;

import java.util.List;

import cn.datasset.yipandian.client.model.user.YpdUserPO;
import cn.datasset.yipandian.client.model.user.YpdUserVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author lzl
 */
public interface YpdUserMapper extends BaseMapper<YpdUserPO> {

    void saveBatch(@Param("list") List<YpdUserPO> list);

    List<YpdUserVO> selectUserAndDept(@Param("tenantCode") String tenantCode, @Param("realName") String realName);

    String selectRealNameByUserId(@Param("userId") String userId);
}
