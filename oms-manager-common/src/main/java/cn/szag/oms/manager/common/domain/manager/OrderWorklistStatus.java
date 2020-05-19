package cn.szag.oms.manager.common.domain.manager;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class OrderWorklistStatus extends Commonality{
    private String id;

    private String orderId;

    private Integer customsClearanceStatus;

    private String boxNo;

    private String bookingNo;

    private String worklistNo;

    private String containerPlan;
    
    private String isAbnormal;

    private String scheduleStatus;

    private Integer score;
    @JsonIgnore
    private Date createdate;

    private Integer finishStatus;
    @JsonIgnore
    private String orderShippingInfoId;
    @JsonIgnore
    private String orderExportShippingInfoId;
    
    private String product;//商品名称
    
    private Integer quantity;
    @JsonIgnore
    private String abnormalRemark;//异常备注
    
    private Date bookingDate;//订舱时间
    
    private Date releaseDate;//电放时间
    
    private Date departTime;//出发时间
    private Date arrivalDate;//抵达时间
    private Date emptyTime;//报空时间
    
    private Date outdate;//出闸日期（码头）
    private Date outdate2;//出闸日期（口岸）
    
    private Date shipUnloadTime;//卸船时间
    private Integer type;//业务标志（1=进口/2=出口）
    @JsonIgnore
    private Integer delFlag;//删除标志位（0=正常/1=删除）
    private String boxfreePeriod;//免柜截止日
    private String freeDemurrage;//免仓日期
    private Date finishTime;//完成时间

    private Date returnboxtime;//还箱时间
    
    private String isEvaluation;//是否评价
    private String dispatchid;//调度员ID

    private String clearanceid;//报关单ID

    private Integer isbill;//已出账单 0否 1是

    private Integer isempty;//是否报空 0否 1=是
    
    private Date cusAdviceDate;//客户通知时间
    
    private Integer isAttention;//是否关注，0=否/1=是
    
    private String orderSource;//订单来源
    
    private Integer keyWord;
    
    private Double progressBar;//进度条 
    
    private String customerName;//客户名称
    
    private Date cusEmptyTime;//客户报空时间
    
    private Date mentionArkTime;//提柜时间
    
    private String orderPlacer;//下单人
    
    private Integer certStatus;//单证状态
    
    private String deliveryCountryName;//目的国
    
    private String deliveryCountryEName;//目的国
    
    private String keyWordName;//订舱号/集装箱
    
    
    private String domesticShipperName;//境内收货人
    private String domesticShipperEName;
    private String overseasConsigneeName;//境外发货人
    private String overseasConsigneeEName;
    
    
    /**
	 * 是否还重（0=no，1=yes）
	 */
	private Integer isArrive;
	/**
	 * 集装箱Id
	 */
	private String containerId;
	
	
    
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

	public String getContainerId() {
		return containerId;
	}

	public void setContainerId(String containerId) {
		this.containerId = containerId;
	}

	public Integer getIsArrive() {
		return isArrive;
	}

	public void setIsArrive(Integer isArrive) {
		this.isArrive = isArrive;
	}

	public String getDeliveryCountryEName() {
		return deliveryCountryEName;
	}

	public void setDeliveryCountryEName(String deliveryCountryEName) {
		this.deliveryCountryEName = deliveryCountryEName;
	}

	public String getKeyWordName() {
		return keyWordName;
	}

	public void setKeyWordName(String keyWordName) {
		this.keyWordName = keyWordName;
	}

	public String getDeliveryCountryName() {
		return deliveryCountryName;
	}

	public void setDeliveryCountryName(String deliveryCountryName) {
		this.deliveryCountryName = deliveryCountryName;
	}

	public String getOrderPlacer() {
		return orderPlacer;
	}

	public void setOrderPlacer(String orderPlacer) {
		this.orderPlacer = orderPlacer;
	}

	public Integer getCertStatus() {
		return certStatus;
	}

	public void setCertStatus(Integer certStatus) {
		this.certStatus = certStatus;
	}

	public Date getMentionArkTime() {
		return mentionArkTime;
	}

	public void setMentionArkTime(Date mentionArkTime) {
		this.mentionArkTime = mentionArkTime;
	}

	public Date getCusEmptyTime() {
		return cusEmptyTime;
	}

	public void setCusEmptyTime(Date cusEmptyTime) {
		this.cusEmptyTime = cusEmptyTime;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public Double getProgressBar() {
		return progressBar;
	}

	public void setProgressBar(Double progressBar) {
		this.progressBar = progressBar;
	}

	public Integer getKeyWord() {
		return keyWord;
	}

	public void setKeyWord(Integer keyWord) {
		this.keyWord = keyWord;
	}

	public String getOrderSource() {
		return orderSource;
	}

	public void setOrderSource(String orderSource) {
		this.orderSource = orderSource;
	}

	public Integer getIsAttention() {
		return isAttention;
	}

	public void setIsAttention(Integer isAttention) {
		this.isAttention = isAttention;
	}

	public Date getCusAdviceDate() {
		return cusAdviceDate;
	}

	public void setCusAdviceDate(Date cusAdviceDate) {
		this.cusAdviceDate = cusAdviceDate;
	}

	public String getDispatchid() {
		return dispatchid;
	}

	public void setDispatchid(String dispatchid) {
		this.dispatchid = dispatchid;
	}

	public String getClearanceid() {
		return clearanceid;
	}

	public void setClearanceid(String clearanceid) {
		this.clearanceid = clearanceid;
	}

	public Integer getIsbill() {
		return isbill;
	}

	public void setIsbill(Integer isbill) {
		this.isbill = isbill;
	}

	public Integer getIsempty() {
		return isempty;
	}

	public void setIsempty(Integer isempty) {
		this.isempty = isempty;
	}

	public String getIsEvaluation() {
		return isEvaluation;
	}

	public void setIsEvaluation(String isEvaluation) {
		this.isEvaluation = isEvaluation;
	}

	public Date getFinishTime() {
		return finishTime;
	}

	public void setFinishTime(Date finishTime) {
		this.finishTime = finishTime;
	}

	public Date getReturnboxtime() {
		return returnboxtime;
	}

	public void setReturnboxtime(Date returnboxtime) {
		this.returnboxtime = returnboxtime;
	}

	public Date getShipUnloadTime() {
		return shipUnloadTime;
	}

	public void setShipUnloadTime(Date shipUnloadTime) {
		this.shipUnloadTime = shipUnloadTime;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(Integer delFlag) {
		this.delFlag = delFlag;
	}

	public String getBoxfreePeriod() {
		return boxfreePeriod;
	}

	public void setBoxfreePeriod(String boxfreePeriod) {
		this.boxfreePeriod = boxfreePeriod;
	}

	

	public String getFreeDemurrage() {
		return freeDemurrage;
	}

	public void setFreeDemurrage(String freeDemurrage) {
		this.freeDemurrage = freeDemurrage;
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

	public Date getDepartTime() {
		return departTime;
	}

	public void setDepartTime(Date departTime) {
		this.departTime = departTime;
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

	public Date getBookingDate() {
		return bookingDate;
	}

	public void setBookingDate(Date bookingDate) {
		this.bookingDate = bookingDate;
	}

	public Date getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}

	public String getAbnormalRemark() {
		return abnormalRemark;
	}

	public void setAbnormalRemark(String abnormalRemark) {
		this.abnormalRemark = abnormalRemark;
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

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
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