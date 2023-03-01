package cn.datasset.yipandian.client.util;

import javax.annotation.Resource;

import cn.datasset.yipandian.client.code.ErrorCode;
import cn.datasset.yipandian.client.exception.MessageException;
import cn.datasset.yipandian.client.model.user.TenantPO;
import cn.datasset.yipandian.client.service.user.UserInfoService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

/**
 * @author lzl
 * @version 1.0
 */
@Slf4j
@Component
public class UserSession {

    @Resource
    private UserInfoService userInfoService;

    public TenantPO getUserInfo() {
        return userInfoService.getUserInfo();
    }

    public String getuserId() {
        TenantPO tenantPO = getUserInfo();
        if (StringUtils.isBlank(tenantPO.getId().toString())) {
            throw new MessageException(ErrorCode.PERSON_INVALID.getCode(), ErrorCode.PERSON_INVALID.getMessage());
        }
        return tenantPO.getId().toString();
    }

    public String getuserName() {
        TenantPO tenantPO = getUserInfo();
        if (StringUtils.isBlank(tenantPO.getUnitName())) {
            throw new MessageException(ErrorCode.PERSON_INVALID.getCode(), ErrorCode.PERSON_INVALID.getMessage());
        }
        return tenantPO.getUnitName();
    }

    public String getTenantCode() {
        TenantPO tenantPO = getUserInfo();
        if (StringUtils.isBlank(tenantPO.getTenantCode())) {
            throw new MessageException(ErrorCode.PERSON_INVALID.getCode(), ErrorCode.PERSON_INVALID.getMessage());
        }
        return tenantPO.getTenantCode();
    }

}
