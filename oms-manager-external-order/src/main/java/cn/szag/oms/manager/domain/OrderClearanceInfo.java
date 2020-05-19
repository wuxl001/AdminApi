package cn.szag.oms.manager.domain;

import java.util.Date;
/**
 * 报关信息
* @ClassName: OrderClearanceInfo 
* @Description: TODO
* @author dengyanghao
* @date 2019年9月19日 下午5:28:07
 */
public class OrderClearanceInfo {
    private String id;
    /**
     * 通关单号
     */
    private String customsFormNo;
    /**
     * 报关单号
     */
    private String customsDeclarationNo;
    /**
     * 报关时间
     */
    private Date customsDeclarationTime;
    /**
     * 状态
     */
    private Integer status;
    /**
     * 出闸时间
     */
    private Date outdate;
    /**
     * 海关查验点
     */
    private String checkSite;
    /**
     * 查验时间
     */
    private Date checkTime;
    /**
     * 订单Id
     */
    private String orderId;
    /**
     * 出闸日期（口岸）
     */
    private Date outdate2;
    /**
     * 异常备注
     */
    private String abnormalRemark;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getCustomsFormNo() {
        return customsFormNo;
    }

    public void setCustomsFormNo(String customsFormNo) {
        this.customsFormNo = customsFormNo == null ? null : customsFormNo.trim();
    }

    public String getCustomsDeclarationNo() {
        return customsDeclarationNo;
    }

    public void setCustomsDeclarationNo(String customsDeclarationNo) {
        this.customsDeclarationNo = customsDeclarationNo == null ? null : customsDeclarationNo.trim();
    }

    public Date getCustomsDeclarationTime() {
        return customsDeclarationTime;
    }

    public void setCustomsDeclarationTime(Date customsDeclarationTime) {
        this.customsDeclarationTime = customsDeclarationTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getOutdate() {
        return outdate;
    }

    public void setOutdate(Date outdate) {
        this.outdate = outdate;
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

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId == null ? null : orderId.trim();
    }

    public Date getOutdate2() {
        return outdate2;
    }

    public void setOutdate2(Date outdate2) {
        this.outdate2 = outdate2;
    }

    public String getAbnormalRemark() {
        return abnormalRemark;
    }

    public void setAbnormalRemark(String abnormalRemark) {
        this.abnormalRemark = abnormalRemark == null ? null : abnormalRemark.trim();
    }
}