package cn.szag.oms.manager.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.szag.oms.manager.common.dao.manager.RuleRepositoryMapper;
import cn.szag.oms.manager.common.domain.manager.RuleRepository;
import cn.szag.oms.manager.service.RuleRepositoryService;

/**
 * 知识库
* @ClassName: RuleRrepositoryServiceImpl 
* @Description: TODO
* @author dengyanghao
* @date 2019年12月11日 上午11:47:00
 */
@Service("ruleRepositoryService")
public class RuleRepositoryServiceImpl implements RuleRepositoryService {
	@Autowired
	private RuleRepositoryMapper ruleRrepositoryMapper;
	@Override
	public int deleteByPrimaryKey(String id) {
		// TODO Auto-generated method stub
		return ruleRrepositoryMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(RuleRepository record) {
		// TODO Auto-generated method stub
		return ruleRrepositoryMapper.insert(record);
	}

	@Override
	public int insertSelective(RuleRepository record) {
		// TODO Auto-generated method stub
		return ruleRrepositoryMapper.insertSelective(record);
	}

	@Override
	public RuleRepository selectByPrimaryKey(String id) {
		// TODO Auto-generated method stub
		return ruleRrepositoryMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(RuleRepository record) {
		// TODO Auto-generated method stub
		return ruleRrepositoryMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(RuleRepository record) {
		// TODO Auto-generated method stub
		return ruleRrepositoryMapper.updateByPrimaryKey(record);
	}

}
