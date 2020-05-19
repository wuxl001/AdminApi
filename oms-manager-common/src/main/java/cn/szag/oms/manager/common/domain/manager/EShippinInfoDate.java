package cn.szag.oms.manager.common.domain.manager;

import java.io.Serializable;
import java.util.Date;

public class EShippinInfoDate implements Serializable {
	/**
	 * 订舱时间
	 */
	private Date bookingDate;
	/**
	 * 出车时间
	 */
	private Date departTime;
	/**
	 * 还重时间
	 */
	private Date returnboxTime;
	/**
	 * 离港时间
	 */
	private Date ATD;
	/**
	 * 电放时间
	 */
	private Date releaseDate;
	/**
	 * 抵达时间（装货）
	 */
	private Date arrivalDate;
	/**
	 * 提柜时间
	 */
	private Date mentionArkTime;
	/**
	 * 放行时间时间
	 */
	private Date outdate2;
	
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
	public Date getMentionArkTime() {
		return mentionArkTime;
	}
	public void setMentionArkTime(Date mentionArkTime) {
		this.mentionArkTime = mentionArkTime;
	}
	public Date getBookingDate() {
		return bookingDate;
	}
	public void setBookingDate(Date bookingDate) {
		this.bookingDate = bookingDate;
	}
	public Date getDepartTime() {
		return departTime;
	}
	public void setDepartTime(Date departTime) {
		this.departTime = departTime;
	}
	public Date getReturnboxTime() {
		return returnboxTime;
	}
	public void setReturnboxTime(Date returnboxTime) {
		this.returnboxTime = returnboxTime;
	}
	public Date getATD() {
		return ATD;
	}
	public void setATD(Date aTD) {
		ATD = aTD;
	}
	public Date getReleaseDate() {
		return releaseDate;
	}
	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}
	
}
