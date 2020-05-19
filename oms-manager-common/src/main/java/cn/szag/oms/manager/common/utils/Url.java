package cn.szag.oms.manager.common.utils;

public class Url {
	public static String uggUrl;
	
	public static String omsUrl;
	
	public static String cfsUrl;
	
	public static String feiyongUrl;
	
	public static String imagesUrl;
	
	public static String wechatUrl;
	
	public static String wechatEUrl;
	
	public static String mqUrl;
	
	public static String carryOut;//1=发送到MQ消息队列执行规则引擎

	public static String getCarryOut() {
		return carryOut;
	}

	public static void setCarryOut(String carryOut) {
		Url.carryOut = carryOut;
	}

	public static String getMqUrl() {
		return mqUrl;
	}

	public static void setMqUrl(String mqUrl) {
		Url.mqUrl = mqUrl;
	}

	public static String getWechatEUrl() {
		return wechatEUrl;
	}

	public static void setWechatEUrl(String wechatEUrl) {
		Url.wechatEUrl = wechatEUrl;
	}

	public static String getWechatUrl() {
		return wechatUrl;
	}

	public static void setWechatUrl(String wechatUrl) {
		Url.wechatUrl = wechatUrl;
	}

	public static String getImagesUrl() {
		return imagesUrl;
	}

	public static void setImagesUrl(String imagesUrl) {
		Url.imagesUrl = imagesUrl;
	}

	public static String getFeiyongUrl() {
		return feiyongUrl;
	}

	public static void setFeiyongUrl(String feiyongUrl) {
		Url.feiyongUrl = feiyongUrl;
	}

	public static String getUggUrl() {
		return uggUrl;
	}

	public static void setUggUrl(String uggUrl) {
		Url.uggUrl = uggUrl;
	}

	public static String getOmsUrl() {
		return omsUrl;
	}

	public static void setOmsUrl(String omsUrl) {
		Url.omsUrl = omsUrl;
	}

	public static String getCfsUrl() {
		return cfsUrl;
	}

	public static void setCfsUrl(String cfsUrl) {
		Url.cfsUrl = cfsUrl;
	}
	
	
	
}
