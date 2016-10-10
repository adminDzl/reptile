package com.reptile.common.logic.service.movie;

import java.util.List;

import com.reptile.common.logic.entity.TMovieData;

/***
 * *
 * 类名称：		UserEventServiceI.java 
 * 类描述：   		
 * 创建人：		
 * 创建时间：		2016-8-4上午10:34:43 
 * 修改人：		liuxing
 * 修改时间：		2016-8-4上午10:34:43 
 * 修改备注：   		
 * @version
 */
public interface MovieServiceI  {

	List<TMovieData> findAllMovie() ;
	
}
