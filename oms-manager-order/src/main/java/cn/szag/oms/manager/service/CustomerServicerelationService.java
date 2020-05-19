package cn.szag.oms.manager.service;

import cn.szag.oms.manager.common.domain.manager.CustomerServicerelation;

public interface CustomerServicerelationService {

	int findCusomerIs(String id);
	
	int insert(CustomerServicerelation ss);
	
	int findCount();
}
