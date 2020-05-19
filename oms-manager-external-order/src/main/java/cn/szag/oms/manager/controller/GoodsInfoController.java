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
/**
 * 物资
* @ClassName: GoodsInfoController 
* @Description: TODO
* @author dengyanghao
* @date 2019年10月11日 下午6:06:27
 */
@Controller
@RequestMapping(value ="/goods")
public class GoodsInfoController {
private KafkaProvider kafkaProvider = new KafkaProvider();
	
	private final String manager_url = "http://127.0.0.1:8088/oms-manager/goods";
	/**
	 * 新增物资信息
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
			kafkaProvider.sendMessage(TopicEnum.ADD_GOODSINFO.getCode(), new Date().getTime(), TopicEnum.ADD_GOODSINFO.getCode(), json);
			ar.setSucceedMsg("新增物资信息成功");
		} catch (NumberFormatException e) {
			ar.setFailMsg("参数错误！");
		} catch (UserException e) {
			ar.setFailMsg("令牌为空！");
			ar.setRes(-1);
			e.printStackTrace();
		} catch (Exception e) {
			ar.setFailMsg("新增物资信息失败，出现异常");
			e.printStackTrace();
		}
		return ar;
	}
	/**
	 * 修改物资信息
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
			System.out.println("json="+json);
			HttpRequestUtil.sendPost(manager_url+"/edit", "json="+json, false);
			kafkaProvider.sendMessage(TopicEnum.EDIT_GOODSINFO.getCode(), new Date().getTime(), TopicEnum.EDIT_GOODSINFO.getCode(), json);
			ar.setSucceedMsg("修改物资信息成功");
		} catch (NumberFormatException e) {
			ar.setFailMsg("参数错误！");
		} catch (UserException e) {
			ar.setFailMsg("令牌为空！");
			ar.setRes(-1);
			e.printStackTrace();
		} catch (Exception e) {
			ar.setFailMsg("修改物资信息失败，出现异常");
			e.printStackTrace();
		}
		return ar;
	}
}
