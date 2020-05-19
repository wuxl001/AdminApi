package cn.szag.oms.manager.domain;

import java.util.Date;

public class Attention {
    private String id;
    /**
     * 订单号
     */
    private String orderId;
    /**
     * 柜号
     */
    private String boxNo;
    /**
     * 订舱号
     */
    private String bookingNo;
    /**
     * 关注时间
     */
    private Date attentionDate;
    /**
     * 是否关注（0=是/1=否）
     */
    private Integer isAttention;
    /**
     * 用户Id
     */
    private String userId;
    
    public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

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

    public String getBookingNo() {
        return bookingNo;
    }

    public void setBookingNo(String bookingNo) {
        this.bookingNo = bookingNo == null ? null : bookingNo.trim();
    }

    public Date getAttentionDate() {
        return attentionDate;
    }

    public void setAttentionDate(Date attentionDate) {
        this.attentionDate = attentionDate;
    }

    public Integer getIsAttention() {
        return isAttention;
    }

    public void setIsAttention(Integer isAttention) {
        this.isAttention = isAttention;
    }
}