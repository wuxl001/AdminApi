package cn.szag.oms.manager.controller;

import cn.szag.oms.manager.common.ajax.AjaxRes;
import cn.szag.oms.manager.common.domain.manager.LoginLog;
import cn.szag.oms.manager.common.domain.manager.Role;
import cn.szag.oms.manager.common.domain.manager.User;
import cn.szag.oms.manager.common.ldap.LdapConfig;
import cn.szag.oms.manager.common.utils.Const;
import cn.szag.oms.manager.common.utils.EncryptUtil;
import cn.szag.oms.manager.common.utils.RedisUtil;
import cn.szag.oms.manager.common.utils.UserException;
import cn.szag.oms.manager.common.utils.UuidUtil;
import cn.szag.oms.manager.common.utils.Verification;
import cn.szag.oms.manager.service.RoleService;
import cn.szag.oms.manager.service.UserControlService;
import cn.szag.oms.manager.service.UserLoginService;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.apache.oltu.oauth2.as.issuer.MD5Generator;
import org.apache.oltu.oauth2.as.issuer.OAuthIssuer;
import org.apache.oltu.oauth2.as.issuer.OAuthIssuerImpl;
import org.apache.oltu.oauth2.common.exception.OAuthSystemException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.naming.AuthenticationException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;
import javax.naming.ldap.InitialLdapContext;
import javax.servlet.http.HttpServletRequest;

import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;

@RestController
@RequestMapping("/user")
public class UserLoginController {
    // 存储token的时间
    private static final int reminis = 30*60*1000;


    @Autowired
    private UserControlService userControlService;
    @Autowired
    private UserLoginService userLoginService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private LdapConfig ldapConfig;

    @Autowired
    private RedisUtil redisUtil;


