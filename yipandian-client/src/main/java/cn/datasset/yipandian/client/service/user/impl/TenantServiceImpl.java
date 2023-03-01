package cn.datasset.yipandian.client.service.user.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import cn.datasset.yipandian.client.common.ResultVO;
import cn.datasset.yipandian.client.mapper.TenantMapper;
import cn.datasset.yipandian.client.mapper.YpdDeptMapper;
import cn.datasset.yipandian.client.mapper.YpdDeptUserMapper;
import cn.datasset.yipandian.client.mapper.YpdUserMapper;
import cn.datasset.yipandian.client.model.dept.YpdDeptPO;
import cn.datasset.yipandian.client.model.dept.YpdDeptUserRespVO;
import cn.datasset.yipandian.client.model.dept.YpdDeptVO;
import cn.datasset.yipandian.client.model.user.GenderUpdReqVO;
import cn.datasset.yipandian.client.model.user.TenantDTO;
import cn.datasset.yipandian.client.model.user.TenantPO;
import cn.datasset.yipandian.client.model.user.YpdDeptUserPO;
import cn.datasset.yipandian.client.model.user.YpdDeptUserReqVO;
import cn.datasset.yipandian.client.model.user.YpdUserPO;
import cn.datasset.yipandian.client.service.user.TenantService;
import cn.datasset.yipandian.client.util.UserSession;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author lizhaolong
 * @create 2023-02-24 09:41
 */
@Service
@Slf4j
public class TenantServiceImpl implements TenantService {

    @Resource
    UserSession userSession;

    @Resource
    private TenantMapper tenantMapper;

    @Resource
    YpdDeptUserMapper ypdDeptUserMapper;

    @Resource
    YpdUserMapper ypdUserMapper;

    @Resource
    YpdDeptMapper ypdDeptMapper;

    @Override
    public ResultVO authorizationList(TenantDTO tenantDTO) {

        IPage<TenantPO> assetStockPage = tenantMapper.authorizationList(new Page<>(tenantDTO.getCurrent(), tenantDTO.getPageSize()),
            tenantDTO);

        return ResultVO.success(assetStockPage);
    }

    @Override
    public ResultVO enableAuthorization(List<String> ids) {
        String updateUserId = userSession.getuserId();
        if (tenantMapper.enableAuthorization(ids,updateUserId) > 0){
             return ResultVO.success();
         }
        return ResultVO.fail();
    }

    @Override
    public ResultVO disableAuthorization(List<String> ids) {
        String updateUserId = userSession.getuserId();
        if (tenantMapper.disableAuthorization(ids,updateUserId) > 0){
            return ResultVO.success();
        }
        return ResultVO.fail();
    }

    @Transactional(rollbackFor = Exception.class)
    public void updateGender(List<GenderUpdReqVO> genderUpdReqVOList) {
        for (GenderUpdReqVO genderUpdReqVO : genderUpdReqVOList) {
            ypdUserMapper.update(new YpdUserPO().setGender(genderUpdReqVO.getGender()),
                Wrappers.lambdaUpdate(YpdUserPO.class).eq(YpdUserPO::getTenantCode, userSession.getTenantCode())
                    .eq(YpdUserPO::getUserId, genderUpdReqVO.getUserId()));
        }
    }

    @Override
    public TenantPO getTenantInfo() {
        long l = System.currentTimeMillis();
        TenantPO tenantInfo = new TenantPO();
        String orgId = userSession.getTenantCode();
        List<YpdDeptUserPO> ypdDeptUserPos = ypdDeptUserMapper.selectList(new QueryWrapper<YpdDeptUserPO>().eq("tenant_code", orgId));
        List<YpdUserPO> ypdUserPos = ypdUserMapper.selectList(new QueryWrapper<YpdUserPO>().eq("tenant_code", orgId));
        YpdDeptVO depTree = ypdDeptMapper.getDepTreeByTenantCode(orgId);
        depTree.setAgencyName(userSession.getuserName());
        List<YpdDeptVO> ypdDeptVoList = new ArrayList<>();
        ypdDeptVoList.add(depTree);
        tenantInfo.setDept(ypdDeptVoList);
        tenantInfo.setUser(ypdUserPos);
        tenantInfo.setUserAndDept(ypdDeptUserPos);
        log.info("查询组织树：  组织：{}  耗时：{}", orgId, System.currentTimeMillis() - l);
        return tenantInfo;
    }


    @Override
    public YpdDeptVO getTenantAllDept() {
        String tenantCode = userSession.getTenantCode();
        YpdDeptVO depTree = ypdDeptMapper.getDepTreeByTenantCode(tenantCode);
        return depTree;
    }

