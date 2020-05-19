package cn.szag.oms.manager.controller;

import cn.jiguang.common.resp.APIConnectionException;
import cn.jiguang.common.resp.APIRequestException;
import cn.jpush.api.JPushClient;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.PushPayload;
import cn.szag.oms.manager.common.ajax.AjaxRes;
import cn.szag.oms.manager.common.domain.manager.*;
import cn.szag.oms.manager.common.enums.OrderStatusEnum;
import cn.szag.oms.manager.common.enums.ScheduleStatusEnum;
import cn.szag.oms.manager.common.page.Page;
import cn.szag.oms.manager.common.utils.*;
import cn.szag.oms.manager.common.utils.wechat.Template;
import cn.szag.oms.manager.common.utils.wechat.WeChatExploitMessage;
import cn.szag.oms.manager.service.AutomaticOrderService;
import cn.szag.oms.manager.service.CusUserService;
import cn.szag.oms.manager.service.CustomerServicerelationService;
import cn.szag.oms.manager.service.ManagerNoticeService;
import cn.szag.oms.manager.service.MessagelogService;
import cn.szag.oms.manager.service.ModuleManagerService;
import cn.szag.oms.manager.service.OrderAttachmentService;
import cn.szag.oms.manager.service.OrderImportService;
import cn.szag.oms.manager.service.OrderStatusHistoryService;
import cn.szag.oms.manager.service.OrderWorklistStatusService;
import cn.szag.oms.manager.service.UserControlService;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * 自助下单功能
 */
@RestController
@RequestMapping("/orderI")
public class AutomaticOrderController {
	// 本地
	// static final String FILE_PATH = "\\szag-oms-images\\"; // 附件上传的位置
	// 测试
	static final String FILE_PATH = "/szag-oms-images/"; // 附件上传的位置
	private static String imageUrl = Url.imagesUrl;
	@Autowired
	private AutomaticOrderService automaticOrderService; // 自助下单服务

	@Autowired
	private OrderAttachmentService orderAttachmentService; // 附件业务

	@Autowired
	private ManagerNoticeService managerNoticeService; // 客户通知信息

	@Autowired
	private OrderStatusHistoryService orderStatusHistoryService;

	@Autowired
	private RedisUtil redisUtil;

	@Autowired
	private OrderImportService orderImportService;

	@Autowired
	private CusUserService cusUserService;// 客户联系人

	@Autowired
	private PlatformTransactionManager transactionManager;

	@Autowired
	private UserControlService userControlService;// 用户

	@Autowired
	private OrderWorklistStatusService orderWorklistStatusService;// 工作状态

	@Autowired
	private MessagelogService messagelogService;// 记录日志消息

	@Autowired
	private CustomerServicerelationService CsService;// 客户，客服绑定

	// private final String external_url =
	// "http://192.168.252.245:8065/fms_api/oms";
	private final String external_url = Url.cfsUrl;
	// private final String external_url = "http://192.168.252.154:8065/oms";

	// oms客户地址
	private final String omscustomer_url = Url.omsUrl;

	JPushClient jpushClient = new JPushClient(PushExample.MASTER_SECRET, PushExample.APP_KEY);// 客户

	/**
	 * 查询功能，根据用户的权限查看订单
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	public AjaxRes findByPage(HttpServletRequest request, AutomaticOrder automaticOrder, Page page) {
		AjaxRes ar = new AjaxRes();

		try {
			// 获得token信息
			String token = request.getParameter("token");
			String parentId = request.getParameter("parentId");
			User user = Verification.getUser(token, redisUtil);
			// 获得用户的权限
			Integer dataPermission = user.getDataPermission();
			if (dataPermission == null) {
				return new AjaxRes(Const.FAIL, Const.NO_AUTHORIZED_MSG);
			}
			return automaticOrderService.selectList(user, automaticOrder, page, parentId);

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

	@RequestMapping(value = "/getKey", method = RequestMethod.POST)
	public AjaxRes selectAll(HttpServletRequest request, AutomaticOrder automaticOrder) {
		AjaxRes ar = new AjaxRes();

		try {
			// 获得token信息
			String token = request.getParameter("token");
			User user = Verification.getUser(token, redisUtil);
			// 获得用户的权限
			Integer dataPermission = user.getDataPermission();
			if (dataPermission == null) {
				return new AjaxRes(Const.FAIL, Const.NO_AUTHORIZED_MSG);
			}
			List<String> list = automaticOrderService.selectAll(automaticOrder, user);

			System.out.println(list.toString());

			ar.setSucceed(list, "获取数据成功");

		} catch (UserException e) {
			ar.setFailMsg("令牌为空！");
			ar.setRes(-1);
			e.printStackTrace();
		} catch (Exception e) {
			ar.setFailMsg("获取订单信息失败失败，出现异常");
			e.printStackTrace();
		}
		return ar;
	}

	@RequestMapping(value = "/getKey2", method = RequestMethod.POST)
	public AjaxRes selectAll2(HttpServletRequest request, AutomaticOrder automaticOrder) {
		AjaxRes ar = new AjaxRes();

		try {
			// 获得token信息
			String token = request.getParameter("token");
			User user = Verification.getUser(token, redisUtil);
			// 获得用户的权限
			Integer dataPermission = user.getDataPermission();
			if (dataPermission == null) {
				return new AjaxRes(Const.FAIL, Const.NO_AUTHORIZED_MSG);
			}
			List<OrderImport> list = automaticOrderService.selectAll2(automaticOrder, user);

			System.out.println(list.toString());

			ar.setSucceed(list, "获取数据成功");

		} catch (UserException e) {
			ar.setFailMsg("令牌为空！");
			ar.setRes(-1);
			e.printStackTrace();
		} catch (Exception e) {
			ar.setFailMsg("获取订单信息失败失败，出现异常");
			e.printStackTrace();
		}
		return ar;
	}

	/**
	 * 查询待审核和单证未全数量
	 * 
	 * @param request
	 * @param automaticOrder
	 * @return
	 */
	@RequestMapping(value = "/selectCount", method = RequestMethod.POST)
	public AjaxRes selectCount(HttpServletRequest request, AutomaticOrder automaticOrder) {
		AjaxRes ar = new AjaxRes();

		try {
			// 获得token信息
			String token = request.getParameter("token");
			User user = Verification.getUser(token, redisUtil);
			// 获得用户的权限
			Integer dataPermission = user.getDataPermission();
			if (dataPermission == null) {
				return new AjaxRes(Const.FAIL, Const.NO_AUTHORIZED_MSG);
			}

			int count = automaticOrderService.selectCount(user, automaticOrder);
			ar.setSucceed(count, "获取成功");
		} catch (UserException e) {
			ar.setFailMsg("令牌为空！");
			ar.setRes(-1);
			e.printStackTrace();
		} catch (Exception e) {
			ar.setFailMsg("查询数量失败失败，出现异常");
			e.printStackTrace();
		}
		return ar;
	}

