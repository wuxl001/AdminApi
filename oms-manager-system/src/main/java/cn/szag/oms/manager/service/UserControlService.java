package cn.szag.oms.manager.service;

import cn.szag.oms.manager.common.domain.manager.User;
import cn.szag.oms.manager.common.page.Page;

public interface UserControlService {

    int deleteByPrimaryKey(String id);

    int insertSelective(User record);

    User selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    void insert(User record, String[] roleIds);

    Page selectList(User ur, Page page);

    void updateUser(User ur, String[] roleIds);

    User findByAccount(String account,String id);
    
    User findByuserName(String userName);
    /**
     * 修改用户极光推送Id
    * @Title: updateRegistrationId 
    * @Description: TODO 
    * @param @param jpushId
    * @param @param id
    * @param @return
    * @author dengyanghao
    * @return int
    * @throws
     */
    int updateRegistrationId(String jpushId,String id);
}
