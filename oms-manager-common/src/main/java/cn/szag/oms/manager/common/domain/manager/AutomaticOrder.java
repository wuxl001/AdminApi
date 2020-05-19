package cn.szag.oms.manager.common.domain.manager;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import java.util.Date;

/**
 * 自助下单
 */
public class AutomaticOrder implements Serializable {

	private String id; // 主键
	private Integer orderStatus; // 订单状态
	private String orderNo; // 订单号
	private String transportWay; // 运输方式
	private String domesticConsignee; // 境内收货人
	private String overseasShipper; // 境外发货人
	private String customerName; // 客户名称
	private String originCountry; // 原产国
	private String extractOrderNum; // 提单号
	private String product; // 商品名称
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date startCreatTime; // 创建日期（始）
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date endCreatTime; // 创建日期（终）
	private String creator; // 创建人
	private String creatorId; // 创建人 id
	private String companyId; // 客户公司id
	private String boxNo; // 柜号
	private String worklistNo; // 工作单号
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date startAdvcusTime; // 客户通知起始时间
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date endAdvcusTime; // 客户通知起始时间

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date startCheckTime; // 客户通知起始时间
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date endCheckTime; // 客户通知起始时间

	
	private String destPort;
	
	/**
	 * 进度状态
	 */
	private String scheduleStatus;
	/**
	 * 清关状态
	 */
	private Integer customsClearanceStatus;
	/**
	 * 完成情况0：未完成/1：已完成
	 */
	@JsonIgnore
	private Integer finishStatus;
	/**
	 * 是否关注
	 */
	@JsonIgnore
	private Integer isAttention;
	/**
	 * 开始时间
	 */
	@JsonIgnore
	private Date startTime;
	/**
	 * 客户通知日期（始）
	 */
	@JsonIgnore
	private String cusAdviceDateStart;
	/**
	 * 客户通知日期（始）
	 */
	@JsonIgnore
	private String cusAdviceDateEnd;
	
	/**
	 * 结束时间
	 */
	@JsonIgnore
	private Date endTime;
	/**
	 * 评分
	 */
	private Integer Score;
	/**
	 * 报空时间
	 */
	private Date emptyTime;


	/**
	 * 是否报空
	 */
	@JsonIgnore
	private String ifEmpty;


	/**
	 * 航名航次
	 */
	@JsonIgnore
	private String shipInfo;
	/**
	 * 到港日期（始）
	 */
	@JsonIgnore
	private String arriveTimeStart;

	/**
	 * 到港日期（始）
	 */
	@JsonIgnore
	private String arriveTimeEnd;

	/**
	 */
	@JsonIgnore
	private String condition;

	/**
	 * 关键字
	 */
	@JsonIgnore
	private String keyword;

	/**
	 * 1=今日到港/2=明日到港/3=2天后到港/4=未报空/5=免箱逾期/6=免舱逾期/7=未还箱/8=已到港24小时未提货/9=评分低于2分
	 */
	@JsonIgnore
	private String extentStatus;
	
	private String containerId;
	
	private String orderId;
	
	private String certStatus;
	
	private String extractOrdernum;
	
	
	
	public String getExtractOrdernum() {
		return extractOrdernum;
	}

	public void setExtractOrdernum(String extractOrdernum) {
		this.extractOrdernum = extractOrdernum;
	}

	public String getCertStatus() {
		return certStatus;
	}

	public void setCertStatus(String certStatus) {
		this.certStatus = certStatus;
	}

	public String getContainerId() {
		return containerId;
	}

