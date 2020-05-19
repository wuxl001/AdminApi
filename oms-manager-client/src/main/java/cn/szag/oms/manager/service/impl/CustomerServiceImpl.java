package cn.szag.oms.manager.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.szag.oms.manager.common.dao.manager.CustomerMapper;
import cn.szag.oms.manager.common.domain.manager.Customer;
import cn.szag.oms.manager.common.page.Page;
import cn.szag.oms.manager.service.CustomerService;
@Service("customerService")
public class CustomerServiceImpl implements CustomerService {
	@Autowired
	private CustomerMapper customerMapper;
	@Override
	public int deleteByPrimaryKey(String id) {
		// TODO Auto-generated method stub
		return customerMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(Customer record) {
		// TODO Auto-generated method stub
		return customerMapper.insert(record);
	}

	@Override
	public int insertSelective(Customer record) {
		// TODO Auto-generated method stub
		return customerMapper.insertSelective(record);
	}

	@Override
	public Customer selectByPrimaryKey(String id) {
		// TODO Auto-generated method stub
		return customerMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(Customer record) {
		// TODO Auto-generated method stub
		return customerMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(Customer record) {
		// TODO Auto-generated method stub
		return customerMapper.updateByPrimaryKey(record);
	}

	@Override
	public List<Customer> findByPage(Customer customer, Page page) {
		// TODO Auto-generated method stub
		return customerMapper.findByPage(customer, page);
	}

	@Override
	public int selectList(Customer customer) {
		// TODO Auto-generated method stub
		return customerMapper.selectList(customer);
	}

}
