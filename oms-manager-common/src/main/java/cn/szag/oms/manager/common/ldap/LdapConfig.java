package cn.szag.oms.manager.common.ldap;

/**
 * LDAP服务器的属性
 * 
 * @author zhuxiaohai
 *
 */
public class LdapConfig {
	private String url;
	private String user;
	private String pwd;
	private String base;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getBase() {
		return base;
	}

	public void setBase(String base) {
		this.base = base;
	}
}
