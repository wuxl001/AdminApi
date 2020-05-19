package cn.szag.oms.manager.common.domain.manager;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * 消息中心
 * 
 * @ClassName: ManagerNotice
 * @Description: TODO
 * @author dengyanghao
 * @date 2019年9月26日 上午9:27:39
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ManagerNotice {
	private String id;

	private Integer titleType;

	private String title;

	private String orderId;

	private String content;

	private String receiverId;

	private Integer readStatus;

	private Date createtime;

	private Date readtime;
	
	private String containerId;
	/**
	 * 工作单号
	 */
	private String worklistNo;
	/**
	 * 订单号
	 */
	private String orderNo;
	/**
	 * 集装箱号
	 */
	private String boxNo;
	/**
	 * 关键字
	 */
	private String keyWord;
	
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

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getBoxNo() {
		return boxNo;
	}

	public void setBoxNo(String boxNo) {
		this.boxNo = boxNo;
	}

	public String getContainerId() {
		return containerId;
	}

	public void setContainerId(String containerId) {
		this.containerId = containerId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id == null ? null : id.trim();
	}

	public Integer getTitleType() {
		return titleType;
	}

	public void setTitleType(Integer titleType) {
		this.titleType = titleType;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title == null ? null : title.trim();
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId == null ? null : orderId.trim();
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content == null ? null : content.trim();
	}

	public String getReceiverId() {
		return receiverId;
	}

	public void setReceiverId(String receiverId) {
		this.receiverId = receiverId == null ? null : receiverId.trim();
	}

	public Integer getReadStatus() {
		return readStatus;
	}

	public void setReadStatus(Integer readStatus) {
		this.readStatus = readStatus;
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public Date getReadtime() {
		return readtime;
	}

	public void setReadtime(Date readtime) {
		this.readtime = readtime;
	}
}