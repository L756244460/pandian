package cn.datasset.yipandian.client.enums.common;

/**
 * @author zhoukun
 * @version 1.0.0
 * @date 2022/6/29
 */
public interface IEnum<K, V> {

    K getKey();

    V getValue();
}
