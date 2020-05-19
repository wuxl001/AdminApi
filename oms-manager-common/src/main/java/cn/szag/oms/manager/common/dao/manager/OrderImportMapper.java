package cn.szag.oms.manager.common.dao.manager;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.szag.oms.manager.common.domain.manager.OrderImport;
import cn.szag.oms.manager.common.domain.manager.User;
import cn.szag.oms.manager.common.page.Page;
/**
 * 进口
* @ClassName: OrderImportMapper 
* @Description: TODO
* @author dengyanghao
* @date 2019年9月19日 下午5:28:51
 */
public interface OrderImportMapper {
    int deleteByPrimaryKey(String id);

    int insert(OrderImport record);

    int insertSelective(OrderImport record);

    OrderImport selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(OrderImport record);

    int updateByPrimaryKeyWithBLOBs(OrderImport record);

    int updateByPrimaryKey(OrderImport record);
    /**
     * 获取进口订单各个状态
    * @Title: findCount 
    * @Description: TODO 
    * @param @param codeName
    * @param @param user
    * @param @return
    * @author dengyanghao
    * @return int
    * @throws
     */
    int findCount(@Param("keyWord")int keyWord,@Param("user")User user);
    /**
     * 获取各个状态订单列表
    * @Title: findByStatus 
    * @Description: TODO 
    * @param @param codeName
    * @param @param user
    * @param @param page
    * @param @return
    * @author dengyanghao
    * @return List<OrderImport>
    * @throws
     */
    List<OrderImport> findByStatus(@Param("keyWord")int keyWord,@Param("user")User user,@Param("page")Page page);
    /**
     * 根据模糊查询条件获取订单列表
    * @Title: findByPage 
    * @Description: TODO 
    * @param @param order
    * @param @param user
    * @param @param page
    * @param @return
    * @author dengyanghao
    * @return List<OrderImport>
    * @throws
     */
    List<OrderImport> findByPage(@Param("order")OrderImport order,@Param("user")User user,@Param("page")Page page);
    /**
     * 查询24小时未审核订单
    * @Title: select24 
    * @Description: TODO 
    * @param @param user
    * @param @return
    * @author dengyanghao
    * @return int
    * @throws
     */
    int select24(@Param("user")User user);

    int findOrderNoCount(String no);
}