    /**
     * 用户登陆功能
     * @param
     * @return
     */
    @RequestMapping(value = "/login")
    public AjaxRes login(User user ){
        //获得所有的参数
        String account = user.getAccount();
        String password = user.getPassword();
        // 通过账号和MD5加密的密码来查询用户是否存在
        String md5Password = EncryptUtil.md5Password(password);
        try {
            List<User> users = userLoginService.login(account, md5Password);
            User user1 = null;
            if (users.size() == 0) {
                int i = loadingVerify(user.getAccount(), user.getPassword());
                if (i == 0){
                    user1 = userLoginService.findByAccount(user.getAccount(),null);
                    if (user1 ==null){
                        return new AjaxRes(Const.FAIL,"没有用户的信息");
                    }
                }else {
                    return new AjaxRes(Const.FAIL,"域账号验证失败");
                }
            }else {
                user1 = users.get(0);
                user1.setPassword(null);
            }


            // 生成Access Token
            OAuthIssuer oauthIssuerImpl = new OAuthIssuerImpl(new MD5Generator());
            String token = oauthIssuerImpl.accessToken();
            // 生成OAuth响应
            redisUtil.set(token, JSONObject.toJSONString(user1).toString(),reminis);

            LoginLog loginLog = new LoginLog();
            loginLog.setId(UuidUtil.get32UUID());
            loginLog.setType("登陆");
            loginLog.setIp(user.getIp());
            loginLog.setOperateTime(new Date());
            loginLog.setUserId(user1.getId());
            userLoginService.insertLoginLog(loginLog);
            List<Role> roleList = roleService.selectRoleList(user1.getId());
            String roleName = "";
            for (Role role : roleList) {
				if(roleList.size()>1){
					roleName = role.getName()+"|";
				}else{
					roleName = role.getName();
				}
			}
            user1.setRoleName(roleName);
            user1.setToken(token);
            return new AjaxRes(Const.SUCCEED, "成功", user1);
        } catch (OAuthSystemException e) {
            e.printStackTrace();
            return new AjaxRes(Const.FAIL, "验证未通过");
        } catch (NullPointerException e) {
            return new AjaxRes(Const.FAIL, "用户登录账号和密码有误");
        }catch (Exception e) {
            e.printStackTrace();
            return new AjaxRes(Const.FAIL, "连接超时");
        }
    }
    /**
     * 用户登陆功能
     * @param
     * @return
     */
    @RequestMapping(value = "/appLogin")
    public AjaxRes appLogin(User user ){
        //获得所有的参数
        String account = user.getAccount();
        String password = user.getPassword();
        // 通过账号和MD5加密的密码来查询用户是否存在
        String md5Password = EncryptUtil.md5Password(password);
        try {
            List<User> users = userLoginService.login(account, md5Password);
            User user1 = null;
            if (users.size() == 0) {
                int i = loadingVerify(user.getAccount(), user.getPassword());
                if (i == 0){
                    user1 = userLoginService.findByAccount(user.getAccount(),null);
                    if (user1 ==null){
                        return new AjaxRes(Const.FAIL,"没有用户的信息");
                    }
                }else {
                    return new AjaxRes(Const.FAIL,"域账号验证失败");
                }
            }else {
                user1 = users.get(0);
                user1.setPassword(null);
            }


            // 生成Access Token
            OAuthIssuer oauthIssuerImpl = new OAuthIssuerImpl(new MD5Generator());
            String token = oauthIssuerImpl.accessToken();
            // 生成OAuth响应
            redisUtil.set(token, JSONObject.toJSONString(user1).toString(),reminis);

            LoginLog loginLog = new LoginLog();
            loginLog.setId(UuidUtil.get32UUID());
            loginLog.setType("登陆");
            loginLog.setIp(user.getIp());
            loginLog.setOperateTime(new Date());
            loginLog.setUserId(user1.getId());
            userLoginService.insertLoginLog(loginLog);
            List<Role> roleList = roleService.selectRoleList(user1.getId());
            String roleName = "";
            for (Role role : roleList) {
				if(roleList.size()>1){
					roleName = role.getName()+"|";
				}else{
					roleName = role.getName();
				}
			}
            user1.setRoleName(roleName);
            user1.setToken(token);
            return new AjaxRes(Const.SUCCEED, "成功", user1);
        } catch (OAuthSystemException e) {
            e.printStackTrace();
            return new AjaxRes(Const.FAIL, "验证未通过");
        } catch (NullPointerException e) {
            return new AjaxRes(Const.FAIL, "用户登录账号和密码有误");
        }catch (Exception e) {
            e.printStackTrace();
            return new AjaxRes(Const.FAIL, "连接超时");
        }
    }
    /**
     * 菜单查询功能  在 base_module 菜单模块表根据条件查询记录，默认查询所有 del_flag 为 0 的记录
     * @param request
     * @param
     * @return
     */
    @RequestMapping(value = "/logout")
    public AjaxRes logout(HttpServletRequest request){
    	AjaxRes ar =new AjaxRes();
        try {
            // 获得token信息
            String token = request.getParameter("token");
            User user = Verification.getUser(token,redisUtil);
            userControlService.updateRegistrationId("", user.getId());
            redisUtil.del(token);
            ar.setSucceedMsg("注销成功");
        } catch (NumberFormatException e) {
        	ar.setFailMsg("参数错误！！");
        } catch (UserException e) {
            e.printStackTrace();
            ar.setFailMsg("令牌为空！");
        } catch (Exception e) {
            e.printStackTrace();
            ar.setFailMsg("注销失败，数据出现异常");
        }
        return ar;
    }
    /**
     * 登陆日志
     * @param loginLog
     * @return
     */
    @RequestMapping(value = "/addLog",method = RequestMethod.POST)
    public AjaxRes addLog(LoginLog loginLog){
        loginLog.setId(UuidUtil.get32UUID());
        loginLog.setOperateTime(new Date());
        try {
            if (loginLog != null){
                userLoginService.insertLoginLog(loginLog);
                return new AjaxRes(Const.SUCCEED,"日志保存成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new AjaxRes(Const.FAIL,"日志保存异常");
        }
        return new AjaxRes(Const.FAIL,"登陆日志为空");
    }


    // 域登陆验证
    private int loadingVerify(String account , String pwd){
    	String dn = findByAccount(account);
    	Hashtable<Object, Object> hashtable = new Hashtable<Object, Object>();
    	if(dn != null){
            hashtable.put(Context.INITIAL_CONTEXT_FACTORY,"com.sun.jndi.ldap.LdapCtxFactory");
            hashtable.put(Context.PROVIDER_URL,"ldap.url = ldap://fanyaadsrv01.szfanya.com:389");
            hashtable.put(Context.SECURITY_AUTHENTICATION,"simple");
            hashtable.put(Context.SECURITY_PRINCIPAL,dn);
            hashtable.put(Context.SECURITY_CREDENTIALS,pwd);
	        try {
	            DirContext ctx  = new InitialDirContext(hashtable);
	        } catch (NamingException e) {
	            e.printStackTrace();
	            return -1;
	        }catch (Exception e){
	            e.printStackTrace();
	            return -1 ;
	        }
    	}
        return 0;

    }
    /**
     * 从ldap里获取一个连接
    * @Title: getDirContext 
    * @Description: TODO 
    * @param @param url
    * @param @param userdn
    * @param @param password
    * @param @return
    * @author dengyanghao
    * @return DirContext
    * @throws
     */
    DirContext getDirContext(String url, String userdn, String password) {
        Properties mEnv = new Properties();
        mEnv.put(Context.INITIAL_CONTEXT_FACTORY,
                "com.sun.jndi.ldap.LdapCtxFactory");
        mEnv.put(Context.PROVIDER_URL, url);
        mEnv.put(Context.SECURITY_AUTHENTICATION, "simple");
        mEnv.put(Context.SECURITY_PRINCIPAL, userdn);
        mEnv.put(Context.SECURITY_CREDENTIALS, password);
        DirContext ctx = null;
        try {
            ctx = new InitialLdapContext(mEnv, null);
        } catch (NamingException ex) {
            ex.printStackTrace();
            close(ctx);
        }
        return ctx;
    }
    String findByAccount(String account){
        DirContext dc = getDirContext(ldapConfig.getUrl(), ldapConfig.getUser(), ldapConfig.getPwd());
        String base = "OU=泛亚物流,DC=szfanya,DC=com";
        String scope = "";

        String filter = "(objectclass=user)";
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("sAMAccountName", "*" + account + "*");

        String qry = null;
        int count = map.size();
        StringBuilder sb = new StringBuilder();
        sb.append("(&");
        Iterator<Map.Entry<String, String>> iter = map.entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry<String, String> entry = (Map.Entry<String, String>) iter
                    .next();
            sb.append("(");
            sb.append(entry.getKey());
            sb.append("=");
            sb.append(entry.getValue());
            sb.append(")");
        }
        sb.append(")");
        qry = sb.toString();

        if (null!=qry){
            filter = qry;
        }

        SearchControls sc = new SearchControls();
        if (scope.equals("base")) {
            sc.setSearchScope(SearchControls.OBJECT_SCOPE);
        } else if (scope.equals("one")) {
            sc.setSearchScope(SearchControls.ONELEVEL_SCOPE);
        } else {
            sc.setSearchScope(SearchControls.SUBTREE_SCOPE);
        }

        NamingEnumeration<SearchResult> ne = null;
        try {
            ne = dc.search(base, filter, sc);
            SearchResult sr;
            HashMap<String, Object> resultRowMap;
            ArrayList<Object> valList = new ArrayList<Object>();
            while (ne.hasMoreElements()) {
                sr = (SearchResult) ne.next();
                resultRowMap = builtSearchResult(sr);
                if (resultRowMap != null) {
                    valList = (ArrayList<Object>) resultRowMap.get("distinguishedName");
                    if (null !=valList && valList.size() > 0) {
                        return valList.get(0).toString();
                    }
                }
            }
        } catch (Exception nex) {
            System.err.println("Error: " + nex.getMessage());
            nex.printStackTrace();
            return null;
        } finally {
            close(dc);
        }

        return null;
    }
    void close(DirContext ctx) {
        if (ctx != null) {
            try {
                ctx.close();
            } catch (NamingException e) {
                System.out.println("NamingException in close():" + e);
            }
        }
    }
    HashMap<String, Object> builtSearchResult(SearchResult sr) throws NamingException{
        HashMap<String, Object> resultRowMap = new HashMap<String, Object>();// 一行数据
        Attributes attrs = sr.getAttributes();
        if (attrs != null) {
            for (NamingEnumeration<? extends Attribute> ae = attrs
                    .getAll(); ae.hasMoreElements();) {
                Attribute attr = (Attribute) ae.next();// 获取一个属性
                String attrId = attr.getID();
                Enumeration<?> vals = attr.getAll();
                if (vals != null) {
                    ArrayList<Object> valList = new ArrayList();
                    while (vals.hasMoreElements()) {
                        Object obj = vals.nextElement();
                        if (obj instanceof String) {
                            String _value = (String) obj;
                            valList.add(_value);
                        } else {
                            valList.add(obj);
                        }
                    }
                    resultRowMap.put(attrId, valList);
                }
            }
        }

        return resultRowMap;
    }
}
