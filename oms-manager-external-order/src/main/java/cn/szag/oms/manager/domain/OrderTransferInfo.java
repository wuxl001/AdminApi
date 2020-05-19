package cn.szag.oms.manager.domain;

import java.util.Date;
import java.util.List;

public class OrderTransferInfo {
    private String id;

    private String orderId;//订单id

    private String boxNo;//柜号

    private String content;//内容

    private String attachment;//附件

    private Date createtime;//创建时间

    private String carNo;//车牌号

    private String driverPhone;//司机电话

    private Date arrivalDate;//抵达时间

    private Integer status;//状态

    private String contacts;//收货单位

    private String plocation;//收货地址

    private String telephone;//收货联系方式

    private Date departTime;//收货时间

    private Date leaveTime;//出车时间

    private String abnormalRemark;//离港时间
    
    private String containerId;//集装箱Id
    
    private List<DispatchOrder> dispatchList;//调度集合
    
    private List<PictureAttachment> pictureAttachmentList;//附件集合
    
    public String getContainerId() {
		return containerId;
	}

	public void setContainerId(String containerId) {
		this.containerId = containerId;
	}

	public List<DispatchOrder> getDispatchList() {
		return dispatchList;
	}

	public void setDispatchList(List<DispatchOrder> dispatchList) {
		this.dispatchList = dispatchList;
	}

	public List<PictureAttachment> getPictureAttachmentList() {
		return pictureAttachmentList;
	}

	public void setPictureAttachmentList(List<PictureAttachment> pictureAttachmentList) {
		this.pictureAttachmentList = pictureAttachmentList;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public String getAttachment() {
        return attachment;
    }

    public void setAttachment(String attachment) {
        this.attachment = attachment == null ? null : attachment.trim();
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public String getCarNo() {
        return carNo;
    }

    public void setCarNo(String carNo) {
        this.carNo = carNo == null ? null : carNo.trim();
    }

    public String getDriverPhone() {
        return driverPhone;
    }

    public void setDriverPhone(String driverPhone) {
        this.driverPhone = driverPhone == null ? null : driverPhone.trim();
    }

    public Date getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(Date arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getContacts() {
        return contacts;
    }

    public void setContacts(String contacts) {
        this.contacts = contacts == null ? null : contacts.trim();
    }

    public String getPlocation() {
        return plocation;
    }

    public void setPlocation(String plocation) {
        this.plocation = plocation == null ? null : plocation.trim();
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone == null ? null : telephone.trim();
    }

    public Date getDepartTime() {
        return departTime;
    }

    public void setDepartTime(Date departTime) {
        this.departTime = departTime;
    }

    public Date getLeaveTime() {
        return leaveTime;
    }

    public void setLeaveTime(Date leaveTime) {
        this.leaveTime = leaveTime;
    }

    public String getAbnormalRemark() {
        return abnormalRemark;
    }

    public void setAbnormalRemark(String abnormalRemark) {
        this.abnormalRemark = abnormalRemark == null ? null : abnormalRemark.trim();
    }
}