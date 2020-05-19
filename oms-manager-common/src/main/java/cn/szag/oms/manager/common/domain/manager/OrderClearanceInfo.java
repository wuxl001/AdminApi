package cn.szag.oms.manager.common.domain.manager;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;
/**
 * 报关信息
* @ClassName: OrderClearanceInfo 
* @Description: TODO
* @author dengyanghao
* @date 2019年9月19日 下午5:28:07
 */
public class OrderClearanceInfo implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;
    /**
     * 报关单号
     */
    private String customsDeclarationNo;
    /**
     * 报关时间
     */
    private Date customsDeclarationTime;
    /**
     * 状态
     */
    private Integer status;
    /**
     * 出闸时间
     */
    @JsonIgnore
    private Date outdate;
    /**
     * 海关查验点
     */
    @JsonIgnore
    private String checkSite;
    /**
     * 查验时间
     */
    private Date checkTime;
    /**
     * 订单Id
     */
    @JsonIgnore
    private String orderId;
    /**
     * 出闸日期（口岸）
     */
    @JsonIgnore
    private Date outdate2;
    
    /**
     * 异常备注
     */
    private String abnormalRemark;
    private Date createTime;

    private Integer delFlag;

    private String createrId;

    private String updaterId;

    private Date updateTime;
    
    private String scheduleStatus;
    
    
    

	public String getScheduleStatus() {
		return scheduleStatus;
	}

	public void setScheduleStatus(String scheduleStatus) {
		this.scheduleStatus = scheduleStatus;
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getCustomsDeclarationNo() {
        return customsDeclarationNo;
    }

    public void setCustomsDeclarationNo(String customsDeclarationNo) {
        this.customsDeclarationNo = customsDeclarationNo == null ? null : customsDeclarationNo.trim();
    }

    public Date getCustomsDeclarationTime() {
        return customsDeclarationTime;
    }

    public void setCustomsDeclarationTime(Date customsDeclarationTime) {
        this.customsDeclarationTime = customsDeclarationTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getOutdate() {
        return outdate;
    }

    public void setOutdate(Date outdate) {
        this.outdate = outdate;
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

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId == null ? null : orderId.trim();
    }

    public Date getOutdate2() {
        return outdate2;
    }

    public void setOutdate2(Date outdate2) {
        this.outdate2 = outdate2;
    }

    public String getAbnormalRemark() {
        return abnormalRemark;
    }

    public void setAbnormalRemark(String abnormalRemark) {
        this.abnormalRemark = abnormalRemark == null ? null : abnormalRemark.trim();
    }
}