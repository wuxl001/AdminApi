package cn.szag.oms.manager.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.szag.oms.manager.common.dao.manager.OrderAdviceManagerMapper;
import cn.szag.oms.manager.common.domain.manager.OrderAdviceManager;
import cn.szag.oms.manager.service.OrderAdviceManagerService;
@Service("orderAdviceManagerService")
public class OrderAdviceManagerServiceImpl implements OrderAdviceManagerService {
	@Autowired
	private OrderAdviceManagerMapper orderAdviceManagerMapper;
	@Override
	public int deleteByPrimaryKey(String id) {
		// TODO Auto-generated method stub
		return orderAdviceManagerMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(OrderAdviceManager record) {
		// TODO Auto-generated method stub
		return orderAdviceManagerMapper.insert(record);
	}

	@Override
	public int insertSelective(OrderAdviceManager record) {
		// TODO Auto-generated method stub
		return orderAdviceManagerMapper.insertSelective(record);
	}

	@Override
	public OrderAdviceManager selectByPrimaryKey(String id) {
		// TODO Auto-generated method stub
		return orderAdviceManagerMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(OrderAdviceManager record) {
		// TODO Auto-generated method stub
		return orderAdviceManagerMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(OrderAdviceManager record) {
		// TODO Auto-generated method stub
		return updateByPrimaryKey(record);
	}

	@Override
	public OrderAdviceManager findByUserId(String userId) {
		// TODO Auto-generated method stub
		return orderAdviceManagerMapper.findByUserId(userId);
	}

}
