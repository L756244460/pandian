package cn.datasset.yipandian.client.service.place;

import java.util.List;

import cn.datasset.yipandian.client.common.ResultVO;
import cn.datasset.yipandian.client.model.place.YpdPlaceDTO;
import cn.datasset.yipandian.client.model.place.YpdPlacePO;
import cn.datasset.yipandian.client.model.place.YpdPlaceVO;

/**
 * @author: Created by lzl
 */
public interface YpdPlaceService {

    List<YpdPlacePO> getPlaceList(String placeName);

    YpdPlaceVO getPlaceDetail(Integer id);

    ResultVO deletePlace(YpdPlaceDTO dto);

    ResultVO updatePlace(YpdPlaceDTO dto);

    ResultVO addPlace(YpdPlaceDTO dto);

    List<YpdPlacePO> getQueryList(String placeName);
}
