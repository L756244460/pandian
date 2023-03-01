package cn.datasset.yipandian.client.model.user;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @author lzl
 * @version 1.0
 * @date 2021/11/1 20:14
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@TableName("ypd_user")
public class YpdUserPO {

    /**
     * id
     */
    private String id;
    /**
     * 对应用户数据Id
     */
    private String userId;
    /**
     * 租户id
     */
    private String tenantCode;
    /**
     * 租户id
     */
    @TableField(exist = false)
    private Long businessId;
    /**
     * 城市
     */
    private String city;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 创建人
     */
    private String createUser;
    /**
     * 性别：1、男2、女
     */
    private Integer gender;
    /**
     * 身份证号码
     */
    private String idCard;
    /**
     * 是否已删除
     */
    private Integer isDeleted;
    /**
     * 是否是主单位
     */
    private String isMaster;
    /**
     * 主单位人员Id
     */
    private Long masterPersonId;
    /**
     * 主单位Code
     */
    private String masterTenantCode;
    /**
     * 主单位Id
     */
    private String masterUnitId;
    /**
     * 用户手机号
     */
    private String phoneNumber;
    /**
     * 省份
     */
    private String province;
    /**
     * 真实姓名
     */
    private String realName;
    /**
     * 状态
     */
    private String status;
    /**
     * 街道
     */
    private String streetAddress;
    /**
     * 更新时间
     */
    private Date updateTime;
    /**
     * 更新人
     */
    private Date updateUser;
    /**
     * 出生日期
     */
    private Date userBirthday;
    /**
     * 用户code
     */
    private String userCode;
    /**
     * 用户邮箱
     */
    private String userEmail;
    /**
     * 用户照片
     */
    private String userPhoto;
}
