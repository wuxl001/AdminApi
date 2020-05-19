package cn.szag.oms.manager.common.dao.manager;

import org.apache.ibatis.annotations.Param;

import cn.szag.oms.manager.common.domain.manager.OrderNationalFlag;

public interface OrderNationalFlagMapper {
    int deleteByPrimaryKey(String id);

    int insert(OrderNationalFlag record);

    int insertSelective(OrderNationalFlag record);

    OrderNationalFlag selectByPrimaryKey(@Param("id")String id,@Param("name")String name);

    int updateByPrimaryKeySelective(OrderNationalFlag record);

    int updateByPrimaryKey(OrderNationalFlag record);
}