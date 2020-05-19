package cn.szag.oms.manager.common.utils.sequence;

import cn.szag.oms.manager.common.dao.manager.SequenceDao;

public class Seq extends BaseSequence {
	public Seq() {
	}

	@Override
	protected void doGetCurrentMaxValue(SequenceDao sequenceDao,
			String seqName) {
		executeSelectForUpdate(sequenceDao, seqName);
	}
}
