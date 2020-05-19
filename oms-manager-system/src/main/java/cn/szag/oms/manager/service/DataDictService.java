package cn.szag.oms.manager.service;

import java.util.List;

import cn.szag.oms.manager.common.domain.manager.DataDict;
import cn.szag.oms.manager.common.page.Page;


public interface DataDictService {
	
	// 列表接口（当前页 pageNum，pageSize 分页大小，字典名称 keyword）
	public List<DataDict> findDataDictByPage(Page page,DataDict record);
	
	// 添加字典
	public void insert(DataDict dataDict);
	
	// 查看详情接口
	public List<DataDict> findByParentId(String parentId);
	
	// 修改接口
	public void updateDataDict(DataDict dataDict);
	
	// 删除接口
	public void del(DataDict dataDict);
	
	// 根据 id 查询
	public DataDict findDataDictById(String id);
	
	// 删除子项（根据 parentId，逻辑删除）
	public void delByParentId(DataDict dataDict);
	
	// 根据 name 查询（目前用于：套餐创建 - 新增 - 下拉框）
	public List<DataDict> selectByName(String name);
	
	// 查询 delFlag 为 0 的记录数
	int countByDelFlag();
	
	// 判断新增的字典名称是否重复
	List<DataDict> selectByNameAndParentId(DataDict data);
	
}