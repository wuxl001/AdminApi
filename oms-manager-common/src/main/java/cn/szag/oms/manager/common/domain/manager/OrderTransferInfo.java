package cn.szag.oms.manager.common.domain.manager;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class OrderTransferInfo {
    private String id;

    private String orderId;//订单id
    @JsonIgnore
    private String boxNo;//柜号
    @JsonIgnore
    private String content;//内容
    @JsonIgnore
    private String attachment;//附件
    @JsonIgnore
    private Date createtime;//创建时间
    @JsonIgnore
    private String carNo;//车牌号
    @JsonIgnore
    private String driverPhone;//司机电话
    @JsonIgnore
    private Date arrivalDate;//抵达时间

    private Integer status;//状态
    @JsonIgnore
    private String contacts;//收货单位
    
    private String plocation;//收货地址
    
    private String telephone;//收货联系方式
    @JsonIgnore
    private Date departTime;//收货时间
    @JsonIgnore
    private Date leaveTime;//出车时间
    @JsonIgnore
    private String abnormalRemark;//离港时间
    @JsonIgnore
    private String containerId;//集装箱Id
    
    private List<DispatchOrder> dispatchList;//调度集合
    
    private List<PictureAttachment> image;//附件集合
    
    private List<BaseBoxNoMessage> list;
    
    private String containerlocation;//做柜地点

    private String seattle;//启运地点

    private String hoverporttitle;//中转地点

    private String transferno;//运输单号
    
    private Integer delFlag;

    private String updaterId;

    private Date updateTime;
    
    private Integer dispatchingstate;//调度状态
    
    private String scheduleStatus;

	public String getScheduleStatus() {
		return scheduleStatus;
	}

	public void setScheduleStatus(String scheduleStatus) {
		this.scheduleStatus = scheduleStatus;
	}

	public List<BaseBoxNoMessage> getList() {
		return list;
	}

	public void setList(List<BaseBoxNoMessage> list) {
		this.list = list;
	}

	public Integer getDispatchingstate() {
		return dispatchingstate;
	}

	public void setDispatchingstate(Integer dispatchingstate) {
		this.dispatchingstate = dispatchingstate;
	}

	public Integer getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(Integer delFlag) {
		this.delFlag = delFlag;
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

	public String getContainerlocation() {
		return containerlocation;
	}

	public void setContainerlocation(String containerlocation) {
		this.containerlocation = containerlocation;
	}

	public String getSeattle() {
		return seattle;
	}

	public void setSeattle(String seattle) {
		this.seattle = seattle;
	}

	public String getHoverporttitle() {
		return hoverporttitle;
	}

	public void setHoverporttitle(String hoverporttitle) {
		this.hoverporttitle = hoverporttitle;
	}

	public String getTransferno() {
		return transferno;
	}

	public void setTransferno(String transferno) {
		this.transferno = transferno;
	}

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


	public List<PictureAttachment> getImage() {
		return image;
	}

	public void setImage(List<PictureAttachment> image) {
		this.image = image;
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