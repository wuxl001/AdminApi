package cn.szag.oms.manager.service;


import cn.szag.oms.manager.common.domain.manager.ModulePermissionCustomer;
import cn.szag.oms.manager.common.domain.manager.User;

public interface ModulePermissionCustomerService {
	int deleteByPrimaryKey(String id);

    int insert(ModulePermissionCustomer record);

    int insertSelective(ModulePermissionCustomer record);

    ModulePermissionCustomer selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(ModulePermissionCustomer record);

    int updateByPrimaryKey(ModulePermissionCustomer record);
    
    int updateModuleAndUser(String roleid,String aus);
}