package cn.szag.oms.manager.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;

import cn.szag.oms.manager.ajax.AjaxRes;
import cn.szag.oms.manager.domain.Attention;
import cn.szag.oms.manager.domain.DispatchOrder;
import cn.szag.oms.manager.domain.OrderClearanceInfo;
import cn.szag.oms.manager.domain.OrderContainerAdvice;
import cn.szag.oms.manager.domain.OrderPaymentInfo;
import cn.szag.oms.manager.domain.OrderReturnContainerInfo;
import cn.szag.oms.manager.domain.OrderShippingInfo;
import cn.szag.oms.manager.domain.OrderTransferInfo;
import cn.szag.oms.manager.domain.OrderWorklistStatus;
import cn.szag.oms.manager.domain.PictureAttachment;
import cn.szag.oms.manager.domain.User;
import cn.szag.oms.manager.enums.TopicEnum;
import cn.szag.oms.manager.utils.HttpRequestUtil;
import cn.szag.oms.manager.utils.KafkaProvider;
import cn.szag.oms.manager.utils.RedisUtil;
import cn.szag.oms.manager.utils.UserException;
import cn.szag.oms.manager.utils.Verification;






/**
 * 
* @ClassName: OrderIController 
* @Description: TODO
* @author dengyanghao
* @date 2019年9月16日 上午11:21:50
 */
@Controller
@RequestMapping(value ="/orderI")
public class OrderIController {
	private KafkaProvider kafkaProvider = new KafkaProvider();
	
