package cn.datasset.yipandian.client.model.user;

import java.util.Date;
import java.util.List;

import cn.datasset.yipandian.client.model.dept.YpdDeptPO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @author lzl
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class YpdUserVO {
    /**id*/
    private String id;
    /**对应用户数据Id*/
    private String userId;
    /**租户id*/
    private String tenantCode;
    /**城市*/
    private String city;
    /**创建时间*/
    private Date createTime;
    /**创建人*/
    private String createUser;
    /**性别：1、男2、女*/
    private Integer gender;
    /**身份证号码*/
    private String idCard;
    /**是否已删除*/
    private Integer isDeleted;
    /**是否是主单位*/
    private String isMaster;
    /**主单位人员Id*/
    private Long masterPersonId;
    /**主单位Code*/
    private String masterTenantCode;
    /**主单位Id*/
    private String masterUnitId;
    /**用户手机号*/
    private String phoneNumber;
    /**省份*/
    private String province;
    /**真实姓名*/
    private String realName;
    /**状态*/
    private String status;
    /**街道*/
    private String streetAddress;
    /**更新时间*/
    private Date updateTime;
    /**更新人*/
    private Date updateUser;
    /**出生日期
     */
    private Date userBirthday;
    /**用户code*/
    private String userCode;
    /**用户邮箱*/
    private String userEmail;
    /**用户照片*/
    private String userPhoto;

    /**
     * 用户所属部门
     */
    private List<YpdDeptPO>  deptPos;
}
