package cn.szag.oms.manager.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.iterators.ArrayListIterator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.google.common.base.Joiner;

import cn.szag.oms.manager.common.ajax.AjaxRes;
import cn.szag.oms.manager.common.domain.manager.ModuleCustomer;
import cn.szag.oms.manager.common.domain.manager.User;
import cn.szag.oms.manager.common.utils.HttpRequestUtil;
import cn.szag.oms.manager.common.utils.RedisUtil;
import cn.szag.oms.manager.common.utils.Transaction;
import cn.szag.oms.manager.common.utils.Url;
import cn.szag.oms.manager.common.utils.Verification;
import cn.szag.oms.manager.service.ModuleCustomerService;
import cn.szag.oms.manager.service.ModulePermissionCustomerService;



@RestController
@RequestMapping("/moduleCustomer")
public class ModuleCustomerController {

	@Autowired
	private ModuleCustomerService moduleService;
	
	@Autowired
	private ModulePermissionCustomerService modulePermissionCustomerService;
	
	@Autowired
    private RedisUtil redisUtil;
	@Autowired
	private PlatformTransactionManager transactionManager;
	
	private final String external_url = Url.omsUrl;
	
	
	/**
	 * 用户修改菜单权限
	 * @title: edit
	 * @author: tansongke
	 * @description: TODO
	 * @date: 2019年10月10日 下午4:31:32
	 * @param request
	 * @param aus 菜单id
	 * @return
	 * @return: AjaxRes
	 */
	@RequestMapping(value = "edit", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes edit(HttpServletRequest request,String aus,String roleid) {
		AjaxRes ar = new AjaxRes();
			try {
				String token =request.getParameter("token");
	        	User user = Verification.getUser(token,redisUtil);
				if(modulePermissionCustomerService.updateModuleAndUser(roleid,aus)>0){
					ar.setSucceedMsg("修改菜单权限成功！");
					HttpRequestUtil.sendPost(external_url + "/module/update", "userId=" + roleid +"&aus="+aus, false);
				}else{
					ar.setFailMsg("修改菜单权限失败！");
				}
				Transaction.commit(transactionManager);
			} catch (Exception e) {
				Transaction.rollback(transactionManager);
				// TODO: handle exception
				e.printStackTrace();
				ar.setFailMsg("修改菜单权限失败！");
			}
		return ar;
	}
	
	
	/**
	 * 用户修改菜单权限
	 * @title: edit
	 * @author: tansongke
	 * @description: TODO
	 * @date: 2019年10月10日 下午4:31:32
	 * @param request
	 * @param aus 菜单id
	 * @return
	 * @return: AjaxRes
	 */
	@RequestMapping(value = "edit2", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes edit2(HttpServletRequest request,String aus,String userId) {
		AjaxRes ar = new AjaxRes();
			try {
				String token =request.getParameter("token");
	        	User user = Verification.getUser(token,redisUtil);
				if(modulePermissionCustomerService.updateModuleAndUser(userId,aus)>0){
					ar.setSucceedMsg("修改菜单权限成功！");
					
				}else{
					ar.setFailMsg("修改菜单权限失败！");
				}
				Transaction.commit(transactionManager);
			} catch (Exception e) {
				Transaction.rollback(transactionManager);
				// TODO: handle exception
				e.printStackTrace();
				ar.setFailMsg("修改菜单权限失败！");
			}
		return ar;
	}
	
	/**
	 * 获取菜单列表
	 * @title: listAllParentMenu
	 * @author: tansongke
	 * @description: TODO
	 * @date: 2019年10月10日 下午5:02:22
	 * @param request
	 * @return
	 * @return: AjaxRes
	 */
	@RequestMapping(value = "list", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes listAllParentMenu(HttpServletRequest request,String roleid) {
		AjaxRes ar = new AjaxRes();
		try {
			String token =request.getParameter("token");
        	User user = Verification.getUser(token,redisUtil);
        	ar.setSucceed(moduleService.findModule(roleid), "获取菜单列表成功！");
        	Transaction.commit(transactionManager);
		} catch (Exception e) {
			Transaction.rollback(transactionManager);
			e.printStackTrace();
			ar.setFailMsg("获取菜单列表失败！");
			// TODO: handle exception
		}
		return ar;
	}
	/**
	 * 获取菜单列表
	 * @title: listAllParentMenu
	 * @author: tansongke
	 * @description: TODO
	 * @date: 2019年10月10日 下午5:02:22
	 * @param request
	 * @return
	 * @return: AjaxRes
	 */
	@RequestMapping(value = "list3", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes listAllParentMenu2(HttpServletRequest request,String roleid) {
		AjaxRes ar = new AjaxRes();
		try {
			String token =request.getParameter("token");
        	User user = Verification.getUser(token,redisUtil);
        	List<String> aus = new ArrayList<>();
        	aus.add("005");
        	aus.add("007");
        	List<String> list = moduleService.initMenu(aus);
        	String json = Joiner.on(',').join(list);
        	ar.setSucceed(json, "获取菜单列表成功！");
		} catch (Exception e) {
			e.printStackTrace();
			ar.setFailMsg("获取菜单列表失败！");
			// TODO: handle exception
		}
		return ar;
	}
	
	/**
	 * 获取菜单列表
	 * @title: listAllParentMenu
	 * @author: tansongke
	 * @description: TODO
	 * @date: 2019年10月10日 下午5:02:22
	 * @param request
	 * @return
	 * @return: AjaxRes
	 */
	@RequestMapping(value = "list2", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes listAuthorizedUser(HttpServletRequest request,String roleid, ModuleCustomer module) {
		AjaxRes ar = new AjaxRes();
		try {
			String token =request.getParameter("token");
        	User user = Verification.getUser(token,redisUtil);
        	List<ModuleCustomer> list = moduleService.listAuthorizedUser(roleid, module);
        	List<String> moduleId = new ArrayList<String>();
        	for(ModuleCustomer moduleCustomer :list){
        	   moduleId.add(moduleCustomer.getId());
        	}
            System.out.println(moduleId.toString());
        	ar.setSucceed(moduleId, "获取菜单列表成功！");	
		} catch (Exception e) {
			e.printStackTrace();
			ar.setFailMsg("获取菜单列表失败！");
			// TODO: handle exception
		}
		return ar;
	}
	
}
