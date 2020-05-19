package cn.szag.oms.manager.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.szag.oms.manager.common.dao.manager.OrderAttachmentMapper;
import cn.szag.oms.manager.common.domain.manager.OrderAttachment;
import cn.szag.oms.manager.service.OrderAttachmentService;
@Service("orderAttachmentService")
public class OrderAttachmentServiceImpl implements OrderAttachmentService {
	@Autowired
	private OrderAttachmentMapper orderAttachmentMapper;
	@Override
	public int deleteByPrimaryKey(String id) {
		// TODO Auto-generated method stub
		return orderAttachmentMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(OrderAttachment record) {
		// TODO Auto-generated method stub
		return orderAttachmentMapper.insert(record);
	}

	@Override
	public int insertSelective(OrderAttachment record) {
		// TODO Auto-generated method stub
		return orderAttachmentMapper.insertSelective(record);
	}

	@Override
	public OrderAttachment selectByPrimaryKey(String id) {
		// TODO Auto-generated method stub
		return orderAttachmentMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(OrderAttachment record) {
		// TODO Auto-generated method stub
		return orderAttachmentMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(OrderAttachment record) {
		// TODO Auto-generated method stub
		return orderAttachmentMapper.updateByPrimaryKey(record);
	}

	@Override
	public List<OrderAttachment> selectByOrderId(String orderId) {
		// TODO Auto-generated method stub
		return orderAttachmentMapper.selectByOrderId(orderId);
	}

	@Override
	public int findByOrderIdCount(String orderId) {
		
		// TODO Auto-generated method stub
		return orderAttachmentMapper.findByOrderIdCount(orderId);
	}

}
