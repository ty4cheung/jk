package jk.pagination;

import java.util.List;

public class Page<T> {
	private int pageNo = 1;
	  private int pageSize = 10;
	  private int totalRecord;
	  private int totalPage;
	  private List<T> results;
	public int getPageNo() {
		return pageNo;
	}
	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
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
	}
	public int getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	public List<T> getResults() {
		return results;
	}
	public void setResults(List<T> results) {
		this.results = results;
	}
	public String pageLinks(String url){
		int endPage = this.totalPage / this.pageSize +1;	
		StringBuffer sBuf = new StringBuffer();
		sBuf.append("<input type=\"hidden\" name=\"pageNo\" value=\"").append(this.pageNo).append("\">");
		sBuf.append("<span class=\"noprint\" style=\"padding:5px;\">");
		 sBuf.append("&nbsp;第").append(this.pageNo).append("页 / 共").append(endPage).append("页&nbsp;");
		    sBuf.append("&nbsp;总共").append(this.totalRecord).append("条记录 每页").append(this.pageSize).append("条记录&nbsp;");

		    sBuf.append("<a href=\"").append(url).append("?pageNo=1");
		    sBuf.append("\">[首页]");
		    sBuf.append("</a>&nbsp;");

		    sBuf.append("<a href=\"").append(url).append("?pageNo=");
		    if (this.pageNo <= 1)
		      sBuf.append(1);
		    else {
		      sBuf.append(this.pageNo - 1);
		    }
		    sBuf.append("\">[上一页]");
		    sBuf.append("</a>&nbsp;");

		    sBuf.append("<a href=\"").append(url).append("?pageNo=");
		    if (this.pageNo >= endPage)
		      sBuf.append(endPage);
		    else {
		      sBuf.append(this.pageNo + 1);
		    }
		    sBuf.append("\">[下一页]");
		    sBuf.append("</a>&nbsp;");

		    sBuf.append("<a href=\"").append(url).append("?pageNo=").append(endPage);
		    sBuf.append("\">[末页]");
		    sBuf.append("</a>&nbsp;");

		    sBuf.append("</span>");

		return sBuf.toString();
	}
	  
}
