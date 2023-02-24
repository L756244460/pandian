package cn.datasset.yipandian.client.util;

import javax.annotation.Resource;

import cn.datasset.yipandian.client.code.ErrorCode;
import cn.datasset.yipandian.client.exception.MessageException;
import cn.datasset.yipandian.client.model.user.UserPO;
import cn.datasset.yipandian.client.service.UserInfoService;
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

    public UserPO getUserInfo() {
        return userInfoService.getUserInfo();
    }

    public String getuserId() {
        UserPO userPO = getUserInfo();
        if (StringUtils.isBlank(userPO.getId().toString())) {
            throw new MessageException(ErrorCode.PERSON_INVALID.getCode(), ErrorCode.PERSON_INVALID.getMessage());
        }
        return userPO.getId().toString();
    }

    public String getuserName() {
        UserPO userPO = getUserInfo();
        if (StringUtils.isBlank(userPO.getUnitName())) {
            throw new MessageException(ErrorCode.PERSON_INVALID.getCode(), ErrorCode.PERSON_INVALID.getMessage());
        }
        return userPO.getUnitName();
    }

    public String getTenantCode() {
        UserPO userPO = getUserInfo();
        if (StringUtils.isBlank(userPO.getTenantCode())) {
            throw new MessageException(ErrorCode.PERSON_INVALID.getCode(), ErrorCode.PERSON_INVALID.getMessage());
        }
        return userPO.getTenantCode();
    }

}
