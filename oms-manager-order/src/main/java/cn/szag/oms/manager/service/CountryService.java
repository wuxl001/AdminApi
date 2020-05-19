package cn.szag.oms.manager.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.szag.oms.manager.common.domain.manager.Attention;
import cn.szag.oms.manager.common.domain.manager.Country;
import cn.szag.oms.manager.common.domain.manager.OrderNationalFlag;
import cn.szag.oms.manager.common.domain.manager.User;
import cn.szag.oms.manager.common.page.Page;

/**
 * @ClassName: AttentionService
 * @Description: TODO
 * @author dengyanghao
 * @date 2019年9月19日 上午11:49:10
 */
public interface CountryService {
	int deleteByPrimaryKey(String id);

	int insert(Country record);

	int insertSelective(Country record);


	Country selectByPrimaryKey(String id,String name);


	int updateByPrimaryKeySelective(Country record);

	int updateByPrimaryKey(Country record);

	List<Country> findByPage(String keyword, Page page);
	/**
     * 根据id查出国家详情
    * @Title: find 
    * @Description: TODO 
    * @param @param id
    * @param @return
    * @author dengyanghao
    * @return Country
    * @throws
     */
    Country find(String id);

}
