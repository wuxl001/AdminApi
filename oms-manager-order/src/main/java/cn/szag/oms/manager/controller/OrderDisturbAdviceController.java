package cn.szag.oms.manager.controller;

import javax.management.RuntimeErrorException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.web.bind.annotation.RequestMapping;
/**
 * 消息免打扰
* @ClassName: OrderDisturbAdviceController 
* @Description: TODO
* @author dengyanghao
* @date 2019年9月26日 下午7:31:06
 */
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;

import cn.szag.oms.manager.common.ajax.AjaxRes;
import cn.szag.oms.manager.common.domain.manager.OrderDisturbAdvice;
import cn.szag.oms.manager.common.domain.manager.User;
import cn.szag.oms.manager.common.utils.RedisUtil;
import cn.szag.oms.manager.common.utils.Transaction;
import cn.szag.oms.manager.common.utils.UserException;
import cn.szag.oms.manager.common.utils.UuidUtil;
import cn.szag.oms.manager.common.utils.Verification;
import cn.szag.oms.manager.service.OrderDisturbAdviceService;
@Controller
@RequestMapping(value ="/board")
public class OrderDisturbAdviceController {
	@Autowired
	private OrderDisturbAdviceService orderDisturbAdviceService;

	//在每个controller中注入transactionManager
	@Autowired
	private PlatformTransactionManager transactionManager;
	@Autowired
	private RedisUtil redisUtil;
	@RequestMapping(value = "/disturbAdd")
	@ResponseBody
	public AjaxRes disturbAdd(HttpServletRequest request, OrderDisturbAdvice advice){
		AjaxRes ar = new AjaxRes();
		String token = null;
		User user = null;
		try {
			String json = request.getParameter("json");
			if("".equals(json) || json == null){
				token = request.getParameter("token");
				user = Verification.getUser(token,redisUtil);
				advice.setUserId(user.getId());
				advice.setId(UuidUtil.get32UUID());
			}else{
				advice = JSON.parseObject(json, OrderDisturbAdvice.class);
			}
			OrderDisturbAdvice advice2 = orderDisturbAdviceService.selectByPrimaryKey(advice);
			if(null != advice2){
				orderDisturbAdviceService.updateByPrimaryKeySelective(advice);
			}else{
				orderDisturbAdviceService.insertSelective(advice);
			}
			Transaction.commit(transactionManager);
			ar.setSucceedMsg("消息免打扰设置成功");
		} catch (NumberFormatException e) {
			ar.setFailMsg("参数错误！");
		} catch (UserException e) {
			ar.setFailMsg("令牌为空！");
			ar.setRes(-1);
			e.printStackTrace();
		} catch (Exception e) {
			Transaction.rollback(transactionManager);
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			ar.setFailMsg("消息免打扰设置失败，出现异常");
			e.printStackTrace();
		}
		return ar;
	}
	@RequestMapping(value = "/disturbEdit")
	@ResponseBody
	public AjaxRes disturbEdit(HttpServletRequest request, OrderDisturbAdvice advice){
		AjaxRes ar = new AjaxRes();
		try {
			String token = request.getParameter("token");
			User user = Verification.getUser(token,redisUtil);
			orderDisturbAdviceService.updateByPrimaryKeySelective(advice);
			ar.setSucceedMsg("消息免打扰修改成功");
			Transaction.commit(transactionManager);
		} catch (NumberFormatException e) {
			ar.setFailMsg("参数错误！");
		} catch (UserException e) {
			ar.setFailMsg("令牌为空！");
			ar.setRes(-1);
			e.printStackTrace();
		} catch (Exception e) {
			Transaction.rollback(transactionManager);
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			ar.setFailMsg("消息免打扰修改失败，出现异常");
			e.printStackTrace();
		}
		return ar;
	}
	@RequestMapping(value = "/disturb")
	@ResponseBody
	public AjaxRes disturb(HttpServletRequest request, OrderDisturbAdvice advice){
		AjaxRes ar = new AjaxRes();
		try {
			String token = request.getParameter("token");
			User user = Verification.getUser(token,redisUtil);
			advice.setUserId(user.getId());
			advice = orderDisturbAdviceService.selectByPrimaryKey(advice);
			ar.setSucceed(advice,"消息免打扰信息获取成功");
		} catch (NumberFormatException e) {
			ar.setFailMsg("参数错误！");
		} catch (UserException e) {
			ar.setFailMsg("令牌为空！");
			ar.setRes(-1);
			e.printStackTrace();
		} catch (Exception e) {
			ar.setFailMsg("消息免打扰信息获取失败，出现异常");
			e.printStackTrace();
		}
		return ar;
	}
	@RequestMapping(value = "/disturbAdd2")
	@ResponseBody
	public AjaxRes Adddisturb(HttpServletRequest request, OrderDisturbAdvice advice)throws Exception{
		AjaxRes ar = new AjaxRes();
		String token = null;
		User user = null;
		try {
			String json = request.getParameter("json");
			if("".equals(json) || json == null){
				token = request.getParameter("token");
				user = Verification.getUser(token,redisUtil);
				advice.setUserId(user.getId());
				advice.setId(UuidUtil.get32UUID());
			}
			orderDisturbAdviceService.insertSelective2(advice);
			advice.setId(UuidUtil.get32UUID());
			orderDisturbAdviceService.insertSelective2(advice);
			System.out.println(1/0);
			ar.setSucceedMsg("消息免打扰设置成功");
			Transaction.commit(transactionManager);
		} catch (NumberFormatException e) {
			e.printStackTrace();
			ar.setFailMsg("参数错误！");
		} catch (UserException e) {
			ar.setFailMsg("令牌为空！");
			ar.setRes(-1);
			e.printStackTrace();
		} catch (Exception e) {
			Transaction.rollback(transactionManager);
			ar.setFailMsg("消息免打扰设置失败，出现异常");
			e.printStackTrace();
			
		}
		return ar;
	}
}
