package cn.szag.oms.manager.controller;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import cn.jiguang.common.resp.APIConnectionException;
import cn.jiguang.common.resp.APIRequestException;
import cn.jpush.api.JPushClient;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.PushPayload;
import cn.szag.oms.manager.common.ajax.AjaxRes;
import cn.szag.oms.manager.common.domain.manager.AutomaticOrder;
import cn.szag.oms.manager.common.domain.manager.CustomerAccount;
import cn.szag.oms.manager.common.domain.manager.CustomerServicerelation;
import cn.szag.oms.manager.common.domain.manager.EShippinInfoDate;
import cn.szag.oms.manager.common.domain.manager.ManagerNotice;
import cn.szag.oms.manager.common.domain.manager.Messagelog;
import cn.szag.oms.manager.common.domain.manager.OrderAttachment;
import cn.szag.oms.manager.common.domain.manager.OrderExport;
import cn.szag.oms.manager.common.domain.manager.OrderExportShippingInfo;
import cn.szag.oms.manager.common.domain.manager.OrderImport;
import cn.szag.oms.manager.common.domain.manager.OrderShippingInfo;
import cn.szag.oms.manager.common.domain.manager.OrderStatusHistory;
import cn.szag.oms.manager.common.domain.manager.OrderWorklistStatus;
import cn.szag.oms.manager.common.domain.manager.User;
import cn.szag.oms.manager.common.enums.EOrderStatusEnum;
import cn.szag.oms.manager.common.enums.EScheduleStatusEnum;
import cn.szag.oms.manager.common.enums.InformEnum;
import cn.szag.oms.manager.common.enums.OrderStatusEnum;
import cn.szag.oms.manager.common.enums.ScheduleStatusEnum;
import cn.szag.oms.manager.common.page.Page;
import cn.szag.oms.manager.common.utils.Activemq;
import cn.szag.oms.manager.common.utils.Const;
import cn.szag.oms.manager.common.utils.EmailDemo;
import cn.szag.oms.manager.common.utils.HttpRequestUtil;
import cn.szag.oms.manager.common.utils.HttpUtils;
import cn.szag.oms.manager.common.utils.MessageXLS;
import cn.szag.oms.manager.common.utils.PushExample;
import cn.szag.oms.manager.common.utils.RedisUtil;
import cn.szag.oms.manager.common.utils.Transaction;
import cn.szag.oms.manager.common.utils.Url;
import cn.szag.oms.manager.common.utils.UserException;
import cn.szag.oms.manager.common.utils.UuidUtil;
import cn.szag.oms.manager.common.utils.Verification;
import cn.szag.oms.manager.common.utils.wechat.Template;
import cn.szag.oms.manager.common.utils.wechat.WeChatExploitMessage;
import cn.szag.oms.manager.common.utils.wechat.WeChatUtil;
import cn.szag.oms.manager.service.AutomaticOrderService;
import cn.szag.oms.manager.service.CusUserService;
import cn.szag.oms.manager.service.CustomerServicerelationService;
import cn.szag.oms.manager.service.ManagerNoticeService;
import cn.szag.oms.manager.service.MessagelogService;
import cn.szag.oms.manager.service.ModuleManagerService;
import cn.szag.oms.manager.service.OrderAttachmentService;
import cn.szag.oms.manager.service.OrderExportService;
import cn.szag.oms.manager.service.OrderExportShippingInfoService;
import cn.szag.oms.manager.service.OrderStatusHistoryService;
import cn.szag.oms.manager.service.OrderWorklistStatusService;
import cn.szag.oms.manager.service.UserLoginService;

/**
 * 
 * @ClassName: OrderEController
 * @Description: TODO
 * @author dengyanghao
 * @date 2019年11月12日 上午9:44:16
 */
@Controller
@RequestMapping(value = "/orderE")
public class OrderEController {
	@Autowired
	private OrderExportService orderExportService;// 出口
	@Autowired
	private OrderWorklistStatusService orderWorklistStatusService;// 集装箱
	@Autowired
	private OrderExportShippingInfoService orderExportShippingInfoService;// 出口海运
	@Autowired
	private RedisUtil redisUtil;
	@Autowired
	private ManagerNoticeService managerNoticeService;// 业务消息中心
	@Autowired
	private CusUserService cusUserService;// 客户联系人
	@Autowired
	private OrderStatusHistoryService orderStatusHistoryService;// 订单操作历史
	@Autowired
	private PlatformTransactionManager transactionManager;
	@Autowired
	private OrderAttachmentService orderAttachmentService;
	@Autowired
	private AutomaticOrderService automaticOrderService;
	@Autowired
	private UserLoginService userLoginService;// 用户
	@Autowired
	private ModuleManagerService moduleManagerService;
	@Autowired
	private MessagelogService messagelogService;// 记录日志消息

	private static final String FILE_PATH = "/szag-oms-images/"; // 附件上传的位置
	// APP消息推送
	JPushClient jpushClient = new JPushClient(PushExample.MASTER_SECRET, PushExample.APP_KEY);

	EmailDemo emailDemo = new EmailDemo();// 邮箱发送

	public static String uggUrl = Url.uggUrl;

	public static String cfsUrl = Url.cfsUrl;

	public static String omsUrl = Url.omsUrl;

	@Autowired
	private CustomerServicerelationService CsService;// 客户，客服绑定

