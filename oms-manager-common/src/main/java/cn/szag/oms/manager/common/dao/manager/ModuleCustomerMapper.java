package cn.szag.oms.manager.common.dao.manager;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.szag.oms.manager.common.domain.manager.ModuleCustomer;

public interface ModuleCustomerMapper {
	int deleteByPrimaryKey(String id);

	int insert(ModuleCustomer record);

	int insertSelective(ModuleCustomer record);

	ModuleCustomer selectByPrimaryKey(String id);

	int updateByPrimaryKeySelective(ModuleCustomer record);

	int updateByPrimaryKey(ModuleCustomer record);

	List<ModuleCustomer> listAuthorizedUser(@Param("userid") String userid, @Param("module") ModuleCustomer module);

	List<ModuleCustomer> findModule(@Param("module") ModuleCustomer m);
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