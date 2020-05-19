package cn.szag.oms.manager.controller;

import cn.szag.oms.manager.common.ajax.AjaxRes;
import cn.szag.oms.manager.common.domain.manager.ManagerNotice;
import cn.szag.oms.manager.common.domain.manager.User;
import cn.szag.oms.manager.common.utils.Const;
import cn.szag.oms.manager.common.utils.UserException;
import cn.szag.oms.manager.service.ManagerNoticeService;
import com.alibaba.fastjson.JSON;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;


import javax.servlet.http.HttpServletRequest;

/**
 * 消息推送中心 修改消息状态
 */
@RestController
@RequestMapping("/board")
public class PushMessageController {



    @Autowired
    private ManagerNoticeService managerNoticeService;

    private final String external_url = "http://127.0.0.1:8080/oms-manager-external/board";


    /**
     * 消息修改功能
     * @param request
     * @return
     */
    @RequestMapping("/noticeEdit")
    public AjaxRes noticeEdit(HttpServletRequest request, ManagerNotice managerNotice ) {


            String token = null;
            User user = null;
            try {
                String json = request.getParameter("json");
                if ("".equals(json) || json == null) {
                    return new AjaxRes(Const.SUCCEED, "未获得数据");
                } else {
                    managerNotice = JSON.parseObject(json, ManagerNotice.class);
                    managerNoticeService.updateByPrimaryKeySelective(managerNotice);
                    return new AjaxRes(Const.SUCCEED, "订单删除成功");
                }
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
