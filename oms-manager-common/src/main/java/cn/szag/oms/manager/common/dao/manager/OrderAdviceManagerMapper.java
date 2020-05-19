package cn.szag.oms.manager.common.dao.manager;

import cn.szag.oms.manager.common.domain.manager.OrderAdviceManager;

public interface OrderAdviceManagerMapper {
    int deleteByPrimaryKey(String id);

    int insert(OrderAdviceManager record);

    int insertSelective(OrderAdviceManager record);

    OrderAdviceManager selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(OrderAdviceManager record);

    int updateByPrimaryKey(OrderAdviceManager record);
    
    OrderAdviceManager findByUserId(String userId);
}