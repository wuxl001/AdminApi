package cn.szag.oms.manager.common.domain.manager;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class OrderExport {
    private String id;
    /**
     * 订单号
     */
    private String orderNo;
    /**
     * 客户名称
     */
    private String customerName;
    /**
     * 运输方式:0海运 1空运 2陆运
     */
    private String transportWay;
    /**
     * 境内收货人
     */
    private String domesticShipper;
    /**
     * 境外发货人
     */
    private String overseasConsignee;
    /**
     * 提单号
     */
    private String extractOrderNum;
    /**
     * 原产国
     */
    private String originCountry;
    /**
     * 目的国
     */
    private String destCountry;
    /**
     * 启运港
     */
    private String departurePort;
    /**
     * 目的港
     */
    private String destPort;
    /**
     * 商品名称
     */
    private String product;
    /**
     * 进境口岸
     */
    private String entryPort;
    /**
     * 创建时间
     */
    private Date createtime;
    /**
     * 创建人id
     */
    private String creatorId;
    /**
     * 创建人
     */
    private String creator;
    /**
     * 创建人手机号
     */
    private String creatorTel;
    /**
     * 客服
     */
    private String supportStaff;
    /**
     * 手机号
     */
    private String supportStaffTel;
    /**
     * 删除标志位（0=未删除/1=已删除）
     */
    private Integer delFlag;
    /**
     * 订单状态
     */
    private Integer orderStatus;
    /**
     * 单证状态（0=齐全，1=未全）
     */
    private Integer certStatus;
    /**
     * 进度状态
     */
    private Integer scheduleStatus;
    /**
     * 清关状态
     */
    private Integer customsClearanceStatus;
    /**
     * 验货需求
     */
    private Integer inspectNeed;
    /**
     * 清关需求
     */
    private Integer clearanceNeed;
    /**
     * 运输需求
     */
    private Integer transportNeed;
    /**
     * 付汇需求
     */
    private Integer paymentNeed;
    /**
     * 垫付需求
     */
    private Integer advanceNeed;
    /**
     * 生产使用单位
     */
    private String producerOrg;
    /**
     * 订单确认日期
     */
    private Date afffirmDate;
    /**
     * 客户通知日期
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date cusAdviceDate;
    /**
     * 订单来源
     */
    private String orderSource;
    /**
     * 下单人
     */
    private String orderPlacer;
    /**
     * 订舱号
     */
    private String bookingNo;
    /**
     * 业务员
     */
    private String salesman;
    /**
     * 业务员电话
     */
    private String salesmanTel;
    /**
     * 处理意见
     */
    private String handlingSuggestion;
    /**
     * 审核时间
     */
    private Date checkTime;
    /**
     * 补料时间
     */
    private Date supplementTime;
    /**
     * 扩展类型（终止、补料、修改）
     */
    private String extendType;
    /**
     * 客户公司id
     */
    private String companyId;
    /**
     * 补充说明
     */
    private String remark;
    /**
     * 工作单号
     */
    private String worklistNo;
    /**
     * 处理结果（1=通过/2=退回）
     */
    private String handlingResult;
    /**
     * 码头
     */
    private String dock;
    
    /**
     * 搜索条件-----------------
     */
    /**
     * 截关日期（起）
     */
    private Date cyDateStart;
    /**
     * 截关日期（始）
     */
    private Date cyDateEnd;
    /**
     * 航名航次
     */
    private String shipInfo;
    /**
     * 是否还重
     */
    private String isArrive;
    /**
     * 客户通知时间（起）
     */
    private Date cusAdviceDateStart;
    /**
     * 客户通知时间（始）
     */
    private Date cusAdviceDateEnd;
    /**
     * 货物类型（1=东南亚货/2=西货）
     */
    private String goodsType;
    private String updateId;//修改人id
    private Date updateTime;//修改时间
    private String closingPer;//终止人
    private Date closingDate;//修改时间
    /**
     * 统计类型
     */
    private String keyWord;//统计类型
    //扩展字段
    private String customerEName;//客户英文名称
    private String originCountryName;//原产国
    private String originCountryEName;
    private String destCountryName;//目的国
    private String destCountryEName;
    private String domesticShipperName;//境内收货人
    private String domesticShipperEName;
    private String overseasConsigneeName;//境内发货人
    private String overseasConsigneeEName;
    private String producerOrgName;//生产使用单位
    private String producerOrgEName;
    private String departurePortName;//启运港
    private String departurePortEName;
    private String destPortName;//目的港
    private String destPortEName;
    private String supportStaffName;//客服
    private String salesmanName;//业务员
    private String orderPlacerName;//下单人
    
    private Date createStartTime;//创建日期（始）
    
    private Date createEndTime;//创建日期（终）
    
    private String customer;//客户名称id
    
    private Integer disposeStatus;//处理状态（1=待发送，2=成功，3=失败）
    
    private String supportStaffDepa;//客服部门
	
	private String salesmanDepa; //业务员部门
    
	private String loadingTime;//装货时间
	    
	private String loadingLocation;//装货地点
	    
	private String temperature;//温度
	    
	private String vent;//通风口
	    
	private String packages;//包装
	    
	private String netWeight;//净重
	    
	private String grossWeight;//毛重
	    
	private String customsUnitPrice;//报关单价
	    
	private String formOfCommerce;//贸易方式
	    
	private String domesticResources;//境内资源地
	    
	private String contractNo;//合同号

	private String Hoe;//唛头
	    
	private String quantity;//件数
	    
	    
	private String agent;//代理商
	
	private String customerId;
	
	private String company;
	
		public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

		public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

		public String getAgent() {
		return agent;
	}

	public void setAgent(String agent) {
		this.agent = agent;
	}

		public String getLoadingTime() {
		return loadingTime;
	}

	public void setLoadingTime(String loadingTime) {
		this.loadingTime = loadingTime;
	}

		public String getLoadingLocation() {
			return loadingLocation;
		}

		public void setLoadingLocation(String loadingLocation) {
			this.loadingLocation = loadingLocation;
		}

		public String getTemperature() {
			return temperature;
		}

		public void setTemperature(String temperature) {
			this.temperature = temperature;
		}

		public String getVent() {
			return vent;
		}

		public void setVent(String vent) {
			this.vent = vent;
		}

		public String getPackages() {
			return packages;
		}

		public void setPackages(String packages) {
			this.packages = packages;
		}

		public String getNetWeight() {
			return netWeight;
		}

		public void setNetWeight(String netWeight) {
			this.netWeight = netWeight;
		}

		public String getGrossWeight() {
			return grossWeight;
		}

		public void setGrossWeight(String grossWeight) {
			this.grossWeight = grossWeight;
		}

		public String getCustomsUnitPrice() {
			return customsUnitPrice;
		}

		public void setCustomsUnitPrice(String customsUnitPrice) {
			this.customsUnitPrice = customsUnitPrice;
		}

		public String getFormOfCommerce() {
			return formOfCommerce;
		}

		public void setFormOfCommerce(String formOfCommerce) {
			this.formOfCommerce = formOfCommerce;
		}

		public String getDomesticResources() {
			return domesticResources;
		}

		public void setDomesticResources(String domesticResources) {
			this.domesticResources = domesticResources;
		}

		public String getContractNo() {
			return contractNo;
		}

		public void setContractNo(String contractNo) {
			this.contractNo = contractNo;
		}

		public String getHoe() {
			return Hoe;
		}

		public void setHoe(String hoe) {
			Hoe = hoe;
		}

		public String getQuantity() {
			return quantity;
		}

		public void setQuantity(String quantity) {
			this.quantity = quantity;
		}

	public String getSupportStaffDepa() {
		return supportStaffDepa;
	}

	public void setSupportStaffDepa(String supportStaffDepa) {
		this.supportStaffDepa = supportStaffDepa;
	}

	public String getSalesmanDepa() {
		return salesmanDepa;
	}

	public void setSalesmanDepa(String salesmanDepa) {
		this.salesmanDepa = salesmanDepa;
	}

	public Integer getDisposeStatus() {
		return disposeStatus;
	}

	public void setDisposeStatus(Integer disposeStatus) {
		this.disposeStatus = disposeStatus;
	}

	public String getSupportStaffName() {
		return supportStaffName;
	}

	public void setSupportStaffName(String supportStaffName) {
		this.supportStaffName = supportStaffName;
	}


	public String getSalesmanName() {
		return salesmanName;
	}

	public void setSalesmanName(String salesmanName) {
		this.salesmanName = salesmanName;
	}


	public String getOrderPlacerName() {
		return orderPlacerName;
	}

	public void setOrderPlacerName(String orderPlacerName) {
		this.orderPlacerName = orderPlacerName;
	}


	public String getClosingPer() {
		return closingPer;
	}

	public void setClosingPer(String closingPer) {
		this.closingPer = closingPer;
	}

	public Date getClosingDate() {
		return closingDate;
	}

	public void setClosingDate(Date closingDate) {
		this.closingDate = closingDate;
	}

	public String getUpdateId() {
		return updateId;
	}

	public void setUpdateId(String updateId) {
		this.updateId = updateId;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getDock() {
		return dock;
	}

	public void setDock(String dock) {
		this.dock = dock;
	}

	public String getCustomer() {
		return customer;
	}

	public void setCustomer(String customer) {
		this.customer = customer;
	}

	public String getHandlingResult() {
		return handlingResult;
	}

	public void setHandlingResult(String handlingResult) {
		this.handlingResult = handlingResult;
	}

	public String getKeyWord() {
		return keyWord;
	}

	public void setKeyWord(String keyWord) {
		this.keyWord = keyWord;
	}

	public String getWorklistNo() {
		return worklistNo;
	}

	public void setWorklistNo(String worklistNo) {
		this.worklistNo = worklistNo;
	}

	public Date getCreateStartTime() {
		return createStartTime;
	}

	public void setCreateStartTime(Date createStartTime) {
		this.createStartTime = createStartTime;
	}

	public Date getCreateEndTime() {
		return createEndTime;
	}

	public void setCreateEndTime(Date createEndTime) {
		this.createEndTime = createEndTime;
	}

	public String getCustomerEName() {
		return customerEName;
	}

	public void setCustomerEName(String customerEName) {
		this.customerEName = customerEName;
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

	public String getDestCountryName() {
		return destCountryName;
	}

	public void setDestCountryName(String destCountryName) {
		this.destCountryName = destCountryName;
	}

	public String getDestCountryEName() {
		return destCountryEName;
	}

	public void setDestCountryEName(String destCountryEName) {
		this.destCountryEName = destCountryEName;
	}

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

	public String getProducerOrgName() {
		return producerOrgName;
	}

	public void setProducerOrgName(String producerOrgName) {
		this.producerOrgName = producerOrgName;
	}

	public String getProducerOrgEName() {
		return producerOrgEName;
	}

	public void setProducerOrgEName(String producerOrgEName) {
		this.producerOrgEName = producerOrgEName;
	}

	public String getDeparturePortName() {
		return departurePortName;
	}

	public void setDeparturePortName(String departurePortName) {
		this.departurePortName = departurePortName;
	}

	public String getDeparturePortEName() {
		return departurePortEName;
	}

	public void setDeparturePortEName(String departurePortEName) {
		this.departurePortEName = departurePortEName;
	}

	public String getDestPortName() {
		return destPortName;
	}

	public void setDestPortName(String destPortName) {
		this.destPortName = destPortName;
	}

	public String getDestPortEName() {
		return destPortEName;
	}

	public void setDestPortEName(String destPortEName) {
		this.destPortEName = destPortEName;
	}

	public String getGoodsType() {
		return goodsType;
	}

	public void setGoodsType(String goodsType) {
		this.goodsType = goodsType;
	}

	public String getShipInfo() {
		return shipInfo;
	}

	public void setShipInfo(String shipInfo) {
		this.shipInfo = shipInfo;
	}

	public String getIsArrive() {
		return isArrive;
	}

	public void setIsArrive(String isArrive) {
		this.isArrive = isArrive;
	}

	public Date getCusAdviceDateStart() {
		return cusAdviceDateStart;
	}

	public void setCusAdviceDateStart(Date cusAdviceDateStart) {
		this.cusAdviceDateStart = cusAdviceDateStart;
	}

	public Date getCusAdviceDateEnd() {
		return cusAdviceDateEnd;
	}

	public void setCusAdviceDateEnd(Date cusAdviceDateEnd) {
		this.cusAdviceDateEnd = cusAdviceDateEnd;
	}

	public Date getCyDateStart() {
		return cyDateStart;
	}

	public void setCyDateStart(Date cyDateStart) {
		this.cyDateStart = cyDateStart;
	}

	public Date getCyDateEnd() {
		return cyDateEnd;
	}

	public void setCyDateEnd(Date cyDateEnd) {
		this.cyDateEnd = cyDateEnd;
	}

	public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo == null ? null : orderNo.trim();
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName == null ? null : customerName.trim();
    }

    public String getTransportWay() {
        return transportWay;
    }

    public void setTransportWay(String transportWay) {
        this.transportWay = transportWay == null ? null : transportWay.trim();
    }

    public String getDomesticShipper() {
        return domesticShipper;
    }

    public void setDomesticShipper(String domesticShipper) {
        this.domesticShipper = domesticShipper == null ? null : domesticShipper.trim();
    }

    public String getOverseasConsignee() {
        return overseasConsignee;
    }

    public void setOverseasConsignee(String overseasConsignee) {
        this.overseasConsignee = overseasConsignee == null ? null : overseasConsignee.trim();
    }

    public String getExtractOrderNum() {
        return extractOrderNum;
    }

    public void setExtractOrderNum(String extractOrderNum) {
        this.extractOrderNum = extractOrderNum == null ? null : extractOrderNum.trim();
    }

    public String getOriginCountry() {
        return originCountry;
    }

    public void setOriginCountry(String originCountry) {
        this.originCountry = originCountry == null ? null : originCountry.trim();
    }

    public String getDestCountry() {
        return destCountry;
    }

    public void setDestCountry(String destCountry) {
        this.destCountry = destCountry == null ? null : destCountry.trim();
    }

    public String getDeparturePort() {
        return departurePort;
    }

    public void setDeparturePort(String departurePort) {
        this.departurePort = departurePort == null ? null : departurePort.trim();
    }

    public String getDestPort() {
        return destPort;
    }

    public void setDestPort(String destPort) {
        this.destPort = destPort == null ? null : destPort.trim();
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product == null ? null : product.trim();
    }

    public String getEntryPort() {
        return entryPort;
    }

    public void setEntryPort(String entryPort) {
        this.entryPort = entryPort == null ? null : entryPort.trim();
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public String getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(String creatorId) {
        this.creatorId = creatorId == null ? null : creatorId.trim();
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator == null ? null : creator.trim();
    }

    public String getCreatorTel() {
        return creatorTel;
    }

    public void setCreatorTel(String creatorTel) {
        this.creatorTel = creatorTel == null ? null : creatorTel.trim();
    }

    public String getSupportStaff() {
        return supportStaff;
    }

    public void setSupportStaff(String supportStaff) {
        this.supportStaff = supportStaff == null ? null : supportStaff.trim();
    }

    public String getSupportStaffTel() {
        return supportStaffTel;
    }

    public void setSupportStaffTel(String supportStaffTel) {
        this.supportStaffTel = supportStaffTel == null ? null : supportStaffTel.trim();
    }

    public Integer getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(Integer delFlag) {
        this.delFlag = delFlag;
    }

    public Integer getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Integer getCertStatus() {
        return certStatus;
    }

    public void setCertStatus(Integer certStatus) {
        this.certStatus = certStatus;
    }

    public Integer getScheduleStatus() {
        return scheduleStatus;
    }

    public void setScheduleStatus(Integer scheduleStatus) {
        this.scheduleStatus = scheduleStatus;
    }

    public Integer getCustomsClearanceStatus() {
        return customsClearanceStatus;
    }

    public void setCustomsClearanceStatus(Integer customsClearanceStatus) {
        this.customsClearanceStatus = customsClearanceStatus;
    }

    public Integer getInspectNeed() {
        return inspectNeed;
    }

    public void setInspectNeed(Integer inspectNeed) {
        this.inspectNeed = inspectNeed;
    }

    public Integer getClearanceNeed() {
        return clearanceNeed;
    }

    public void setClearanceNeed(Integer clearanceNeed) {
        this.clearanceNeed = clearanceNeed;
    }

    public Integer getTransportNeed() {
        return transportNeed;
    }

    public void setTransportNeed(Integer transportNeed) {
        this.transportNeed = transportNeed;
    }

    public Integer getPaymentNeed() {
        return paymentNeed;
    }

    public void setPaymentNeed(Integer paymentNeed) {
        this.paymentNeed = paymentNeed;
    }

    public Integer getAdvanceNeed() {
        return advanceNeed;
    }

    public void setAdvanceNeed(Integer advanceNeed) {
        this.advanceNeed = advanceNeed;
    }

    public String getProducerOrg() {
        return producerOrg;
    }

    public void setProducerOrg(String producerOrg) {
        this.producerOrg = producerOrg == null ? null : producerOrg.trim();
    }

    public Date getAfffirmDate() {
        return afffirmDate;
    }

    public void setAfffirmDate(Date afffirmDate) {
        this.afffirmDate = afffirmDate;
    }

    public Date getCusAdviceDate() {
        return cusAdviceDate;
    }

    public void setCusAdviceDate(Date cusAdviceDate) {
        this.cusAdviceDate = cusAdviceDate;
    }

    public String getOrderSource() {
        return orderSource;
    }

    public void setOrderSource(String orderSource) {
        this.orderSource = orderSource == null ? null : orderSource.trim();
    }

    public String getOrderPlacer() {
        return orderPlacer;
    }

    public void setOrderPlacer(String orderPlacer) {
        this.orderPlacer = orderPlacer == null ? null : orderPlacer.trim();
    }

    public String getBookingNo() {
        return bookingNo;
    }

    public void setBookingNo(String bookingNo) {
        this.bookingNo = bookingNo == null ? null : bookingNo.trim();
    }

    public String getSalesman() {
        return salesman;
    }

    public void setSalesman(String salesman) {
        this.salesman = salesman == null ? null : salesman.trim();
    }

    public String getSalesmanTel() {
        return salesmanTel;
    }

    public void setSalesmanTel(String salesmanTel) {
        this.salesmanTel = salesmanTel == null ? null : salesmanTel.trim();
    }

    public String getHandlingSuggestion() {
        return handlingSuggestion;
    }

    public void setHandlingSuggestion(String handlingSuggestion) {
        this.handlingSuggestion = handlingSuggestion == null ? null : handlingSuggestion.trim();
    }

    public Date getCheckTime() {
        return checkTime;
    }

    public void setCheckTime(Date checkTime) {
        this.checkTime = checkTime;
    }

    public Date getSupplementTime() {
        return supplementTime;
    }

    public void setSupplementTime(Date supplementTime) {
        this.supplementTime = supplementTime;
    }

    public String getExtendType() {
        return extendType;
    }

    public void setExtendType(String extendType) {
        this.extendType = extendType == null ? null : extendType.trim();
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId == null ? null : companyId.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}