	/**
	 * 订单管理进口单查询接口设计
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/getImportManageList", method = RequestMethod.POST)
	public AjaxRes findImportManageList(HttpServletRequest request, AutomaticOrder automaticOrder, Page page) {
		AjaxRes ar = new AjaxRes();

		try {
			// 获得token信息
			String token = request.getParameter("token");
			User user = Verification.getUser(token, redisUtil);
			// 获得用户的权限
			Integer dataPermission = user.getDataPermission();
			if (dataPermission == null) {
				return new AjaxRes(Const.FAIL, Const.NO_AUTHORIZED_MSG);
			}
			return automaticOrderService.orderTrackingList(user, automaticOrder, page);

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
	 * 订单管理进口单查询接口设计
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/standardImportList", method = RequestMethod.POST)
	public AjaxRes standardImportList(HttpServletRequest request, AutomaticOrder automaticOrder, Page page) {
		AjaxRes ar = new AjaxRes();

		try {
			// 获得token信息
			String token = request.getParameter("token");
			User user = Verification.getUser(token, redisUtil);
			// 获得用户的权限
			Integer dataPermission = user.getDataPermission();
			if (dataPermission == null) {
				return new AjaxRes(Const.FAIL, Const.NO_AUTHORIZED_MSG);
			}
			ar = automaticOrderService.standardImportList(user, automaticOrder, page);
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
	 * 新增订单 写入 b库 在order_import 。
	 * 
	 * @return
	 */
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public AjaxRes addOrder(HttpServletRequest request, OrderImport orderImport) {

		String token = null;
		User user = null;
		try {
			String json = request.getParameter("json");
			OrderStatusHistory history = null;
			CustomerServicerelation csl = new CustomerServicerelation();
			csl.setCreateTime(new Date());
			if ("".equals(json) || json == null) {
				token = request.getParameter("token");
				user = Verification.getUser(token, redisUtil);
			} else {
				orderImport = JSON.parseObject(json, OrderImport.class);
				orderImportService.insertSelective(orderImport);
				// 订单状态历史
				history = new OrderStatusHistory();
				history.setId(UuidUtil.get32UUID());
				history.setOrderId(orderImport.getId());
				history.setStatus(orderImport.getOrderStatus());
				history.setCreator(orderImport.getCreator());
				history.setCreatetime(new Date());
				history.setCreatorId(orderImport.getCreatorId());
				history.setIsAvailable(1);
				if (CsService.findCusomerIs(orderImport.getCompanyId()) == 0) {// 如果该客户没有绑定过客服和业务员，将绑定当前客服和业务员
					csl.setCreator(orderImport.getCreator());
					csl.setCustomerId(orderImport.getCompanyId());
					csl.setSalesmanId(Integer.parseInt(orderImport.getSalesman()));
					csl.setId(UuidUtil.get32UUID());
					csl.setServiceId(Integer.parseInt(orderImport.getSupportStaff()));
					if (orderImport.getGoodstype() == 1) {
						csl.setOrigin("西货");
					} else {
						csl.setOrigin("东南亚货");
					}
					CsService.insert(csl);
				}
				orderStatusHistoryService.insertSelective(history);

				return new AjaxRes(Const.SUCCEED, "订单添加成功");
			}

			if (StringUtils.isEmpty(orderImport.getTransportWay())) {
				return new AjaxRes(Const.FAIL, "运输方式不能为空");
			}

			if (!StringUtils.isEmpty(orderImport.getExtractOrderNum())) {
				Map<String, Object> params = new HashMap<String, Object>();
				params.put("orderId", orderImport.getId());
				params.put("blNo", orderImport.getExtractOrderNum());
				String str = HttpUtils.post(external_url + "/checkBlNo", params, 5000, 5000, "UTF-8");
				Map maps = (Map) JSON.parse(str);
				if ("1".equals(maps.get("code"))) {
					return new AjaxRes(Const.FAIL, "提单号已存在");
				}
				int count = automaticOrderService.findByExtractOrderNum(orderImport.getExtractOrderNum(), null);
				if (count > 0) {
					return new AjaxRes(Const.FAIL, "提单号已存在");
				}
			}
			// orderImport.setId(UuidUtil.get32UUID());
			orderImport.setDelFlag(0);
			orderImport.setCreatorId(user.getId());
			orderImport.setCreator(user.getUsername());
			orderImport.setCreatetime(new Date());
			orderImport.setCreatorTel(user.getTel());
			if (CsService.findCusomerIs(orderImport.getCompanyId()) == 0) {// 如果该客户没有绑定过客服和业务员，将绑定当前客服和业务员
				csl.setCreator(orderImport.getCreator());
				csl.setCustomerId(orderImport.getCompanyId());
				csl.setSalesmanId(Integer.parseInt(orderImport.getSalesman()));
				csl.setId(UuidUtil.get32UUID());
				csl.setServiceId(Integer.parseInt(orderImport.getSupportStaff()));
				if (orderImport.getGoodstype() == 1) {
					csl.setOrigin("西货");
				} else {
					csl.setOrigin("东南亚货");
				}
				CsService.insert(csl);
			}
			automaticOrderService.insertSelective(orderImport);

			// 订单状态历史
			history = new OrderStatusHistory();
			history.setId(UuidUtil.get32UUID());
			history.setOrderId(orderImport.getId());
			history.setStatus(orderImport.getOrderStatus());
			history.setCreator(orderImport.getCreator());
			history.setCreatetime(new Date());
			history.setIsAvailable(1);
			history.setCreatorId(orderImport.getCreatorId());
			orderStatusHistoryService.insertSelective(history);
			Transaction.commit(transactionManager);
			return new AjaxRes(Const.SUCCEED, "订单添加成功");
		} catch (NumberFormatException e) {
			return new AjaxRes(Const.FAIL, "参数错误！！");
		} catch (UserException e) {
			e.printStackTrace();
			return new AjaxRes(Const.FAIL, "令牌为空！");
		} catch (Exception e) {
			Transaction.rollback(transactionManager);
			e.printStackTrace();
			return new AjaxRes(Const.FAIL, "添加订单信息失败，出现异常");
		}
	}

