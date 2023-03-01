package cn.datasset.yipandian.client.util;

import java.util.List;

import org.springframework.util.CollectionUtils;

/**
 * @author chendong
 * @data 2019/11/21 15:29
 * @description 字符串工具类
 */
public class StringUtils extends org.apache.commons.lang3.StringUtils {
	
	/**
	 * 字符串空判断
	 * @param str
	 * @return
	 * @author ChenDong
	 * @date 2019年5月28日
	 */
	public static boolean isEmpty(String str) {
		return (null == str || str.length() == 0 || "null".equals(str));
	}
	
	/**
	 * 字符串非空判断
	 * @param str
	 * @return
	 * @author ChenDong
	 * @date 2019年5月28日
	 */
	public static boolean isNotEmpty(String str) {
		return !isEmpty(str);
	}
	
	/**
	 * 将List<String>集合 转化为String
	 * 如{"aaa","bbb"} To 'aaa','bbb'
	 */
	public static String convertListToString(List<String> strlist) {
		StringBuffer sb = new StringBuffer();
		if (!CollectionUtils.isEmpty(strlist)) {
			for (int i = 0; i < strlist.size(); i++) {
				if (i == 0) {
					sb.append("'").append(strlist.get(i)).append("'");
				} else {
					sb.append(",").append("'").append(strlist.get(i)).append("'");
				}
			}
		}
		return sb.toString();
	}

	public static String autoGenericCode(Long code, int num) {
		String result = "";
		// 保留num的位数
		// 0 代表前面补充0
		// num 代表长度为4
		// d 代表参数为正数型
		result = String.format("%0" + num + "d", code);
		return result;
	}
}
