package cn.szag.oms.manager.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.szag.oms.manager.common.dao.manager.DepartmentMapper;
import cn.szag.oms.manager.common.domain.manager.Department;
import cn.szag.oms.manager.common.page.Page;
import cn.szag.oms.manager.service.DepartmentService;

@Service("departmentService")
public class DepartmentServiceImpl implements DepartmentService {
	@Autowired
	private DepartmentMapper departmentMapper;
	@Override
	public int deleteByPrimaryKey(String id) {
		// TODO Auto-generated method stub
		return departmentMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(Department record) {
		// TODO Auto-generated method stub
		return departmentMapper.insert(record);
	}

	@Override
	public int insertSelective(Department record) {
		// TODO Auto-generated method stub
		return departmentMapper.insertSelective(record);
	}

	@Override
	public Department selectByPrimaryKey(String id) {
		// TODO Auto-generated method stub
		return departmentMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(Department record) {
		// TODO Auto-generated method stub
		return departmentMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKeyWithBLOBs(Department record) {
		// TODO Auto-generated method stub
		return departmentMapper.updateByPrimaryKeyWithBLOBs(record);
	}

	@Override
	public int updateByPrimaryKey(Department record) {
		// TODO Auto-generated method stub
		return departmentMapper.updateByPrimaryKey(record);
	}

	@Override
	public List<Department> find(Department department,Page page) {
		// TODO Auto-generated method stub
		return departmentMapper.find(department,page);
	}

}
