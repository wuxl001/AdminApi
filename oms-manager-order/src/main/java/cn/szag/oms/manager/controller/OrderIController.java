package cn.szag.oms.manager.controller;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;

import cn.jiguang.common.resp.APIConnectionException;
import cn.jiguang.common.resp.APIRequestException;
import cn.jpush.api.JPushClient;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.PushPayload;
import cn.szag.oms.manager.common.ajax.AjaxRes;
import cn.szag.oms.manager.common.domain.Items;
import cn.szag.oms.manager.common.domain.manager.Attention;
import cn.szag.oms.manager.common.domain.manager.BaseBoxNoMessage;
import cn.szag.oms.manager.common.domain.manager.CustomerAccount;
import cn.szag.oms.manager.common.domain.manager.DispatchOrder;
import cn.szag.oms.manager.common.domain.manager.ManagerNotice;
import cn.szag.oms.manager.common.domain.manager.Messagelog;
import cn.szag.oms.manager.common.domain.manager.OrderAttachment;
import cn.szag.oms.manager.common.domain.manager.OrderClearanceInfo;
import cn.szag.oms.manager.common.domain.manager.OrderContainerAdvice;
import cn.szag.oms.manager.common.domain.manager.OrderEvaluationScore;
import cn.szag.oms.manager.common.domain.manager.OrderExport;
import cn.szag.oms.manager.common.domain.manager.OrderExportShippingInfo;
import cn.szag.oms.manager.common.domain.manager.OrderImport;
import cn.szag.oms.manager.common.domain.manager.OrderPaymentInfo;
import cn.szag.oms.manager.common.domain.manager.OrderReturnContainerInfo;
import cn.szag.oms.manager.common.domain.manager.OrderShippingInfo;
import cn.szag.oms.manager.common.domain.manager.OrderTransferInfo;
import cn.szag.oms.manager.common.domain.manager.OrderWorklistStatus;
import cn.szag.oms.manager.common.domain.manager.PictureAttachment;
import cn.szag.oms.manager.common.domain.manager.ShippinInfoDate;
import cn.szag.oms.manager.common.domain.manager.User;
import cn.szag.oms.manager.common.enums.EScheduleStatusEnum;
import cn.szag.oms.manager.common.enums.InformEnum;
import cn.szag.oms.manager.common.enums.ScheduleStatusEnum;
import cn.szag.oms.manager.common.page.Page;
import cn.szag.oms.manager.common.utils.Const;
import cn.szag.oms.manager.common.utils.CryptoUtils;
import cn.szag.oms.manager.common.utils.EmailDemo;
import cn.szag.oms.manager.common.utils.EncryptUtil;
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
import cn.szag.oms.manager.common.utils.wechat.TTResult;
import cn.szag.oms.manager.common.utils.wechat.Template;
import cn.szag.oms.manager.common.utils.wechat.Token;
import cn.szag.oms.manager.common.utils.wechat.WeChatExploitMessage;
import cn.szag.oms.manager.common.utils.wechat.WeChatUtil;
import cn.szag.oms.manager.service.AttentionService;
import cn.szag.oms.manager.service.CusUserService;
import cn.szag.oms.manager.service.DispatchOrderService;
import cn.szag.oms.manager.service.ManagerNoticeService;
import cn.szag.oms.manager.service.MessagelogService;
import cn.szag.oms.manager.service.ModuleManagerService;
import cn.szag.oms.manager.service.OrderAttachmentService;
import cn.szag.oms.manager.service.OrderClearanceInfoService;
import cn.szag.oms.manager.service.OrderContainerAdviceService;
import cn.szag.oms.manager.service.OrderEvaluationScoreService;
import cn.szag.oms.manager.service.OrderExportService;
import cn.szag.oms.manager.service.OrderExportShippingInfoService;
import cn.szag.oms.manager.service.OrderImportService;
import cn.szag.oms.manager.service.OrderPaymentInfoService;
import cn.szag.oms.manager.service.OrderReturnContainerInfoService;
import cn.szag.oms.manager.service.OrderShippingInfoService;
import cn.szag.oms.manager.service.OrderTransferInfoService;
import cn.szag.oms.manager.service.OrderWorklistStatusService;
import cn.szag.oms.manager.service.PictureAttachmentService;
import cn.szag.oms.manager.service.UserControlService;

/**
 * 
 * @ClassName: OrderIController
 * @Description: TODO
 * @author dengyanghao
 * @date 2019年9月16日 上午11:21:50
 */
@Controller
@RequestMapping(value = "/orderI")
public class OrderIController {
	@Autowired
	private RedisUtil redisUtil;
	@Autowired
	private OrderShippingInfoService orderShippingInfoService;// 海运信息
	@Autowired
	private OrderClearanceInfoService orderClearanceInfoService;// 报空
	@Autowired
	private OrderTransferInfoService orderTransferInfoService;// 运输
	@Autowired
	private DispatchOrderService dispatchOrderService;// 调度
	@Autowired
	private PictureAttachmentService pictureAttachmentService;// 运输附件
	@Autowired
	private OrderReturnContainerInfoService orderReturnContainerInfoService;// 报空信息
	@Autowired
	private OrderAttachmentService orderAttachmentService;// 单证
	@Autowired
	private OrderPaymentInfoService orderPaymentInfoService;// 付汇
	@Autowired
	private ManagerNoticeService managerNoticeService;// 业务消息中心
	@Autowired
	private OrderEvaluationScoreService orderEvaluationScoreService;// 订单评分
	@Autowired
	private AttentionService attentionService;// 关注
	@Autowired
	private OrderContainerAdviceService orderContainerAdviceService;// 报空通知
	@Autowired
	private OrderWorklistStatusService orderWorklistStatusService;// 工作状态
	@Autowired
	private OrderImportService orderImportService;// 进口订单
	@Autowired
	private CusUserService cusUserService;// 客户联系人
	@Autowired
	private UserControlService userService;
	@Autowired
	private OrderExportService orderExportService;// 出口
	@Autowired
	private OrderExportShippingInfoService orderExportShippingInfoService;// 出口海运
	@Autowired
	private PlatformTransactionManager transactionManager;
	@Autowired
	private ModuleManagerService moduleManagerService;
	@Autowired
	private MessagelogService messagelogService;// 记录日志消息

	JPushClient jpushClient = new JPushClient(PushExample.MASTER_SECRET, PushExample.APP_KEY);// 客户

	// JPushClient jpushClient2 = new JPushClient(PushExample.MASTER_SECRET2,
	// PushExample.APP_KEY2);//业务
	public static String uggUrl = Url.uggUrl;

	public static String feiyongUrl = Url.feiyongUrl;

	EmailDemo emailDemo = new EmailDemo();

	/**
	 * 查询进度时间信息 @Title: shipping @Description: TODO @param @param
	 * request @param @param orderId @param @param boxNo @param @return @author
	 * dengyanghao @return AjaxRes @throws
	 */
	@RequestMapping(value = "/scheduleTime")
	@ResponseBody
	public AjaxRes scheduleTime(HttpServletRequest request, String orderId, String boxNo, String id, Integer type) {
		AjaxRes ar = new AjaxRes();
		try {
			String token = request.getParameter("token");
			User user = Verification.getUser(token, redisUtil);
			ShippinInfoDate shippinInfoDate = orderShippingInfoService.select(orderId, boxNo, id, type);
			ar.setSucceed(shippinInfoDate, "进度时间获取成功");
		} catch (NumberFormatException e) {
			ar.setFailMsg("参数错误！");
		} catch (UserException e) {
			ar.setFailMsg("令牌为空！");
			ar.setRes(-1);
			e.printStackTrace();
		} catch (Exception e) {
			ar.setFailMsg("进度时间获取失败，出现异常");
			e.printStackTrace();
		}
		return ar;
	}

	/**
	 * 查询海运信息 @Title: shipping @Description: TODO @param @param
	 * request @param @param boxNo @param @return @author dengyanghao @return
	 * AjaxRes @throws
	 */
	@RequestMapping(value = "/shipping")
	@ResponseBody
	public AjaxRes shipping(HttpServletRequest request, String orderId, String containerId) {
		AjaxRes ar = new AjaxRes();
		try {
			String token = request.getParameter("token");
			User user = Verification.getUser(token, redisUtil);
			// 订单Id和集装箱Id
			OrderShippingInfo orderShippingInfo = orderShippingInfoService.selectByOrderId(orderId, containerId);
			ar.setSucceed(orderShippingInfo, "海运信息获取成功");
		} catch (NumberFormatException e) {
			ar.setFailMsg("参数错误！");
		} catch (UserException e) {
			ar.setFailMsg("令牌为空！");
			ar.setRes(-1);
			e.printStackTrace();
		} catch (Exception e) {
			ar.setFailMsg("海运信息获取失败，出现异常");
			e.printStackTrace();
		}
		return ar;
	}

