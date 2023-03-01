package cn.datasset.yipandian.client.model.place;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @author: Created by lzl
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class YpdPlaceDTO {
    /**
     * id
     */
    private Integer id;
    /**
     * 地点编号
     */
    private String placeNo;
    /**
     * 地点名称
     */
    private String placeName;
    /**
     * 子节点当前可用序号
     */
    private Integer number;
    /**
     * 添加地点的名称
     */
    private String addPlaceName;
    /**
     * 是否是第一级
     */
    private Boolean flag;

    /**
     * 单位id
     */
    private String tenantId;
}
