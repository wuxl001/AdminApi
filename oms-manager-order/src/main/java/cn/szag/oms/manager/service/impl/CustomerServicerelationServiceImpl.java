package cn.szag.oms.manager.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.szag.oms.manager.common.dao.manager.CustomerServicerelationMapper;
import cn.szag.oms.manager.common.domain.manager.CustomerServicerelation;
import cn.szag.oms.manager.service.CustomerServicerelationService;

@Service("CustomerServicerelationService")
public class CustomerServicerelationServiceImpl implements CustomerServicerelationService{

	@Autowired
	private CustomerServicerelationMapper dao;
	
	@Override
	public int findCusomerIs(String id) {
		// TODO Auto-generated method stub
		return dao.findCusomerIs(id);
	}

	@Override
	public int insert(CustomerServicerelation ss) {
		// TODO Auto-generated method stub
		return dao.insertSelective(ss);
	}

	@Override
	public int findCount() {
		// TODO Auto-generated method stub
		return dao.findCount();
	}

}
