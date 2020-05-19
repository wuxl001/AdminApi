package cn.szag.oms.manager.controller;

import java.util.ArrayList;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.szag.oms.manager.common.ajax.AjaxRes;
import cn.szag.oms.manager.common.domain.manager.DataDict;
import cn.szag.oms.manager.common.domain.manager.User;
import cn.szag.oms.manager.common.page.Page;
import cn.szag.oms.manager.common.utils.Const;
import cn.szag.oms.manager.common.utils.RedisUtil;
import cn.szag.oms.manager.common.utils.UserException;
import cn.szag.oms.manager.common.utils.UuidUtil;
import cn.szag.oms.manager.common.utils.Verification;
import cn.szag.oms.manager.service.DataDictService;
import cn.szag.oms.manager.service.ModuleManagerService;

@Controller
@RequestMapping(value ="/data")
public class DataDictController {
	
	@Autowired
	public DataDictService dataDictService;
	@Autowired
	private ModuleManagerService moduleManagerService;
	@Autowired
	private RedisUtil redisUtil;
	
	// 列表接口（当前页 pageNum，pageSize 分页大小，字典名称 keyword）
	@RequestMapping(value = "/list")
	@ResponseBody
	public AjaxRes list(HttpServletRequest request,DataDict dataDict,Page page) {
		AjaxRes ar = new AjaxRes();
		try {
			String token = request.getParameter("token");
			String parentId = request.getParameter("parentId");
			User user = Verification.getUser(token,redisUtil);
			List<DataDict> dataDictList = dataDictService.findDataDictByPage(page,dataDict);
			//page.setTotalRecord(dataDictService.countByDelFlag()); // 该方法不适用
//			page.setTotalRecord(dataDictService.findDataDictByPage(null,dataDict).size());
			page.setResults(dataDictList);
			Map<String, Object> p = new HashMap<String, Object>();
			p.put("list", page);
			p.put("permitBtn", moduleManagerService.findBtn(user.getId(), Const.RESOURCES_TYPE_BUTTON, parentId));
			ar.setSucceed(p, "操作成功");
		} catch (NumberFormatException e) {
			ar.setFailMsg("参数错误！");
		} catch (UserException e) {
			ar.setFailMsg("令牌为空！");
			ar.setRes(-1);
		} catch (Exception e) {
			ar.setRes(0);
			ar.setResMsg("操作失败");
			e.printStackTrace();
		}
		return ar;
	}
	
	// 查看详情接口（查询子项）（请求名 find 是根据《产品功能概要设计》命名）
	@RequestMapping(value = "/find")
	@ResponseBody
	public AjaxRes listById(HttpServletRequest request,String id){
		AjaxRes ar = new AjaxRes();
		try {
			String token = request.getParameter("token");
			User user = Verification.getUser(token,redisUtil);
			DataDict dataDict = dataDictService.findDataDictById(id);
			ar.setSucceed(dataDict);
			ar.setRes(1);
			ar.setResMsg("操作成功");
		} catch (Exception e) {
			ar.setRes(0);
			ar.setResMsg("操作失败");
			e.printStackTrace();
		}
		return ar;
	}
	
	// 添加字典
	@RequestMapping(value = "/add")
	@ResponseBody
	public AjaxRes add(DataDict dataDict,HttpServletRequest request) {
		AjaxRes ar = new AjaxRes();
		try {
			dataDict.setId(UuidUtil.get32UUID());
			String token = request.getParameter("token");
			User user = Verification.getUser(token,redisUtil);
			if(dataDict.getParentId() == null){
				dataDict.setParentId("0"); // 默认值为 0
			}
			
			// 判断新增的字典名称是否重复
			List<DataDict> dataDictList = dataDictService.selectByNameAndParentId(dataDict);
			if(dataDictList.size() > 0){
				ar.setRes(0);
				ar.setResMsg("对不起，字典名称【" + dataDict.getName() + "】已存在");
				return ar;
			}
			
			dataDict.setCreatorId(user.getId());
			dataDict.setCreator(user.getUsername());
			dataDict.setCreateTime(new Date());
			dataDict.setStatus(1); // 状态（0 NO/1 YES）
			dataDict.setDelFlag("0"); // 删除状态（0 正常/1 删除）
			if(dataDict.getParentId()==null&&dataDict.getParentId().equals("")){
				dataDict.setParentId("0");
			}
			dataDictService.insert(dataDict);
			ar.setRes(1);
			ar.setResMsg("操作成功");
		} catch (NumberFormatException e) {
			ar.setFailMsg("参数错误！");
		} catch (UserException e) {
			ar.setFailMsg("令牌为空！");
			ar.setRes(-1);
		} catch (Exception e) {
			ar.setRes(0);
			ar.setResMsg("操作失败");
			e.printStackTrace();
		}
		return ar;
	}
	
