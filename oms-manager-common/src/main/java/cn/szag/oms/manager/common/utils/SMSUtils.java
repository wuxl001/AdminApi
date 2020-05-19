package cn.szag.oms.manager.common.utils;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;

/**
 * 短信发工具类
 */
public class SMSUtils {

    //public static final String VALIDATE_CODE = "SMS_168592018";//发短信验证码
    //public static final String ORDER_NOTICE = "SMS_168592017";//体检预约成功通知
    /**
     * 发短
     *
     * @param templateCode 短信模板 注册阿里云短 系列审核
     * @param phoneNumbers 手机号码
     * @param param 参数 验证
     * @throws ClientException
     */
    public static void sendShortMessage(String templateCode, String phoneNumbers, String param) throws ClientException {
        // 设置超时时间-可自行调
        System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
        System.setProperty("sun.net.client.defaultReadTimeout", "10000");
        // 初始化ascClient要的几个参数
        final String product = "Dysmsapi";// 短信API产品名称（短信产品名固定，无修改
        final String domain = "dysmsapi.aliyuncs.com";// 短信API产品域名（接口地固定，无修改
        // 替换成你的AK
        final String accessKeyId = "LTAIfdHTS3nXHr4n";// 你的accessKeyId,参本文档步骤2     ***********111********
        final String accessKeySecret = "JLytefLR0Awogm1y9sR2dM59SA1UWL";// 你的accessKeySecret，参考本文档步骤2 ***********222********
        // 初始化ascClient,暂时不支持多region（请勿修改）
        IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId, accessKeySecret);
        DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);
        IAcsClient acsClient = new DefaultAcsClient(profile);
        // 组装请求对象
        SendSmsRequest request = new SendSmsRequest();
        // 使用post提交
        request.setMethod(MethodType.POST);
        // 必填:待发送手机号。支持以逗号分隔的形式进行批量调用，批量上限1000个手机号,批量调用相对于单条调用及时稍有延,验证码类型的短信推荐使用单条调用的方
        request.setPhoneNumbers(phoneNumbers);
        // 必填:短信签名-可在短信控制台中找到
        request.setSignName("优鲜达科");//跟实际的注册账号的签名保持一  ***********333********
        // 必填:短信模板-可在短信控制台中找到
        request.setTemplateCode(templateCode);
        // 可:模板中的变量替换JSON,如模板内容为"亲爱${name},您的验证码为${code}",此处的为
        // 友情提示:如果JSON中需要带换行,请参照标准的JSON协议对换行符的要,比如短信内容中包含\r\n的情况在JSON中需要表示成\\r\\n,否则会导致JSON在服务端解析失败
        request.setTemplateParam("{\"code\":\"" + param + "\"}");//code 必须要跟短信模板中的key保持 ***********444********
        // 可-上行短信扩展(扩展码字段控制在7位或以下，无特殊求用户请忽略此字)
        // request.setSmsUpExtendCode("90997");
        // 可:outId为提供给业务方扩展字,终在短信回执消息中将此带回给调用
        // request.setOutId("yourOutId");
        // 请求失败这里会抛ClientException异常
        SendSmsResponse sendSmsResponse = acsClient.getAcsResponse(request);
        if (sendSmsResponse.getCode() != null && sendSmsResponse.getCode().equals("OK")) {
            // 请求成功
            System.out.println("请求成功");
        }
    }


    public static void main(String[] args) {
        //短信测试
        try {
            //SMSUtils.sendShortMessage(SMSUtils.VALIDATE_CODE,"15607115636","666888");
            SMSUtils.sendShortMessage("SMS_173761766","13026102303","666888");
        } catch (ClientException e) {
            e.printStackTrace();
        }
    }
}
