package cn.szag.oms.manager.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.szag.oms.manager.common.dao.manager.RuleTemplateMapper;
import cn.szag.oms.manager.common.domain.manager.RuleTemplate;
import cn.szag.oms.manager.service.RuleTemplateService;
/**
 * 模板
* @ClassName: RuleTemplateServiceImpl 
* @Description: TODO
* @author dengyanghao
* @date 2019年12月11日 上午11:43:05
 */
@Service("ruleTemplateService")
public class RuleTemplateServiceImpl implements RuleTemplateService {
	@Autowired
	private RuleTemplateMapper ruleTemplateMapper;
	@Override
	public int deleteByPrimaryKey(String id) {
		// TODO Auto-generated method stub
		return ruleTemplateMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(RuleTemplate record) {
		// TODO Auto-generated method stub
		return ruleTemplateMapper.insert(record);
	}

	@Override
	public int insertSelective(RuleTemplate record) {
		// TODO Auto-generated method stub
		return ruleTemplateMapper.insertSelective(record);
	}

	@Override
	public RuleTemplate selectByPrimaryKey(String id) {
		// TODO Auto-generated method stub
		return ruleTemplateMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(RuleTemplate record) {
		// TODO Auto-generated method stub
		return ruleTemplateMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(RuleTemplate record) {
		// TODO Auto-generated method stub
		return ruleTemplateMapper.updateByPrimaryKey(record);
	}

}
