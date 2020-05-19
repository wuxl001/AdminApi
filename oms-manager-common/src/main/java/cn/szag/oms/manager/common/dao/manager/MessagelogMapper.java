package cn.szag.oms.manager.common.dao.manager;

import cn.szag.oms.manager.common.domain.manager.Messagelog;

public interface MessagelogMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Messagelog record);

    int insertSelective(Messagelog record);

    Messagelog selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Messagelog record);

    int updateByPrimaryKey(Messagelog record);
}