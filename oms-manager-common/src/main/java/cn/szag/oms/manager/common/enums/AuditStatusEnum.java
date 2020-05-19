package cn.szag.oms.manager.common.enums;

public enum AuditStatusEnum {
	STAY_AUDIT(0, "待审核"), 
	ALREADY_AUDIT(1, "已审核"),
	ALREADY_SEND_BACK(3, "已退回"); 
	
	private final Integer code;

	private final String value;

	private AuditStatusEnum(Integer code, String value) {
		this.code = code;
		this.value = value;
	}

	public Integer getCode(){
		return code;
	}
	
	public String getValue(){
		return value;
	}

	public String getValue(Integer code){
		for (AuditStatusEnum status : AuditStatusEnum.values()) {
			if(status.getCode()==code){
				return status.value;
			}
		}
		return null;
	}
}
