package cn.szag.oms.manager.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.szag.oms.manager.common.domain.manager.ModuleCustomer;

public interface ModuleCustomerService {
	int deleteByPrimaryKey(String id);

    int insert(ModuleCustomer record);

    int insertSelective(ModuleCustomer record);

    ModuleCustomer selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(ModuleCustomer record);

    int updateByPrimaryKey(ModuleCustomer record);
    
    List<ModuleCustomer> findModule(String userid);
    
    List<ModuleCustomer> listAuthorizedUser(String userid,ModuleCustomer module);
    /**
     * 获取客户初始菜单权限
    * @Title: initMenu 
    * @Description: TODO 
    * @param @param aus
    * @param @return
    * @author dengyanghao
    * @return List<String>
    * @throws
     */
    List<String> initMenu(List<String> aus);
    
}
