package cn.szag.oms.manager.service;

import cn.szag.oms.manager.common.domain.manager.OrderClearanceInfo;

/** 
* @ClassName: OrderClearanceInfoService 
* @Description: TODO
* @author dengyanghao
* @date 2019年9月16日 下午4:42:41  
*/
public interface OrderClearanceInfoService {
	
	int deleteByPrimaryKey(String id);

    int insert(OrderClearanceInfo record);

    int insertSelective(OrderClearanceInfo record);

    OrderClearanceInfo selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(OrderClearanceInfo record);

    int updateByPrimaryKeyWithBLOBs(OrderClearanceInfo record);

    int updateByPrimaryKey(OrderClearanceInfo record);
    /**
     * 根据部分信息查询报关明细
    * @Title: select 
    * @Description: TODO 
    * @param @param record
    * @param @return
    * @author dengyanghao
    * @return OrderClearanceInfo
    * @throws
     */
    OrderClearanceInfo select(String orderId,String containerId);

}
