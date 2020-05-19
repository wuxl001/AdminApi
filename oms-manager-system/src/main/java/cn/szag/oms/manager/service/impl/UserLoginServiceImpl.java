package cn.szag.oms.manager.service.impl;

import cn.szag.oms.manager.common.dao.manager.LoginLogMapper;
import cn.szag.oms.manager.common.dao.manager.RolememberMapper;
import cn.szag.oms.manager.common.dao.manager.UserMapper;
import cn.szag.oms.manager.common.domain.manager.LoginLog;
import cn.szag.oms.manager.common.domain.manager.User;
import cn.szag.oms.manager.common.page.Page;
import cn.szag.oms.manager.service.UserLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service("userLoginService")
public class UserLoginServiceImpl implements UserLoginService{

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private LoginLogMapper loginLogMapper;

    @Override
    public int deleteByPrimaryKey(String id) {
        return userMapper.deleteByPrimaryKey(id);
    }
    @Override
    public int insertSelective(User record) {
        return userMapper.insertSelective(record);
    }
    @Override
    public User selectByPrimaryKey(String id) {
        return userMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(User record) {
        return 0;
    }

    @Override
    public int updateByPrimaryKey(User record) {
        return userMapper.updateByPrimaryKey(record);
    }

    /**
     *  分页查询
     * @param
     * @param page
     * @return
     */
    @Override
    public Page selectList(User user, Page page) {
        List<User> list = userMapper.selectList(user,page);
        page.setResults(list);
        return page;
    }



    /**
     *  通过用户账号和密码查询用户信息
     * @param account
     * @param password
     * @return
     */
    @Override
    public List<User>  login(String account, String password) {
        List<User> users = userMapper.login(account, password);

        return users;
    }

    /**
     *  通过用户账号查询用户
     * @param account
     * @return
     */
    @Override
    public User findByAccount(String account,String id) {

        return userMapper.findByAccount(account,id);
    }

    /**
     *  记录用户的登陆信息
     * @param record
     */
    @Override
    public void insertLoginLog(LoginLog record) {

        loginLogMapper.insertSelective(record);
    }


}
