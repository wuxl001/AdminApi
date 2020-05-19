package cn.szag.oms.manager.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.szag.oms.manager.common.dao.manager.OrderEvaluationScoreMapper;
import cn.szag.oms.manager.common.domain.manager.OrderEvaluationScore;
import cn.szag.oms.manager.service.OrderEvaluationScoreService;

/** 
* @ClassName: OrderEvaluationScoreServiceImpl 
* @Description: TODO
* @author dengyanghao
* @date 2019年9月16日 上午11:37:07  
*/
@Service("orderEvaluationScoreService")
public class OrderEvaluationScoreServiceImpl implements OrderEvaluationScoreService {
	@Autowired
	private OrderEvaluationScoreMapper orderEvaluationScoreMapper;
	@Override
	public int deleteByPrimaryKey(String id) {
		// TODO Auto-generated method stub
		return orderEvaluationScoreMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(OrderEvaluationScore record) {
		// TODO Auto-generated method stub
		return orderEvaluationScoreMapper.insert(record);
	}

	@Override
	public int insertSelective(OrderEvaluationScore record) {
		// TODO Auto-generated method stub
		return orderEvaluationScoreMapper.insertSelective(record);
	}

	@Override
	public OrderEvaluationScore selectByPrimaryKey(String id) {
		// TODO Auto-generated method stub
		return orderEvaluationScoreMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(OrderEvaluationScore record) {
		// TODO Auto-generated method stub
		return orderEvaluationScoreMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(OrderEvaluationScore record) {
		// TODO Auto-generated method stub
		return orderEvaluationScoreMapper.updateByPrimaryKey(record);
	}

	@Override
	public List<OrderEvaluationScore> select(String orderId, String containerId) {
		// TODO Auto-generated method stub
		return orderEvaluationScoreMapper.select(orderId, containerId);
	}
	

}
