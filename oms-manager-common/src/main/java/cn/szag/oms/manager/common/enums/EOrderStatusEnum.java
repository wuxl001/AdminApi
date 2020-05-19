package cn.szag.oms.manager.common.enums;

public enum EOrderStatusEnum {
	STAY_AUDIT(0, "待审核"), 
	ALREADY_SEND_BACK(1, "已退回"), 
	ALREADY_AUDIT(2, "已审核"),
	ALREADY_TERMINATION(3, "已终止"),
	DRAFT(4, "草稿"); 
	
	private final Integer code;

	private final String value;

	private EOrderStatusEnum(Integer code, String value) {
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
		for (EOrderStatusEnum status : EOrderStatusEnum.values()) {
			if(status.getCode()==code){
				return status.value;
			}
		}
		return null;
	}
}
