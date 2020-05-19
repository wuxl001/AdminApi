package cn.szag.oms.manager.common.dao.manager;

import org.apache.ibatis.annotations.Param;

import cn.szag.oms.manager.common.domain.manager.OrderTransferInfo;

/**
 * 运输信息
* @ClassName: OrderTransferInfoMapper 
* @Description: TODO
* @author dengyanghao
* @date 2019年9月16日 下午6:54:26
 */
public interface OrderTransferInfoMapper {
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
    * @param @param boxNo
    * @param @return
    * @author dengyanghao
    * @return OrderTransferInfo
    * @throws
     */
    OrderTransferInfo select(@Param("containerId")String containerId,@Param("orderId")String orderId);
}