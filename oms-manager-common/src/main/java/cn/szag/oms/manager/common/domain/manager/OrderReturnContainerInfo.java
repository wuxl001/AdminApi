package cn.szag.oms.manager.common.domain.manager;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class OrderReturnContainerInfo {
    private String id;

    private String orderId;//订单Id

    private String boxfreePeriod;//免箱截止日

    private Date emptyTime;//报空时间

    private Date returnboxTime;//还箱时间

    private Integer overdueDays;//逾期天数

    private Integer status;//还箱状态
    
    private String freeDemurrage;//免仓时间

    private String emptyPlace;//报空地点
    @JsonIgnore
    private String checkSite;//
    @JsonIgnore
    private Date checkTime;
    @JsonIgnore
    private String boxNo;//柜号

    private String abnormalRemark;//异常备注
    
    private String containerId;//集装箱Id
    
    private String returnboxPlace;//还箱地点

    private Date createTime;

    private Integer delFlag;

    private String createrId;

    private String updaterId;

    private Date updateTime;
    private Integer freedemurrageoverduedays;//免舱逾期天数


    private Integer boxfreeoverduedays;//免箱逾期天数

    private Date cusEmptyTime;//客户报空时间
    
    public Date getCusEmptyTime() {
		return cusEmptyTime;
	}

	public void setCusEmptyTime(Date cusEmptyTime) {
		this.cusEmptyTime = cusEmptyTime;
	}

	public String getReturnboxPlace() {
		return returnboxPlace;
	}

	public void setReturnboxPlace(String returnboxPlace) {
		this.returnboxPlace = returnboxPlace;
	}

	public Integer getFreedemurrageoverduedays() {
		return freedemurrageoverduedays;
	}

	public void setFreedemurrageoverduedays(Integer freedemurrageoverduedays) {
		this.freedemurrageoverduedays = freedemurrageoverduedays;
	}

	public Integer getBoxfreeoverduedays() {
		return boxfreeoverduedays;
	}

	public void setBoxfreeoverduedays(Integer boxfreeoverduedays) {
		this.boxfreeoverduedays = boxfreeoverduedays;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Integer getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(Integer delFlag) {
		this.delFlag = delFlag;
	}

	public String getCreaterId() {
		return createrId;
	}

	public void setCreaterId(String createrId) {
		this.createrId = createrId;
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

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId == null ? null : orderId.trim();
    }

    public String getBoxfreePeriod() {
        return boxfreePeriod;
    }

    public void setBoxfreePeriod(String boxfreePeriod) {
        this.boxfreePeriod = boxfreePeriod;
    }

    public Date getEmptyTime() {
        return emptyTime;
    }

    public void setEmptyTime(Date emptyTime) {
        this.emptyTime = emptyTime;
    }

    public Date getReturnboxTime() {
        return returnboxTime;
    }

    public void setReturnboxTime(Date returnboxTime) {
        this.returnboxTime = returnboxTime;
    }

    public Integer getOverdueDays() {
        return overdueDays;
    }

    public void setOverdueDays(Integer overdueDays) {
        this.overdueDays = overdueDays;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getFreeDemurrage() {
        return freeDemurrage;
    }

    public void setFreeDemurrage(String freeDemurrage) {
        this.freeDemurrage = freeDemurrage;
    }

    public String getEmptyPlace() {
        return emptyPlace;
    }

    public void setEmptyPlace(String emptyPlace) {
        this.emptyPlace = emptyPlace == null ? null : emptyPlace.trim();
    }

    public String getCheckSite() {
        return checkSite;
    }

    public void setCheckSite(String checkSite) {
        this.checkSite = checkSite == null ? null : checkSite.trim();
    }

    public Date getCheckTime() {
        return checkTime;
    }

    public void setCheckTime(Date checkTime) {
        this.checkTime = checkTime;
    }

    public String getBoxNo() {
        return boxNo;
    }

    public void setBoxNo(String boxNo) {
        this.boxNo = boxNo == null ? null : boxNo.trim();
    }

    public String getAbnormalRemark() {
        return abnormalRemark;
    }

    public void setAbnormalRemark(String abnormalRemark) {
        this.abnormalRemark = abnormalRemark == null ? null : abnormalRemark.trim();
    }
}