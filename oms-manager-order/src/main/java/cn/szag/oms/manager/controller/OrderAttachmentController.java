package cn.szag.oms.manager.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import cn.szag.oms.manager.common.domain.manager.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;

import cn.szag.oms.manager.common.ajax.AjaxRes;
import cn.szag.oms.manager.common.domain.manager.AutomaticOrder;
import cn.szag.oms.manager.common.domain.manager.OrderAttachment;
import cn.szag.oms.manager.common.domain.manager.Port;
import cn.szag.oms.manager.common.domain.manager.User;
import cn.szag.oms.manager.common.page.Page;
import cn.szag.oms.manager.common.utils.Const;
import cn.szag.oms.manager.common.utils.RedisUtil;
import cn.szag.oms.manager.common.utils.UserException;
import cn.szag.oms.manager.common.utils.UuidUtil;
import cn.szag.oms.manager.common.utils.Verification;
import cn.szag.oms.manager.service.AutomaticOrderService;
import cn.szag.oms.manager.service.OrderAttachmentService;
import cn.szag.oms.manager.service.PortService;

/**
 * 业务管理--单证信息
 * 
 * @ClassName: OrderAttachmentController
 * @Description: TODO
 * @author dengyanghao
 * @date 2019年9月9日 上午11:21:50
 */
@Controller
@RequestMapping(value = "/orderI")
public class OrderAttachmentController {
	@Autowired
	private OrderAttachmentService orderIAttachmentService; // 自助下单服务

	@Autowired
	private RedisUtil redisUtil;

