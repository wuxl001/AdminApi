package cn.szag.oms.manager.domain;

import java.io.Serializable;

/**
 * 用于接收用户管理的类（同步字段。username和account，name和username的改变）
 */
public class UserManage implements Serializable{

    private static final long serialVersionUID = 1L;

    private String id;


    private String account;


    private String username;

    private String password;

    private Integer status;

    private String source;

    private String description;

    private Integer dataPermission;

    private Integer accountType;


    // 新增字段 客户类别
    private String customerType;

    // 新增字段 客户 id
    private String customerId;

    // 新增字段 公司 id
    private  String companyId;

    // 新增字段 时区
    private String timezone;

    // 新增字段 语言
    private  String language;

    // 新增字段 极光推送 id
    private String jpushId;

    private String areaCode;  //手机区号

    private String receivemailaccounten;  // 邮箱区号

    private String tel;

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    private String email;

    private String post;



    public String getJpushId() {
        return jpushId;
    }

    public void setJpushId(String jpushId) {
        this.jpushId = jpushId;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public String getReceivemailaccounten() {
        return receivemailaccounten;
    }

    public void setReceivemailaccounten(String receivemailaccounten) {
        this.receivemailaccounten = receivemailaccounten;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }


    public String getCustomerType() {
        return customerType;
    }

    public void setCustomerType(String customerType) {
        this.customerType = customerType;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }




    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source == null ? null : source.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public Integer getDataPermission() {
        return dataPermission;
    }

    public void setDataPermission(Integer dataPermission) {
        this.dataPermission = dataPermission;
    }

    public Integer getAccountType() {
        return accountType;
    }

    public void setAccountType(Integer accountType) {
        this.accountType = accountType;
    }
}