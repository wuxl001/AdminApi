package cn.szag.oms.manager.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.szag.oms.manager.common.dao.manager.OrderExportShippingInfoMapper;
import cn.szag.oms.manager.common.domain.manager.OrderExportShippingInfo;
import cn.szag.oms.manager.service.OrderExportShippingInfoService;
@Service("orderExportShippingInfoService")
public class OrderExportShippingInfoServiceImpl implements OrderExportShippingInfoService {
	@Autowired
	private OrderExportShippingInfoMapper orderExportShippingInfoMapper;
	@Override
	public int deleteByPrimaryKey(String id) {
		// TODO Auto-generated method stub
		return orderExportShippingInfoMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(OrderExportShippingInfo record) {
		// TODO Auto-generated method stub
		return orderExportShippingInfoMapper.insert(record);
	}

	@Override
	public int insertSelective(OrderExportShippingInfo record) {
		// TODO Auto-generated method stub
		return orderExportShippingInfoMapper.insertSelective(record);
	}

	@Override
	public OrderExportShippingInfo selectByPrimaryKey(String id) {
		// TODO Auto-generated method stub
		return orderExportShippingInfoMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(OrderExportShippingInfo record) {
		// TODO Auto-generated method stub
		return orderExportShippingInfoMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(OrderExportShippingInfo record) {
		// TODO Auto-generated method stub
		return orderExportShippingInfoMapper.updateByPrimaryKey(record);
	}

	@Override
	public OrderExportShippingInfo find(String orderId, String containerId) {
		// TODO Auto-generated method stub
		return orderExportShippingInfoMapper.find(orderId, containerId);
	}

}
