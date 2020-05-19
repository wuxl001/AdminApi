package cn.szag.oms.manager.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.szag.oms.manager.common.dao.manager.OrderStatusHistoryMapper;
import cn.szag.oms.manager.common.domain.manager.OrderStatusHistory;
import cn.szag.oms.manager.service.OrderStatusHistoryService;

@Service("orderStatusHistoryService")
public class OrderStatusHistoryServiceImpl implements OrderStatusHistoryService {
	
	@Autowired
	private OrderStatusHistoryMapper orderStatusHistoryMapper;

	@Override
	public int deleteByPrimaryKey(String id) {
		// TODO Auto-generated method stub
		return orderStatusHistoryMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(OrderStatusHistory record) {
		// TODO Auto-generated method stub
		return orderStatusHistoryMapper.insert(record);
	}

	@Override
	public int insertSelective(OrderStatusHistory record) {
		// TODO Auto-generated method stub
		return orderStatusHistoryMapper.insertSelective(record);
	}

	@Override
	public OrderStatusHistory selectByPrimaryKey(String id) {
		// TODO Auto-generated method stub
		return orderStatusHistoryMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(OrderStatusHistory record) {
		// TODO Auto-generated method stub
		return orderStatusHistoryMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKeyWithBLOBs(OrderStatusHistory record) {
		// TODO Auto-generated method stub
		return orderStatusHistoryMapper.updateByPrimaryKeyWithBLOBs(record);
	}

	@Override
	public int updateByPrimaryKey(OrderStatusHistory record) {
		// TODO Auto-generated method stub
		return orderStatusHistoryMapper.updateByPrimaryKey(record);
	}

	@Override
	public List<OrderStatusHistory> selectList(OrderStatusHistory record) {
		// TODO Auto-generated method stub
		return orderStatusHistoryMapper.selectList(record);
		
	}

}
