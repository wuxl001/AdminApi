package cn.szag.oms.manager.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.szag.oms.manager.common.ajax.AjaxRes;
import cn.szag.oms.manager.common.domain.manager.Country;
import cn.szag.oms.manager.common.domain.manager.GoodsInfo;
import cn.szag.oms.manager.common.domain.manager.OrderNationalFlag;
import cn.szag.oms.manager.common.domain.manager.User;
import cn.szag.oms.manager.common.page.Page;
import cn.szag.oms.manager.common.utils.RedisUtil;
import cn.szag.oms.manager.common.utils.UserException;
import cn.szag.oms.manager.common.utils.Verification;
import cn.szag.oms.manager.service.CountryService;
import cn.szag.oms.manager.service.OrderNationalFlagService;
/**
 * 国家国旗关系
* @ClassName: OrderNationalFlagController 
* @Description: TODO
* @author dengyanghao
* @date 2019年9月27日 上午9:18:02
 */
@Controller
@RequestMapping(value ="/board")
public class OrderNationalFlagController {
	@Autowired
	private CountryService countryService;
	@Autowired
	private RedisUtil redisUtil;
	@RequestMapping(value = "/national_flag_pic")
	@ResponseBody
	public AjaxRes pic(HttpServletRequest request, String id ,String name){
		AjaxRes ar = new AjaxRes();
		try {
			String token = request.getParameter("token");
			User user = Verification.getUser(token,redisUtil);
			user.setDataPermission(1);
			Country orderNationalFlag = countryService.selectByPrimaryKey(id,name);
			ar.setSucceed(orderNationalFlag, "国家国旗关系成功");
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
}
