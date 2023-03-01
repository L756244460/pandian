package cn.datasset.yipandian.client.service.place.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import cn.datasset.yipandian.client.code.ErrorCode;
import cn.datasset.yipandian.client.common.ResultVO;
import cn.datasset.yipandian.client.mapper.YpdPlaceMapper;
import cn.datasset.yipandian.client.model.place.YpdPlaceDTO;
import cn.datasset.yipandian.client.model.place.YpdPlacePO;
import cn.datasset.yipandian.client.model.place.YpdPlaceVO;
import cn.datasset.yipandian.client.service.place.YpdPlaceService;
import cn.datasset.yipandian.client.util.StringUtils;
import cn.datasset.yipandian.client.util.UserSession;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author: Created by lzl
 */
@Service
@Slf4j
public class YpdPlaceServiceImpl implements YpdPlaceService {

    @Resource
    YpdPlaceMapper ypdPlaceMapper;

    @Resource
    UserSession userSession;


    private QueryWrapper<YpdPlacePO> getWrapper() {
        return new QueryWrapper<YpdPlacePO>().eq("tenant_id", userSession.getuserId());
    }

    //获取查询地点列表(小程序端)
    @Override
    public List<YpdPlacePO> getQueryList(String placeName) {
        QueryWrapper<YpdPlacePO> wrapper = getWrapper().ne("pid", -1);
        List<YpdPlacePO> list = ypdPlaceMapper.selectList(wrapper);
        if (StringUtils.isNotEmpty(placeName)) {
            List<YpdPlacePO> places = ypdPlaceMapper.selectList(getWrapper()
                    .like("place_name", placeName)
                    .eq("last", 1));
            if (CollectionUtils.isEmpty(places)) return null;
            //用来查询节点的父节点
            Map<Integer, YpdPlacePO> map = list.stream().collect(Collectors
                    .toMap(YpdPlacePO::getId, ypdPlacePO -> ypdPlacePO));

            places.forEach(ypdPlacePO -> {
                YpdPlacePO parent = map.get(ypdPlacePO.getPid());
                if (null!=parent)ypdPlacePO.setParentName(parent.getPlaceName());
            });
            return places;
        }
        return list;
    }

    @Override
    public List<YpdPlacePO> getPlaceList(String placeName) {
        QueryWrapper<YpdPlacePO> wrapper = getWrapper().ne("pid", -1);
        List<YpdPlacePO> list = ypdPlaceMapper.selectList(wrapper);
        if (StringUtils.isNotEmpty(placeName)) {
            List<YpdPlacePO> places = ypdPlaceMapper.selectList(getWrapper()
                    .like("place_name", placeName)
                    .eq("last", 1));
            if (CollectionUtils.isEmpty(places))
                return null;
            //用来查询节点的父节点
            Map<Integer, YpdPlacePO> map = list.stream().collect(Collectors
                    .toMap(YpdPlacePO::getId, ypdPlacePO -> ypdPlacePO));
            HashSet<YpdPlacePO> set = new HashSet<>();
            places.forEach(ypdPlacePO -> {
                while (ypdPlacePO.getPid() != 0) {
                    set.add(ypdPlacePO);
                    ypdPlacePO = map.get(ypdPlacePO.getPid());
                }
                set.add(ypdPlacePO);
            });
            list = new ArrayList<>(set);
        }

        Map<Integer, List<YpdPlacePO>> collect = list.stream().collect(Collectors.groupingBy(YpdPlacePO::getPid));
        list.forEach(node -> node.setChildren(collect.get(node.getId())));
        List<YpdPlacePO> ypdPlacePOS = collect.get(0);
        setValues(ypdPlacePOS, "");
        return ypdPlacePOS;
    }

    // 递归设置fullName属性
    private void setValues(List<YpdPlacePO> ypdPlacePOS, String fullName) {
        if (CollectionUtils.isEmpty(ypdPlacePOS)) return;
        ypdPlacePOS.forEach(ypdPlacePO -> {
            setValues(ypdPlacePO.getChildren(), fullName + ypdPlacePO.getPlaceName());
            ypdPlacePO.setFullName(fullName + ypdPlacePO.getPlaceName());
        });
    }

