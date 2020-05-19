package cn.szag.oms.manager.common.domain;

import java.io.Serializable;
import java.util.Date;

import org.apache.ibatis.type.Alias;

@SuppressWarnings("serial")
public class Sequence implements Serializable {
	/** 序号代号 **/
	private String seqname;
	/** 当前值 **/
	private Integer seqvalue;
	/** 同步数据库的时间阀值 **/
	private Integer cachesize;

	/** 最后时间 **/
	private Date lastupdatetime;

	public String getSeqname() {
		return seqname;
	}

	public void setSeqname(String seqname) {
		this.seqname = seqname;
	}

	public Integer getSeqvalue() {
		return seqvalue;
	}

	public void setSeqvalue(Integer seqvalue) {
		this.seqvalue = seqvalue;
	}

	public Integer getCachesize() {
		return cachesize;
	}

	public void setCachesize(Integer cachesize) {
		this.cachesize = cachesize;
	}

	public Date getLastupdatetime() {
		return lastupdatetime;
	}

	public void setLastupdatetime(Date lastupdatetime) {
		this.lastupdatetime = lastupdatetime;
	}
	
	

}
