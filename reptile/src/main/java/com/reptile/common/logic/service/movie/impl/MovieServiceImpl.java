package com.reptile.common.logic.service.movie.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.reptile.common.logic.dao.TMovieDataMapper;
import com.reptile.common.logic.entity.TMovieData;
import com.reptile.common.logic.service.movie.MovieServiceI;

@Service("MovieService")
public class MovieServiceImpl implements MovieServiceI {
	
	@Resource
	private TMovieDataMapper Moviemapper ;

	@Override
	public List<TMovieData> findAllMovie() {
		// TODO Auto-generated method stub
		return Moviemapper.findAllMovie();
	}

}
