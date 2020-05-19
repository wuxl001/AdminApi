package cn.szag.oms.manager.common.utils;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.jiguang.common.ClientConfig;
import cn.jiguang.common.resp.APIConnectionException;
import cn.jiguang.common.resp.APIRequestException;
import cn.jpush.api.JPushClient;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.Message;
import cn.jpush.api.push.model.Options;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.SMS;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.audience.AudienceTarget;
import cn.jpush.api.push.model.notification.AndroidNotification;
import cn.jpush.api.push.model.notification.IosAlert;
import cn.jpush.api.push.model.notification.IosAlert.Builder;
import cn.jpush.api.push.model.notification.IosNotification;
import cn.jpush.api.push.model.notification.Notification;
import cn.szag.oms.manager.common.domain.manager.User;

public class PushExample {
	private static Logger logger = LoggerFactory.getLogger(PushExample.class);
	public final static String MASTER_SECRET2 = "67ec8bcfbc8c5a80645f6240";//业务38fa04ae404f2addccb691b2 
	public final static String APP_KEY2 = "45bfcbe7f6c206fb38c71ce2";//业务e98f3795c01f9a7dce76ad7d 
	public final static String MASTER_SECRET = "a25a49b6ac1aa9ca394614cf";//客户a21874c3ddec87d922558a4b 
	public final static String APP_KEY = "85e0beb6f87e0f6aa04e29ae";//客户d72b795813aefad755207c58 
	public final static String ALERT = "";
	public final static String MSG_CONTENT="";
	public final static String TITLE="";

