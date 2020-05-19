package cn.szag.oms.manager.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.szag.oms.manager.ajax.AjaxRes;
import cn.szag.oms.manager.enums.TopicEnum;
import cn.szag.oms.manager.utils.HttpRequestUtil;
import cn.szag.oms.manager.utils.KafkaProvider;
import cn.szag.oms.manager.utils.UserException;

@Controller
@RequestMapping(value ="/cusUser")
public class CusUserController {
private KafkaProvider kafkaProvider = new KafkaProvider();
	
	private final String manager_url = "http://127.0.0.1:8088/oms-manager/cusUser";
	/**
	 * 新增联系人
	* @Title: add 
	* @Description: TODO 
	* @param @param request
	* @param @param add
	* @param @return
	* @author dengyanghao
	* @return AjaxRes
	* @throws
	 */
	@RequestMapping(value = "/add")
	@ResponseBody
	public AjaxRes add(HttpServletRequest request,String json){
		AjaxRes ar = new AjaxRes();
		try {
			System.out.println(json);
			HttpRequestUtil.sendPost(manager_url+"/add", "json="+json, false);
			kafkaProvider.sendMessage(TopicEnum.ADD_CUSUSER.getCode(), new Date().getTime(), TopicEnum.ADD_CUSUSER.getCode(), json);
			ar.setSucceedMsg("新增客户账户信息成功");
		} catch (NumberFormatException e) {
			ar.setFailMsg("参数错误！");
		} catch (UserException e) {
			ar.setFailMsg("令牌为空！");
			ar.setRes(-1);
			e.printStackTrace();
		} catch (Exception e) {
			ar.setFailMsg("新增客户账户信息失败，出现异常");
			e.printStackTrace();
		}
		return ar;
	}
	/**
	 * 修改联系人
	* @Title: edit 
	* @Description: TODO 
	* @param @param request
	* @param @return
	* @author dengyanghao
	* @return AjaxRes
	* @throws
	 */
	@RequestMapping(value = "/edit")
	@ResponseBody
	public AjaxRes edit(HttpServletRequest request,String json){
		AjaxRes ar = new AjaxRes();
		try {
			HttpRequestUtil.sendPost(manager_url+"/edit", "json="+json, false);
			kafkaProvider.sendMessage(TopicEnum.EDIT_CUSUSER.getCode(), new Date().getTime(), TopicEnum.EDIT_CUSUSER.getCode(), json);
			ar.setSucceedMsg("修改客户账户信息成功");
		} catch (NumberFormatException e) {
			ar.setFailMsg("参数错误！");
		} catch (UserException e) {
			ar.setFailMsg("令牌为空！");
			ar.setRes(-1);
			e.printStackTrace();
		} catch (Exception e) {
			ar.setFailMsg("修改客户账户信息失败，出现异常");
			e.printStackTrace();
		}
		return ar;
	}
}
