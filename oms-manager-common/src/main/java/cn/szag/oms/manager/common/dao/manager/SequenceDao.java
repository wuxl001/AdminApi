package cn.szag.oms.manager.common.dao.manager;

import org.apache.ibatis.annotations.Param;

import cn.szag.oms.manager.common.domain.Sequence;


public interface SequenceDao extends BaseDao<Sequence> {
	Sequence findSequence(@Param("seqname") String seqName);

	void updateSeq(Sequence sequence);
}