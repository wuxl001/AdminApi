package cn.szag.oms.manager.common.domain.manager;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Commonality {
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
	private Integer isAttention;
	/**
	 * 柜号
	 */
	@JsonIgnore
	private String boxNo;
	/**
	 * 开始时间
	 */
	private String startTime;
	/**
	 * 结束时间
	 */
	private String endTime;
	/**
	 * 评分
	 */
	private Integer Score;
	/**
	 * 报空时间
	 */
	private Date emptyTime;
	/**
	 * 客户名称
	 */
	private String customerName;
	/**
	 * 关注id
	 */
	private String attentionId;

	private String businessType; // 1=进口 2=出口
	private String isAbnormal; // 1/是否有异常 0=否 1=是
	private String isBill; // 是否有账单 0=否 1=是
	private Date arriveTime; // 到港时间
	private Date cyDate; // 预计截关日期
	private Integer quantity; // 商品件数
	private String isScore; // isScore
	/**
	 * 订单号
	 */
	private String orderNo;
	/**
	 * 提单号
	 */
	private String extractOrderNum;
	/**
	 * 运输方式
	 */
	private String transportWay;
	/**
	 * 原产国
	 */
	private String originCountry;
	/**
	 * 截关日期（始）
	 */
	private String cyDateStart;
	/**
	 * 截关日期（终）
	 */
	private String cyDateEnd;
	/**
	 * 客户通知日期（始）
	 */
	private String cusAdviceDateStart;
	/**
	 * 客户通知日期（终）
	 */
	private String cusAdviceDateEnd;
	/**
	 * 目的港
	 */
	private String destPort;
	
	/**
	 * 船名航次
	 */
	private String shipInfo;
	/**
	 * 目的国
	 */
	private String destCountry;
	
	public String getDestCountry() {
		return destCountry;
	}

	public void setDestCountry(String destCountry) {
		this.destCountry = destCountry;
	}

	public String getShipInfo() {
		return shipInfo;
	}

	public void setShipInfo(String shipInfo) {
		this.shipInfo = shipInfo;
	}

	public String getDestPort() {
		return destPort;
	}

	public void setDestPort(String destPort) {
		this.destPort = destPort;
	}

	public String getCyDateStart() {
		return cyDateStart;
	}

	public void setCyDateStart(String cyDateStart) {
		this.cyDateStart = cyDateStart;
	}

	public String getCyDateEnd() {
		return cyDateEnd;
	}

	public void setCyDateEnd(String cyDateEnd) {
		this.cyDateEnd = cyDateEnd;
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

	public String getAttentionId() {
		return attentionId;
	}

	public void setAttentionId(String attentionId) {
		this.attentionId = attentionId;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getExtractOrderNum() {
		return extractOrderNum;
	}

	public void setExtractOrderNum(String extractOrderNum) {
		this.extractOrderNum = extractOrderNum;
	}

	public String getTransportWay() {
		return transportWay;
	}

	public void setTransportWay(String transportWay) {
		this.transportWay = transportWay;
	}

	public String getOriginCountry() {
		return originCountry;
	}

	public void setOriginCountry(String originCountry) {
		this.originCountry = originCountry;
	}

	public String getBusinessType() {
		return businessType;
	}

	public void setBusinessType(String businessType) {
		this.businessType = businessType;
	}

	public String getIsAbnormal() {
		return isAbnormal;
	}

	public void setIsAbnormal(String isAbnormal) {
		this.isAbnormal = isAbnormal;
	}

	public String getIsBill() {
		return isBill;
	}

	public void setIsBill(String isBill) {
		this.isBill = isBill;
	}

	public Date getArriveTime() {
		return arriveTime;
	}

	public void setArriveTime(Date arriveTime) {
		this.arriveTime = arriveTime;
	}
	
	public Date getCyDate() {
		return cyDate;
	}

	public void setCyDate(Date cyDate) {
		this.cyDate = cyDate;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public String getIsScore() {
		return isScore;
	}

	public void setIsScore(String isScore) {
		this.isScore = isScore;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
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


	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getBoxNo() {
		return boxNo;
	}

	public void setBoxNo(String boxNo) {
		this.boxNo = boxNo;
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
}
