package cn.szag.oms.manager.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.szag.oms.manager.common.ajax.AjaxRes;
import cn.szag.oms.manager.common.domain.manager.AutomaticOrder;
import cn.szag.oms.manager.common.domain.manager.OrderImport;
import cn.szag.oms.manager.common.domain.manager.User;
import cn.szag.oms.manager.common.page.Page;

/**
 * 自助下单功能
 */
public interface AutomaticOrderService {
	int deleteByPrimaryKey(String id);

	int insert(OrderImport record);

	int insertSelective(OrderImport record);

	OrderImport selectByPrimaryKey(String containerId,String id);

	int updateByPrimaryKeySelective(OrderImport record);

	int updateByPrimaryKeyWithBLOBs(OrderImport record);

	int updateByPrimaryKey(OrderImport record);

	// 查询用户的订单是否存在
	OrderImport selectByOrderNO(String orderNo);

	AjaxRes selectList(User user, AutomaticOrder automaticOrder, Page page,String parentId);

	int selectCount(User user,AutomaticOrder automaticOrder);
	
	// 查询未审核通知时间大于24小时的订单
	Page findlistBy24(User user, AutomaticOrder automaticOrder, Page page);

	AjaxRes orderTrackingList(User user, AutomaticOrder automaticOrder, Page page);

	
	AjaxRes standardImportList(User user, AutomaticOrder automaticOrder, Page page);
	
	
    String generateOrderNo(AutomaticOrder orderImport);
    
    int findByExtractOrderNum(String  extractOrderNum,String id);
    
    OrderImport findcustomerId(String companyId,String origin,String inport);
    
    List<String> selectAll(AutomaticOrder automaticOrder,User user);
    
    
    List<OrderImport> selectAll2(AutomaticOrder automaticOrder,User user);
    
    
	
	

}
