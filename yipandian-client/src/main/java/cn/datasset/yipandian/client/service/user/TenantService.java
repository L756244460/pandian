package cn.datasset.yipandian.client.service.user;

import java.util.List;

import cn.datasset.yipandian.client.common.ResultVO;
import cn.datasset.yipandian.client.model.dept.YpdDeptUserRespVO;
import cn.datasset.yipandian.client.model.dept.YpdDeptVO;
import cn.datasset.yipandian.client.model.user.GenderUpdReqVO;
import cn.datasset.yipandian.client.model.user.TenantDTO;
import cn.datasset.yipandian.client.model.user.TenantPO;
import cn.datasset.yipandian.client.model.user.YpdDeptUserReqVO;
import com.baomidou.mybatisplus.core.metadata.IPage;

/**
 * @author lizhaolong
 * @create 2023-02-24 09:40
 */
public interface TenantService {
    ResultVO authorizationList(TenantDTO tenantDTO);

    ResultVO enableAuthorization(List<String> ids);

    ResultVO disableAuthorization(List<String> ids);

    void updateGender(List<GenderUpdReqVO> genderUpdReqVOList);

    TenantPO getTenantInfo();

    YpdDeptVO getTenantAllDept();

    YpdDeptVO getTenantAllDeptUser();

    IPage<YpdDeptUserRespVO> getUserByDeptId(YpdDeptUserReqVO reqVO);
}
