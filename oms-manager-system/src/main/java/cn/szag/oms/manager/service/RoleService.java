package cn.szag.oms.manager.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.szag.oms.manager.common.domain.manager.Role;
import cn.szag.oms.manager.common.domain.manager.User;
import cn.szag.oms.manager.common.page.Page;

public interface RoleService {

    int deleteByPrimaryKey(String id);

    int insert(Role record);

    Role selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);

    int insertSelective(Role record,String[] moduleIds);

    Page selectList(Role role, Page page);

    void updateRole(Role role, String[] moduleIds);
    /**
     * 根据用户ID获取用户角色
    * @Title: selectList 
    * @Description: TODO 
    * @param @param userId
    * @param @return
    * @author dengyanghao
    * @return List<Role>
    * @throws
     */
    List<Role> selectRoleList(String userId);
    /**
     * 根据姓名查找记录
    * @Title: findByName 
    * @Description: TODO 
    * @param @param id
    * @param @param name
    * @param @return
    * @author dengyanghao
    * @return int
    * @throws
     */
    int findByName(String id,String name);
}
