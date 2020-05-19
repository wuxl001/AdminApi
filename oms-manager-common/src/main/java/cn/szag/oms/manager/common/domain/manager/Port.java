package cn.szag.oms.manager.common.domain.manager;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Port {
    private String id;

    private String code;//编码

    private String name;//中文名称

    private String ename;//英文名称

    private String country;//所属国家

    private String contact;//联系人

    private String telephone;//电话

    private String address;//地址

    private Date createdate;//创建时间

    private String creator;//创建人

    private Date lastupdate;//更新时间

    private String editor;//更新人

    private String inPortId;//目的港为父类，进口维护选择了目的港后，仅可选择固定的子类口岸。进境口岸为子类
    @JsonIgnore
    private Integer delFlag;//删除标志位（0=未删除/1=已删除）

    private String remark;//备注
    
    private String keyword;//关键字
    
    private Integer domestic;//区分是否显示
    
    private String countryId;//所属国家Id
    
    public String getCountryId() {
		return countryId;
	}

	public void setCountryId(String countryId) {
		this.countryId = countryId;
	}

	public Integer getDomestic() {
		return domestic;
	}

	public void setDomestic(Integer domestic) {
		this.domestic = domestic;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getEname() {
        return ename;
    }

    public void setEname(String ename) {
        this.ename = ename == null ? null : ename.trim();
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country == null ? null : country.trim();
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact == null ? null : contact.trim();
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone == null ? null : telephone.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public Date getCreatedate() {
        return createdate;
    }

    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator == null ? null : creator.trim();
    }

    public Date getLastupdate() {
        return lastupdate;
    }

    public void setLastupdate(Date lastupdate) {
        this.lastupdate = lastupdate;
    }

    public String getEditor() {
        return editor;
    }

    public void setEditor(String editor) {
        this.editor = editor == null ? null : editor.trim();
    }

    public String getInPortId() {
        return inPortId;
    }

    public void setInPortId(String inPortId) {
        this.inPortId = inPortId == null ? null : inPortId.trim();
    }

    public Integer getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(Integer delFlag) {
        this.delFlag = delFlag;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}