package cn.datasset.yipandian.client.controller;

import java.util.List;

import javax.annotation.Resource;

import cn.datasset.yipandian.client.code.ErrorCode;
import cn.datasset.yipandian.client.common.ResultVO;
import cn.datasset.yipandian.client.exception.MessageException;
import cn.datasset.yipandian.client.model.user.GenderUpdReqVO;
import cn.datasset.yipandian.client.model.user.TenantDTO;
import cn.datasset.yipandian.client.model.user.YpdDeptUserReqVO;
import cn.datasset.yipandian.client.service.user.TenantService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lizhaolong
 * @create 2023-02-24 09:35
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private TenantService tenantService;

    /**
     * 授权管理查询
     */
    @PostMapping("/authorizationList")
    public ResultVO authorizationList(@RequestBody TenantDTO tenantDTO){
        return tenantService.authorizationList(tenantDTO);
    }

    /**
     * 启用授权
     */
    @PostMapping("/enableAuthorization")
    public ResultVO enableAuthorization(@RequestBody List<String> ids){
        return tenantService.enableAuthorization(ids);
    }

    /**
     * 禁用授权
     */
    @PostMapping("/disableAuthorization")
    public ResultVO disableAuthorization(@RequestBody List<String> ids){
        return tenantService.disableAuthorization(ids);
    }

    /**
     * 编辑用户性别
     */
    @PutMapping("/updateGender")
    public ResultVO updateGender(@RequestBody List<GenderUpdReqVO> genderUpdReqVOList) {
        tenantService.updateGender(genderUpdReqVOList);
        return ResultVO.success();
    }

    /**
     * 查询当前用户所在单位全部元数据信息
     *
     * @return TenantInfo
     */
    @GetMapping("/getTenantInfo")
    public ResultVO getTenantInfo() {
        return ResultVO.success(tenantService.getTenantInfo());
    }


    /**
     * 查询当前单位所有部门
     *
     * @return TenantInfo
     */
    @GetMapping("/getTenantAllDept")
    public ResultVO getTenantAllDept() {
        return ResultVO.success(tenantService.getTenantAllDept());
    }

    /**
     * 查询当前用户所在单位所有部门以及对应人员
     *
     * @return TenantInfo
     */
    @GetMapping("/getTenantAllDeptUser")
    public ResultVO getTenantAllDeptUser() {
        return ResultVO.success(tenantService.getTenantAllDeptUser());
    }

    /**
     * 查询部门对应人员(分页)
     * @return TenantInfo
     */
    @PostMapping("/getUserByDeptId")
    public ResultVO getUserByDept(@RequestBody YpdDeptUserReqVO reqVO) {
        if (StringUtils.isEmpty(reqVO.getId()) || StringUtils.isEmpty(reqVO.getParentId())) {
            throw new MessageException(ErrorCode.PARAM_NULL.getCode(), ErrorCode.PARAM_NULL.getMessage());
        }
        return ResultVO.success(tenantService.getUserByDeptId(reqVO));
    }




}
