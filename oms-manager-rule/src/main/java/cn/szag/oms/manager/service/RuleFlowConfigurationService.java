package cn.szag.oms.manager.service;


import cn.szag.oms.manager.common.domain.manager.RuleFlowConfiguration;

public interface RuleFlowConfigurationService {
	int deleteByPrimaryKey(String id);

    int insert(RuleFlowConfiguration record);

    int insertSelective(RuleFlowConfiguration record);

    RuleFlowConfiguration selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(RuleFlowConfiguration record);

    int updateByPrimaryKey(RuleFlowConfiguration record);
    /**
     * 获取流程编号
    * @Title: generateOrderNo 
    * @Description: TODO 
    * @param @param orderExport
    * @param @return
    * @author dengyanghao
    * @return String
    * @throws
     */
    String generateOrderNo(RuleFlowConfiguration record);
}