	@RequestMapping(value = "/getAttachmentList")
	@ResponseBody
	public AjaxRes list(HttpServletRequest request) {
		AjaxRes ar = new AjaxRes();
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			String token = request.getParameter("token");
			String orderId = request.getParameter("orderId");
			User user = Verification.getUser(token, redisUtil);
			List<OrderAttachment> portList = orderIAttachmentService.selectByOrderId(orderId);
			map.put("list", portList);
			map.put("totalRecord", portList.size());
			ar.setSucceed(map, "单证信息列表获取成功");
		} catch (NumberFormatException e) {
			ar.setFailMsg("参数错误！");
		} catch (UserException e) {
			ar.setFailMsg("令牌为空！");
			ar.setRes(-1);
			e.printStackTrace();
		} catch (Exception e) {
			ar.setFailMsg("单证信息列表获取失败，出现异常");
			e.printStackTrace();
		}
		return ar;
	}

	@RequestMapping(value = "/getAttachmentCount")
	@ResponseBody
	public AjaxRes count(HttpServletRequest request) {
		AjaxRes ar = new AjaxRes();
		try {
			String token = request.getParameter("token");
			String orderId = request.getParameter("orderId");
			User user = Verification.getUser(token, redisUtil);
			int count = orderIAttachmentService.findByOrderIdCount(orderId);
			if(count>0){
				count = 1; 
			}
			ar.setSucceed(count, "单证信息获取成功");
		} catch (NumberFormatException e) {
			ar.setFailMsg("参数错误！");
		} catch (UserException e) {
			ar.setFailMsg("令牌为空！");
			ar.setRes(-1);
			e.printStackTrace();
		} catch (Exception e) {
			ar.setFailMsg("单证信息列表获取失败，出现异常");
			e.printStackTrace();
		}
		return ar;
	}

	@RequestMapping(value = "/updateAttachmentList")
	@ResponseBody
	public AjaxRes update(HttpServletRequest request, OrderAttachment orderAttachment) {
		AjaxRes ar = new AjaxRes();
		String token = null;
		User user = null;
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			String json = request.getParameter("json");
			if (json == null || "".equals(json)) {
				token = request.getParameter("token");
				user = Verification.getUser(token, redisUtil);
				orderAttachment.setLastUpdateTime(new Date());
				orderAttachment.setLastUpdator(user.getUsername());
				orderAttachment.setLastUpdatorId(user.getId());
			} else {
				orderAttachment = JSON.parseObject(json, OrderAttachment.class);
				orderIAttachmentService.updateByPrimaryKeySelective(orderAttachment);
				return new AjaxRes(Const.SUCCEED, "单证信息添加成功");
			}
			orderIAttachmentService.updateByPrimaryKeySelective(orderAttachment);
			return new AjaxRes(Const.SUCCEED, "单证信息添加成功");

		} catch (NumberFormatException e) {
			ar.setFailMsg("参数错误！");
		} catch (UserException e) {
			ar.setFailMsg("令牌为空！");
			ar.setRes(-1);
			e.printStackTrace();
		} catch (Exception e) {
			ar.setFailMsg("单证信息新增失败，出现异常");
			e.printStackTrace();
		}
		return ar;
	}
	
	@RequestMapping(value = "/update")
	@ResponseBody
	public AjaxRes updateAttachment(HttpServletRequest request, OrderAttachment orderAttachment) {
		AjaxRes ar = new AjaxRes();
		String token = null;
		User user = null;
		try {
				token = request.getParameter("token");
				user = Verification.getUser(token, redisUtil);
				orderAttachment.setLastUpdateTime(new Date());
				orderAttachment.setLastUpdator(user.getUsername());
				orderAttachment.setLastUpdatorId(user.getId());
				orderIAttachmentService.updateByPrimaryKeySelective(orderAttachment);
			return new AjaxRes(Const.SUCCEED, "单证信息修改成功");
		} catch (NumberFormatException e) {
			ar.setFailMsg("参数错误！");
		} catch (UserException e) {
			ar.setFailMsg("令牌为空！");
			ar.setRes(-1);
			e.printStackTrace();
		} catch (Exception e) {
			ar.setFailMsg("单证信息修改失败，出现异常");
			e.printStackTrace();
		}
		return ar;
	}
	

	@RequestMapping(value = "/addAttachmentList")
	@ResponseBody
	public AjaxRes add(HttpServletRequest request, OrderAttachment orderAttachment) {
		AjaxRes ar = new AjaxRes();
		String token = null;
		User user = null;
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			String json = request.getParameter("json");
			if (json == null || "".equals(json)) {
				token = request.getParameter("token");
				user = Verification.getUser(token, redisUtil);
				orderAttachment.setId(UuidUtil.get32UUID());
				orderAttachment.setCreator(user.getUsername());
			} else {
				orderAttachment = JSON.parseObject(json, OrderAttachment.class);
				orderIAttachmentService.insertSelective(orderAttachment);
				return new AjaxRes(Const.SUCCEED, "单证信息添加成功");
			}
			String orderId = request.getParameter("orderId");
			List<OrderAttachment> portList = orderIAttachmentService.selectByOrderId(orderId);
			if (portList.size() == 0) {
				orderAttachment.setCreatetime(new Date());
				orderAttachment.setType(0);
				orderAttachment.setCreator(user.getUsername());
				orderAttachment.setCreatorId(user.getId());
				orderAttachment.setDelFlag(0);
				orderAttachment.setOrderId(orderId);
				orderAttachment.setId(UuidUtil.get32UUID());
				orderIAttachmentService.insertSelective(orderAttachment);

				orderAttachment.setCreatetime(new Date());
				orderAttachment.setType(1);
				orderAttachment.setCreator(user.getUsername());
				orderAttachment.setCreatorId(user.getId());
				orderAttachment.setDelFlag(0);
				orderAttachment.setOrderId(orderId);
				orderAttachment.setId(UuidUtil.get32UUID());
				orderIAttachmentService.insertSelective(orderAttachment);

				orderAttachment.setCreatetime(new Date());
				orderAttachment.setType(2);
				orderAttachment.setCreator(user.getUsername());
				orderAttachment.setCreatorId(user.getId());
				orderAttachment.setDelFlag(0);
				orderAttachment.setOrderId(orderId);
				orderAttachment.setId(UuidUtil.get32UUID());
				orderIAttachmentService.insertSelective(orderAttachment);

				orderAttachment.setCreatetime(new Date());
				orderAttachment.setType(3);
				orderAttachment.setCreator(user.getUsername());
				orderAttachment.setCreatorId(user.getId());
				orderAttachment.setDelFlag(0);
				orderAttachment.setOrderId(orderId);
				orderAttachment.setId(UuidUtil.get32UUID());
				orderIAttachmentService.insertSelective(orderAttachment);

				orderAttachment.setCreatetime(new Date());
				orderAttachment.setType(4);
				orderAttachment.setCreator(user.getUsername());
				orderAttachment.setCreatorId(user.getId());
				orderAttachment.setDelFlag(0);
				orderAttachment.setOrderId(orderId);
				orderAttachment.setId(UuidUtil.get32UUID());
				orderIAttachmentService.insertSelective(orderAttachment);

				orderAttachment.setCreatetime(new Date());
				orderAttachment.setType(5);
				orderAttachment.setCreator(user.getUsername());
				orderAttachment.setCreatorId(user.getId());
				orderAttachment.setDelFlag(0);
				orderAttachment.setOrderId(orderId);
				orderAttachment.setId(UuidUtil.get32UUID());
				orderIAttachmentService.insertSelective(orderAttachment);

				orderAttachment.setCreatetime(new Date());
				orderAttachment.setType(6);
				orderAttachment.setCreator(user.getUsername());
				orderAttachment.setCreatorId(user.getId());
				orderAttachment.setDelFlag(0);
				orderAttachment.setOrderId(orderId);
				orderAttachment.setId(UuidUtil.get32UUID());
				orderIAttachmentService.insertSelective(orderAttachment);

				orderAttachment.setCreatetime(new Date());
				orderAttachment.setType(7);
				orderAttachment.setCreator(user.getUsername());
				orderAttachment.setCreatorId(user.getId());
				orderAttachment.setDelFlag(0);
				orderAttachment.setOrderId(orderId);
				orderAttachment.setId(UuidUtil.get32UUID());
				orderIAttachmentService.insertSelective(orderAttachment);

				orderAttachment.setCreatetime(new Date());
				orderAttachment.setType(8);
				orderAttachment.setCreator(user.getUsername());
				orderAttachment.setCreatorId(user.getId());
				orderAttachment.setDelFlag(0);
				orderAttachment.setOrderId(orderId);
				orderAttachment.setId(UuidUtil.get32UUID());
				orderIAttachmentService.insertSelective(orderAttachment);

				orderAttachment.setCreatetime(new Date());
				orderAttachment.setType(9);
				orderAttachment.setCreator(user.getUsername());
				orderAttachment.setCreatorId(user.getId());
				orderAttachment.setDelFlag(0);
				orderAttachment.setOrderId(orderId);
				orderAttachment.setId(UuidUtil.get32UUID());
				orderIAttachmentService.insertSelective(orderAttachment);
			}
			return new AjaxRes(Const.SUCCEED, "单证信息添加成功");

		} catch (NumberFormatException e) {
			ar.setFailMsg("参数错误！");
		} catch (UserException e) {
			ar.setFailMsg("令牌为空！");
			ar.setRes(-1);
			e.printStackTrace();
		} catch (Exception e) {
			ar.setFailMsg("单证信息新增失败，出现异常");
			e.printStackTrace();
		}
		return ar;
	}
	@RequestMapping(value = "/addAttachmentListE")
	@ResponseBody
	public AjaxRes addE(HttpServletRequest request, OrderAttachment orderAttachment) {
		AjaxRes ar = new AjaxRes();
		String token = null;
		User user = null;
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			String json = request.getParameter("json");
			if (json == null || "".equals(json)) {
				token = request.getParameter("token");
				user = Verification.getUser(token, redisUtil);
				orderAttachment.setId(UuidUtil.get32UUID());
				orderAttachment.setCreator(user.getUsername());
			} else {
				orderAttachment = JSON.parseObject(json, OrderAttachment.class);
				orderIAttachmentService.insertSelective(orderAttachment);
				return new AjaxRes(Const.SUCCEED, "单证信息添加成功");
			}
			String orderId = request.getParameter("orderId");
			List<OrderAttachment> portList = orderIAttachmentService.selectByOrderId(orderId);
			if (portList.size() == 0) {
				orderAttachment.setCreatetime(new Date());
				orderAttachment.setType(0);
				orderAttachment.setCreator(user.getUsername());
				orderAttachment.setCreatorId(user.getId());
				orderAttachment.setDelFlag(0);
				orderAttachment.setOrderId(orderId);
				orderAttachment.setId(UuidUtil.get32UUID());
				orderIAttachmentService.insertSelective(orderAttachment);

				orderAttachment.setType(1);
				orderAttachment.setId(UuidUtil.get32UUID());
				orderIAttachmentService.insertSelective(orderAttachment);

				orderAttachment.setType(2);
				orderAttachment.setId(UuidUtil.get32UUID());
				orderIAttachmentService.insertSelective(orderAttachment);

				orderAttachment.setType(3);
				orderAttachment.setId(UuidUtil.get32UUID());
				orderIAttachmentService.insertSelective(orderAttachment);

				orderAttachment.setType(4);
				orderAttachment.setId(UuidUtil.get32UUID());
				orderIAttachmentService.insertSelective(orderAttachment);

				orderAttachment.setType(5);
				orderAttachment.setId(UuidUtil.get32UUID());
				orderIAttachmentService.insertSelective(orderAttachment);

				orderAttachment.setType(6);
				orderAttachment.setId(UuidUtil.get32UUID());
				orderIAttachmentService.insertSelective(orderAttachment);

				orderAttachment.setType(7);
				orderAttachment.setId(UuidUtil.get32UUID());
				orderIAttachmentService.insertSelective(orderAttachment);

				orderAttachment.setType(8);
				orderAttachment.setId(UuidUtil.get32UUID());
				orderIAttachmentService.insertSelective(orderAttachment);

				orderAttachment.setType(9);
				orderAttachment.setId(UuidUtil.get32UUID());
				orderIAttachmentService.insertSelective(orderAttachment);
				
				orderAttachment.setType(10);
				orderAttachment.setId(UuidUtil.get32UUID());
				orderIAttachmentService.insertSelective(orderAttachment);
				
				orderAttachment.setType(11);
				orderAttachment.setId(UuidUtil.get32UUID());
				orderIAttachmentService.insertSelective(orderAttachment);
			}
			return new AjaxRes(Const.SUCCEED, "单证信息添加成功");

		} catch (NumberFormatException e) {
			ar.setFailMsg("参数错误！");
		} catch (UserException e) {
			ar.setFailMsg("令牌为空！");
			ar.setRes(-1);
			e.printStackTrace();
		} catch (Exception e) {
			ar.setFailMsg("单证信息新增失败，出现异常");
			e.printStackTrace();
		}
		return ar;
	}
	@RequestMapping(value = "/examineAttachment")
	@ResponseBody
	public AjaxRes edit(HttpServletRequest request, OrderAttachment orderAttachment) {
		AjaxRes ar = new AjaxRes();
		String token = null;
		User user = null;
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			String json = request.getParameter("json");
			if (json == null || "".equals(json)) {
				token = request.getParameter("token");
				user = Verification.getUser(token, redisUtil);
				orderAttachment.setLastUpdateTime(new Date());
				orderAttachment.setLastUpdator(user.getUsername());
				orderAttachment.setLastUpdatorId(user.getId());
			} else {
				orderAttachment = JSON.parseObject(json, OrderAttachment.class);
				orderIAttachmentService.updateByPrimaryKeySelective(orderAttachment);
				return new AjaxRes(Const.SUCCEED, "单证信息审核成功");
			}
			orderIAttachmentService.updateByPrimaryKeySelective(orderAttachment);
			return new AjaxRes(Const.SUCCEED, "单证信息审核成功");

		} catch (NumberFormatException e) {
			ar.setFailMsg("参数错误！");
		} catch (UserException e) {
			ar.setFailMsg("令牌为空！");
			ar.setRes(-1);
			e.printStackTrace();
		} catch (Exception e) {
			ar.setFailMsg("单证信息审核失败，出现异常");
			e.printStackTrace();
		}
		return ar;
	}
	

}