	JPushClient jpushClient = new JPushClient(MASTER_SECRET, APP_KEY, null, ClientConfig.getInstance());
	// For push, all you need do is to build PushPayload object.
	PushPayload payload = buildPushObject_all_all_alert();
	/**
	 * 推送对象：所有平台
	* @Title: buildPushObject_all_all_alert 
	* @Description: TODO 
	* @param @return
	* @author dengyanghao
	* @return PushPayload
	* @throws
	 */
	public static PushPayload buildPushObject_all_all_alert() {
        return PushPayload.alertAll(ALERT);
    }
	/**
	 * 推送对象：Andorid
	* @Title: buildPushObject_android_tag_alertWithTitle 
	* @Description: TODO 
	* @param @return
	* @author dengyanghao
	* @return PushPayload
	* @throws
	 */
	public static PushPayload buildPushObject_android_tag_alertWithTitle() {
	    return PushPayload.newBuilder()
	                .setPlatform(Platform.android())
	                .setAudience(Audience.tag("tag1"))
	                .setNotification(Notification.android(ALERT, TITLE, null))
	                .build();
	}
	/**
	 * 推送对象：IOS
	* @Title: buildPushObject_ios_tagAnd_alertWithExtrasAndMessage 
	* @Description: TODO 
	* @param @return
	* @author dengyanghao
	* @return PushPayload
	* @throws
	 */
	public static PushPayload buildPushObject_ios_tagAnd_alertWithExtrasAndMessage() {
        return PushPayload.newBuilder()
                .setPlatform(Platform.ios())
                .setAudience(Audience.tag_and("tag1", "tag_all"))
                .setNotification(Notification.newBuilder()
                        .addPlatformNotification(IosNotification.newBuilder()
                                .setAlert(ALERT)
                                .setBadge(5)
                                .setSound("happy")
                                .addExtra("from", "JPush")
                                .build())
                        .build())
                 .setMessage(Message.content(MSG_CONTENT))
                 .setOptions(Options.newBuilder()
                         .setApnsProduction(true)
                         .build())
                 .build();
    }
	/**
	 * 推送对象：Andorid 与 iOS
	* @Title: buildPushObject_ios_audienceMore_messageWithExtras 
	* @Description: TODO 
	* @param @return
	* @author dengyanghao
	* @return PushPayload
	* @throws
	 */
	public static PushPayload buildPushObject_ios_audienceMore_messageWithExtras(String registrationId,String title,String msg) {
		msg =  msg.replaceAll("\\<.*?>", ""); 
        return PushPayload.newBuilder()
        		.setPlatform(Platform.android_ios())
                .setAudience(Audience.newBuilder()
                        .addAudienceTarget(AudienceTarget.registrationId(registrationId))
                        .build())
                .setNotification(Notification.newBuilder()
                        .setAlert(msg)
                        
                        .addPlatformNotification(AndroidNotification.newBuilder()
                                .setAlert(msg)
                                .setTitle(title)
                                .build()
                        )
                        .addPlatformNotification(IosNotification.newBuilder()
                                //传一个IosAlert对象，指定apns title、title、subtitle等
                                .setAlert(msg)
                                //直接传alert
                                //此项是指定此推送的badge自动加1
                                .incrBadge(+1)
                                //此字段的值default表示系统默认声音；传sound.caf表示此推送以项目里面打包的sound.caf声音来提醒，
                                // 如果系统没有此音频则以系统默认声音提醒；此字段如果传空字符串，iOS9及以上的系统是无声音提醒，以下的系统是默认声音
                                .setSound("sound.caf")
                                //此字段为透传字段，不会显示在通知栏。用户可以通过此字段来做一些定制需求，如特定的key传要指定跳转的页面（value）
                                //此项说明此推送是一个background推送，想了解background看：http://docs.jpush.io/client/ios_tutorials/#ios-7-background-remote-notification
                                // .setContentAvailable(true)
 
                                .build()
                        )
                        .build()
                )
                //Platform指定了哪些平台就会像指定平台中符合推送条件的设备进行推送。 jpush的自定义消息，
                // sdk默认不做任何处理，不会有通知提示。建议看文档http://docs.jpush.io/guideline/faq/的
                // [通知与自定义消息有什么区别？]了解通知和自定义消息的区别
                .setMessage(Message.newBuilder()
                        .setMsgContent(msg)
                        .build())
 
                .setOptions(Options.newBuilder()
                        //此字段的值是用来指定本推送要推送的apns环境，false表示开发，true表示生产；对android和自定义消息无意义
                        .setApnsProduction(false)
                        //此字段是给开发者自己给推送编号，方便推送者分辨推送记录
                        .setSendno(1)
                        //此字段的值是用来指定本推送的离线保存时长，如果不传此字段则默认保存一天，最多指定保留十天，单位为秒
                        .setTimeToLive(86400)
                        .build()
                )
                .build();
    }
	/**
	 * 推送对象：推送内容包含SMS信息
	* @Title: testSendWithSMS 
	* @Description: TODO 
	* @param 
	* @author dengyanghao
	* @return void
	* @throws
	 */
	public static void testSendWithSMS() {
        JPushClient jpushClient = new JPushClient(MASTER_SECRET, APP_KEY);
        try {
            SMS sms = SMS.newBuilder()
                    .setDelayTime(1000)
                    .setTempID(2000)
                    .addPara("Test", 1)
                    .build();
            PushResult result = jpushClient.sendAndroidMessageWithAlias("Test SMS", "test sms", sms, "alias1");
            logger.info("Got result - " + result);
            System.out.println("Got result - " + result);
        } catch (APIConnectionException e) {
        	logger.error("Connection error. Should retry later. ", e);
        	e.printStackTrace();
        } catch (APIRequestException e) {
        	logger.error("Error response from JPush server. Should review and fix it. ", e);
        	e.printStackTrace();
        	logger.info("HTTP Status: " + e.getStatus());
        	logger.info("Error Code: " + e.getErrorCode());
        	logger.info("Error Message: " + e.getErrorMessage());
        }
    }
	public static void main(String[] args) {
		JPushClient jpushClient = new JPushClient(MASTER_SECRET, APP_KEY);
		/*Builder b = new Builder();
		b.setTitleLoc(title_loc_key, title_loc_args)*///101d855909d08181096
		PushPayload payload = PushExample.buildPushObject_ios_audienceMore_messageWithExtras("170976fa8a17457e62c","海运进口","海运进口消息提醒2020-03-05 06:12:00 您的订单号为I200305012集装箱22处理结果为通过，请查看");
		try {
            PushResult pu = jpushClient.sendPush(payload);
            testSendWithSMS();
        } catch (APIConnectionException e) {
            e.printStackTrace();
        } catch (APIRequestException e) {
            e.printStackTrace();
        } catch (Exception e) {
        	e.printStackTrace();
		}
	}
}
