package cn.szag.oms.manager.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.szag.oms.manager.common.dao.manager.OrderNationalFlagMapper;
import cn.szag.oms.manager.common.domain.manager.OrderNationalFlag;
import cn.szag.oms.manager.service.OrderNationalFlagService;
@Service("orderNationalFlagService")
public class OrderNationalFlagServiceImpl implements OrderNationalFlagService {
	@Autowired
	private OrderNationalFlagMapper orderNationalFlagMapper;
	@Override
	public int deleteByPrimaryKey(String id) {
		// TODO Auto-generated method stub
		return orderNationalFlagMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(OrderNationalFlag record) {
		// TODO Auto-generated method stub
		return orderNationalFlagMapper.insert(record);
	}

	@Override
	public int insertSelective(OrderNationalFlag record) {
		// TODO Auto-generated method stub
		return orderNationalFlagMapper.insertSelective(record);
	}

	@Override
	public OrderNationalFlag selectByPrimaryKey(String id,String name) {
		// TODO Auto-generated method stub
		return orderNationalFlagMapper.selectByPrimaryKey(id,name);
	}

	@Override
	public int updateByPrimaryKeySelective(OrderNationalFlag record) {
		// TODO Auto-generated method stub
		return orderNationalFlagMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(OrderNationalFlag record) {
		// TODO Auto-generated method stub
		return orderNationalFlagMapper.updateByPrimaryKey(record);
	}

}
