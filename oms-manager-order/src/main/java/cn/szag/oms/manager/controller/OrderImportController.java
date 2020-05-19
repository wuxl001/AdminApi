package cn.szag.oms.manager.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.szag.oms.manager.common.ajax.AjaxRes;
import cn.szag.oms.manager.common.domain.manager.AutomaticOrder;
import cn.szag.oms.manager.common.domain.manager.OrderImport;
import cn.szag.oms.manager.common.domain.manager.User;
import cn.szag.oms.manager.common.page.Page;
import cn.szag.oms.manager.common.utils.Const;
import cn.szag.oms.manager.common.utils.RedisUtil;
import cn.szag.oms.manager.common.utils.UserException;
import cn.szag.oms.manager.common.utils.Verification;
import cn.szag.oms.manager.service.AutomaticOrderService;
import cn.szag.oms.manager.service.ModuleManagerService;
import cn.szag.oms.manager.service.OrderExportService;
import cn.szag.oms.manager.service.OrderImportService;


/**
 * 业务管理--进口单
* @ClassName: OrderImportController 
* @Description: TODO
* @author dengyanghao
* @date 2019年9月19日 上午11:21:50
 */
@Controller
@RequestMapping(value ="/board")
public class OrderImportController{
	@Autowired
	private OrderImportService orderImportService;//进口
	@Autowired
	private OrderExportService orderExportService;//出口
	@Autowired
	private AutomaticOrderService automaticOrderService;
	@Autowired
	private ModuleManagerService moduleManagerService;
	@Autowired
	private RedisUtil redisUtil;
	@RequestMapping(value = "/findCount")
	@ResponseBody
	public AjaxRes findCount(HttpServletRequest request,Integer keyWord,Integer type){
		AjaxRes ar = new AjaxRes();
		try {
			Map<String,Object> map = new HashMap<String, Object>();
			String token = request.getParameter("token");
			User user = Verification.getUser(token,redisUtil);
			AutomaticOrder automaticOrder = new AutomaticOrder();
			int totalRecord = 0;
			if(type == 1){//进口
				if(keyWord == 9){//未审核
					automaticOrder.setOrderStatus(0);
					totalRecord = automaticOrderService.selectCount(user, automaticOrder);
				}else if(keyWord == 10){//单证未齐
					automaticOrder.setOrderStatus(4);
					totalRecord = automaticOrderService.selectCount(user, automaticOrder);
				}else if(keyWord == 16){//24小时未审核
					totalRecord = orderImportService.select24(user);
				}else{
					totalRecord = orderImportService.findCount(keyWord, user);
				}
			}else{//出口
				if(keyWord == 30 || keyWord == 31 || keyWord == 33){//（未审核/单证未齐全/24小时未审核）
					totalRecord = orderExportService.findOrder(keyWord, user);
				}else{
					totalRecord = orderExportService.findCount(keyWord, user);
				}
				
			}
			map.put("totalRecord", totalRecord);
			ar.setSucceed(map, "看板数量获取成功");
		} catch (NumberFormatException e) {
			ar.setFailMsg("参数错误！");
		} catch (UserException e) {
			ar.setFailMsg("令牌为空！");
			ar.setRes(-1);
			e.printStackTrace();
		} catch (Exception e) {
			ar.setFailMsg("进口看板数量获取失败，出现异常");
			e.printStackTrace();
		}
		return ar;
	}
	@RequestMapping(value = "/findByCusIdStatus")
	@ResponseBody
	public AjaxRes findByCusIdStatus(HttpServletRequest request,Integer keyWord,Page page){
		AjaxRes ar = new AjaxRes();
		try {
			String parentId = request.getParameter("parentId");
			Map<String,Object> map = new HashMap<String, Object>();
			String token = request.getParameter("token");
			User user = Verification.getUser(token,redisUtil);
			List<OrderImport> orderImportList = orderImportService.findByStatus(keyWord, user, page);
			page.setResults(orderImportList);
			map.put("list", page);
			map.put("permitBtn", moduleManagerService.findBtn(user.getId(), Const.RESOURCES_TYPE_SEARCH, parentId));
			map.put("permitBtn2",moduleManagerService.findBtn2(user.getId(), Const.RESOURCES_TYPE_BUTTON));
			ar.setSucceed(map, "进口订单列表获取成功");
		} catch (NumberFormatException e) {
			ar.setFailMsg("参数错误！");
		} catch (UserException e) {
			ar.setFailMsg("令牌为空！");
			ar.setRes(-1);
			e.printStackTrace();
		} catch (Exception e) {
			ar.setFailMsg("进口订单列表失败，出现异常");
			e.printStackTrace();
		}
		return ar;
	}
}
