package cn.szag.oms.manager.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.szag.oms.manager.common.dao.manager.OrderContainerAdviceMapper;
import cn.szag.oms.manager.common.domain.manager.OrderContainerAdvice;
import cn.szag.oms.manager.common.page.Page;
import cn.szag.oms.manager.service.OrderContainerAdviceService;


@Service("orderContainerAdviceService")
public class OrderContainerAdviceServiceImpl implements OrderContainerAdviceService {
	@Autowired
	private OrderContainerAdviceMapper orderContainerAdviceMapper;
	@Override
	public int deleteByPrimaryKey(String id) {
		// TODO Auto-generated method stub
		return orderContainerAdviceMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(OrderContainerAdvice record) {
		// TODO Auto-generated method stub
		return orderContainerAdviceMapper.insert(record);
	}

	@Override
	public int insertSelective(OrderContainerAdvice record) {
		// TODO Auto-generated method stub
		return orderContainerAdviceMapper.insertSelective(record);
	}

	@Override
	public OrderContainerAdvice selectByPrimaryKey(String id) {
		// TODO Auto-generated method stub
		return orderContainerAdviceMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(OrderContainerAdvice record) {
		// TODO Auto-generated method stub
		return orderContainerAdviceMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(OrderContainerAdvice record) {
		// TODO Auto-generated method stub
		return orderContainerAdviceMapper.updateByPrimaryKey(record);
	}

	@Override
	public List<OrderContainerAdvice> findByPage(OrderContainerAdvice orderContainerAdvice, Page page) {
		// TODO Auto-generated method stub
		return orderContainerAdviceMapper.findByPage(orderContainerAdvice, page);
	}

	@Override
	public OrderContainerAdvice select(String orderId, String containerId,String sponsorId) {
		// TODO Auto-generated method stub
		return orderContainerAdviceMapper.select(orderId, containerId,sponsorId);
	}

}
