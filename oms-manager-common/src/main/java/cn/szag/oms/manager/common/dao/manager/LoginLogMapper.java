package cn.szag.oms.manager.common.dao.manager;


import cn.szag.oms.manager.common.domain.manager.LoginLog;
import cn.szag.oms.manager.common.domain.manager.User;

public interface LoginLogMapper {
    int deleteByPrimaryKey(String id);

    int insert(LoginLog record);

    int insertSelective(LoginLog record);

    LoginLog selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(LoginLog record);

    int updateByPrimaryKey(LoginLog record);

    // 通过用户查询用户信息
    User findByAccount(String account);
}