    @Override
    public YpdPlaceVO getPlaceDetail(Integer id) {
        QueryWrapper<YpdPlacePO> wrapper = getWrapper().eq("id", id);
        YpdPlaceVO vo = new YpdPlaceVO();
        YpdPlacePO po = ypdPlaceMapper.selectOne(wrapper);
        BeanUtils.copyProperties(po, vo);
        StringBuilder funName = new StringBuilder(vo.getPlaceName());
        int pid = vo.getPid();
        //获取地点的全路径名称
        for (int i = 0; i < 5; i++) {
            if (pid == 0)
                break;
            YpdPlacePO one = ypdPlaceMapper.selectOne(getWrapper().eq("id", pid));
            pid = one.getPid();
            funName.insert(0, one.getPlaceName());
        }
        vo.setFullName(funName.toString());
        return vo;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public ResultVO deletePlace(YpdPlaceDTO dto) {
        int num;
        //判断是否是末级
        num = ypdPlaceMapper.selectCount(getWrapper().eq("pid", dto.getId()));
        if (num != 0) {
            return ResultVO.fail(ErrorCode.REMOVE_ERROR.getCode(), "请先删除子节点");
        }
        YpdPlacePO one = ypdPlaceMapper.selectOne(new QueryWrapper<YpdPlacePO>().eq("id", dto.getId()));
        ypdPlaceMapper.delete(getWrapper().eq("id", dto.getId()));
        //更新父地点是否为叶子节点状态
        Integer count = ypdPlaceMapper.selectCount(new QueryWrapper<YpdPlacePO>().eq("pid", one.getPid()));
        if (count == 0) {
            ypdPlaceMapper.update(new YpdPlacePO().setLast(true), new QueryWrapper<YpdPlacePO>().eq("id", one.getPid()));
        }
        return ResultVO.success();
    }

    @Override
    public ResultVO updatePlace(YpdPlaceDTO dto) {
        String placeName = dto.getPlaceName();
        if (StringUtils.isEmpty(placeName)) {
            return ResultVO.fail(ErrorCode.UPDATE_ERROR.getCode(), ErrorCode.UPDATE_ERROR.getMessage());
        }
        YpdPlacePO ypdPlacePO = new YpdPlacePO();
        ypdPlacePO.setPlaceName(placeName);
        ypdPlaceMapper.update(ypdPlacePO, getWrapper().eq("id", dto.getId()));
        return ResultVO.success();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResultVO addPlace(YpdPlaceDTO dto) {
        String addPlaceName = dto.getAddPlaceName();
        String tenantId = userSession.getuserId();
        //获取编号
        String placeNo = this.getPlaceNo(dto);
        YpdPlacePO ypdPlacePO = new YpdPlacePO();
        //第一级地点生成
        if (dto.getFlag()) {
            log.info("第一级placeNo：{}", placeNo);
            ypdPlacePO.setTenantId(tenantId)
                    .setPlaceName(addPlaceName)
                    .setPid(0)
                    .setPlaceNo(placeNo);
        } else {
            //其他级地点生成
            if (null == dto.getId()) return ResultVO.fail(ErrorCode.PARAM_NULL.getCode(), ErrorCode.PARAM_ERROR.getMessage());
            log.info("其他级placeNo：{}", placeNo);
            ypdPlacePO.setTenantId(tenantId)
                    .setPlaceName(addPlaceName)
                    .setPid(dto.getId())
                    .setPlaceNo(placeNo);
        }
        //是否进行编号复用
        if (StringUtils.isEmpty(ypdPlacePO.getPlaceNo())) {
            //编号复用
            placeNo = this.reusePlaceNo(dto);
            if (null == placeNo) return ResultVO.fail(ErrorCode.COMMON_ERROR.getCode(), "无可用编号，添加失败");
            log.info("复用placeNo：{}", placeNo);
            ypdPlaceMapper.update(new YpdPlacePO().setPlaceName(addPlaceName).setDeleted(0),
                    new QueryWrapper<YpdPlacePO>().eq("tenant_id", tenantId)
                            .eq("place_no", placeNo));
        } else {
            //编号新增
            ypdPlaceMapper.insert(ypdPlacePO);
        }

        //更新父地点是否为叶子节点状态
        if (!dto.getFlag()) {
            ypdPlaceMapper.update(new YpdPlacePO().setLast(false), new QueryWrapper<YpdPlacePO>().eq("id", dto.getId()));
        }
        return ResultVO.success();
    }


    //存放地点编号生成
    private synchronized String getPlaceNo(YpdPlaceDTO dto) {
        //第一级地点编号生成
        if (dto.getFlag()) {
            YpdPlacePO one = ypdPlaceMapper.selectOne(new QueryWrapper<YpdPlacePO>()
                    .eq("tenant_id", userSession.getuserId())
                    .eq("pid", -1));
            if (one != null)
                if (one.getNumber() <= 999) {
                    ypdPlaceMapper.updateCount(one.getId());
                    return one.getPlaceNo() + StringUtils.autoGenericCode(Integer.toUnsignedLong(one.getNumber()), 3);
                } else return null;
            else {
                YpdPlacePO po = new YpdPlacePO().setPlaceNo("CFDD").setPid(-1)
                        .setNumber(2).setTenantId(userSession.getuserId());
                ypdPlaceMapper.insert(po);
                return "CFDD001";
            }
        } else {
            YpdPlacePO one = ypdPlaceMapper.selectOne(new QueryWrapper<YpdPlacePO>().eq("id", dto.getId()));
            //其他级地点编号生成
            if (one.getNumber() <= 999) {
                ypdPlaceMapper.updateCount(dto.getId());
                return one.getPlaceNo() + StringUtils.autoGenericCode(Integer.toUnsignedLong(one.getNumber()), 3);
            }
            return null;
        }
    }

    //存放地点编号复用
    private String reusePlaceNo(YpdPlaceDTO dto) {
        dto.setTenantId(userSession.getuserId());
        List<YpdPlacePO> list = ypdPlaceMapper.selectReuseList(dto);
        if (CollectionUtils.isEmpty(list)) return null;
        return list.get(0).getPlaceNo();
    }
}
