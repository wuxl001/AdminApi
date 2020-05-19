package cn.szag.oms.manager.common.enums;
/**
 * 工作进度
* @ClassName: ScheduleStatusEnum 
* @Description: TODO
* @author dengyanghao
* @date 2019年9月9日 下午4:08:44
 */
public enum ScheduleStatusEnum {
	NOT_ARRIVAL("0", "未到港"), 
	ALREADY_ARRIVAL("1", "已到港"), 
	ALREADY_CARTONNING	("2", "已卸箱"), 
	CUSTOMS_RELEASE("3", "海关放行"),
	GATE_OUT("4", "码头出闸"),
	PORT_GATE_OUT("5", "出发在途"),//调度的终点等于运输单的终点，更新出车状态以及时间
	ALREADY_ARRIVE("6", "已抵达"),
	ALREADY_THE_EMPTY("7", "已报空"),
	ALREADY_TERMINAL("8", "已还柜"),
	ALREADY_ACCOMPLISH("9", "已完成"); 
	
	private final String code;

	private final String value;

	private ScheduleStatusEnum(String code, String value) {
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
		for (ScheduleStatusEnum status : ScheduleStatusEnum.values()) {
			if(status.getCode().equalsIgnoreCase(code)){
				return status.value;
			}
		}
		return null;
	}
}
