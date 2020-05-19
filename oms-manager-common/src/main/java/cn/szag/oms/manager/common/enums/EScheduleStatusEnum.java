package cn.szag.oms.manager.common.enums;
/**
 * 工作进度
* @ClassName: ScheduleStatusEnum 
* @Description: TODO
* @author dengyanghao
* @date 2019年9月9日 下午4:08:44
 */
public enum EScheduleStatusEnum {
	NOT_BOOKING("0", "未订舱"), 
	ALREADY_BOOKING("1", "已订舱"), 
	ALREADY_TURNOUT("2", "已出车"), 
	MENTION_ARK("3", "提柜"),
	LOADING("4", "装货"),
	ALREADY_AISOHEAVY("5", "已还重"), 
	ALREADY_GREEN_LIGHT("6", "已放行"),
	ALREADY_DEPARTURE("7", "已离港"),
	ALREADY_RELEASE("8", "已电放"),
	ALREADY_ACCOMPLISH("9", "已完成"); 
	
	private final String code;

	private final String value;

	private EScheduleStatusEnum(String code, String value) {
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
		for (EScheduleStatusEnum status : EScheduleStatusEnum.values()) {
			if(status.getCode().equalsIgnoreCase(code)){
				return status.value;
			}
		}
		return null;
	}
}
