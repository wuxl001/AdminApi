package cn.szag.oms.manager.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.szag.oms.manager.common.dao.manager.CountryMapper;
import cn.szag.oms.manager.common.domain.manager.Country;
import cn.szag.oms.manager.common.domain.manager.OrderNationalFlag;
import cn.szag.oms.manager.common.page.Page;
import cn.szag.oms.manager.service.CountryService;

@Service("countryService")
public class CountryServiceImpl implements CountryService{

	@Autowired
	private CountryMapper countryMapper;
	
	
	@Override
	public int deleteByPrimaryKey(String id) {
		// TODO Auto-generated method stub
		return countryMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(Country record) {
		// TODO Auto-generated method stub
		return countryMapper.insert(record);
	}

	@Override
	public int insertSelective(Country record) {
		// TODO Auto-generated method stub
		return countryMapper.insertSelective(record);
	}

	@Override
	public Country selectByPrimaryKey(String id,String name) {
		// TODO Auto-generated method stub
		return countryMapper.selectByPrimaryKey(id,name);
	}

	@Override
	public int updateByPrimaryKeySelective(Country record) {
		// TODO Auto-generated method stub
		return countryMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(Country record) {
		// TODO Auto-generated method stub
		return countryMapper.updateByPrimaryKey(record);
	}

	@Override
	public List<Country> findByPage(String keyword, Page page) {
		// TODO Auto-generated method stub
		return countryMapper.selectList(keyword, page);
	}

	@Override
	public Country find(String id) {
		// TODO Auto-generated method stub
		return countryMapper.find(id);
	}

	
}
