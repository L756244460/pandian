package cn.datasset.yipandian.client.constant;

/**
 * @author chendong
 * @date 2020/9/9 16:31
 * @description 静态变量
 */
public class Constants {

    /**
     * 数据有效
     */
    public static final Byte DATA_VALID = 1;

    /**
     * 数据无效
     */
    public static final Byte DATA_UNVALID = 0;

    /**
     * 用户冻结
     */
    public static final Byte USER_BLOCK = 2;

    /**
     * 黑名单用户
     */
    public static final Byte USER_BLACK = 3;

    /**
     * 集合为空时, total总数
     */
    public static final byte TOTAL_EMPTY = 0;

    /**
     * 前端传递的base64前缀-jpg
     */
    public static final String BASE64_PREFIX_jpeg = "data:image/jpeg;base64,";

    /**
     * 前端传递的base64前缀-png
     */
    public static final String BASE64_PREFIX_png = "data:image/png;base64,";

    /**
     * 加/解密
     */
    public static final String DES_ERROR = "DES KEY不能小于8位";

    /**
     * 密码加密盐值
     */
    public static final String SALT = "chd-salt";

    /**
     * 上报类型
     */
    public static final String DIC_TYPE_006 = "006";

    /**
     * 空字符串
     */
    public static final String EMPTY_STRING = "";

    /**
     * 字符串 英文 , 分割
     */
    public static final String STRING_SPLIT = ",";

    /**
     * 是/正式
     */
    public static final Integer IS_TRUE = 1;

    /**
     * 否/草稿
     */
    public static final Integer IS_FALSE = 0;

    /**
     * 资产信息
     */
    public static final String ASSET_INFO = "资产信息";

}
