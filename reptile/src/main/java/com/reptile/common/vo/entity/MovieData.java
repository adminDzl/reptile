package com.reptile.common.vo.entity;


/****
 * *
 * 类名称：		MovieData.java 
 * 类描述：   		爬虫抓取数据实体类
 * 创建人：		
 * 创建时间：		2016-8-30上午11:28:07 
 * 修改人：		liuxing
 * 修改时间：		2016-8-30上午11:28:07 
 * 修改备注：   		
 * @version
 */
//@Entity
//@Table(name = "t_movie_data")
//public class MovieData  extends IdEntity{	
public class MovieData {	
	
private static final long serialVersionUID = 1L;
	
	private String movieName ;		// 电影名称
	private Integer movieNation ;	// 电影发行区域
	private String movieActor; 		// 电影主要参影演员
	private Double douBanVal ;		// 豆瓣评分
	private String movieImgUrl;		// 影片头像地址
	private String movieSourceUrl;	// 影片抓取来源地址
	private String movieDownLoadSourceUrl;	// 影片下载地址
	
	//@Column(columnDefinition="varchar(80) COMMENT '电影名称'")
	public String getMovieName() {
		return movieName;
	}
	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}
	
	//@Column(columnDefinition="varchar(40) COMMENT '电影出品国家'")
	public Integer getMovieNation() {
		return movieNation;
	}
	public void setMovieNation(Integer movieNation) {
		this.movieNation = movieNation;
	}
	
	//@Column(columnDefinition="varchar(60) COMMENT '电影主要参影演员'")
	public String getMovieActor() {
		return movieActor;
	}
	public void setMovieActor(String movieActor) {
		this.movieActor = movieActor;
	}
	
	//@Column(columnDefinition="double(2,1) COMMENT '豆瓣评分: 0< 评分 <10'")
	public Double getDouBanVal() {
		return douBanVal;
	}
	public void setDouBanVal(Double douBanVal) {
		this.douBanVal = douBanVal;
	}
	
	//@Column(columnDefinition="varchar(200) COMMENT '影片头像地址'")
	public String getMovieImgUrl() {
		return movieImgUrl;
	}
	public void setMovieImgUrl(String movieImgUrl) {
		this.movieImgUrl = movieImgUrl;
	}
	
	//@Column(columnDefinition="varchar(200) COMMENT '影片抓取来源地址'")
	public String getMovieSourceUrl() {
		return movieSourceUrl;
	}
	public void setMovieSourceUrl(String movieSourceUrl) {
		this.movieSourceUrl = movieSourceUrl;
	}
	
	//@Column(columnDefinition="varchar(200) COMMENT '影片下载地址'")
	public String getMovieDownLoadSourceUrl() {
		return movieDownLoadSourceUrl;
	}
	public void setMovieDownLoadSourceUrl(String movieDownLoadSourceUrl) {
		this.movieDownLoadSourceUrl = movieDownLoadSourceUrl;
	}
	
	

}
