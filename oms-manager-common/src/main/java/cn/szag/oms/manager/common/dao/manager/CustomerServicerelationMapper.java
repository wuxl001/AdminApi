package cn.szag.oms.manager.common.dao.manager;

import cn.szag.oms.manager.common.domain.manager.CustomerServicerelation;

public interface CustomerServicerelationMapper {
    int deleteByPrimaryKey(Long id);

    int insert(CustomerServicerelation record);

    int insertSelective(CustomerServicerelation record);

    CustomerServicerelation selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(CustomerServicerelation record);

    int updateByPrimaryKey(CustomerServicerelation record);
    
    int findCusomerIs(String id);
    
    int findCount();
}