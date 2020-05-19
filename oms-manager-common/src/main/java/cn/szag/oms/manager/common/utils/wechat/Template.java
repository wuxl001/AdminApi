package cn.szag.oms.manager.common.utils.wechat;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;

import cn.szag.oms.manager.common.utils.Url;

public class Template {

	private static Logger log = LoggerFactory.getLogger(Template.class);
	/**
	 * 客服接口给用户发送消息接口
	 */
	public static String content_openid = "https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token=ACCESS_TOKEN";

	/**
	 *
	 * @param openid
	 *            openid
	 * @param saveUrl
	 *            静态页面访问地址
	 * @return
	 */
	public TTResult contentTest(String openid, String saveUrl) {
		// 获得令牌
		String accessToken = WeChatUtil.getToken(WeChatExploitMessage.AppID, WeChatExploitMessage.AppSecret)
				.getAccessToken();
		// 替换token
		String url = content_openid.replace("ACCESS_TOKEN", accessToken);

		TestMessage testMessage = new TestMessage();
		// 设置消息的类型
		testMessage.setMsgtype("text");
		// 设置要发送的openid集合
		testMessage.setTouser(openid);
		// 创建集合
		Map<String, Object> map = new HashMap<>();
		// 设置发送内容
		map.put("content", saveUrl);
		testMessage.setText(map);
		// 将测试消息对象转成json
		String jsonTestMessage = JSON.toJSONString(testMessage);
		// 调用接口进行发送
		JSONObject jsonObject = WeChatUtil.httpsRequest(url, "POST", jsonTestMessage);

		log.error("分组群发消息失败 errcode:{" + jsonObject.getInt("errcode") + "} " + "errmsg:{"
				+ jsonObject.getString("errmsg") + "} ");
		Integer errcode = jsonObject.getInt("errcode");
		if (errcode != 0) {
			return TTResult.fail(errcode);
		}
		return TTResult.ok();

	}

	/* *//**
			 * 发送模板消息 appId 公众账号的唯一标识 appSecret 公众账号的密钥 openId 用户标识
			 * 
			 * @return
			 */
	public TTResult send_template_message(String openId, String templateId, String content,Map<String,Object> map,String goUrl) {
		Token token = WeChatUtil.getToken(WeChatExploitMessage.AppID, WeChatExploitMessage.AppSecret);// 这里要注意，如果你是申请的正式公众号的话，获取token的时候，一定要在后台加上你的ip，不然获取token的时候会报错
		String access_token = token.getAccessToken();
		String url = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=" + access_token;
		if(null!=map){
			goUrl +="?orderId="+map.get("orderId")+"&containerId="+map.get("containerId")+"&boxNo="+map.get("boxNo");
		}
		NewOrdersTemplate temp = createTemplate(openId, templateId, goUrl, content);//生成模板内容信息
		String jsonString = JSONObject.fromObject(temp).toString().replace("day", "Day");
		JSONObject jsonObject = WeChatUtil.httpsRequest(url, "POST", jsonString);
		System.out.println(jsonObject);
		int result = 0;
		if (null != jsonObject) {
			if (0 != jsonObject.getInt("errcode")) {
				result = jsonObject.getInt("errcode");
				log.error("错误 errcode:{} errmsg:{}", jsonObject.getInt("errcode"), jsonObject.getString("errmsg"));
				System.out.println(
						"错误 errcode:{} errmsg:{}" + jsonObject.getInt("errcode") + jsonObject.getString("errmsg"));
				return TTResult.fail(result);
			}
		}
		log.info("模板消息发送结果：" + result);
		return TTResult.ok();
	}

	public NewOrdersTemplate createTemplate(String openId,String templateId,String goUrl,String content) {
		// 因为我申请的模板是需要填写当前时间戳的，所以在这里我获取了当前的时间
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String format = simpleDateFormat.format(new Date());
		Data_style first = new Data_style();
		Data_style keyword1 = new Data_style();
		Data_style keyword2 = new Data_style();
		Data_style remark = new Data_style();

		NewOrdersTemplate temp = new NewOrdersTemplate();
		Data data = new Data();

		first.setValue("荟鲜生消息提醒");
		first.setColor("#173177");
		keyword1.setValue(format);
		keyword1.setColor("#173177");

		keyword2.setValue(content);
		keyword2.setColor("#173177");

		remark.setValue("");
		remark.setColor("#173177");

		data.setFirst(first);
		data.setKeyword1(keyword1);
		data.setKeyword2(keyword2);
		data.setRemark(remark);

		temp.setTouser(openId);
		temp.setTemplate_id(templateId);
		temp.setUrl(goUrl);
		temp.setTopcolor("#173177");
		temp.setData(data);
		return temp;
	}
}
/*
 * https://mp.weixin.qq.com/advanced/tmplmsg?action=faq&token=1244423508&lang=
 * zh_CN 这个是模板消息接口文档地址。 上面的 Data_style first = new Data_style(); Data_style
 * keyword1 = new Data_style(); Data_style keyword2 = new Data_style();
 * Data_style remark = new Data_style(); 根据你实际的模板消息的参数个数添加修改。我这里是两个参数的模板。
 */
