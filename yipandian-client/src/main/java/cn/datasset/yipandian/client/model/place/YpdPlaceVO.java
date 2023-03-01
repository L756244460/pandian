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
public class YpdPlaceVO {
    /**
     * id
     */
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
     * 地点全称
     */
    private String fullName;
}