	/**
	 * 新增海运信息 @Title: addShipping @Description: TODO @param @param
	 * request @param @param orderShippingInfo @param @return @author
	 * dengyanghao @return AjaxRes @throws
	 */
	@RequestMapping(value = "/addShipping")
	@ResponseBody
	public AjaxRes addShipping(HttpServletRequest request, OrderShippingInfo orderShippingInfo) {
		AjaxRes ar = new AjaxRes();
		String token = null;
		User user = null;
		try {
			String json = request.getParameter("json");
			if ("".equals(json) || json == null) {
				token = request.getParameter("token");
				user = Verification.getUser(token, redisUtil);
				if (null == orderShippingInfo.getId()) {
					orderShippingInfo.setId(UuidUtil.get32UUID());
				}
			} else {
				System.out.println(json);
				orderShippingInfo = JSON.parseObject(json, OrderShippingInfo.class);
			}
			OrderShippingInfo o = orderShippingInfoService.selectByPrimaryKey(orderShippingInfo.getId());
			boolean flag = false;
			if (null != o && null != orderShippingInfo.getArriveTime() && null == o.getArriveTime()) {// 判断当前数据库里这条海运信息没有实际到港时间，而这条记录却又传递时间到港
				flag = true;
			}
			if (flag) {// CFS到港时间不为空且数据库到港时间为空则判断为到港
				ManagerNotice managerNotice = new ManagerNotice();
				List<OrderWorklistStatus> orderWorklistStatusList = orderWorklistStatusService.select(o.getOrderId());
				OrderImport orderImport = orderImportService.selectByPrimaryKey(o.getOrderId());
				if (orderImport != null) {
					if (null != orderWorklistStatusList) {
						String extractOrderNum = "";
						for (OrderWorklistStatus orderWorklistStatus : orderWorklistStatusList) {
							// extractOrderNum =
							// orderWorklistStatus.getExtractOrderNum();
							if ("0".equals(orderWorklistStatus.getScheduleStatus())) {
								orderWorklistStatus.setScheduleStatus(ScheduleStatusEnum.ALREADY_ARRIVAL.getCode());// 已到港
								orderWorklistStatusService.updateByPrimaryKeySelective(orderWorklistStatus);//
								List<CustomerAccount> accountList = cusUserService
										.findByCustomerId(orderImport.getTrueCustomerId());
								Map<String, Object> map = new HashMap<>();
								map.put("orderId", orderWorklistStatus.getOrderId());
								map.put("containerId", orderWorklistStatus.getId());
								map.put("boxNo", orderWorklistStatus.getBoxNo());
								for (CustomerAccount customerAccount : accountList) {
									managerNotice.setId(UuidUtil.get32UUID());
									managerNotice.setContent(MessageXLS.returnMessage(
											InformEnum.ARRIVAL_INFORM.getValue(), o.getExtractOrderNo(),
											orderWorklistStatus.getBoxNo(), orderWorklistStatus.getProduct()));
									String title = "";
									if (orderImport.getTransportWay().equals("0")) {
										title = "海运进口";
									} else if (orderImport.getTransportWay().equals("2")) {
										title = "空运进口";
									} else {
										title = "陆运进口";
									}
									managerNotice.setOrderId(o.getOrderId());
									managerNotice.setReceiverId(customerAccount.getId());
									managerNotice.setCreatetime(new Date());
									managerNotice.setTitleType(1);
									managerNotice.setContainerId(orderWorklistStatus.getId());
									managerNotice.setTitle(title);
									/**
									 * App推送方法
									 */
									send(customerAccount, managerNotice.getContent(), title, map,
											orderWorklistStatus.getType());
									managerNoticeService.insertSelective(managerNotice);// 新增消息通知
								}
							}
						}
					}
				}
			}
			if (null == o) {// 根据Id判断是否存在（存在修改/不存在新增）
				orderShippingInfoService.insertSelective(orderShippingInfo);
			} else {
				if (null != user) {
					orderShippingInfo.setUpdaterId(user.getId());
					orderShippingInfo.setUpdateTime(new Date());
				}
				orderShippingInfoService.updateByPrimaryKeySelective(orderShippingInfo);
			}
			Transaction.commit(transactionManager);
			ar.setSucceedMsg("海运新增信息获取成功");
		} catch (NumberFormatException e) {
			e.printStackTrace();
			ar.setFailMsg("参数错误！");
		} catch (UserException e) {
			ar.setFailMsg("令牌为空！");
			ar.setRes(-1);
			e.printStackTrace();
		} catch (Exception e) {
			Transaction.rollback(transactionManager);
			ar.setFailMsg("海运新增获取失败，出现异常");
			e.printStackTrace();
		}
		return ar;
	}

	/**
	 * 修改海运信息 @Title: editShipping @Description: TODO @param @param
	 * request @param @param orderShippingInfo @param @return @author
	 * dengyanghao @return AjaxRes @throws
	 */
	@RequestMapping(value = "/editShipping")
	@ResponseBody
	public AjaxRes editShipping(HttpServletRequest request, OrderShippingInfo orderShippingInfo) {
		AjaxRes ar = new AjaxRes();
		String token = null;
		User user = null;
		try {
			String json = request.getParameter("json");
			if ("".equals(json) || json == null) {
				token = request.getParameter("token");
				user = Verification.getUser(token, redisUtil);
			} else {
				orderShippingInfo = JSON.parseObject(json, OrderShippingInfo.class);
			}
			orderShippingInfoService.updateByPrimaryKeySelective(orderShippingInfo);
			ar.setSucceedMsg("海运修改信息获取成功");
		} catch (NumberFormatException e) {
			ar.setFailMsg("参数错误！");
		} catch (UserException e) {
			ar.setFailMsg("令牌为空！");
			ar.setRes(-1);
			e.printStackTrace();
		} catch (Exception e) {
			ar.setFailMsg("海运修改获取失败，出现异常");
			e.printStackTrace();
		}
		return ar;
	}

	/**
	 * 查询报关信息 @Title: clearance @Description: TODO @param @param
	 * request @param @param orderId @param @return @author dengyanghao @return
	 * AjaxRes @throws
	 */
	@RequestMapping(value = "/clearance")
	@ResponseBody
	public AjaxRes clearance(HttpServletRequest request, String orderId, String containerId) {
		AjaxRes ar = new AjaxRes();
		try {
			String token = request.getParameter("token");
			User user = Verification.getUser(token, redisUtil);
			OrderClearanceInfo orderClearanceInfo = orderClearanceInfoService.select(orderId, containerId);
			ar.setSucceed(orderClearanceInfo, "报关信息获取成功");
		} catch (NumberFormatException e) {
			ar.setFailMsg("参数错误！");
		} catch (UserException e) {
			ar.setFailMsg("令牌为空！");
			ar.setRes(-1);
			e.printStackTrace();
		} catch (Exception e) {
			ar.setFailMsg("报关信息获取失败，出现异常");
			e.printStackTrace();
		}
		return ar;
	}

	/**
	 * 新增报关信息 @Title: addClearance @Description: TODO @param @param
	 * request @param @param orderClearanceInfo @param @return @author
	 * dengyanghao @return AjaxRes @throws
	 */
	@RequestMapping(value = "/addClearance", method = RequestMethod.POST)
	@ResponseBody
	public AjaxRes addClearance(HttpServletRequest request, OrderClearanceInfo orderClearanceInfo) {
		AjaxRes ar = new AjaxRes();
		String token = null;
		User user = null;
		try {
			String json = request.getParameter("json");
			System.out.println(json+"---------------------------------------------------------------------------------------------------------------");
			if ("".equals(json) || json == null) {
				token = request.getParameter("token");
				user = Verification.getUser(token, redisUtil);
				if (null == orderClearanceInfo.getId()) {
					orderClearanceInfo.setId(UuidUtil.get32UUID());
				}
			} else {
				orderClearanceInfo = JSON.parseObject(json, OrderClearanceInfo.class);
			}
			OrderClearanceInfo o = orderClearanceInfoService.selectByPrimaryKey(orderClearanceInfo.getId());
			// OrderImport orderImport =
			// orderImportService.selectByPrimaryKey(orderClearanceInfo.getOrderId());
			if (null == o) {// 根据Id判断是否存在（存在修改/不存在新增）
				if (null != user) {
					orderClearanceInfo.setCreaterId(user.getId());
				}
				orderClearanceInfo.setCreateTime(new Date());
				orderClearanceInfoService.insertSelective(orderClearanceInfo);

			} else {
				if (null != user) {
					orderClearanceInfo.setUpdaterId(user.getId());
				}
				orderClearanceInfo.setUpdateTime(new Date());
				orderClearanceInfoService.updateByPrimaryKeySelective(orderClearanceInfo);
			}
			ar.setSucceedMsg("报关信息新增成功");
		} catch (NumberFormatException e) {
			ar.setFailMsg("参数错误！");
		} catch (UserException e) {
			ar.setFailMsg("令牌为空！");
			ar.setRes(-1);
			e.printStackTrace();
		} catch (Exception e) {
			ar.setFailMsg("报关信息新增失败，出现异常");
			e.printStackTrace();
		}
		return ar;
	}

	/**
	 * 修改报关信息 @Title: editClearance @Description: TODO @param @param
	 * request @param @param orderId @param @return @author dengyanghao @return
	 * AjaxRes @throws
	 */
	@RequestMapping(value = "/editClearance")
	@ResponseBody
	public AjaxRes editClearance(HttpServletRequest request, OrderClearanceInfo orderClearanceInfo) {
		AjaxRes ar = new AjaxRes();
		String token = null;
		User user = null;
		try {
			String json = request.getParameter("json");
			if ("".equals(json) || json == null) {
				token = request.getParameter("token");
				user = Verification.getUser(token, redisUtil);
			} else {
				orderClearanceInfo = JSON.parseObject(json, OrderClearanceInfo.class);
			}
			orderClearanceInfoService.updateByPrimaryKeyWithBLOBs(orderClearanceInfo);//
			ar.setSucceedMsg("报关信息修改成功");
		} catch (NumberFormatException e) {
			ar.setFailMsg("参数错误！");
		} catch (UserException e) {
			ar.setFailMsg("令牌为空！");
			ar.setRes(-1);
			e.printStackTrace();
		} catch (Exception e) {
			ar.setFailMsg("报关信息修改失败，出现异常");
			e.printStackTrace();
		}
		return ar;
	}

	/**
	 * 查询运输信息 @Title: transfer @Description: TODO @param @param
	 * request @param @param id @param @param orderId @param @param
	 * boxNo @param @return @author dengyanghao @return AjaxRes @throws
	 */
	@RequestMapping(value = "/transfer")
	@ResponseBody
	public AjaxRes transfer(HttpServletRequest request, String orderId, String containerId) {
		AjaxRes ar = new AjaxRes();
		try {
			String token = request.getParameter("token");
			User user = Verification.getUser(token, redisUtil);
			OrderTransferInfo orderTransferInfo = orderTransferInfoService.select(containerId, orderId);
			OrderWorklistStatus work = orderWorklistStatusService.selectByPrimaryKey(containerId);
			if (work != null) {
				// orderTransferInfo.setDispatchList(dispatchOrderService.select(orderTransferInfo.getId()));//参数为运输单id
				List<PictureAttachment> p = pictureAttachmentService.select(containerId);
				orderTransferInfo = orderTransferInfo == null ? new OrderTransferInfo() : orderTransferInfo;
				if (!p.isEmpty()) {
					orderTransferInfo.setImage(p);
				}
				orderTransferInfo.setScheduleStatus(work.getScheduleStatus());
				int status = Integer.parseInt(work.getScheduleStatus());
				List<BaseBoxNoMessage> list2 = new ArrayList<BaseBoxNoMessage>();
//				if(work.getType() == 1 &&status==3){
//					status+=1;
//				}else if(work.getType() == 1 &&status==4){
//					status=3;
//				}
				for (int i = 0; i < status + 2; i++) {
					List<BaseBoxNoMessage> list = null;
					if (work.getType() == 1) {
						list = orderWorklistStatusService.selectOrderId(orderId, i + "", containerId);
					} else {
						list = orderWorklistStatusService.selectOrderEId(orderId, i + "", containerId);
					}
					System.out.println(i+"--"+list);
					for (BaseBoxNoMessage baseBoxNoMessage : list) {
						baseBoxNoMessage.setStatus(i + "");
						list2.add(baseBoxNoMessage);
					}
				}
				orderTransferInfo.setList(list2);
			}
			ar.setSucceed(orderTransferInfo, "运输信息获取成功");
		} catch (NumberFormatException e) {
			ar.setFailMsg("参数错误！");
		} catch (UserException e) {
			ar.setFailMsg("令牌为空！");
			ar.setRes(-1);
			e.printStackTrace();
		} catch (Exception e) {
			ar.setFailMsg("运输信息获取失败，出现异常");
			e.printStackTrace();
		}
		return ar;
	}

