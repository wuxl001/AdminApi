package cn.szag.oms.manager.common.domain.manager;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonInclude;
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
    /**
     * 集装箱Id
     */
    private String containerId;
    /**
     * 进度状态
     */
    private String scheduleStatus;
    /**
     * 业务类型（1=进口/2=出口）
     */
    private String businessType;
    /**
     * 运输方式:0 海运 1空运 2海运
     */
    private String transportWay;
    /**
     * 原产国
     */
    private String originCountryName;
    /**
     * 原产国英文
     */
    private String originCountryEName;
    /**
     * 目的国
     */
    private String deliveryCountryName;
    /**
     * 目的国英文
     */
    private String deliveryCountryEName;
    /**
     * 商品名称
     */
    private String product;
    /**
     * 单证状态 :0未全/1齐全
     */
    private String certStatus;
    /**
     * 是否有异常 0=否 1=是
     */
    private String isAbnormal;
    /**
     * 是否有账单 0=否 1=是
     */
    private String isbill;
    /**
     * 到港时间
     */
    private Date arriveTime;
    /**
     * 预计截关日期
     */
    private String cyDate;
    /**
     * 商品件数
     */
    private String quantity;
    /**
     * 公司名称
     */
    private String customerName;
    /**
     * 公司名称英文
     */
    private String customerEName;
    /**
     * 
     */
    private String  isEvaluation;
    /**
     * 订单来源
     */
    private String orderSource;
    /**
     * id
     */
    private String attentionId;
    
    private String domesticShipperName;//境内收货人
    private String domesticShipperEName;
    private String overseasConsigneeName;//境外发货人
    private String overseasConsigneeEName;
    
    
    
    public String getDomesticShipperName() {
		return domesticShipperName;
	}

	public void setDomesticShipperName(String domesticShipperName) {
		this.domesticShipperName = domesticShipperName;
	}

	public String getDomesticShipperEName() {
		return domesticShipperEName;
	}

	public void setDomesticShipperEName(String domesticShipperEName) {
		this.domesticShipperEName = domesticShipperEName;
	}

	public String getOverseasConsigneeName() {
		return overseasConsigneeName;
	}

	public void setOverseasConsigneeName(String overseasConsigneeName) {
		this.overseasConsigneeName = overseasConsigneeName;
	}

	public String getOverseasConsigneeEName() {
		return overseasConsigneeEName;
	}

	public void setOverseasConsigneeEName(String overseasConsigneeEName) {
		this.overseasConsigneeEName = overseasConsigneeEName;
	}

	public String getAttentionId() {
		return attentionId;
	}

	public void setAttentionId(String attentionId) {
		this.attentionId = attentionId;
	}

	public String getIsEvaluation() {
		return isEvaluation;
	}

	public void setIsEvaluation(String isEvaluation) {
		this.isEvaluation = isEvaluation;
	}

	public String getOrderSource() {
		return orderSource;
	}

	public void setOrderSource(String orderSource) {
		this.orderSource = orderSource;
	}

	public String getScheduleStatus() {
		return scheduleStatus;
	}

	public void setScheduleStatus(String scheduleStatus) {
		this.scheduleStatus = scheduleStatus;
	}

	public String getBusinessType() {
		return businessType;
	}

	public void setBusinessType(String businessType) {
		this.businessType = businessType;
	}

	public String getTransportWay() {
		return transportWay;
	}

	public void setTransportWay(String transportWay) {
		this.transportWay = transportWay;
	}

	public String getOriginCountryName() {
		return originCountryName;
	}

	public void setOriginCountryName(String originCountryName) {
		this.originCountryName = originCountryName;
	}

	public String getOriginCountryEName() {
		return originCountryEName;
	}

	public void setOriginCountryEName(String originCountryEName) {
		this.originCountryEName = originCountryEName;
	}

	

	public String getDeliveryCountryName() {
		return deliveryCountryName;
	}

	public void setDeliveryCountryName(String deliveryCountryName) {
		this.deliveryCountryName = deliveryCountryName;
	}

	public String getDeliveryCountryEName() {
		return deliveryCountryEName;
	}

	public void setDeliveryCountryEName(String deliveryCountryEName) {
		this.deliveryCountryEName = deliveryCountryEName;
	}

	public String getProduct() {
		return product;
	}

	public void setProduct(String product) {
		this.product = product;
	}

	public String getCertStatus() {
		return certStatus;
	}

	public void setCertStatus(String certStatus) {
		this.certStatus = certStatus;
	}

	public String getIsAbnormal() {
		return isAbnormal;
	}

	public void setIsAbnormal(String isAbnormal) {
		this.isAbnormal = isAbnormal;
	}

	public String getIsbill() {
		return isbill;
	}

	public void setIsbill(String isbill) {
		this.isbill = isbill;
	}

	public Date getArriveTime() {
		return arriveTime;
	}

	public void setArriveTime(Date arriveTime) {
		this.arriveTime = arriveTime;
	}

	public String getCyDate() {
		return cyDate;
	}

	public void setCyDate(String cyDate) {
		this.cyDate = cyDate;
	}

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCustomerEName() {
		return customerEName;
	}

	public void setCustomerEName(String customerEName) {
		this.customerEName = customerEName;
	}

	public String getContainerId() {
		return containerId;
	}

	public void setContainerId(String containerId) {
		this.containerId = containerId;
	}

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