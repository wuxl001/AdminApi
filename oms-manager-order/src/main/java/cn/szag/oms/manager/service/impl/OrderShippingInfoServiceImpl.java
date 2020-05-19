package cn.szag.oms.manager.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.szag.oms.manager.common.dao.manager.OrderShippingInfoMapper;
import cn.szag.oms.manager.common.domain.manager.OrderShippingInfo;
import cn.szag.oms.manager.common.domain.manager.ShippinInfoDate;
import cn.szag.oms.manager.service.OrderShippingInfoService;

@Service("orderShippingInfoService")
public class OrderShippingInfoServiceImpl implements OrderShippingInfoService {
	@Autowired
	private OrderShippingInfoMapper orderShippingInfoMapper;
	@Override
	public int deleteByPrimaryKey(String id) {
		// TODO Auto-generated method stub
		return orderShippingInfoMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(OrderShippingInfo record) {
		// TODO Auto-generated method stub
		return orderShippingInfoMapper.insert(record);
	}

	@Override
	public int insertSelective(OrderShippingInfo record) {
		// TODO Auto-generated method stub
		return orderShippingInfoMapper.insertSelective(record);
	}

	@Override
	public OrderShippingInfo selectByPrimaryKey(String id) {
		// TODO Auto-generated method stub
		return orderShippingInfoMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(OrderShippingInfo record) {
		// TODO Auto-generated method stub
		return orderShippingInfoMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(OrderShippingInfo record) {
		// TODO Auto-generated method stub
		return orderShippingInfoMapper.updateByPrimaryKey(record);
	}

	@Override
	public OrderShippingInfo selectByOrderId(String orderId,String containerId) {
		// TODO Auto-generated method stub
		return orderShippingInfoMapper.selectByOrderId(orderId,containerId);
	}

	@Override
	public ShippinInfoDate select(String orderId, String boxNo, String id, Integer type) {
		// TODO Auto-generated method stub
		return orderShippingInfoMapper.select(orderId, boxNo, id, type);
	}

}
