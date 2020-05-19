package cn.szag.oms.manager.service;


import cn.szag.oms.manager.common.ajax.AjaxRes;
import cn.szag.oms.manager.common.domain.Module;
import cn.szag.oms.manager.common.domain.manager.User;
import cn.szag.oms.manager.common.page.Page;

import java.util.List;

/**
* @ClassName: GoodsInfoService 
* @Description: TODO
* @author dengyanghao
* @date 2019年9月18日 上午10:48:55  
*/
public interface ModuleManagerService {

	int deleteByPrimaryKey(String id);

    int insert(Module record);

    Module selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Module record);

    AjaxRes insertSelective(Module record) throws Exception;

    int updateByPrimaryKey(Module record);

    // 根据资源名称查询菜单管理
    AjaxRes findByPage(Module module, Page page,User user,String parentId);
    /**
     * 查询按钮
    * @Title: findBtn 
    * @Description: TODO 
    * @param @param userId
    * @param @param type
    * @param @return
    * @author dengyanghao
    * @return List<Module>
    * @throws
     */
    List<Module> findBtn(String userId ,String type,String parentId);
    /**
     * 查询全部按钮
     * @title: findBtn2
     * @author: tansongke
     * @description: TODO
     * @date: 2020年3月23日 上午10:57:27
     * @param userId
     * @param type
     * @param parentId
     * @return
     * @return: List<Module>
     */
    public List<Module> findBtn2(String userId, String type);
    /**
     * 查询用户权限菜单
    * @Title: findBtn 
    * @Description: TODO 
    * @param @param userId
    * @param @return
    * @author dengyanghao
    * @return List<Module>
    * @throws
     */
    List<Module> findMenu(String userId);
    /**
     * 根据角色Id查询菜单权限
    * @Title: findRoleMus 
    * @Description: TODO 
    * @param @param roleId
    * @param @return
    * @author dengyanghao
    * @return List<Module>
    * @throws
     */
    List<String> findRoleMus(String roleId);
    /**
     * 根据菜单名称查询菜单
    * @Title: findByName 
    * @Description: TODO 
    * @param @param id
    * @param @param name
    * @param @return
    * @author dengyanghao
    * @return int
    * @throws
     */
    int findByName(String id,String name,String type);
    /**
     * 根据用户Id以及菜单Id判断是否有当前菜单权限
    * @Title: findJurisdiction 
    * @Description: TODO 
    * @param @param moduleId
    * @param @param userId
    * @param @return
    * @author dengyanghao
    * @return int
    * @throws
     */
    int findJurisdiction(String moduleId,String userId);
}
