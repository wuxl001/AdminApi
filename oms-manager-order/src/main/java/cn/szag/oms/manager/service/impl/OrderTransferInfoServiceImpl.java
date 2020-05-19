package cn.szag.oms.manager.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.szag.oms.manager.common.dao.manager.OrderTransferInfoMapper;
import cn.szag.oms.manager.common.domain.manager.OrderTransferInfo;
import cn.szag.oms.manager.service.OrderTransferInfoService;
@Service("orderTransferInfoService")
public class OrderTransferInfoServiceImpl implements OrderTransferInfoService {
	@Autowired
	private OrderTransferInfoMapper orderTransferInfoMapper;
	@Override
	public int deleteByPrimaryKey(String id) {
		// TODO Auto-generated method stub
		return orderTransferInfoMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(OrderTransferInfo record) {
		// TODO Auto-generated method stub
		return orderTransferInfoMapper.insert(record);
	}

	@Override
	public int insertSelective(OrderTransferInfo record) {
		// TODO Auto-generated method stub
		return orderTransferInfoMapper.insertSelective(record);
	}

	@Override
	public OrderTransferInfo selectByPrimaryKey(String id) {
		// TODO Auto-generated method stub
		return orderTransferInfoMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(OrderTransferInfo record) {
		// TODO Auto-generated method stub
		return orderTransferInfoMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKeyWithBLOBs(OrderTransferInfo record) {
		// TODO Auto-generated method stub
		return orderTransferInfoMapper.updateByPrimaryKeyWithBLOBs(record);
	}

	@Override
	public int updateByPrimaryKey(OrderTransferInfo record) {
		// TODO Auto-generated method stub
		return orderTransferInfoMapper.updateByPrimaryKey(record);
	}

	@Override
	public OrderTransferInfo select(String containerId, String orderId) {
		// TODO Auto-generated method stub
		return orderTransferInfoMapper.select(containerId, orderId);
	}

}
