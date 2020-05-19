package cn.szag.oms.manager.common.domain.manager;

import java.io.Serializable;
import java.util.Date;
/**
 * 付汇
* @ClassName: OrderPaymentInfo 
* @Description: TODO
* @author dengyanghao
* @date 2019年9月19日 下午5:23:10
 */
public class OrderPaymentInfo implements Serializable {
    private String id;

    private String orderId;//订单Id

    private String paymentOrderNo;//付汇水单

    private Date paymentTime;//付汇时间

    private Integer status;//付汇状态

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

    public String getPaymentOrderNo() {
        return paymentOrderNo;
    }

    public void setPaymentOrderNo(String paymentOrderNo) {
        this.paymentOrderNo = paymentOrderNo == null ? null : paymentOrderNo.trim();
    }

    public Date getPaymentTime() {
        return paymentTime;
    }

    public void setPaymentTime(Date paymentTime) {
        this.paymentTime = paymentTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getAbnormalRemark() {
        return abnormalRemark;
    }

    public void setAbnormalRemark(String abnormalRemark) {
        this.abnormalRemark = abnormalRemark == null ? null : abnormalRemark.trim();
    }
}