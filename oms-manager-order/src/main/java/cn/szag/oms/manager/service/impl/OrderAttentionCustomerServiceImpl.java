package cn.szag.oms.manager.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.szag.oms.manager.common.dao.manager.OrderAttentionCustomerMapper;
import cn.szag.oms.manager.common.domain.manager.OrderAttentionCustomer;
import cn.szag.oms.manager.service.OrderAttentionCustomerService;
@Service("orderAttentionCustomerService")
public class OrderAttentionCustomerServiceImpl implements OrderAttentionCustomerService{
	@Autowired
	private OrderAttentionCustomerMapper orderAttachmentCustomerMapper;
	@Override
	public int deleteByPrimaryKey(String id) {
		// TODO Auto-generated method stub
		return orderAttachmentCustomerMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(OrderAttentionCustomer record) {
		// TODO Auto-generated method stub
		return orderAttachmentCustomerMapper.insert(record);
	}

	@Override
	public int insertSelective(OrderAttentionCustomer record) {
		// TODO Auto-generated method stub
		return orderAttachmentCustomerMapper.insertSelective(record);
	}

	@Override
	public OrderAttentionCustomer selectByPrimaryKey(String id) {
		// TODO Auto-generated method stub
		return orderAttachmentCustomerMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(OrderAttentionCustomer record) {
		// TODO Auto-generated method stub
		return orderAttachmentCustomerMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(OrderAttentionCustomer record) {
		// TODO Auto-generated method stub
		return orderAttachmentCustomerMapper.updateByPrimaryKey(record);
	}

}
