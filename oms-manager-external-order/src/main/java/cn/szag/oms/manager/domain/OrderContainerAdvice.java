package cn.szag.oms.manager.domain;

import java.io.Serializable;
import java.util.Date;

public class OrderContainerAdvice implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String id;

    private String orderId;//订单Id

    private String boxNo;//柜号

    private String sponsorId;//报空人Id

    private String sponsor;//报空人

    private Date adivceTime;//报空时间

    private Integer readStatus;//阅读状态(0=未读/1=已读)

    private String dispatchId;//调度单Id

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId == null ? null : orderId.trim();
    }

    public String getBoxNo() {
        return boxNo;
    }

    public void setBoxNo(String boxNo) {
        this.boxNo = boxNo == null ? null : boxNo.trim();
    }

    public String getSponsorId() {
        return sponsorId;
    }

    public void setSponsorId(String sponsorId) {
        this.sponsorId = sponsorId == null ? null : sponsorId.trim();
    }

    public String getSponsor() {
        return sponsor;
    }

    public void setSponsor(String sponsor) {
        this.sponsor = sponsor == null ? null : sponsor.trim();
    }

    public Date getAdivceTime() {
        return adivceTime;
    }

    public void setAdivceTime(Date adivceTime) {
        this.adivceTime = adivceTime;
    }

    public Integer getReadStatus() {
        return readStatus;
    }

    public void setReadStatus(Integer readStatus) {
        this.readStatus = readStatus;
    }

    public String getDispatchId() {
        return dispatchId;
    }

    public void setDispatchId(String dispatchId) {
        this.dispatchId = dispatchId == null ? null : dispatchId.trim();
    }
}