	/**
	 * 新增运输信息 @Title: addTransfer @Description: TODO @param @param
	 * request @param @param orderTransferInfo @param @return @author
	 * dengyanghao @return AjaxRes @throws
	 */
	@RequestMapping(value = "/addTransfer")
	@ResponseBody
	public AjaxRes addTransfer(HttpServletRequest request, OrderTransferInfo orderTransferInfo) {
		AjaxRes ar = new AjaxRes();
		String token = null;
		User user = null;
		try {
			String json = request.getParameter("json");
			if ("".equals(json) || json == null) {
				token = request.getParameter("token");
				user = Verification.getUser(token, redisUtil);
				if (null == orderTransferInfo.getId()) {
					orderTransferInfo.setId(UuidUtil.get32UUID());
					orderTransferInfo.setCreatetime(new Date());
				}
			} else {
				orderTransferInfo = JSON.parseObject(json, OrderTransferInfo.class);
			}
			OrderTransferInfo o = orderTransferInfoService.selectByPrimaryKey(orderTransferInfo.getId());
			if (null == o) {// 根据Id判断是否存在（存在修改/不存在新增）
				orderTransferInfo.setCreatetime(new Date());
				orderTransferInfoService.insertSelective(orderTransferInfo);// 新增
			} else {
				if (null != user) {
					orderTransferInfo.setUpdaterId(user.getId());
				}
				orderTransferInfo.setUpdateTime(new Date());
				orderTransferInfoService.updateByPrimaryKeySelective(orderTransferInfo);
			}
			ar.setSucceedMsg("运输信息新增成功");
		} catch (NumberFormatException e) {
			ar.setFailMsg("参数错误！");
		} catch (UserException e) {
			ar.setFailMsg("令牌为空！");
			ar.setRes(-1);
			e.printStackTrace();
		} catch (Exception e) {
			ar.setFailMsg("运输信息新增失败，出现异常");
			e.printStackTrace();
		}
		return ar;
	}

	/**
	 * 修改运输信息 @Title: editTransfer @Description: TODO @param @param
	 * request @param @param orderTransferInfo @param @return @author
	 * dengyanghao @return AjaxRes @throws
	 */
	@RequestMapping(value = "/editTransfer")
	@ResponseBody
	public AjaxRes editTransfer(HttpServletRequest request, OrderTransferInfo orderTransferInfo) {
		AjaxRes ar = new AjaxRes();
		String token = null;
		User user = null;
		try {
			String json = request.getParameter("json");
			if ("".equals(json) || json == null) {
				token = request.getParameter("token");
				user = Verification.getUser(token, redisUtil);
			} else {
				orderTransferInfo = JSON.parseObject(json, OrderTransferInfo.class);
			}
			orderTransferInfoService.updateByPrimaryKeySelective(orderTransferInfo);// 修改
			ar.setSucceedMsg("运输信息修改成功");
		} catch (NumberFormatException e) {
			ar.setFailMsg("参数错误！");
		} catch (UserException e) {
			ar.setFailMsg("令牌为空！");
			ar.setRes(-1);
			e.printStackTrace();
		} catch (Exception e) {
			ar.setFailMsg("运输信息修改失败，出现异常");
			e.printStackTrace();
		}
		return ar;
	}

	/**
	 * 新增调度单 @Title: addDispatch @Description: TODO @param @param
	 * request @param @param dispatchOrder @param @return @author
	 * dengyanghao @return AjaxRes @throws
	 */
	@RequestMapping(value = "/addDispatch")
	@ResponseBody
	public AjaxRes addDispatch(HttpServletRequest request, DispatchOrder dispatchOrder) {
		AjaxRes ar = new AjaxRes();
		String token = null;
		User user = null;
		try {
			String json = request.getParameter("json");
			if ("".equals(json) || json == null) {
				token = request.getParameter("token");
				user = Verification.getUser(token, redisUtil);
				if (null == dispatchOrder.getId()) {
					dispatchOrder.setId(UuidUtil.get32UUID());
				}
			} else {
				dispatchOrder = JSON.parseObject(json, DispatchOrder.class);
			}
			DispatchOrder o = dispatchOrderService.selectByPrimaryKey(dispatchOrder.getId());
			OrderTransferInfo transferInfo = orderTransferInfoService.selectByPrimaryKey(dispatchOrder.getTransferID());// 运输单Id
			OrderWorklistStatus work = orderWorklistStatusService.selectByPrimaryKey(dispatchOrder.getContainerId());// 集装箱Id
			OrderImport orderImport = null;
			OrderExport orderExport = null;
			String trueCustomerId = "";// 真实公司Id
			String orderId = "";
			String extractOrderNo = "";// 提单号
			String transportWay = "";// 运输类型
			if (null != transferInfo) {
				orderId = transferInfo.getOrderId();
				if (null != work) {
					if (work.getType() == 1) {// 进口
						orderImport = orderImportService.selectByPrimaryKey(transferInfo.getOrderId());// 获取订单信息
						OrderShippingInfo osi = orderShippingInfoService
								.selectByPrimaryKey(work.getOrderShippingInfoId());
						extractOrderNo = osi.getExtractOrderNo();
					} else if (work.getType() == 2) {
						orderExport = orderExportService.selectByPrimaryKey(transferInfo.getOrderId());
						OrderExportShippingInfo osi = orderExportShippingInfoService
								.selectByPrimaryKey(work.getOrderExportShippingInfoId());
						extractOrderNo = osi.getBlNo();
					}
				}
			}
			OrderWorklistStatus orderWorklistStatus = new OrderWorklistStatus();
			if (null != orderImport || null != orderExport) {
				trueCustomerId = orderImport == null ? orderExport.getCustomerName() : orderImport.getTrueCustomerId();
				transportWay = orderImport == null ? orderExport.getTransportWay() : orderImport.getTransportWay();
				if (dispatchOrder.getEndPlace().equals(transferInfo.getPlocation())) {// 判断当前调度是否是最后一层调度
					if ((null != dispatchOrder.getArrivalTime() && null == work.getArriveTime())
							|| (null != dispatchOrder.getDepartureTime() && null == work.getDepartTime())
							|| (null != dispatchOrder.getMentionArkTime() && null == work.getMentionArkTime())) {// 判断当前是否出车或抵达
						ManagerNotice managerNotice = new ManagerNotice();
						String scheduleStatus = "";
						String titleS = "";
						if (dispatchOrder.getStatus() == 2) {
							titleS = InformEnum.TURNOUT_INFORM.getValue();
							if (work.getType() == 1) {// 进口
								scheduleStatus = ScheduleStatusEnum.PORT_GATE_OUT.getCode();// 出车
							} else {
								scheduleStatus = EScheduleStatusEnum.ALREADY_TURNOUT.getCode();// 出车
							}
						} else if (dispatchOrder.getStatus() == 3) {
							if (work.getType() == 1) {// 进口
								scheduleStatus = ScheduleStatusEnum.ALREADY_ARRIVE.getCode();// 已抵达
								titleS = InformEnum.ARRIVE_INFORM.getValue();
							}
						} else if (dispatchOrder.getStatus() == 9) {
							if (work.getType() == 2) {
								scheduleStatus = EScheduleStatusEnum.MENTION_ARK.getCode();// 已提柜
								titleS = InformEnum.MENTION_ARK.getValue();
							}
						} else if (dispatchOrder.getStatus() == 10) {
							if (work.getType() == 2) {
								scheduleStatus = EScheduleStatusEnum.LOADING.getCode();// 装货
								titleS = InformEnum.LOADING.getValue();
							}
						}
						if (work.getType() == 1) {
							managerNotice.setContent(MessageXLS.returnMessage(titleS, extractOrderNo, work.getBoxNo(),
									work.getProduct()));
						} else if (work.getType() == 2) {
							managerNotice.setContent(MessageXLS.returnBookingMessage(titleS, work.getBookingNo(),
									work.getBoxNo(), work.getProduct()));
						}
						String title = "";
						if (transportWay.equals("0")) {
							title = "海运" + (work.getType() == 1 ? "进口" : "出口");
						} else if (transportWay.equals("2")) {
							title = "空运" + (work.getType() == 1 ? "进口" : "出口");
							;
						} else {
							title = "陆运" + (work.getType() == 1 ? "进口" : "出口");
							;
						}
						managerNotice.setOrderId(orderId);
						managerNotice.setTitle(title);
						managerNotice.setCreatetime(new Date());
						managerNotice.setContainerId(dispatchOrder.getContainerId());
						managerNotice.setTitleType(work.getType());
						List<CustomerAccount> accountList = cusUserService.findByCustomerId(trueCustomerId);
						Map<String, Object> map = new HashMap<>();
						map.put("orderId", work.getOrderId());
						map.put("containerId", work.getId());
						map.put("boxNo", work.getBoxNo());
						for (CustomerAccount customerAccount : accountList) {
							managerNotice.setId(UuidUtil.get32UUID());
							managerNotice.setReceiverId(customerAccount.getId());
							managerNoticeService.insertSelective(managerNotice);// 添加消息通知中心
							/**
							 * App推送方法
							 */
							send(customerAccount, managerNotice.getContent(), title, map, work.getType());
						}
						orderWorklistStatus.setId(dispatchOrder.getContainerId());
						orderWorklistStatus.setArrivalDate(dispatchOrder.getArrivalTime());// 抵达/装货
						orderWorklistStatus.setDepartTime(dispatchOrder.getDepartureTime());// 发车
						orderWorklistStatus.setMentionArkTime(dispatchOrder.getMentionArkTime());// 提柜
						orderWorklistStatus.setDispatchid(dispatchOrder.getDispatcher());
						orderWorklistStatus.setScheduleStatus(scheduleStatus);
						orderWorklistStatusService.updateByPrimaryKeySelective(orderWorklistStatus);// 更新集装箱部分信息

					}
				}
			}
			if (null == o) {// 根据Id判断是否存在（存在修改/不存在新增）
				dispatchOrderService.insertSelective(dispatchOrder);// 新增
			} else {
				dispatchOrderService.updateByPrimaryKeySelective(dispatchOrder);
			}
			Transaction.commit(transactionManager);
			ar.setSucceedMsg("调度单新增成功");
		} catch (NumberFormatException e) {
			ar.setFailMsg("参数错误！");
		} catch (UserException e) {
			ar.setFailMsg("令牌为空！");
			ar.setRes(-1);
			e.printStackTrace();
		} catch (Exception e) {
			Transaction.rollback(transactionManager);
			ar.setFailMsg("调度单新增失败，出现异常");
			e.printStackTrace();
		}
		return ar;
	}

