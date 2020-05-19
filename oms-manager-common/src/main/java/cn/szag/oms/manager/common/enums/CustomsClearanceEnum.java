package cn.szag.oms.manager.common.enums;

/**
 * 报关状态
* @ClassName: CustomsClearanceEnum 
* @Description: TODO
* @author dengyanghao
* @date 2019年9月5日 下午5:38:41
 */
public enum CustomsClearanceEnum {
	HIRE("0", "录入"), 
	SENT("1", "已发送"), 
	RETURNED("2", "已退回"), 
	COMPLETED("3", "已完成"), 
	DOCUMENTS_RELEASED("4", "单证放行"), 
	PORT_CLEARANCE("5", "码头放行"); 
	
	private final String code;

	private final String value;

	private CustomsClearanceEnum(String code, String value) {
		this.code = code;
		this.value = value;
	}

	public String getCode(){
		return code;
	}
	
	public String getValue(){
		return value;
	}

	public String getValue(String code){
		for (CustomsClearanceEnum status : CustomsClearanceEnum.values()) {
			if(status.getCode().equalsIgnoreCase(code)){
				return status.value;
			}
		}
		return null;
	}
}
