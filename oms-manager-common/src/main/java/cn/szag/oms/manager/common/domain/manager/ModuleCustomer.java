package cn.szag.oms.manager.common.domain.manager;

import java.util.Date;
import java.util.List;


public class ModuleCustomer {
	 private String id;

	    private String name;

	    private Date publishat;

	    private Integer linktype;

	    private String linkurl;

	    private String parentid;

	    private String idpath;

	    private Integer status;

	    private String sort;

	    private String productId;

	    private String icon;

	    private String type;

	    private String description;

	    private String btnid;

	    private String btnfun;

	    private String source;
	    
	    private List<ModuleCustomer> children;
	    
	    public List<ModuleCustomer> getChildren() {
			return children;
		}

		public void setChildren(List<ModuleCustomer> children) {
			this.children = children;
		}

	    
	   
		public String getId() {
	        return id;
	    }

	    public void setId(String id) {
	        this.id = id == null ? null : id.trim();
	    }

	    public String getName() {
	        return name;
	    }

	    public void setName(String name) {
	        this.name = name == null ? null : name.trim();
	    }

	    public Date getPublishat() {
	        return publishat;
	    }

	    public void setPublishat(Date publishat) {
	        this.publishat = publishat;
	    }

	    public Integer getLinktype() {
	        return linktype;
	    }

	    public void setLinktype(Integer linktype) {
	        this.linktype = linktype;
	    }

	    public String getLinkurl() {
	        return linkurl;
	    }

	    public void setLinkurl(String linkurl) {
	        this.linkurl = linkurl == null ? null : linkurl.trim();
	    }

	    public String getParentid() {
	        return parentid;
	    }

	    public void setParentid(String parentid) {
	        this.parentid = parentid == null ? null : parentid.trim();
	    }

	    public String getIdpath() {
	        return idpath;
	    }

	    public void setIdpath(String idpath) {
	        this.idpath = idpath == null ? null : idpath.trim();
	    }

	    public Integer getStatus() {
	        return status;
	    }

	    public void setStatus(Integer status) {
	        this.status = status;
	    }

	    public String getSort() {
	        return sort;
	    }

	    public void setSort(String sort) {
	        this.sort = sort == null ? null : sort.trim();
	    }

	    public String getProductId() {
	        return productId;
	    }

	    public void setProductId(String productId) {
	        this.productId = productId == null ? null : productId.trim();
	    }

	    public String getIcon() {
	        return icon;
	    }

	    public void setIcon(String icon) {
	        this.icon = icon == null ? null : icon.trim();
	    }

	    public String getType() {
	        return type;
	    }

	    public void setType(String type) {
	        this.type = type == null ? null : type.trim();
	    }

	    public String getDescription() {
	        return description;
	    }

	    public void setDescription(String description) {
	        this.description = description == null ? null : description.trim();
	    }

	    public String getBtnid() {
	        return btnid;
	    }

	    public void setBtnid(String btnid) {
	        this.btnid = btnid == null ? null : btnid.trim();
	    }

	    public String getBtnfun() {
	        return btnfun;
	    }

	    public void setBtnfun(String btnfun) {
	        this.btnfun = btnfun == null ? null : btnfun.trim();
	    }

	    public String getSource() {
	        return source;
	    }

	    public void setSource(String source) {
	        this.source = source == null ? null : source.trim();
	    }

}
