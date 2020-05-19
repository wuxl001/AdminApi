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
import cn.szag.oms.manager.common.domain.manager.GoodsInfo;
import cn.szag.oms.manager.common.domain.manager.OrderDisturbAdvice;
import cn.szag.oms.manager.common.domain.manager.User;
import cn.szag.oms.manager.common.page.Page;
import cn.szag.oms.manager.common.utils.RedisUtil;
import cn.szag.oms.manager.common.utils.Transaction;
import cn.szag.oms.manager.common.utils.UserException;
import cn.szag.oms.manager.common.utils.UuidUtil;
import cn.szag.oms.manager.common.utils.Verification;
import cn.szag.oms.manager.service.GoodsInfoService;


/**
 * 业务管理--物资信息
* @ClassName: OrderImportController 
* @Description: TODO
* @author dengyanghao
* @date 2019年9月9日 上午11:21:50
 */
@Controller
@RequestMapping(value ="/goodsInfo")
public class GoodsInfoController{
	@Autowired
	private GoodsInfoService goodsInfoService;
	@Autowired
	private RedisUtil redisUtil;
	@Autowired
	private PlatformTransactionManager transactionManager;
	@RequestMapping(value = "/list")
	@ResponseBody
	public AjaxRes list(HttpServletRequest request, GoodsInfo info ,Page page){
		AjaxRes ar = new AjaxRes();
		try {
			Map<String,Object> map = new HashMap<String, Object>();
			String token = request.getParameter("token");
			User user = Verification.getUser(token,redisUtil);
			List<GoodsInfo> portList = goodsInfoService.findByPage(info, page);
			page.setResults(portList);
			map.put("list", page);
			ar.setSucceed(map, "货物信息列表获取成功");
		} catch (NumberFormatException e) {
			ar.setFailMsg("参数错误！");
		} catch (UserException e) {
			ar.setFailMsg("令牌为空！");
			ar.setRes(-1);
			e.printStackTrace();
		} catch (Exception e) {
			ar.setFailMsg("货物信息列表获取失败，出现异常");
			e.printStackTrace();
		}
		return ar;
	}
	@RequestMapping(value = "/add")
	@ResponseBody
	public AjaxRes add(HttpServletRequest request, GoodsInfo info){
		AjaxRes ar = new AjaxRes();
		String token = null;
		User user = null;
		try {
			String json = request.getParameter("json");
			Map<String,Object> map = new HashMap<String, Object>();
			if(json ==null || "".equals(json)){
				token = request.getParameter("token");
				user = Verification.getUser(token,redisUtil);
				info.setId(UuidUtil.get32UUID());
				info.setCreator(user.getUsername());
			}else{
				info = JSON.parseObject(json, GoodsInfo.class);
			}
			GoodsInfo g = goodsInfoService.selectByPrimaryKey(info.getId());
			if(g == null){
				info.setCreatedate(new Date());
				goodsInfoService.insertSelective(info);
			}else{
				info.setLastupdate(new Date());
				goodsInfoService.updateByPrimaryKeySelective(info);
			}
			Transaction.commit(transactionManager);
			ar.setSucceedMsg("货物信息新增成功");
		} catch (NumberFormatException e) {
			ar.setFailMsg("参数错误！");
		} catch (UserException e) {
			ar.setFailMsg("令牌为空！");
			ar.setRes(-1);
			e.printStackTrace();
		} catch (Exception e) {
			Transaction.rollback(transactionManager);
			ar.setFailMsg("货物信息新增失败，出现异常");
			e.printStackTrace();
		}
		return ar;
	}
	@RequestMapping(value = "/edit")
	@ResponseBody
	public AjaxRes edit(HttpServletRequest request, GoodsInfo info){
		AjaxRes ar = new AjaxRes();
		try {
			Map<String,Object> map = new HashMap<String, Object>();
			String token = request.getParameter("token");
			User user = Verification.getUser(token,redisUtil);
			info.setEditor(user.getUsername());
			goodsInfoService.updateByPrimaryKeySelective(info);
			ar.setSucceedMsg("货物信息修改成功");
		} catch (NumberFormatException e) {
			ar.setFailMsg("参数错误！");
		} catch (UserException e) {
			ar.setFailMsg("令牌为空！");
			ar.setRes(-1);
			e.printStackTrace();
		} catch (Exception e) {
			ar.setFailMsg("货物信息修改失败，出现异常");
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
			GoodsInfo info = new GoodsInfo();
			info.setId(id);
			info.setDelFlag(1);
			goodsInfoService.updateByPrimaryKeySelective(info);
			ar.setSucceedMsg("货物信息删除成功");
		} catch (NumberFormatException e) {
			ar.setFailMsg("参数错误！");
		} catch (UserException e) {
			ar.setFailMsg("令牌为空！");
			ar.setRes(-1);
			e.printStackTrace();
		} catch (Exception e) {
			ar.setFailMsg("货物信息删除失败，出现异常");
			e.printStackTrace();
		}
		return ar;
	}
}
