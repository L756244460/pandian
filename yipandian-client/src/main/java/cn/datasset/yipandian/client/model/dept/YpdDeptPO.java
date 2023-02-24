package cn.datasset.yipandian.client.model.dept;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @author lzl
 * @version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@TableName("ypd_dept")
public class YpdDeptPO {
    /**
     * 主键
     */
    private String id;
    /**
     * 状态 1正常
     */
    private Integer status;
    /**
     * 是否删除 1是 0否
     */
    private Integer isDeleted;
    /**
     * 部门名称
     */
    private String agencyName;
    /**
     * 部门code
     */
    private String agencyCode;
    /**
     * 租客code
     */
    private String tenantCode;
    /**
     * 父级id
     */
    private String parentId;
    /***/
    private String createUser;
    /***/
    private Date createTime;
    /***/
    private Date updateTime;
    /***/
    private String updateUser;
}
