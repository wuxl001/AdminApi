package cn.szag.oms.manager.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cn.szag.oms.manager.common.ajax.AjaxRes;
import cn.szag.oms.manager.common.domain.manager.AutomaticOrder;
import cn.szag.oms.manager.common.domain.manager.User;
import cn.szag.oms.manager.common.page.Page;
import cn.szag.oms.manager.common.utils.Const;
import cn.szag.oms.manager.common.utils.RedisUtil;
import cn.szag.oms.manager.common.utils.UserException;
import cn.szag.oms.manager.common.utils.Verification;
import cn.szag.oms.manager.service.AttentionService;
import cn.szag.oms.manager.service.AutomaticOrderService;
import cn.szag.oms.manager.service.ManagerNoticeService;
import cn.szag.oms.manager.service.OrderAttachmentService;

@RestController
@RequestMapping("/generality")
public class AttentionController {

	@Autowired
	private AttentionService attentionService;

	@Autowired
	private RedisUtil redisUtil;

	/**
	 * 查询功能，根据用户的权限查看订单
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/followList", method = RequestMethod.POST)
	public AjaxRes findByPage(HttpServletRequest request, Page page) {
		AjaxRes ar = new AjaxRes();

		try {
			// 获得token信息
			String token = request.getParameter("token");
			User user = Verification.getUser(token, redisUtil);
			String condition = request.getParameter("condition");
			// 获得用户的权限
			Integer dataPermission = user.getDataPermission();
			if (dataPermission == null) {
				return new AjaxRes(Const.FAIL, Const.NO_AUTHORIZED_MSG);
			}
			return attentionService.followList(user, condition, page);

		} catch (UserException e) {
			ar.setFailMsg("令牌为空！");
			ar.setRes(-1);
			e.printStackTrace();
		} catch (Exception e) {
			ar.setObj(page);
			ar.setFailMsg("获取订单信息失败失败，出现异常");
			e.printStackTrace();
		}
		return ar;
	}

	/**
	 * 查询功能，根据用户的权限查看订单
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/followNumber", method = RequestMethod.POST)
	public AjaxRes followNumber(HttpServletRequest request, AutomaticOrder automaticOrder, Page page) {
		AjaxRes ar = new AjaxRes();

		try {
			Map<String, Object> map = new HashMap<String, Object>();
			String token = request.getParameter("token");
			User user = Verification.getUser(token, redisUtil);
			String isAttention = request.getParameter("isAttention");
			int totalRecord = attentionService.followNumber(isAttention,user);
			map.put("total", totalRecord);
			ar.setSucceed(map, "关注数量获取成功");
		} catch (NumberFormatException e) {
			ar.setFailMsg("参数错误！");
		} catch (UserException e) {
			ar.setFailMsg("令牌为空！");
			ar.setRes(-1);
			e.printStackTrace();
		} catch (Exception e) {
			ar.setFailMsg("关注数量获取失败，出现异常");
			e.printStackTrace();
		}
		return ar;
	}

}
