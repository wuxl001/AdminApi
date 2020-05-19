package cn.szag.oms.manager.common.dao.manager;


import cn.szag.oms.manager.common.domain.manager.User;
import cn.szag.oms.manager.common.page.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {
    int deleteByPrimaryKey(String id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    List<User> selectList(@Param("user") User user, Page page);

    List<User>  login(@Param("account")String account, @Param("password")String password);

    // 通过用户查询用户信息
    User findByAccount(@Param("account")String account,@Param("id")String id);
    //通过姓名查询用户信息
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
    int updateRegistrationId(@Param("jpushId")String jpushId,@Param("id")String id);
}