package cn.szag.oms.manager.common.domain.manager;

import java.util.Date;

public class CustomerServicerelation {
    private String id;

    private Integer salesmanId;

    private String customerId;

    private Integer serviceId;

    private Integer wltype;

    private String origin;

    private Integer inport;

    private String creator;

    private Date createTime;

    private String editor;

    private Date lastupdate;

    

    public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Integer getSalesmanId() {
        return salesmanId;
    }

    public void setSalesmanId(Integer salesmanId) {
        this.salesmanId = salesmanId;
    }


    public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public Integer getServiceId() {
        return serviceId;
    }

    public void setServiceId(Integer serviceId) {
        this.serviceId = serviceId;
    }

    public Integer getWltype() {
        return wltype;
    }

    public void setWltype(Integer wltype) {
        this.wltype = wltype;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin == null ? null : origin.trim();
    }

    public Integer getInport() {
        return inport;
    }

    public void setInport(Integer inport) {
        this.inport = inport;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator == null ? null : creator.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getEditor() {
        return editor;
    }

    public void setEditor(String editor) {
        this.editor = editor == null ? null : editor.trim();
    }

    public Date getLastupdate() {
        return lastupdate;
    }

    public void setLastupdate(Date lastupdate) {
        this.lastupdate = lastupdate;
    }
}