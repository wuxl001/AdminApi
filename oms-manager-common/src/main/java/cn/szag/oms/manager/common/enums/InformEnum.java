package cn.szag.oms.manager.common.enums;

public enum InformEnum {
	TURNOUT_INFORM(0, "出车通知"), 
	DEPARTURE_INFORM(1, "离港通知"), 
	ARRIVAL_INFORM(2, "到港通知"), 
	ARRIVE_INFORM(3, "抵达通知"), 
	OF_RETURN_INFORM(4, "还箱通知"), 
	GREEN_LIGHT_INFORM(5, "放行通知"), 
	GATE_OUT_INFORM(6, "码头出闸通知"), 
	BOOKING_INFORM(7, "订舱通知"),
	ALSO_HEAVY_INFORM(8, "还重通知"), 
	TELEX_RELEASE_INFORM(9, "电放通知"),
	THE_EMPTY_INFORM(10, "报空通知"),
	UNLOAD_SHIP_INFORM(11, "卸船通知"),
	LOADING(12, "装货通知"),
	MENTION_ARK(13, "提柜通知"); 
	
	private final Integer code;

	private final String value;

	private InformEnum(Integer code, String value) {
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
		for (InformEnum status : InformEnum.values()) {
			if(status.getCode()==code){
				return status.value;
			}
		}
		return null;
	}
}
