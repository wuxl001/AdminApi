package cn.szag.oms.manager.service.impl;

import cn.szag.oms.manager.common.dao.manager.RolememberMapper;
import cn.szag.oms.manager.common.dao.manager.UserMapper;
import cn.szag.oms.manager.common.domain.manager.Role;
import cn.szag.oms.manager.common.domain.manager.User;
import cn.szag.oms.manager.common.page.Page;
import cn.szag.oms.manager.common.utils.UuidUtil;
import cn.szag.oms.manager.service.UserControlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Service("userControlService")
public class UserControlServiceImpl implements UserControlService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RolememberMapper rolememberMapper;

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
    public int updateByPrimaryKey(User record) {
        return userMapper.updateByPrimaryKey(record);
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

        // 添加用户，并添加到中间表
    @Override
    public void insert(User record, String[] roleIds) {
        userMapper.insert(record);
        String userId = record.getId();
        if (roleIds !=null && roleIds.length>0) {
            for (String roleId : roleIds) {
                Map<String, String> map = new HashMap<String, String>();
                map.put("id", UuidUtil.get32UUID());
                map.put("userId", userId);
                map.put("roleId", roleId);
                rolememberMapper.addRoleIds(map);
            }
        }

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

    // 修改用户，需要先删除中间表，然后重新添加
    @Override
    public void updateUser(User ur, String[] roleIds) {
        // 先删除中间表
        rolememberMapper.deleteByUserId(ur.getId());
        // 添加用户表和中间表
        String userId = ur.getId();
        if (roleIds !=null && roleIds.length>0 ) {
            for (String roleId : roleIds) {
                Map<String, String> map = new HashMap<String, String>();
                map.put("id", UuidUtil.get32UUID());
                map.put("userId", userId);
                map.put("roleId", roleId);
                rolememberMapper.addRoleIds(map);
            }
        }
        userMapper.updateByPrimaryKeySelective(ur);


    }


    /**
     * 删除用户，逻辑删除，并需要删除中间表
     * @param record
     * @return
     */
    @Override
    public int updateByPrimaryKeySelective(User record) {
        rolememberMapper.deleteByUserId(record.getId());
        System.out.println(record);
        return userMapper.updateByPrimaryKeySelective(record);
    }
	@Override
	public User findByuserName(String userName) {
		// TODO Auto-generated method stub
		return userMapper.findByuserName(userName);
	}
	@Override
	public int updateRegistrationId(String jpushId, String id) {
		// TODO Auto-generated method stub
		return userMapper.updateRegistrationId(jpushId, id);
	}
}
