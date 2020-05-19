package cn.szag.oms.manager.common.enums;

public enum OrderStatusEnum {
	STAY_AUDIT(0, "待审核"), 
	ALREADY_SEND_BACK(1, "已退回"), 
	STAY_POLISHING(2, "待补齐"), 
	STAY_FEEDING_AUDIT(3, "待补料审核"), 
	ALREADY_AUDIT(4, "已审核"),
	ALREADY_TERMINATION(5, "已终止"),
	DRAFT(6, "草稿"); 
	
	private final Integer code;

	private final String value;

	private OrderStatusEnum(Integer code, String value) {
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
		for (OrderStatusEnum status : OrderStatusEnum.values()) {
			if(status.getCode()==code){
				return status.value;
			}
		}
		return null;
	}
}
