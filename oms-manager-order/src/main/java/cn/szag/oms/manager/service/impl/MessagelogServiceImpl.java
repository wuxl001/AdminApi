package cn.szag.oms.manager.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.szag.oms.manager.common.dao.manager.MessagelogMapper;
import cn.szag.oms.manager.common.domain.manager.Messagelog;
import cn.szag.oms.manager.service.MessagelogService;
@Service("messagelogService")
public class MessagelogServiceImpl implements MessagelogService {
	@Autowired
	private MessagelogMapper messagelogMapper;
	@Override
	public int deleteByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return messagelogMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(Messagelog record) {
		// TODO Auto-generated method stub
		return messagelogMapper.insert(record);
	}

	@Override
	public int insertSelective(Messagelog record) {
		// TODO Auto-generated method stub
		return messagelogMapper.insertSelective(record);
	}

	@Override
	public Messagelog selectByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return messagelogMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(Messagelog record) {
		// TODO Auto-generated method stub
		return messagelogMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(Messagelog record) {
		// TODO Auto-generated method stub
		return messagelogMapper.updateByPrimaryKey(record);
	}

}
