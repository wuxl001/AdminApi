package cn.szag.oms.manager.utils;

import cn.szag.oms.manager.ajax.AjaxRes;

@SuppressWarnings("serial")
public class UserException extends RuntimeException {
	private AjaxRes ar;

	public AjaxRes getAr() {
		return ar;
	}

	public void setAr(AjaxRes ar) {
		this.ar = ar;
	}

	public UserException(AjaxRes ar_) {
		this.setAr(ar_);
	}
}
