package cn.szag.oms.manager.common.enums;

public enum IssueStatusEnum {
	STAY_ISSUE(0, "待发布"),
	ALREADY_ISSUE(1, "已发布"),
	ALREADY_STOP(3, "已停止"); 
	
	private final Integer code;

	private final String value;

	private IssueStatusEnum(Integer code, String value) {
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
		for (IssueStatusEnum status : IssueStatusEnum.values()) {
			if(status.getCode()==code){
				return status.value;
			}
		}
		return null;
	}
}
