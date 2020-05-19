package cn.szag.oms.manager.common.dao.manager;


import cn.szag.oms.manager.common.domain.manager.modulePermission;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

/**
 * 角色和菜单的中间表
 */
public interface ModulePermissionMapper {
    int deleteByPrimaryKey(String id);

    int insert(modulePermission record);

    int insertSelective(modulePermission record);

    modulePermission selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(modulePermission record);

    int updateByPrimaryKey(modulePermission record);

    // 添加中间表
    void addModuleIds(Map<String, String> map);

    // 通过角色的id删除中间表
    void deleteByRoleId(String id);

    // 通过菜单的id删除中间表
    void deleteByModuleId(String id);
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
    int findJurisdiction(@Param("moduleId")String moduleId,@Param("userId")String userId);
    
}