package cn.szag.oms.manager.domain;

import java.util.Date;

public class ShippinInfoDate {
	/**
	 * 到港时间
	 */
	private Date arriveTime;
	/**
	 * 卸船时间
	 */
	private Date shipUnloadTime;
	/**
	 * 出闸日期（码头）
	 */
	private Date outdate;
	/**
	 * 出闸日期（口岸）
	 */
	private Date outdate2;
	/**
	 * 抵达时间
	 */
	private Date arrivalDate;
	/**
	 * 报空时间
	 */
	private Date emptyTime;
	/**
	 * 还箱时间
	 */
	private Date returnboxTime;
	public Date getArriveTime() {
		return arriveTime;
	}
	public void setArriveTime(Date arriveTime) {
		this.arriveTime = arriveTime;
	}
	public Date getShipUnloadTime() {
		return shipUnloadTime;
	}
	public void setShipUnloadTime(Date shipUnloadTime) {
		this.shipUnloadTime = shipUnloadTime;
	}
	public Date getOutdate() {
		return outdate;
	}
	public void setOutdate(Date outdate) {
		this.outdate = outdate;
	}
	public Date getOutdate2() {
		return outdate2;
	}
	public void setOutdate2(Date outdate2) {
		this.outdate2 = outdate2;
	}
	public Date getArrivalDate() {
		return arrivalDate;
	}
	public void setArrivalDate(Date arrivalDate) {
		this.arrivalDate = arrivalDate;
	}
	public Date getEmptyTime() {
		return emptyTime;
	}
	public void setEmptyTime(Date emptyTime) {
		this.emptyTime = emptyTime;
	}
	public Date getReturnboxTime() {
		return returnboxTime;
	}
	public void setReturnboxTime(Date returnboxTime) {
		this.returnboxTime = returnboxTime;
	}
	
}