	/**
	 * 修改订单前的数据回显
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/findOrderById")
	public AjaxRes findOrderById(HttpServletRequest request) {
		try {
			// 获得token信息
			String token = request.getParameter("token");
			User user = Verification.getUser(token, redisUtil);
			String id = request.getParameter("id");
			String containerId = request.getParameter("containerId");
			OrderImport orderImport = automaticOrderService.selectByPrimaryKey(containerId, id);
			if(orderImport!=null){
				if(!StringUtils.isEmpty(orderImport.getRemark())){
					SpiltTest st = new SpiltTest();
					orderImport.setRemark(st.JPGZH(orderImport.getRemark()));
				}
			}
			return new AjaxRes(Const.SUCCEED, "订单查询成功", orderImport);
		} catch (NumberFormatException e) {
			return new AjaxRes(Const.FAIL, "参数错误！！");
		} catch (UserException e) {
			e.printStackTrace();
			return new AjaxRes(Const.FAIL, "令牌为空！");
		} catch (Exception e) {
			e.printStackTrace();
			return new AjaxRes(Const.FAIL, "查询订单信息失败，出现异常");
		}
	}

	/**
	 * 修改订单 写入 b库 在order_import
	 * 
	 * @return
	 */
	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public AjaxRes editOrder(HttpServletRequest request, OrderImport orderImport) {

		String token = null;
		User user = null;
		try {
			String json = request.getParameter("json");
			OrderStatusHistory history = new OrderStatusHistory();
			if ("".equals(json) || json == null) {
				token = request.getParameter("token");
				user = Verification.getUser(token, redisUtil);
			} else {
				orderImport = JSON.parseObject(json, OrderImport.class);
				orderImportService.updateByPrimaryKeyWithBLOBs(orderImport);
				// 订单状态历史
				// 订单状态历史
				history.setIsAvailable(1);
				history.setId(UuidUtil.get32UUID());
				history.setOrderId(orderImport.getId());
				history.setStatus(orderImport.getOrderStatus());
				history.setCreator(orderImport.getCreator());
				history.setCreatetime(new Date());
				history.setIsAvailable(1);
				history.setCreatorId(orderImport.getCreatorId());
				orderStatusHistoryService.insertSelective(history);
				return new AjaxRes(Const.SUCCEED, "订单修改成功");
			}
			if (StringUtils.isEmpty(orderImport.getId())) {
				return new AjaxRes(Const.FAIL, "订单id不能为空");
			}
			if (!StringUtils.isEmpty(orderImport.getExtractOrderNum())) {
				Map<String, Object> params = new HashMap<String, Object>();
				params.put("orderId", orderImport.getId());
				params.put("blNo", orderImport.getExtractOrderNum());
				String str = HttpUtils.post(external_url + "/checkBlNo", params, 5000, 5000, "UTF-8");
				Map maps = (Map) JSON.parse(str);
				if (maps.get("code").equals(1)) {
					return new AjaxRes(Const.FAIL, "提单号已存在");
				}
				int count = automaticOrderService.findByExtractOrderNum(orderImport.getExtractOrderNum(), null);
				if (count > 0) {
					int count2 = automaticOrderService.findByExtractOrderNum(orderImport.getExtractOrderNum(),
							orderImport.getId());
					if (count2 == 0) {
						return new AjaxRes(Const.FAIL, "提单号已存在");
					} else if (count > 1) {
						return new AjaxRes(Const.FAIL, "提单号已存在");
					}
				}
			}

			// 0：待审核/1：已退回/2：待补齐/3：待补料审核/4：已审核/5：已终止
			if (orderImport.getOrderStatus() == 4 || orderImport.getOrderStatus() == 1) {
				// 审核时间
				orderImport.setCheckTime(new Date());
			} else if (orderImport.getOrderStatus() == 5) {
				// 终止人和终止时间
				orderImport.setClosingPer(user.getUsername());
				orderImport.setClosingDate(new Date());
			} else if (orderImport.getOrderStatus() == 2) {
				orderImport.setSupplementTime(new Date());
			}

			OrderImport oi = automaticOrderService.selectByPrimaryKey(null, orderImport.getId());
			if (null == oi) {
				orderImport.setCreatorId(user.getId());
				orderImport.setCreatetime(new Date());
			} else {
				orderImport.setUpdaterId(user.getId());
				orderImport.setUpdateTime(new Date());
			}
			if ("1".equals(Url.carryOut) && orderImport.getOrderStatus() == 4) {
				Activemq.sned(orderImport, "orderICamel");// 发送mq队列
				return new AjaxRes(Const.SUCCEED, "订单修改成功");
			}
			addHistory(orderImport, user);
			if (null == oi) {
 				automaticOrderService.insertSelective(orderImport);
			} else {
				automaticOrderService.updateByPrimaryKeyWithBLOBs(orderImport);
			}
			if (orderImport.getOrderStatus() == 4) {
				OrderImport orderJson = orderImportService.selectByPrimaryKey(orderImport.getId());
				User user2 = userControlService.selectByPrimaryKey(orderImport.getSalesmanId());// 业务员
				if (null != user2.getDpname()) {// 业务员
					orderJson.setSalesmanDepa(user2.getDpname());
				}
				User user3 = userControlService.selectByPrimaryKey(orderImport.getSupportStaffId());
				if (null != user3.getDpname()) {// 客服部门
					orderJson.setSupportStaffDepa(user3.getDpname());
				}
				// orderJson.setCreatetime(orderJson.getCreatetime());
				if (null != orderJson) {
					if (!StringUtils.isEmpty(orderJson.getHandlingSuggestion())) {
						orderJson.setHandlingSuggestion(orderJson.getHandlingSuggestion().replaceAll("\\<.*?>", ""));
					}
					if (!StringUtils.isEmpty(orderJson.getRemark())) {
						orderJson.setRemark(orderJson.getRemark().replaceAll("\\<.*?>", ""));
					}
				}
				String json2 = JSONObject.toJSONString(orderJson);
				System.out.println("往cfs发送数据：" + json2);
				String str = HttpRequestUtil.sendPost(external_url + "/addWorklist", "json=" + json2, false);
				Map maps = (Map) JSON.parse(str);
				if (maps.get("code").equals(1)) {
					Transaction.rollback(transactionManager);
					return new AjaxRes(Const.FAIL, "发送CFS失败，请联系相关人员");
				} else {
					orderImport.setWorklistNo(maps.get("worklistNo") + "");
				}
			}
			automaticOrderService.updateByPrimaryKeyWithBLOBs(orderImport);
			// 订单状态历史
			if (orderImport.getOrderStatus() == 4 || orderImport.getOrderStatus() == 1
					|| orderImport.getOrderStatus() == 2) {
				OrderImport import1 = orderImportService.selectByPrimaryKey(orderImport.getId());
				CustomerAccount customerAccount = null;
				if (!import1.getOrderSource().equals("2")) {
					customerAccount = cusUserService.selectByPrimaryKey(import1.getCreatorId());
					if (customerAccount != null) {
						pushMessage(orderImport, customerAccount);
					}
				} else {
					customerAccount = null;
					customerAccount = cusUserService.selectByPrimaryKey(orderImport.getOrderPlacer());
					if (customerAccount != null) {
						pushMessage(orderImport, customerAccount);
					}
				}
			}
			Transaction.commit(transactionManager);
			return new AjaxRes(Const.SUCCEED, "订单修改成功");
		} catch (NumberFormatException e) {
			return new AjaxRes(Const.FAIL, "参数错误！！");
		} catch (UserException e) {
			e.printStackTrace();
			return new AjaxRes(Const.FAIL, "令牌为空！");
		} catch (Exception e) {
			Transaction.rollback(transactionManager);
			e.printStackTrace();
			return new AjaxRes(Const.FAIL, "修改订单信息失败，出现异常");
		}
	}

