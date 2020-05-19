package cn.szag.oms.manager.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.szag.oms.manager.common.dao.manager.ModulePermissionCustomerMapper;
import cn.szag.oms.manager.common.domain.manager.ModulePermissionCustomer;
import cn.szag.oms.manager.common.domain.manager.User;
import cn.szag.oms.manager.common.utils.UuidUtil;
import cn.szag.oms.manager.service.ModulePermissionCustomerService;


@Service("modulePermissionCustomerService")
public class ModulePermissionCustomerServiceImpl implements ModulePermissionCustomerService{
	@Autowired
	private ModulePermissionCustomerMapper modulePermissionCustomerMapper;
	@Override
	public int deleteByPrimaryKey(String id) {
		// TODO Auto-generated method stub
		return modulePermissionCustomerMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(ModulePermissionCustomer record) {
		// TODO Auto-generated method stub
		return modulePermissionCustomerMapper.insert(record);
	}

	@Override
	public int insertSelective(ModulePermissionCustomer record) {
		// TODO Auto-generated method stub
		return modulePermissionCustomerMapper.insertSelective(record);
	}

	@Override
	public ModulePermissionCustomer selectByPrimaryKey(String id) {
		// TODO Auto-generated method stub
		return modulePermissionCustomerMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(ModulePermissionCustomer record) {
		// TODO Auto-generated method stub
		return modulePermissionCustomerMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(ModulePermissionCustomer record) {
		// TODO Auto-generated method stub
		return modulePermissionCustomerMapper.updateByPrimaryKey(record);
	}

	@Override
	public int updateModuleAndUser(String roleid, String aus) {
		// TODO Auto-generated method stub
		ModulePermissionCustomer rm = new ModulePermissionCustomer();
		rm.setTargetId(roleid);
		modulePermissionCustomerMapper.deleteByPrimaryKey(roleid);
		ModulePermissionCustomer mp = new ModulePermissionCustomer();
		mp.setTargetId(roleid);
		String As[] = aus.split(",");
		int sum = 0;
		for (int i = 0; i < As.length; i++) {
			mp.setModuleId(As[i]);
			mp.setId(UuidUtil.get32UUID());
			sum = modulePermissionCustomerMapper.insertSelective(mp);
		}
		
		return sum;
	}
}
