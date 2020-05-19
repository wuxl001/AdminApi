package cn.szag.oms.manager.common.dao.manager;


import cn.szag.oms.manager.common.domain.manager.Rolemember;

import java.util.Map;

public interface RolememberMapper {
    int deleteByPrimaryKey(String id);

    int insert(Rolemember record);

    int insertSelective(Rolemember record);

    Rolemember selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Rolemember record);

    int updateByPrimaryKey(Rolemember record);

    // 通过角色的id删除中间表
    void deleteByRoleId(String id);

    // 通过用户的id删除中间表
    void deleteByUserId(String id);

    void addRoleIds(Map<String, String> map);

    void addModuleIds(Map<String, String> map);
}