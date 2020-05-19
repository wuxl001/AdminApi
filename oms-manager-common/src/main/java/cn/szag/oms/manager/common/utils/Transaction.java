package cn.szag.oms.manager.common.utils;

import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.transaction.support.DefaultTransactionDefinition;

public class Transaction {
	private static TransactionStatus gain(PlatformTransactionManager transactionManager){
		DefaultTransactionDefinition defaultTransactionDefinition = new DefaultTransactionDefinition();
		defaultTransactionDefinition.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
		TransactionStatus status = transactionManager.getTransaction(defaultTransactionDefinition);
		return status;
	}
	public static void commit(PlatformTransactionManager transactionManager){
		transactionManager.commit(gain(transactionManager));
	}
	public static void rollback(PlatformTransactionManager transactionManager){
		transactionManager.rollback(gain(transactionManager));
		TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
	}
}
