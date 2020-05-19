package cn.szag.oms.manager.controller;

import java.io.File;
import java.io.FileInputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONObject;

import cn.szag.oms.manager.common.ajax.AjaxRes;
import cn.szag.oms.manager.common.domain.manager.Advertisement;
import cn.szag.oms.manager.common.domain.manager.OrderAttachment;
import cn.szag.oms.manager.common.domain.manager.OrderImport;
import cn.szag.oms.manager.common.domain.manager.User;
import cn.szag.oms.manager.common.enums.AuditStatusEnum;
import cn.szag.oms.manager.common.enums.IssueStatusEnum;
import cn.szag.oms.manager.common.page.Page;
import cn.szag.oms.manager.common.utils.Const;
import cn.szag.oms.manager.common.utils.FtpUtils;
import cn.szag.oms.manager.common.utils.HttpRequestUtil;
import cn.szag.oms.manager.common.utils.RedisUtil;
import cn.szag.oms.manager.common.utils.Transaction;
import cn.szag.oms.manager.common.utils.UserException;
import cn.szag.oms.manager.common.utils.UuidUtil;
import cn.szag.oms.manager.common.utils.Verification;
import cn.szag.oms.manager.service.AdvertisementService;
import cn.szag.oms.manager.service.ModuleManagerService;

@Controller
@RequestMapping(value = "/advertisement")
public class AdvertisementController {
	@Autowired
	private RedisUtil redisUtil;
	@Autowired
	private AdvertisementService advertisementService;
	@Autowired
	private PlatformTransactionManager transactionManager;
	@Autowired
	private ModuleManagerService moduleManagerService;
	
