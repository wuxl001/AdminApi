package cn.szag.oms.manager.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.google.common.base.Joiner;

import cn.szag.oms.manager.common.ajax.AjaxRes;
import cn.szag.oms.manager.common.domain.manager.Customer;
import cn.szag.oms.manager.common.domain.manager.CustomerAccount;
import cn.szag.oms.manager.common.domain.manager.User;
import cn.szag.oms.manager.common.page.Page;
import cn.szag.oms.manager.common.utils.Const;
import cn.szag.oms.manager.common.utils.EncryptUtil;
import cn.szag.oms.manager.common.utils.HttpRequestUtil;
import cn.szag.oms.manager.common.utils.RedisUtil;
import cn.szag.oms.manager.common.utils.Transaction;
import cn.szag.oms.manager.common.utils.Url;
import cn.szag.oms.manager.common.utils.UserException;
import cn.szag.oms.manager.common.utils.UuidUtil;
import cn.szag.oms.manager.common.utils.Verification;
import cn.szag.oms.manager.service.CusUserService;
import cn.szag.oms.manager.service.CustomerService;
import cn.szag.oms.manager.service.ModuleCustomerService;
import cn.szag.oms.manager.service.ModuleManagerService;
import cn.szag.oms.manager.service.ModulePermissionCustomerService;
/**
 * 客户用户账户管理
* @ClassName: CusUserController 
* @Description: TODO
* @author dengyanghao
* @date 2019年9月26日 下午6:16:05
 */
