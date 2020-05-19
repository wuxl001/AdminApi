package cn.szag.oms.manager.common.page;

import java.util.List;


/**
 * 对分页的基本数据进行封装
 * 
 * @author zhuxiaohai
 * @date 2017-08-08
 */
@SuppressWarnings("rawtypes")
public class Page{
	/**
	 * 
	 */
	private int pageNum = 1;// 页码，默认是第一页
	private int pageSize = 5;// 每页显示的记录数，默认是5
	private int totalRecord;// 总记录数
	private int totalPage;// 总页数
	private List results;// 对应的当前页记录
	
	public Page() {
		super();
	}
	public Page(int pageSize) {
		super();
		this.pageSize = pageSize;
	}
	
	public Page(int pageNum, int pageSize) {
		super();
		this.pageNum = pageNum;
		this.pageSize = pageSize;
	}
	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		
		this.pageNum = pageNum;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getTotalRecord() {
		return totalRecord;
	}

	public void setTotalRecord(int totalRecord) {
		this.totalRecord = totalRecord;
		int totalPage = totalRecord % pageSize == 0 ? totalRecord / pageSize
				: totalRecord / pageSize + 1;
		this.setTotalPage(totalPage);
	}

	public int getTotalPage() {
		return totalPage;
	}

	private void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public List getResults() {
		if (null != results && results.size() == 0) {
			return null;
		}
		return results;
	}

	public void setResults(List results) {
		this.results = results;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Page [pageNum=").append(pageNum).append(", pageSize=")
				.append(pageSize).append(", results=").append(results)
				.append(", totalPage=").append(totalPage)
				.append(", totalRecord=").append(totalRecord).append("]");
		return builder.toString();
	}
}