package cn.szag.oms.manager.common.dao.manager;

import cn.szag.oms.manager.common.domain.manager.RuleFlow;

public interface RuleFlowMapper {
    int deleteByPrimaryKey(String id);

    int insert(RuleFlow record);

    int insertSelective(RuleFlow record);

    RuleFlow selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(RuleFlow record);

    int updateByPrimaryKey(RuleFlow record);
}