	/**
	 * 修改调度单 @Title: editDispatch @Description: TODO @param @param
	 * request @param @param dispatchOrder @param @return @author
	 * dengyanghao @return AjaxRes @throws
	 */
	@RequestMapping(value = "/editDispatch")
	@ResponseBody
	public AjaxRes editDispatch(HttpServletRequest request, DispatchOrder dispatchOrder) {
		AjaxRes ar = new AjaxRes();
		String token = null;
		User user = null;
		try {
			String json = request.getParameter("json");
			if ("".equals(json) || json == null) {
				token = request.getParameter("token");
				user = Verification.getUser(token, redisUtil);
			} else {
				dispatchOrder = JSON.parseObject(json, DispatchOrder.class);
			}
			dispatchOrderService.updateByPrimaryKeySelective(dispatchOrder);// 新增
			ar.setSucceedMsg("调度单修改成功");
		} catch (NumberFormatException e) {
			ar.setFailMsg("参数错误！");
		} catch (UserException e) {
			ar.setFailMsg("令牌为空！");
			ar.setRes(-1);
			e.printStackTrace();
		} catch (Exception e) {
			ar.setFailMsg("调度单修改失败，出现异常");
			e.printStackTrace();
		}
		return ar;
	}

	/**
	 * 新增运输附件 @Title: addPictureAttachment @Description: TODO @param @param
	 * request @param @param pictureAttachment @param @return @author
	 * dengyanghao @return AjaxRes @throws
	 */
	@RequestMapping(value = "/addPictureAttachment")
	@ResponseBody
	public AjaxRes addPictureAttachment(HttpServletRequest request, PictureAttachment pictureAttachment) {
		AjaxRes ar = new AjaxRes();
		String token = null;
		User user = null;
		try {
			String json = request.getParameter("json");
			if ("".equals(json) || json == null) {
				token = request.getParameter("token");
				user = Verification.getUser(token, redisUtil);
				if (null == pictureAttachment.getId()) {
					pictureAttachment.setId(UuidUtil.get32UUID());
				}
			} else {
				pictureAttachment = JSON.parseObject(json, PictureAttachment.class);
			}
			PictureAttachment p = pictureAttachmentService.selectByPrimaryKey(pictureAttachment.getId());
			if (null == p) {// 存在此记录则修改，反之新增
				if (null != user) {
					pictureAttachment.setCreator(user.getUsername());
				}
				pictureAttachment.setCreatedate(new Date());
				pictureAttachmentService.insertSelective(pictureAttachment);// 新增
			} else {
				pictureAttachmentService.updateByPrimaryKeySelective(pictureAttachment);// 修改
			}

			ar.setSucceedMsg("运输附件新增成功");
		} catch (NumberFormatException e) {
			ar.setFailMsg("参数错误！");
		} catch (UserException e) {
			ar.setFailMsg("令牌为空！");
			ar.setRes(-1);
			e.printStackTrace();
		} catch (Exception e) {
			ar.setFailMsg("运输附件新增失败，出现异常");
			e.printStackTrace();
		}
		return ar;
	}

	/**
	 * 修改运输附件 @Title: editPictureAttachment @Description: TODO @param @param
	 * request @param @param pictureAttachment @param @return @author
	 * dengyanghao @return AjaxRes @throws
	 */
	@RequestMapping(value = "/editPictureAttachment")
	@ResponseBody
	public AjaxRes editPictureAttachment(HttpServletRequest request, PictureAttachment pictureAttachment) {
		AjaxRes ar = new AjaxRes();
		String token = null;
		User user = null;
		try {
			String json = request.getParameter("json");
			if ("".equals(json) || json == null) {
				token = request.getParameter("token");
				user = Verification.getUser(token, redisUtil);
			} else {
				pictureAttachment = JSON.parseObject(json, PictureAttachment.class);
			}
			pictureAttachmentService.updateByPrimaryKeySelective(pictureAttachment);// 新增
			ar.setSucceedMsg("运输附件修改成功");
		} catch (NumberFormatException e) {
			ar.setFailMsg("参数错误！");
		} catch (UserException e) {
			ar.setFailMsg("令牌为空！");
			ar.setRes(-1);
			e.printStackTrace();
		} catch (Exception e) {
			ar.setFailMsg("运输附件修改失败，出现异常");
			e.printStackTrace();
		}
		return ar;
	}

	/**
	 * 查询还箱信息 @Title: returnContainer @Description: TODO @param @param
	 * request @param @param orderId @param @param boxNo @param @return @author
	 * dengyanghao @return AjaxRes @throws
	 */
	@RequestMapping(value = "/returnContainer")
	@ResponseBody
	public AjaxRes returnContainer(HttpServletRequest request, String orderId, String containerId) {
		AjaxRes ar = new AjaxRes();
		try {
			String token = request.getParameter("token");
			User user = Verification.getUser(token, redisUtil);
			OrderReturnContainerInfo orderReturnContainerInfo = orderReturnContainerInfoService.select(orderId,
					containerId);
			ar.setSucceed(orderReturnContainerInfo, "报空信息获取成功");
		} catch (NumberFormatException e) {
			ar.setFailMsg("参数错误！");
		} catch (UserException e) {
			ar.setFailMsg("令牌为空！");
			ar.setRes(-1);
			e.printStackTrace();
		} catch (Exception e) {
			ar.setFailMsg("报空信息获取失败，出现异常");
			e.printStackTrace();
		}
		return ar;
	}

	/**
	 * 新增还箱信息 @Title: addReturnContainer @Description: TODO @param @param
	 * request @param @param orderReturnContainerInfo @param @return @author
	 * dengyanghao @return AjaxRes @throws
	 */
	@RequestMapping(value = "/addReturnContainer")
	@ResponseBody
	public AjaxRes addReturnContainer(HttpServletRequest request, OrderReturnContainerInfo orderReturnContainerInfo) {
		AjaxRes ar = new AjaxRes();
		String token = null;
		User user = null;
		try {
			String json = request.getParameter("json");
			if ("".equals(json) || json == null) {
				token = request.getParameter("token");
				user = Verification.getUser(token, redisUtil);
				orderReturnContainerInfo.setId(UuidUtil.get32UUID());
				orderReturnContainerInfo.setCreaterId(user.getId());
			} else {
				orderReturnContainerInfo = JSON.parseObject(json, OrderReturnContainerInfo.class);
			}
			OrderReturnContainerInfo o = orderReturnContainerInfoService
					.selectByPrimaryKey(orderReturnContainerInfo.getId());
			String orderId = "";// 订单Id（进口/出口）
			String orderTransportWay = "";// 订单类型
			String orderTrueCustomerId = "";// 客户Id
			OrderWorklistStatus work = orderWorklistStatusService
					.selectByPrimaryKey(orderReturnContainerInfo.getContainerId());// 集装箱
			if (work != null) {
				orderId = work.getOrderId();
				if (work.getType() == 1) {
					OrderImport orderImport = orderImportService
							.selectByPrimaryKey(orderReturnContainerInfo.getOrderId());// 进口
					orderTransportWay = orderImport.getTransportWay();
					orderTrueCustomerId = orderImport.getTrueCustomerId();
				} else {
					OrderExport orderExport = orderExportService
							.selectByPrimaryKey(orderReturnContainerInfo.getOrderId());// 出口
					orderTransportWay = orderExport.getTransportWay();
					orderTrueCustomerId = orderExport.getCustomerName();
				}
			}
			if (!"".equals(orderId) && null != work) {
				ManagerNotice managerNotice = new ManagerNotice();// 消息中心
				String extractOrderNo = "";// 提单号
				String title = "";
				if (orderTransportWay.equals("0")) {
					title = "海运";
				} else if (orderTransportWay.equals("2")) {
					title = "空运";
				} else {
					title = "陆运";
				}
				if (work.getType() == 1) {
					title += "进口";
					OrderShippingInfo osi = orderShippingInfoService.selectByPrimaryKey(work.getOrderShippingInfoId());
					extractOrderNo = osi.getExtractOrderNo();
				} else {
					title += "出口";
					OrderExportShippingInfo oesi = orderExportShippingInfoService
							.selectByPrimaryKey(work.getOrderExportShippingInfoId());
					extractOrderNo = oesi.getBlNo();
				}
				work.setId(orderReturnContainerInfo.getContainerId());
				managerNotice.setContainerId(orderReturnContainerInfo.getContainerId());
				managerNotice.setOrderId(orderId);
				managerNotice.setTitleType(work.getType());
				managerNotice.setCreatetime(new Date());
				if (orderReturnContainerInfo.getReturnboxTime() != null) {// 当报空时间和还箱时间都不等于空时（状态为：还箱）还箱时间等于空，报空时间不等于空时为报空
					work.setReturnboxtime(orderReturnContainerInfo.getReturnboxTime());
					if (work.getType() == 1) {
						work.setScheduleStatus(ScheduleStatusEnum.ALREADY_TERMINAL.getCode());
						managerNotice.setContent(MessageXLS.returnMessage(InformEnum.OF_RETURN_INFORM.getValue(),
								extractOrderNo, work.getBoxNo(), work.getProduct()));
					} else {
						work.setScheduleStatus(EScheduleStatusEnum.ALREADY_AISOHEAVY.getCode());
						work.setIsArrive(1);
						managerNotice
								.setContent(MessageXLS.returnBookingMessage(InformEnum.ALSO_HEAVY_INFORM.getValue(),
										work.getBookingNo(), work.getBoxNo(), work.getProduct()));
					}
				} else if (orderReturnContainerInfo.getEmptyTime() != null) {
					work.setEmptyTime(orderReturnContainerInfo.getEmptyTime());
					work.setScheduleStatus(ScheduleStatusEnum.ALREADY_THE_EMPTY.getCode());
					managerNotice.setContent(MessageXLS.returnMessage(InformEnum.THE_EMPTY_INFORM.getValue(),
							extractOrderNo, work.getBoxNo(), work.getProduct()));
				}
				managerNotice.setTitle(title);
				List<CustomerAccount> accountList = cusUserService.findByCustomerId(orderTrueCustomerId);
				Map<String, Object> map = new HashMap<>();
				map.put("orderId", work.getOrderId());
				map.put("containerId", work.getId());
				map.put("boxNo", work.getBoxNo());
				for (CustomerAccount customerAccount : accountList) {// 客户联系人
					managerNotice.setId(UuidUtil.get32UUID());
					managerNotice.setReceiverId(customerAccount.getId());
					managerNoticeService.insertSelective(managerNotice);
					/**
					 * App推送方法
					 */
					send(customerAccount, managerNotice.getContent(), title, map, work.getType());
				}
				orderWorklistStatusService.updateByPrimaryKeySelective(work);
			}
			if (null == o) {
				orderReturnContainerInfo.setCreateTime(new Date());
				orderReturnContainerInfoService.insertSelective(orderReturnContainerInfo);
			} else {
				if (null != user) {
					orderReturnContainerInfo.setUpdaterId(user.getId());
				}
				orderReturnContainerInfo.setUpdateTime(new Date());
				orderReturnContainerInfoService.updateByPrimaryKeySelective(orderReturnContainerInfo);
			}
			Transaction.commit(transactionManager);
			ar.setSucceedMsg("报空信息新增成功");
		} catch (NumberFormatException e) {
			ar.setFailMsg("参数错误！");
		} catch (UserException e) {
			ar.setFailMsg("令牌为空！");
			ar.setRes(-1);
			e.printStackTrace();
		} catch (Exception e) {
			Transaction.rollback(transactionManager);
			ar.setFailMsg("报空信息新增失败，出现异常");
			e.printStackTrace();
		}
		return ar;
	}