	/**
	 * 修改订单 写入 b库 在order_import
	 * 
	 * @return
	 */
	@RequestMapping(value = "/edit2", method = RequestMethod.POST)
	public AjaxRes editOrder2(HttpServletRequest request, OrderImport orderImport) {

		String token = null;
		User user = null;
		try {
			String json = request.getParameter("json");
			OrderStatusHistory history = new OrderStatusHistory();
			if ("".equals(json) || json == null) {
				token = request.getParameter("token");
				user = Verification.getUser(token, redisUtil);
			} else {
				orderImport = JSON.parseObject(json, OrderImport.class);
				orderImportService.updateByPrimaryKeyWithBLOBs(orderImport);
				// 订单状态历史
				// 订单状态历史
				history.setIsAvailable(1);
				history.setId(UuidUtil.get32UUID());
				history.setOrderId(orderImport.getId());
				history.setStatus(orderImport.getOrderStatus());
				history.setCreator(orderImport.getCreator());
				history.setCreatetime(new Date());
				history.setIsAvailable(1);
				history.setCreatorId(orderImport.getCreatorId());
				orderStatusHistoryService.insertSelective(history);
				return new AjaxRes(Const.SUCCEED, "订单修改成功");
			}
			if (StringUtils.isEmpty(orderImport.getId())) {
				return new AjaxRes(Const.FAIL, "订单id不能为空");
			}
			if (!StringUtils.isEmpty(orderImport.getExtractOrderNum())) {
				Map<String, Object> params = new HashMap<String, Object>();
				params.put("orderId", orderImport.getId());
				params.put("blNo", orderImport.getExtractOrderNum());
				String str = HttpUtils.post(external_url + "/checkBlNo", params, 5000, 5000, "UTF-8");
				Map maps = (Map) JSON.parse(str);
				if (maps.get("code").equals(1)) {
					return new AjaxRes(Const.FAIL, "提单号已存在");
				}
				int count = automaticOrderService.findByExtractOrderNum(orderImport.getExtractOrderNum(), null);
				if (count > 0) {
					int count2 = automaticOrderService.findByExtractOrderNum(orderImport.getExtractOrderNum(),
							orderImport.getId());
					if (count2 == 0) {
						return new AjaxRes(Const.FAIL, "提单号已存在");
					} else if (count > 1) {
						return new AjaxRes(Const.FAIL, "提单号已存在");
					}
				}
			}
			orderImport.setCreatorId(user.getId());
			orderImport.setCreatetime(new Date());
			Activemq.sned(orderImport, "orderICamel");// 发送mq队列
			return new AjaxRes(Const.SUCCEED, "订单修改成功");
		} catch (NumberFormatException e) {
			return new AjaxRes(Const.FAIL, "参数错误！！");
		} catch (UserException e) {
			e.printStackTrace();
			return new AjaxRes(Const.FAIL, "令牌为空！");
		} catch (Exception e) {
			Transaction.rollback(transactionManager);
			e.printStackTrace();
			return new AjaxRes(Const.FAIL, "修改订单信息失败，出现异常");
		}
	}

	/**
	 * 修改订单 写入 b库 在order_import
	 * 
	 * @return
	 */
	@RequestMapping(value = "/edit3")
	public AjaxRes editOrder3(HttpServletRequest request) {

		User user = new User();
		OrderImport orderImport = null;
		try {
			String json = request.getParameter("json");
			System.out.println("json=" + json);
			orderImport = JSON.parseObject(json, OrderImport.class);
			// 0：待审核/1：已退回/2：待补齐/3：待补料审核/4：已审核/5：已终止
			if (orderImport.getOrderStatus() == 4) {
				// 审核时间
				orderImport.setCheckTime(new Date());
			}
			user.setId(orderImport.getCreatorId());
			user.setUsername(orderImport.getCreator());
			addHistory(orderImport, user);
			OrderImport oi = automaticOrderService.selectByPrimaryKey(null, orderImport.getId());
			if (null == oi) {
				automaticOrderService.insertSelective(orderImport);
			} else {
				automaticOrderService.updateByPrimaryKeyWithBLOBs(orderImport);
			}

			if (orderImport.getOrderStatus() == 4) {
				OrderImport orderJson = orderImportService.selectByPrimaryKey(orderImport.getId());
				User user2 = userControlService.selectByPrimaryKey(orderImport.getSupportStaffId());// 业务员
				if (null != user2.getDpname()) {// 客服部门
					orderJson.setSupportStaffDepa(user2.getDpname());
				}
				User user3 = userControlService.selectByPrimaryKey(orderImport.getSalesmanId());
				if (null != user3.getDpname()) {// 业务员部门
					orderJson.setSalesmanDepa(user3.getDpname());
				}
				if (null != orderJson) {
					orderJson.setHandlingSuggestion(orderJson.getHandlingSuggestion().replaceAll("\\<.*?>", ""));
					orderJson.setRemark(orderJson.getRemark().replaceAll("\\<.*?>", ""));
				}
				String json2 = JSONObject.toJSONString(orderJson);
				System.out.println("往cfs发送数据：" + json2);
				String str = HttpRequestUtil.sendPost(external_url + "/addWorklist", "json=" + json2, false);
				Map maps = (Map) JSON.parse(str);
				if (maps.get("code").equals(1)) {
					Transaction.rollback(transactionManager);
					return new AjaxRes(Const.FAIL, "发送CFS失败，请联系相关人员");
				} else {
					orderImport.setWorklistNo(maps.get("worklistNo") + "");
				}
			}
			orderImport.setDisposeStatus(2);
			automaticOrderService.updateByPrimaryKeyWithBLOBs(orderImport);
			// 订单状态历史
			if (orderImport.getOrderStatus() == 4) {
				OrderImport import1 = orderImportService.selectByPrimaryKey(orderImport.getId());
				CustomerAccount customerAccount = null;
				if (!import1.getOrderSource().equals("2")) {
					customerAccount = cusUserService.selectByPrimaryKey(import1.getCreatorId());
					if (customerAccount != null) {
						pushMessage(orderImport, customerAccount);
					}
				} else {
					customerAccount = null;
					customerAccount = cusUserService.selectByPrimaryKey(orderImport.getOrderPlacer());
					if (customerAccount != null) {
						pushMessage(orderImport, customerAccount);
					}
				}
			}
			Transaction.commit(transactionManager);
			return new AjaxRes(Const.SUCCEED, "订单修改成功");
		} catch (NumberFormatException e) {
			return new AjaxRes(Const.FAIL, "参数错误！！");
		} catch (UserException e) {
			e.printStackTrace();
			return new AjaxRes(Const.FAIL, "令牌为空！");
		} catch (Exception e) {
			Transaction.rollback(transactionManager);// 回滚
			OrderImport o = new OrderImport();
			o.setId(orderImport.getId());
			o.setDisposeStatus(3);
			orderImportService.updateByPrimaryKeySelective(o);// 记录发送失败
			Transaction.commit(transactionManager);// 重新提交
			e.printStackTrace();
			return new AjaxRes(Const.FAIL, "修改订单信息失败，出现异常");
		}
	}

	// 添加审核历史
	public void addHistory(OrderImport orderImport, User user) {
		OrderStatusHistory history = new OrderStatusHistory();
		history.setId(UuidUtil.get32UUID());
		history.setOrderId(orderImport.getId());
		history.setStatus(orderImport.getOrderStatus());
		history.setIsAvailable(1);
		history.setCreator(user.getUsername());
		history.setCreatetime(new Date());
		history.setCreatorId(user.getId());
		history.setRemark(orderImport.getHandlingSuggestion());
		if (orderImport.getOrderStatus() == 2) {
			history.setPhase("补料");
		}
		orderStatusHistoryService.insertSelective(history);
	}

