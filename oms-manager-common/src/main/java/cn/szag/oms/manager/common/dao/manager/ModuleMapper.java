package cn.szag.oms.manager.common.dao.manager;

import cn.szag.oms.manager.common.domain.Module;
import cn.szag.oms.manager.common.page.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ModuleMapper  {
    int deleteByPrimaryKey(String id);

    int insert(Module record);

    int insertSelective(Module record);

    Module selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Module record);

    int updateByPrimaryKey(Module record);

    List<Module>  findModuleByName(@Param("name") String name, @Param("type")String type);

    List<Module> findByPage(@Param("module") Module module, @Param("page") Page page);
    /**
     * 查询按钮
    * @Title: findBtn 
    * @Description: TODO 
    * @param @param userId
    * @param @param type
    * @param @param parentId
    * @param @return
    * @author dengyanghao
    * @return List<Module>
    * @throws
     */
    List<Module> findBtn(@Param("userId")String userId ,@Param("type")String type,@Param("parentId")String parentId);
    
    /**
     * 查询全部按钮
     * @title: findBtn2
     * @author: tansongke
     * @description: TODO
     * @date: 2020年3月23日 上午10:59:56
     * @param userId
     * @param type
     * @return
     * @return: List<Module>
     */
    List<Module> findBtn2(@Param("userId")String userId ,@Param("type")String type);
    
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
    List<Module> findMenu(@Param("userId")String userId);
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
    List<String> findRoleMus(@Param("roleId")String roleId);
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
    int findByName(@Param("id")String id,@Param("name")String name,@Param("type")String type);
}