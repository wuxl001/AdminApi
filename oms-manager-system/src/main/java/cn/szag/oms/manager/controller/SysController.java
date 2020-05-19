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

/**
 * 菜单管理功能
 */
@RestController
@RequestMapping("/sys")
public class SysController {

    @Autowired
    private RedisUtil redisUtil; // redis工具类

    @Autowired
    private ModuleManagerService moduleManagerService;  //菜单管理服务

    /**
     * 菜单查询功能  在 base_module 菜单模块表根据条件查询记录，默认查询所有 del_flag 为 0 的记录
     * @param request
     * @param
     * @return
     */
    @RequestMapping(value = "/getCustomerMenuPower")
    public AjaxRes getCustomerMenuPower(HttpServletRequest request){
    	AjaxRes ar =new AjaxRes();
        try {
            // 获得token信息
            String token = request.getParameter("token");
            User user = Verification.getUser(token,redisUtil);
            String menuId = request.getParameter("menuId");
            int i = moduleManagerService.findJurisdiction(menuId, user.getId());
            if(i>0){
            	ar.setSucceedMsg("菜单权限获取成功");
            }else{
            	ar.setFailMsg("暂无菜单权限");
            }
        } catch (NumberFormatException e) {
        	ar.setFailMsg("参数错误！！");
        } catch (UserException e) {
            e.printStackTrace();
            ar.setFailMsg("令牌为空！");
        } catch (Exception e) {
            e.printStackTrace();
            ar.setFailMsg("查询菜单信息失败，数据出现异常");
        }
        return ar;
    }
}
