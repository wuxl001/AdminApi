package cn.szag.oms.manager.controller;



import cn.szag.oms.manager.ajax.AjaxRes;
import cn.szag.oms.manager.domain.UserManage;
import cn.szag.oms.manager.enums.TopicEnum;
import cn.szag.oms.manager.utils.Const;
import cn.szag.oms.manager.utils.KafkaProvider;
import cn.szag.oms.manager.utils.RedisUtil;
import com.alibaba.fastjson.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;


/**
 * 用户管理，修改用户信息
 */
@RestController
@RequestMapping("/user")
public class UserLoginController {

    private KafkaProvider kafkaProvider = new KafkaProvider();

    /**
     * 用户管理修改用户功能
     * @param json
     * @return
     */
    @RequestMapping(value = "/edit",method = RequestMethod.POST)
    public AjaxRes editUser(HttpServletRequest request, String json) {

        kafkaProvider.sendMessage(TopicEnum.EDIT_USER.getCode(), new Date().getTime(), TopicEnum.EDIT_USER.getCode(), json);
        return new AjaxRes(Const.SUCCEED, "用户修改成功");
    }
}
