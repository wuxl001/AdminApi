package cn.szag.oms.manager.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.szag.oms.manager.common.dao.manager.DispatchOrderMapper;
import cn.szag.oms.manager.common.domain.manager.DispatchOrder;
import cn.szag.oms.manager.service.DispatchOrderService;
@Service("dispatchOrderService")
public class DispatchOrderServiceImpl implements DispatchOrderService {
	@Autowired
	private DispatchOrderMapper dispatchOrderMapper;
	@Override
	public int deleteByPrimaryKey(String id) {
		// TODO Auto-generated method stub
		return dispatchOrderMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(DispatchOrder record) {
		// TODO Auto-generated method stub
		return dispatchOrderMapper.insert(record);
	}

	@Override
	public int insertSelective(DispatchOrder record) {
		// TODO Auto-generated method stub
		return dispatchOrderMapper.insertSelective(record);
	}

	@Override
	public DispatchOrder selectByPrimaryKey(String id) {
		// TODO Auto-generated method stub
		return dispatchOrderMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(DispatchOrder record) {
		// TODO Auto-generated method stub
		return dispatchOrderMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(DispatchOrder record) {
		// TODO Auto-generated method stub
		return dispatchOrderMapper.updateByPrimaryKey(record);
	}

	@Override
	public List<DispatchOrder> select(String transferID) {
		// TODO Auto-generated method stub
		return dispatchOrderMapper.select(transferID);
	}

}
