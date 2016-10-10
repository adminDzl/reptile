package com.reptile.common.logic.dao;

import java.util.List;

import com.reptile.common.logic.entity.TMovieData;

public interface TMovieDataMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TMovieData record);

    int insertSelective(TMovieData record);

    TMovieData selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TMovieData record);

    int updateByPrimaryKey(TMovieData record);
    
    
    //业务代码-----------------------------------------------
    List<TMovieData> findAllMovie() ;
    
    //业务代码-----------------------------------------------
	
}