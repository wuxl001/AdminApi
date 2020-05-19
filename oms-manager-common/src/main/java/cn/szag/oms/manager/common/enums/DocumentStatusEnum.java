package cn.szag.oms.manager.common.enums;

public enum DocumentStatusEnum {
	COMPLETE("0", "齐全"), 
	NOT_COMPLETE("1", "未齐全"); 
	
	private final String code;

	private final String value;

	private DocumentStatusEnum(String code, String value) {
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
		for (DocumentStatusEnum status : DocumentStatusEnum.values()) {
			if(status.getCode().equalsIgnoreCase(code)){
				return status.value;
			}
		}
		return null;
	}
}
