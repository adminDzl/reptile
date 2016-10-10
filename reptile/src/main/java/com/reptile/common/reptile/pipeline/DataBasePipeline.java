package com.reptile.common.reptile.pipeline;

import java.util.Date;
import java.util.Map;

import org.springframework.stereotype.Service;

import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

import com.reptile.common.framework.SystemParamCache;
import com.reptile.common.logic.dao.TMovieDataMapper;
import com.reptile.common.logic.entity.TMovieData;

/***
 * *
 * 类名称：		DataBasePipeline.java 
 * 类描述：   		对爬虫爬到的数据进行持久化到数据库
 * 创建人：		
 * 创建时间：		2016-8-26上午11:21:47 
 * 修改人：		liuxing
 * 修改时间：		2016-8-26上午11:21:47 
 * 修改备注：   		
 * @version
 */
@Service("dataBasePipeline")
public class DataBasePipeline implements Pipeline {
	
	private TMovieDataMapper moviemapper ;
	
	/****
	 * Spring mapper注册
	 */
    public DataBasePipeline() {
		super();
		if( moviemapper == null ){
			this.moviemapper = SystemParamCache.applicationContext.getBean( TMovieDataMapper.class ) ;
		}
	}

	@Override
    public void process(ResultItems resultItems, Task task) {
    	//只对有效数据进行处理
    	if( ! resultItems.isSkip() ){
	       /* System.out.println("get page: " + resultItems.getRequest().getUrl());*/
	        for (Map.Entry<String, Object> entry : resultItems.getAll().entrySet()) {
	            //System.out.println(entry.getKey() + ":\t" + entry.getValue());
	        	try {
					if( entry.getKey().equals("豆瓣评分") && Double.parseDouble( entry.getValue().toString() ) > 6 ){
						movieSave(resultItems, task) ;	    
						break ;
					}
				} catch (NumberFormatException e) {
					break ;
				}
	        }
    	}
    }
    
    /***
     * 对单断数据进行封装
     * @param resultItems
     * @param task
     */
    public void movieSave( ResultItems resultItems, Task task ){
    	TMovieData movieData = new TMovieData() ;
    	movieData.setMovieSourceUrl( resultItems.getRequest().getUrl() ) ;
    	for (Map.Entry<String, Object> entry : resultItems.getAll().entrySet()) {
    		if( entry.getKey().equals("电影名称") ){
    			movieData.setMovieName( entry.getValue().toString() ) ;
    			continue ;
    		}
    		if( entry.getKey().equals("电影发行区域") ){
    			movieData.setMovieNation( entry.getValue().toString() ) ;
    			continue ;
    		}
    		if( entry.getKey().equals("电影主要参影演员") ){
    			movieData.setMovieActor( entry.getValue().toString() ) ;
    			continue ;
    		}
    		if( entry.getKey().equals("豆瓣评分") ){
				movieData.setDouBanVal( Double.parseDouble( entry.getValue().toString() ) ) ; 
    			continue ;
    		}
    		if( entry.getKey().equals("影片头像") ){
    			movieData.setMovieImgUrl( entry.getValue().toString() ) ;
    			continue ;
    		}
    		if( entry.getKey().equals("下载链接") ){
    			movieData.setMovieDownLoadSourceUrl(  entry.getValue().toString() ) ;
    			continue ;
    		}
    		if( entry.getKey().equals("上映年份") ){
    			movieData.setReleaseYear( Integer.parseInt( entry.getValue().toString() ) ) ;
    			continue ;
    		}
    		if( entry.getKey().equals("抓取平台") ){
    			movieData.setReleaseYear( Integer.parseInt( entry.getValue().toString() ) ) ;
    			continue ;
    		}
        }
    	//if( movieData.getDouBanVal() != null && movieData.getDouBanVal() > 6 ){
    		movieData.setCreateTime( new Date() ) ;
    		moviemapper.insertSelective(movieData) ;
    	//}
    }
    

}
