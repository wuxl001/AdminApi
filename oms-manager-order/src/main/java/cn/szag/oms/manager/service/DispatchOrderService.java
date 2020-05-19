package cn.szag.oms.manager.service;

import java.util.List;

import cn.szag.oms.manager.common.domain.manager.DispatchOrder;



/** 
 * 调度
* @ClassName: DispatchOrderService 
* @Description: TODO
* @author dengyanghao
* @date 2019年9月19日 下午7:39:23  
*/
public interface DispatchOrderService {
	
	int deleteByPrimaryKey(String id);

    int insert(DispatchOrder record);

    int insertSelective(DispatchOrder record);

    DispatchOrder selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(DispatchOrder record);

    int updateByPrimaryKey(DispatchOrder record);
    /**
     * 根据运输单Id获取调度信息列表
    * @Title: select 
    * @Description: TODO 
    * @param @param orderId
    * @param @return
    * @author dengyanghao
    * @return List<DispatchOrder>
    * @throws
     */
    List<DispatchOrder> select(String transferID);
}
