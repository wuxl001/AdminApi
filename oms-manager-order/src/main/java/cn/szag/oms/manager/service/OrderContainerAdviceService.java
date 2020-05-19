package cn.szag.oms.manager.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.szag.oms.manager.common.domain.manager.OrderContainerAdvice;
import cn.szag.oms.manager.common.page.Page;

/** 
 * 报空通知
* @ClassName: OrderContainerAdviceServiceImpl 
* @Description: TODO
* @author dengyanghao
* @date 2019年9月19日 上午10:53:20  
*/
public interface OrderContainerAdviceService {
	int deleteByPrimaryKey(String id);

    int insert(OrderContainerAdvice record);

    int insertSelective(OrderContainerAdvice record);

    OrderContainerAdvice selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(OrderContainerAdvice record);

    int updateByPrimaryKey(OrderContainerAdvice record);
    
    List<OrderContainerAdvice> findByPage(OrderContainerAdvice orderContainerAdvice,Page page);
    
    /**
     * 分页模糊查询
    * @Title: findByPage 
    * @Description: TODO 
    * @param @param orderContainerAdvice
    * @param @param page
    * @param @return
    * @author dengyanghao
    * @return List<OrderContainerAdvice>
    * @throws
     */
    OrderContainerAdvice select(String orderId ,String containerId,String sponsorId);

}
