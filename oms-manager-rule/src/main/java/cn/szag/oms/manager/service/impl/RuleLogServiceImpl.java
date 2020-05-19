package cn.szag.oms.manager.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.szag.oms.manager.common.dao.manager.RuleLogMapper;
import cn.szag.oms.manager.common.domain.manager.RuleLog;
import cn.szag.oms.manager.service.RuleLogService;
/**
 * 日志
* @ClassName: RuleLogServiceImpl 
* @Description: TODO
* @author dengyanghao
* @date 2019年12月11日 上午11:46:38
 */
@Service("ruleLogService")
public class RuleLogServiceImpl implements RuleLogService {
	@Autowired
	private RuleLogMapper ruleLogMapper;
	@Override
	public int deleteByPrimaryKey(String id) {
		// TODO Auto-generated method stub
		return ruleLogMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(RuleLog record) {
		// TODO Auto-generated method stub
		return ruleLogMapper.insert(record);
	}

	@Override
	public int insertSelective(RuleLog record) {
		// TODO Auto-generated method stub
		return ruleLogMapper.insertSelective(record);
	}

	@Override
	public RuleLog selectByPrimaryKey(String id) {
		// TODO Auto-generated method stub
		return ruleLogMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(RuleLog record) {
		// TODO Auto-generated method stub
		return ruleLogMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(RuleLog record) {
		// TODO Auto-generated method stub
		return ruleLogMapper.updateByPrimaryKey(record);
	}

}
