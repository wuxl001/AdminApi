package cn.szag.oms.manager.service;

import cn.szag.oms.manager.common.domain.manager.OrderAdviceManager;

/** 
* @ClassName: OrderAdviceManagerService 
* @Description: TODO
* @author dengyanghao
* @date 2019年11月22日 下午4:40:59  
*/
public interface OrderAdviceManagerService {
	int deleteByPrimaryKey(String id);

    int insert(OrderAdviceManager record);

    int insertSelective(OrderAdviceManager record);

    OrderAdviceManager selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(OrderAdviceManager record);

    int updateByPrimaryKey(OrderAdviceManager record);
    
    OrderAdviceManager findByUserId(String userId);

}
