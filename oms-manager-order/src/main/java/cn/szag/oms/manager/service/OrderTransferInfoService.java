package cn.szag.oms.manager.service;

import cn.szag.oms.manager.common.domain.manager.OrderTransferInfo;

/** 
* @ClassName: OrderTransferInfoService 
* @Description: TODO
* @author dengyanghao
* @date 2019年9月19日 下午7:01:15  
*/
public interface OrderTransferInfoService {
	
	int deleteByPrimaryKey(String id);

    int insert(OrderTransferInfo record);

    int insertSelective(OrderTransferInfo record);

    OrderTransferInfo selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(OrderTransferInfo record);

    int updateByPrimaryKeyWithBLOBs(OrderTransferInfo record);

    int updateByPrimaryKey(OrderTransferInfo record);
    /**
     * 查询明细（运输）
    * @Title: select 
    * @Description: TODO 
    * @param @param id
    * @param @param orderId
    * @param @return
    * @author dengyanghao
    * @return OrderTransferInfo
    * @throws
     */
    OrderTransferInfo select(String containerId,String orderId);
}
