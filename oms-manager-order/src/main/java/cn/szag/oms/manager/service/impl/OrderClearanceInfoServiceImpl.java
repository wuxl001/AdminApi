package cn.szag.oms.manager.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.szag.oms.manager.common.dao.manager.OrderClearanceInfoMapper;
import cn.szag.oms.manager.common.domain.manager.OrderClearanceInfo;
import cn.szag.oms.manager.service.OrderClearanceInfoService;
@Service("orderClearanceInfoService")
public class OrderClearanceInfoServiceImpl implements OrderClearanceInfoService {
	@Autowired
	private OrderClearanceInfoMapper orderClearanceInfoMapper;
	@Override
	public int deleteByPrimaryKey(String id) {
		// TODO Auto-generated method stub
		return orderClearanceInfoMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(OrderClearanceInfo record) {
		// TODO Auto-generated method stub
		return orderClearanceInfoMapper.insert(record);
	}

	@Override
	public int insertSelective(OrderClearanceInfo record) {
		// TODO Auto-generated method stub
		return orderClearanceInfoMapper.insertSelective(record);
	}

	@Override
	public OrderClearanceInfo selectByPrimaryKey(String id) {
		// TODO Auto-generated method stub
		return orderClearanceInfoMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(OrderClearanceInfo record) {
		// TODO Auto-generated method stub
		return orderClearanceInfoMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKeyWithBLOBs(OrderClearanceInfo record) {
		// TODO Auto-generated method stub
		return orderClearanceInfoMapper.updateByPrimaryKeyWithBLOBs(record);
	}

	@Override
	public int updateByPrimaryKey(OrderClearanceInfo record) {
		// TODO Auto-generated method stub
		return orderClearanceInfoMapper.updateByPrimaryKey(record);
	}

	@Override
	public OrderClearanceInfo select(String orderId,String containerId) {
		// TODO Auto-generated method stub
		return orderClearanceInfoMapper.select(orderId,containerId);
	}

}
