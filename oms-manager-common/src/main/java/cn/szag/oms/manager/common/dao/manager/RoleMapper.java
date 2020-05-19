package cn.szag.oms.manager.common.dao.manager;


import cn.szag.oms.manager.common.domain.manager.Role;
import cn.szag.oms.manager.common.page.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RoleMapper {
    int deleteByPrimaryKey(String id);

    int insert(Role record);

    int insertSelective(Role record);

    Role selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);

    List<Role> selectList(@Param("role") Role role, @Param("page") Page page);
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
    List<Role> selectRoleList(@Param("userId") String userId);
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
    int findByName(@Param("id")String id,@Param("name")String name);
}