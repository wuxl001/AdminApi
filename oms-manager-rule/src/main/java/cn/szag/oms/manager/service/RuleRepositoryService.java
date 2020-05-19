package cn.szag.oms.manager.service;

import cn.szag.oms.manager.common.domain.manager.RuleRepository;

public interface RuleRepositoryService {
    int deleteByPrimaryKey(String id);

    int insert(RuleRepository record);

    int insertSelective(RuleRepository record);

    RuleRepository selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(RuleRepository record);

    int updateByPrimaryKey(RuleRepository record);
}