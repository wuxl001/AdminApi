package cn.szag.oms.manager.controller;


import cn.szag.oms.manager.common.ajax.AjaxRes;
import cn.szag.oms.manager.common.domain.Module;
import cn.szag.oms.manager.common.domain.manager.User;
import cn.szag.oms.manager.common.page.Page;
import cn.szag.oms.manager.common.utils.*;
import cn.szag.oms.manager.service.ModuleManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 菜单管理功能
 */
@RestController
@RequestMapping("/resource")
public class ModuleManagerController {

    @Autowired
    private RedisUtil redisUtil; // redis工具类

    @Autowired
    private ModuleManagerService moduleManagerService;  //菜单管理服务
    /**
     * 用户权限资源菜单查询
     * @param request
     * @param
     * @return
     */
    @RequestMapping(value = "/menu")
    public AjaxRes menu(HttpServletRequest request){
    	AjaxRes ar = new AjaxRes();
    	Map<String, Object> map = new HashMap<String, Object>();
        try {
            // 获得token信息
            String token = request.getParameter("token");
            User user = Verification.getUser(token,redisUtil);
            List<Module> moduleList=moduleManagerService.findMenu(user.getId());
            map.put("list", moduleList);
            
            ar.setFailMsg(map, "用户权限资源菜单获取成功");
            return ar;
        } catch (NumberFormatException e) {
            return new AjaxRes(Const.FAIL, "参数错误！！");
        } catch (UserException e) {
            e.printStackTrace();
            return new AjaxRes(Const.FAIL, "令牌为空！");
        } catch (Exception e) {
            e.printStackTrace();
            return new AjaxRes(Const.FAIL, "用户权限资源菜单获取失败，数据出现异常");
        }

    }
    /**
     * 菜单查询功能  在 base_module 菜单模块表根据条件查询记录，默认查询所有 del_flag 为 0 的记录
     * @param request
     * @param
     * @return
     */
    @RequestMapping(value = "/list")
    public AjaxRes findMenu(HttpServletRequest request,Module module, Page page){
        try {
            // 获得token信息
            String token = request.getParameter("token");
            String parentId = request.getParameter("parentId");
            User user = Verification.getUser(token,redisUtil);
            return moduleManagerService.findByPage(module,page,user,parentId);
        } catch (NumberFormatException e) {
            return new AjaxRes(Const.FAIL, "参数错误！！");
        } catch (UserException e) {
            e.printStackTrace();
            return new AjaxRes(Const.FAIL, "令牌为空！");
        } catch (Exception e) {
            e.printStackTrace();
            return new AjaxRes(Const.FAIL, "查询菜单信息失败，数据出现异常");
        }

    }

    /**
     *  在 base_module 菜单模块表新增一条记录
     * @param request
     * @return
     */
    @RequestMapping(value = "/add")
    public AjaxRes addMenu(HttpServletRequest request,Module module){
        try {
            // 获得token信息
            String token = request.getParameter("token");
            User user = Verification.getUser(token,redisUtil);

            module.setId(UuidUtil.get32UUID());
            module.setStatus(0);
            module.setPublishat(new Date());

            return moduleManagerService.insertSelective(module);


        } catch (NumberFormatException e) {
            return new AjaxRes(Const.FAIL, "参数错误！！");
        } catch (UserException e) {
            e.printStackTrace();
            return new AjaxRes(Const.FAIL, "令牌为空！");
        }catch (Exception e) {
            e.printStackTrace();
            return new AjaxRes(Const.FAIL, "添加信息失败，出现异常");
        }

    }


    /**
     * 修改菜单查询
     * @param request
     * @return
     */
    @RequestMapping(value = "/edit")
    public AjaxRes editMenu(HttpServletRequest request,Module module){
        try {
            // 获得token信息
            String token = request.getParameter("token");
            User user = Verification.getUser(token,redisUtil);
            module.setPublishat(new Date());
            int i = moduleManagerService.findByName(module.getId(), module.getName(),module.getType());
            if(i>0){
            	return new AjaxRes(Const.FAIL,"同级菜单名称重复，修改失败");
            }
            moduleManagerService.updateByPrimaryKey(module);
            return new AjaxRes(Const.SUCCEED,"修改菜单成功");
        } catch (NumberFormatException e) {
            return new AjaxRes(Const.FAIL, "参数错误！！");
        } catch (UserException e) {
            e.printStackTrace();
            return new AjaxRes(Const.FAIL, "令牌为空！");
        } catch (Exception e) {
            e.printStackTrace();
            return new AjaxRes(Const.FAIL, "修改菜单信息失败，出现异常");
        }

    }


    /**
     * 删除菜单
     * @param request
     * @return
     */
    @RequestMapping(value = "/del")
    public AjaxRes delMenu(HttpServletRequest request,String id){
        try {
            // 获得token信息
            String token = request.getParameter("token");
            User user = Verification.getUser(token,redisUtil);
            Module module = new Module();
            module.setId(id);
            module.setDelFlag("1");
            moduleManagerService.updateByPrimaryKeySelective(module);
            return new AjaxRes(Const.SUCCEED,"删除成功");
        } catch (NumberFormatException e) {
            return new AjaxRes(Const.FAIL, "参数错误！！");
        } catch (UserException e) {
            e.printStackTrace();
            return new AjaxRes(Const.FAIL, "令牌为空！");
        } catch (Exception e) {
            e.printStackTrace();
            return new AjaxRes(Const.FAIL, "删除菜单信息失败，出现异常");
        }

    }


}
