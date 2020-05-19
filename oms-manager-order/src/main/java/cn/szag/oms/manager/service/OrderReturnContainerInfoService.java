package cn.szag.oms.manager.service;

import cn.szag.oms.manager.common.domain.manager.OrderReturnContainerInfo;

/** 
 * 报空信息
* @ClassName: OrderReturnContainerInfoService 
* @Description: TODO
* @author dengyanghao
* @date 2019年9月17日 上午8:50:11  
*/
public interface OrderReturnContainerInfoService {
	
	int deleteByPrimaryKey(String id);

    int insert(OrderReturnContainerInfo record);

    int insertSelective(OrderReturnContainerInfo record);

    OrderReturnContainerInfo selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(OrderReturnContainerInfo record);

    int updateByPrimaryKeyWithBLOBs(OrderReturnContainerInfo record);

    int updateByPrimaryKey(OrderReturnContainerInfo record);
    /**
     * 根据订单Id以及集装箱Id获取报空信息
    * @Title: select 
    * @Description: TODO 
    * @param @param orderId
    * @param @param containerId
    * @param @return
    * @author dengyanghao
    * @return OrderReturnContainerInfo
    * @throws
     */
    OrderReturnContainerInfo select(String orderId,String containerId);
}
