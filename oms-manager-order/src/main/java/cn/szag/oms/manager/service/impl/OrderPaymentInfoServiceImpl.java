package cn.szag.oms.manager.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.szag.oms.manager.common.dao.manager.OrderPaymentInfoMapper;
import cn.szag.oms.manager.common.domain.manager.OrderPaymentInfo;
import cn.szag.oms.manager.service.OrderPaymentInfoService;

@Service("orderPaymentInfoService")
public class OrderPaymentInfoServiceImpl implements OrderPaymentInfoService {
	@Autowired OrderPaymentInfoMapper orderPaymentInfoMapper;
	@Override
	public int deleteByPrimaryKey(String id) {
		// TODO Auto-generated method stub
		return orderPaymentInfoMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(OrderPaymentInfo record) {
		// TODO Auto-generated method stub
		return orderPaymentInfoMapper.insert(record);
	}

	@Override
	public int insertSelective(OrderPaymentInfo record) {
		// TODO Auto-generated method stub
		return orderPaymentInfoMapper.insertSelective(record);
	}

	@Override
	public OrderPaymentInfo selectByPrimaryKey(String id) {
		// TODO Auto-generated method stub
		return orderPaymentInfoMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(OrderPaymentInfo record) {
		// TODO Auto-generated method stub
		return orderPaymentInfoMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKeyWithBLOBs(OrderPaymentInfo record) {
		// TODO Auto-generated method stub
		return orderPaymentInfoMapper.updateByPrimaryKeyWithBLOBs(record);
	}

	@Override
	public int updateByPrimaryKey(OrderPaymentInfo record) {
		// TODO Auto-generated method stub
		return orderPaymentInfoMapper.updateByPrimaryKey(record);
	}

	@Override
	public OrderPaymentInfo selectByOrderId(String orderId) {
		// TODO Auto-generated method stub
		return orderPaymentInfoMapper.selectByOrderId(orderId);
	}

}
