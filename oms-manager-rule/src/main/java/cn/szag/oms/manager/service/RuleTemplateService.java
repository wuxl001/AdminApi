package cn.szag.oms.manager.service;

import cn.szag.oms.manager.common.domain.manager.RuleTemplate;

public interface RuleTemplateService {
    int deleteByPrimaryKey(String id);

    int insert(RuleTemplate record);

    int insertSelective(RuleTemplate record);

    RuleTemplate selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(RuleTemplate record);

    int updateByPrimaryKey(RuleTemplate record);
}