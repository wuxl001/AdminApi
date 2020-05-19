package cn.szag.oms.manager.common.utils;


import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.QuerySendDetailsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.QuerySendDetailsResponse;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
/**
 * Created on 17/6/7.
 * 短信API产品的DEMO程序,工程中包含了个SmsDemo类，直接通过
 * 执行main函数即可体验短信产品API功能(只需要将AK替换成开通了云信-短信产品功能的AK即可)
 * 工程依赖2个jar(存放在工程的libs目录)
 * 1:aliyun-java-sdk-core.jar
 * 2:aliyun-java-sdk-dysmsapi.jar
 *
 * 备注:Demo工程编码采用UTF-8
 * 国际短信发请勿参照此DEMO
 */
public class SmsDemo {

    //产品名称:云信短信API产品,发无替换
    static final String product = "Dysmsapi";
    //产品域名,发无替换
    static final String domain = "dysmsapi.aliyuncs.com";

    // TODO 此处要替换成发自己的AK(在阿里云访问控制台寻)
    private static String accessKeyId;
    private static String accessKeySecret;
    private static String signname;
    private static String templatecode;
    private static String templateparam;
    private static String outid;
    private static String sMItemplatecode;
    private static String sMItemplateparam;
    private static String signnameG;
    private static String templatecodeG;
    private static String signnameR;
    private static String templatecodeR;


