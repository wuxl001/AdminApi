package cn.szag.oms.manager.service;

import cn.szag.oms.manager.common.domain.manager.RuleFlow;

public interface RuleFlowService {
    int deleteByPrimaryKey(String id);

    int insert(RuleFlow record);

    int insertSelective(RuleFlow record);

    RuleFlow selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(RuleFlow record);

    int updateByPrimaryKey(RuleFlow record);
}