package cn.szag.oms.manager.controller;

import cn.szag.oms.manager.ajax.AjaxRes;
import cn.szag.oms.manager.domain.ManagerNotice;
import cn.szag.oms.manager.enums.TopicEnum;
import cn.szag.oms.manager.utils.Const;
import cn.szag.oms.manager.utils.KafkaProvider;
import cn.szag.oms.manager.utils.UserException;
import cn.szag.oms.manager.utils.Verification;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * 消息推送中心
 */
@RestController
@RequestMapping("/board")
public class PushMessageController {


    private KafkaProvider kafkaProvider = new KafkaProvider();
    /**
     * 消息修改功能
     * @param request
     * @return
     */
    @RequestMapping("/noticeEdit")
    public AjaxRes noticeEdit(HttpServletRequest request,String json ){

        try {

            System.out.println(json);
            kafkaProvider.sendMessage(TopicEnum.EDIT_NOTICE.getCode(), new Date().getTime(), TopicEnum.EDIT_NOTICE.getCode(),json);

            return new AjaxRes(Const.SUCCEED,"修改消息功能成功");
        } catch (NumberFormatException e) {
            return new AjaxRes(Const.FAIL, "参数错误！！");
        } catch (UserException e) {
            e.printStackTrace();
            return new AjaxRes(Const.FAIL, "令牌为空！");
        } catch (Exception e) {
            e.printStackTrace();
            return new AjaxRes(Const.FAIL, "查询订单信息失败，出现异常");
        }

    }


}
