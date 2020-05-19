package cn.szag.oms.manager.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.szag.oms.manager.common.dao.manager.RuleFlowMapper;
import cn.szag.oms.manager.common.domain.manager.RuleFlow;
import cn.szag.oms.manager.service.RuleFlowService;
/**
 * 流程
* @ClassName: RuleFlowServiceImpl 
* @Description: TODO
* @author dengyanghao
* @date 2019年12月11日 上午11:42:19
 */
@Service("ruleFlowService")
public class RuleFlowServiceImpl implements RuleFlowService {
	@Autowired
	private RuleFlowMapper ruleFlowMapper;
	@Override
	public int deleteByPrimaryKey(String id) {
		// TODO Auto-generated method stub
		return ruleFlowMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(RuleFlow record) {
		// TODO Auto-generated method stub
		return ruleFlowMapper.insert(record);
	}

	@Override
	public int insertSelective(RuleFlow record) {
		// TODO Auto-generated method stub
		return ruleFlowMapper.insertSelective(record);
	}

	@Override
	public RuleFlow selectByPrimaryKey(String id) {
		// TODO Auto-generated method stub
		return ruleFlowMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(RuleFlow record) {
		// TODO Auto-generated method stub
		return ruleFlowMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(RuleFlow record) {
		// TODO Auto-generated method stub
		return ruleFlowMapper.updateByPrimaryKey(record);
	}

}