    @Override
    public YpdDeptVO getTenantAllDeptUser() {
        String tenantCode = userSession.getTenantCode();
        List<YpdDeptPO> ypdDeptPOS = ypdDeptMapper.selectList(Wrappers.lambdaQuery(YpdDeptPO.class)
            .select(YpdDeptPO::getId, YpdDeptPO::getAgencyCode, YpdDeptPO::getAgencyName, YpdDeptPO::getParentId)
            .eq(YpdDeptPO::getTenantCode, tenantCode));
        Optional<YpdDeptPO> top = ypdDeptPOS.stream().filter(ypdDeptPO -> "0".equals(ypdDeptPO.getParentId())).findFirst();
        if (!top.isPresent()) {
            return null;
        }
        List<YpdUserPO> ypdUserPOS = ypdUserMapper.selectList(Wrappers.lambdaQuery(YpdUserPO.class)
            .select(YpdUserPO::getUserId, YpdUserPO::getRealName)
            .eq(YpdUserPO::getTenantCode, tenantCode));
        List<YpdDeptUserPO> ypdDeptUserPOS = ypdDeptUserMapper.selectList(Wrappers.lambdaQuery(YpdDeptUserPO.class)
            .select(YpdDeptUserPO::getAgencyId, YpdDeptUserPO::getUserId)
            .eq(YpdDeptUserPO::getTenantCode, tenantCode));
        Map<String, List<YpdDeptUserPO>> deptUserMap = ypdDeptUserPOS.stream().collect(Collectors.groupingBy(YpdDeptUserPO::getAgencyId));
        YpdDeptPO ypdDeptPO = top.get();
        YpdDeptVO ypdDeptVO = new YpdDeptVO();
        BeanUtils.copyProperties(ypdDeptPO, ypdDeptVO);
        List<String> existsDeptUserIds = ypdDeptUserPOS.stream().map(YpdDeptUserPO::getUserId).collect(Collectors.toList());
        ypdDeptVO.setYpdUserPos(ypdUserPOS.stream().filter(ypdUserPO -> !existsDeptUserIds.contains(ypdUserPO.getUserId())).collect(Collectors.toList()));
        doMakeDeptUser(ypdDeptVO, ypdDeptPOS, deptUserMap, ypdUserPOS);

        return ypdDeptVO;
    }

    private void doMakeDeptUser(YpdDeptVO ypdDeptVO, List<YpdDeptPO> ypdDeptPOS, Map<String, List<YpdDeptUserPO>> deptUserMap, List<YpdUserPO> ypdUserPOS) {
        List<YpdDeptVO> depts = ypdDeptPOS.stream().filter(ypdDeptPO -> ypdDeptPO.getParentId().equals(ypdDeptVO.getId()))
            .map(ypdDeptPO -> {
                YpdDeptVO deptVO = new YpdDeptVO();
                deptVO.setId(ypdDeptPO.getId())
                    .setAgencyName(ypdDeptPO.getAgencyName())
                    .setAgencyCode(ypdDeptPO.getAgencyCode());
                List<YpdDeptUserPO> ypdDeptUserPOS = deptUserMap.get(ypdDeptPO.getId());
                if (CollectionUtils.isNotEmpty(ypdDeptUserPOS)) {
                    List<String> userIds = ypdDeptUserPOS.stream().map(YpdDeptUserPO::getUserId).collect(Collectors.toList());
                    List<YpdUserPO> userVOs = ypdUserPOS.stream().filter(ypdUserPO -> userIds.contains(ypdUserPO.getUserId())).collect(Collectors.toList());
                    deptVO.setYpdUserPos(userVOs);
                } else {
                    deptVO.setYpdUserPos(new ArrayList<>());
                }
                doMakeDeptUser(deptVO, ypdDeptPOS, deptUserMap, ypdUserPOS);
                return deptVO;
            })
            .collect(Collectors.toList());
        ypdDeptVO.setChildren(depts);
    }

    @Override
    public IPage<YpdDeptUserRespVO> getUserByDeptId(YpdDeptUserReqVO reqVO) {
        String tenantCode = userSession.getTenantCode();
        reqVO.setTenantCode(tenantCode);
        if ("0".equals(reqVO.getParentId())) {
            reqVO.setId(null);
        }
        IPage<YpdDeptUserRespVO> page = new Page<>(reqVO.getCurrent(), reqVO.getPageSize());
        ypdDeptUserMapper.selectDeptUserList(page, reqVO);

        if (page.getTotal() <= 0) {
            return page;
        }
        return page;
    }
}
