package cn.szag.oms.manager.common.dao.manager;

import cn.szag.oms.manager.common.domain.manager.Attention;
import cn.szag.oms.manager.common.domain.manager.AutomaticOrder;
import cn.szag.oms.manager.common.domain.manager.OrderImport;
import cn.szag.oms.manager.common.domain.manager.User;
import cn.szag.oms.manager.common.page.Page;
import org.apache.ibatis.annotations.Param;
import org.apache.kafka.common.protocol.types.Field.Str;

import java.util.List;

/**
 * 自助下单功能
 */
public interface AutomaticOrderMapper {

	int deleteByPrimaryKey(String id);

	int insert(OrderImport record);

	int insertSelective(OrderImport record);

	OrderImport selectByPrimaryKey(@Param("containerId") String containerId, @Param("id") String id);

	int updateByPrimaryKeySelective(OrderImport record);

	int updateByPrimaryKeyWithBLOBs(OrderImport record);

	int updateByPrimaryKey(OrderImport record);

	// 查询所有的订单的订单号
	int findAll();

	// 通过提单号查询订单是否存在
	OrderImport selectByOrderNO(String extractOrderNum);

	List<OrderImport> selectList(@Param("user") User user, @Param("automaticOrder") AutomaticOrder automaticOrder,
			@Param("page") Page page);

	int selectCount(@Param("user") User user, @Param("automaticOrder") AutomaticOrder automaticOrder);

	// 查询订单通知时间大于24小时的订单
	List<OrderImport> findlistBy24(@Param("user") User user, @Param("automaticOrder") AutomaticOrder automaticOrder,
			@Param("page") Page page);

	// 订单管理进口单查询
	List<OrderImport> orderTrackingList(@Param("user") User user,
			@Param("automaticOrder") AutomaticOrder automaticOrder, @Param("page") Page page);

	// 订单管理进口单查询
	List<Attention> standardImportList(@Param("user") User user,
			@Param("automaticOrder") AutomaticOrder automaticOrder, @Param("page") Page page);

	OrderImport findOrderNo(@Param("orderNo") String orderNo);

	OrderImport findcustomerId(@Param("companyId") String companyId, @Param("origin") String origin,
			@Param("inport") String inport);

	int findByExtractOrderNum(@Param("extractOrderNum") String extractOrderNum, @Param("id") String id);
	
	
	List<String> selectAll(@Param("automaticOrder")AutomaticOrder automaticOrder,@Param("user") User user);
	
	List<OrderImport> selectAll2(@Param("automaticOrder")AutomaticOrder automaticOrder,@Param("user") User user);

}
