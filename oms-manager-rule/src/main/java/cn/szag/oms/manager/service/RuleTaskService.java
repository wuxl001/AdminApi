package cn.szag.oms.manager.service;

import cn.szag.oms.manager.common.domain.manager.RuleTask;

public interface RuleTaskService {
    int deleteByPrimaryKey(String id);

    int insert(RuleTask record);

    int insertSelective(RuleTask record);

    RuleTask selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(RuleTask record);

    int updateByPrimaryKey(RuleTask record);
}