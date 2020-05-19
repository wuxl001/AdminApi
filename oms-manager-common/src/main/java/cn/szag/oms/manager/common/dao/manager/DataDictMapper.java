package cn.szag.oms.manager.common.dao.manager;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.szag.oms.manager.common.domain.manager.DataDict;
import cn.szag.oms.manager.common.page.Page;


public interface DataDictMapper {
	  int insert(DataDict record);
	    
	    // 添加字典
	    int insertSelective(DataDict record);

	    DataDict selectByPrimaryKey(String id);

	    // 删除接口（父项，单条记录，逻辑删除）
	    int updateByPrimaryKeySelective(DataDict record);

	    int updateByPrimaryKey(DataDict record);
	    
	    // 列表接口（当前页 pageNum，pageSize 分页大小，字典名称 keyword）
	    List<DataDict> findDataDictByPage(Page page,@Param("dd")DataDict record);
	    
	    // 查看详情接口
	    List<DataDict> findByParentId(String parentId);
	    
	    // 统计父项下子项字典
	    int childBatchCount(List<DataDict> list);
	    
	    // 根据父 id 统计有多少子项
	    int childCount(String resId);
	    
	    // 根据 id 查询
	    DataDict findDataDictById(String id);
	    
	    // 删除父项（多条记录，逻辑删除）
	    void delBatch(List<DataDict> list);
	    
	    // 删除子项（根据 parentId，逻辑删除）
	    void delByParentId(@Param("dd")DataDict dataDict);
	    
	    // 根据 name 查询（目前用于：套餐创建 - 新增 - 下拉框）
	    List<DataDict> selectByName(@Param("name")String name);
	    
	    //查询列表
	    List<DataDict> findByPage(@Param("dd")DataDict data,@Param("page")Page page);
	    
	    // 查询 delFlag 为 0 的记录数
	  	int countByDelFlag();
	  	
	  	// 判断新增的字典名称是否重复
	  	List<DataDict> selectByNameAndParentId(@Param("dd")DataDict data);
}
