package cn.szag.oms.manager.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.szag.oms.manager.common.dao.manager.CodeNationalityMapper;
import cn.szag.oms.manager.common.domain.manager.CodeNationality;
import cn.szag.oms.manager.common.domain.manager.Country;
import cn.szag.oms.manager.common.page.Page;
import cn.szag.oms.manager.service.CodeNationalityService;

@Service("codeNationalityService")
public class CodeNationalityServiceImpl implements CodeNationalityService{
	
	@Autowired
	private CodeNationalityMapper codeNationalityMapper;

	@Override
	public int deleteByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return codeNationalityMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(CodeNationality record) {
		// TODO Auto-generated method stub
		return codeNationalityMapper.insert(record);
	}

	@Override
	public int insertSelective(CodeNationality record) {
		// TODO Auto-generated method stub
		return codeNationalityMapper.insertSelective(record);
	}

	@Override
	public CodeNationality selectByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return codeNationalityMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(CodeNationality record) {
		// TODO Auto-generated method stub
		return codeNationalityMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(CodeNationality record) {
		// TODO Auto-generated method stub
		return codeNationalityMapper.updateByPrimaryKey(record);
	}

	@Override
	public List<CodeNationality> selectList(String keyword, Page page) {
		// TODO Auto-generated method stub
		return codeNationalityMapper.selectList(keyword, page);
	}

	

}
