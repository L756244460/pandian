package cn.datasset.yipandian.client.model.dept;

import cn.datasset.yipandian.client.enums.common.GenderEnum;
import lombok.Data;

/**
 *
 * @author lzl
 */
@Data
public class YpdDeptUserRespVO {

    private String userId;

    private String userCode;

    private String realName;

    private String idCard;

    private String phoneNumber;

    private String userEmail;

    private String streetAddress;

    private GenderEnum gender;
}