@Controller
@RequestMapping(value ="/cusUser")
public class CusUserController {
	@Autowired
	private CusUserService cusUserService;
	@Autowired
	private CustomerService customerService;
	@Autowired
	private PlatformTransactionManager transactionManager;
	@Autowired
    private ModuleManagerService  moduleManagerService;
	@Autowired
	private ModuleCustomerService moduleService;
	@Autowired
	private ModulePermissionCustomerService modulePermissionCustomerService;
	@Autowired
	private RedisUtil redisUtil;
	public static String omsUrl=Url.omsUrl;
	@RequestMapping(value = "/add",produces="application/json;charset=UTF-8")
	@ResponseBody
	public AjaxRes add(HttpServletRequest request, CustomerAccount customerAccount){
		AjaxRes ar = new AjaxRes();
		String token = null;
		User user = null;
		try {
			String json = request.getParameter("json");
			if("".equals(json) || json == null){
				token = request.getParameter("token");
				user = Verification.getUser(token,redisUtil);
				customerAccount.setId(UuidUtil.get32UUID());
			}else{
				customerAccount = JSON.parseObject(json, CustomerAccount.class);
			}
			CustomerAccount c = new CustomerAccount();
			c.setAccount(customerAccount.getAccount());
			c.setEmail(customerAccount.getEmail());
			c.setTel(customerAccount.getTel());
			int i = cusUserService.selectList(c);
			if(i>0){//判断是否存在当前（手机号码/邮箱/用户名）用户信息
				ar.setFailMsg("客户用户信息新增失败，用户名或手机号码或邮箱已存在");
				return ar;
			}
			Customer customer= customerService.selectByPrimaryKey(customerAccount.getCustomerId());//根据客户公司Id查找客户公司信息
			if(null != customer){
				customerAccount.setCompanyZh(customer.getName());//客户公司中文名称
				customerAccount.setCompanyEn(customer.getEname());//客户公司英文名称
			}
			cusUserService.insertSelective(customerAccount);
			customerAccount.setAccountType(null);
			List<String> aus = new ArrayList<>();
			aus.add("002");//自助下单权限
			aus.add("006");//自助下单权限
			aus.add("024");//自助下单权限
			if("0".equals(customerAccount.getCustomerType())){//客户
	        	aus.add("008");//订单追踪权限
	        	aus.add("009");//订单追踪权限
			}
			List<String> list = moduleService.initMenu(aus);
			if(!list.isEmpty()){
				json = Joiner.on(',').join(list);
				customerAccount.setAus(json);
			}
			modulePermissionCustomerService.updateModuleAndUser(customerAccount.getId(),customerAccount.getAus());
			json = JSON.toJSONString(customerAccount);
			System.out.println("json = " + json);
			String str = HttpRequestUtil.sendPost(omsUrl+"/user/add", "json="+json, false);
			ar = JSON.parseObject(str, AjaxRes.class);
			if(ar.getRes()==0){
				Transaction.rollback(transactionManager);
				ar.setFailMsg("新增客户联系人,发送客户中心失败");
				return ar;
			}
			Transaction.commit(transactionManager);
			ar.setSucceedMsg("客户用户信息新增成功");
		} catch (NumberFormatException e) {
			ar.setFailMsg("参数错误！");
		} catch (UserException e) {
			ar.setFailMsg("令牌为空！");
			ar.setRes(-1);
			e.printStackTrace();
		} catch (Exception e) {
			Transaction.rollback(transactionManager);
			ar.setFailMsg("客户用户信息新增失败，出现异常");
			e.printStackTrace();
		}
		return ar;
	}
	@RequestMapping(value = "/edit")
	@ResponseBody
	public AjaxRes edit(HttpServletRequest request, CustomerAccount customerAccount){
		AjaxRes ar = new AjaxRes();
		String token = null;
		User user = null;
		try {
			String json = request.getParameter("json");
			if("".equals(json) || json == null){
				token = request.getParameter("token");
				user = Verification.getUser(token,redisUtil);
				customerAccount.setIsEncrypt("1");
			}else{
				customerAccount = JSON.parseObject(json, CustomerAccount.class);
			}
			int i = cusUserService.selectList(customerAccount);
			if(i>0){
				ar.setFailMsg("客户用户信息修改失败，用户名或手机号码或邮箱已存在");
				return ar;
			}
			Customer customer= customerService.selectByPrimaryKey(customerAccount.getCustomerId());//根据客户公司Id查找客户公司信息
			if(null != customer){
				customerAccount.setCompanyZh(customer.getName());//客户公司中文名称
				customerAccount.setCompanyEn(customer.getEname());//客户公司英文名称
			}
			CustomerAccount c = cusUserService.selectByPrimaryKey(customerAccount.getId());
			if(null ==c){
				cusUserService.insertSelective(customerAccount);
			}else{
				cusUserService.updateByPrimaryKeySelective(customerAccount);
			}
			customerAccount.setAccountType(null);
			List<String> aus = new ArrayList<>();
			aus.add("002");//自助下单权限
			aus.add("006");//自助下单权限
			aus.add("024");//自助下单权限
			if("0".equals(customerAccount.getCustomerType())){//客户
	        	aus.add("008");//订单追踪权限
	        	aus.add("009");//订单追踪权限
			}
			List<String> list = moduleService.initMenu(aus);
			if(!list.isEmpty()){
				json = Joiner.on(',').join(list);
				customerAccount.setAus(json);
			}
			json = JSON.toJSONString(customerAccount);
			String str = HttpRequestUtil.sendPost(omsUrl+"/user/updateUser", "json="+json, false);
			System.out.println("str = "+str);
			ar = JSON.parseObject(str, AjaxRes.class);
			if(ar.getRes()==0){
				Transaction.rollback(transactionManager);
				ar.setFailMsg("修改客户联系人,发送客户中心失败");
				return ar;
			}
			Transaction.commit(transactionManager);
			ar.setSucceedMsg("客户用户信息修改成功");
		} catch (NumberFormatException e) {
			ar.setFailMsg("参数错误！");
		} catch (UserException e) {
			ar.setFailMsg("令牌为空！");
			ar.setRes(-1);
			e.printStackTrace();
		} catch (Exception e) {
			Transaction.rollback(transactionManager);
			ar.setFailMsg("客户用户信息修改失败，出现异常");
			e.printStackTrace();
		}
		return ar;
	}
	@RequestMapping(value = "/edit2")
	@ResponseBody
	public AjaxRes edit2(HttpServletRequest request, CustomerAccount customerAccount){
		AjaxRes ar = new AjaxRes();
		String token = null;
		User user = null;
		try {
			String json = request.getParameter("json");
			if("".equals(json) || json == null){
				token = request.getParameter("token");
				user = Verification.getUser(token,redisUtil);
			}else{
				customerAccount = JSON.parseObject(json, CustomerAccount.class);
			}
			cusUserService.updateByPrimaryKey(customerAccount);
			ar.setSucceedMsg("客户用户信息修改成功");
		} catch (NumberFormatException e) {
			ar.setFailMsg("参数错误！");
		} catch (UserException e) {
			ar.setFailMsg("令牌为空！");
			ar.setRes(-1);
			e.printStackTrace();
		} catch (Exception e) {
			ar.setFailMsg("客户用户信息修改失败，出现异常");
			e.printStackTrace();
		}
		return ar;
	}
	@RequestMapping(value = "/find")
	@ResponseBody
	public AjaxRes list(HttpServletRequest request, String id){
		AjaxRes ar = new AjaxRes();
		try {
			String token = request.getParameter("token");
			User user = Verification.getUser(token,redisUtil);
			CustomerAccount customerAccount = cusUserService.selectByPrimaryKey(id);
			ar.setSucceed(customerAccount,"客户用户信息获取成功");
		} catch (NumberFormatException e) {
			ar.setFailMsg("参数错误！");
		} catch (UserException e) {
			ar.setFailMsg("令牌为空！");
			ar.setRes(-1);
			e.printStackTrace();
		} catch (Exception e) {
			ar.setFailMsg("客户用户信息获取失败，出现异常");
			e.printStackTrace();
		}
		return ar;
	}
	@RequestMapping(value = "/del")
	@ResponseBody
	public AjaxRes del(HttpServletRequest request, String id){
		AjaxRes ar = new AjaxRes();
		try {
			String token = request.getParameter("token");
			User user = Verification.getUser(token,redisUtil);
			CustomerAccount customerAccount = new CustomerAccount();
			customerAccount.setId(id);
			customerAccount.setStatus(1);;
			cusUserService.updateByPrimaryKeySelective(customerAccount);
			ar.setSucceedMsg("客户用户信息删除成功");
		} catch (NumberFormatException e) {
			ar.setFailMsg("参数错误！");
		} catch (UserException e) {
			ar.setFailMsg("令牌为空！");
			ar.setRes(-1);
			e.printStackTrace();
		} catch (Exception e) {
			ar.setFailMsg("客户用户信息删除失败，出现异常");
			e.printStackTrace();
		}
		return ar;
	}
	@RequestMapping(value = "/list")
	@ResponseBody
	public AjaxRes list(HttpServletRequest request, CustomerAccount customerAccount,Page page){
		AjaxRes ar = new AjaxRes();
		try {
			String token = request.getParameter("token");
			String parentId = request.getParameter("parentId");
			User user = Verification.getUser(token,redisUtil);
			Map<String,Object> map = new HashMap<String, Object>();
			List<CustomerAccount> customerList = cusUserService.select(customerAccount,page);
			page.setResults(customerList);
			map.put("list", page);
			map.put("permitBtn", moduleManagerService.findBtn(user.getId(), Const.RESOURCES_TYPE_BUTTON, parentId));
			ar.setSucceed(map,"客户用户信息列表获取成功");
		} catch (NumberFormatException e) {
			ar.setFailMsg("参数错误！");
		} catch (UserException e) {
			ar.setFailMsg("令牌为空！");
			ar.setRes(-1);
			e.printStackTrace();
		} catch (Exception e) {
			ar.setFailMsg("客户用户信息列表获取失败，出现异常");
			e.printStackTrace();
		}
		return ar;
	}
}
