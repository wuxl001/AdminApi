package cn.szag.oms.manager.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.szag.oms.manager.common.domain.manager.Customer;
import cn.szag.oms.manager.common.page.Page;

/** 客户账户管理
* @ClassName: CustomerService 
* @Description: TODO
* @author dengyanghao
* @date 2019年9月25日 下午5:30:59  
*/
public interface CustomerService {
	
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
    List<Customer> findByPage(Customer customer,Page page);
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
    int selectList(Customer customer);
}
