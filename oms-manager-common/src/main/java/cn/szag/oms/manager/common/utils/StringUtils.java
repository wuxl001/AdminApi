package cn.szag.oms.manager.common.utils;

import java.math.BigDecimal;

public class StringUtils extends org.apache.commons.lang3.StringUtils {
	/**
	 * 字符串转长整型
	 * 
	 * @param str
	 * @return
	 */
	public static Long str2Long(String str) {
		if (org.apache.commons.lang3.StringUtils.isEmpty(str)) {
			return null;
		}
		if (org.apache.commons.lang3.StringUtils.isNumeric(str)) {
			try {
				return Long.parseLong(str);
			} catch (Exception e) {
				return null;
			}
		}

		return null;
	}

	/**
	 * 字符串转整型
	 * 
	 * @param str
	 * @return
	 */
	public static Integer str2Integer(String str) {
		if (org.apache.commons.lang3.StringUtils.isEmpty(str)) {
			return null;
		}
		if (org.apache.commons.lang3.StringUtils.isNumeric(str)) {
			try {
				return Integer.parseInt(str);
			} catch (Exception e) {
				return null;
			}
		}

		return null;
	}

	/**
	 * 字符串转整型
	 * 
	 * @param str
	 * @return
	 */
	public static int str2Int(String str) {
		if (org.apache.commons.lang3.StringUtils.isEmpty(str)) {
			return 0;
		}
		if (org.apache.commons.lang3.StringUtils.isNumeric(str)) {
			try {
				return Integer.parseInt(str);
			} catch (Exception e) {
				return 0;
			}
		}

		return 0;
	}

	public static BigDecimal str2BigDecimal(String str) {
		if (org.apache.commons.lang3.StringUtils.isEmpty(str)) {
			return null;
		}
		if (org.apache.commons.lang3.StringUtils.isNumeric(str)) {
			try {
				return new BigDecimal(str);
			} catch (Exception e) {
				return null;
			}
		}

		return null;
	}
}
