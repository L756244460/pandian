package cn.datasset.yipandian.client.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

/**
 * @author chendong
 * @date 2020/9/9 17:14
 * @description 分页page
 */
@Data
public class Page {

    /**
     * 当前页数
     */
    @TableField(exist = false)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer current = 1;

    /**
     * 每页记录数
     */
    @TableField(exist = false)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer pageSize = 20;


    /**
     * 偏移量
     */
    @TableField(exist = false)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer offset;

    public Integer getOffset() {
        offset = current < 1 ? 0 : (current - 1) * pageSize;
        return offset;
    }

}
