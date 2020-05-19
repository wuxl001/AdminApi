package cn.szag.oms.manager.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.szag.oms.manager.common.dao.manager.PortMapper;
import cn.szag.oms.manager.common.domain.manager.Port;
import cn.szag.oms.manager.common.page.Page;
import cn.szag.oms.manager.service.PortService;

@Service("portService")
public class PortServiceImpl implements PortService {
	@Autowired
	private PortMapper portMapper;
	@Override
	public int deleteByPrimaryKey(String id) {
		// TODO Auto-generated method stub
		return portMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(Port record) {
		// TODO Auto-generated method stub
		return portMapper.insert(record);
	}

	@Override
	public int insertSelective(Port record) {
		// TODO Auto-generated method stub
		return portMapper.insertSelective(record);
	}

	@Override
	public Port selectByPrimaryKey(String id) {
		// TODO Auto-generated method stub
		return portMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(Port record) {
		// TODO Auto-generated method stub
		return portMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKeyWithBLOBs(Port record) {
		// TODO Auto-generated method stub
		return portMapper.updateByPrimaryKeyWithBLOBs(record);
	}

	@Override
	public int updateByPrimaryKey(Port record) {
		// TODO Auto-generated method stub
		return portMapper.updateByPrimaryKey(record);
	}

	@Override
	public List<Port> findByPage(Port port, Page page) {
		// TODO Auto-generated method stub
		return portMapper.findByPage(port, page);
	}

}
