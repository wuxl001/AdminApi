package cn.szag.oms.manager.common.dao.manager;

import cn.szag.oms.manager.common.domain.manager.RuleTemplateModule;

public interface RuleTemplateModuleMapper {
    int deleteByPrimaryKey(String id);

    int insert(RuleTemplateModule record);

    int insertSelective(RuleTemplateModule record);

    RuleTemplateModule selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(RuleTemplateModule record);

    int updateByPrimaryKey(RuleTemplateModule record);
}