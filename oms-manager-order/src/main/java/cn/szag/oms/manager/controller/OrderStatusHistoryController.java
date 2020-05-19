package cn.szag.oms.manager.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cn.szag.oms.manager.common.ajax.AjaxRes;
import cn.szag.oms.manager.common.domain.manager.AutomaticOrder;
import cn.szag.oms.manager.common.domain.manager.OrderStatusHistory;
import cn.szag.oms.manager.common.domain.manager.User;
import cn.szag.oms.manager.common.page.Page;
import cn.szag.oms.manager.common.utils.Const;
import cn.szag.oms.manager.common.utils.RedisUtil;
import cn.szag.oms.manager.common.utils.SpiltTest;
import cn.szag.oms.manager.common.utils.UserException;
import cn.szag.oms.manager.common.utils.Verification;
import cn.szag.oms.manager.service.AttentionService;
import cn.szag.oms.manager.service.AutomaticOrderService;
import cn.szag.oms.manager.service.ManagerNoticeService;
import cn.szag.oms.manager.service.OrderAttachmentService;
import cn.szag.oms.manager.service.OrderStatusHistoryService;

@RestController
@RequestMapping("/statusHistory")
public class OrderStatusHistoryController {

	@Autowired
	private OrderStatusHistoryService orderStatusHistoryService; 


	@Autowired
	private RedisUtil redisUtil;

	
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	public AjaxRes findByPage(HttpServletRequest request,OrderStatusHistory history) {
		AjaxRes ar = new AjaxRes();

		try {
			// 获得token信息
			String token = request.getParameter("token");
			User user = Verification.getUser(token, redisUtil);
			String condition = request.getParameter("condition");
			List<OrderStatusHistory> list = orderStatusHistoryService.selectList(history);
			Map<String,String> map = new HashMap<String,String>();
			List<OrderStatusHistory> list2 = new ArrayList<OrderStatusHistory>();
			for (OrderStatusHistory orderStatusHistory : list) {
				map = SpiltTest.createImg(orderStatusHistory.getRemark(),orderStatusHistory.getId());
				if(!map.isEmpty()){
					orderStatusHistory.setRemark(map.get("results"));
					orderStatusHistory.setUrl(map.get("filename"));
				}
				list2.add(orderStatusHistory);
			}
			ar.setSucceed(list2, "获取订单状态历史数据成功");
            ar.setRes(1);
		} catch (UserException e) {
			ar.setFailMsg("令牌为空！");
			ar.setRes(-1);
			e.printStackTrace();
		} catch (Exception e) {
			ar.setFailMsg("获取订单状态历史失败失败，出现异常");
			e.printStackTrace();
		}
		return ar;
	}

}
