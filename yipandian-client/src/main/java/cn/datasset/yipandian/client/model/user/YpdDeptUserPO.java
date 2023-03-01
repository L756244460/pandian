package cn.datasset.yipandian.client.model.user;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
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
@TableName("ypd_dept_user")
public class YpdDeptUserPO {

    /**主键id*/
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**关联的部门id*/
    private String agencyId;
    /**关联的用户id*/
    private String userId;
    /**租户id*/
    private String tenantCode;

}
