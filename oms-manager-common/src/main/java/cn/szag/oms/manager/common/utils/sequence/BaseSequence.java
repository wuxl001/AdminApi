package cn.szag.oms.manager.common.utils.sequence;
import java.util.Calendar;
import java.util.Date;

import cn.szag.oms.manager.common.dao.manager.SequenceDao;
import cn.szag.oms.manager.common.domain.Sequence;





public abstract class BaseSequence {
	/**
	 * The max key value
	 */
	protected int maxKey = 0;

	/**
	 * The min key value
	 */
	protected int minKey = 0;

	/**
	 * The current key value
	 */
	protected int currentKey = 0;

	/**
	 * The cached key size
	 */
	protected int cacheSize = 0;

	/**
	 * 所有的键值在数据库中的初始化值都为0
	 */
	public BaseSequence() {
	}

	public void initValue(SequenceDao sequenceDao, String seqName) {
		doGetCurrentMaxValue(sequenceDao, seqName);
		minKey = maxKey - cacheSize + 1;
		currentKey = minKey;
	}

	/**
	 * 取值方法，提供键的当前值(Temeplate method)
	 * 
	 * @return 返回当前的键值 @
	 */
	public synchronized int getNextKey(SequenceDao sequenceDao, String seqName) {
		if (currentKey > maxKey) {
			doGetCurrentMaxValue(sequenceDao, seqName);
			minKey = maxKey - cacheSize + 1;
			currentKey = minKey;
		}

		return currentKey++;
	}

	/**
	 * 执行更新查询 1.独立提交,防止事务失败缓存脏数据 2.先更新后查询,防止多个服务器读取相同的数据
	 * 
	 * @param sequenceName
	 *            String @
	 * @return int
	 */
	protected void executeSelectForUpdate(SequenceDao sequenceDao,
			String sequenceName) {
		int nMaxValue = 0;
		int nCacheSize = 0;
		int num = 1;

		try {
//			Map<String, String> map = System.getenv();
//			String computerName = map.get("COMPUTERNAME");

			// 执行查询
			Sequence sequence = sequenceDao.findSequence(sequenceName);
			if (sequence == null) {
				nMaxValue = num;
				nCacheSize = num;

				sequence = new Sequence();
				sequence.setSeqname(sequenceName);
				sequence.setCachesize(nCacheSize);
				sequence.setSeqvalue(nMaxValue);
				sequence.setLastupdatetime(new Date());
				sequenceDao.insert(sequence);
			} else {
				// 执行更新
				Date date2 = new Date();
				Date date1 = sequence.getLastupdatetime();
				boolean bol = this.isSameDate(date1, date2);

				sequence.setLastupdatetime(new Date());
				if (!bol) {
					sequence.setSeqvalue(num);
					sequenceDao.updateSeq(sequence);
				} else {
					sequenceDao.update(sequence);
				}

				// 执行查询
				Sequence s = sequenceDao.findSequence(sequenceName);
				if (s != null) {
					nMaxValue = s.getSeqvalue();
					nCacheSize = s.getCachesize();
				}
			}

			maxKey = nMaxValue;
			cacheSize = nCacheSize;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 从数据库提取键的当前值(primitive method)
	 * 
	 * @return 数据库键值更新后的值(CurrentValue + CachedSize) @
	 */
	protected abstract void doGetCurrentMaxValue(SequenceDao sequenceDao,
			String seqName);
	
	
	/**
	 * 判断日期是否为同一天
	 * @param date1
	 * @param date2
	 * @return
	 */
	public boolean isSameDate(Date date1, Date date2) {
		Calendar cal1 = Calendar.getInstance();
		cal1.setTime(date1);
		Calendar cal2 = Calendar.getInstance();
		cal2.setTime(date2);
		boolean isSameYear = cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR);
		boolean isSameMonth = isSameYear
				&& cal1.get(Calendar.MONTH) == cal2.get(Calendar.MONTH);
		boolean isSameDate = isSameMonth
				&& cal1.get(Calendar.DAY_OF_MONTH) == cal2
						.get(Calendar.DAY_OF_MONTH);
		return isSameDate;
	}

	
}