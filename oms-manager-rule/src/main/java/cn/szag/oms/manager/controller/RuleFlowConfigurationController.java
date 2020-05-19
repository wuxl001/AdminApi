package cn.szag.oms.manager.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import cn.szag.oms.manager.common.ajax.AjaxRes;
import cn.szag.oms.manager.common.domain.manager.RuleFlowConfiguration;
import cn.szag.oms.manager.common.domain.manager.User;
import cn.szag.oms.manager.common.utils.RedisUtil;
import cn.szag.oms.manager.common.utils.Verification;
import cn.szag.oms.manager.service.RuleFlowConfigurationService;

/**
 * 流程配置
 * 
 * @ClassName: RuleFlowConfigurationController
 * @Description: TODO
 * @author dengyanghao
 * @date 2019年12月11日 下午2:34:28
 */
@RestController
@RequestMapping(value = "/ruleFlowConfiguration")
public class RuleFlowConfigurationController {

	@Autowired
	private RedisUtil redisUtil;

	@Autowired
	private RuleFlowConfigurationService ruleFlowConfigurationService;

	// 新增
	@RequestMapping(value = "/add")
	public AjaxRes add(HttpServletRequest request, RuleFlowConfiguration ruleFlowConfiguration) {
		AjaxRes ar = new AjaxRes();
		try {
			String token = request.getParameter("token");
			User user = Verification.getUser(token, redisUtil);
			ruleFlowConfigurationService.insertSelective(ruleFlowConfiguration);
			ar.setSucceedMsg("新增流程配置成功！");
		} catch (Exception e) {
			ar.setRes(0);
			ar.setResMsg("新增流程配置失败");
			e.printStackTrace();
		}
		return ar;
	}

	// 修改
	@RequestMapping(value = "/edit")
	public AjaxRes edit(HttpServletRequest request, RuleFlowConfiguration ruleFlowConfiguration) {
		AjaxRes ar = new AjaxRes();
		try {
			String token = request.getParameter("token");
			User user = Verification.getUser(token, redisUtil);
			ruleFlowConfigurationService.updateByPrimaryKeySelective(ruleFlowConfiguration);
			ar.setSucceedMsg("修改流程配置成功！");
		} catch (Exception e) {
			ar.setRes(0);
			ar.setResMsg("修改流程配置失败");
			e.printStackTrace();
		}
		return ar;
	}
}
