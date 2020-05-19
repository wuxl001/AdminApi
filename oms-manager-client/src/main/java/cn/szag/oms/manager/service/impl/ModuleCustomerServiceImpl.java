package cn.szag.oms.manager.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import cn.szag.oms.manager.common.dao.manager.ModuleCustomerMapper;
import cn.szag.oms.manager.common.domain.manager.ModuleCustomer;
import cn.szag.oms.manager.service.ModuleCustomerService;

@Service("moduleCustomerService")
public class ModuleCustomerServiceImpl implements ModuleCustomerService{
	@Autowired
	private ModuleCustomerMapper moduleMapper;
	
	
	@Override
	public int deleteByPrimaryKey(String id) {
		// TODO Auto-generated method stub
		return moduleMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(ModuleCustomer record) {
		// TODO Auto-generated method stub
		return moduleMapper.insert(record);
	}

	@Override
	public int insertSelective(ModuleCustomer record) {
		// TODO Auto-generated method stub
		return moduleMapper.insertSelective(record);
	}

	@Override
	public ModuleCustomer selectByPrimaryKey(String id) {
		// TODO Auto-generated method stub
		return moduleMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(ModuleCustomer record) {
		// TODO Auto-generated method stub
		return moduleMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(ModuleCustomer record) {
		// TODO Auto-generated method stub
		return moduleMapper.updateByPrimaryKey(record);
	}

	@Override
	public List<ModuleCustomer> findModule(String userid) {
		List<ModuleCustomer> list = null;
		try {
			ModuleCustomer m = new ModuleCustomer();
			m.setParentid("0");;//0=查询最大的菜单
			list = moduleMapper.findModule(m);
			Items(list);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
	/**
	 * 根据角色查询子菜单
	 * @title: Item
	 * @author: tansongke
	 * @description: TODO
	 * @date: 2019年6月17日 下午4:11:30
	 * @param list
	 * @param userid
	 * @return: void
	 */
	public void Item(List<ModuleCustomer> list,String userid){
		for (ModuleCustomer m : list) {
			if(m.getChildren()==null){
				ModuleCustomer ml= new ModuleCustomer();
				ml.setParentid(m.getId());
				List<ModuleCustomer> children = moduleMapper.listAuthorizedUser(userid,ml);
				m.setChildren(children);
				Item(children, userid);
			}
		}
	}
	
	/**
	 * 查询子菜单
	 * @title: Items
	 * @author: tansongke
	 * @description: TODO
	 * @date: 2019年6月17日 下午4:11:26
	 * @param list
	 * @return: void
	 */
	public void Items(List<ModuleCustomer> list){
		for (ModuleCustomer m : list) {
			if(m.getChildren()==null){
				ModuleCustomer ml= new ModuleCustomer();
				ml.setParentid(m.getId());
				List<ModuleCustomer> children = moduleMapper.findModule(ml);
				m.setChildren(children);
				Items(children);
			}
		}
	}

	@Override
	public List<ModuleCustomer> listAuthorizedUser(String userid, ModuleCustomer module) {
		// TODO Auto-generated method stub
		return moduleMapper.listAuthorizedUser(userid, module);
	}

	@Override
	public List<String> initMenu(List<String> aus) {
		// TODO Auto-generated method stub
		return moduleMapper.initMenu(aus);
	}

}
