package cn.szag.oms.manager.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.szag.oms.manager.common.dao.manager.RuleTaskMapper;
import cn.szag.oms.manager.common.domain.manager.RuleTask;
import cn.szag.oms.manager.service.RuleTaskService;
/**
 * 任务
* @ClassName: RuleTaskServiceImpl 
* @Description: TODO
* @author dengyanghao
* @date 2019年12月11日 上午11:44:32
 */
@Service("ruleTaskService")
public class RuleTaskServiceImpl implements RuleTaskService {
	@Autowired
	private RuleTaskMapper ruleTaskMapper;
	@Override
	public int deleteByPrimaryKey(String id) {
		// TODO Auto-generated method stub
		return ruleTaskMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(RuleTask record) {
		// TODO Auto-generated method stub
		return ruleTaskMapper.insert(record);
	}

	@Override
	public int insertSelective(RuleTask record) {
		// TODO Auto-generated method stub
		return ruleTaskMapper.insertSelective(record);
	}

	@Override
	public RuleTask selectByPrimaryKey(String id) {
		// TODO Auto-generated method stub
		return ruleTaskMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(RuleTask record) {
		// TODO Auto-generated method stub
		return ruleTaskMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(RuleTask record) {
		// TODO Auto-generated method stub
		return ruleTaskMapper.updateByPrimaryKey(record);
	}

}
