package cn.szag.oms.manager.common.dao.manager;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.szag.oms.manager.common.domain.manager.OrderContainerAdvice;
import cn.szag.oms.manager.common.page.Page;

/**
 * 报空通知
* @ClassName: OrderContainerAdviceMapper 
* @Description: TODO
* @author dengyanghao
* @date 2019年9月19日 下午5:18:04
 */
public interface OrderContainerAdviceMapper {
    int deleteByPrimaryKey(String id);

    int insert(OrderContainerAdvice record);

    int insertSelective(OrderContainerAdvice record);

    OrderContainerAdvice selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(OrderContainerAdvice record);

    int updateByPrimaryKey(OrderContainerAdvice record);
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
    List<OrderContainerAdvice> findByPage(@Param("advice")OrderContainerAdvice orderContainerAdvice,Page page);
    /**
     * 查询详情
    * @Title: findByPage 
    * @Description: TODO 
    * @param @param orderContainerAdvice
    * @param @param page
    * @param @return
    * @author dengyanghao
    * @return List<OrderContainerAdvice>
    * @throws
     */
    OrderContainerAdvice select(@Param("orderId")String orderId ,@Param("containerId") String containerId,@Param("sponsorId")String sponsorId);
}