	public void pushMessage(OrderImport orderImports, CustomerAccount account) {
		OrderImport orderImport = orderImportService.selectByPrimaryKey(orderImports.getId());

		if (null != orderImports) {
			ManagerNotice managerNotice = new ManagerNotice();// 消息中心
			List<OrderWorklistStatus> wokelist = orderWorklistStatusService.select(orderImports.getId());// 集装箱
			if (wokelist.size() > 0) {
				List<String> boxId = new ArrayList<String>();
				for (OrderWorklistStatus orderWorklistStatus : wokelist) {
					boxId.add(orderWorklistStatus.getId());
				}
				String containerId = boxId.toString();
				managerNotice.setContainerId(containerId.replace("[", "").replace("]", ""));
			}
			managerNotice.setId(UuidUtil.get32UUID());
			managerNotice.setOrderId(orderImport.getId());
			managerNotice.setTitleType(1);
			managerNotice.setContent(orderImports.getHandlingSuggestion());
			managerNotice.setCreatetime(new Date());
			managerNotice.setReceiverId(account.getId());
			// managerNoticeService.insert(managerNotice);
			String title = "";
			String result = "";
			if (orderImport.getTransportWay().equals("0")) {
				title = "海运进口";
			} else if (orderImport.getTransportWay().equals("1")) {
				title = "陆运进口";
			} else if (orderImport.getTransportWay().equals("2")) {
				title = "空运进口";
			}
			if (orderImports.getOrderStatus() == 4) {
				result = "通过";
			} else if (orderImports.getOrderStatus() == 1) {
				result = "退回";
			}
			managerNotice.setTitle(title);
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String dateString = formatter.format(orderImport.getCusAdviceDate());
			String text = "";// 公众号消息推送
			if (orderImports.getOrderStatus() == 2) {
				String cause = "原因：" + managerNotice.getContent() + "";
				String str = account.getUsername() + "您好，你有OMS" + title + "的集装箱号为" + orderImport.getBoxNo() + "的消息提醒";
				String stc = title + "消息提醒:" + dateString + " 您的订单号为" + orderImport.getOrderNo() + " 集装箱"
						+ orderImport.getBoxNo() + " 单证资料未齐。";
				text = MessageXLS.ImportFeedingMessage(orderImport.getOrderNo(), orderImport.getBoxNo(),
						managerNotice.getContent());
				managerNotice.setContent(stc + cause);
				EmailDemo emailDemo = new EmailDemo();
				if (account.getEmail() != null && !"".equals(account.getEmail())) {
					String arr = stc + "<br/>" + cause;
					System.out.println("邮件内容=" + arr);
					emailDemo.send(account.getEmail(), str, arr);
				}
			} else if (orderImports.getOrderStatus() == 1 || orderImports.getOrderStatus() == 4) {
				String str = account.getUsername() + "您好，你有OMS" + title + "的集装箱号为" + orderImport.getBoxNo() + "的消息提醒";
				String stc = title + "消息提醒" + dateString + " 您的订单号为" + orderImport.getOrderNo() + "集装箱"
						+ orderImport.getBoxNo() + "处理结果为" + result + "，请查看";
				managerNotice.setContent(stc);
				// 获取公众号推送消息内容格式
				text = MessageXLS.ImportAuditMessage(orderImport.getOrderNo(), orderImport.getBoxNo(), result);
				EmailDemo emailDemo = new EmailDemo();
				if (account.getEmail() != null && !"".equals(account.getEmail())) {
					if (emailDemo.checkout(account.getEmail())) {// 校验邮箱是否正确
						emailDemo.send(account.getEmail(), str, stc);
					} else {
						Messagelog messageLog = new Messagelog();
						messageLog.setAccount(account.getAccount());
						messageLog.setEmail(account.getEmail());
						messageLog.setMessage(stc);
						messageLog.setUserId(account.getId());
						messagelogService.insert(messageLog);
					}
				}
			}
			managerNoticeService.insertSelective(managerNotice);
			/**
			 * App推送方法
			 */
			if (null != account) {
				if (account.getPushStatus() == 0) {
					if (null != account.getJpushId()) {
						System.out.println(PushExample.MASTER_SECRET + "---" + PushExample.APP_KEY);
						System.out.println(PushExample.MASTER_SECRET + "---" + PushExample.APP_KEY
								+ account.getJpushId() + "---" + title + "---" + managerNotice.getContent());
						PushPayload payload = PushExample.buildPushObject_ios_audienceMore_messageWithExtras(
								account.getJpushId(), title, managerNotice.getContent());
						try {
							PushResult pu = jpushClient.sendPush(payload);
						} catch (APIConnectionException | APIRequestException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
				if (null != account.getWechatId() && !"".equals(account.getWechatId())) {
					Template template = new Template();
					template.contentTest(account.getWechatId(), text);
				}
			}
		}
	}

	/**
	 * 修改订单 写入 b库 在order_import
	 * 
	 * @return
	 */
	@RequestMapping(value = "/returnWriteId", method = RequestMethod.POST)
	public AjaxRes returnWriteId(HttpServletRequest request, OrderImport orderImport) {

		String token = null;
		User user = null;
		try {
			String json = request.getParameter("json");
			OrderStatusHistory history = new OrderStatusHistory();
			if ("".equals(json) || json == null) {
				token = request.getParameter("token");
				user = Verification.getUser(token, redisUtil);
				orderImport.setUpdaterId(user.getId());
				orderImport.setUpdateTime(new Date());
			} else {
				orderImport = JSON.parseObject(json, OrderImport.class);
				orderImportService.updateByPrimaryKeySelective(orderImport);
				return new AjaxRes(Const.SUCCEED, "订单修改成功");
			}
			if(!StringUtils.isEmpty(orderImport.getCustomerId())){
				int i = CsService.findCusomerIs(orderImport.getCustomerId());
				if (i == 0) {// 如果当前公司没有绑定客服，则为当前公司绑定一个新客服，用于当前公司以后的单都分配给这个客服和业务员
					CustomerServicerelation customerService = new CustomerServicerelation();
					customerService.setId(UuidUtil.get32UUID());
					customerService.setCustomerId(orderImport.getCustomerId());
					customerService.setSalesmanId(Integer.valueOf(orderImport.getSalesman()));
					customerService.setServiceId(Integer.valueOf(orderImport.getSupportStaff()));
					CsService.insert(customerService);
	
				}	
			}

			orderImportService.updateByPrimaryKeySelective(orderImport);
			Transaction.commit(transactionManager);
			return new AjaxRes(Const.SUCCEED, "订单修改成功");
		} catch (NumberFormatException e) {
			return new AjaxRes(Const.FAIL, "参数错误！！");
		} catch (UserException e) {
			e.printStackTrace();
			return new AjaxRes(Const.FAIL, "令牌为空！");
		} catch (Exception e) {
			e.printStackTrace();
			Transaction.rollback(transactionManager);
			return new AjaxRes(Const.FAIL, "修改订单信息失败，出现异常");
		}

	}

	/**
	 * 删除订单 1、用 b库。
	 * 
	 * @return
	 */
	@RequestMapping(value = "/del", method = RequestMethod.POST)
	public AjaxRes delOrder(HttpServletRequest request, OrderImport orderImport) {

		String token = null;
		User user = null;
		try {
			String json = request.getParameter("json");
			if ("".equals(json) || json == null) {
				token = request.getParameter("token");
				user = Verification.getUser(token, redisUtil);
			} else {
				orderImport = JSON.parseObject(json, OrderImport.class);
				automaticOrderService.updateByPrimaryKeySelective(orderImport);
				return new AjaxRes(Const.SUCCEED, "订单删除成功");
			}

			// 判断是否是已审核、待审核、已终止，且订单状态是否被删除
			OrderImport orderImport1 = automaticOrderService.selectByPrimaryKey(null, orderImport.getId());

			// 订单状态
			if (orderImport1 != null) {
				if (orderImport1.getOrderStatus() == null) {
					return new AjaxRes(Const.FAIL, "订单状态不能为空");
				}
				if (orderImport1.getOrderStatus().equals(OrderStatusEnum.STAY_AUDIT)) {
					return new AjaxRes(Const.FAIL, "订单已审核不能删除");

				}
				if (orderImport1.getOrderStatus().equals(OrderStatusEnum.ALREADY_SEND_BACK)) {
					return new AjaxRes(Const.FAIL, "订单已退回不能删除");

				}
				if (orderImport1.getOrderStatus().equals(OrderStatusEnum.ALREADY_TERMINATION)) {
					return new AjaxRes(Const.FAIL, "订单已终止不能删除");

				}
			}
			if (orderImport.getId() == null) {
				return new AjaxRes(Const.FAIL, "订单ID不能为空");
			}
			// 逻辑删除 修改del_flag为0：已经删除标志
			orderImport.setDelFlag(1);
			automaticOrderService.updateByPrimaryKeySelective(orderImport);
			return new AjaxRes(Const.SUCCEED, "订单删除成功！");
		} catch (NumberFormatException e) {
			return new AjaxRes(Const.FAIL, "参数错误！！");
		} catch (UserException e) {
			e.printStackTrace();
			return new AjaxRes(Const.FAIL, "令牌为空！");
		} catch (Exception e) {
			e.printStackTrace();
			return new AjaxRes(Const.FAIL, "删除订单失败，出现异常");
		}

	}

	/**
	 * 在 order_import 表根据提单号查询是否存在，因为提单号不能重复，所以需做判断
	 * 
	 * @param request
	 * @param orderImport
	 * @return
	 */
	@RequestMapping(value = "/findByExtOrdNum", method = RequestMethod.POST)
	public AjaxRes findByExtOrdNum(HttpServletRequest request, OrderImport orderImport) {
		try {
			// 获得token信息
			String token = request.getParameter("token");
			User user = Verification.getUser(token, redisUtil);

			OrderImport orderImport1 = automaticOrderService.selectByOrderNO(orderImport.getExtractOrderNum());
			if (orderImport1 == null) {
				return new AjaxRes(Const.FAIL, "该提单号不存在");
			}
			return new AjaxRes(Const.SUCCEED, "该提单号存在!");
		} catch (NumberFormatException e) {
			return new AjaxRes(Const.FAIL, "参数错误！！");
		} catch (UserException e) {
			e.printStackTrace();
			return new AjaxRes(Const.FAIL, "令牌为空！");
		} catch (Exception e) {
			e.printStackTrace();
			return new AjaxRes(Const.FAIL, "查询订单信息失败，出现异常!");
		}

	}

	@RequestMapping(value = "/addAttachment")
	@ResponseBody
	public AjaxRes add(HttpServletRequest request, Port port) {
		AjaxRes ar = new AjaxRes();
		String token = null;
		User user = null;
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			String json = request.getParameter("json");
			if (json == null || "".equals(json)) {
				token = request.getParameter("token");
				user = Verification.getUser(token, redisUtil);
				port.setId(UuidUtil.get32UUID());
				port.setCreator(user.getUsername());
			} else {
				port = JSON.parseObject(json, Port.class);
			}
			port.setCreatedate(new Date());

			ar.setSucceedMsg("港口信息新增成功");
		} catch (NumberFormatException e) {
			ar.setFailMsg("参数错误！");
		} catch (UserException e) {
			ar.setFailMsg("令牌为空！");
			ar.setRes(-1);
			e.printStackTrace();
		} catch (Exception e) {
			ar.setFailMsg("港口信息新增失败，出现异常");
			e.printStackTrace();
		}
		return ar;
	}

	/**
	 * 附件上传
	 * 
	 * @param orderAttachment
	 * @return
	 */
	@RequestMapping(value = "/upload", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	@ResponseBody
	public AjaxRes uploadFile(HttpServletRequest request, @RequestParam(value = "file") MultipartFile file,
			OrderAttachment orderAttachment) {

		// 获得token信息
		String token = request.getParameter("token");
		User user = Verification.getUser(token, redisUtil);
		// 根据订单查询，查询点订单的状态是否为待补料，当附件上传完成后自动更新状态为补料待审核
		OrderImport orderImport = automaticOrderService.selectByPrimaryKey(null, orderAttachment.getOrderId());
		if (orderAttachment.getOrderId() == null) {
			return new AjaxRes(Const.FAIL, "订单不能为空");
		}
		if (null == orderImport) {
			return new AjaxRes(Const.FAIL, "该订单不存在");
		}
		if (orderImport.getDelFlag() == 1) {
			return new AjaxRes(Const.FAIL, "订单已被删除");
		}
		String originalFilename = file.getOriginalFilename();
		String userHome = System.getProperty("user.home");
		String Path = userHome + FILE_PATH;
		if (!file.isEmpty()) {
			try {
				File files = new File(Path);
				if (!files.exists() && !files.isDirectory()) {
					files.mkdir();
				}
				// 获得文件名
				String filename = file.getOriginalFilename();
				// 命名新的文件名
				String extension = FilenameUtils.getExtension(filename);
				String newFileName = UuidUtil.get32UUID() + "." + extension;
				System.out.println(newFileName);
				long size = file.getSize();
				System.out.println("文件大小 :" + size);
				String sizes = size / 1024 + "";
				System.out.println("文件大小 :" + sizes);
				String savePath = Path + newFileName;
				File file1 = new File(savePath);
				file.transferTo(file1);
				orderAttachment.setSize(Integer.parseInt(sizes));
				orderAttachment.setFormat(extension);
				// orderAttachment.setId(UuidUtil.get32UUID());
				orderAttachment.setFileUrl(savePath);
				// orderAttachment.setCreatetime(new Date());
				// orderAttachment.setCreatorId(user.getId());
				// orderAttachment.setCreator(user.getUsername());
				orderAttachment.setDelFlag(0);
				orderAttachment.setLastUpdateTime(new Date());
				orderAttachment.setLastUpdator(user.getUsername());
				orderAttachment.setLastUpdatorId(user.getId());
				orderAttachment.setAttachmentName(filename);
				if (orderImport.getOrderStatus() == 2) {
					orderImport.setOrderStatus(3);
					automaticOrderService.updateByPrimaryKeySelective(orderImport);
				}
				orderAttachmentService.updateByPrimaryKeySelective(orderAttachment);
				if (orderImport.getOrderSource() != "2") {
					String json2 = JSONObject.toJSONString(orderAttachment);
					System.out.println(json2);
					HttpRequestUtil.sendPost(omscustomer_url + "/orderI/UpadteAttachmen", "json=" + json2, false);
				}
				return new AjaxRes(Const.SUCCEED, "附件数据上传成功");
			} catch (NumberFormatException e) {
				return new AjaxRes(Const.FAIL, "参数错误！！");
			} catch (UserException e) {
				e.printStackTrace();
				return new AjaxRes(Const.FAIL, "令牌为空！");
			} catch (Exception e) {
				e.printStackTrace();
				return new AjaxRes(Const.FAIL, "附件信息上传失败，出现异常");
			}
		}
		return null;
	}

	/**
	 * 附件下载
	 * 
	 * @param
	 * @return
	 */
	@RequestMapping(value = "/down")
	public AjaxRes down(HttpServletRequest req, HttpServletResponse resp, String id) {
		Map<String, Object> params = new HashMap<String, Object>();
		try {
			// 获得token信息
			String token = req.getParameter("token");
			User user = Verification.getUser(token, redisUtil);
			resp.reset();// 清空输出流
			OrderAttachment orderAttachment = orderAttachmentService.selectByPrimaryKey(id);
			String attachmentName = orderAttachment.getAttachmentName();
			String fileUrl = orderAttachment.getFileUrl();
			File file = new File(fileUrl);
			InputStream inputStream = null;
			if (!file.exists()) {
				String httpUrl = omscustomer_url + "/orderI/downAtter?id=" + id;
				URL url = new URL(httpUrl);
				HttpURLConnection conn = (HttpURLConnection) url.openConnection();
				inputStream = conn.getInputStream();
			} else {
				inputStream = new FileInputStream(file);
			}
			// 输入流：本地文件路径

			// 输出流
			OutputStream out = resp.getOutputStream();
			attachmentName = URLEncoder.encode(attachmentName, "UTF-8");
			resp.setCharacterEncoding("UTF-8");
			resp.setHeader("Content-disposition", "attachment; filename=" + attachmentName);// 设定输出文件头
			resp.setContentType("application/msexcel");// 定义输出类型

			// 输出文件
			int bytes = 0;
			byte[] bufferOut = new byte[1024];
			while ((bytes = inputStream.read(bufferOut)) != -1) {
				out.write(bufferOut, 0, bytes);
			}
			out.close();
			inputStream.close();
			return new AjaxRes(Const.SUCCEED, "下载成功");
		} catch (NumberFormatException e) {
			return new AjaxRes(Const.FAIL, "参数错误！！");
		} catch (UserException e) {
			e.printStackTrace();
			return new AjaxRes(Const.FAIL, "令牌为空！");
		} catch (IOException e) {
			e.printStackTrace();
			return new AjaxRes(Const.FAIL, "下载失败");
		}

	}

	/**
	 * 修改附件，用于读取/写入 A 库（主要给业务订单系统调用）
	 * 
	 * @return
	 */
	@RequestMapping(value = "editAttachmen")
	private AjaxRes editAttachmen(HttpServletRequest request, MultipartFile attachmentName,
			OrderAttachment orderAttachment) {
		// 获得token信息
		String token = request.getParameter("token");
		User user = Verification.getUser(token, redisUtil);
		String userHome = System.getProperty("user.home");
		String Path = userHome + FILE_PATH;
		System.out.println(Path);
		if (!attachmentName.isEmpty()) {
			try {
				File file = new File(Path);
				if (!file.exists() && !file.isDirectory()) {
					file.mkdir();
				}
				// 获得文件名
				String filename = attachmentName.getOriginalFilename();
				// 命名新的文件名
				String extension = FilenameUtils.getExtension(filename);
				String newFileName = UuidUtil.get32UUID() + "." + extension;
				System.out.println(newFileName);

				String savePath = Path + newFileName;
				attachmentName.transferTo(new File(savePath));

				orderAttachment.setFileUrl(savePath);
				orderAttachment.setDelFlag(0);
				orderAttachment.setLastUpdateTime(new Date());
				orderAttachment.setLastUpdator(user.getUsername());
				orderAttachment.setLastUpdatorId(user.getId());
				orderAttachment.setAttachmentName(newFileName);
				orderAttachmentService.updateByPrimaryKeySelective(orderAttachment);
				return new AjaxRes(Const.SUCCEED, "附件数据保存成功");
			} catch (NumberFormatException e) {
				return new AjaxRes(Const.FAIL, "参数错误！！");
			} catch (UserException e) {
				e.printStackTrace();
				return new AjaxRes(Const.FAIL, "令牌为空！");
			} catch (Exception e) {
				e.printStackTrace();
				return new AjaxRes(Const.FAIL, "附件信息上传失败，出现异常");
			}
		}
		return new AjaxRes(Const.SUCCEED, "附件修改成功");

	}

	/**
	 * 发送补料通知接口设计
	 * 
	 * @param request
	 * @param managerNotice
	 * @return
	 */
	@RequestMapping("/advice")
	public AjaxRes addAdvice(HttpServletRequest request, ManagerNotice managerNotice) {
		try {
			// 获得token信息
			String token = request.getParameter("token");
			User user = Verification.getUser(token, redisUtil);
			// 判断通知消息的id是否为空，如果不为空
			if (managerNotice.getTitleType() == null) {
				return new AjaxRes(Const.FAIL, "通知主题类别不能为空");
			}
			// if ( managerNotice.getMessageType()==null){
			// return new AjaxRes(Const.FAIL,"通知内容类别不能为空");
			// }
			if (managerNotice.getOrderId() == null) {
				return new AjaxRes(Const.FAIL, "订单id不能为空");
			}
			managerNotice.setId(UuidUtil.get32UUID());
			managerNotice.setCreatetime(new Date());
			managerNoticeService.insert(managerNotice);

			return new AjaxRes(Const.SUCCEED, "消息中心表添加成功");
		} catch (NumberFormatException e) {
			return new AjaxRes(Const.FAIL, "参数错误！！");
		} catch (UserException e) {
			e.printStackTrace();
			return new AjaxRes(Const.FAIL, "令牌为空！");
		} catch (Exception e) {
			e.printStackTrace();
			return new AjaxRes(Const.FAIL, "添加补料通知信息失败，出现异常");
		}

	}

	/**
	 * 查询通知时间大于24小时的订单
	 * 
	 * @param request
	 * @param
	 * @return
	 */
	@RequestMapping("/listBy24")
	public AjaxRes listBy24(HttpServletRequest request, AutomaticOrder automaticOrder, Page page) {
		try {
			// 获得token信息
			String token = request.getParameter("token");
			User user = Verification.getUser(token, redisUtil);
			Integer dataPermission = user.getDataPermission();
			if (dataPermission == null) {
				return new AjaxRes(Const.FAIL, Const.NO_AUTHORIZED_MSG);
			}
			Page pageResult = automaticOrderService.findlistBy24(user, automaticOrder, page);
			return new AjaxRes(Const.SUCCEED, "成功", pageResult);
		} catch (NumberFormatException e) {
			return new AjaxRes(Const.FAIL, "参数错误！！");
		} catch (UserException e) {
			e.printStackTrace();
			return new AjaxRes(Const.FAIL, "令牌为空！");
		} catch (Exception e) {
			e.printStackTrace();
			return new AjaxRes(Const.FAIL, "修改订单信息失败，出现异常");
		}

	}

	/**
	 * 生成订单头部接口
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/establishOrderHead")
	@ResponseBody
	public AjaxRes establishOrderHead(HttpServletRequest request) {
		AjaxRes ar = new AjaxRes();
		try {
			// String token = request.getParameter("token");
			// User user = Verification.getUser(token, redisUtil);
			AutomaticOrder automaticOrder = new AutomaticOrder();
			automaticOrder.setId(UuidUtil.get32UUID());
			String num = automaticOrderService.generateOrderNo(automaticOrder);
			String customerId = request.getParameter("customerId");
			String goodstype = request.getParameter("goodstype");
			String orgin = null;
			if (goodstype != null) {
				if (goodstype.equals("0")) {
					orgin = "东南亚货";
				} else if (goodstype.equals("1")) {
					orgin = "西货";
				}
			}
			String inport = request.getParameter("entryPortId");
			OrderImport orderImport = automaticOrderService.findcustomerId(customerId, orgin, inport);
			Map<String, Object> list = new HashMap<String, Object>();
			if (orderImport != null) {
				list.put("salesman", orderImport.getSalesman());
				list.put("salesmanId", orderImport.getSalesmanId());
				list.put("salesmanTel", orderImport.getSalesmanTel());
				list.put("supportStaff", orderImport.getSupportStaff());
				list.put("supportStaffId", orderImport.getSupportStaffId());
				list.put("supportStaffTel", orderImport.getSupportStaffTel());
				list.put("goodstype", orderImport.getGoodstype());
			} else {
				if ("".equals(goodstype) || goodstype == null) {
					list.put("salesman", "常贝贝");
					list.put("salesmanId", "168");
					list.put("salesmanTel", "25157940");

				} else {
					list.put("salesman", "鲁凤鸣");
					list.put("salesmanId", "57");
					list.put("salesmanTel", "25915016");
				}
				list.put("supportStaff", "林衡");
				list.put("supportStaffId", "169");
				list.put("supportStaffTel", "075525159727");
			}
			list.put("id", automaticOrder.getId());
			list.put("orderNo", num);
			ar.setSucceed(list);
			ar.setRes(1);
			return ar;
		} catch (NumberFormatException e) {
			return new AjaxRes(Const.FAIL, "参数错误！！");
		} catch (UserException e) {
			e.printStackTrace();
			return new AjaxRes(Const.FAIL, "令牌为空！");
		} catch (Exception e) {
			ar.setResMsg(Const.DO_FAIL);
			ar.setRes(-1);
			e.printStackTrace();
		}
		return ar;
	}

	@RequestMapping(value = "/establishOrderHead2")
	@ResponseBody
	public AjaxRes establishOrderHead2(HttpServletRequest request) {
		AjaxRes ar = new AjaxRes();
		try {
			// String token = request.getParameter("token");
			// User user = Verification.getUser(token, redisUtil);
			String customerId = request.getParameter("customerId");
			String inport = request.getParameter("entryPortId");
			String goodstype = request.getParameter("goodstype");
			String orgin = null;
			if (goodstype != null) {
				if (goodstype.equals("0")) {
					orgin = "东南亚货";
				} else if (goodstype.equals("1")) {
					orgin = "西货";
				}
			}
			OrderImport orderImport = automaticOrderService.findcustomerId(customerId, orgin, inport);
			Map<String, Object> list = new HashMap<String, Object>();
			if (orderImport != null) {
				list.put("salesman", orderImport.getSalesman());
				list.put("salesmanId", orderImport.getSalesmanId());
				list.put("salesmanTel", orderImport.getSalesmanTel());
				list.put("supportStaff", orderImport.getSupportStaff());
				list.put("supportStaffId", orderImport.getSupportStaffId());
				list.put("supportStaffTel", orderImport.getSupportStaffTel());
				list.put("goodstype", orderImport.getGoodstype());
			} else {
				if ("".equals(goodstype) || goodstype == null) {
					list.put("salesman", "常贝贝");
					list.put("salesmanId", "168");
					list.put("salesmanTel", "25157940");

				} else {
					list.put("salesman", "鲁凤鸣");
					list.put("salesmanId", "57");
					list.put("salesmanTel", "25915016");
				}
				list.put("supportStaff", "林衡");
				list.put("supportStaffId", "169");
				list.put("supportStaffTel", "075525159727");

			}
			ar.setSucceed(list);
			ar.setRes(1);
			return ar;
		} catch (NumberFormatException e) {
			return new AjaxRes(Const.FAIL, "参数错误！！");
		} catch (UserException e) {
			e.printStackTrace();
			return new AjaxRes(Const.FAIL, "令牌为空！");
		} catch (Exception e) {
			ar.setResMsg(Const.DO_FAIL);
			ar.setRes(-1);
			e.printStackTrace();
		}
		return ar;
	}

	@RequestMapping(value = "/addRemoteOrder", method = RequestMethod.POST)
	public AjaxRes addRemoteOrder(HttpServletRequest request, OrderImport orderImport) {

		String token = null;
		User user = null;
		try {
			String json = request.getParameter("json");
			OrderStatusHistory history = new OrderStatusHistory();
			if ("".equals(json) || json == null) {
				token = request.getParameter("token");
				user = Verification.getUser(token, redisUtil);
				orderImport.setUpdaterId(user.getId());
				orderImport.setUpdateTime(new Date());
			} else {
				orderImport = JSON.parseObject(json, OrderImport.class);
				if(org.springframework.util.StringUtils.isEmpty(orderImport.getWorklistNo())){
					return new AjaxRes(Const.FAIL, "工作单号不能为空！");
				}
				if(org.springframework.util.StringUtils.isEmpty(orderImport.getCreatorId())){
					return new AjaxRes(Const.FAIL, "创建人Id不能为空！");
				}
				int orderCount = orderImportService.findOrderNoCount(orderImport.getWorklistNo());
				if(orderCount > 0){
					return new AjaxRes(Const.FAIL, "订单号["+ orderImport.getWorklistNo() +"]已存在！");
				}
				orderImport.setId(UuidUtil.get32UUID());
				orderImport.setCreatetime(new Date());
				orderImport.setDelFlag(0);
			}
			orderImportService.insert(orderImport);
			Transaction.commit(transactionManager);
			return new AjaxRes(2, "订单新增成功。", orderImport.getId());
		} catch (NumberFormatException e) {
			return new AjaxRes(Const.FAIL, "参数错误！！");
		} catch (UserException e) {
			e.printStackTrace();
			return new AjaxRes(Const.FAIL, "令牌为空！");
		} catch (Exception e) {
			e.printStackTrace();
			Transaction.rollback(transactionManager);
			return new AjaxRes(Const.FAIL, "新增订单信息失败，出现异常");
		}

	}

	public static void main(String[] args) {
		EmailDemo dome = new EmailDemo();
		dome.send("18124780404@163.com", "海运提醒", "1111" + "<br/><br/>" + "<p>22222</p>");
	}
}