	// 列表（根据 id 查询)
	@RequestMapping(value = "/findById")
	@ResponseBody
	public AjaxRes listDataDict(String parentId) {
		AjaxRes ar = new AjaxRes();
		try {
			List<DataDict> dataDictList = dataDictService.findByParentId(parentId);
			ar.setObj(dataDictList);
			Map<String, Object> p = new HashMap<String, Object>();
			p.put("list",dataDictList);
			ar.setSucceed(p);
			ar.setRes(1);
			ar.setResMsg("操作成功");
		} catch (Exception e) {
			ar.setRes(0);
			ar.setResMsg("操作失败");
			e.printStackTrace();
		}
		return ar;
	}
	
	// 修改接口
	@RequestMapping(value = "/edit")
	@ResponseBody
	public AjaxRes update(DataDict dataDict,HttpServletRequest request) {
		AjaxRes ar = new AjaxRes();
		try {
			String token = request.getParameter("token");
			User user = Verification.getUser(token,redisUtil);
			dataDict.setUpdatorId(user.getId());
			dataDict.setUpdator(user.getUsername());
			dataDict.setUpdateTime(new Date());
			dataDictService.updateDataDict(dataDict);
			ar.setRes(1);
			ar.setResMsg("操作成功");
		} catch (NumberFormatException e) {
			ar.setFailMsg("参数错误！");
		} catch (UserException e) {
			ar.setFailMsg("令牌为空！");
			ar.setRes(-1);
		} catch (Exception e) {
			ar.setRes(0);
			ar.setResMsg("操作失败");
			e.printStackTrace();
		}
		return ar;
	}
	
	// 删除接口
	@RequestMapping(value = "/del")
	@ResponseBody
	public AjaxRes del(String id,HttpServletRequest request) {
		AjaxRes ar = new AjaxRes();
		try {
			String token = request.getParameter("token");
			User user = Verification.getUser(token,redisUtil);
			if(!"".equals(id) && null !=id){
				String[] ids = id.split(",");
				for(String id2:ids){
					DataDict dataDict = new DataDict();
					dataDict.setId(id2);
					dataDict.setUpdatorId(user.getId());
					dataDict.setUpdator(user.getUsername());
					dataDict.setUpdateTime(new Date());
					dataDict.setDelFlag("1");
					// 根据 id 删除父项
					dataDictService.del(dataDict);
					// 根据 parentId 删除子项
					dataDictService.delByParentId(dataDict);
				}
			}
			ar.setRes(1);
			ar.setResMsg("操作成功");
		} catch (NumberFormatException e) {
			ar.setFailMsg("参数错误！");
		} catch (UserException e) {
			ar.setFailMsg("令牌为空！");
			ar.setRes(-1);
		} catch (Exception e) {
			ar.setRes(0);
			ar.setResMsg("操作失败");
			e.printStackTrace();
		}
		return ar;
	}
	
	// 列表（根据 name 查询)
	@RequestMapping(value = "/selectByName")
	@ResponseBody
	public AjaxRes selectByName(String name) {
		AjaxRes ar = new AjaxRes();
		try {
			List<DataDict> dataDictList = dataDictService.selectByName(name);
			ar.setObj(dataDictList);
			Map<String, Object> p = new HashMap<String, Object>();
			// 前端只要返回 name 值
			List<Map> listMap = new ArrayList<Map>();
			for(int i=0;i<dataDictList.size();i++){
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("name",dataDictList.get(i).getName());
				listMap.add(map);
			}
			p.put("list",listMap);
			//p.put("list",dataDictList);
			ar.setSucceed(p);
			ar.setRes(1);
			ar.setResMsg("操作成功");
		} catch (Exception e) {
			ar.setRes(0);
			ar.setResMsg("操作失败");
			e.printStackTrace();
		}
		return ar;
	}
	
}