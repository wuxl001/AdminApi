package cn.szag.oms.manager.common.dao.manager;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.szag.oms.manager.common.domain.manager.CodeNationality;
import cn.szag.oms.manager.common.page.Page;

public interface CodeNationalityMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CodeNationality record);

    int insertSelective(CodeNationality record);

    CodeNationality selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CodeNationality record);

    int updateByPrimaryKey(CodeNationality record);
    
    List<CodeNationality> selectList(@Param("keyword") String keyword,@Param("page") Page page);
}