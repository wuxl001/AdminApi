package cn.szag.oms.manager.service;

import cn.szag.oms.manager.common.domain.manager.LoginLog;
import cn.szag.oms.manager.common.domain.manager.User;
import cn.szag.oms.manager.common.page.Page;

import java.util.List;

public interface UserLoginService {


    int deleteByPrimaryKey(String id);

    int insertSelective(User record);

    User selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    Page selectList(User ur, Page page);


    List<User> login(String account, String password);

    // 通过用户账号查询用户
    User findByAccount(String account,String id);

    // 记录登陆的日志信息
    void insertLoginLog(LoginLog record);


}
