package cn.datasset.yipandian.client.controller;

import javax.annotation.Resource;

import cn.datasset.yipandian.client.code.ErrorCode;
import cn.datasset.yipandian.client.common.ResultVO;
import cn.datasset.yipandian.client.model.place.YpdPlaceDTO;
import cn.datasset.yipandian.client.service.place.YpdPlaceService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *     存放地点controller
 * </p>
 *
 * @author: Created by lzl
 */
@RestController
@RequestMapping("/place")
public class YpdPlaceController {

    @Resource
    YpdPlaceService ypdPlaceService;

    /**
     * 获取存放地点
     */
    @GetMapping("/get_place_list")
    public ResultVO getPlaceList(String keyword){
        return ResultVO.success(ypdPlaceService.getPlaceList(keyword));
    }

    //小程序端存放地点搜索列表
    @GetMapping("/get_query_list")
    public ResultVO getQueryList(String placeName){
        if (StringUtils.isEmpty(placeName)) {
            return ResultVO.fail(ErrorCode.PARAM_NULL);
        }
        return ResultVO.success(ypdPlaceService.getQueryList(placeName));
    }

    /**
     * 获取地点的详细信息
     */
    @GetMapping("/get_place_detail")
    public ResultVO getPlaceDetail(Integer id){
        if (null==id){
            return ResultVO.fail(ErrorCode.PARAM_NULL);
        }
        return ResultVO.success(ypdPlaceService.getPlaceDetail(id));
    }

    /**
     * 添加存放地点
     */
    @PostMapping("/add")
    public ResultVO addPlace(@RequestBody YpdPlaceDTO dto){
        if (StringUtils.isEmpty(dto.getAddPlaceName())) {
            return ResultVO.fail(ErrorCode.PARAM_NULL.getCode(), ErrorCode.PARAM_ERROR.getMessage());
        }
        return ypdPlaceService.addPlace(dto);
    }
    /**
     * 删除存放地点
     */
    @PostMapping("/delete")
    public ResultVO deletePlace(@RequestBody YpdPlaceDTO dto){
        if (null==dto.getId()||StringUtils.isEmpty(dto.getPlaceNo())){
            return ResultVO.fail(ErrorCode.PARAM_NULL);
        }
        return ypdPlaceService.deletePlace(dto);
    }

    /**
     * 更新存放地点
     */
    @PostMapping("/update")
    public ResultVO updatePlace(@RequestBody YpdPlaceDTO dto){
        if (null==dto.getId()){
            return ResultVO.fail(ErrorCode.PARAM_NULL);
        }
        return ypdPlaceService.updatePlace(dto);
    }

}
