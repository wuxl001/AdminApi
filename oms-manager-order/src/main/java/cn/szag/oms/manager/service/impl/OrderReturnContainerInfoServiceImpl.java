package cn.szag.oms.manager.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.szag.oms.manager.common.dao.manager.OrderReturnContainerInfoMapper;
import cn.szag.oms.manager.common.domain.manager.OrderReturnContainerInfo;
import cn.szag.oms.manager.service.OrderReturnContainerInfoService;

@Service("orderReturnContainerInfoService")
public class OrderReturnContainerInfoServiceImpl implements OrderReturnContainerInfoService {
	@Autowired
	private OrderReturnContainerInfoMapper orderReturnContainerInfoMapper;
	@Override
	public int deleteByPrimaryKey(String id) {
		// TODO Auto-generated method stub
		return orderReturnContainerInfoMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(OrderReturnContainerInfo record) {
		// TODO Auto-generated method stub
		return orderReturnContainerInfoMapper.insert(record);
	}

	@Override
	public int insertSelective(OrderReturnContainerInfo record) {
		// TODO Auto-generated method stub
		return orderReturnContainerInfoMapper.insertSelective(record);
	}

	@Override
	public OrderReturnContainerInfo selectByPrimaryKey(String id) {
		// TODO Auto-generated method stub
		return orderReturnContainerInfoMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(OrderReturnContainerInfo record) {
		// TODO Auto-generated method stub
		return orderReturnContainerInfoMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKeyWithBLOBs(OrderReturnContainerInfo record) {
		// TODO Auto-generated method stub
		return orderReturnContainerInfoMapper.updateByPrimaryKeyWithBLOBs(record);
	}

	@Override
	public int updateByPrimaryKey(OrderReturnContainerInfo record) {
		// TODO Auto-generated method stub
		return orderReturnContainerInfoMapper.updateByPrimaryKey(record);
	}

	@Override
	public OrderReturnContainerInfo select(String orderId, String containerId) {
		// TODO Auto-generated method stub
		return orderReturnContainerInfoMapper.select(orderId, containerId);
	}

}
