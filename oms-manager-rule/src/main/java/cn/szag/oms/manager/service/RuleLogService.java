package cn.szag.oms.manager.service;

import cn.szag.oms.manager.common.domain.manager.RuleLog;

public interface RuleLogService {
    int deleteByPrimaryKey(String id);

    int insert(RuleLog record);

    int insertSelective(RuleLog record);

    RuleLog selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(RuleLog record);

    int updateByPrimaryKey(RuleLog record);
}