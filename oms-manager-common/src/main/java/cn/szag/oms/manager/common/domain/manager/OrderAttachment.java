package cn.szag.oms.manager.common.domain.manager;

import java.io.Serializable;
import java.util.Date;
/**
 * 单证
* @ClassName: OrderAttachment 
* @Description: TODO
* @author dengyanghao
* @date 2019年9月19日 下午5:25:45
 */
public class OrderAttachment implements Serializable{
    private String id;
    
    private String orderId;//订单Id

    private String fileUrl;//文件路径

    private Date createtime;//创建时间

    private String creatorId;//创建人Id

    private String creator;//创建人

    private Integer delFlag;//删除标志位（0=未/1=已）

    private Date lastUpdateTime;//最后更新时间

    private String lastUpdator;//最后更新人

    private String lastUpdatorId;//更新人Id

    private Integer isQualified;//是否合格

    private String attachmentName;//附件名称
    
    private Integer size;

    private String format;

    private Integer syncStatus;
    
    private Integer type;
    
    private Integer status;//0未读1已读
    

    
    
    public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getSize() {
		return size;
	}

	public void setSize(Integer size) {
		this.size = size;
	}

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}

	public Integer getSyncStatus() {
		return syncStatus;
	}

	public void setSyncStatus(Integer syncStatus) {
		this.syncStatus = syncStatus;
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

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl == null ? null : fileUrl.trim();
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

    public Integer getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(Integer delFlag) {
        this.delFlag = delFlag;
    }

    public Date getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(Date lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    public String getLastUpdator() {
        return lastUpdator;
    }

    public void setLastUpdator(String lastUpdator) {
        this.lastUpdator = lastUpdator == null ? null : lastUpdator.trim();
    }

    public String getLastUpdatorId() {
        return lastUpdatorId;
    }

    public void setLastUpdatorId(String lastUpdatorId) {
        this.lastUpdatorId = lastUpdatorId == null ? null : lastUpdatorId.trim();
    }

    public Integer getIsQualified() {
        return isQualified;
    }

    public void setIsQualified(Integer isQualified) {
        this.isQualified = isQualified;
    }

    public String getAttachmentName() {
        return attachmentName;
    }

    public void setAttachmentName(String attachmentName) {
        this.attachmentName = attachmentName == null ? null : attachmentName.trim();
    }
}