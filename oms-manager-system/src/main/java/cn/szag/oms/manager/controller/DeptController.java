package cn.szag.oms.manager.controller;


import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;

import cn.szag.oms.manager.common.ajax.AjaxRes;
import cn.szag.oms.manager.common.domain.manager.Department;
import cn.szag.oms.manager.common.domain.manager.User;
import cn.szag.oms.manager.common.page.Page;
import cn.szag.oms.manager.common.utils.Const;
import cn.szag.oms.manager.common.utils.MenuTreeUtil;
import cn.szag.oms.manager.common.utils.RedisUtil;
import cn.szag.oms.manager.common.utils.Transaction;
import cn.szag.oms.manager.common.utils.UserException;
import cn.szag.oms.manager.common.utils.UuidUtil;
import cn.szag.oms.manager.common.utils.Verification;
import cn.szag.oms.manager.service.DepartmentService;
import cn.szag.oms.manager.service.ModuleManagerService;
/**
 * 部门管理
* @ClassName: DeptController 
* @Description: TODO
* @author dengyanghao
* @date 2019年9月25日 下午4:16:05
 */
@Controller
@RequestMapping(value ="/dept")
public class DeptController {
	@Autowired
	private DepartmentService departmentService;
	@Autowired
	private ModuleManagerService moduleManagerService;
	@Autowired
	private RedisUtil redisUtil;
	@Autowired
	private PlatformTransactionManager transactionManager;
	@RequestMapping(value = "/list")
	@ResponseBody
	public AjaxRes list(HttpServletRequest request,Department department, Page page){
		AjaxRes ar = new AjaxRes();
		Map<String,Object> map = new HashMap<String, Object>();
		try {
			String token = request.getParameter("token");
			String parentId = request.getParameter("parentId");
			User user = Verification.getUser(token,redisUtil);
			List<Department> departmentList = departmentService.find(department,page);
			List<Department> tree = (List<Department>)MenuTreeUtil.buildTree4Class(departmentList,"Department");
			page.setTotalRecord(tree.size());
			page.setResults(tree);
			map.put("list", page);
			map.put("permitBtn", moduleManagerService.findBtn(user.getId(), Const.RESOURCES_TYPE_BUTTON, parentId));
			ar.setSucceed(map,"部门列表获取成功");
		} catch (NumberFormatException e) {
			ar.setFailMsg("参数错误！");
		} catch (UserException e) {
			ar.setFailMsg("令牌为空！");
			ar.setRes(-1);
			e.printStackTrace();
		} catch (Exception e) {
			ar.setFailMsg("部门列表获取失败，出现异常");
			e.printStackTrace();
		}
		return ar;
	}
	@RequestMapping(value = "/add")
	@ResponseBody
	public AjaxRes add(HttpServletRequest request, Department department){
		AjaxRes ar = new AjaxRes();
		String token = null;
		User user = null;
		try {
			String json = request.getParameter("json");
			Map<String,Object> map = new HashMap<String, Object>();
			if(json ==null || "".equals(json)){
				token = request.getParameter("token");
				user = Verification.getUser(token,redisUtil);
				department.setId(UuidUtil.get32UUID());
				department.setCreatedate(new Date());
				department.setCreator(user.getUsername());
			}else{
				System.out.println("json="+json);
				department = JSON.parseObject(json, Department.class);
			}
			Department d = departmentService.selectByPrimaryKey(department.getId());
			if(d ==null){
				departmentService.insertSelective(department);
			}else{
				departmentService.updateByPrimaryKeySelective(department);
			}
			Transaction.commit(transactionManager);
			ar.setSucceedMsg("部门新增成功");
		} catch (NumberFormatException e) {
			ar.setFailMsg("参数错误！");
		} catch (UserException e) {
			ar.setFailMsg("令牌为空！");
			ar.setRes(-1);
			e.printStackTrace();
		} catch (Exception e) {
			Transaction.rollback(transactionManager);
			ar.setFailMsg("部门新增失败，出现异常");
			e.printStackTrace();
		}
		return ar;
	}
	@RequestMapping(value = "/edit")
	@ResponseBody
	public AjaxRes edit(HttpServletRequest request, Department department){
		AjaxRes ar = new AjaxRes();
		try {
			String token = request.getParameter("token");
			User user = Verification.getUser(token,redisUtil);
			department.setUpdatedate(new Date());
			department.setEditor(user.getUsername());
			departmentService.updateByPrimaryKeySelective(department);
			Transaction.commit(transactionManager);
			ar.setSucceedMsg("部门修改成功");
		} catch (NumberFormatException e) {
			ar.setFailMsg("参数错误！");
		} catch (UserException e) {
			ar.setFailMsg("令牌为空！");
			ar.setRes(-1);
			e.printStackTrace();
		} catch (Exception e) {
			Transaction.rollback(transactionManager);
			ar.setFailMsg("部门修改失败，出现异常");
			e.printStackTrace();
		}
		return ar;
	}
	@RequestMapping(value = "/find")
	@ResponseBody
	public AjaxRes find(HttpServletRequest request, String id){
		AjaxRes ar = new AjaxRes();
		try {
			String token = request.getParameter("token");
			User user = Verification.getUser(token,redisUtil);
			Department department = departmentService.selectByPrimaryKey(id);
			ar.setSucceed(department,"部门信息获取成功");
		} catch (NumberFormatException e) {
			ar.setFailMsg("参数错误！");
		} catch (UserException e) {
			ar.setFailMsg("令牌为空！");
			ar.setRes(-1);
			e.printStackTrace();
		} catch (Exception e) {
			ar.setFailMsg("部门信息获取失败，出现异常");
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
			Department department = new Department();
			department.setId(id);
			department.setDelFlag(1);
			departmentService.updateByPrimaryKeySelective(department);
			ar.setSucceedMsg("部门删除成功");
			Transaction.commit(transactionManager);
		} catch (NumberFormatException e) {
			ar.setFailMsg("参数错误！");
		} catch (UserException e) {
			ar.setFailMsg("令牌为空！");
			ar.setRes(-1);
			e.printStackTrace();
		} catch (Exception e) {
			Transaction.rollback(transactionManager);
			ar.setFailMsg("部门删除失败，出现异常");
			e.printStackTrace();
		}
		return ar;
	}
}
