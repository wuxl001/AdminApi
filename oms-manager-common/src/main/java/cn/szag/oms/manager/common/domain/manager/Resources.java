package cn.szag.oms.manager.common.domain.manager;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.type.Alias;

//菜单
@SuppressWarnings("serial")
@Alias("BaseResources")
public class Resources implements Serializable {
	private String id;//菜单序列号
	private String name;//菜单名称
	private Date publishat;//发布时间
	private String parentId;//父菜单序列号	
	private String parentName;//父菜单名称
	private String btnId;//按钮主键
	private String btnFun;//按钮方法
	private String idpath;//菜单编号
	private Integer type;//菜单类型：资源类型(1:菜单，2:功能，3:按钮)
	private Integer linkType;//跳转方式：0:集成访问；1:跳转访问
	private String linkUrl;//菜单路径
	private String icon;//菜单图标
	private int sort;//排序
	private Integer status;//状态
	private String productId;//产品序列号;
	private List<Resources> nodes = new ArrayList<Resources>();
	private String companyCode;//公司编号
	private String description;
	private String source;//来源
	private String tabname;//表名

	
	public String getTabname() {
		return tabname;
	}

	public void setTabname(String tabname) {
		this.tabname = tabname;
	}

	public List<Resources> getNodes() {
		return nodes;
	}

	public void setNodes(List<Resources> nodes) {
		this.nodes = nodes;
	}

	@Override
	public String toString() {
		return "Resources [id=" + id + ", name=" + name + ", parentId="
				+ parentId + ", nodes=" + nodes + "]";
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getPublishat() {
		return publishat;
	}

	public void setPublishat(Date publishat) {
		this.publishat = publishat;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getBtnId() {
		return btnId;
	}

	public void setBtnId(String btnId) {
		this.btnId = btnId;
	}

	public String getBtnFun() {
		return btnFun;
	}

	public void setBtnFun(String btnFun) {
		this.btnFun = btnFun;
	}

	public String getIdpath() {
		return idpath;
	}

	public void setIdpath(String idpath) {
		this.idpath = idpath;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getLinkType() {
		return linkType;
	}

	public void setLinkType(Integer linkType) {
		this.linkType = linkType;
	}

	public String getLinkUrl() {
		return linkUrl;
	}

	public void setLinkUrl(String linkUrl) {
		this.linkUrl = linkUrl;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public int getSort() {
		return sort;
	}

	public void setSort(int sort) {
		this.sort = sort;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getCompanyCode() {
		return companyCode;
	}

	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}
}