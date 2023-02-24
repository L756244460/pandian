package cn.datasset.yipandian.client.model.user;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * <p>
 *     后台用戶表
 * </p>
 * @author: Created by lzl
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@TableName("ypd_user")
public class UserPO {
    /**
     * id
     */
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;
    /**
     * 电话号码
     */
    private String phoneNumber;
    /**
     * 密码
     */
    private String password;
    /**
     * 单位名称
     */
    private String unitName;

    /**
     * 单位code
     */
    private String tenantCode;
    /**
     * 账号状态
     * 0 启用
     * 1 停用
     */
    private Integer status;
    /**
     * 账号种类
     * 0 单位管理员
     * 1 超级管理员
     */
    private Integer type;

    /**
     * 修改时间
     */
    private Date updateTime;

    /**
     * 修改人
     */
    private String updateUser;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 创建人
     */
    private String createUser;

    /**
     * 注册时间
     */
    private Date registerTime;

    /**
     * 失效时间
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private Date failureTime;

    /**
     * 授权码
     */
    private String authorizationCode;

}
