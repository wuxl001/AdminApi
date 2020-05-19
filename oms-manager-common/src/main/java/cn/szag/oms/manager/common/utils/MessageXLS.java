package cn.szag.oms.manager.common.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MessageXLS {
	static DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
	static Date date = new Date();//当前时间
	public static String returnMessage(String title,String extractOrderNum,String boxNo,String product){
		String str = title+"(时间："+df.format(new Date())+",提单号："+extractOrderNum+",集装箱："+boxNo+",商品【"+product+"】)。";
		return str;
	}
	
	public static String returnBookingMessage(String title,String booking,String boxNo,String product){
		String str = title+"(时间："+df.format(new Date())+",订舱号："+booking+",集装箱号："+boxNo+",商品【"+product+"】)。";
		return str;
	}
	public static String returnAuditMessage(String title,String orderNo,String bookingNo,String result){
		String str = title + "消息提醒(时间："+df.format(new Date())+" 您的订单号为" + orderNo + "订舱号"
				+ bookingNo + "处理结果为" + result + "，请查看";
		return str;
	}
	/**
	 * 公众号进口审核消息推送
	 */
	public static String ImportAuditMessage(String orderNo,String boxNo,String result){
		String str = "荟鲜生进口订单审核消息提醒\n提醒时间："+df.format(new Date())+"\n提醒内容：您的订单号为" + orderNo + "集装箱"
				+ boxNo + "处理结果为" + result + "。";
		return str;
	}
	/**
	 * 公众号进口补料消息推送
	 */
	public static String ImportFeedingMessage(String orderNo,String boxNo,String result){
		String str = "荟鲜生进口订单补料消息提醒\n提醒时间："+df.format(new Date())+"\n提醒内容： 您的订单号为" + orderNo + " 集装箱"
				+ boxNo + " 单证资料未齐。\n原因："+result;
		return str;
	}
	/**
	 * 公众号出口审核消息推送
	 */
	public static String ExportAuditMessage(String title,String orderNo,String bookingNo,String result){
		String str = "荟鲜生出口订单审核消息提醒\n提醒时间："+df.format(new Date())+"\n提醒内容：您的订单号为" + orderNo + "订舱号"
				+ bookingNo + "处理结果为" + result + "。";
		return str;
	}
}
