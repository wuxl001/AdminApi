package cn.szag.oms.manager.common.dao.manager;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.szag.oms.manager.common.domain.manager.Customer;
import cn.szag.oms.manager.common.page.Page;

public interface CustomerMapper {
    int deleteByPrimaryKey(String id);

    int insert(Customer record);

    int insertSelective(Customer record);

    Customer selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Customer record);

    int updateByPrimaryKey(Customer record);
    /**
     * 分页模糊查询
    * @Title: findByPage 
    * @Description: TODO 
    * @param @param customer
    * @param @param page
    * @param @return
    * @author dengyanghao
    * @return List<Customer>
    * @throws
     */
    List<Customer> findByPage(@Param("customer")Customer customer,@Param("page")Page page);
    /**
     * 校验客户公司以及客户编码是否存在
    * @Title: selectList 
    * @Description: TODO 
    * @param @param customer
    * @param @return
    * @author dengyanghao
    * @return int
    * @throws
     */
    int selectList(@Param("customer")Customer customer);
}