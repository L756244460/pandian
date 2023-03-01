package cn.datasset.yipandian.client.model.place;

import java.util.Date;
import java.util.List;
import java.util.Objects;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * @author: Created by lzl
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@TableName("ypd_place")
public class YpdPlacePO {
    /**
     * id
     */
    @TableId(type = IdType.AUTO)
    private Integer id;
    /**
     * 所属公司id
     */
    private String tenantId;
    /**
     * 地点编号
     */
    private String placeNo;
    /**
     * 地点名称
     */
    private String placeName;
    /**
     * 父id
     */
    private Integer pid;
    /**
     * 存放地点全称
     */
    @TableField(exist = false)
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private String fullName;
    /**
     * 逻辑删除（0未删除 / 1已删除）
     */
    @JsonIgnore
    @TableLogic(value = "0",delval = "1")
    private Integer deleted;
    /**
     * 创建时间
     */
    @JsonIgnore
    private Date createTime;
    /**
     * 更新时间
     */
    @JsonIgnore
    private Date updateTime;
    /**
     * 子节点当前可用序号
     */
    private Integer number;
    /**
     * 是否是末节点
     */
    private Boolean last;
    /**
     * 父地点名称
     */
    @TableField(exist = false)
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private String parentName;
    /**
     * 子节点集合
     */
    @TableField(exist = false)
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<YpdPlacePO> children;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        YpdPlacePO po = (YpdPlacePO) o;
        return id.equals(po.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
