package cn.szag.oms.manager.controller;

import cn.szag.oms.manager.ajax.AjaxRes;
import cn.szag.oms.manager.domain.OrderAttachment;
import cn.szag.oms.manager.domain.OrderImport;
import cn.szag.oms.manager.domain.User;
import cn.szag.oms.manager.enums.TopicEnum;
import cn.szag.oms.manager.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * 自助下单功能
 */
@RestController
@RequestMapping("/orderI")
public class AutomaticOrderController {


    private KafkaProvider kafkaProvider = new KafkaProvider();


    @Autowired
    private RedisUtil redisUtil;


    /**
     * 新增订单
     * 写入 A 库 在order_import 表新增一条记录 调用业务管理中心 API，写入 B 库。
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public AjaxRes addOrder(HttpServletRequest request, String json) {
        try {
            System.out.println(json);
            kafkaProvider.sendMessage(TopicEnum.ADD_AUTOORDER.getCode(), new Date().getTime(), TopicEnum.ADD_SHIPPING.getCode(), json);
            return new AjaxRes(Const.SUCCEED,"订单添加成功");
        } catch (NumberFormatException e) {
            return new AjaxRes(Const.FAIL, "参数错误！！");
        } catch (UserException e) {
            e.printStackTrace();
            return new AjaxRes(Const.FAIL, "令牌为空！");
        } catch (Exception e) {
            e.printStackTrace();
            return new AjaxRes(Const.FAIL, "添加订单信息失败，出现异常");
        }

    }


    /**
     * 修改订单
     * 写入 A 库 在order_import 表修改一条记录 调用业务管理中心 API，写入 B 库。
     * @return
     */
    @RequestMapping(value = "/edit",method = RequestMethod.POST)
    public AjaxRes editOrder(HttpServletRequest request,String json) {
        try {

            System.out.println(json);
            kafkaProvider.sendMessage(TopicEnum.EDIT_AUTOORDER.getCode(), new Date().getTime(), TopicEnum.EDIT_AUTOORDER.getCode(), json);
            return new AjaxRes(Const.SUCCEED,"订单修改成功");
        } catch (NumberFormatException e) {
            return new AjaxRes(Const.FAIL, "参数错误！！");
        } catch (UserException e) {
            e.printStackTrace();
            return new AjaxRes(Const.FAIL, "令牌为空！");
        } catch (Exception e) {
            e.printStackTrace();
            return new AjaxRes(Const.FAIL, "修改订单信息失败，出现异常");
        }

    }



    /**
     * 删除订单
     * 1、用 A 库。在 order_import 表删除（逻辑删除）一条记录（已审核、待审核、已终止不能删）。调用业务管理中心 API，同步到 B 库。
     * @return
     */
    @RequestMapping(value = "/del",method = RequestMethod.POST)
    public AjaxRes delOrder(HttpServletRequest request,String json) {
        try {

            System.out.println(json);
            kafkaProvider.sendMessage(TopicEnum.DEL_AUTOORDER.getCode(), new Date().getTime(), TopicEnum.DEL_AUTOORDER.getCode(), json);
            return new AjaxRes(Const.SUCCEED,"订单删除成功！");
        } catch (NumberFormatException e) {
            return new AjaxRes(Const.FAIL, "参数错误！！");
        } catch (UserException e) {
            e.printStackTrace();
            return new AjaxRes(Const.FAIL, "令牌为空！");
        } catch (Exception e) {
            e.printStackTrace();
            return new AjaxRes(Const.FAIL, "删除订单失败，出现异常");
        }

    }


    /**
     * 附件上传
     * @param
     * @return
     */
    @RequestMapping(value = "/upload",method = RequestMethod.POST)
    public AjaxRes uploadFile(HttpServletRequest request,String json) {


        try {System.out.println(json);

            kafkaProvider.sendMessage(TopicEnum.UPLOAD_FILE.getCode(), new Date().getTime(), TopicEnum.UPLOAD_FILE.getCode(),json);
                return new AjaxRes(Const.SUCCEED, "附件数据保存成功");
            } catch (NumberFormatException e) {
                return new AjaxRes(Const.FAIL, "参数错误！！");
            } catch (UserException e) {
                e.printStackTrace();
                return new AjaxRes(Const.FAIL, "令牌为空！");
            } catch (Exception e) {
                e.printStackTrace();
                return new AjaxRes(Const.FAIL, "附件信息上传失败，出现异常");
            }
    }




    /**
     * 修改附件，用于读取/写入 A 库（主要给业务订单系统调用）
     * @return
     */
    @RequestMapping(value = "editAttachmen")
    private AjaxRes editAttachmen(HttpServletRequest request,String json){

        try {
            System.out.println(json);
            kafkaProvider.sendMessage(TopicEnum.EDIT_FILE.getCode(), new Date().getTime(), TopicEnum.EDIT_FILE.getCode(),json);
            return new AjaxRes(Const.SUCCEED, "附件修改保存成功");
            } catch (NumberFormatException e) {
                return new AjaxRes(Const.FAIL, "参数错误！！");
            } catch (UserException e) {
                e.printStackTrace();
                return new AjaxRes(Const.FAIL, "令牌为空！");
            } catch (Exception e) {
                e.printStackTrace();
                return new AjaxRes(Const.FAIL, "附件信息上传失败，出现异常");
            }
        }


}