	private final String manager_url = "http://127.0.0.1:8088/oms-manager/orderI";
	/**
	 * 新增海运信息
	* @Title: addShipping 
	* @Description: TODO 
	* @param @param request
	* @param @param orderShippingInfo
	* @param @return
	* @author dengyanghao
	* @return AjaxRes
	* @throws
	 */
	@RequestMapping(value = "/addShipping")
	@ResponseBody
	public AjaxRes addShipping(HttpServletRequest request,String json){
		AjaxRes ar = new AjaxRes();
		try {
			System.out.println(json);
			HttpRequestUtil.sendPost(manager_url+"/addShipping", "json="+json, false);
			kafkaProvider.sendMessage(TopicEnum.ADD_SHIPPING.getCode(), new Date().getTime(), TopicEnum.ADD_SHIPPING.getCode(), json);
			ar.setSucceedMsg("海运新增信息获取成功");
		} catch (NumberFormatException e) {
			ar.setFailMsg("参数错误！");
		} catch (UserException e) {
			ar.setFailMsg("令牌为空！");
			ar.setRes(-1);
			e.printStackTrace();
		} catch (Exception e) {
			ar.setFailMsg("海运新增获取失败，出现异常");
			e.printStackTrace();
		}
		return ar;
	}
	/**
	 * 修改海运信息
	* @Title: editShipping 
	* @Description: TODO 
	* @param @param request
	* @param @param orderShippingInfo
	* @param @return
	* @author dengyanghao
	* @return AjaxRes
	* @throws
	 */
	@RequestMapping(value = "/editShipping")
	@ResponseBody
	public AjaxRes editShipping(HttpServletRequest request,String json){
		AjaxRes ar = new AjaxRes();
		try {
			kafkaProvider.sendMessage(TopicEnum.EDIT_SHIPPING.getCode(), new Date().getTime(), TopicEnum.EDIT_SHIPPING .getCode(), json);
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
	 * 新增报关信息
	* @Title: addClearance 
	* @Description: TODO 
	* @param @param request
	* @param @param orderClearanceInfo
	* @param @return
	* @author dengyanghao
	* @return AjaxRes
	* @throws
	 */
	@RequestMapping(value = "/addClearance")
	@ResponseBody
	public AjaxRes addClearance(HttpServletRequest request,String json){
		AjaxRes ar = new AjaxRes();
		try {
			System.out.println(json);
			HttpRequestUtil.sendPost(manager_url+"/addClearance", "json="+json, false);
			kafkaProvider.sendMessage(TopicEnum.ADD_CLEARANCE.getCode(), new Date().getTime(), TopicEnum.ADD_CLEARANCE .getCode(), json);
			//orderClearanceInfoService.insertSelective(orderClearanceInfo);
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
	 * 修改报关信息
	* @Title: editClearance 
	* @Description: TODO 
	* @param @param request
	* @param @param orderId
	* @param @return
	* @author dengyanghao
	* @return AjaxRes
	* @throws
	 */
	@RequestMapping(value = "/editClearance")
	@ResponseBody
	public AjaxRes editClearance(HttpServletRequest request,String json){
		AjaxRes ar = new AjaxRes();
		try {
			kafkaProvider.sendMessage(TopicEnum.EDIT_CLEARANCE.getCode(), new Date().getTime(), TopicEnum.EDIT_CLEARANCE .getCode(), json);
			//orderClearanceInfoService.updateByPrimaryKeyWithBLOBs(orderClearanceInfo);//
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
	 * 新增运输信息
	* @Title: addTransfer 
	* @Description: TODO 
	* @param @param request
	* @param @param orderTransferInfo
	* @param @return
	* @author dengyanghao
	* @return AjaxRes
	* @throws
	 */
	@RequestMapping(value = "/addTransfer")
	@ResponseBody
	public AjaxRes addTransfer(HttpServletRequest request,String json){
		AjaxRes ar = new AjaxRes();
		try {
			System.out.println(json);
			HttpRequestUtil.sendPost(manager_url+"/addTransfer", "json="+json, false);
			kafkaProvider.sendMessage(TopicEnum.ADD_TRANSFER.getCode(), new Date().getTime(), TopicEnum.ADD_TRANSFER .getCode(), json);
			//orderTransferInfoService.deleteByPrimaryKey(orderTransferInfo.getId());//物理删除
			//orderTransferInfoService.insertSelective(orderTransferInfo);//新增
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
	 * 修改运输信息
	* @Title: editTransfer 
	* @Description: TODO 
	* @param @param request
	* @param @param orderTransferInfo
	* @param @return
	* @author dengyanghao
	* @return AjaxRes
	* @throws
	 */
	@RequestMapping(value = "/editTransfer")
	@ResponseBody
	public AjaxRes editTransfer(HttpServletRequest request,String json){
		AjaxRes ar = new AjaxRes();
		try {
			kafkaProvider.sendMessage(TopicEnum.EDIT_TRANSFER.getCode(), new Date().getTime(), TopicEnum.EDIT_TRANSFER .getCode(), json);
			//orderTransferInfoService.updateByPrimaryKeyWithBLOBs(orderTransferInfo);//修改
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
	 * 新增调度单
	* @Title: addDispatch 
	* @Description: TODO 
	* @param @param request
	* @param @param dispatchOrder
	* @param @return
	* @author dengyanghao
	* @return AjaxRes
	* @throws
	 */
	@RequestMapping(value = "/addDispatch")
	@ResponseBody
	public AjaxRes addDispatch(HttpServletRequest request,String json){
		AjaxRes ar = new AjaxRes();
		try {
			System.out.println(json);
			HttpRequestUtil.sendPost(manager_url+"/addDispatch", "json="+json, false);
			kafkaProvider.sendMessage(TopicEnum.ADD_DISPATCH.getCode(), new Date().getTime(), TopicEnum.ADD_DISPATCH .getCode(), json);
			ar.setSucceedMsg("调度单新增成功");
		} catch (NumberFormatException e) {
			ar.setFailMsg("参数错误！");
		} catch (UserException e) {
			ar.setFailMsg("令牌为空！");
			ar.setRes(-1);
			e.printStackTrace();
		} catch (Exception e) {
			ar.setFailMsg("调度单新增失败，出现异常");
			e.printStackTrace();
		}
		return ar;
	}
	/**
	 * 修改调度单
	* @Title: editDispatch 
	* @Description: TODO 
	* @param @param request
	* @param @param dispatchOrder
	* @param @return
	* @author dengyanghao
	* @return AjaxRes
	* @throws
	 */
	@RequestMapping(value = "/editDispatch")
	@ResponseBody
	public AjaxRes editDispatch(HttpServletRequest request,String json){
		AjaxRes ar = new AjaxRes();
		try {
			System.out.println(json);
			kafkaProvider.sendMessage(TopicEnum.EDIT_DISPATCH.getCode(), new Date().getTime(), TopicEnum.EDIT_DISPATCH .getCode(), json);
			//dispatchOrderService.updateByPrimaryKeySelective(dispatchOrder);//新增
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
	 * 新增运输附件
	* @Title: addPictureAttachment 
	* @Description: TODO 
	* @param @param request
	* @param @param pictureAttachment
	* @param @return
	* @author dengyanghao
	* @return AjaxRes
	* @throws
	 */
	@RequestMapping(value = "/addPictureAttachment")
	@ResponseBody
	public AjaxRes addPictureAttachment(HttpServletRequest request,String json){
		AjaxRes ar = new AjaxRes();
		try {
			System.out.println(json);
			HttpRequestUtil.sendPost(manager_url+"/addPictureAttachment", "json="+json, false);
			kafkaProvider.sendMessage(TopicEnum.ADD_PICTURE_ATTACHMENT.getCode(), new Date().getTime(), TopicEnum.ADD_PICTURE_ATTACHMENT .getCode(), json);
			//pictureAttachmentService.deleteByPrimaryKey(pictureAttachment.getId());//物理删除
			//pictureAttachmentService.insertSelective(pictureAttachment);//新增
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
	 * 修改运输附件
	* @Title: editPictureAttachment 
	* @Description: TODO 
	* @param @param request
	* @param @param pictureAttachment
	* @param @return
	* @author dengyanghao
	* @return AjaxRes
	* @throws
	 */
	@RequestMapping(value = "/editPictureAttachment")
	@ResponseBody
	public AjaxRes editPictureAttachment(HttpServletRequest request,String json){
		AjaxRes ar = new AjaxRes();
		try {
			kafkaProvider.sendMessage(TopicEnum.EDIT_PICTURE_ATTACHMENT.getCode(), new Date().getTime(), TopicEnum.EDIT_PICTURE_ATTACHMENT .getCode(), json);
			//pictureAttachmentService.updateByPrimaryKeyWithBLOBs(pictureAttachment);//新增
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
	 * 新增报空信息
	* @Title: addReturnContainer 
	* @Description: TODO 
	* @param @param request
	* @param @param orderReturnContainerInfo
	* @param @return
	* @author dengyanghao
	* @return AjaxRes
	* @throws
	 */
	@RequestMapping(value = "/addReturnContainer")
	@ResponseBody
	public AjaxRes addReturnContainer(HttpServletRequest request,String json){
		AjaxRes ar = new AjaxRes();
		try {
			System.out.println(json);
			HttpRequestUtil.sendPost(manager_url+"/addReturnContainer", "json="+json, false);
			kafkaProvider.sendMessage(TopicEnum.ADD_RETURN_CONTAINER.getCode(), new Date().getTime(), TopicEnum.ADD_RETURN_CONTAINER .getCode(), json);
			ar.setSucceedMsg("报空信息新增成功");
		} catch (NumberFormatException e) {
			ar.setFailMsg("参数错误！");
		} catch (UserException e) {
			ar.setFailMsg("令牌为空！");
			ar.setRes(-1);
			e.printStackTrace();
		} catch (Exception e) {
			ar.setFailMsg("报空信息新增失败，出现异常");
			e.printStackTrace();
		}
		return ar;
	}
	/**
	 * 修改报空信息
	* @Title: editReturnContainer 
	* @Description: TODO 
	* @param @param request
	* @param @param orderReturnContainerInfo
	* @param @return
	* @author dengyanghao
	* @return AjaxRes
	* @throws
	 */
	@RequestMapping(value = "/editReturnContainer")
	@ResponseBody
	public AjaxRes editReturnContainer(HttpServletRequest request,String json){
		AjaxRes ar = new AjaxRes();
		try {
			kafkaProvider.sendMessage(TopicEnum.EDIT_RETURN_CONTAINER.getCode(), new Date().getTime(), TopicEnum.EDIT_RETURN_CONTAINER .getCode(), json);
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
	 * 新增付汇信息
	* @Title: addPayment 
	* @Description: TODO 
	* @param @param request
	* @param @param orderPaymentInfo
	* @param @return
	* @author dengyanghao
	* @return AjaxRes
	* @throws
	 */
	@RequestMapping(value = "/addPayment")
	@ResponseBody
	public AjaxRes addPayment(HttpServletRequest request,String json){
		AjaxRes ar = new AjaxRes();
		try {
			HttpRequestUtil.sendPost(manager_url+"/addPayment", "json="+json, false);
			kafkaProvider.sendMessage(TopicEnum.ADD_PAYMENT.getCode(), new Date().getTime(), TopicEnum.ADD_PAYMENT .getCode(), json);
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
	 * 修改付汇信息
	* @Title: editPayment 
	* @Description: TODO 
	* @param @param request
	* @param @param orderPaymentInfo
	* @param @return
	* @author dengyanghao
	* @return AjaxRes
	* @throws
	 */
	@RequestMapping(value = "/editPayment")
	@ResponseBody
	public AjaxRes editPayment(HttpServletRequest request,String json){
		AjaxRes ar = new AjaxRes();
		try {
			kafkaProvider.sendMessage(TopicEnum.EDIT_PAYMENT.getCode(), new Date().getTime(), TopicEnum.EDIT_PAYMENT .getCode(), json);
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
	 * 新增关注信息
	* @Title: addAttention 
	* @Description: TODO 
	* @param @param request
	* @param @param attention
	* @param @return
	* @author dengyanghao
	* @return AjaxRes
	* @throws
	 */
	@RequestMapping(value = "/addAttention")
	@ResponseBody
	public AjaxRes addAttention(HttpServletRequest request,String json){
		AjaxRes ar = new AjaxRes();
		try {
			HttpRequestUtil.sendPost(manager_url+"/addAttention", "json="+json, false);
			kafkaProvider.sendMessage(TopicEnum.ADD_ATTENTION.getCode(), new Date().getTime(), TopicEnum.ADD_ATTENTION .getCode(), json);
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
	 * 修改关注关注信息
	* @Title: editAttention 
	* @Description: TODO 
	* @param @param request
	* @param @param attention
	* @param @return
	* @author dengyanghao
	* @return AjaxRes
	* @throws
	 */
	@RequestMapping(value = "/editAttention")
	@ResponseBody
	public AjaxRes editAttention(HttpServletRequest request,String json){
		AjaxRes ar = new AjaxRes();
		try {
			kafkaProvider.sendMessage(TopicEnum.EDIT_ATTENTION.getCode(), new Date().getTime(), TopicEnum.EDIT_ATTENTION .getCode(), json);
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
	 * 新增报空通知
	* @Title: addAdvice 
	* @Description: TODO 
	* @param @param request
	* @param @param orderContainerAdvice
	* @param @return
	* @author dengyanghao
	* @return AjaxRes
	* @throws
	 */
	@RequestMapping(value = "/addAdvice")
	@ResponseBody
	public AjaxRes addAdvice(HttpServletRequest request,String json){
		AjaxRes ar = new AjaxRes();
		try {
			//HttpRequestUtil.sendPost(manager_url+"/addAdvice", "json="+json, false);
			kafkaProvider.sendMessage(TopicEnum.ADD_ADVICE.getCode(), new Date().getTime(), TopicEnum.ADD_ADVICE .getCode(), json);
			ar.setSucceedMsg("报空通知新增成功");
		} catch (Exception e) {
			ar.setFailMsg("报空通知新增失败，出现异常");
			e.printStackTrace();
		}
		return ar;
	}
	/**
	 * 修改报空通知
	* @Title: eidtAdvice 
	* @Description: TODO 
	* @param @param request
	* @param @param orderContainerAdvice
	* @param @return
	* @author dengyanghao
	* @return AjaxRes
	* @throws
	 */
	@RequestMapping(value = "/eidtAdvice")
	@ResponseBody
	public AjaxRes eidtAdvice(HttpServletRequest request,String json){
		AjaxRes ar = new AjaxRes();
		try {
			kafkaProvider.sendMessage(TopicEnum.EDIT_ADVICE.getCode(), new Date().getTime(), TopicEnum.EDIT_ADVICE .getCode(), json);
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
	 * 新增(集装箱)
	* @Title: addContainer 
	* @Description: TODO 
	* @param @param request
	* @param @return
	* @author dengyanghao
	* @return AjaxRes
	* @throws
	 */
	@RequestMapping(value = "/addContainer")
	@ResponseBody
	public AjaxRes addContainer(HttpServletRequest request,String json){
		
		AjaxRes ar = new AjaxRes();
		try {
			HttpRequestUtil.sendPost(manager_url+"/addContainer", "json="+json, false);
			kafkaProvider.sendMessage(TopicEnum.ADD_CONTAINER.getCode(), new Date().getTime(), TopicEnum.ADD_CONTAINER .getCode(), json);
			ar.setSucceedMsg("集装箱新增成功");
		} catch (NumberFormatException e) {
			ar.setFailMsg("参数错误！");
		} catch (UserException e) {
			ar.setFailMsg("令牌为空！");
			ar.setRes(-1);
			e.printStackTrace();
		} catch (Exception e) {
			ar.setFailMsg("集装箱新增失败，出现异常");
			e.printStackTrace();
		}
		return ar;
	}
	/**
	 *修改(集装箱)
	* @Title: editContainer 
	* @Description: TODO 
	* @param @param request
	* @param @return orderWorklistStatus
	* @author dengyanghao
	* @return AjaxRes
	* @throws
	 */
	@RequestMapping(value = "/editContainer")
	@ResponseBody
	public AjaxRes editContainer(HttpServletRequest request,String json){
		
		AjaxRes ar = new AjaxRes();
		try {
			kafkaProvider.sendMessage(TopicEnum.EDIT_CONTAINER.getCode(), new Date().getTime(), TopicEnum.EDIT_CONTAINER .getCode(), json);
			ar.setSucceedMsg("集装箱修改成功");
		} catch (NumberFormatException e) {
			ar.setFailMsg("参数错误！");
		} catch (UserException e) {
			ar.setFailMsg("令牌为空！");
			ar.setRes(-1);
			e.printStackTrace();
		} catch (Exception e) {
			ar.setFailMsg("集装箱修改失败，出现异常");
			e.printStackTrace();
		}
		return ar;
	}
	/**
	 * 新增评价
	* @Title: addContainer 
	* @Description: TODO 
	* @param @param request
	* @param @return
	* @author dengyanghao
	* @return AjaxRes
	* @throws
	 */
	@RequestMapping(value = "/addEvaluate")
	@ResponseBody
	public AjaxRes addEvaluate(HttpServletRequest request,String json){
		
		AjaxRes ar = new AjaxRes();
		try {
			HttpRequestUtil.sendPost(manager_url+"/addEvaluate", "json="+json, false);
			kafkaProvider.sendMessage(TopicEnum.ADD_EVALUATE.getCode(), new Date().getTime(), TopicEnum.ADD_EVALUATE .getCode(), json);
			ar.setSucceedMsg("评价新增成功");
		} catch (NumberFormatException e) {
			ar.setFailMsg("参数错误！");
		} catch (UserException e) {
			ar.setFailMsg("令牌为空！");
			ar.setRes(-1);
			e.printStackTrace();
		} catch (Exception e) {
			ar.setFailMsg("评价新增失败，出现异常");
			e.printStackTrace();
		}
		return ar;
	}
	/**
	 *修改评价
	* @Title: editContainer 
	* @Description: TODO 
	* @param @param request
	* @param @return orderWorklistStatus
	* @author dengyanghao
	* @return AjaxRes
	* @throws
	 */
	@RequestMapping(value = "/editEvaluate")
	@ResponseBody
	public AjaxRes editEvaluate(HttpServletRequest request,String json){
		
		AjaxRes ar = new AjaxRes();
		try {
			kafkaProvider.sendMessage(TopicEnum.EDIT_EVALUATE.getCode(), new Date().getTime(), TopicEnum.EDIT_EVALUATE .getCode(), json);
			ar.setSucceedMsg("评价修改成功");
		} catch (NumberFormatException e) {
			ar.setFailMsg("参数错误！");
		} catch (UserException e) {
			ar.setFailMsg("令牌为空！");
			ar.setRes(-1);
			e.printStackTrace();
		} catch (Exception e) {
			ar.setFailMsg("评价修改失败，出现异常");
			e.printStackTrace();
		}
		return ar;
	}
	/**
	 * 通知免打扰新增
	* @Title: disturbAdd 
	* @Description: TODO 
	* @param @param request
	* @param @param json
	* @param @return
	* @author dengyanghao
	* @return AjaxRes
	* @throws
	 */
	@RequestMapping(value = "/disturbAdd")
	@ResponseBody
	public AjaxRes disturbAdd(HttpServletRequest request,String json){
		AjaxRes ar = new AjaxRes();
		try {
			HttpRequestUtil.sendPost(manager_url+"/board/disturbAdd", "json="+json, false);
			kafkaProvider.sendMessage(TopicEnum.ADD_DISTURB.getCode(), new Date().getTime(), TopicEnum.ADD_DISTURB .getCode(), json);
			ar.setSucceedMsg("消息通知免打扰新增成功");
		} catch (NumberFormatException e) {
			ar.setFailMsg("参数错误！");
		} catch (UserException e) {
			ar.setFailMsg("令牌为空！");
			ar.setRes(-1);
			e.printStackTrace();
		} catch (Exception e) {
			ar.setFailMsg("消息通知免打扰新增失败，出现异常");
			e.printStackTrace();
		}
		return ar;
	}
	/**
	 * 通知免打扰修改
	* @Title: disturbEdit 
	* @Description: TODO 
	* @param @param request
	* @param @param json
	* @param @return
	* @author dengyanghao
	* @return AjaxRes
	* @throws
	 */
	@RequestMapping(value = "/disturbEdit")
	@ResponseBody
	public AjaxRes disturbEdit(HttpServletRequest request,String json){
		AjaxRes ar = new AjaxRes();
		try {
			HttpRequestUtil.sendPost(manager_url+"/board/disturbEdit", "json="+json, false);
			kafkaProvider.sendMessage(TopicEnum.EDIT_DISTURB.getCode(), new Date().getTime(), TopicEnum.EDIT_DISTURB .getCode(), json);
			ar.setSucceedMsg("消息通知免打扰修改成功");
		} catch (NumberFormatException e) {
			ar.setFailMsg("参数错误！");
		} catch (UserException e) {
			ar.setFailMsg("令牌为空！");
			ar.setRes(-1);
			e.printStackTrace();
		} catch (Exception e) {
			ar.setFailMsg("消息通知免打扰修改成功，出现异常");
			e.printStackTrace();
		}
		return ar;
	}
}
