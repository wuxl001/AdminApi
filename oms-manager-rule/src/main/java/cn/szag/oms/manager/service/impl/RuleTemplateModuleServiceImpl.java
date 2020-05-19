package cn.szag.oms.manager.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.szag.oms.manager.common.dao.manager.RuleTemplateModuleMapper;
import cn.szag.oms.manager.common.domain.manager.RuleTemplateModule;
import cn.szag.oms.manager.service.RuleTemplateModuleService;
/**
 * 模板组件
* @ClassName: RuleTemplateModuleServiceImpl 
* @Description: TODO
* @author dengyanghao
* @date 2019年12月11日 上午11:44:11
 */
@Service("ruleTemplateModuleService")
public class RuleTemplateModuleServiceImpl implements RuleTemplateModuleService {
	@Autowired
	private RuleTemplateModuleMapper ruleTemplateModuleMapper;
	@Override
	public int deleteByPrimaryKey(String id) {
		// TODO Auto-generated method stub
		return ruleTemplateModuleMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(RuleTemplateModule record) {
		// TODO Auto-generated method stub
		return ruleTemplateModuleMapper.insert(record);
	}

	@Override
	public int insertSelective(RuleTemplateModule record) {
		// TODO Auto-generated method stub
		return ruleTemplateModuleMapper.insertSelective(record);
	}

	@Override
	public RuleTemplateModule selectByPrimaryKey(String id) {
		// TODO Auto-generated method stub
		return ruleTemplateModuleMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(RuleTemplateModule record) {
		// TODO Auto-generated method stub
		return ruleTemplateModuleMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(RuleTemplateModule record) {
		// TODO Auto-generated method stub
		return ruleTemplateModuleMapper.updateByPrimaryKey(record);
	}

}
