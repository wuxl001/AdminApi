package cn.szag.oms.manager.domain;

import java.util.Date;

public class OrderReturnContainerInfo {
    private String id;

    private String orderId;//订单Id

    private Date boxfreePeriod;//免箱截止日

    private Date emptyTime;//报空时间

    private Date returnboxTime;//还箱时间

    private Integer overdueDays;//逾期天数

    private Integer status;//还箱状态

    private Date freeDemurrage;//免仓时间

    private String emptyPlace;//报空地点

    private String checkSite;//

    private Date checkTime;

    private String boxNo;//柜号

    private String abnormalRemark;//异常备注

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

    public Date getBoxfreePeriod() {
        return boxfreePeriod;
    }

    public void setBoxfreePeriod(Date boxfreePeriod) {
        this.boxfreePeriod = boxfreePeriod;
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

    public Integer getOverdueDays() {
        return overdueDays;
    }

    public void setOverdueDays(Integer overdueDays) {
        this.overdueDays = overdueDays;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getFreeDemurrage() {
        return freeDemurrage;
    }

    public void setFreeDemurrage(Date freeDemurrage) {
        this.freeDemurrage = freeDemurrage;
    }

    public String getEmptyPlace() {
        return emptyPlace;
    }

    public void setEmptyPlace(String emptyPlace) {
        this.emptyPlace = emptyPlace == null ? null : emptyPlace.trim();
    }

    public String getCheckSite() {
        return checkSite;
    }

    public void setCheckSite(String checkSite) {
        this.checkSite = checkSite == null ? null : checkSite.trim();
    }

    public Date getCheckTime() {
        return checkTime;
    }

    public void setCheckTime(Date checkTime) {
        this.checkTime = checkTime;
    }

    public String getBoxNo() {
        return boxNo;
    }

    public void setBoxNo(String boxNo) {
        this.boxNo = boxNo == null ? null : boxNo.trim();
    }

    public String getAbnormalRemark() {
        return abnormalRemark;
    }

    public void setAbnormalRemark(String abnormalRemark) {
        this.abnormalRemark = abnormalRemark == null ? null : abnormalRemark.trim();
    }
}