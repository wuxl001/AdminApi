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
import cn.szag.oms.manager.common.domain.manager.CodeNationality;
import cn.szag.oms.manager.common.domain.manager.CodeNationality;
import cn.szag.oms.manager.common.domain.manager.User;
import cn.szag.oms.manager.common.page.Page;
import cn.szag.oms.manager.common.utils.RedisUtil;
import cn.szag.oms.manager.common.utils.Transaction;
import cn.szag.oms.manager.common.utils.UserException;
import cn.szag.oms.manager.common.utils.UuidUtil;
import cn.szag.oms.manager.common.utils.Verification;
import cn.szag.oms.manager.service.CodeNationalityService;


@RestController
@RequestMapping("/nationality")
public class CodeNationalityController {
	@Autowired
	private CodeNationalityService codeNationalityService;

	@Autowired
	private RedisUtil redisUtil;
	@Autowired
	private PlatformTransactionManager transactionManager;

	@RequestMapping(value = "/list")
	@ResponseBody
	public AjaxRes list(HttpServletRequest request, Page page) {
		AjaxRes ar = new AjaxRes();
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			String token = request.getParameter("token");
			User user = Verification.getUser(token, redisUtil);
			String keyword = request.getParameter("keyword");
			List<CodeNationality> portList = codeNationalityService.selectList(keyword, page);
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
	public AjaxRes add(HttpServletRequest request, CodeNationality CodeNationality) {
		AjaxRes ar = new AjaxRes();
		String token = null;
		User user = null;
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			String json = request.getParameter("json");
			if (json == null || "".equals(json)) {
				token = request.getParameter("token");
				user = Verification.getUser(token, redisUtil);
				CodeNationality.setCreator(user.getUsername());
			} else {
				CodeNationality = JSON.parseObject(json, CodeNationality.class);
			}
			CodeNationality c = codeNationalityService.selectByPrimaryKey(CodeNationality.getId());
			if(c == null){
				CodeNationality.setCreatedate(new Date());
				codeNationalityService.insertSelective(CodeNationality);
			}else{
				CodeNationality.setLastupdate(new Date());
				codeNationalityService.updateByPrimaryKeySelective(CodeNationality);
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
	public AjaxRes edit(HttpServletRequest request, CodeNationality CodeNationality) {
		AjaxRes ar = new AjaxRes();
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			String token = request.getParameter("token");
			User user = Verification.getUser(token, redisUtil);
			CodeNationality.setEditor(user.getUsername());
			codeNationalityService.updateByPrimaryKeySelective(CodeNationality);
			ar.setSucceedMsg("国家信息修改成功");
		} catch (NumberFormatException e) {
			ar.setFailMsg("参数错误！");
		} catch (UserException e) {
			ar.setFailMsg("令牌为空！");
			ar.setRes(-1);
			e.printStackTrace();
		} catch (Exception e) {
			ar.setFailMsg("国家信息修改失败，出现异常");
			e.printStackTrace();
		}
		return ar;
	}

	@RequestMapping(value = "/del")
	@ResponseBody
	public AjaxRes del(HttpServletRequest request, Integer id) {
		AjaxRes ar = new AjaxRes();
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			String token = request.getParameter("token");
			User user = Verification.getUser(token, redisUtil);
			CodeNationality CodeNationality = new CodeNationality();
			CodeNationality.setId(id);
			CodeNationality.setDelFlag(1);
			codeNationalityService.updateByPrimaryKeySelective(CodeNationality);
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
	public AjaxRes find(HttpServletRequest request, String id) {
		AjaxRes ar = new AjaxRes();
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			String token = request.getParameter("token");
			User user = Verification.getUser(token, redisUtil);
			codeNationalityService.selectByPrimaryKey(Integer.parseInt(id));
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