	private static final String FILE_PATH= "/images/";
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
			Advertisement adv = new Advertisement();
			Map<String, Object> list = new HashMap<String, Object>();
			adv.setId(UuidUtil.get32UUID());
			String num = advertisementService.generateNo(adv);
			list.put("No", num);
			ar.setSucceed(list,"编号获取成功");
		} catch (NumberFormatException e) {
			return new AjaxRes(Const.FAIL, "参数错误！！");
		} catch (UserException e) {
			e.printStackTrace();
			return new AjaxRes(Const.FAIL, "令牌为空！");
		} catch (Exception e) {
			ar.setFailMsg("编号获取失败");
			e.printStackTrace();
		}
		return ar;
	}
	// 列表接口（当前页 pageNum，pageSize 分页大小，字典名称 keyword）
	@RequestMapping(value = "/getAdvList")
	@ResponseBody
	public AjaxRes getAdvList(HttpServletRequest request, Advertisement adv, Page page) {
		AjaxRes ar = new AjaxRes();
		try {
			String token = request.getParameter("token");
			String parentId = request.getParameter("parentId");
			User user = Verification.getUser(token, redisUtil);
			List<Advertisement> advertisementList = advertisementService.getAdvList(adv, page);
			page.setResults(advertisementList);
			Map<String, Object> p = new HashMap<String, Object>();
			p.put("list", page);
			p.put("permitBtn", moduleManagerService.findBtn(user.getId(), Const.RESOURCES_TYPE_BUTTON, parentId));
			p.put("permitBtn2",moduleManagerService.findBtn2(user.getId(), Const.RESOURCES_TYPE_BUTTON));
			ar.setSucceed(p, "操作成功");
		} catch (NumberFormatException e) {
			ar.setFailMsg("参数错误！");
		} catch (UserException e) {
			ar.setFailMsg("令牌为空！");
			ar.setRes(-1);
		} catch (Exception e) {
			ar.setFailMsg("操作失败");
			e.printStackTrace();
		}
		return ar;
	}
	/**
	 * 查询广告信息
	* @Title: add 
	* @Description: TODO 
	* @param @param request
	* @param @param adv
	* @param @return
	* @author dengyanghao
	* @return AjaxRes
	* @throws
	 */
	@RequestMapping(value = "/getAdvDeail")
	@ResponseBody
	public AjaxRes getAdvDeail(HttpServletRequest request, String id) {
		AjaxRes ar = new AjaxRes();
		try {
			String token = request.getParameter("token");
			User user = Verification.getUser(token, redisUtil);
			Advertisement advertisement= advertisementService.selectByPrimaryKey(id);
			ar.setSucceed(advertisement,"广告信息查询成功");
		} catch (NumberFormatException e) {
			ar.setFailMsg("参数错误！");
		} catch (UserException e) {
			ar.setFailMsg("令牌为空！");
			ar.setRes(-1);
		} catch (Exception e) {
			ar.setFailMsg("广告信息查询失败,出现异常");
			e.printStackTrace();
		}
		return ar;
	}
	/**
	 * 新增广告
	* @Title: add 
	* @Description: TODO 
	* @param @param request
	* @param @param adv
	* @param @return
	* @author dengyanghao
	* @return AjaxRes
	* @throws
	 */
	@RequestMapping(value = "/save")
	@ResponseBody
	public AjaxRes save(HttpServletRequest request, Advertisement adv) {
		AjaxRes ar = new AjaxRes();
		String type = "";
		try {
			String token = request.getParameter("token");
			User user = Verification.getUser(token, redisUtil);
			Advertisement advertisement = advertisementService.selectByPrimaryKey(adv.getId());
			if(null != advertisement){
				type = "修改";
				adv.setUpdator(user.getUsername());
				adv.setUpdatetime(new Date());
				adv.setUpdatorid(user.getId());
				advertisementService.updateByPrimaryKeySelective(adv);
			}else{
				type = "新增";
				adv.setId(UuidUtil.get32UUID());
				adv.setCreator(user.getUsername());
				adv.setCreatetime(new Date());
				adv.setCreatorid(user.getId());
				advertisementService.insertSelective(adv);
			}
			Transaction.commit(transactionManager);
			ar.setSucceedMsg("广告信息"+type+"成功");
		} catch (NumberFormatException e) {
			ar.setFailMsg("参数错误！");
		} catch (UserException e) {
			ar.setFailMsg("令牌为空！");
			ar.setRes(-1);
		} catch (Exception e) {
			Transaction.rollback(transactionManager);
			ar.setFailMsg("广告信息"+type+"失败,出现异常");
			e.printStackTrace();
		}
		return ar;
	}
	/**
	 * 删除广告
	* @Title: del 
	* @Description: TODO 
	* @param @param request
	* @param @param adv
	* @param @return
	* @author dengyanghao
	* @return AjaxRes
	* @throws
	 */
	@RequestMapping(value = "/operation")
	@ResponseBody
	public AjaxRes operation(HttpServletRequest request, String id ,Integer type) {
		AjaxRes ar = new AjaxRes();
		try {
			String token = request.getParameter("token");
			User user = Verification.getUser(token, redisUtil);
			Advertisement adv = advertisementService.selectByPrimaryKey(id);
			if(type == 0){
				if(adv.getExaminestatus() != AuditStatusEnum.ALREADY_AUDIT.getCode()){//当前广告状态不为已审核
					adv.setDelFlag(1);
				}else{
					ar.setFailMsg("对不起，已审核记录不能删除");
					return ar;
				}
			}else if(type == 1){//审核
				//审核状态未待审核
				if(adv.getExaminestatus() == AuditStatusEnum.STAY_AUDIT.getCode()){//当前广告状态为待审核
					adv.setExaminestatus(AuditStatusEnum.ALREADY_AUDIT.getCode());//已审核
					adv.setPublicstatus(IssueStatusEnum.ALREADY_ISSUE.getCode());//已发布
					adv.setExamineperson(user.getUsername());
					adv.setExaminepersonid(user.getId());
					adv.setExaminetime(new Date());
				}else{
					ar.setFailMsg("对不起，只允许待审核状态记录进行操作");
					return ar;
				}
			}else if(type == 2){//退回
				//审核状态未待审核
				if(adv.getExaminestatus() == AuditStatusEnum.STAY_AUDIT.getCode()){//当前广告状态为待审核
					adv.setExaminestatus(AuditStatusEnum.ALREADY_SEND_BACK.getCode());//已退回
					adv.setPublicstatus(IssueStatusEnum.STAY_ISSUE.getCode());//待发布
				}else{
					ar.setFailMsg("对不起，只允许待审核状态记录进行操作");
					return ar;
				}
			}else if(type == 4){//发布
				//当前广告发布状态为停止发布，审核状态为已审核
				if(adv.getPublicstatus() == IssueStatusEnum.ALREADY_STOP.getCode() && adv.getExaminestatus() == AuditStatusEnum.ALREADY_AUDIT.getCode()){
					adv.setPublicstatus(IssueStatusEnum.ALREADY_ISSUE.getCode());//发布
					adv.setPublictime(new Date());
					adv.setPublicpersonid(user.getId());
				}else{
					ar.setFailMsg("对不起，只允许待停止状态记录进行操作");
					return ar;
				}
			}else if(type == 5){//停止发布
				//当前广告发布状态为已发布，审核状态为已审核
				if(adv.getPublicstatus() == IssueStatusEnum.ALREADY_ISSUE.getCode() && adv.getExaminestatus() == AuditStatusEnum.ALREADY_AUDIT.getCode()){
					adv.setPublicstatus(IssueStatusEnum.ALREADY_STOP.getCode());//停止发布
				}else{
					ar.setFailMsg("对不起，只允许已发布状态记录进行操作");
					return ar;
				}
			}
			adv.setUpdator(user.getUsername());
			adv.setUpdatetime(new Date());
			adv.setUpdatorid(user.getId());
			advertisementService.updateByPrimaryKeySelective(adv);
			Transaction.commit(transactionManager);
			ar.setSucceedMsg("删除成功");
		} catch (NumberFormatException e) {
			ar.setFailMsg("参数错误！");
		} catch (UserException e) {
			ar.setFailMsg("令牌为空！");
			ar.setRes(-1);
		} catch (Exception e) {
			Transaction.rollback(transactionManager);
			ar.setFailMsg("删除失败,出现异常");
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
		AjaxRes ar = new AjaxRes();
		String token = request.getParameter("token");
		User user = Verification.getUser(token, redisUtil);
		//String originalFilename = file.getOriginalFilename();
		//String userHome = System.getProperty("user.home");
		if (!file.isEmpty()) {
			try {
				// 获得文件名
				String filename = file.getOriginalFilename();
				// 命名新的文件名
				String extension = FilenameUtils.getExtension(filename);
				String newFileName = UuidUtil.get32UUID() + "." + extension;

				File files = new File(newFileName);
				//file.transferTo(file1);
				FileInputStream inputStream = new FileInputStream(files);
				byte [] bytes = new byte [inputStream.available()];
				FtpUtils.sshSftp(bytes, newFileName);
				ar.setSucceed(newFileName,"附件数据上传成功");
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
		return ar;
	}
}