	/**
	 * 修改还箱信息 @Title: editReturnContainer @Description: TODO @param @param
	 * request @param @param orderReturnContainerInfo @param @return @author
	 * dengyanghao @return AjaxRes @throws
	 */
	@RequestMapping(value = "/editReturnContainer")
	@ResponseBody
	public AjaxRes editReturnContainer(HttpServletRequest request, OrderReturnContainerInfo orderReturnContainerInfo) {
		AjaxRes ar = new AjaxRes();
		String token = null;
		User user = null;
		try {
			String json = request.getParameter("json");
			if ("".equals(json) || json == null) {
				token = request.getParameter("token");
				user = Verification.getUser(token, redisUtil);
			} else {
				orderReturnContainerInfo = JSON.parseObject(json, OrderReturnContainerInfo.class);
			}
			if (null != user) {
				orderReturnContainerInfo.setUpdaterId(user.getId());
				orderReturnContainerInfo.setUpdateTime(new Date());
			}
			orderReturnContainerInfoService.updateByPrimaryKeyWithBLOBs(orderReturnContainerInfo);
			ar.setSucceedMsg("报空信息修改成功");
		} catch (NumberFormatException e) {
			ar.setFailMsg("参数错误！");
		} catch (UserException e) {
			ar.setFailMsg("令牌为空！");
			ar.setRes(-1);
			e.printStackTrace();
		} catch (Exception e) {
			ar.setFailMsg("报空信息修改失败，出现异常");
			e.printStackTrace();
		}
		return ar;
	}

	/**
	 * 查询单证信息 @Title: attachment @Description: TODO @param @param
	 * request @param @param orderId @param @return @author dengyanghao @return
	 * AjaxRes @throws
	 */
	@RequestMapping(value = "/attachment")
	@ResponseBody
	public AjaxRes attachment(HttpServletRequest request, String containerId) {
		AjaxRes ar = new AjaxRes();
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			String token = request.getParameter("token");
			User user = Verification.getUser(token, redisUtil);
			Items it = new Items();
			map.put("userId", user.getId());
			map.put("box", containerId);
			String str = HttpUtils.post(feiyongUrl + "/getRemoteDoc", map, 5000, 5000, "UTF-8");
			it = JSON.parseObject(str, Items.class);
			ar.setSucceed(it, "单证信息列表获取成功");
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
	/**
	 * 下载
	 * @title: getBillList
	 * @author: tansongke
	 * @description: TODO
	 * @date: 2019年10月18日 下午3:02:51
	 * @param request
	 * @return
	 * @return: AjaxRes
	 * @throws Exception 
	 */
	@RequestMapping(value = "/downAtterA")
	@ResponseBody
	public AjaxRes downAtterA(HttpServletRequest req ,HttpServletResponse resp,String path,String name,String userId) throws Exception{
		AjaxRes ar = new AjaxRes();
		try {
            // 获得token信息
			String token = req.getParameter("token");
            User user = Verification.getUser(token, redisUtil);
	            String tmppath = path;
	            String attrname = name;
	            String tmp = "";
	            if (tmppath != null) {
	                tmp = tmppath.substring(tmppath.indexOf("."));
	            }
	            path = tmppath;
	            int len = user.getAccount().length();
	            len += 516;
	            String ken = CryptoUtils.encrypt(len + user.getAccount() + user.getPassword(), "14ca6fad9a10f55f3dac8da1");
	            ken = URLEncoder.encode(ken);

	            Map<String, String> paramMap = new HashMap<String, String>();
	            paramMap.put("path", path);
	            paramMap.put("token", ken);
	            String param = HttpRequestUtil.paramsConvert(paramMap);
	            String httpUrl = Url.feiyongUrl +"/download?"+ param;
	            URL url = new URL(httpUrl);
	            System.out.println(httpUrl);
	            HttpURLConnection conn = (HttpURLConnection)url.openConnection();
	            InputStream inputStream = conn.getInputStream();

	            String name1 = URLEncoder.encode(attrname, "UTF-8");

	            resp.setContentType("application/octet-stream");
	            resp.setHeader("Content-disposition", "inline; filename=" + name1 + tmp);



	            ServletOutputStream out = resp.getOutputStream();

	            byte[] buffer = new byte[1024];
	            int i = -1;
	            while ((i = inputStream.read(buffer)) != -1) {
	                out.write(buffer, 0, i);
	            }
	            out.flush();
	            out.close();
	            inputStream.close();
//           resp.reset();// 清空输出流
//            String fileUrl = feiyong_url+"?path="+path+"&name="+name+"&userId="+userId;
//            File file = new File(fileUrl);
//
//            resp.setCharacterEncoding("UTF-8");
//            resp.setHeader("Content-disposition", "attachment; filename=" + path);// 设定输出文件头
//            resp.setContentType("application/msexcel");// 定义输出类型
//            //输入流：本地文件路径
//            DataInputStream in = new DataInputStream(
//                    new FileInputStream(new File(fileUrl)));
//            //输出流
//            OutputStream out = resp.getOutputStream();
//            //输出文件
//            int bytes = 0;
//            byte[] bufferOut = new byte[1024];
//            while ((bytes = in.read(bufferOut)) != -1) {
//                out.write(bufferOut, 0, bytes);
//            }
//            out.close();
//            in.close();
            return new AjaxRes(Const.SUCCEED,"下载成功");
        } catch (NumberFormatException e) {
            return new AjaxRes(Const.FAIL, "参数错误！！");
        } catch (UserException e) {
            e.printStackTrace();
            return new AjaxRes(Const.FAIL, "令牌为空！");
        } catch (IOException e) {
            e.printStackTrace();
            return new AjaxRes(Const.FAIL,"下载失败");
        }catch (Exception e) {
            e.printStackTrace();
            return new AjaxRes(Const.FAIL,"下载失败");
        }
	}
	/**
	 * 查询付汇 @Title: payment @Description: TODO @param @param
	 * request @param @param orderId @param @return @author dengyanghao @return
	 * AjaxRes @throws
	 */
	@RequestMapping(value = "/payment")
	@ResponseBody
	public AjaxRes payment(HttpServletRequest request, String orderId) {
		AjaxRes ar = new AjaxRes();
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			String token = request.getParameter("token");
			User user = Verification.getUser(token, redisUtil);
			OrderPaymentInfo orderPaymentInfo = orderPaymentInfoService.selectByOrderId(orderId);
			ar.setSucceed(orderPaymentInfo, "付汇信息获取成功");
		} catch (NumberFormatException e) {
			ar.setFailMsg("参数错误！");
		} catch (UserException e) {
			ar.setFailMsg("令牌为空！");
			ar.setRes(-1);
			e.printStackTrace();
		} catch (Exception e) {
			ar.setFailMsg("付汇信息获取失败，出现异常");
			e.printStackTrace();
		}
		return ar;
	}

	/**
	 * 新增付汇信息 @Title: addPayment @Description: TODO @param @param
	 * request @param @param orderPaymentInfo @param @return @author
	 * dengyanghao @return AjaxRes @throws
	 */
	@RequestMapping(value = "/addPayment")
	@ResponseBody
	public AjaxRes addPayment(HttpServletRequest request, OrderPaymentInfo orderPaymentInfo) {
		AjaxRes ar = new AjaxRes();
		String token = null;
		User user = null;
		try {
			String json = request.getParameter("json");
			if ("".equals(json) || json == null) {
				token = request.getParameter("token");
				user = Verification.getUser(token, redisUtil);
				orderPaymentInfo.setId(UuidUtil.get32UUID());
			} else {
				orderPaymentInfo = JSON.parseObject(json, OrderPaymentInfo.class);
			}
			orderPaymentInfoService.deleteByPrimaryKey(orderPaymentInfo.getId());
			orderPaymentInfoService.insertSelective(orderPaymentInfo);
			ar.setSucceedMsg("付汇信息新增成功");
		} catch (NumberFormatException e) {
			ar.setFailMsg("参数错误！");
		} catch (UserException e) {
			ar.setFailMsg("令牌为空！");
			ar.setRes(-1);
			e.printStackTrace();
		} catch (Exception e) {
			ar.setFailMsg("付汇信息新增失败，出现异常");
			e.printStackTrace();
		}
		return ar;
	}

