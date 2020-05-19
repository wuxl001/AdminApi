package cn.szag.oms.manager.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import cn.szag.oms.manager.common.ajax.AjaxRes;
import cn.szag.oms.manager.common.domain.manager.OrderAdviceManager;
import cn.szag.oms.manager.common.domain.manager.User;
import cn.szag.oms.manager.common.utils.RedisUtil;
import cn.szag.oms.manager.common.utils.Transaction;
import cn.szag.oms.manager.common.utils.UserException;
import cn.szag.oms.manager.common.utils.UuidUtil;
import cn.szag.oms.manager.common.utils.Verification;
import cn.szag.oms.manager.service.OrderAdviceManagerService;

@RestController
@RequestMapping("/adviceManager")
public class OrderAdviceManagerController {
	@Autowired
	private OrderAdviceManagerService orderAdviceManagerService;
	@Autowired
	private RedisUtil redisUtil;
	@Autowired
	private PlatformTransactionManager transactionManager;
	
	@RequestMapping(value = "/add")
	@ResponseBody
	public AjaxRes add(HttpServletRequest request,OrderAdviceManager adviceManager){
		AjaxRes ar = new AjaxRes();
		try {
			String token = request.getParameter("token");
			User user = Verification.getUser(token,redisUtil);
			adviceManager.setUserId(user.getId());
			OrderAdviceManager adv = orderAdviceManagerService.findByUserId(user.getId());
			if(null == adv){
				adviceManager.setId(UuidUtil.get32UUID());
				orderAdviceManagerService.insertSelective(adviceManager);
			}else{
				adviceManager.setId(adv.getId());
				orderAdviceManagerService.updateByPrimaryKeySelective(adviceManager);
			}
			Transaction.commit(transactionManager);
			ar.setSucceedMsg("消息免打扰开启成功");
		} catch (NumberFormatException e) {
			ar.setFailMsg("参数错误！");
		} catch (UserException e) {
			ar.setFailMsg("令牌为空！");
			ar.setRes(-1);
			e.printStackTrace();
		} catch (Exception e) {
			Transaction.rollback(transactionManager);
			ar.setFailMsg("消息免打扰开启失败，出现异常");
			e.printStackTrace();
		}
		return ar;
	}
	@RequestMapping(value = "/disturb")
	@ResponseBody
	public AjaxRes disturb(HttpServletRequest request){
		AjaxRes ar = new AjaxRes();
		try {
			String token = request.getParameter("token");
			User user = Verification.getUser(token,redisUtil);
			Map<String,Object> map = new HashMap<>();
			OrderAdviceManager adv = orderAdviceManagerService.findByUserId(user.getId());
			map.put("status", adv==null ? 1:adv.getStatus());
			ar.setSucceed(map,"消息免打扰状态获取成功");
		} catch (NumberFormatException e) {
			ar.setFailMsg("参数错误！");
		} catch (UserException e) {
			ar.setFailMsg("令牌为空！");
			ar.setRes(-1);
			e.printStackTrace();
		} catch (Exception e) {
			ar.setFailMsg("消息免打扰状态获取失败，出现异常");
			e.printStackTrace();
		}
		return ar;
	}
}
