package cn.szag.oms.manager.common.enums;

public enum TopicEnum {
	ADD_SHIPPING("ADD_SHIPPING", "新增海运"), 
	EDIT_SHIPPING("EDIT_SHIPPING", "修改海运"), 
	ADD_CLEARANCE("ADD_CLEARANCE", "新增报关"),
	EDIT_CLEARANCE("EDIT_CLEARANCE", "修改报关"),
	ADD_CUSUSER("ADD_CUSTOMER","新增客户用户账户信息"),
	EDIT_CUSUSER("EDIT_CUSTOMER","修改客户用户账户信息"); 
	
	private final String code;

	private final String value;

	private TopicEnum(String code, String value) {
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
		for (TopicEnum status : TopicEnum.values()) {
			if(status.getCode().equalsIgnoreCase(code)){
				return status.value;
			}
		}
		return null;
	}
}
