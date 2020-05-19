package cn.szag.oms.manager.common.utils.sequence;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.collections.FastHashMap;

import cn.szag.oms.manager.common.dao.manager.SequenceDao;



/**
 * 序列号产生类
 * 
 */
public class SequenceGenerator {
	private static SequenceGenerator seqGenarator = new SequenceGenerator();

	private FastHashMap keyList = new FastHashMap(20);

	private SequenceGenerator() {
		keyList.setFast(true);
	}

	/**
	 * 单例模式，提供自己的实例
	 * 
	 * @return KeyGenerator instance
	 */
	public static SequenceGenerator getInstance() {
		return seqGenarator;
	}

	private static SimpleDateFormat df = new SimpleDateFormat("yyMMdd");

	/**
	 * @param sequenceDao
	 * @param seqName
	 * @param companyCode
	 * @return
	 */
	public String getNextKey(SequenceDao sequenceDao, String seqName, String companyCode) {
		String className = "Seq";
		BaseSequence baseSequence = (BaseSequence) keyList.get(className);

		if (baseSequence == null) {
			synchronized (keyList) {
				baseSequence = (BaseSequence) keyList.get(className);
				if (baseSequence == null) {
					try {
						Class<?> clazz = Loader.loadClass("cn.szag.oms.manager.common.utils.sequence.Seq");
						baseSequence = (BaseSequence) clazz.newInstance();
						baseSequence.initValue(sequenceDao, seqName);
						keyList.put(className, baseSequence);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}

		int index = baseSequence.getNextKey(sequenceDao, seqName);

		// 生成格式
		StringBuilder sb = new StringBuilder();
		sb.append(seqName);
		sb.append(companyCode);
		sb.append(df.format(new Date()));
		if (index / 100 > 0) {
			sb.append(index);
		} else if (index / 10> 0) {
			sb.append("0").append(index);
		} else{
			sb.append("00").append(index);
		} 
		return sb.toString().toUpperCase();
	}
}