	public void setContainerId(String containerId) {
		this.containerId = containerId;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getDestPort() {
		return destPort;
	}

	public void setDestPort(String destPort) {
		this.destPort = destPort;
	}

	public String getCusAdviceDateStart() {
		return cusAdviceDateStart;
	}

	public void setCusAdviceDateStart(String cusAdviceDateStart) {
		this.cusAdviceDateStart = cusAdviceDateStart;
	}

	public String getCusAdviceDateEnd() {
		return cusAdviceDateEnd;
	}

	public void setCusAdviceDateEnd(String cusAdviceDateEnd) {
		this.cusAdviceDateEnd = cusAdviceDateEnd;
	}

	public String getIfEmpty() {
		return ifEmpty;
	}

	public void setIfEmpty(String ifEmpty) {
		this.ifEmpty = ifEmpty;
	}

	public String getShipInfo() {
		return shipInfo;
	}

	public void setShipInfo(String shipInfo) {
		this.shipInfo = shipInfo;
	}

	public String getArriveTimeStart() {
		return arriveTimeStart;
	}

	public void setArriveTimeStart(String arriveTimeStart) {
		this.arriveTimeStart = arriveTimeStart;
	}

	public String getArriveTimeEnd() {
		return arriveTimeEnd;
	}

	public void setArriveTimeEnd(String arriveTimeEnd) {
		this.arriveTimeEnd = arriveTimeEnd;
	}

	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public String getExtentStatus() {
		return extentStatus;
	}

	public void setExtentStatus(String extentStatus) {
		this.extentStatus = extentStatus;
	}


	public Date getEmptyTime() {
		return emptyTime;
	}

	public void setEmptyTime(Date emptyTime) {
		this.emptyTime = emptyTime;
	}

	public Integer getScore() {
		return Score;
	}

	public void setScore(Integer score) {
		Score = score;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	

	public String getScheduleStatus() {
		return scheduleStatus;
	}

	public void setScheduleStatus(String scheduleStatus) {
		this.scheduleStatus = scheduleStatus;
	}

	public Integer getCustomsClearanceStatus() {
		return customsClearanceStatus;
	}

	public void setCustomsClearanceStatus(Integer customsClearanceStatus) {
		this.customsClearanceStatus = customsClearanceStatus;
	}

	public Integer getFinishStatus() {
		return finishStatus;
	}

	public void setFinishStatus(Integer finishStatus) {
		this.finishStatus = finishStatus;
	}

	public Integer getIsAttention() {
		return isAttention;
	}

	public void setIsAttention(Integer isAttention) {
		this.isAttention = isAttention;
	}

	public Date getStartCreatTime() {
		return startCreatTime;
	}

	public void setStartCreatTime(Date startCreatTime) {
		this.startCreatTime = startCreatTime;
	}

	public Date getEndCreatTime() {
		return endCreatTime;
	}

	public void setEndCreatTime(Date endCreatTime) {
		this.endCreatTime = endCreatTime;
	}

	public Date getStartAdvcusTime() {
		return startAdvcusTime;
	}

	public void setStartAdvcusTime(Date startAdvcusTime) {
		this.startAdvcusTime = startAdvcusTime;
	}

	public Date getEndAdvcusTime() {
		return endAdvcusTime;
	}

	public void setEndAdvcusTime(Date endAdvcusTime) {
		this.endAdvcusTime = endAdvcusTime;
	}

	public String getWorklistNo() {
		return worklistNo;
	}

	public void setWorklistNo(String worklistNo) {
		this.worklistNo = worklistNo;
	}

	public String getConsumerOrg() {
		return consumerOrg;
	}

	public void setConsumerOrg(String consumerOrg) {
		this.consumerOrg = consumerOrg;
	}

	private String consumerOrg; // 消费使用单位

	public String getBoxNo() {
		return boxNo;
	}

	public void setBoxNo(String boxNo) {
		this.boxNo = boxNo;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Integer getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(Integer orderStatus) {
		this.orderStatus = orderStatus;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getTransportWay() {
		return transportWay;
	}

	public void setTransportWay(String transportWay) {
		this.transportWay = transportWay;
	}

	public String getDomesticConsignee() {
		return domesticConsignee;
	}

	public void setDomesticConsignee(String domesticConsignee) {
		this.domesticConsignee = domesticConsignee;
	}

	public String getOverseasShipper() {
		return overseasShipper;
	}

	public void setOverseasShipper(String overseasShipper) {
		this.overseasShipper = overseasShipper;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getOriginCountry() {
		return originCountry;
	}

	public void setOriginCountry(String originCountry) {
		this.originCountry = originCountry;
	}

	public String getExtractOrderNum() {
		return extractOrderNum;
	}

	public void setExtractOrderNum(String extractOrderNum) {
		this.extractOrderNum = extractOrderNum;
	}

	public String getProduct() {
		return product;
	}

	public void setProduct(String product) {
		this.product = product;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public String getCreatorId() {
		return creatorId;
	}

	public void setCreatorId(String creatorId) {
		this.creatorId = creatorId;
	}

	public String getCompanyId() {
		return companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

	public Date getStartCheckTime() {
		return startCheckTime;
	}

	public void setStartCheckTime(Date startCheckTime) {
		this.startCheckTime = startCheckTime;
	}

	public Date getEndCheckTime() {
		return endCheckTime;
	}

	public void setEndCheckTime(Date endCheckTime) {
		this.endCheckTime = endCheckTime;
	}
	
	
}
