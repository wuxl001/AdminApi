package cn.szag.oms.manager.common.dao.manager;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.szag.oms.manager.common.domain.manager.CustomerAccount;
import cn.szag.oms.manager.common.page.Page;

public interface CustomerAccountMapper {
    int deleteByPrimaryKey(String id);

    int insert(CustomerAccount record);

    int insertSelective(CustomerAccount record);

    CustomerAccount selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(CustomerAccount record);

    int updateByPrimaryKey(CustomerAccount record);
    
    List<CustomerAccount> select(@Param("cus")CustomerAccount account,@Param("page")Page page);
    /**
     * 根据客户Id查找属于客户Id的联系人
    * @Title: findByCustomerId 
    * @Description: TODO 
    * @param @param customerId
    * @param @return
    * @author dengyanghao
    * @return List<CustomerAccount>
    * @throws
     */
    List<CustomerAccount> findByCustomerId(String customerId);
    /**
     * 校验是否存在用户名、手机号码、邮箱
    * @Title: selectList 
    * @Description: TODO 
    * @param @param account
    * @param @return
    * @author dengyanghao
    * @return int
    * @throws
     */
    int selectList(@Param("cus")CustomerAccount account);
}