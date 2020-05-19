package cn.szag.oms.manager.common.enums;
/**
 * 调度状态
* @ClassName: DispatchStatusEnum 
* @Description: TODO
* @author dengyanghao
* @date 2019年9月5日 下午5:54:04
 */
public enum DispatchStatusEnum {
	DISPATCH("1", "调度"), 
	SET_OFF_IN_TRANSIT	("2", "出发在途"), 
	ALREADY_ACCOMPLISH("3", "已完成"), 
	ALREADY_RETURN_THE_CONTAINER("4", "已还柜"),
	ALREADY_TERMINATION("5", "已终止"),
	ALREADY_THE_EMPTY("6", "已报空"),
	ALREADY_TWICE_THE_EMPTY("7", "已二次报空"),
	STAY_AISO_THE_CHEST("8", "待还柜中"); 
	
	private final String code;

	private final String value;

	private DispatchStatusEnum(String code, String value) {
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
		for (DispatchStatusEnum status : DispatchStatusEnum.values()) {
			if(status.getCode().equalsIgnoreCase(code)){
				return status.value;
			}
		}
		return null;
	}
}
