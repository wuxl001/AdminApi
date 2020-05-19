package cn.szag.oms.manager.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;

import cn.szag.oms.manager.common.ajax.AjaxRes;
import cn.szag.oms.manager.common.domain.manager.Attention;
import cn.szag.oms.manager.common.domain.manager.OrderAttentionCustomer;
import cn.szag.oms.manager.common.domain.manager.User;
import cn.szag.oms.manager.common.utils.RedisUtil;
import cn.szag.oms.manager.common.utils.UserException;
import cn.szag.oms.manager.common.utils.UuidUtil;
import cn.szag.oms.manager.common.utils.Verification;
import cn.szag.oms.manager.service.OrderAttentionCustomerService;

@RestController
@RequestMapping("/attentionCustomer")
public class AttentionCustomerController {
	@Autowired
	private OrderAttentionCustomerService orderAttentionCustomerService;
	@Autowired
	private RedisUtil redisUtil;
	/**
	 * 新增关注信息 @Title: addAttention @Description: TODO @param @param
	 * request @param @param attention @param @return @author
	 * dengyanghao @return AjaxRes @throws
	 */
	@RequestMapping(value = "/addAttention")
	@ResponseBody
	public AjaxRes addAttention(HttpServletRequest request, OrderAttentionCustomer attention) {
		AjaxRes ar = new AjaxRes();
		String token = null;
		User user = null;
		try {
			String json = request.getParameter("json");
			if ("".equals(json) || json == null) {
				token = request.getParameter("token");
				user = Verification.getUser(token, redisUtil);
				attention.setId(UuidUtil.get32UUID());
				attention.setUserId(user.getId());
				attention.setAttentionDate(new Date());
			} else {
				attention = JSON.parseObject(json, OrderAttentionCustomer.class);
			}
			orderAttentionCustomerService.deleteByPrimaryKey(attention.getId());
			orderAttentionCustomerService.insertSelective(attention);
			ar.setSucceedMsg("关注信息新增成功");
		} catch (NumberFormatException e) {
			ar.setFailMsg("参数错误！");
		} catch (UserException e) {
			ar.setFailMsg("令牌为空！");
			ar.setRes(-1);
			e.printStackTrace();
		} catch (Exception e) {
			ar.setFailMsg("关注信息新增失败，出现异常");
			e.printStackTrace();
		}
		return ar;
	}

	/**
	 * 修改关注关注信息 @Title: editAttention @Description: TODO @param @param
	 * request @param @param attention @param @return @author
	 * dengyanghao @return AjaxRes @throws
	 */
	@RequestMapping(value = "/editAttention")
	@ResponseBody
	public AjaxRes editAttention(HttpServletRequest request, OrderAttentionCustomer attention) {
		AjaxRes ar = new AjaxRes();
		String token = null;
		User user = null;
		try {
			String json = request.getParameter("json");
			if ("".equals(json) || json == null) {
				token = request.getParameter("token");
				user = Verification.getUser(token, redisUtil);
			} else {
				attention = JSON.parseObject(json, OrderAttentionCustomer.class);
			}
			orderAttentionCustomerService.updateByPrimaryKeySelective(attention);
			ar.setSucceedMsg("关注信息修改成功");
		} catch (NumberFormatException e) {
			ar.setFailMsg("参数错误！");
		} catch (UserException e) {
			ar.setFailMsg("令牌为空！");
			ar.setRes(-1);
			e.printStackTrace();
		} catch (Exception e) {
			ar.setFailMsg("关注信息修改失败，出现异常");
			e.printStackTrace();
		}
		return ar;
	}
}
