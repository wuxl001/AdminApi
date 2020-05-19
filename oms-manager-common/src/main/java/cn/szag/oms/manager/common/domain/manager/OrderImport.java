package cn.szag.oms.manager.common.domain.manager;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * 进口
 * 
 * @ClassName: OrderImport
 * @Description: TODO
 * @author dengyanghao
 * @date 2019年9月19日 下午5:23:17
 */
public class OrderImport extends Commonality {
	private String id;

	private String orderNo;// 订单号

	private String customerName;// 客户名称

	private String transportWay;// 运输方式

	private String domesticConsignee;// 境内收货人

	private String domesticConsigneeId;// 境内收货人Id

	private String overseasShipper;// 境外发货人

	private String overseasShipperId;// 境外发货人

	private String extractOrderNum;// 提单号

	private String originCountry;// 原产国

	private Integer originCountryId;// 原产国

	private String destCountry;// 目的国

	private String destCountryId;// 目的国

	private String departurePort;// 启运港

	private String departurePortId;// 启运港

	private String destPort;// 目的港

	private String destPortId;// 目的港

	private String product;// 商品名称

	private String entryPort;// 进境口岸

	private String entryPortId;// 进境口岸

	private Date createtime;// 创建时间
	@JsonIgnore
	private String creatorId;// 创建人Id

	
	private String creator;// 创建人
	@JsonIgnore
	private String creatorTel;// 创建人手机号码

	private String supportStaff;// 客服

	private String supportStaffId;// 客服
	
	private String supportStaffTel;// 客服电话
	@JsonIgnore
	private Integer delFlag;// 删除标志位（0=未删除/1=已删除）

	
	private Integer orderStatus;// 订单状态

	private Integer certStatus;// 单证状态

	
	private String orderPlacer;// 下单人
	@JsonIgnore
	private String companyCode;// 创建人公司名称
	@JsonIgnore
	private String orderPlacerType;// 下单人类型
	@JsonIgnore
	private Integer inspectNeed;// 验货需求

	private Integer clearanceNeed;// 清关状
	
	private Integer transportNeed;// 运输需求

	private Integer paymentNeed;// 付汇需求
	@JsonIgnore
	private Integer advanceNeed;// 垫付需求

	private String consumerOrg;// 消费使用单位

	private String consumerOrgId;// 消费使用单位

	
	private String companyId;// 公司客户Id

	private String salesman;// 业务员
	
	
	
	private String salesmanEName;// 业务员

	private String salesmanId;// 业务员

	@JsonIgnore
	private String salesmanTel;// 业务员电话

	
	private String orderSource;// 订单来源

	private String closingPer;// 终止人

	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
	private Date closingDate;// 终止时间
	@JsonIgnore
	private String creatorCompany;//

	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
	private Date cusAdviceDate;// 客户通知时间

	private String agent;// 代理商
	
	private String agentEName;// 代理商

	private String agentId;// 代理商
	@JsonIgnore
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
	private Date agentAdviceDate;// 代理商通知时间

	private String boxNo;// 柜号

	private String remark;// 备注

	private String worklistNo;// 工作单号
	@JsonIgnore
	private String paymentOrderNo;// 付汇水单
	@JsonIgnore
	private Date afffirmDate;// 订单确认日期

	private String handlingSuggestion;// 处理意见
	private String trueCustomer;
	private String trueCustomerId;// 真实客户Id
	private String scheduleStatus;// 进度状态
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
	private Date checkTime;// 审核时间
	private Date supplementTime;// 补料时间
	private String extendType;// 扩展类型（终止、补料、修改）

	private String updaterId;

	private Date updateTime;
	
	private Integer goodstype;
	
	/**
	 * 发送时间
	 */
	private Date sendTime;
	/**
	 * 1=待发送，2=成功，3=失败
	 */
	private Integer disposeStatus;
	
	private String supportStaffDepa;//客服部门
	
	private String salesmanDepa; //业务员部门
	
	private String customerId;

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
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

	public String getAgentEName() {
		return agentEName;
	}

	public void setAgentEName(String agentEName) {
		this.agentEName = agentEName;
	}

	public String getSalesmanEName() {
		return salesmanEName;
	}

	public void setSalesmanEName(String salesmanEName) {
		this.salesmanEName = salesmanEName;
	}

	public Date getSendTime() {
		return sendTime;
	}

	public void setSendTime(Date sendTime) {
		this.sendTime = sendTime;
	}

	public Integer getGoodstype() {
		return goodstype;
	}

	public void setGoodstype(Integer goodstype) {
		this.goodstype = goodstype;
	}

	public String getUpdaterId() {
		return updaterId;
	}

