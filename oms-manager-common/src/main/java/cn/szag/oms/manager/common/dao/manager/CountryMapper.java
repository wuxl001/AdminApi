package cn.szag.oms.manager.common.dao.manager;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.szag.oms.manager.common.domain.manager.Country;
import cn.szag.oms.manager.common.domain.manager.OrderNationalFlag;
import cn.szag.oms.manager.common.page.Page;

public interface CountryMapper {
    int deleteByPrimaryKey(String id);

    int insert(Country record);

    int insertSelective(Country record);


    Country selectByPrimaryKey(@Param("id")String id,@Param("name")String name);


    int updateByPrimaryKeySelective(Country record);

    int updateByPrimaryKey(Country record);
    
    List<Country> selectList(@Param("keyword") String keyword,@Param("page") Page page);
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