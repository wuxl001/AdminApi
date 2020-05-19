package cn.szag.oms.manager.common.utils;

import java.util.UUID;

/**
 * 封装JDK自带的UUID, 通过Random数字生成
 * 
 * @author zhuxiaohai
 * @date 2017-08-08
 *
 */
public class UuidUtil {
	/**
	 * 封装JDK自带的UUID, 通过Random数字生成, 中间无-分割.
	 */
	public static String get32UUID() {
		String uuid = UUID.randomUUID().toString().trim().replaceAll("-", "");
		return uuid;
	}

	/**
	 * 封装JDK自带的UUID, 通过Random数字生成, 中间有-分割.
	 */
	public static String uuid() {
		return UUID.randomUUID().toString();
	}

	public static void main(String[] args) {
		System.out.println(uuid());
	}
}

