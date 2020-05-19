package cn.szag.oms.manager.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.szag.oms.manager.common.domain.manager.Department;
import cn.szag.oms.manager.common.page.Page;

/** 部门
* @ClassName: DepartmentService 
* @Description: TODO
* @author dengyanghao
* @date 2019年9月25日 下午4:12:00  
*/
public interface DepartmentService {
	int deleteByPrimaryKey(String id);

    int insert(Department record);

    int insertSelective(Department record);

    Department selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Department record);

    int updateByPrimaryKeyWithBLOBs(Department record);

    int updateByPrimaryKey(Department record);
    /**
     * 分页查询
    * @Title: findByPage 
    * @Description: TODO 
    * @param @param department
    * @param @param page
    * @param @return
    * @author dengyanghao
    * @return List<Department>
    * @throws
     */
    List<Department> find(Department department,Page page);

}
