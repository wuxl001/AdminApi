package cn.szag.oms.manager.enums;

public enum TopicEnum {
	ADD_SHIPPING("ADD_SHIPPING", "新增海运"), 
	EDIT_SHIPPING("EDIT_SHIPPING", "修改海运"), 
	ADD_CLEARANCE("ADD_CLEARANCE", "新增报关"),
	EDIT_CLEARANCE("EDIT_CLEARANCE", "修改报关"),
	ADD_TRANSFER("ADD_TRANSFER", "新增运输"),
	EDIT_TRANSFER("EDIT_TRANSFER", "修改运输"),
	ADD_DISPATCH("ADD_DISPATCH", "新增调度"),
	EDIT_DISPATCH("EDIT_DISPATCH", "修改调度"),
	ADD_PICTURE_ATTACHMENT("ADD_PICTURE_ATTACHMENT", "新增运输附件"),
	EDIT_PICTURE_ATTACHMENT("EDIT_PICTURE_ATTACHMENT", "修改运输附件"),
	ADD_RETURN_CONTAINER("ADD_RETURN_CONTAINER", "新增报空信息"),
	EDIT_RETURN_CONTAINER("EDIT_RETURN_CONTAINER", "修改报空信息"),
	ADD_PAYMENT("ADD_PAYMENT", "新增付汇"),
	EDIT_PAYMENT("EDIT_PAYMENT", "修改报关"),
	ADD_ATTENTION("ADD_ATTENTION", "新增关注信息"),
	EDIT_ATTENTION("EDIT_ATTENTION", "修改关注信息"),
	ADD_ADVICE("ADD_ADVICE", "新增报空通知"),
	EDIT_ADVICE("EDIT_ADVICE", "修改报空通知"),
	ADD_CONTAINER("ADD_CONTAINER", "新增集装箱"),

	EDIT_CONTAINER("EDIT_CONTAINER", "修改集装箱"),
	ADD_AUTOORDER("ADD_AUTOORDER","新增自动下单"),
	EDIT_AUTOORDER("EDIT_AUTOORDER","新增自动下单"),
	DEL_AUTOORDER("DEL_AUTOORDER","删除自动下单"),
	UPLOAD_FILE("UPLOAD_FILE","附件上传"),
	EDIT_FILE("EDIT_FILE","修改附件"),
	EDIT_NOTICE("EDIT_NOTICE","修改消息推送信息"),
	ADD_EVALUATE("ADD_EVALUATE", "新增评价"),
	ADD_CUSUSER("ADD_CUSUSER","新增客户用户账户信息"),
	EDIT_CUSUSER("EDIT_CUSUSER","修改客户用户账户信息"),
	EDIT_USER("ADD_USER","修该用户"),

	EDIT_EVALUATE("EDIT_EVALUATE", "修改评价"),
	ADD_CUSTOMER("ADD_CUSTOMER","新增客户账户信息"),
	EDIT_CUSTOMER("EDIT_CUSTOMER","修改客户账户信息"),
	ADD_DISTURB("ADD_DISTURB","新增免打扰通知"),
	EDIT_DISTURB("EDIT_DISTURB","修改免打扰通知"),
	ADD_GOODSINFO("ADD_GOODSINFO","新增物资信息"),
	EDIT_GOODSINFO("EDIT_GOODSINFO","修改物资信息"),
	ADD_PORT("ADD_PORT","新增港口信息"),
	EDIT_PORT("EDIT_PORT","修改港口信息");


	
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
