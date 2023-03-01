package cn.datasset.yipandian.client.model.user;

import cn.datasset.yipandian.client.model.Page;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 *
 * @author lzl
 */
@Data
@Accessors(chain = true)
public class YpdDeptUserReqVO extends Page {

    private String id;

    private String parentId;

    private String tenantCode;

    private String userCode;

    private String realName;

    private String idCard;

    private String phoneNumber;

    private String userEmail;

    private String streetAddress;

    private Integer gender;

}
