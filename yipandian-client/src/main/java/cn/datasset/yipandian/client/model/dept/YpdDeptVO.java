package cn.datasset.yipandian.client.model.dept;

import java.util.Date;
import java.util.List;

import cn.datasset.yipandian.client.model.user.YpdUserPO;
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
public class YpdDeptVO {
    /**主键*/
    private String id;
    /**状态 1正常*/
    private Integer status;
    /**是否删除 1是 0否*/
    private Integer isDeleted;
    /**部门名称*/
    private String agencyName;
    /**部门code*/
    private String agencyCode;
    /**租客code*/
    private String tenantCode;
    /**父级id*/
    private String parentId;
    /***/
    private String createUser;
    /***/
    private Date createTime;
    /***/
    private Date updateTime;
    /***/
    private String updateUser;

    /**
     * 部门下的组织
     */
    private List<YpdDeptVO> children;

    /**
     * 部门下的人员
     */
    private List<YpdUserPO> ypdUserPos;

    /**
     * 用户 id 字符串，多个值用“，”号分割
     */
    private String userIds;

    /**
     * 部门下删除的用户id
     */
    private String delUserIds;
    /**
     * ids
     */
    private List<String> ids;
}