	/**
	 * 修改付汇信息 @Title: editPayment @Description: TODO @param @param
	 * request @param @param orderPaymentInfo @param @return @author
	 * dengyanghao @return AjaxRes @throws
	 */
	@RequestMapping(value = "/editPayment")
	@ResponseBody
	public AjaxRes editPayment(HttpServletRequest request, OrderPaymentInfo orderPaymentInfo) {
		AjaxRes ar = new AjaxRes();
		String token = null;
		User user = null;
		try {
			String json = request.getParameter("json");
			if ("".equals(json) || json == null) {
				token = request.getParameter("token");
				user = Verification.getUser(token, redisUtil);
			} else {
				orderPaymentInfo = JSON.parseObject(json, OrderPaymentInfo.class);
			}
			orderPaymentInfoService.updateByPrimaryKeyWithBLOBs(orderPaymentInfo);
			ar.setSucceedMsg("付汇信息修改成功");
		} catch (NumberFormatException e) {
			ar.setFailMsg("参数错误！");
		} catch (UserException e) {
			ar.setFailMsg("令牌为空！");
			ar.setRes(-1);
			e.printStackTrace();
		} catch (Exception e) {
			ar.setFailMsg("付汇信息修改失败，出现异常");
			e.printStackTrace();
		}
		return ar;
	}

