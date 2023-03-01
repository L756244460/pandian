package cn.datasset.yipandian.client.service.user;

import javax.servlet.http.HttpServletResponse;

import cn.datasset.yipandian.client.common.ResultVO;
import cn.datasset.yipandian.client.model.user.TenantDTO;
import cn.datasset.yipandian.client.model.user.TenantPO;

/**
 * @author: Created by zhoujian
 * @Date: 2021/12/6 14:29
 */
public interface LoginService {
    ResultVO login(TenantDTO dto, HttpServletResponse response);

    ResultVO logout();

    ResultVO register(TenantPO tenantPO);
}
