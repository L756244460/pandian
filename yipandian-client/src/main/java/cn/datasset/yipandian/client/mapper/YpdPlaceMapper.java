package cn.datasset.yipandian.client.mapper;

import java.util.List;

import cn.datasset.yipandian.client.model.place.YpdPlaceDTO;
import cn.datasset.yipandian.client.model.place.YpdPlacePO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

/**
 * @author: Created by lzl
 */
@Mapper
public interface YpdPlaceMapper extends BaseMapper<YpdPlacePO> {

    @Update("update `ypd_place` set number=number+1 where id=#{id}")
    void updateCount(Integer id);

    List<YpdPlacePO> selectReuseList(YpdPlaceDTO dto);
}
