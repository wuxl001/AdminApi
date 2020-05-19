package cn.szag.oms.manager.common.domain.manager;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
/**
 * 客户账户管理
* @ClassName: Customer 
* @Description: TODO
* @author dengyanghao
* @date 2019年9月26日 上午9:27:22
 */
public class Customer {
    private String id;

    private String code;// 客户代码

    private String mnemonic; // 助记码

    private String name;// 中文名称

    private String ename;// 中文名称

    private String shortname;// 中文名称

    private String type;// 客户类型

    private Integer custtype;// 客户类型（1：客户/2：船公司/3：船代）

    private String contact; // 联系人

    private String address;//地址

    private String eaddress;//英文地址

    private String manageplace; // 管理地址

    private String telephone;// 联系电话

    private String fax;// 传真

    private String email;// 邮箱

    private Integer accountType;// 账户类型（正式客户/代理商）
    @JsonIgnore
    private String status;//状态 0=禁用/1=启用
    @JsonIgnore
    private String secondLevel;//二级标识
    @JsonIgnore
    private Integer delFlag;//0=正常，1=删除

    private String salesman;// 所属业务
    
    private String keyword;//关键字

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

    public String getMnemonic() {
        return mnemonic;
    }

    public void setMnemonic(String mnemonic) {
        this.mnemonic = mnemonic == null ? null : mnemonic.trim();
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

    public String getShortname() {
        return shortname;
    }

    public void setShortname(String shortname) {
        this.shortname = shortname == null ? null : shortname.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public Integer getCusttype() {
        return custtype;
    }

    public void setCusttype(Integer custtype) {
        this.custtype = custtype;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact == null ? null : contact.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getEaddress() {
        return eaddress;
    }

    public void setEaddress(String eaddress) {
        this.eaddress = eaddress == null ? null : eaddress.trim();
    }

    public String getManageplace() {
        return manageplace;
    }

    public void setManageplace(String manageplace) {
        this.manageplace = manageplace == null ? null : manageplace.trim();
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone == null ? null : telephone.trim();
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax == null ? null : fax.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public Integer getAccountType() {
        return accountType;
    }

    public void setAccountType(Integer accountType) {
        this.accountType = accountType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getSecondLevel() {
        return secondLevel;
    }

    public void setSecondLevel(String secondLevel) {
        this.secondLevel = secondLevel == null ? null : secondLevel.trim();
    }

    public Integer getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(Integer delFlag) {
        this.delFlag = delFlag;
    }

    public String getSalesman() {
        return salesman;
    }

    public void setSalesman(String salesman) {
        this.salesman = salesman == null ? null : salesman.trim();
    }
}