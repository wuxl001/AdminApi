package cn.szag.oms.manager.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import cn.szag.oms.manager.common.ajax.AjaxRes;
import cn.szag.oms.manager.common.domain.manager.ManagerNotice;
import cn.szag.oms.manager.common.domain.manager.User;
import cn.szag.oms.manager.common.page.Page;
import cn.szag.oms.manager.common.utils.RedisUtil;
import cn.szag.oms.manager.common.utils.UserException;
import cn.szag.oms.manager.common.utils.Verification;
import cn.szag.oms.manager.service.ManagerNoticeService;

/**
 * 业务消息中心
* @ClassName: ManagerNoticeController 
* @Description: TODO
* @author dengyanghao
* @date 2019年10月11日 上午11:37:31
 */
@RestController
@RequestMapping("/message")
public class ManagerNoticeController {
	@Autowired
	private ManagerNoticeService managerNoticeService;
	@Autowired
	private RedisUtil redisUtil;
	/**
	 * 统计未读消息数量
	* @Title: getUnreadMessageNumber 
	* @Description: TODO 
	* @param @param request
	* @param @return
	* @author dengyanghao
	* @return AjaxRes
	* @throws
	 */
	@RequestMapping(value = "/getUnreadMessageNumber")
	@ResponseBody
	public AjaxRes getUnreadMessageNumber(HttpServletRequest request){
		AjaxRes ar = new AjaxRes();
		try {
			Map<String,Object> map = new HashMap<String, Object>();
			String token = request.getParameter("token");
			User user = Verification.getUser(token,redisUtil);
			int  i = managerNoticeService.findByUserId(user.getId());
			map.put("size", i);
			ar.setSucceed(map, "用户未读消息数量获取成功");
		} catch (NumberFormatException e) {
			ar.setFailMsg("参数错误！");
		} catch (UserException e) {
			ar.setFailMsg("令牌为空！");
			ar.setRes(-1);
			e.printStackTrace();
		} catch (Exception e) {
			ar.setFailMsg("用户未读消息数量获取失败，出现异常");
			e.printStackTrace();
		}
		return ar;
	}
	/**
	 * 消息列表
	* @Title: getMessageList 
	* @Description: TODO 
	* @param @param request
	* @param @param page
	* @param @return
	* @author dengyanghao
	* @return AjaxRes
	* @throws
	 */
	@RequestMapping(value = "/getMessageList")
	@ResponseBody
	public AjaxRes getMessageList(HttpServletRequest request,ManagerNotice managerNotice,Page page){
		AjaxRes ar = new AjaxRes();
		try {
			Map<String,Object> map = new HashMap<String, Object>();
			String token = request.getParameter("token");
			User user = Verification.getUser(token,redisUtil);
			managerNotice.setReceiverId(user.getId());
			List<ManagerNotice> messageList = managerNoticeService.findByPage(managerNotice,page);
			page.setResults(messageList);
			map.put("list", page);
			ar.setSucceed(map, "消息中心列表获取成功");
		} catch (NumberFormatException e) {
			ar.setFailMsg("参数错误！");
		} catch (UserException e) {
			ar.setFailMsg("令牌为空！");
			ar.setRes(-1);
			e.printStackTrace();
		} catch (Exception e) {
			ar.setFailMsg("消息中心列表获取失败，出现异常");
			e.printStackTrace();
		}
		return ar;
	}
	
	
	/**
	 * 消息列表
	* @Title: getMessageList 
	* @Description: TODO 
	* @param @param request
	* @param @param page
	* @param @return
	* @author dengyanghao
	* @return AjaxRes
	* @throws
	 */
	@RequestMapping(value = "/inform")
	@ResponseBody
	public AjaxRes getInform(HttpServletRequest request){
		AjaxRes ar = new AjaxRes();
		try {
			Map<String,Object> map = new HashMap<String, Object>();
			String token = request.getParameter("token");
			User user = Verification.getUser(token,redisUtil);
			String orderId = request.getParameter("orderId");
			List<ManagerNotice> messageList = managerNoticeService.selectInform(orderId);
			map.put("list", messageList);
			ar.setSucceed(map, "消息中心列表获取成功");
		} catch (NumberFormatException e) {
			ar.setFailMsg("参数错误！");
		} catch (UserException e) {
			ar.setFailMsg("令牌为空！");
			ar.setRes(-1);
			e.printStackTrace();
		} catch (Exception e) {
			ar.setFailMsg("消息中心列表获取失败，出现异常");
			e.printStackTrace();
		}
		return ar;
	}
	
	@RequestMapping(value = "/editMessage")
	@ResponseBody
	public AjaxRes editMessage(HttpServletRequest request,ManagerNotice managerNotice){
		AjaxRes ar = new AjaxRes();
		try {
			Map<String,Object> map = new HashMap<String, Object>();
			String token = request.getParameter("token");
			User user = Verification.getUser(token,redisUtil);
			managerNotice.setReadStatus(1);
			//List<ManagerNotice> messageList = managerNoticeService.findByPage(managerNotice,page);
			managerNoticeService.updateByPrimaryKeySelective(managerNotice);
			ar.setSucceedMsg("消息已读修改成功");
		} catch (NumberFormatException e) {
			ar.setFailMsg("参数错误！");
		} catch (UserException e) {
			ar.setFailMsg("令牌为空！");
			ar.setRes(-1);
			e.printStackTrace();
		} catch (Exception e) {
			ar.setFailMsg("消息已读修改失败，出现异常");
			e.printStackTrace();
		}
		return ar;
	}
	@RequestMapping(value = "/editMessageStatus")
	@ResponseBody
	public AjaxRes editMessageStatus(HttpServletRequest request){
		AjaxRes ar = new AjaxRes();
		try {
			Map<String,Object> map = new HashMap<String, Object>();
			String token = request.getParameter("token");
			User user = Verification.getUser(token,redisUtil);
			//List<ManagerNotice> messageList = managerNoticeService.findByPage(managerNotice,page);
			managerNoticeService.updateStatus(user.getId());
			ar.setSucceedMsg("消息已读修改成功");
		} catch (NumberFormatException e) {
			ar.setFailMsg("参数错误！");
		} catch (UserException e) {
			ar.setFailMsg("令牌为空！");
			ar.setRes(-1);
			e.printStackTrace();
		} catch (Exception e) {
			ar.setFailMsg("消息已读修改失败，出现异常");
			e.printStackTrace();
		}
		return ar;
	}
}
