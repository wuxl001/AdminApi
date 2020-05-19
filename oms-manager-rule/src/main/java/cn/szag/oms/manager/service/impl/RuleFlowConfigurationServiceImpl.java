package cn.szag.oms.manager.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.szag.oms.manager.common.dao.manager.RuleFlowConfigurationMapper;
import cn.szag.oms.manager.common.domain.manager.RuleFlowConfiguration;
import cn.szag.oms.manager.service.RuleFlowConfigurationService;

/**
 * 流程配置
* @ClassName: RuleFlowConfigurationServiceImpl 
* @Description: TODO
* @author dengyanghao
* @date 2019年12月11日 上午11:47:36
 */
@Service("ruleFlowConfigurationService")
public class RuleFlowConfigurationServiceImpl implements RuleFlowConfigurationService{
	@Autowired
	private RuleFlowConfigurationMapper ruleFlowConfigurationMapper;

	@Override
	public int deleteByPrimaryKey(String id) {
		// TODO Auto-generated method stub
		return ruleFlowConfigurationMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(RuleFlowConfiguration record) {
		// TODO Auto-generated method stub
		return ruleFlowConfigurationMapper.insert(record);
	}

	@Override
	public int insertSelective(RuleFlowConfiguration record) {
		// TODO Auto-generated method stub
		return ruleFlowConfigurationMapper.insertSelective(record);
	}

	@Override
	public RuleFlowConfiguration selectByPrimaryKey(String id) {
		// TODO Auto-generated method stub
		return ruleFlowConfigurationMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(RuleFlowConfiguration record) {
		// TODO Auto-generated method stub
		return ruleFlowConfigurationMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(RuleFlowConfiguration record) {
		// TODO Auto-generated method stub
		return ruleFlowConfigurationMapper.updateByPrimaryKey(record);
	}

	@Override
	public String generateOrderNo(RuleFlowConfiguration record) {
		// TODO Auto-generated method stub
		return null;
	}
}
