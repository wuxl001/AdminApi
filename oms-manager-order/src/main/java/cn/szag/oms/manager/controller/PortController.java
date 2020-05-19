package cn.szag.oms.manager.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import cn.szag.oms.manager.common.domain.manager.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;

import cn.szag.oms.manager.common.ajax.AjaxRes;
import cn.szag.oms.manager.common.domain.manager.Port;
import cn.szag.oms.manager.common.domain.manager.User;
import cn.szag.oms.manager.common.page.Page;
import cn.szag.oms.manager.common.utils.RedisUtil;
import cn.szag.oms.manager.common.utils.Transaction;
import cn.szag.oms.manager.common.utils.UserException;
import cn.szag.oms.manager.common.utils.UuidUtil;
import cn.szag.oms.manager.common.utils.Verification;
import cn.szag.oms.manager.service.PortService;


/**
 * 业务管理--港口信息
* @ClassName: OrderImportController 
* @Description: TODO
* @author dengyanghao
* @date 2019年9月9日 上午11:21:50
 */
@Controller
@RequestMapping(value ="/port")
public class PortController {
	@Autowired
	private PortService portService;
	@Autowired
	private RedisUtil redisUtil;
	@Autowired
	private PlatformTransactionManager transactionManager;
	@RequestMapping(value = "/list")
	@ResponseBody
	public AjaxRes list(HttpServletRequest request, Port port ,Page page){
		AjaxRes ar = new AjaxRes();
		try {
			Map<String,Object> map = new HashMap<String, Object>();
			String token = request.getParameter("token");
			User user = Verification.getUser(token,redisUtil);
			List<Port> portList = portService.findByPage(port, page);
			page.setResults(portList);
			map.put("list", page);
			ar.setSucceed(map, "港口信息列表获取成功");
		} catch (NumberFormatException e) {
			ar.setFailMsg("参数错误！");
		} catch (UserException e) {
			ar.setFailMsg("令牌为空！");
			ar.setRes(-1);
			e.printStackTrace();
		} catch (Exception e) {
			ar.setFailMsg("港口信息列表获取失败，出现异常");
			e.printStackTrace();
		}
		return ar;
	}
	@RequestMapping(value = "/add")
	@ResponseBody
	public AjaxRes add(HttpServletRequest request, Port port){
		AjaxRes ar = new AjaxRes();
		String token = null;
		User user = null;
		try {
			Map<String,Object> map = new HashMap<String, Object>();
			String json = request.getParameter("json");
			if(json ==null || "".equals(json)){
				token = request.getParameter("token");
				user = Verification.getUser(token,redisUtil);
				port.setId(UuidUtil.get32UUID());
				port.setCreator(user.getUsername());
			}else{
				port = JSON.parseObject(json, Port.class);
			}
			Port p = portService.selectByPrimaryKey(port.getId());
			if(p == null){
				port.setCreatedate(new Date());
				portService.insertSelective(port);
			}else{
				portService.updateByPrimaryKeySelective(port);
			}
			Transaction.commit(transactionManager);
			ar.setSucceedMsg("港口信息新增成功");
		} catch (NumberFormatException e) {
			ar.setFailMsg("参数错误！");
		} catch (UserException e) {
			ar.setFailMsg("令牌为空！");
			ar.setRes(-1);
			e.printStackTrace();
		} catch (Exception e) {
			Transaction.rollback(transactionManager);
			ar.setFailMsg("港口信息新增失败，出现异常");
			e.printStackTrace();
		}
		return ar;
	}
	@RequestMapping(value = "/edit")
	@ResponseBody
	public AjaxRes edit(HttpServletRequest request, Port port){
		AjaxRes ar = new AjaxRes();
		try {
			Map<String,Object> map = new HashMap<String, Object>();
			String token = request.getParameter("token");
			User user = Verification.getUser(token,redisUtil);
			port.setEditor(user.getUsername());
			portService.updateByPrimaryKeySelective(port);
			Transaction.commit(transactionManager);
			ar.setSucceedMsg("港口信息修改成功");
		} catch (NumberFormatException e) {
			ar.setFailMsg("参数错误！");
		} catch (UserException e) {
			ar.setFailMsg("令牌为空！");
			ar.setRes(-1);
			e.printStackTrace();
		} catch (Exception e) {
			Transaction.rollback(transactionManager);
			ar.setFailMsg("港口信息修改失败，出现异常");
			e.printStackTrace();
		}
		return ar;
	}
	@RequestMapping(value = "/del")
	@ResponseBody
	public AjaxRes del(HttpServletRequest request, String id){
		AjaxRes ar = new AjaxRes();
		try {
			Map<String,Object> map = new HashMap<String, Object>();
			String token = request.getParameter("token");
			User user = Verification.getUser(token,redisUtil);
			Port port = new Port();
			port.setId(id);
			port.setDelFlag(1);
			portService.updateByPrimaryKeySelective(port);
			ar.setSucceedMsg("港口信息删除成功");
		} catch (NumberFormatException e) {
			ar.setFailMsg("参数错误！");
		} catch (UserException e) {
			ar.setFailMsg("令牌为空！");
			ar.setRes(-1);
			e.printStackTrace();
		} catch (Exception e) {
			ar.setFailMsg("港口信息删除失败，出现异常");
			e.printStackTrace();
		}
		return ar;
	}
	@RequestMapping(value = "/find")
	@ResponseBody
	public AjaxRes find(HttpServletRequest request, String id){
		AjaxRes ar = new AjaxRes();
		try {
			Map<String,Object> map = new HashMap<String, Object>();
			String token = request.getParameter("token");
			User user = Verification.getUser(token,redisUtil);
			portService.selectByPrimaryKey(id);
			ar.setSucceed(map, "港口信息获取成功");
		} catch (NumberFormatException e) {
			ar.setFailMsg("参数错误！");
		} catch (UserException e) {
			ar.setFailMsg("令牌为空！");
			ar.setRes(-1);
			e.printStackTrace();
		} catch (Exception e) {
			ar.setFailMsg("港口信息获取失败，出现异常");
			e.printStackTrace();
		}
		return ar;
	}
}
