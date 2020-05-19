package cn.szag.oms.manager.controller;

import cn.szag.oms.manager.common.ajax.AjaxRes;
import cn.szag.oms.manager.common.domain.manager.GoodsInfo;
import cn.szag.oms.manager.common.domain.manager.Role;
import cn.szag.oms.manager.common.domain.manager.User;
import cn.szag.oms.manager.common.page.Page;
import cn.szag.oms.manager.common.utils.*;
import cn.szag.oms.manager.service.ModuleManagerService;
import cn.szag.oms.manager.service.RoleService;
import cn.szag.oms.manager.service.UserControlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/user")
public class UserControlController {

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private UserControlService userControlService;
    
    @Autowired
    private RoleService roleService;
    
    @Autowired
    private ModuleManagerService  moduleManagerService;
    @Autowired
	private PlatformTransactionManager transactionManager;

    // 查询所有分页查询
    @RequestMapping("/list")
    public AjaxRes findLit(HttpServletRequest request ,  User ur, Page page) {
    	Map<String,Object> map = new HashMap<String, Object>();
        try {
            // 获得token信息
            String token = request.getParameter("token");
            String parentId = request.getParameter("parentId");
            User user = Verification.getUser(token,redisUtil);
            Page page1 = userControlService.selectList(ur, page);
            map.put("list", page1);
            map.put("permitBtn", moduleManagerService.findBtn(user.getId(), Const.RESOURCES_TYPE_BUTTON, parentId));
            return new AjaxRes(Const.SUCCEED, "用户查询成功",map);
        } catch (NumberFormatException e) {
            return new AjaxRes(Const.FAIL, "参数错误！！");
        } catch (UserException e) {
            e.printStackTrace();
            return new AjaxRes(Const.FAIL, "令牌为空！");
        } catch (Exception e) {
            e.printStackTrace();
            return new AjaxRes(Const.FAIL, "查询用户信息失败，出现异常");
        }
    }

    // 添加用户
    @RequestMapping("/add")
    public AjaxRes addUser(HttpServletRequest request , User ur,String [] roleIds ) {

    	String token = null;
		User user = null;
		try {
			String json = request.getParameter("json");
			Map<String,Object> map = new HashMap<String, Object>();
			if(json ==null || "".equals(json)){
				token = request.getParameter("token");
				user = Verification.getUser(token,redisUtil);
				ur.setId(UuidUtil.get32UUID());
			}else{
				ur = JSON.parseObject(json, User.class);
			}
            User userAccount = userControlService.findByAccount(ur.getAccount(),ur.getId());
            if (userAccount != null){
                return new AjaxRes(Const.FAIL,"该账户已存在");
            }
            userAccount = userControlService.selectByPrimaryKey(ur.getId());
            if(userAccount == null){
            	userControlService.insert(ur,roleIds);
            }else{
            	userControlService.updateByPrimaryKeySelective(ur);
            }
            Transaction.commit(transactionManager);
            return new AjaxRes(Const.SUCCEED, "用户添加成功");
        } catch (NumberFormatException e) {
            return new AjaxRes(Const.FAIL, "参数错误！！");
        } catch (UserException e) {
            e.printStackTrace();
            return new AjaxRes(Const.FAIL, "令牌为空！");
        } catch (Exception e) {
        	Transaction.rollback(transactionManager);
            e.printStackTrace();
            return new AjaxRes(Const.FAIL, "添加用户信息失败，出现异常");
        }
    }