	public void setUpdaterId(String updaterId) {
		this.updaterId = updaterId;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getTrueCustomerId() {
		return trueCustomerId;
	}

	public void setTrueCustomerId(String trueCustomerId) {
		this.trueCustomerId = trueCustomerId;
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
		this.extendType = extendType;
	}

	public String getScheduleStatus() {
		return scheduleStatus;
	}

	public void setScheduleStatus(String scheduleStatus) {
		this.scheduleStatus = scheduleStatus;
	}

	public String getHandlingSuggestion() {
		return handlingSuggestion;
	}

	public void setHandlingSuggestion(String handlingSuggestion) {
		this.handlingSuggestion = handlingSuggestion;
	}

	public String getWorklistNo() {
		return worklistNo;
	}

	public void setWorklistNo(String worklistNo) {
		this.worklistNo = worklistNo;
	}

	public String getPaymentOrderNo() {
		return paymentOrderNo;
	}

	public void setPaymentOrderNo(String paymentOrderNo) {
		this.paymentOrderNo = paymentOrderNo;
	}

	public Date getAfffirmDate() {
		return afffirmDate;
	}

	public void setAfffirmDate(Date afffirmDate) {
		this.afffirmDate = afffirmDate;
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

	public String getDomesticConsignee() {
		return domesticConsignee;
	}

	public void setDomesticConsignee(String domesticConsignee) {
		this.domesticConsignee = domesticConsignee == null ? null : domesticConsignee.trim();
	}

	public String getOverseasShipper() {
		return overseasShipper;
	}

	public void setOverseasShipper(String overseasShipper) {
		this.overseasShipper = overseasShipper == null ? null : overseasShipper.trim();
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

	public String getOrderPlacer() {
		return orderPlacer;
	}

	public void setOrderPlacer(String orderPlacer) {
		this.orderPlacer = orderPlacer == null ? null : orderPlacer.trim();
	}

	public String getCompanyCode() {
		return companyCode;
	}

	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode == null ? null : companyCode.trim();
	}

	public String getOrderPlacerType() {
		return orderPlacerType;
	}

	public void setOrderPlacerType(String orderPlacerType) {
		this.orderPlacerType = orderPlacerType == null ? null : orderPlacerType.trim();
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

	public String getConsumerOrg() {
		return consumerOrg;
	}

	public void setConsumerOrg(String consumerOrg) {
		this.consumerOrg = consumerOrg == null ? null : consumerOrg.trim();
	}

	public String getCompanyId() {
		return companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId == null ? null : companyId.trim();
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

	public String getOrderSource() {
		return orderSource;
	}

	public void setOrderSource(String orderSource) {
		this.orderSource = orderSource == null ? null : orderSource.trim();
	}

	public String getClosingPer() {
		return closingPer;
	}

	public void setClosingPer(String closingPer) {
		this.closingPer = closingPer == null ? null : closingPer.trim();
	}

	public Date getClosingDate() {
		return closingDate;
	}

	public void setClosingDate(Date closingDate) {
		this.closingDate = closingDate;
	}

	public String getCreatorCompany() {
		return creatorCompany;
	}

	public void setCreatorCompany(String creatorCompany) {
		this.creatorCompany = creatorCompany == null ? null : creatorCompany.trim();
	}

	public Date getCusAdviceDate() {
		return cusAdviceDate;
	}

	public void setCusAdviceDate(Date cusAdviceDate) {
		this.cusAdviceDate = cusAdviceDate;
	}

	public String getAgent() {
		return agent;
	}

	public void setAgent(String agent) {
		this.agent = agent == null ? null : agent.trim();
	}

	public Date getAgentAdviceDate() {
		return agentAdviceDate;
	}

	public void setAgentAdviceDate(Date agentAdviceDate) {
		this.agentAdviceDate = agentAdviceDate;
	}

	public String getBoxNo() {
		return boxNo;
	}

	public void setBoxNo(String boxNo) {
		this.boxNo = boxNo == null ? null : boxNo.trim();
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark == null ? null : remark.trim();
	}

	public String getDomesticConsigneeId() {
		return domesticConsigneeId;
	}

	public void setDomesticConsigneeId(String domesticConsigneeId) {
		this.domesticConsigneeId = domesticConsigneeId;
	}

	public String getOverseasShipperId() {
		return overseasShipperId;
	}

	public void setOverseasShipperId(String overseasShipperId) {
		this.overseasShipperId = overseasShipperId;
	}

	public Integer getOriginCountryId() {
		return originCountryId;
	}

	public void setOriginCountryId(Integer originCountryId) {
		this.originCountryId = originCountryId;
	}

	public String getDestCountryId() {
		return destCountryId;
	}

	public void setDestCountryId(String destCountryId) {
		this.destCountryId = destCountryId;
	}

	public String getDeparturePortId() {
		return departurePortId;
	}

	public void setDeparturePortId(String departurePortId) {
		this.departurePortId = departurePortId;
	}

	public String getDestPortId() {
		return destPortId;
	}

	public void setDestPortId(String destPortId) {
		this.destPortId = destPortId;
	}

	public String getEntryPortId() {
		return entryPortId;
	}

	public void setEntryPortId(String entryPortId) {
		this.entryPortId = entryPortId;
	}

	public String getSupportStaffId() {
		return supportStaffId;
	}

	public void setSupportStaffId(String supportStaffId) {
		this.supportStaffId = supportStaffId;
	}

	public String getSalesmanId() {
		return salesmanId;
	}

	public void setSalesmanId(String salesmanId) {
		this.salesmanId = salesmanId;
	}

	public String getAgentId() {
		return agentId;
	}

	public void setAgentId(String agentId) {
		this.agentId = agentId;
	}

	public String getConsumerOrgId() {
		return consumerOrgId;
	}

	public void setConsumerOrgId(String consumerOrgId) {
		this.consumerOrgId = consumerOrgId;
	}

	public String getTrueCustomer() {
		return trueCustomer;
	}

	public void setTrueCustomer(String trueCustomer) {
		this.trueCustomer = trueCustomer;
	}
    
	
}