    public static SendSmsResponse sendSms(String phone,String product1,Integer code) throws ClientException {

        //可自助调整超时时
        System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
        System.setProperty("sun.net.client.defaultReadTimeout", "10000");

        //初始化acsClient,暂不支持region
        IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId, accessKeySecret);
        DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);
        IAcsClient acsClient = new DefaultAcsClient(profile);

        //组装请求对象-具体描述见控制台-文档部分内容
        SendSmsRequest request = new SendSmsRequest();
        //必填:待发送手机号
        request.setPhoneNumbers(phone);
        //必填:短信签名-可在短信控制台中找到
      /*  String mm=null;
        try {
			 mm=new String (signname.getBytes("ISO-8859-1"),"UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
        request.setSignName(signname);
        //必填:短信模板-可在短信控制台中找到
        request.setTemplateCode(templatecode);
        //可:模板中的变量替换JSON,如模板内容为"亲爱${name},您的验证码为${code}",此处的为
        String dd[]=templateparam.split(",");
        String cc[]=dd[0].split(":");
        cc[1]="\""+code+"\"";
        String db="{"+cc[0]+":"+cc[1]+"}";
        request.setTemplateParam(db);
        //选填-上行短信扩展(无特殊需求用户请忽略此字)
        //request.setSmsUpExtendCode("90997");
         
        //可:outId为提供给业务方扩展字,终在短信回执消息中将此带回给调用
        request.setOutId(outid);

        //hint 此处可能会抛出异常，注意catch
        SendSmsResponse sendSmsResponse = acsClient.getAcsResponse(request);

        return sendSmsResponse;
    }
    
    public static SendSmsResponse sendSms1(String phone,String name,String code) throws ClientException {

        //可自助调整超时时
        System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
        System.setProperty("sun.net.client.defaultReadTimeout", "10000");

        //初始化acsClient,暂不支持region
        IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId, accessKeySecret);
        DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);
        IAcsClient acsClient = new DefaultAcsClient(profile);

        //组装请求对象-具体描述见控制台-文档部分内容
        SendSmsRequest request = new SendSmsRequest();
        //必填:待发送手机号
        request.setPhoneNumbers(phone);
        //必填:短信签名-可在短信控制台中找到
        String mm=null;
        try {
			 mm=new String (signname.getBytes("ISO-8859-1"),"UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        request.setSignName(mm);
        //必填:短信模板-可在短信控制台中找到
        request.setTemplateCode(sMItemplatecode);
        //可:模板中的变量替换JSON,如模板内容为"亲爱${name},您的验证码为${code}",此处的为
        String dd[]=sMItemplateparam.split(",");
        String cc[]=dd[0].split(":");
        cc[1]="\""+code+"\"";
        String bb[]=dd[1].split(":"); 
        bb[1]="\""+name+"\"";
        String db="{"+cc[0]+":"+cc[1]+","+bb[0]+":"+bb[1]+"}";
        request.setTemplateParam(db);
        //选填-上行短信扩展(无特殊需求用户请忽略此字)
        //request.setSmsUpExtendCode("90997");
         
        //可:outId为提供给业务方扩展字,终在短信回执消息中将此带回给调用
        request.setOutId(outid);

        //hint 此处可能会抛出异常，注意catch
        SendSmsResponse sendSmsResponse = acsClient.getAcsResponse(request);

        return sendSmsResponse;
    }
    

    public static QuerySendDetailsResponse querySendDetails(String bizId,String phone) throws ClientException {

        //可自助调整超时时
        System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
        System.setProperty("sun.net.client.defaultReadTimeout", "10000");

        //初始化acsClient,暂不支持region
        IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId, accessKeySecret);
        DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);
        IAcsClient acsClient = new DefaultAcsClient(profile);

        //组装请求对象
        QuerySendDetailsRequest request = new QuerySendDetailsRequest();
        //必填-号码
        request.setPhoneNumber(phone);
        //可-流水
        request.setBizId(bizId);
        //必填-发日 支持30天内记录查询，格式yyyyMMdd
        SimpleDateFormat ft = new SimpleDateFormat("yyyyMMdd");
        request.setSendDate(ft.format(new Date()));
        //必填-页大
        request.setPageSize(10L);
        //必填-当前页码1始计
        request.setCurrentPage(1L);

        //hint 此处可能会抛出异常，注意catch
        QuerySendDetailsResponse querySendDetailsResponse = acsClient.getAcsResponse(request);

        return querySendDetailsResponse;
    }

    public static void main(String[] args) throws ClientException, InterruptedException {

    
    }
    
    public static SendSmsResponse sendSmsRegisteredG(String phone,String name,String code) throws ClientException {

        //可自助调整超时时
        System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
        System.setProperty("sun.net.client.defaultReadTimeout", "10000");

        //初始化acsClient,暂不支持region
        IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId, accessKeySecret);
        DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);
        IAcsClient acsClient = new DefaultAcsClient(profile);

        //组装请求对象-具体描述见控制台-文档部分内容
        SendSmsRequest request = new SendSmsRequest();
        //必填:待发送手机号
        request.setPhoneNumbers(phone);
        //必填:短信签名-可在短信控制台中找到
        String mm=null;
        try {
			 mm=new String (signname.getBytes("ISO-8859-1"),"UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        request.setSignName(mm);
        //必填:短信模板-可在短信控制台中找到
        request.setTemplateCode(signnameR);
        //可:模板中的变量替换JSON,如模板内容为"亲爱${name},您的验证码为${code}",此处的为
        String dd[]=templatecodeR.split(",");
        String cc[]=dd[0].split(":");
        cc[1]="\""+code+"\"";
        String bb[]=dd[1].split(":"); 
        bb[1]="\""+name+"\"";
        String db="{"+cc[0]+":"+cc[1]+","+bb[0]+":"+bb[1]+"}";
        request.setTemplateParam(db);
        //选填-上行短信扩展(无特殊需求用户请忽略此字)
        //request.setSmsUpExtendCode("90997");
         
        //可:outId为提供给业务方扩展字,终在短信回执消息中将此带回给调用
        request.setOutId(outid);

        //hint 此处可能会抛出异常，注意catch
        SendSmsResponse sendSmsResponse = acsClient.getAcsResponse(request);

        return sendSmsResponse;
    }
    
    public boolean DapiRegisteredG(String phone,String product,String code){

        try {
        	//发短
            SendSmsResponse response = sendSmsRegisteredG(phone,product,code);
            System.out.println("短信接口返回的数----------------");
            System.out.println("Code=" + response.getCode());
            System.out.println("Message=" + response.getMessage());
            System.out.println("RequestId=" + response.getRequestId());
            System.out.println("BizId=" + response.getBizId());

            Thread.sleep(100L);

            //查明
            if(response.getCode() != null && response.getCode().equals("OK")) {
                QuerySendDetailsResponse querySendDetailsResponse = querySendDetails(response.getBizId(),phone);
               System.out.println("短信明细查询接口返回数据----------------");
                System.out.println("Code=" + querySendDetailsResponse.getCode());
                System.out.println("Message=" + querySendDetailsResponse.getMessage());
                int i = 0;
                for(QuerySendDetailsResponse.SmsSendDetailDTO smsSendDetailDTO : querySendDetailsResponse.getSmsSendDetailDTOs())
                {
                    System.out.println("SmsSendDetailDTO["+i+"]:");
                    System.out.println("Content=" + smsSendDetailDTO.getContent());
                    System.out.println("ErrCode=" + smsSendDetailDTO.getErrCode());
                    System.out.println("OutId=" + smsSendDetailDTO.getOutId());
                    System.out.println("PhoneNum=" + smsSendDetailDTO.getPhoneNum());
                    System.out.println("ReceiveDate=" + smsSendDetailDTO.getReceiveDate());
                    System.out.println("SendDate=" + smsSendDetailDTO.getSendDate());
                    System.out.println("SendStatus=" + smsSendDetailDTO.getSendStatus());
                    System.out.println("Template=" + smsSendDetailDTO.getTemplateCode());
                }
                System.out.println("TotalCount=" + querySendDetailsResponse.getTotalCount());
                System.out.println("RequestId=" + querySendDetailsResponse.getRequestId());
                return true;
            }
        } catch (Exception e) {
			// TODO: handle exception
        	e.printStackTrace();
		}
     
        
        return false;
        

    }
    
    public static SendSmsResponse sendSmsG(String phone,String product1,Integer code) throws ClientException {

        //可自助调整超时时
        System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
        System.setProperty("sun.net.client.defaultReadTimeout", "10000");

        //初始化acsClient,暂不支持region
        IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId, accessKeySecret);
        DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);
        IAcsClient acsClient = new DefaultAcsClient(profile);

        //组装请求对象-具体描述见控制台-文档部分内容
        SendSmsRequest request = new SendSmsRequest();
        //必填:待发送手机号
        request.setPhoneNumbers(phone);
        //必填:短信签名-可在短信控制台中找到
      /*  String mm=null;
        try {
			 mm=new String (signname.getBytes("ISO-8859-1"),"UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
        request.setSignName(signname);
        //必填:短信模板-可在短信控制台中找到
        request.setTemplateCode(signnameG);
        //可:模板中的变量替换JSON,如模板内容为"亲爱${name},您的验证码为${code}",此处的为
        String dd[]=templatecodeG.split(",");
        String cc[]=dd[0].split(":");
        cc[1]="\""+code+"\"";
        String db="{"+cc[0]+":"+cc[1]+"}";
        request.setTemplateParam(db);
        //选填-上行短信扩展(无特殊需求用户请忽略此字)
        //request.setSmsUpExtendCode("90997");
         
        //可:outId为提供给业务方扩展字,终在短信回执消息中将此带回给调用
        request.setOutId(outid);

        //hint 此处可能会抛出异常，注意catch
        SendSmsResponse sendSmsResponse = acsClient.getAcsResponse(request);

        return sendSmsResponse;
    }
    public boolean DapiG(String phone,String product,Integer code){

        try {
        	//发短
            SendSmsResponse response = sendSmsG(phone,product,code);
            System.out.println("短信接口返回的数----------------");
            System.out.println("Code=" + response.getCode());
            System.out.println("Message=" + response.getMessage());
            System.out.println("RequestId=" + response.getRequestId());
            System.out.println("BizId=" + response.getBizId());

            Thread.sleep(100L);

            //查明
            if(response.getCode() != null && response.getCode().equals("OK")) {
                QuerySendDetailsResponse querySendDetailsResponse = querySendDetails(response.getBizId(),phone);
               System.out.println("短信明细查询接口返回数据----------------");
                System.out.println("Code=" + querySendDetailsResponse.getCode());
                System.out.println("Message=" + querySendDetailsResponse.getMessage());
                int i = 0;
                for(QuerySendDetailsResponse.SmsSendDetailDTO smsSendDetailDTO : querySendDetailsResponse.getSmsSendDetailDTOs())
                {
                    System.out.println("SmsSendDetailDTO["+i+"]:");
                    System.out.println("Content=" + smsSendDetailDTO.getContent());
                    System.out.println("ErrCode=" + smsSendDetailDTO.getErrCode());
                    System.out.println("OutId=" + smsSendDetailDTO.getOutId());
                    System.out.println("PhoneNum=" + smsSendDetailDTO.getPhoneNum());
                    System.out.println("ReceiveDate=" + smsSendDetailDTO.getReceiveDate());
                    System.out.println("SendDate=" + smsSendDetailDTO.getSendDate());
                    System.out.println("SendStatus=" + smsSendDetailDTO.getSendStatus());
                    System.out.println("Template=" + smsSendDetailDTO.getTemplateCode());
                }
                System.out.println("TotalCount=" + querySendDetailsResponse.getTotalCount());
                System.out.println("RequestId=" + querySendDetailsResponse.getRequestId());
                return true;
            }
        } catch (Exception e) {
			// TODO: handle exception
        	e.printStackTrace();
		}
     
        
        return false;
        

    }
    
    public boolean Dapi(String phone,String product,Integer code){

        try {
        	//发短
            SendSmsResponse response = sendSms(phone,product,code);
            System.out.println("短信接口返回的数----------------");
            System.out.println("Code=" + response.getCode());
            System.out.println("Message=" + response.getMessage());
            System.out.println("RequestId=" + response.getRequestId());
            System.out.println("BizId=" + response.getBizId());

            Thread.sleep(100L);

            //查明
            if(response.getCode() != null && response.getCode().equals("OK")) {
                QuerySendDetailsResponse querySendDetailsResponse = querySendDetails(response.getBizId(),phone);
               System.out.println("短信明细查询接口返回数据----------------");
                System.out.println("Code=" + querySendDetailsResponse.getCode());
                System.out.println("Message=" + querySendDetailsResponse.getMessage());
                int i = 0;
                for(QuerySendDetailsResponse.SmsSendDetailDTO smsSendDetailDTO : querySendDetailsResponse.getSmsSendDetailDTOs())
                {
                    System.out.println("SmsSendDetailDTO["+i+"]:");
                    System.out.println("Content=" + smsSendDetailDTO.getContent());
                    System.out.println("ErrCode=" + smsSendDetailDTO.getErrCode());
                    System.out.println("OutId=" + smsSendDetailDTO.getOutId());
                    System.out.println("PhoneNum=" + smsSendDetailDTO.getPhoneNum());
                    System.out.println("ReceiveDate=" + smsSendDetailDTO.getReceiveDate());
                    System.out.println("SendDate=" + smsSendDetailDTO.getSendDate());
                    System.out.println("SendStatus=" + smsSendDetailDTO.getSendStatus());
                    System.out.println("Template=" + smsSendDetailDTO.getTemplateCode());
                }
                System.out.println("TotalCount=" + querySendDetailsResponse.getTotalCount());
                System.out.println("RequestId=" + querySendDetailsResponse.getRequestId());
                return true;
            }
        } catch (Exception e) {
			// TODO: handle exception
        	e.printStackTrace();
		}
     
        
        return false;
        

    }

	public static String getSignnameG() {
		return signnameG;
	}

	public static void setSignnameG(String signnameG) {
		SmsDemo.signnameG = signnameG;
	}

	public static String getTemplatecodeG() {
		return templatecodeG;
	}

	public static void setTemplatecodeG(String templatecodeG) {
		SmsDemo.templatecodeG = templatecodeG;
	}

	public String getAccessKeyId() {
		return accessKeyId;
	}


	public void setAccessKeyId(String accessKeyId) {
		this.accessKeyId = accessKeyId;
	}


	public String getAccessKeySecret() {
		return accessKeySecret;
	}


	public void setAccessKeySecret(String accessKeySecret) {
		this.accessKeySecret = accessKeySecret;
	}


	public String getSignname() {
		return signname;
	}


	public void setSignname(String signname) {
		this.signname = signname;
	}


	public String getTemplatecode() {
		return templatecode;
	}


	public void setTemplatecode(String templatecode) {
		this.templatecode = templatecode;
	}


	public String getTemplateparam() {
		return templateparam;
	}


	public void setTemplateparam(String templateparam) {
		this.templateparam = templateparam;
	}


	public String getOutid() {
		return outid;
	}


	public void setOutid(String outid) {
		this.outid = outid;
	}

	public static String getsMItemplatecode() {
		return sMItemplatecode;
	}

	public static void setsMItemplatecode(String sMItemplatecode) {
		SmsDemo.sMItemplatecode = sMItemplatecode;
	}

	public static String getsMItemplateparam() {
		return sMItemplateparam;
	}

	public static void setsMItemplateparam(String sMItemplateparam) {
		SmsDemo.sMItemplateparam = sMItemplateparam;
	}

	public static String getSignnameR() {
		return signnameR;
	}

	public static void setSignnameR(String signnameR) {
		SmsDemo.signnameR = signnameR;
	}

	public static String getTemplatecodeR() {
		return templatecodeR;
	}

	public static void setTemplatecodeR(String templatecodeR) {
		SmsDemo.templatecodeR = templatecodeR;
	}

	
	
  
}
