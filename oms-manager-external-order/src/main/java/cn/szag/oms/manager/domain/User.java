package cn.szag.oms.manager.domain;

import java.io.Serializable;

public class User implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String id;

	// 更改字段 username 改为 account
    private String account;

    // 更改字段 name 改为 username
    private String username;

    private String password;

    private Integer status;

    private String source;

    private String description;

    private Integer dataPermission;

    private Integer accountType;

    // 新增字段 用户登录ip
    private String ip;

    // 新增字段 客户类别
    private String customer_type;

    // 新增字段 客户 id
    private String customer_id;

    // 新增字段 公司 id
    private  String company_id;

    // 新增字段 时区
    private String timezone;

    // 新增字段 语言
    private  String language;

    // 新增字段 极光推送 id
    private String jpush_id;

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getCustomer_type() {
        return customer_type;
    }

    public void setCustomer_type(String customer_type) {
        this.customer_type = customer_type;
    }

    public String getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(String customer_id) {
        this.customer_id = customer_id;
    }

    public String getCompany_id() {
        return company_id;
    }

    public void setCompany_id(String company_id) {
        this.company_id = company_id;
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

    public String getJpush_id() {
        return jpush_id;
    }

    public void setJpush_id(String jpush_id) {
        this.jpush_id = jpush_id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
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