	/**
	 * 查询业务消息中心 @Title: managerNotice @Description: TODO @param @param
	 * request @param @param orderId @param @return @author dengyanghao @return
	 * AjaxRes @throws
	 */
	@RequestMapping(value = "/managerNotice")
	@ResponseBody
	public AjaxRes managerNotice(HttpServletRequest request, String orderId, String containerId, Page page) {
		AjaxRes ar = new AjaxRes();
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			String token = request.getParameter("token");
			User user = Verification.getUser(token, redisUtil);
			List<ManagerNotice> managerNoticeList = managerNoticeService.select(orderId, containerId, user.getId(),
					page);
			page.setResults(managerNoticeList);
			map.put("list", page);
			ar.setSucceed(map, "业务消息中心列表获取成功");
		} catch (NumberFormatException e) {
			ar.setFailMsg("参数错误！");
		} catch (UserException e) {
			ar.setFailMsg("令牌为空！");
			ar.setRes(-1);
			e.printStackTrace();
		} catch (Exception e) {
			ar.setFailMsg("业务消息中心获取失败，出现异常");
			e.printStackTrace();
		}
		return ar;
	}

	/**
	 * 查询订单评分 @Title: score @Description: TODO @param @param
	 * request @param @param orderId @param @param boxNo @param @return @author
	 * dengyanghao @return AjaxRes @throws
	 */
	@RequestMapping(value = "/score")
	@ResponseBody
	public AjaxRes score(HttpServletRequest request, String orderId, String containerId) {
		AjaxRes ar = new AjaxRes();
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			String token = request.getParameter("token");
			User user = Verification.getUser(token, redisUtil);
			List<OrderEvaluationScore> orderEvaluationScoreList = orderEvaluationScoreService.select(orderId,
					containerId);
			Page page = new Page();
			page.setResults(orderEvaluationScoreList);
			map.put("list", page);
			ar.setSucceed(map, "订单评分列表获取成功");
		} catch (NumberFormatException e) {
			ar.setFailMsg("参数错误！");
		} catch (UserException e) {
			ar.setFailMsg("令牌为空！");
			ar.setRes(-1);
			e.printStackTrace();
		} catch (Exception e) {
			ar.setFailMsg("订单评分列表获取失败，出现异常");
			e.printStackTrace();
		}
		return ar;
	}

	/**
	 * 新增关注信息 @Title: addAttention @Description: TODO @param @param
	 * request @param @param attention @param @return @author
	 * dengyanghao @return AjaxRes @throws
	 */
	@RequestMapping(value = "/addAttention")
	@ResponseBody
	public AjaxRes addAttention(HttpServletRequest request, Attention attention) {
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
				attention = JSON.parseObject(json, Attention.class);
			}
			attentionService.deleteByPrimaryKey(attention.getId());
			attentionService.insertSelective(attention);
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
	public AjaxRes editAttention(HttpServletRequest request, Attention attention) {
		AjaxRes ar = new AjaxRes();
		String token = null;
		User user = null;
		try {
			String json = request.getParameter("json");
			if ("".equals(json) || json == null) {
				token = request.getParameter("token");
				user = Verification.getUser(token, redisUtil);
			} else {
				attention = JSON.parseObject(json, Attention.class);
			}
			attention.setUserId(user.getId());
			String status = request.getParameter("status");
			String isAttention = request.getParameter("isAttention");
			if (!"".equals(status) && null != status) {
				attention.setIsAttention(Integer.parseInt(status));
			} else {
				attention.setIsAttention(Integer.parseInt(isAttention));
			}
			Attention a = attentionService.selectByPrimaryKey(attention.getId());
			if (null == a) {
				attention.setAttentionDate(new Date());
				attention.setId(UuidUtil.get32UUID());
				attentionService.insertSelective(attention);
			} else {
				attention.setAttentionDate(new Date());
				attentionService.updateByPrimaryKeySelective(attention);

			}
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

	/**
	 * 新增报空通知 @Title: addAdvice @Description: TODO @param @param
	 * request @param @param orderContainerAdvice @param @return @author
	 * dengyanghao @return AjaxRes @throws
	 */
	@RequestMapping(value = "/addAdvice")
	@ResponseBody
	public AjaxRes addAdvice(HttpServletRequest request, OrderContainerAdvice orderContainerAdvice) {
		AjaxRes ar = new AjaxRes();
		String token = null;
		User user = null;
		try {
			String json = request.getParameter("json");
			if ("".equals(json) || json == null) {
				token = request.getParameter("token");
				user = Verification.getUser(token, redisUtil);
				orderContainerAdvice.setId(UuidUtil.get32UUID());
				orderContainerAdvice.setSponsor(user.getUsername());
				orderContainerAdvice.setSponsorId(user.getId());
				orderContainerAdvice.setAdivceTime(new Date());
			} else {
				orderContainerAdvice = JSON.parseObject(json, OrderContainerAdvice.class);
			}
			OrderContainerAdvice o = orderContainerAdviceService.selectByPrimaryKey(orderContainerAdvice.getId());
			OrderWorklistStatus ows = orderWorklistStatusService
					.selectByPrimaryKey(orderContainerAdvice.getContainerId());
			OrderImport oi = orderImportService.selectByPrimaryKey(orderContainerAdvice.getOrderId());
			OrderShippingInfo osi = orderShippingInfoService.selectByPrimaryKey(ows.getOrderShippingInfoId());
			DispatchOrder dispatchOrder = dispatchOrderService.selectByPrimaryKey(orderContainerAdvice.getDispatchId());
			String name = dispatchOrder.getDispatcher() == null ? "" : dispatchOrder.getDispatcher();
			user = userService.findByuserName(name);
			if (null != ows && Integer.parseInt(ows.getScheduleStatus()) != 6) {
				ar.setFailMsg("当前集装箱无法进行报空");
				return ar;
			}
			ManagerNotice managerNotice = new ManagerNotice();
			managerNotice.setId(UuidUtil.get32UUID());
			managerNotice.setCreatetime(new Date());
			managerNotice.setOrderId(orderContainerAdvice.getOrderId());
			if (user != null) {
				managerNotice.setReceiverId(user.getId());
			}
			managerNotice.setTitleType(ows.getType());
			String title = "";
			if (oi.getTransportWay().equals("0")) {
				title = "海运";
			} else if (oi.getTransportWay().equals("2")) {
				title = "空运";
			} else {
				title = "陆运";
			}
			if (ows.getType() == 1) {
				title += "进口";
			} else if (ows.getType() == 2) {
				title += "出口";
			}
			managerNotice.setContent(MessageXLS.returnMessage(InformEnum.THE_EMPTY_INFORM.getValue(),
					osi.getExtractOrderNo(), ows.getBoxNo(), ows.getProduct()));
			if (null != user) {
				if (null != user.getJpushId() && !"".equals(user.getJpushId())) {
					PushPayload payload = PushExample.buildPushObject_ios_audienceMore_messageWithExtras(
							user.getJpushId(), title, managerNotice.getContent());
					PushResult pu = jpushClient.sendPush(payload);
				}
				if (null != user.getEmail() && !"".equals(user.getEmail())) {
					emailDemo.send(user.getEmail(), title + "消息提醒", managerNotice.getContent());
				}
			}
			ows.setCusEmptyTime(orderContainerAdvice.getAdivceTime());
			ows.setIsempty(1);
			ows.setScheduleStatus(ScheduleStatusEnum.ALREADY_THE_EMPTY.getCode());
			orderWorklistStatusService.updateByPrimaryKeySelective(ows);
			managerNoticeService.insertSelective(managerNotice);
			if (null == o) {
				orderContainerAdviceService.insertSelective(orderContainerAdvice);
			} else {
				orderContainerAdviceService.updateByPrimaryKeySelective(orderContainerAdvice);
			}
			Transaction.commit(transactionManager);
			ar.setSucceedMsg("报空通知新增成功");
		} catch (NumberFormatException e) {
			ar.setFailMsg("参数错误！");
		} catch (UserException e) {
			ar.setFailMsg("令牌为空！");
			ar.setRes(-1);
			e.printStackTrace();
		} catch (Exception e) {
			Transaction.rollback(transactionManager);
			ar.setFailMsg("报空通知新增失败，出现异常");
			e.printStackTrace();
		}
		return ar;
	}

	/**
	 * 修改报空通知 @Title: eidtAdvice @Description: TODO @param @param
	 * request @param @param orderContainerAdvice @param @return @author
	 * dengyanghao @return AjaxRes @throws
	 */
	@RequestMapping(value = "/editAdvice")
	@ResponseBody
	public AjaxRes editAdvice(HttpServletRequest request, OrderContainerAdvice orderContainerAdvice) {
		AjaxRes ar = new AjaxRes();
		String token = null;
		User user = null;
		try {
			String json = request.getParameter("json");
			if ("".equals(json) || json == null) {
				token = request.getParameter("token");
				user = Verification.getUser(token, redisUtil);
				orderContainerAdvice.setSponsor(user.getUsername());
				orderContainerAdvice.setSponsorId(user.getId());
				orderContainerAdvice.setAdivceTime(new Date());
			} else {
				orderContainerAdvice = JSON.parseObject(json, OrderContainerAdvice.class);
			}
			orderContainerAdviceService.updateByPrimaryKeySelective(orderContainerAdvice);
			ar.setSucceedMsg("报空通知修改成功");
		} catch (NumberFormatException e) {
			ar.setFailMsg("参数错误！");
		} catch (UserException e) {
			ar.setFailMsg("令牌为空！");
			ar.setRes(-1);
			e.printStackTrace();
		} catch (Exception e) {
			ar.setFailMsg("报空通知修改失败，出现异常");
			e.printStackTrace();
		}
		return ar;
	}

	/**
	 * 查询报空通知 @Title: findAdvice @Description: TODO @param @param
	 * request @param @return @author dengyanghao @return AjaxRes @throws
	 */
	@RequestMapping(value = "/findAdvice")
	@ResponseBody
	public AjaxRes findAdvice(HttpServletRequest request, String orderId, String containerId) {

		AjaxRes ar = new AjaxRes();
		try {
			String token = request.getParameter("token");
			User user = Verification.getUser(token, redisUtil);
			Map<String, Object> map = new HashMap<String, Object>();
			OrderContainerAdvice orderContainerAdvice = orderContainerAdviceService.select(orderId, containerId,
					user.getId());
			ar.setSucceed(orderContainerAdvice, "报空通知获取成功");
		} catch (NumberFormatException e) {
			ar.setFailMsg("参数错误！");
		} catch (UserException e) {
			ar.setFailMsg("令牌为空！");
			ar.setRes(-1);
			e.printStackTrace();
		} catch (Exception e) {
			ar.setFailMsg("报空通知获取失败，出现异常");
			e.printStackTrace();
		}
		return ar;
	}

	/**
	 * 查询报空通知 @Title: listAdvice @Description: TODO @param @param
	 * request @param @return @author dengyanghao @return AjaxRes @throws
	 */
	@RequestMapping(value = "/listAdvice")
	@ResponseBody
	public AjaxRes listAdvice(HttpServletRequest request, OrderContainerAdvice orderContainerAdvice, Page page) {

		AjaxRes ar = new AjaxRes();
		try {
			String token = request.getParameter("token");
			User user = Verification.getUser(token, redisUtil);
			Map<String, Object> map = new HashMap<String, Object>();
			orderContainerAdvice.setDispatcher(user.getUsername());
			List<OrderContainerAdvice> orderContainerAdviceList = orderContainerAdviceService
					.findByPage(orderContainerAdvice, page);
			page.setResults(orderContainerAdviceList);
			map.put("list", page);
			ar.setSucceed(map, "报空通知列表获取成功");
		} catch (NumberFormatException e) {
			ar.setFailMsg("参数错误！");
		} catch (UserException e) {
			ar.setFailMsg("令牌为空！");
			ar.setRes(-1);
			e.printStackTrace();
		} catch (Exception e) {
			ar.setFailMsg("报空通知获取失败，出现异常");
			e.printStackTrace();
		}
		return ar;
	}

	/**
	 * 查询(集装箱) @Title: container @Description: TODO @param @param
	 * request @param @return @author dengyanghao @return AjaxRes @throws
	 */
	@RequestMapping(value = "/container")
	@ResponseBody
	public AjaxRes container(HttpServletRequest request, String id) {

		AjaxRes ar = new AjaxRes();
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			String token = request.getParameter("token");
			User user = Verification.getUser(token, redisUtil);
			OrderWorklistStatus orderWorklistStatusList = orderWorklistStatusService.selectByPrimaryKey(id);
			ar.setSucceed(orderWorklistStatusList, "集装箱列表获取成功");
		} catch (NumberFormatException e) {
			ar.setFailMsg("参数错误！");
		} catch (UserException e) {
			ar.setFailMsg("令牌为空！");
			ar.setRes(-1);
			e.printStackTrace();
		} catch (Exception e) {
			ar.setFailMsg("集装箱列表获取失败，出现异常");
			e.printStackTrace();
		}
		return ar;
	}

	/**
	 * 新增(集装箱) @Title: addContainer @Description: TODO @param @param
	 * request @param @return @author dengyanghao @return AjaxRes @throws
	 */
	@RequestMapping(value = "/addContainer")
	@ResponseBody
	public AjaxRes addContainer(HttpServletRequest request, OrderWorklistStatus orderWorklistStatus) {

		AjaxRes ar = new AjaxRes();
		String token = null;
		User user = null;
		try {
			String json = request.getParameter("json");
			System.out.println("新增柜的json="+json);
			if ("".equals(json) || json == null) {
				token = request.getParameter("token");
				user = Verification.getUser(token, redisUtil);
				orderWorklistStatus.setId(UuidUtil.get32UUID());
			} else {
				orderWorklistStatus = JSON.parseObject(json, OrderWorklistStatus.class);
			}
			OrderWorklistStatus o = orderWorklistStatusService.selectByPrimaryKey(orderWorklistStatus.getId());
			if (orderWorklistStatus.getType() == 2) {// 出口
				boolean flag = false;
				boolean bookingFlag = false;
				boolean ReleaseFlag = false;
				String type = "";
				if (null != orderWorklistStatus.getBookingDate() || null != orderWorklistStatus.getReleaseDate()) {
					flag = true;
					if (null != orderWorklistStatus.getBookingDate() && null == o) {
						bookingFlag = true;
					}
					if (null != orderWorklistStatus.getBookingDate() && null == o.getBookingDate()) {
						bookingFlag = true;
					}
					if (null != orderWorklistStatus.getReleaseDate() && null == o.getReleaseDate()) {
						ReleaseFlag = true;
					}
				}
				if (flag && (bookingFlag || ReleaseFlag)) {
					System.out.println(flag && (bookingFlag || ReleaseFlag));
					System.out.println("flag="+flag);
					System.out.println("ReleaseFlag="+ReleaseFlag);
					System.out.println("bookingFlag="+bookingFlag);
					if (bookingFlag) {// 如果当前集装箱订舱时间不为空，数据库里存在记录为空则触发消息推送集装箱状态变更
						orderWorklistStatus.setScheduleStatus(EScheduleStatusEnum.ALREADY_BOOKING.getCode());// 订舱
						type = InformEnum.BOOKING_INFORM.getValue();
					}
					if (ReleaseFlag) {// 如果当前集装箱电放时间不为空，数据库里存在记录为空则触发消息推送集装箱状态变更
						orderWorklistStatus.setScheduleStatus(EScheduleStatusEnum.ALREADY_RELEASE.getCode());// 电放
						type = InformEnum.TELEX_RELEASE_INFORM.getValue();
					}
					OrderExport orderExport = orderExportService.selectByPrimaryKey(orderWorklistStatus.getOrderId());
					String orderId = orderExport.getId();
					String trueCustomerId = orderExport.getCustomerName();
					OrderExportShippingInfo oesi = orderExportShippingInfoService.find(orderId,
							orderWorklistStatus.getId());
					String blNo = oesi.getBlNo();// 提单号
					ManagerNotice managerNotice = new ManagerNotice();// 消息中心
					String title = "";
					if (orderExport.getTransportWay().equals("0")) {
						title = "海运出口";
					} else if (orderExport.getTransportWay().equals("2")) {
						title = "空运出口";
					} else {
						title = "陆运出口";
					}
					managerNotice.setContent(MessageXLS.returnBookingMessage(type, orderWorklistStatus.getBookingNo(),
							orderWorklistStatus.getBoxNo(), orderWorklistStatus.getProduct()));
					managerNotice.setContainerId(orderWorklistStatus.getId());
					managerNotice.setOrderId(orderId);
					managerNotice.setTitleType(orderWorklistStatus.getType());
					managerNotice.setCreatetime(new Date());
					managerNotice.setTitle(title);
					List<CustomerAccount> accountList = cusUserService.findByCustomerId(trueCustomerId);
					Map<String, Object> map = new HashMap<>();
					map.put("orderId", orderWorklistStatus.getOrderId());
					map.put("containerId", orderWorklistStatus.getId());
					map.put("boxNo", orderWorklistStatus.getBoxNo());
					for (CustomerAccount customerAccount : accountList) {// 客户联系人
						managerNotice.setId(UuidUtil.get32UUID());
						managerNotice.setReceiverId(customerAccount.getId());
						managerNoticeService.insertSelective(managerNotice);
						/**
						 * App推送方法
						 */
						send(customerAccount, managerNotice.getContent(), title, map, orderWorklistStatus.getType());
					}
				}
			}
			if (null == o) {
				orderWorklistStatus.setCreatedate(new Date());
				orderWorklistStatusService.insertSelective(orderWorklistStatus);
			} else {
				orderWorklistStatusService.updateByPrimaryKeySelective(orderWorklistStatus);
			}
			Transaction.commit(transactionManager);
			ar.setSucceedMsg("集装箱新增成功");
		} catch (NumberFormatException e) {
			ar.setFailMsg("参数错误！");
		} catch (UserException e) {
			ar.setFailMsg("令牌为空！");
			ar.setRes(-1);
			e.printStackTrace();
		} catch (Exception e) {
			Transaction.rollback(transactionManager);
			ar.setFailMsg("集装箱新增失败，出现异常");
			e.printStackTrace();
		}
		return ar;
	}

	/**
	 * 修改(集装箱) @Title: editContainer @Description: TODO @param @param
	 * request @param @return @author dengyanghao @return AjaxRes @throws
	 */
	@RequestMapping(value = "/editContainer")
	@ResponseBody
	public AjaxRes editContainer(HttpServletRequest request, OrderWorklistStatus orderWorklistStatus) {
		AjaxRes ar = new AjaxRes();
		String token = null;
		User user = null;
		try {
			String json = request.getParameter("json");
			if ("".equals(json) || json == null) {
				token = request.getParameter("token");
				user = Verification.getUser(token, redisUtil);
			} else {
				System.out.println("json=" + json);
				orderWorklistStatus = JSON.parseObject(json, OrderWorklistStatus.class);
			}
			OrderWorklistStatus o = orderWorklistStatusService.selectByPrimaryKey(orderWorklistStatus.getId());
			OrderShippingInfo osi = null;
			if (null != o && o.getType() == 1) {
				if (null != orderWorklistStatus.getShipUnloadTime() && null != o && null == o.getShipUnloadTime()) {// 卸船时间不为空，本地数据库卸船时间为空则为卸船
					orderWorklistStatus.setScheduleStatus(ScheduleStatusEnum.ALREADY_CARTONNING.getCode());// 卸船
					orderWorklistStatus.setOutdate(null);
					orderWorklistStatus.setOutdate2(null);
				} else if (null != orderWorklistStatus.getOutdate() && null != o && null == o.getOutdate()) {// 码头出闸日期，，本地数据库码头出闸时间为空则为卸船
					orderWorklistStatus.setScheduleStatus(ScheduleStatusEnum.GATE_OUT.getCode());// 码头出闸
					
				}
				osi = orderShippingInfoService.selectByPrimaryKey(o.getOrderShippingInfoId());
			}
			OrderImport orderImport = null;
			OrderExport orderExport = null;

			OrderExportShippingInfo oesi = null;
			if (null != orderWorklistStatus.getOutdate2() && null != o && null == o.getOutdate2()) {// 放行
				if (o.getType() == 1) {
					orderWorklistStatus.setScheduleStatus(ScheduleStatusEnum.CUSTOMS_RELEASE.getCode());// 进口放行
					orderImport = orderImportService.selectByPrimaryKey(o.getOrderId());
					osi = orderShippingInfoService.selectByPrimaryKey(o.getOrderShippingInfoId());
				} else {
					orderWorklistStatus.setScheduleStatus(EScheduleStatusEnum.ALREADY_GREEN_LIGHT.getCode());// 出口放行
					orderExport = orderExportService.selectByPrimaryKey(o.getOrderId());
					oesi = orderExportShippingInfoService.selectByPrimaryKey(o.getOrderExportShippingInfoId());
				}
				orderWorklistStatus.setOutdate(null);
			}

			if (null != o && null != orderWorklistStatus.getScheduleStatus()
					&& !"".equals(orderWorklistStatus.getScheduleStatus())) {// 当数据库存在这条记录且需要通知时
				orderImport = orderImportService.selectByPrimaryKey(o.getOrderId());
				if (null != orderImport || null != orderExport) {
					String extractOrderNo = osi == null ? oesi.getBlNo() : osi.getExtractOrderNo();
					String transportWay = orderImport == null ? orderExport.getTransportWay()
							: orderImport.getTransportWay();// 判断当前集装箱属于进口还是出口
					String trueCustomerId = orderImport == null ? orderExport.getCustomerName()
							: orderImport.getTrueCustomerId();
					;
					String title = "";
					if (transportWay.equals("0")) {
						title = "海运" + (o.getType() == 1 ? "进口" : "出口");
					} else if (transportWay.equals("2")) {
						title = "空运" + (o.getType() == 1 ? "进口" : "出口");
					} else {
						title = "陆运" + (o.getType() == 1 ? "进口" : "出口");
					}
					ManagerNotice managerNotice = new ManagerNotice();
					managerNotice.setOrderId(o.getOrderId());
					managerNotice.setTitle(title);
					managerNotice.setTitleType(o.getType());
					managerNotice.setCreatetime(new Date());
					managerNotice.setContainerId(o.getId());
					List<CustomerAccount> accountList = cusUserService.findByCustomerId(trueCustomerId);
					for (CustomerAccount customerAccount : accountList) {
						managerNotice.setId(UuidUtil.get32UUID());
						managerNotice.setReceiverId(customerAccount.getId());
						String type = "";
						if (orderWorklistStatus.getScheduleStatus().equals("3")) {
							type = InformEnum.GREEN_LIGHT_INFORM.getValue();
						} else if (orderWorklistStatus.getScheduleStatus().equals("2")) {
							type = InformEnum.UNLOAD_SHIP_INFORM.getValue();
						} else if (orderWorklistStatus.getScheduleStatus().equals("4")
								|| orderWorklistStatus.getScheduleStatus().equals("6")) {
							type = InformEnum.GATE_OUT_INFORM.getValue();
						}
						managerNotice.setContent(
								MessageXLS.returnMessage(type, extractOrderNo, o.getBoxNo(), o.getProduct()));
						Map<String, Object> map = new HashMap<>();
						map.put("orderId", o.getOrderId());
						map.put("containerId", o.getId());
						map.put("boxNo", o.getBoxNo());
						send(customerAccount, managerNotice.getContent(), title, map, o.getType());
						managerNoticeService.insertSelective(managerNotice);// 新增消息通知
					}
				}
			}
			orderWorklistStatusService.updateByPrimaryKeySelective(orderWorklistStatus);
			Transaction.commit(transactionManager);
			ar.setSucceedMsg("集装箱修改成功");
		} catch (NumberFormatException e) {
			ar.setFailMsg("参数错误！");
		} catch (UserException e) {
			ar.setFailMsg("令牌为空！");
			ar.setRes(-1);
			e.printStackTrace();
		} catch (Exception e) {
			Transaction.rollback(transactionManager);
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			ar.setFailMsg("集装箱修改失败，出现异常");
			e.printStackTrace();
		}
		return ar;
	}

	/**
	 * 新增评价 @Title: editContainer @Description: TODO @param @param
	 * request @param @return @author dengyanghao @return AjaxRes @throws
	 */
	@RequestMapping(value = "/addEvaluate")
	@ResponseBody
	public AjaxRes addEvaluate(HttpServletRequest request, OrderEvaluationScore orderEvaluationScore) {
		AjaxRes ar = new AjaxRes();
		String token = null;
		User user = null;
		try {
			String json = request.getParameter("json");
			if ("".equals(json) || json == null) {
				token = request.getParameter("token");
				user = Verification.getUser(token, redisUtil);
				orderEvaluationScore.setId(UuidUtil.get32UUID());
			} else {
				orderEvaluationScore = JSON.parseObject(json, OrderEvaluationScore.class);
			}
			orderEvaluationScoreService.insertSelective(orderEvaluationScore);
			if (null != orderEvaluationScore.getContainerId() && !"".equals(orderEvaluationScore.getContainerId())) {
				OrderWorklistStatus ows = new OrderWorklistStatus();
				ows.setId(orderEvaluationScore.getContainerId());
				ows.setIsEvaluation("1");
				orderWorklistStatusService.updateByPrimaryKeySelective(ows);
			}
			Transaction.commit(transactionManager);
			ar.setSucceedMsg("评价新增成功");
		} catch (NumberFormatException e) {
			ar.setFailMsg("参数错误！");
		} catch (UserException e) {
			ar.setFailMsg("令牌为空！");
			ar.setRes(-1);
			e.printStackTrace();
		} catch (Exception e) {
			Transaction.rollback(transactionManager);
			ar.setFailMsg("评价新增失败，出现异常");
			e.printStackTrace();
		}
		return ar;
	}

	/**
	 * 订单查询 @Title: list @Description: TODO @param @param request @param @param
	 * orderImport @param @param page @param @return @author dengyanghao @return
	 * AjaxRes @throws
	 */
	@RequestMapping(value = "/worklist")
	@ResponseBody
	public AjaxRes list(HttpServletRequest request, OrderWorklistStatus ows, Page page) {
		AjaxRes ar = new AjaxRes();
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			String token = request.getParameter("token");
			String parentId = request.getParameter("parentId");
			User user = Verification.getUser(token, redisUtil);
			List<OrderWorklistStatus> orderWorkList = orderWorklistStatusService.findByPage(ows, user, page);
			page.setResults(orderWorkList);
			map.put("list", page);
			map.put("permitBtn", moduleManagerService.findBtn(user.getId(), Const.RESOURCES_TYPE_BUTTON, parentId));
			map.put("permitBtn2",moduleManagerService.findBtn2(user.getId(), Const.RESOURCES_TYPE_BUTTON));
			ar.setSucceed(map, "进口订单列表获取成功");
		} catch (NumberFormatException e) {
			ar.setFailMsg("参数错误！");
		} catch (UserException e) {
			ar.setFailMsg("令牌为空！");
			ar.setRes(-1);
			e.printStackTrace();
		} catch (Exception e) {
			ar.setFailMsg("进口订单列表获取失败，出现异常");
			e.printStackTrace();
		}
		return ar;
	}

	/**
	 * 订单查询 @Title: list @Description: TODO @param @param request @param @param
	 * orderImport @param @param page @param @return @author dengyanghao @return
	 * AjaxRes @throws
	 */
	@RequestMapping(value = "/getUggEqMessage")
	@ResponseBody
	public AjaxRes getUggEqMessage(HttpServletRequest request, String boxNo,String departTime) {
		AjaxRes ar = new AjaxRes();
		Map<String, Object> params = new HashMap<String, Object>();
		try {
			String token = request.getParameter("token");
			User user = Verification.getUser(token, redisUtil);
			params.put("boxNo", boxNo);
			params.put("departTime", departTime);
			String str = HttpUtils.post(uggUrl + "/api/getUggEqMessage", params, 5000, 5000, "UTF-8");
			ar = JSON.parseObject(str, AjaxRes.class);
		} catch (NumberFormatException e) {
			ar.setFailMsg("参数错误！");
		} catch (UserException e) {
			ar.setFailMsg("令牌为空！");
			ar.setRes(-1);
			e.printStackTrace();
		} catch (Exception e) {
			ar.setFailMsg("温度信息获取失败，出现异常");
			e.printStackTrace();
		}
		return ar;
	}

	/**
	 * 订单查询 @Title: list @Description: TODO @param @param request @param @param
	 * orderImport @param @param page @param @return @author dengyanghao @return
	 * AjaxRes @throws
	 */
	@RequestMapping(value = "/selectDown")
	@ResponseBody
	public AjaxRes selectDown(HttpServletRequest request, String keyWord) {
		AjaxRes ar = new AjaxRes();
		Map<String, Object> params = new HashMap<String, Object>();
		try {
			String token = request.getParameter("token");
			User user = Verification.getUser(token, redisUtil);
			List<String> list = orderWorklistStatusService.selectDown(keyWord, user);
			list.removeAll(Collections.singleton(null));// 剔除集合里null元素
			ar.setSucceed(list, "数据获取成功");
		} catch (NumberFormatException e) {
			ar.setFailMsg("参数错误！");
		} catch (UserException e) {
			ar.setFailMsg("令牌为空！");
			ar.setRes(-1);
			e.printStackTrace();
		} catch (Exception e) {
			ar.setFailMsg("数据获取失败，出现异常");
			e.printStackTrace();
		}
		return ar;
	}

	public void send(CustomerAccount customerAccount, String content, String title, Map<String, Object> map,
			Integer type) {
		Template template = new Template();
		if (null != customerAccount) {
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
			if (customerAccount.getStatus() == 0) {// 是否开启推送
				System.out.println(PushExample.MASTER_SECRET+"---"+PushExample.APP_KEY+customerAccount.getJpushId()+"---"+title+"---"+content);

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
			if (null != customerAccount.getWechatId() && !"".equals(customerAccount.getWechatId())) {
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

	public static void main(String[] args) {
		Token token = WeChatUtil.getToken(WeChatExploitMessage.AppID, WeChatExploitMessage.AppSecret);// 这里要注意，如果你是申请的正式公众号的话，获取token的时候，一定要在后台加上你的ip，不然获取token的时候会报错
		String access_token = token.getAccessToken();
	}
}