	/**
	 * 查询功能，根据用户的权限查看订单数量
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/findOrderCount", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes findOrderCount(HttpServletRequest request, Integer keyWord) {
		AjaxRes ar = new AjaxRes();
		Map<String, Object> map = new HashMap<>();
		try {
			// 获得token信息
			String token = request.getParameter("token");
			User user = Verification.getUser(token, redisUtil);
			// 获得用户的权限
			Integer dataPermission = user.getDataPermission();
			if (dataPermission == null) {
				return new AjaxRes(Const.FAIL, Const.NO_AUTHORIZED_MSG);
			}
			int totalRecord = orderExportService.findOrderCount(keyWord, user);
			map.put("totalRecord", totalRecord);
			ar.setSucceed(map, "订单数量获取成功");
		} catch (UserException e) {
			ar.setFailMsg("令牌为空！");
			ar.setRes(-1);
			e.printStackTrace();
		} catch (Exception e) {
			ar.setFailMsg("获取订单数量失败，出现异常");
			e.printStackTrace();
		}
		return ar;
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
			String token = request.getParameter("token");
			User user = Verification.getUser(token, redisUtil);
			OrderExport orderExport = new OrderExport();
			orderExport.setId(UuidUtil.get32UUID());
			String num = orderExportService.generateOrderNo(orderExport);
			Map<String, Object> list = new HashMap<String, Object>();
			list.put("id", orderExport.getId());
			list.put("orderNo", num);
			ar.setSucceed(list);
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

	/**
	 * 生成订单头部接口
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/establishOrderHead2")
	@ResponseBody
	public AjaxRes establishOrderHead2(HttpServletRequest request) {
		AjaxRes ar = new AjaxRes();
		try {
			String token = request.getParameter("token");
			User user = Verification.getUser(token, redisUtil);
			OrderExport orderExport = new OrderExport();
			orderExport.setId(UuidUtil.get32UUID());
			String customerId = request.getParameter("customerId");
			String goodstype = request.getParameter("goodsType");
			String orgin = null;
			if (goodstype != null) {
				if (goodstype.equals("0")) {
					orgin = "东南亚货";
				} else if (goodstype.equals("1")) {
					orgin = "西货";
				}
			}
			String inport = request.getParameter("departurePort");
			OrderImport orderImport = automaticOrderService.findcustomerId(customerId, orgin, inport);
			Map<String, Object> list = new HashMap<String, Object>();
			if (orderImport != null) {
				list.put("salesman", orderImport.getSalesman());
				list.put("salesmanId", orderImport.getSalesmanId());
				list.put("salesmanTel", orderImport.getSalesmanTel());
				list.put("supportStaff", orderImport.getSupportStaff());
				list.put("supportStaffId", orderImport.getSupportStaffId());
				list.put("supportStaffTel", orderImport.getSupportStaffTel());
			} else {
				if ("".equals(goodstype) || goodstype == null) {
					list.put("salesman", "168");
					list.put("salesmanId", "168");
					list.put("salesmanTel", "25157940");

				} else {
					list.put("salesman", "57");
					list.put("salesmanId", "57");
					list.put("salesmanTel", "25915016");
				}
				list.put("supportStaff", "169");
				list.put("supportStaffId", "169");
				list.put("supportStaffTel", "075525159727");
			}
			ar.setSucceed(list);
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

	/**
	 * 查询功能，根据用户的权限查看订单
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes findByPage(HttpServletRequest request, OrderExport orderExport, Page page) {
		AjaxRes ar = new AjaxRes();
		Map<String, Object> map = new HashMap<>();
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
			List<OrderExport> orderExportList = orderExportService.findByPage(user, orderExport, page);
			page.setResults(orderExportList);
			map.put("list", page);
			map.put("permitBtn", moduleManagerService.findBtn(user.getId(), Const.RESOURCES_TYPE_BUTTON, parentId));
			map.put("permitBtn2",moduleManagerService.findBtn2(user.getId(), Const.RESOURCES_TYPE_BUTTON));
			ar.setSucceed(map, "订单信息获取成功");
		} catch (UserException e) {
			ar.setFailMsg("令牌为空！");
			ar.setRes(-1);
			e.printStackTrace();
		} catch (Exception e) {
			ar.setObj(page);
			ar.setFailMsg("获取订单信息失败，出现异常");
			e.printStackTrace();
		}
		return ar;
	}

	/**
	 * 新增出口订单@Title: list @Description: TODO @param @param request @param @param
	 * orderImport @param @param page @param @return @author dengyanghao @return
	 * AjaxRes @throws
	 */
	@RequestMapping(value = "/add")
	@ResponseBody
	public AjaxRes add(HttpServletRequest request, OrderExport orderExport) {
		AjaxRes ar = new AjaxRes();
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
				// orderExport.setId(UuidUtil.get32UUID());
			} else {
				orderExport = JSON.parseObject(json, OrderExport.class);
				orderExport.setCusAdviceDate(new Date());
				orderExportService.insertSelective(orderExport);
				// 订单状态历史
				history = new OrderStatusHistory();
				history.setId(UuidUtil.get32UUID());
				history.setOrderId(orderExport.getId());
				history.setStatus(orderExport.getOrderStatus());
				history.setCreator(orderExport.getCreator());
				history.setCreatetime(new Date());
				history.setCreatorId(orderExport.getCreatorId());
				history.setIsAvailable(1);
				orderStatusHistoryService.insertSelective(history);
				if(!StringUtils.isEmpty(orderExport.getCompanyId())){
					if (CsService.findCusomerIs(orderExport.getCompanyId()) == 0) {// 如果该客户没有绑定过客服和业务员，将绑定当前客服和业务员
						csl.setCreator(orderExport.getCreator());
						csl.setCustomerId(orderExport.getCompanyId());
						csl.setSalesmanId(Integer.parseInt(orderExport.getSalesman()));
						csl.setId(UuidUtil.get32UUID());
						csl.setServiceId(Integer.parseInt(orderExport.getSupportStaff()));
						if (orderExport.getGoodsType().equals("1")) {
							csl.setOrigin("西货");
						} else {
							csl.setOrigin("东南亚货");
						}
						CsService.insert(csl);
					}
				}
				return new AjaxRes(Const.SUCCEED, "订单添加成功");
			}
			// int count =
			// orderExportService.findByBookingNo(orderExport.getBookingNo(),
			// null);
			// if (count > 0) {
			// return new AjaxRes(Const.FAIL, "订舱号已存在");
			// }
			// 新增订单信息
			orderExport.setCreatorId(user.getId());
			orderExport.setCreator(user.getUsername());
			orderExport.setCreatetime(new Date());
			orderExport.setCreatorTel(user.getTel());
			orderExport.setCusAdviceDate(new Date());
			orderExport.setOrderSource("2");
			if (CsService.findCusomerIs(orderExport.getCompanyId()) == 0) {// 如果该客户没有绑定过客服和业务员，将绑定当前客服和业务员
				csl.setCreator(orderExport.getCreator());
				csl.setCustomerId(orderExport.getCompanyId());
				csl.setSalesmanId(Integer.parseInt(orderExport.getSalesman()));
				csl.setId(UuidUtil.get32UUID());
				csl.setServiceId(Integer.parseInt(orderExport.getSupportStaff()));
				if (orderExport.getGoodsType().equals("1")) {
					csl.setOrigin("西货");
				} else {
					csl.setOrigin("东南亚货");
				}
				CsService.insert(csl);
			}
			orderExportService.insertSelective(orderExport);
			// 新增操作历史
			history = new OrderStatusHistory();
			history.setId(UuidUtil.get32UUID());
			history.setOrderId(orderExport.getId());
			history.setStatus(orderExport.getOrderStatus());
			history.setCreator(orderExport.getCreator());
			history.setCreatetime(new Date());
			history.setIsAvailable(1);
			history.setCreatorId(orderExport.getCreatorId());
			orderStatusHistoryService.insertSelective(history);
			ar.setSucceedMsg("订单新增成功");
			Transaction.commit(transactionManager);
		} catch (NumberFormatException e) {
			ar.setFailMsg("参数错误！");
		} catch (UserException e) {
			ar.setFailMsg("令牌为空！");
			ar.setRes(-1);
			e.printStackTrace();
		} catch (Exception e) {
			Transaction.rollback(transactionManager);
			ar.setFailMsg("订单新增失败，出现异常");
			e.printStackTrace();
		}
		return ar;
	}

	/**
	 * 修改出口订单 @Title: list @Description: TODO @param @param
	 * request @param @param orderImport @param @param
	 * page @param @return @author dengyanghao @return AjaxRes @throws
	 */
	@RequestMapping(value = "/edit")
	@ResponseBody
	public AjaxRes editOrder(HttpServletRequest request, OrderExport orderExport) {
		AjaxRes ar = new AjaxRes();
		String token = null;
		User user = null;
		try {
			String json = request.getParameter("json");
			OrderStatusHistory history = null;
			if ("".equals(json) || json == null) {
				token = request.getParameter("token");
				user = Verification.getUser(token, redisUtil);
			} else {
				orderExport = JSON.parseObject(json, OrderExport.class);
				orderExportService.updateByPrimaryKeySelective(orderExport);
				// 订单状态历史
				history = new OrderStatusHistory();
				history.setId(UuidUtil.get32UUID());
				history.setOrderId(orderExport.getId());
				history.setStatus(orderExport.getOrderStatus());
				history.setCreator(orderExport.getCreator());
				history.setCreatetime(new Date());
				history.setCreatorId(orderExport.getCreatorId());
				history.setIsAvailable(1);
				orderStatusHistoryService.insertSelective(history);
				Transaction.commit(transactionManager);
				return new AjaxRes(Const.SUCCEED, "订单修改成功");
			}
			// int count =
			// orderExportService.findByBookingNo(orderExport.getBookingNo(),
			// orderExport.getId());
			// if (count > 0) {
			// return new AjaxRes(Const.FAIL, "订舱号已存在");
			// }
			if (orderExport.getOrderStatus() == OrderStatusEnum.ALREADY_AUDIT.getCode()) {// 已审核
				orderExport.setCheckTime(new Date());
			} else if (orderExport.getOrderStatus() == OrderStatusEnum.STAY_POLISHING.getCode()) {// 补料
				orderExport.setSupplementTime(new Date());
			} else if (orderExport.getOrderStatus() == OrderStatusEnum.ALREADY_TERMINATION.getCode()) {
				// 终止人和终止时间
				orderExport.setClosingPer(user.getUsername());
				orderExport.setClosingDate(new Date());
			}

			OrderExport oe = orderExportService.selectByPrimaryKey(orderExport.getId());
			if (null == oe) {
				orderExport.setCreator(user.getUsername());
				orderExport.setCreatorId(user.getId());
				orderExport.setCreatetime(new Date());
			}
			if ("1".equals(Url.carryOut) && orderExport.getOrderStatus() == 4) {
				Activemq.sned(orderExport, "orderECamel");// 发送mq队列
				return new AjaxRes(Const.SUCCEED, "订单修改成功");
			}
			if (null == oe) {
				orderExportService.insertSelective(orderExport);
			} else {
				orderExportService.updateByPrimaryKeySelective(orderExport);
			}
			if (orderExport.getOrderStatus() == 4) {
				OrderExport orderJson = orderExportService.selectByPrimaryKey(orderExport.getId());
				User user2 = userLoginService.selectByPrimaryKey(orderExport.getSalesman());// 业务员
				if (null != user2.getDpname()) {// 业务员
					orderJson.setSalesmanDepa(user2.getDpname());
				}
				User user3 = userLoginService.selectByPrimaryKey(orderExport.getSupportStaff());
				if (null != user3.getDpname()) {// 客服部门
					orderJson.setSupportStaffDepa(user3.getDpname());
				}
				orderJson.setCustomerName(orderJson.getCompanyId());
				if(!StringUtils.isEmpty(orderJson.getRemark())){
					orderJson.setRemark(orderJson.getRemark().replaceAll("\\<.*?>", ""));
				}
				if(!StringUtils.isEmpty(orderJson.getHandlingSuggestion())){
					orderJson.setHandlingSuggestion(orderJson.getHandlingSuggestion().replaceAll("\\<.*?>", ""));
				}
				String json2 = JSONObject.toJSONString(orderJson);
				System.out.println("往cfs发送数据：" + json2);
				String str = HttpRequestUtil.sendPost(cfsUrl + "/addExportWorklist", "json=" + json2, false);
				System.out.println("str=" + str);
				Map<String, Object> maps = (Map) JSON.parse(str);
				if (maps.get("code").equals(1)) {
					Transaction.rollback(transactionManager);
					ar.setFailMsg("发送数据到CFS失败，请联系相关人员");
					return ar;
				} else {
					orderExport.setWorklistNo(maps.get("worklistNo") + "");
				}
			}
			addHistory(orderExport, user);

			orderExportService.updateByPrimaryKeySelective(orderExport);
			// 订单状态历史
			if (orderExport.getOrderStatus() == OrderStatusEnum.ALREADY_AUDIT.getCode()
					|| orderExport.getOrderStatus() == OrderStatusEnum.ALREADY_SEND_BACK.getCode()
					|| orderExport.getOrderStatus() == OrderStatusEnum.STAY_POLISHING.getCode()) {
				OrderExport export = orderExportService.selectByPrimaryKey(orderExport.getId());
				CustomerAccount customerAccount = new CustomerAccount();
				if (export.getOrderSource().equals("2")) {//判断是否是客户或代理商
					user = userLoginService.selectByPrimaryKey(export.getCreatorId());
				} else {
					customerAccount = cusUserService.selectByPrimaryKey(export.getCreatorId());
					user = null;
				}
				if (user != null) {
					customerAccount.setJpushId(user.getJpushId());// 极光推送
					customerAccount.setEmail(user.getEmail());// 邮箱
					customerAccount.setUsername(user.getUsername());
					customerAccount.setId(user.getId());
					customerAccount.setStatus(user.getPushStatus());
				}
				pushMessage(orderExport, customerAccount);
			}
			ar.setSucceedMsg("订单修改成功");
			Transaction.commit(transactionManager);
		} catch (NumberFormatException e) {
			ar.setFailMsg("参数错误！");
		} catch (UserException e) {
			ar.setFailMsg("令牌为空！");
			ar.setRes(-1);
			e.printStackTrace();
		} catch (Exception e) {
			Transaction.rollback(transactionManager);
			ar.setFailMsg("订单修改失败，出现异常");
			e.printStackTrace();
		}
		return ar;
	}

	/**
	 * 修改出口订单 @Title: list @Description: TODO @param @param
	 * request @param @param orderImport @param @param
	 * page @param @return @author dengyanghao @return AjaxRes @throws
	 */
	@RequestMapping(value = "/edit2")
	@ResponseBody
	public AjaxRes editOrder2(HttpServletRequest request, OrderExport orderExport) {
		AjaxRes ar = new AjaxRes();
		String token = null;
		User user = null;
		try {
			String json = request.getParameter("json");
			OrderStatusHistory history = null;
			if ("".equals(json) || json == null) {
				token = request.getParameter("token");
				user = Verification.getUser(token, redisUtil);
			} else {
				orderExport = JSON.parseObject(json, OrderExport.class);
				orderExportService.updateByPrimaryKeySelective(orderExport);
				// 订单状态历史
				history = new OrderStatusHistory();
				history.setId(UuidUtil.get32UUID());
				history.setOrderId(orderExport.getId());
				history.setStatus(orderExport.getOrderStatus());
				history.setCreator(orderExport.getCreator());
				history.setCreatetime(new Date());
				history.setCreatorId(orderExport.getCreatorId());
				history.setIsAvailable(1);
				orderStatusHistoryService.insertSelective(history);
				Transaction.commit(transactionManager);
				return new AjaxRes(Const.SUCCEED, "订单修改成功");
			}
			// int count =
			// orderExportService.findByBookingNo(orderExport.getBookingNo(),
			// orderExport.getId());
			// if (count > 0) {
			// return new AjaxRes(Const.FAIL, "订舱号已存在");
			// }
			orderExport.setCreatorId(user.getId());
			orderExport.setCreator(user.getUsername());
			orderExport.setCreatetime(new Date());
			Activemq.sned(orderExport, "orderECamel");// 发送MQ队列
			ar.setSucceedMsg("订单修改成功");
		} catch (NumberFormatException e) {
			ar.setFailMsg("参数错误！");
		} catch (UserException e) {
			ar.setFailMsg("令牌为空！");
			ar.setRes(-1);
			e.printStackTrace();
		} catch (Exception e) {
			Transaction.rollback(transactionManager);
			ar.setFailMsg("订单修改失败，出现异常");
			e.printStackTrace();
		}
		return ar;
	}

	/**
	 * 修改出口订单 @Title: list @Description: TODO @param @param
	 * request @param @param orderImport @param @param
	 * page @param @return @author dengyanghao @return AjaxRes @throws
	 */
	@RequestMapping(value = "/edit3")
	@ResponseBody
	public AjaxRes editOrder3(String json) {
		AjaxRes ar = new AjaxRes();
		User user = new User();
		OrderExport orderExport = null;
		try {
			orderExport = JSON.parseObject(json, OrderExport.class);
			// int count =
			// orderExportService.findByBookingNo(orderExport.getBookingNo(),
			// orderExport.getId());
			// if (count > 0) {
			// return new AjaxRes(Const.FAIL, "订舱号已存在");
			// }
			if (orderExport.getOrderStatus() == OrderStatusEnum.ALREADY_AUDIT.getCode()) {// 已审核
				orderExport.setCheckTime(new Date());
			}
			orderExportService.insertSelective(orderExport);
			if (orderExport.getOrderStatus() == 4) {
				OrderExport orderJson = orderExportService.selectByPrimaryKey(orderExport.getId());
				User user2 = userLoginService.selectByPrimaryKey(orderExport.getSalesman());// 业务员
				if (null != user2.getDpname()) {// 业务员
					orderJson.setSalesmanDepa(user2.getDpname());
				}
				User user3 = userLoginService.selectByPrimaryKey(orderExport.getSupportStaff());
				if (null != user3.getDpname()) {// 客服部门
					orderJson.setSupportStaffDepa(user3.getDpname());
				}
				if(!StringUtils.isEmpty(orderJson.getRemark())){
					orderJson.setRemark(orderJson.getRemark().replaceAll("\\<.*?>", ""));
				}
				if(!StringUtils.isEmpty(orderJson.getHandlingSuggestion())){
					orderJson.setHandlingSuggestion(orderJson.getHandlingSuggestion().replaceAll("\\<.*?>", ""));
				}
				String json2 = JSONObject.toJSONString(orderJson);
				System.out.println("往cfs发送数据：" + json2);
				String str = HttpRequestUtil.sendPost(cfsUrl + "/addExportWorklist", "json=" + json2, false);
				System.out.println("str=" + str);
				Map<String, Object> maps = (Map) JSON.parse(str);
				if (maps.get("code").equals(1)) {
					Transaction.rollback(transactionManager);
					ar.setFailMsg("发送数据到CFS失败，请联系相关人员");
					return ar;
				} else {
					orderExport.setWorklistNo(maps.get("worklistNo") + "");
				}
			}
			user.setId(orderExport.getCreatorId());
			user.setUsername(orderExport.getCreator());
			addHistory(orderExport, user);
			orderExportService.updateByPrimaryKeySelective(orderExport);
			// 订单状态历史
			if (orderExport.getOrderStatus() == OrderStatusEnum.ALREADY_AUDIT.getCode()) {
				OrderExport export = orderExportService.selectByPrimaryKey(orderExport.getId());
				CustomerAccount customerAccount = new CustomerAccount();
				user = userLoginService.selectByPrimaryKey(export.getCreatorId());
				if (user != null) {
					customerAccount.setJpushId(user.getJpushId());// 极光推送
					customerAccount.setEmail(user.getEmail());// 邮箱
					customerAccount.setUsername(user.getUsername());
					customerAccount.setId(user.getId());
					customerAccount.setStatus(user.getPushStatus());
				}
				pushMessage(orderExport, customerAccount);
			}
			ar.setSucceedMsg("订单修改成功");
			Transaction.commit(transactionManager);
		} catch (NumberFormatException e) {
			ar.setFailMsg("参数错误！");
		} catch (UserException e) {
			ar.setFailMsg("令牌为空！");
			ar.setRes(-1);
			e.printStackTrace();
		} catch (Exception e) {
			Transaction.rollback(transactionManager);
			OrderExport o = new OrderExport();
			o.setId(orderExport.getId());
			o.setDisposeStatus(3);
			orderExportService.updateByPrimaryKeySelective(o);// 记录发送失败
			Transaction.commit(transactionManager);// 重新提交
			ar.setFailMsg("订单修改失败，出现异常");
			e.printStackTrace();
		}
		return ar;
	}

	/**
	 * 修改订单 写入 b库 在order_orderExport
	 * 
	 * @return
	 */
	@RequestMapping(value = "/returnWriteId", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes returnWriteId(HttpServletRequest request, OrderExport orderExport) {

		String token = null;
		User user = null;
		try {
			String json = request.getParameter("json");
			OrderStatusHistory history = new OrderStatusHistory();
			if ("".equals(json) || json == null) {
				token = request.getParameter("token");
				user = Verification.getUser(token, redisUtil);
				orderExport.setUpdateId(user.getId());
				orderExport.setUpdateTime(new Date());
			} else {
				orderExport = JSON.parseObject(json, OrderExport.class);
				orderExportService.updateByPrimaryKeySelective(orderExport);
				return new AjaxRes(Const.SUCCEED, "订单修改成功");
			}
			if(!StringUtils.isEmpty(orderExport.getCustomerId())){
				int i = CsService.findCusomerIs(orderExport.getCustomerId());
				if (i == 0) {// 如果当前公司没有绑定客服，则为当前公司绑定一个新客服，用于当前公司以后的单都分配给这个客服和业务员
					CustomerServicerelation customerService = new CustomerServicerelation();
					customerService.setId(UuidUtil.get32UUID());
					customerService.setCustomerId(orderExport.getCustomerId());
					customerService.setSalesmanId(Integer.valueOf(orderExport.getSalesman()));
					customerService.setServiceId(Integer.valueOf(orderExport.getSupportStaff()));
					CsService.insert(customerService);

				}
			}
			orderExportService.updateByPrimaryKeySelective(orderExport);
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
	 * 查询出口订单 @Title: list @Description: TODO @param @param
	 * request @param @param orderImport @param @param
	 * page @param @return @author dengyanghao @return AjaxRes @throws
	 */
	@RequestMapping(value = "/find")
	@ResponseBody
	public AjaxRes findOrder(HttpServletRequest request, String id) {
		AjaxRes ar = new AjaxRes();

		try {
			String token = request.getParameter("token");
			User user = Verification.getUser(token, redisUtil);
			OrderExport o = orderExportService.find(id);
			if(o!=null){
				o.setHandlingSuggestion(null);
			}
			ar.setSucceed(o, "获取订单详情成功");
		} catch (NumberFormatException e) {
			ar.setFailMsg("参数错误！");
		} catch (UserException e) {
			ar.setFailMsg("令牌为空！");
			ar.setRes(-1);
			e.printStackTrace();
		} catch (Exception e) {
			ar.setFailMsg("获取订单详情失败，出现异常");
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
		OrderExport orderExport = orderExportService.selectByPrimaryKey(orderAttachment.getOrderId());
		if (orderAttachment.getOrderId() == null) {
			return new AjaxRes(Const.FAIL, "订单不能为空");
		}
		if (null == orderExport) {
			return new AjaxRes(Const.FAIL, "该订单不存在");
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
				orderAttachment.setFileUrl(savePath);
				orderAttachment.setDelFlag(0);
				orderAttachment.setLastUpdateTime(new Date());
				orderAttachment.setLastUpdator(user.getUsername());
				orderAttachment.setLastUpdatorId(user.getId());
				orderAttachment.setAttachmentName(filename);
				/*
				 * if (orderExport.getOrderStatus() == 2) {
				 * orderExport.setOrderStatus(3);
				 * orderExportService.updateByPrimaryKeySelective(orderImport);
				 * }
				 */
				orderAttachmentService.updateByPrimaryKeySelective(orderAttachment);
				if (orderExport.getOrderSource() != "2") {
					String json2 = JSONObject.toJSONString(orderAttachment);
					System.out.println(json2);
					HttpRequestUtil.sendPost(omsUrl + "/orderI/UpadteAttachmen", "json=" + json2, false);
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
				String httpUrl = omsUrl + "/orderI/downAtter?id=" + id;
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
	 * 修改出口订单 @Title: list @Description: TODO @param @param
	 * request @param @param orderImport @param @param
	 * page @param @return @author dengyanghao @return AjaxRes @throws
	 */
	@RequestMapping(value = "/del")
	@ResponseBody
	public AjaxRes delOrder(HttpServletRequest request, OrderExport orderExport) {
		AjaxRes ar = new AjaxRes();
		String token = null;
		User user = null;
		try {
			String json = request.getParameter("json");
			OrderStatusHistory history = null;
			if ("".equals(json) || json == null) {
				token = request.getParameter("token");
				user = Verification.getUser(token, redisUtil);
			} else {
				orderExport = JSON.parseObject(json, OrderExport.class);
				orderExportService.updateByPrimaryKey(orderExport);
				return new AjaxRes(Const.SUCCEED, "订单删除成功");
			}
			OrderExport o = orderExportService.selectByPrimaryKey(orderExport.getId());
			if (null != o.getOrderStatus()) {
				if (o.getOrderStatus().equals(EOrderStatusEnum.ALREADY_AUDIT)) {
					return new AjaxRes(Const.FAIL, "订单已审核不能删除");
				}
				if (o.getOrderStatus().equals(EOrderStatusEnum.ALREADY_SEND_BACK)) {
					return new AjaxRes(Const.FAIL, "订单已退回不能删除");
				}
				if (o.getOrderStatus().equals(EOrderStatusEnum.ALREADY_TERMINATION)) {
					return new AjaxRes(Const.FAIL, "订单已终止不能删除");
				}
			}
			orderExport.setDelFlag(1);
			orderExportService.updateByPrimaryKeySelective(orderExport);
			ar.setSucceed("订单删除成功");
			Transaction.commit(transactionManager);
		} catch (NumberFormatException e) {
			ar.setFailMsg("参数错误！");
		} catch (UserException e) {
			ar.setFailMsg("令牌为空！");
			ar.setRes(-1);
			e.printStackTrace();
		} catch (Exception e) {
			Transaction.rollback(transactionManager);
			ar.setFailMsg("订单删除失败，出现异常");
			e.printStackTrace();
		}
		return ar;
	}

	/**
	 * 集装箱查询 @Title: list @Description: TODO @param @param request @param @param
	 * orderImport @param @param page @param @return @author dengyanghao @return
	 * AjaxRes @throws
	 */
	@RequestMapping(value = "/worklist")
	@ResponseBody
	public AjaxRes worklist(HttpServletRequest request, OrderWorklistStatus ows, Page page) {
		AjaxRes ar = new AjaxRes();
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			String token = request.getParameter("token");
			String parentId = request.getParameter("parentId");
			User user = Verification.getUser(token, redisUtil);
			List<OrderWorklistStatus> orderWorkList = orderWorklistStatusService.findByPageE(ows, user, page);
			page.setResults(orderWorkList);
			map.put("list", page);
			map.put("permitBtn", moduleManagerService.findBtn(user.getId(), Const.RESOURCES_TYPE_BUTTON, parentId));
			map.put("permitBtn2",moduleManagerService.findBtn2(user.getId(), Const.RESOURCES_TYPE_BUTTON));
			ar.setSucceed(map, "出口订单列表获取成功");
		} catch (NumberFormatException e) {
			ar.setFailMsg("参数错误！");
		} catch (UserException e) {
			ar.setFailMsg("令牌为空！");
			ar.setRes(-1);
			e.printStackTrace();
		} catch (Exception e) {
			ar.setFailMsg("出口订单列表获取失败，出现异常");
			e.printStackTrace();
		}
		return ar;
	}

	/**
	 * 集装箱查询 @Title: list @Description: TODO @param @param request @param @param
	 * orderImport @param @param page @param @return @author dengyanghao @return
	 * AjaxRes @throws
	 */
	@RequestMapping(value = "/worklistA")
	@ResponseBody
	public AjaxRes worklistA(HttpServletRequest request, OrderWorklistStatus ows, Page page) {
		AjaxRes ar = new AjaxRes();
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			String token = request.getParameter("token");
			String parentId = request.getParameter("parentId");
			User user = Verification.getUser(token, redisUtil);
			List<OrderWorklistStatus> orderWorkList = orderWorklistStatusService.findByPageMove(ows, user, page);
			page.setResults(orderWorkList);
			ar.setSucceed(page, "出口订单列表获取成功");
		} catch (NumberFormatException e) {
			ar.setFailMsg("参数错误！");
		} catch (UserException e) {
			ar.setFailMsg("令牌为空！");
			ar.setRes(-1);
			e.printStackTrace();
		} catch (Exception e) {
			ar.setFailMsg("出口订单列表获取失败，出现异常");
			e.printStackTrace();
		}
		return ar;
	}

	/**
	 * 进度时间查询@Title: list @Description: TODO @param @param request @param @param
	 * orderImport @param @param page @param @return @author dengyanghao @return
	 * AjaxRes @throws
	 */
	@RequestMapping(value = "/scheduleTime")
	@ResponseBody
	public AjaxRes scheduleTime(HttpServletRequest request, String orderId, String containerId) {
		AjaxRes ar = new AjaxRes();
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			String token = request.getParameter("token");
			User user = Verification.getUser(token, redisUtil);
			EShippinInfoDate dateObj = orderWorklistStatusService.findExportScheduleDate(orderId, containerId);
			ar.setSucceed(dateObj, "进度时间信息获取成功");
		} catch (NumberFormatException e) {
			ar.setFailMsg("参数错误！");
		} catch (UserException e) {
			ar.setFailMsg("令牌为空！");
			ar.setRes(-1);
			e.printStackTrace();
		} catch (Exception e) {
			ar.setFailMsg("进度时间信息获取失败，出现异常");
			e.printStackTrace();
		}
		return ar;
	}

	/**
	 * 查询海运信息 @Title: list @Description: TODO @param @param
	 * request @param @param orderImport @param @param
	 * page @param @return @author dengyanghao @return AjaxRes @throws
	 */
	@RequestMapping(value = "/shipping")
	@ResponseBody
	public AjaxRes shipping(HttpServletRequest request, String orderId, String containerId) {
		AjaxRes ar = new AjaxRes();
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			String token = request.getParameter("token");
			User user = Verification.getUser(token, redisUtil);
			OrderExportShippingInfo orderExportShippingInfo = orderExportShippingInfoService.find(orderId, containerId);
			ar.setSucceed(orderExportShippingInfo, "出口海运信息获取成功");
		} catch (NumberFormatException e) {
			ar.setFailMsg("参数错误！");
		} catch (UserException e) {
			ar.setFailMsg("令牌为空！");
			ar.setRes(-1);
			e.printStackTrace();
		} catch (Exception e) {
			ar.setFailMsg("出口海运信息获取失败，出现异常");
			e.printStackTrace();
		}
		return ar;
	}

	/**
	 * 新增海运信息 @Title: list @Description: TODO @param @param
	 * request @param @param orderImport @param @param
	 * page @param @return @author dengyanghao @return AjaxRes @throws
	 */
	@RequestMapping(value = "/addShipping")
	@ResponseBody
	public AjaxRes addShipping(HttpServletRequest request, OrderExportShippingInfo orderExportShippingInfo) {
		AjaxRes ar = new AjaxRes();
		String token = null;
		User user = null;
		try {
			String json = request.getParameter("json");
			if ("".equals(json) || json == null) {
				token = request.getParameter("token");
				user = Verification.getUser(token, redisUtil);
				if (null == orderExportShippingInfo.getId()) {
					orderExportShippingInfo.setId(UuidUtil.get32UUID());
				}
			} else {
				System.out.println("海运json=" + json);
				orderExportShippingInfo = JSON.parseObject(json, OrderExportShippingInfo.class);
				if (orderExportShippingInfo.getExpectArriveTime() != null) {
					orderExportShippingInfo.setEta(orderExportShippingInfo.getExpectArriveTime());
				}
			}
			OrderExportShippingInfo o = orderExportShippingInfoService
					.selectByPrimaryKey(orderExportShippingInfo.getId());
			List<OrderWorklistStatus> workList = new ArrayList<OrderWorklistStatus>();
			boolean flag = false;
			if (null != o && null != orderExportShippingInfo.getAtd() && null == o.getAtd()) {// 判断当前数据库里这条海运信息没有实际离港时间，而这条记录却又传递离港时间
				workList = orderWorklistStatusService.select(o.getOrderId());
				flag = true;
			}
			if (flag) {
				OrderExport orderExport = orderExportService.selectByPrimaryKey(o.getOrderId());
				if (null != orderExport) {
					System.out.println("workList=" + workList.size());
					for (OrderWorklistStatus orderWorklistStatus : workList) {// 循环当前海运所有集装箱
						System.out.println("ALREADY_DEPARTURE=" + EScheduleStatusEnum.ALREADY_DEPARTURE.getCode());
						orderWorklistStatus.setScheduleStatus(EScheduleStatusEnum.ALREADY_DEPARTURE.getCode());// 离港
						System.out.println("ScheduleStatus=" + orderWorklistStatus.getScheduleStatus());
						orderWorklistStatusService.updateByPrimaryKeySelective(orderWorklistStatus);//
						ManagerNotice managerNotice = new ManagerNotice();
						List<CustomerAccount> accountList = cusUserService
								.findByCustomerId(orderExport.getCustomerName());
						Map<String, Object> map = new HashMap<>();
						map.put("orderId", orderWorklistStatus.getOrderId());
						map.put("containerId", orderWorklistStatus.getId());
						map.put("boxNo", orderWorklistStatus.getBoxNo());
						for (CustomerAccount customerAccount : accountList) {
							managerNotice.setId(UuidUtil.get32UUID());
							managerNotice.setContent(MessageXLS.returnBookingMessage(
									InformEnum.DEPARTURE_INFORM.getValue(), orderWorklistStatus.getBookingNo(),
									orderWorklistStatus.getBoxNo(), orderWorklistStatus.getProduct()));
							String title = "";
							if (orderExport.getTransportWay().equals("0")) {
								title = "海运出口";
							} else if (orderExport.getTransportWay().equals("1")) {
								title = "陆运出口";
							} else {
								title = "空运出口";
							}
							managerNotice.setOrderId(o.getOrderId());
							managerNotice.setReceiverId(customerAccount.getId());
							managerNotice.setCreatetime(new Date());
							managerNotice.setTitleType(2);
							managerNotice.setContainerId(orderWorklistStatus.getId());
							managerNotice.setTitle(title);
							/**
							 * App推送方法
							 */
							send(customerAccount, managerNotice.getContent(), title, map, 2);
							managerNoticeService.insertSelective(managerNotice);// 新增消息通知
						}
					}
				}
			}
			if (null == o) {
				orderExportShippingInfoService.insertSelective(orderExportShippingInfo);
			} else {
				orderExportShippingInfo.setLastupdate(new Date());
				orderExportShippingInfoService.updateByPrimaryKeySelective(orderExportShippingInfo);
			}
			Transaction.commit(transactionManager);
			ar.setSucceed("海运信息新增成功");
		} catch (NumberFormatException e) {
			ar.setFailMsg("参数错误！");
		} catch (UserException e) {
			ar.setFailMsg("令牌为空！");
			ar.setRes(-1);
			e.printStackTrace();
		} catch (Exception e) {
			Transaction.rollback(transactionManager);
			ar.setFailMsg("海运信息新增失败，出现异常");
			e.printStackTrace();
		}
		return ar;
	}

	/**
	 * 查询出口订单列表 @Title: list @Description: TODO @param @param
	 * request @param @param orderImport @param @param
	 * page @param @return @author dengyanghao @return AjaxRes @throws
	 */
	@RequestMapping(value = "/standardImportList")
	@ResponseBody
	public AjaxRes standardImportList(HttpServletRequest request, OrderExport orderExport, Page page) {
		AjaxRes ar = new AjaxRes();
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			String token = request.getParameter("token");
			User user = Verification.getUser(token, redisUtil);
			List<OrderExport> list = orderExportService.findByPage(user, orderExport, page);
			page.setResults(list);
			ar.setSucceed(page, "出口订单列表获取成功");
		} catch (NumberFormatException e) {
			ar.setFailMsg("参数错误！");
		} catch (UserException e) {
			ar.setFailMsg("令牌为空！");
			ar.setRes(-1);
			e.printStackTrace();
		} catch (Exception e) {
			ar.setFailMsg("出口订单列表获取失败，出现异常");
			e.printStackTrace();
		}
		return ar;
	}

	// 添加审核历史
	public void addHistory(OrderExport orderExport, User user) {
		OrderStatusHistory history = new OrderStatusHistory();
		history.setId(UuidUtil.get32UUID());
		history.setOrderId(orderExport.getId());
		history.setStatus(orderExport.getOrderStatus());
		history.setIsAvailable(1);
		history.setCreator(user.getUsername());
		history.setCreatetime(new Date());
		history.setCreatorId(user.getId());
		history.setRemark(orderExport.getHandlingSuggestion());
		orderStatusHistoryService.insertSelective(history);
	}

	public void pushMessage(OrderExport orderExports, CustomerAccount account)
			throws APIConnectionException, APIRequestException {
		OrderExport orderExport = orderExportService.selectByPrimaryKey(orderExports.getId());
		if (null != orderExports) {
			ManagerNotice managerNotice = new ManagerNotice();// 消息中心
			List<OrderWorklistStatus> wokelist = orderWorklistStatusService.select(orderExports.getId());// 集装箱
			if (wokelist.size() > 0) {
				List<String> boxId = new ArrayList<String>();
				for (OrderWorklistStatus orderWorklistStatus : wokelist) {
					boxId.add(orderWorklistStatus.getId());
				}
				String containerId = boxId.toString();
				managerNotice.setContainerId(containerId.replace("[", "").replace("]", ""));
			}
			managerNotice.setId(UuidUtil.get32UUID());
			managerNotice.setOrderId(orderExport.getId());
			managerNotice.setTitleType(1);
			managerNotice.setContent(orderExport.getHandlingSuggestion());
			managerNotice.setCreatetime(new Date());
			managerNotice.setReceiverId(account.getId());
			// managerNoticeService.insert(managerNotice);
			String title = "";
			String result = "";
			if (orderExport.getTransportWay().equals("0")) {
				title = "海运出口";
			} else if (orderExport.getTransportWay().equals("1")) {
				title = "陆运出口";
			} else if (orderExport.getTransportWay().equals("2")) {
				title = "空运出口";
			}
			if (orderExport.getOrderStatus() == EOrderStatusEnum.ALREADY_AUDIT.getCode()) {
				result = "通过";
			} else if (orderExport.getOrderStatus() == EOrderStatusEnum.ALREADY_SEND_BACK.getCode()) {
				result = "退回";
			}
			managerNotice.setTitle(title);
			managerNotice.setTitleType(2);
			String stc = "";
			if (orderExports.getOrderStatus() == 1 || orderExports.getOrderStatus() == 4) {
				String str = account.getUsername() + "您好，你有荟鲜生" + title + "的订舱号为" + orderExport.getBookingNo()
						+ "的消息提醒";
				stc = MessageXLS.returnAuditMessage(title, orderExport.getOrderNo(), orderExport.getBookingNo(),
						result);
				String context = MessageXLS.ExportAuditMessage(title, orderExport.getOrderNo(),
						orderExport.getBookingNo(), result);
				if(!StringUtils.isEmpty(stc)){
					stc += "。原因:" + managerNotice.getContent();
				}
				managerNotice.setContent(stc);
				managerNoticeService.insertSelective(managerNotice);
				/**
				 * App推送方法
				 */
				send(account, managerNotice.getContent(), title, str, context);
			}
		}
	}

	/**
	 * 用户订单下集装箱进度处理通知 @Title: send @Description: TODO @param @param
	 * customerAccount @param @param content @param @param title @param @throws
	 * APIConnectionException @param @throws APIRequestException @author
	 * dengyanghao @return void @throws
	 */
	public void send(CustomerAccount customerAccount, String content, String title, Map<String, Object> map,
			Integer type) throws APIConnectionException, APIRequestException {
		if (null != customerAccount) {
			if (customerAccount.getStatus() == 0) {// 是否开启推送
				if (null != customerAccount.getJpushId() && !"".equals(customerAccount.getJpushId())) {
					System.out.println(customerAccount.getJpushId());
					PushPayload payload = PushExample.buildPushObject_ios_audienceMore_messageWithExtras(
							customerAccount.getJpushId(), title, content);
					PushResult pu = jpushClient.sendPush(payload);
				}
			}
			if (null != customerAccount.getEmail() && !"".equals(customerAccount.getEmail())) {
				if (emailDemo.checkout(customerAccount.getEmail())) {// 校验邮箱是否正确
					emailDemo.send(customerAccount.getEmail(), title + "消息提醒", content);
				} else {
					Messagelog messageLog = new Messagelog();
					messageLog.setAccount(customerAccount.getAccount());
					messageLog.setEmail(customerAccount.getEmail());
					messageLog.setMessage(content);
					messageLog.setUserId(customerAccount.getId());
					messagelogService.insert(messageLog);
				}
			}
			if (null != customerAccount.getWechatId() && !"".equals(customerAccount.getWechatId())) {
				Template template = new Template();
				String url = "";
				if (type == 1) {
					url = Url.wechatUrl;
				} else {
					url = Url.wechatEUrl;
				}
				template.send_template_message(customerAccount.getWechatId(), WeChatExploitMessage.AuditTemplateId,
						content, map, url);
			}
		}
	}

	/**
	 * 用于订单处理通知 @Title: send @Description: TODO @param @param
	 * customerAccount @param @param content @param @param title @param @param
	 * titles @param @throws APIConnectionException @param @throws
	 * APIRequestException @author dengyanghao @return void @throws
	 */
	public void send(CustomerAccount customerAccount, String content, String title, String titles, String context)
			throws APIConnectionException, APIRequestException {
		if (null != customerAccount) {
			if (null == customerAccount.getStatus() || customerAccount.getStatus() == 0) {// 是否开启推送
				if (null != customerAccount.getJpushId() && !"".equals(customerAccount.getJpushId())) {
					PushPayload payload = PushExample.buildPushObject_ios_audienceMore_messageWithExtras(
							customerAccount.getJpushId(), title, content);
					try {
						PushResult pu = jpushClient.sendPush(payload);
					} catch (APIConnectionException | APIRequestException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			if (null != customerAccount.getEmail() && !"".equals(customerAccount.getEmail())) {
				if (emailDemo.checkout(customerAccount.getEmail())) {// 校验邮箱是否正确
					emailDemo.send(customerAccount.getEmail(), titles, content);
				} else {
					Messagelog messageLog = new Messagelog();
					messageLog.setAccount(customerAccount.getAccount());
					messageLog.setEmail(customerAccount.getEmail());
					messageLog.setMessage(content);
					messageLog.setUserId(customerAccount.getId());
					messagelogService.insert(messageLog);
				}
			}
			if (null != customerAccount.getWechatId() && !"".equals(customerAccount.getWechatId())) {
				Template template = new Template();
				template.contentTest(customerAccount.getWechatId(), context);
			}
		}
	}

	@RequestMapping(value = "/addRemoteOrder", method = RequestMethod.POST)
	public AjaxRes addRemoteOrder(HttpServletRequest request, OrderExport orderExport) {

		String token = null;
		User user = null;
		try {
			String json = request.getParameter("json");
			OrderStatusHistory history = new OrderStatusHistory();
			if ("".equals(json) || json == null) {
				token = request.getParameter("token");
				user = Verification.getUser(token, redisUtil);
				orderExport.setUpdateId(user.getId());
				orderExport.setUpdateTime(new Date());
			} else {
				orderExport = JSON.parseObject(json, OrderExport.class);
				if(org.springframework.util.StringUtils.isEmpty(orderExport.getWorklistNo())){
					return new AjaxRes(Const.FAIL, "工作单号不能为空！");
				}
				if(org.springframework.util.StringUtils.isEmpty(orderExport.getCreatorId())){
					return new AjaxRes(Const.FAIL, "创建人Id不能为空！");
				}
				int orderCount = orderExportService.findOrderNoCount(orderExport.getWorklistNo());
				if(orderCount > 0){
					return new AjaxRes(Const.FAIL, "订单号["+ orderExport.getWorklistNo() +"]已存在！");
				}
				orderExport.setId(UuidUtil.get32UUID());
				orderExport.setCreatetime(new Date());
				orderExport.setDelFlag(0);
			}
			orderExportService.insert(orderExport);
			Transaction.commit(transactionManager);
			return new AjaxRes(2, "订单新增成功。", orderExport.getId());
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
		// 获得令牌
		String accessToken = WeChatUtil.getToken(WeChatExploitMessage.AppID, WeChatExploitMessage.AppSecret)
				.getAccessToken();
	}
}
