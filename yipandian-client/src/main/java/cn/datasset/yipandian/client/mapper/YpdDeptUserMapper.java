package cn.datasset.yipandian.client.mapper;

import java.util.List;

import cn.datasset.yipandian.client.model.dept.YpdDeptUserRespVO;
import cn.datasset.yipandian.client.model.dept.YpdDeptVO;
import cn.datasset.yipandian.client.model.user.YpdDeptUserPO;
import cn.datasset.yipandian.client.model.user.YpdDeptUserReqVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.ibatis.annotations.Param;

/**
 * @author lzl
 */
public interface YpdDeptUserMapper extends BaseMapper<YpdDeptUserPO> {

    void saveBatch(@Param("list") List<YpdDeptUserPO> list);

    List<YpdDeptVO> selectDeptByUserId(@Param("userid") String userid);

    IPage<YpdDeptUserRespVO> selectDeptUserList(IPage<YpdDeptUserRespVO> page, @Param("condition") YpdDeptUserReqVO reqVO);
}
