package cn.szag.oms.manager.common.dao.manager;

import cn.szag.oms.manager.common.domain.manager.RuleFlowConfiguration;

public interface RuleFlowConfigurationMapper {
    int deleteByPrimaryKey(String id);

    int insert(RuleFlowConfiguration record);

    int insertSelective(RuleFlowConfiguration record);

    RuleFlowConfiguration selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(RuleFlowConfiguration record);

    int updateByPrimaryKey(RuleFlowConfiguration record);
}