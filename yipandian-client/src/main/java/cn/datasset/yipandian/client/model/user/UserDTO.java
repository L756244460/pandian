package cn.datasset.yipandian.client.model.user;

import java.util.Date;

import cn.datasset.yipandian.client.model.Page;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @author: Created by lzl
 * @Date: 2021/12/2 11:47
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class UserDTO extends Page {
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
     * 账号授权状态
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
    private Date failureUser;

    /**
     * 单位code
     */
    private String tenantCode;

    /**
     * 授权码
     */
    private String authorizationCode;

    /**
     * 注册开始时间
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private Date beginTime;

    /**
     * 注册结束时间
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private Date endTime;


}
