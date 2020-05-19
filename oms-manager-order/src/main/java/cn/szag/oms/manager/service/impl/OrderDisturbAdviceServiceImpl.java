package cn.szag.oms.manager.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.szag.oms.manager.common.dao.manager.OrderDisturbAdviceMapper;
import cn.szag.oms.manager.common.domain.manager.OrderDisturbAdvice;
import cn.szag.oms.manager.service.OrderDisturbAdviceService;
@Service("orderDisturbAdviceService")
public class OrderDisturbAdviceServiceImpl implements OrderDisturbAdviceService {
	@Autowired
	private OrderDisturbAdviceMapper orderDisturbAdviceMapper;
	@Override
	public int deleteByPrimaryKey(String id) {
		// TODO Auto-generated method stub
		return orderDisturbAdviceMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(OrderDisturbAdvice record) {
		// TODO Auto-generated method stub
		return orderDisturbAdviceMapper.insert(record);
	}

	@Override
	public int insertSelective(OrderDisturbAdvice record) {
		return orderDisturbAdviceMapper.insertSelective(record);
	}

	@Override
	public OrderDisturbAdvice selectByPrimaryKey(OrderDisturbAdvice record) {
		// TODO Auto-generated method stub
		return orderDisturbAdviceMapper.selectByPrimaryKey(record);
	}

	@Override
	public int updateByPrimaryKeySelective(OrderDisturbAdvice record) {
		// TODO Auto-generated method stub
		return orderDisturbAdviceMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(OrderDisturbAdvice record) {
		// TODO Auto-generated method stub
		return orderDisturbAdviceMapper.updateByPrimaryKey(record);
	}
	@Override
	public void insertSelective2(OrderDisturbAdvice record) {
		int i = orderDisturbAdviceMapper.insertSelective(record);
		// TODO Auto-generated method stub
	}
}
