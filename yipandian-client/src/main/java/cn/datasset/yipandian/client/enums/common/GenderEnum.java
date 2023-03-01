package cn.datasset.yipandian.client.enums.common;


import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * @author zhoukun
 * @version 1.0.0
 * @date 2022/6/29
 */
public enum GenderEnum implements IEnum<Integer, String> {
    FEMALE(2, "女"),
    MALE(1, "男");

    @EnumValue
    @JsonValue
    private Integer key;

    private String value;

    GenderEnum(Integer key, String value) {
        this.key = key;
        this.value = value;
    }

    @Override
    public Integer getKey() {
        return this.key;
    }

    @Override
    public String getValue() {
        return this.value;
    }
}