    // 修改角色信息
    @RequestMapping("/edit")
    public AjaxRes editUser(HttpServletRequest request ,  User ur ,String [] roleIds ) {

        try {
            // 获得token信息
            String token = request.getParameter("token");
            User user = Verification.getUser(token,redisUtil);
            if (ur.getId() == null){
                return new AjaxRes(Const.FAIL,"角色的id不能为空");
            }
            userControlService.updateUser(ur,roleIds);
            Transaction.commit(transactionManager);
            return new AjaxRes(Const.SUCCEED, "用户修改成功");
        } catch (NumberFormatException e) {
            return new AjaxRes(Const.FAIL, "参数错误！！");
        } catch (UserException e) {
            e.printStackTrace();
            return new AjaxRes(Const.FAIL, "令牌为空！");
        } catch (Exception e) {
            e.printStackTrace();
            Transaction.rollback(transactionManager);
            return new AjaxRes(Const.FAIL, "修改用户信息失败，出现异常");
        }
    }
 // 查询用户信息
    @RequestMapping("/find")
    public AjaxRes findUser(HttpServletRequest request ,String id) {
    	AjaxRes ar = new AjaxRes();
        try {
            // 获得token信息
            String token = request.getParameter("token");
            User user = Verification.getUser(token,redisUtil);
            User user2 = userControlService.selectByPrimaryKey(id);
            List<Role> roleList= roleService.selectRoleList(id);
            List<String> roleIds = new ArrayList<String>();
            for (Role role : roleList) {
            	roleIds.add(role.getId());
			}
            //user2.setRoles(roleList);
            user2.setRoleIds(roleIds);
            ar.setSucceed(user2, "用户信息查询成功");
            return ar;
        } catch (NumberFormatException e) {
            return new AjaxRes(Const.FAIL, "参数错误！！");
        } catch (UserException e) {
            e.printStackTrace();
            return new AjaxRes(Const.FAIL, "令牌为空！");
        } catch (Exception e) {
            e.printStackTrace();
            return new AjaxRes(Const.FAIL, "用户信息查询失败，出现异常");
        }
    }
    // 删除用户的信息
    @RequestMapping("/del")
    public AjaxRes delUser(HttpServletRequest request , User ur ) {
        try {
            // 获得token信息
            String token = request.getParameter("token");
            User user = Verification.getUser(token,redisUtil);
            if (ur.getId()== null){
                return new AjaxRes(Const.FAIL,"用户ID不能为空");
            }
            if (ur.getStatus()==null){
                return new AjaxRes(Const.FAIL,"用户的状态不能为空");
            }
            userControlService.updateByPrimaryKeySelective(ur);
            Transaction.commit(transactionManager);
            return new AjaxRes(Const.SUCCEED, "用户删除成功");
        } catch (NumberFormatException e) {
            return new AjaxRes(Const.FAIL, "参数错误！！");
        } catch (UserException e) {
            e.printStackTrace();
            return new AjaxRes(Const.FAIL, "令牌为空！");
        } catch (Exception e) {
        	Transaction.rollback(transactionManager);
            e.printStackTrace();
            return new AjaxRes(Const.FAIL, "删除用户信息失败，出现异常");
        }
    }

    /**
     *  修改密码，给cfs调用
     * @param user
     * @return
     */
    @RequestMapping("/editPwd")
    public AjaxRes editPwd(User user){
        try {
        	if(null != user.getPassword()){
        		user.setPassword(EncryptUtil.md5Password(user.getPassword()));
            }
            userControlService.updateByPrimaryKey(user);
            return new AjaxRes(Const.SUCCEED,"密码修改成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new AjaxRes(Const.FAIL,"修改密码失败");
        }
    }
    // 修改极光推送Id
    @RequestMapping("/updateRegistrationId")
    public AjaxRes updateRegistrationId(HttpServletRequest request ,  String registrationId  ) {

        try {
            // 获得token信息
            String token = request.getParameter("token");
            User user = Verification.getUser(token,redisUtil);
            User u = new User();
            u.setId(user.getId());
            if("".equals(registrationId) || registrationId == null){
            	return new AjaxRes(Const.FAIL, "极光推送Id为空");
            }
            userControlService.updateRegistrationId(registrationId, user.getId());
            Transaction.commit(transactionManager);
            return new AjaxRes(Const.SUCCEED, "极光推送Id修改成功");
        } catch (NumberFormatException e) {
            return new AjaxRes(Const.FAIL, "参数错误！！");
        } catch (UserException e) {
            e.printStackTrace();
            return new AjaxRes(Const.FAIL, "令牌为空！");
        } catch (Exception e) {
        	Transaction.rollback(transactionManager);
            e.printStackTrace();
            return new AjaxRes(Const.FAIL, "极光推送Id修改失败，出现异常");
        }
    }
    //解析token
    @RequestMapping("/getToken")
    public AjaxRes getToken(HttpServletRequest request) {
    	AjaxRes ar = new AjaxRes();
        try {
            // 获得token信息
            String token = request.getParameter("token");
            User user = Verification.getUser(token,redisUtil);
            user = userControlService.selectByPrimaryKey(user.getId());
            List<Role> roleList = roleService.selectRoleList(user.getId());
            String roleName = "";
            for (Role role : roleList) {
				if(roleList.size()>1){
					roleName = role.getName()+"|";
				}else{
					roleName = role.getName();
				}
			}
            user.setRoleName(roleName);
            user.setToken(token);
            ar.setSucceed(user, "token解析成功");
        } catch (NumberFormatException e) {
            return new AjaxRes(Const.FAIL, "参数错误！！");
        } catch (UserException e) {
            e.printStackTrace();
            return new AjaxRes(Const.FAIL, "令牌为空！");
        } catch (Exception e) {
            e.printStackTrace();
            return new AjaxRes(Const.FAIL, "token解析失败");
        }
        return ar;
    }
}
