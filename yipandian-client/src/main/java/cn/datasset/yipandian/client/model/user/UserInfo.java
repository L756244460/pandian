package cn.datasset.yipandian.client.model.user;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * lzl
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class UserInfo {
    /**
     * 当前用户id
     */
    private String userId;

    /**
     * 当前用户名称
     */
    private String userName;

    /**
     * 电话号码
     */
    private String phonenumber;


    /**
     * 当前用户所属单位code
     */
    private String tenantCode;


}
