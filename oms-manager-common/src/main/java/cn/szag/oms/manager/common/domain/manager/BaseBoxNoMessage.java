package cn.szag.oms.manager.common.domain.manager;

import java.util.Date;

public class BaseBoxNoMessage {
	private String containerId;//集装箱Id
	private String status;//状态
	private String hoverporttitleName;// 启运地点（中文）
	private String hoverporttitleEName;// 启运地点（英文）
	private String containerlocationName;//做柜地点（中文） 
	private String containerlocationEName;//做柜地点（英文）
	private String plateNo;//车牌
	private String driverName;//司机
	private String driverTel;//电话
	private Date realTime;//实际发生时间
	private String type;//类型
	private String destPortName;//目的港中文
	private String destPortEName;//目的港英文
	private String sponsor;//报空人
	private String returnboxPlace;//还箱地点
	private String dispatcher;//调度人
	private String emptyPlace;//报空地点
	private String departurePortName;//启运港
    private String departurePortEName;
    
	public String getDeparturePortName() {
		return departurePortName;
	}
	public void setDeparturePortName(String departurePortName) {
		this.departurePortName = departurePortName;
	}
	public String getDeparturePortEName() {
		return departurePortEName;
	}
	public void setDeparturePortEName(String departurePortEName) {
		this.departurePortEName = departurePortEName;
	}
	public String getEmptyPlace() {
		return emptyPlace;
	}
	public void setEmptyPlace(String emptyPlace) {
		this.emptyPlace = emptyPlace;
	}
	public String getDispatcher() {
		return dispatcher;
	}
	public void setDispatcher(String dispatcher) {
		this.dispatcher = dispatcher;
	}
	public String getDestPortName() {
		return destPortName;
	}
	public void setDestPortName(String destPortName) {
		this.destPortName = destPortName;
	}
	public String getDestPortEName() {
		return destPortEName;
	}
	public void setDestPortEName(String destPortEName) {
		this.destPortEName = destPortEName;
	}
	public String getSponsor() {
		return sponsor;
	}
	public void setSponsor(String sponsor) {
		this.sponsor = sponsor;
	}
	public String getReturnboxPlace() {
		return returnboxPlace;
	}
	public void setReturnboxPlace(String returnboxPlace) {
		this.returnboxPlace = returnboxPlace;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getContainerId() {
		return containerId;
	}
	public void setContainerId(String containerId) {
		this.containerId = containerId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getHoverporttitleName() {
		return hoverporttitleName;
	}
	public void setHoverporttitleName(String hoverporttitleName) {
		this.hoverporttitleName = hoverporttitleName;
	}
	public String getHoverporttitleEName() {
		return hoverporttitleEName;
	}
	public void setHoverporttitleEName(String hoverporttitleEName) {
		this.hoverporttitleEName = hoverporttitleEName;
	}
	public String getContainerlocationName() {
		return containerlocationName;
	}
	public void setContainerlocationName(String containerlocationName) {
		this.containerlocationName = containerlocationName;
	}
	public String getContainerlocationEName() {
		return containerlocationEName;
	}
	public void setContainerlocationEName(String containerlocationEName) {
		this.containerlocationEName = containerlocationEName;
	}
	public String getPlateNo() {
		return plateNo;
	}
	public void setPlateNo(String plateNo) {
		this.plateNo = plateNo;
	}
	public String getDriverName() {
		return driverName;
	}
	public void setDriverName(String driverName) {
		this.driverName = driverName;
	}
	public String getDriverTel() {
		return driverTel;
	}
	public void setDriverTel(String driverTel) {
		this.driverTel = driverTel;
	}
	public Date getRealTime() {
		return realTime;
	}
	public void setRealTime(Date realTime) {
		this.realTime = realTime;
	}
	
	
}
