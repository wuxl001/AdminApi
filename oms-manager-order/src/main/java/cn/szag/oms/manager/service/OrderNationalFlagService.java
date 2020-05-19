package cn.szag.oms.manager.service;

import cn.szag.oms.manager.common.domain.manager.OrderNationalFlag;

/** 国旗国家关系
* @ClassName: OrderNationalFlagService 
* @Description: TODO
* @author dengyanghao
* @date 2019年9月27日 上午9:14:30  
*/
public interface OrderNationalFlagService {
	int deleteByPrimaryKey(String id);

    int insert(OrderNationalFlag record);

    int insertSelective(OrderNationalFlag record);

    OrderNationalFlag selectByPrimaryKey(String id,String name);

    int updateByPrimaryKeySelective(OrderNationalFlag record);

    int updateByPrimaryKey(OrderNationalFlag record);

}
