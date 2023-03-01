package cn.datasset.yipandian.client.model.user;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @author lizhaolong
 * @create 2023-02-22 09:47
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class TenantTokenInfo {

    /**
     * 用户登录的token
     *
     */
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private String accessToken;
    /**
     * 当前用户id
     */
    private String userId;
    /**
     * 电话号码
     */
    private String phoneNumber;

    /**
     *当前用户名称
     */
    private String name;

    @JsonProperty("isAdmin")
    private Integer admin;

}
