package cn.szag.oms.manager.common.domain.manager;

import java.io.Serializable;
import java.util.Date;

public class ShippinInfoDate implements Serializable {
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
	 * 放行时间
	 */
	private Date outdate2;
	/**
	 * 出车时间
	 */
	private Date departTime;
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
	/**
	 * 客户通知时间
	 */
	private Date cusAdviceDate;
	/**
	 * 订单确认时间
	 */
	private Date afffirmDate;
	/**
	 * 客户报空时间
	 */
	private Date cusEmptyTime;
	
	/**
	 * 判断目的港是否是香港，1：香港，0：其他
	 */
	private int status;
	
	
	
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public Date getCusEmptyTime() {
		return cusEmptyTime;
	}
	public void setCusEmptyTime(Date cusEmptyTime) {
		this.cusEmptyTime = cusEmptyTime;
	}
	public Date getCusAdviceDate() {
		return cusAdviceDate;
	}
	public void setCusAdviceDate(Date cusAdviceDate) {
		this.cusAdviceDate = cusAdviceDate;
	}
	public Date getAfffirmDate() {
		return afffirmDate;
	}
	public void setAfffirmDate(Date afffirmDate) {
		this.afffirmDate = afffirmDate;
	}
	public Date getDepartTime() {
		return departTime;
	}
	public void setDepartTime(Date departTime) {
		this.departTime = departTime;
	}
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
