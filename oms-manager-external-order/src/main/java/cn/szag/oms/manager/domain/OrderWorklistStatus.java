package cn.szag.oms.manager.domain;

import java.util.Date;

public class OrderWorklistStatus {
    private String id;

    private String orderId;

    private Integer customsClearanceStatus;

    private String boxNo;

    private String bookingNo;

    private String worklistNo;

    private String containerPlan;

    private String isAbnormal;

    private String scheduleStatus;

    private String score;

    private Date createdate;

    private Integer finishStatus;

    private String orderShippingInfoId;

    private String orderExportShippingInfoId;

    private String product;

    private Integer quantity;

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

    public Integer getCustomsClearanceStatus() {
        return customsClearanceStatus;
    }

    public void setCustomsClearanceStatus(Integer customsClearanceStatus) {
        this.customsClearanceStatus = customsClearanceStatus;
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

    public String getWorklistNo() {
        return worklistNo;
    }

    public void setWorklistNo(String worklistNo) {
        this.worklistNo = worklistNo == null ? null : worklistNo.trim();
    }

    public String getContainerPlan() {
        return containerPlan;
    }

    public void setContainerPlan(String containerPlan) {
        this.containerPlan = containerPlan == null ? null : containerPlan.trim();
    }

    public String getIsAbnormal() {
        return isAbnormal;
    }

    public void setIsAbnormal(String isAbnormal) {
        this.isAbnormal = isAbnormal == null ? null : isAbnormal.trim();
    }

    public String getScheduleStatus() {
        return scheduleStatus;
    }

    public void setScheduleStatus(String scheduleStatus) {
        this.scheduleStatus = scheduleStatus == null ? null : scheduleStatus.trim();
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score == null ? null : score.trim();
    }

    public Date getCreatedate() {
        return createdate;
    }

    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }

    public Integer getFinishStatus() {
        return finishStatus;
    }

    public void setFinishStatus(Integer finishStatus) {
        this.finishStatus = finishStatus;
    }

    public String getOrderShippingInfoId() {
        return orderShippingInfoId;
    }

    public void setOrderShippingInfoId(String orderShippingInfoId) {
        this.orderShippingInfoId = orderShippingInfoId == null ? null : orderShippingInfoId.trim();
    }

    public String getOrderExportShippingInfoId() {
        return orderExportShippingInfoId;
    }

    public void setOrderExportShippingInfoId(String orderExportShippingInfoId) {
        this.orderExportShippingInfoId = orderExportShippingInfoId == null ? null : orderExportShippingInfoId.trim();
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product == null ? null : product.trim();
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}