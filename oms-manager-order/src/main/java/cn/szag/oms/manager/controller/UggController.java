package cn.szag.oms.manager.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;

import cn.szag.oms.manager.common.ajax.AjaxRes;
import cn.szag.oms.manager.common.domain.manager.User;
import cn.szag.oms.manager.common.page.Page;
import cn.szag.oms.manager.common.utils.HttpUtils;
import cn.szag.oms.manager.common.utils.RedisUtil;
import cn.szag.oms.manager.common.utils.Url;
import cn.szag.oms.manager.common.utils.UserException;
import cn.szag.oms.manager.common.utils.Verification;

/**
 * 调用U管柜外部接口
* @ClassName: UggController 
* @Description: TODO
* @author dengyanghao
* @date 2019年11月4日 上午8:45:32
 */
@Controller
@RequestMapping(value ="/ugg")
public class UggController {
	
	public static String uggUrl=Url.uggUrl;
	@Autowired
	private RedisUtil redisUtil;
	/**
	 * 根据柜号查询实时温度
	* @Title: getUggEqMessage 
	* @Description: TODO 
	* @param @param request
	* @param @param boxNo
	* @param @return
	* @author dengyanghao
	* @return AjaxRes
	* @throws
	 */
	@RequestMapping(value = "/getUggEqMessage")
	@ResponseBody
	public AjaxRes getUggEqMessage(HttpServletRequest request,String boxNo){
		AjaxRes ar = new AjaxRes();
		Map<String,Object> params = new HashMap<String, Object>();
		try {
			String	token = request.getParameter("token");
			User  user = Verification.getUser(token,redisUtil);
			params.put("boxNo", boxNo);
		    String str = HttpUtils.post(uggUrl+"/api/getUggEqMessage", params, 5000, 5000, "UTF-8");
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
	 * 历史温度
	* @Title: getUggTemperature 
	* @Description: TODO 
	* @param @param request
	* @param @param boxNo
	* @param @return
	* @author dengyanghao
	* @return AjaxRes
	* @throws
	 */
	@RequestMapping(value = "/getUggTemperature")
	@ResponseBody
	public AjaxRes getUggTemperature(HttpServletRequest request,String boxNo,String departTime,String arrivalTime,Page page){
		AjaxRes ar = new AjaxRes();
		Map<String,Object> params = new HashMap<String, Object>();
		try {
			String	token = request.getParameter("token");
			User  user = Verification.getUser(token,redisUtil);
			params.put("boxNo", boxNo);
			params.put("departTime", departTime);
			params.put("arrivalTime", arrivalTime);
		    String str = HttpUtils.post(uggUrl+"/api/getUggHistory", params, 5000, 5000, "UTF-8");
			ar = JSON.parseObject(str, AjaxRes.class);
		} catch (NumberFormatException e) {
			ar.setFailMsg("参数错误！");
		} catch (UserException e) {
			ar.setFailMsg("令牌为空！");
			ar.setRes(-1);
			e.printStackTrace();
		} catch (Exception e) {
			ar.setFailMsg("历史温度信息列表获取失败，出现异常");
			e.printStackTrace();
		}
		return ar;
	}
	/**
	 * 历史轨迹
	* @Title: getUggHistoricalRoute 
	* @Description: TODO 
	* @param @param request
	* @param @param boxNo
	* @param @return
	* @author dengyanghao
	* @return AjaxRes
	* @throws
	 */
	@RequestMapping(value = "/getUggHistoricalRoute")
	@ResponseBody
	public AjaxRes getUggHistoricalRoute(HttpServletRequest request,String boxNo,String departTime,String arrivalTime,Page page){
		AjaxRes ar = new AjaxRes();
		Map<String,Object> params = new HashMap<String, Object>();
		try {
			String	token = request.getParameter("token");
			User  user = Verification.getUser(token,redisUtil);
			params.put("boxNo", boxNo);
			params.put("departTime", departTime);
			params.put("arrivalTime", arrivalTime);
		    String str = HttpUtils.post(uggUrl+"/api/getUggPointsLon", params, 5000, 5000, "UTF-8");
			ar = JSON.parseObject(str, AjaxRes.class);
		} catch (NumberFormatException e) {
			ar.setFailMsg("参数错误！");
		} catch (UserException e) {
			ar.setFailMsg("令牌为空！");
			ar.setRes(-1);
			e.printStackTrace();
		} catch (Exception e) {
			ar.setFailMsg("历史轨迹信息列表获取失败，出现异常");
			e.printStackTrace();
		}
		return ar;
	}
	/**
	 * 告警
	* @Title: getAlarmMessage 
	* @Description: TODO 
	* @param @param request
	* @param @param boxNo
	* @param @param departTime
	* @param @param page
	* @param @return
	* @author dengyanghao
	* @return AjaxRes
	* @throws
	 */
	@RequestMapping(value = "/getAlarmMessage")
	@ResponseBody
	public AjaxRes getAlarmMessage(HttpServletRequest request,String boxNo,String departTime,String arrivalTime,Page page){
		AjaxRes ar = new AjaxRes();
		Map<String,Object> params = new HashMap<String, Object>();
		try {
			String	token = request.getParameter("token");
			User  user = Verification.getUser(token,redisUtil);
			params.put("boxNo", boxNo);
			params.put("departTime", departTime);
			params.put("arrivalTime", arrivalTime);
			params.put("pageNum", page.getPageNum());
			params.put("pageSize", page.getPageSize());
		    String str = HttpUtils.post(uggUrl+"/api/getUggMessage", params, 5000, 5000, "UTF-8");
			ar = JSON.parseObject(str, AjaxRes.class);
		} catch (NumberFormatException e) {
			ar.setFailMsg("参数错误！");
		} catch (UserException e) {
			ar.setFailMsg("令牌为空！");
			ar.setRes(-1);
			e.printStackTrace();
		} catch (Exception e) {
			ar.setFailMsg("告警信息列表获取失败，出现异常");
			e.printStackTrace();
		}
		return ar;
	}
}
