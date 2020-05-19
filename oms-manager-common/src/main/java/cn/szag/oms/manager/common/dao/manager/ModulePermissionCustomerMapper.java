package cn.szag.oms.manager.common.dao.manager;

import cn.szag.oms.manager.common.domain.manager.ModulePermissionCustomer;

public interface ModulePermissionCustomerMapper {
    int deleteByPrimaryKey(String id);

    int insert(ModulePermissionCustomer record);

    int insertSelective(ModulePermissionCustomer record);

    ModulePermissionCustomer selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(ModulePermissionCustomer record);

    int updateByPrimaryKey(ModulePermissionCustomer record);
}