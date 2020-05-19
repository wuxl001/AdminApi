package cn.szag.oms.manager.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.szag.oms.manager.common.dao.manager.CustomerAccountMapper;
import cn.szag.oms.manager.common.dao.manager.UserMapper;
import cn.szag.oms.manager.common.domain.manager.CustomerAccount;
import cn.szag.oms.manager.common.domain.manager.User;
import cn.szag.oms.manager.common.page.Page;
import cn.szag.oms.manager.service.CusUserService;
@Service("cusUserService")
public class CusUserServiceImpl implements CusUserService {
	@Autowired
	private CustomerAccountMapper customerAccountMapper;

	@Override
	public int deleteByPrimaryKey(String id) {
		// TODO Auto-generated method stub
		return customerAccountMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(CustomerAccount record) {
		// TODO Auto-generated method stub
		return customerAccountMapper.insert(record);
	}

	@Override
	public int insertSelective(CustomerAccount record) {
		// TODO Auto-generated method stub
		return customerAccountMapper.insertSelective(record);
	}

	@Override
	public CustomerAccount selectByPrimaryKey(String id) {
		// TODO Auto-generated method stub
		return customerAccountMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(CustomerAccount record) {
		// TODO Auto-generated method stub
		return customerAccountMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(CustomerAccount record) {
		// TODO Auto-generated method stub
		return customerAccountMapper.updateByPrimaryKey(record);
	}

	@Override
	public List<CustomerAccount> select(CustomerAccount account,Page page) {
		// TODO Auto-generated method stub
		return customerAccountMapper.select(account,page);
	}

	@Override
	public List<CustomerAccount> findByCustomerId(String customerId) {
		// TODO Auto-generated method stub
		return customerAccountMapper.findByCustomerId(customerId);
	}

	@Override
	public int selectList(CustomerAccount account) {
		// TODO Auto-generated method stub
		return customerAccountMapper.selectList(account);
	}

}
