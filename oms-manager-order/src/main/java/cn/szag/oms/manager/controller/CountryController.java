package cn.szag.oms.manager.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;

import cn.szag.oms.manager.common.ajax.AjaxRes;
import cn.szag.oms.manager.common.domain.manager.Country;
import cn.szag.oms.manager.common.domain.manager.Port;
import cn.szag.oms.manager.common.domain.manager.User;
import cn.szag.oms.manager.common.page.Page;
import cn.szag.oms.manager.common.utils.RedisUtil;
import cn.szag.oms.manager.common.utils.Transaction;
import cn.szag.oms.manager.common.utils.UserException;
import cn.szag.oms.manager.common.utils.UuidUtil;
import cn.szag.oms.manager.common.utils.Verification;
import cn.szag.oms.manager.service.CountryService;


@RestController
@RequestMapping("/country")
public class CountryController {
	
	@Autowired
	private CountryService countryService;
	@Autowired
	private RedisUtil redisUtil;
	@Autowired
	private PlatformTransactionManager transactionManager;
	@RequestMapping(value = "/list")
	@ResponseBody
	public AjaxRes list(HttpServletRequest request,Page page){
		AjaxRes ar = new AjaxRes();
		try {
			Map<String,Object> map = new HashMap<String, Object>();
			String token = request.getParameter("token");
			User user = Verification.getUser(token,redisUtil);
			String keyword = request.getParameter("keyword");
			List<Country> portList = countryService.findByPage(keyword, page);
			page.setResults(portList);
			map.put("list", page);
			ar.setSucceed(map, "国家信息列表获取成功");
		} catch (NumberFormatException e) {
			ar.setFailMsg("参数错误！");
		} catch (UserException e) {
			ar.setFailMsg("令牌为空！");
			ar.setRes(-1);
			e.printStackTrace();
		} catch (Exception e) {
			ar.setFailMsg("国家信息列表获取失败，出现异常");
			e.printStackTrace();
		}
		return ar;
	}
	@RequestMapping(value = "/add")
	@ResponseBody
	public AjaxRes add(HttpServletRequest request, Country country){
		AjaxRes ar = new AjaxRes();
		String token = null;
		User user = null;
		try {
			Map<String,Object> map = new HashMap<String, Object>();
			String json = request.getParameter("json");
			if(json ==null || "".equals(json)){
				token = request.getParameter("token");
				user = Verification.getUser(token,redisUtil);
				country.setId(UuidUtil.get32UUID());
				country.setCreator(user.getUsername());
			}else{
				country = JSON.parseObject(json, Country.class);
			}
			Country c = countryService.find(country.getId());
			if(null == c){
				country.setCreatedate(new Date());
				countryService.insertSelective(country);
			}else{
				country.setLastupdate(new Date());
				countryService.updateByPrimaryKeySelective(country);
			}
			Transaction.commit(transactionManager);
			ar.setSucceedMsg("国家信息新增成功");
		} catch (NumberFormatException e) {
			ar.setFailMsg("参数错误！");
		} catch (UserException e) {
			ar.setFailMsg("令牌为空！");
			ar.setRes(-1);
			e.printStackTrace();
		} catch (Exception e) {
			Transaction.rollback(transactionManager);
			ar.setFailMsg("国家信息新增失败，出现异常");
			e.printStackTrace();
		}
		return ar;
	}
	@RequestMapping(value = "/edit")
	@ResponseBody
	public AjaxRes edit(HttpServletRequest request, Country country){
		AjaxRes ar = new AjaxRes();
		try {
			Map<String,Object> map = new HashMap<String, Object>();
			String token = request.getParameter("token");
			User user = Verification.getUser(token,redisUtil);
			country.setEditor(user.getUsername());
			countryService.updateByPrimaryKeySelective(country);
			Transaction.commit(transactionManager);
			ar.setSucceedMsg("国家信息修改成功");
		} catch (NumberFormatException e) {
			ar.setFailMsg("参数错误！");
		} catch (UserException e) {
			ar.setFailMsg("令牌为空！");
			ar.setRes(-1);
			e.printStackTrace();
		} catch (Exception e) {
			Transaction.rollback(transactionManager);
			ar.setFailMsg("国家信息修改失败，出现异常");
			e.printStackTrace();
		}
		return ar;
	}
	@RequestMapping(value = "/del")
	@ResponseBody
	public AjaxRes del(HttpServletRequest request, String id){
		AjaxRes ar = new AjaxRes();
		try {
			Map<String,Object> map = new HashMap<String, Object>();
			String token = request.getParameter("token");
			User user = Verification.getUser(token,redisUtil);
			Country country = new Country();
			country.setId(id);
			country.setDelflag(1);
			countryService.updateByPrimaryKeySelective(country);
			ar.setSucceedMsg("国家信息删除成功");
		} catch (NumberFormatException e) {
			ar.setFailMsg("参数错误！");
		} catch (UserException e) {
			ar.setFailMsg("令牌为空！");
			ar.setRes(-1);
			e.printStackTrace();
		} catch (Exception e) {
			ar.setFailMsg("国家信息删除失败，出现异常");
			e.printStackTrace();
		}
		return ar;
	}
	@RequestMapping(value = "/find")
	@ResponseBody
	public AjaxRes find(HttpServletRequest request, String id){
		AjaxRes ar = new AjaxRes();
		try {
			Map<String,Object> map = new HashMap<String, Object>();
			String token = request.getParameter("token");
			User user = Verification.getUser(token,redisUtil);
			countryService.selectByPrimaryKey(id,null);
			ar.setSucceed(map, "国家信息获取成功");
		} catch (NumberFormatException e) {
			ar.setFailMsg("参数错误！");
		} catch (UserException e) {
			ar.setFailMsg("令牌为空！");
			ar.setRes(-1);
			e.printStackTrace();
		} catch (Exception e) {
			ar.setFailMsg("国家信息获取失败，出现异常");
			e.printStackTrace();
		}
		return ar;
	}

}
