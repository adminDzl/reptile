package com.reptile.common.logic.entity;


public class BaseEntity {
	
    public int rows ;				//当前页要查询行数
    public int page ;				//当前页要查的页数
    public int firstRow ;			//SQL 开始的行数
    public int maxRow ;				//SQl 一次查询的行数
    
    public String queryStartDate ;	//查询的开始时间
    public String queryStopDate ;	//查询的结束时间
        
    public String getQueryStartDate() {
		return queryStartDate;
	}

	public void setQueryStartDate(String queryStartDate) {
		this.queryStartDate = queryStartDate;
	}

	public String getQueryStopDate() {
		return queryStopDate;
	}

	public void setQueryStopDate(String queryStopDate) {
		this.queryStopDate = queryStopDate;
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getFirstRow() {
		if( page>0){
			setFirstRow( (page-1) * rows ) ;
		}
		return firstRow;
	}

	public void setFirstRow(int firstRow) {
		this.firstRow = firstRow;
	}

	public int getMaxRow() {
		if( rows>0){
			setMaxRow(rows) ;
		}
		return maxRow;
	}

	public void setMaxRow(int maxRow) {
		this.maxRow = maxRow;
	}


}
