package cn.szag.oms.manager.controller;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;

import cn.szag.oms.manager.common.ajax.AjaxRes;
import cn.szag.oms.manager.common.domain.manager.Customer;
import cn.szag.oms.manager.common.domain.manager.OrderShippingInfo;
import cn.szag.oms.manager.common.domain.manager.User;
import cn.szag.oms.manager.common.page.Page;
import cn.szag.oms.manager.common.utils.Const;
import cn.szag.oms.manager.common.utils.RedisUtil;
import cn.szag.oms.manager.common.utils.UserException;
import cn.szag.oms.manager.common.utils.UuidUtil;
import cn.szag.oms.manager.common.utils.Verification;
import cn.szag.oms.manager.service.CustomerService;
import cn.szag.oms.manager.service.ModuleManagerService;
/**
 * 客户账户管理
* @ClassName: CustomerController 
* @Description: TODO
* @author dengyanghao
* @date 2019年9月25日 下午7:16:05
 */
@Controller
@RequestMapping(value ="/customer")
public class CustomerController {
	@Autowired
	private CustomerService customerService;
	@Autowired
    private ModuleManagerService  moduleManagerService;
	@Autowired
	private RedisUtil redisUtil;
	@RequestMapping(value = "/add")
	@ResponseBody
	public AjaxRes add(HttpServletRequest request, Customer customer){
		AjaxRes ar = new AjaxRes();
		String token = null;
		User user = null;
		try {
			String json = request.getParameter("json");
			if("".equals(json) || json == null){
				token = request.getParameter("token");
				user = Verification.getUser(token,redisUtil);
				customer.setId(UuidUtil.get32UUID());
				customer.setSalesman(user.getUsername());
			}else{
				customer = JSON.parseObject(json, Customer.class);
			}
			Customer c = new Customer();
			c.setCode(customer.getCode());
			c.setName(customer.getName());
			int i = customerService.selectList(c);
			if(i>0){
				ar.setFailMsg("新增失败客户名称或客户编码已经存在");
				return ar;
			}
			customerService.insertSelective(customer);
			ar.setSucceedMsg("客户信息新增成功");
		} catch (NumberFormatException e) {
			ar.setFailMsg("参数错误！");
		} catch (UserException e) {
			ar.setFailMsg("令牌为空！");
			ar.setRes(-1);
			e.printStackTrace();
		} catch (Exception e) {
			ar.setFailMsg("客户信息新增失败，出现异常");
			e.printStackTrace();
		}
		return ar;
	}
	@RequestMapping(value = "/edit")
	@ResponseBody
	public AjaxRes edit(HttpServletRequest request, Customer customer){
		AjaxRes ar = new AjaxRes();
		String token = null;
		User user = null;
		try {
			String json = request.getParameter("json");
			if("".equals(json) || json == null){
				token = request.getParameter("token");
				user = Verification.getUser(token,redisUtil);
				customer.setSalesman(user.getUsername());
			}else{
				customer = JSON.parseObject(json, Customer.class);
			}
			Customer c = customerService.selectByPrimaryKey(customer.getId());
			int i = customerService.selectList(customer);
			if(i>0){
				ar.setFailMsg("修改失败客户名称或客户编码已经存在");
				return ar;
			}
			if(null == c){
				customerService.insertSelective(customer);
			}else{
				customerService.updateByPrimaryKey(customer);
			}
			ar.setSucceedMsg("客户信息修改成功");
		} catch (NumberFormatException e) {
			ar.setFailMsg("参数错误！");
		} catch (UserException e) {
			ar.setFailMsg("令牌为空！");
			ar.setRes(-1);
			e.printStackTrace();
		} catch (Exception e) {
			ar.setFailMsg("客户信息修改失败，出现异常");
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
			Customer customer = customerService.selectByPrimaryKey(id);
			ar.setSucceed(customer,"客户信息获取成功");
		} catch (NumberFormatException e) {
			ar.setFailMsg("参数错误！");
		} catch (UserException e) {
			ar.setFailMsg("令牌为空！");
			ar.setRes(-1);
			e.printStackTrace();
		} catch (Exception e) {
			ar.setFailMsg("客户信息获取失败，出现异常");
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
			Customer customer = new Customer();
			customer.setId(id);
			customer.setDelFlag(1);
			customerService.updateByPrimaryKeySelective(customer);
			ar.setSucceedMsg("客户信息删除成功");
		} catch (NumberFormatException e) {
			ar.setFailMsg("参数错误！");
		} catch (UserException e) {
			ar.setFailMsg("令牌为空！");
			ar.setRes(-1);
			e.printStackTrace();
		} catch (Exception e) {
			ar.setFailMsg("客户信息删除失败，出现异常");
			e.printStackTrace();
		}
		return ar;
	}
	@RequestMapping(value = "/list")
	@ResponseBody
	public AjaxRes list(HttpServletRequest request, Customer customer,Page page){
		AjaxRes ar = new AjaxRes();
		try {
			String token = request.getParameter("token");
			String parentId = request.getParameter("parentId");
			User user = Verification.getUser(token,redisUtil);
			Map<String,Object> map = new HashMap<String, Object>();
			List<Customer> customerList = customerService.findByPage(customer, page);
			page.setResults(customerList);
			map.put("list", page);
			map.put("permitBtn", moduleManagerService.findBtn(user.getId(), Const.RESOURCES_TYPE_BUTTON, parentId));
			ar.setSucceed(map,"客户信息列表获取成功");
		} catch (NumberFormatException e) {
			ar.setFailMsg("参数错误！");
		} catch (UserException e) {
			ar.setFailMsg("令牌为空！");
			ar.setRes(-1);
			e.printStackTrace();
		} catch (Exception e) {
			ar.setFailMsg("客户信息列表获取失败，出现异常");
			e.printStackTrace();
		}
		return ar;
	}
}
