package cn.szag.oms.manager.controller;

import cn.szag.oms.manager.common.ajax.AjaxRes;
import cn.szag.oms.manager.common.domain.Module;
import cn.szag.oms.manager.common.domain.manager.Role;
import cn.szag.oms.manager.common.domain.manager.User;
import cn.szag.oms.manager.common.page.Page;
import cn.szag.oms.manager.common.utils.*;
import cn.szag.oms.manager.service.ModuleManagerService;
import cn.szag.oms.manager.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

/**
 * 角色管理
 */
@RestController
@RequestMapping("/role")
public class RoleControer {

    @Autowired
    private RoleService roleService;

    @Autowired
    private RedisUtil redisUtil;
    
    @Autowired
    private ModuleManagerService  moduleManagerService;


    // 查询所有分页查询
    @RequestMapping("/list")
   public AjaxRes findLit(HttpServletRequest request , Role role , Page page) {
    	Map<String,Object> map = new HashMap<String, Object>();
        try {
            // 获得token信息
            String token = request.getParameter("token");
            String parentId = request.getParameter("parentId");
            User user = Verification.getUser(token,redisUtil);
            Page pageResult = roleService.selectList(role, page);
            map.put("list", pageResult);
            map.put("permitBtn", moduleManagerService.findBtn(user.getId(), Const.RESOURCES_TYPE_BUTTON, parentId));
            return new AjaxRes(Const.SUCCEED, "角色查询成功", map);
        } catch (NumberFormatException e) {
            return new AjaxRes(Const.FAIL, "参数错误！！");
        } catch (UserException e) {
            e.printStackTrace();
            return new AjaxRes(Const.FAIL, "令牌为空！");
        } catch (Exception e) {
            e.printStackTrace();
            return new AjaxRes(Const.FAIL, "查询角色信息失败，出现异常");
        }
    }

    // 添加角色
    @RequestMapping("/add")
    public AjaxRes addRole(HttpServletRequest request , Role role,String[] moduleIds ) {

        try {
            // 获得token信息
            String token = request.getParameter("token");
            User user = Verification.getUser(token,redisUtil);

            role.setId(UuidUtil.get32UUID());
            int i = roleService.findByName(null, role.getName());
            if(i>0){
            	return new AjaxRes(Const.FAIL, "角色名称重复，添加失败");
            }
            roleService.insertSelective(role,moduleIds);
            return new AjaxRes(Const.SUCCEED, "角色添加成功");
        } catch (NumberFormatException e) {
            return new AjaxRes(Const.FAIL, "参数错误！！");
        } catch (UserException e) {
            e.printStackTrace();
            return new AjaxRes(Const.FAIL, "令牌为空！");
        } catch (Exception e) {
            e.printStackTrace();
            return new AjaxRes(Const.FAIL, "添加角色信息失败，出现异常");
        }
    }

    // 修改角色信息
    @RequestMapping("/edit")
    public AjaxRes editRole(HttpServletRequest request , Role role,String[] moduleIds ) {

        try {
            // 获得token信息
            String token = request.getParameter("token");
            User user = Verification.getUser(token,redisUtil);
            int i = roleService.findByName(role.getId(), role.getName());
            if(i>0){
            	return new AjaxRes(Const.FAIL, "角色名称重复，添加失败");
            }
            roleService.updateRole(role,moduleIds);
            return new AjaxRes(Const.SUCCEED, "角色修改成功");
        } catch (NumberFormatException e) {
            return new AjaxRes(Const.FAIL, "参数错误！！");
        } catch (UserException e) {
            e.printStackTrace();
            return new AjaxRes(Const.FAIL, "令牌为空！");
        } catch (Exception e) {
            e.printStackTrace();
            return new AjaxRes(Const.FAIL, "修改角色信息失败，出现异常");
        }
    }
 // 查询角色信息
    @RequestMapping("/find")
    public AjaxRes findRole(HttpServletRequest request , String id ) {
    	AjaxRes ar = new AjaxRes();
        try {
            // 获得token信息
            String token = request.getParameter("token");
            User user = Verification.getUser(token,redisUtil);

            Role role = roleService.selectByPrimaryKey(id);
            List<String> moduleIds = moduleManagerService.findRoleMus(id);
            //String[] strArray = moduleList.toArray(new String[moduleList.size()]);  
            role.setModules(moduleIds);
            ar.setSucceed(role, "角色信息查询成功");
            return ar;
        } catch (NumberFormatException e) {
            return new AjaxRes(Const.FAIL, "参数错误！！");
        } catch (UserException e) {
            e.printStackTrace();
            return new AjaxRes(Const.FAIL, "令牌为空！");
        } catch (Exception e) {
            e.printStackTrace();
            return new AjaxRes(Const.FAIL, "角色信息查询失败，出现异常");
        }
    }

    // 删除角色信息
    @RequestMapping("/del")
    public AjaxRes delRole(HttpServletRequest request , Role role ) {
        try {
            // 获得token信息
            String token = request.getParameter("token");
            User user = Verification.getUser(token,redisUtil);
            role.setStatus(1);
            roleService.deleteByPrimaryKey(role.getId());
            return new AjaxRes(Const.SUCCEED, "角色删除成功");
        } catch (NumberFormatException e) {
            return new AjaxRes(Const.FAIL, "参数错误！！");
        } catch (UserException e) {
            e.printStackTrace();
            return new AjaxRes(Const.FAIL, "令牌为空！");
        } catch (Exception e) {
            e.printStackTrace();
            return new AjaxRes(Const.FAIL, "添加角色信息失败，出现异常");
        }
    }




}
