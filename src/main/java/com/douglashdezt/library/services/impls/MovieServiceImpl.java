package com.douglashdezt.library.services.impls;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douglashdezt.library.models.entities.Movie;
import com.douglashdezt.library.repositories.MovieRepository;
import com.douglashdezt.library.services.MovieService;



@Service
public class MovieServiceImpl implements MovieService{
	@Autowired
	private MovieRepository movieRepository;
	
	@Override
	public List<Movie> findAll() {
		return movieRepository.findAll();
	}

	@Override
	public Movie findOneByCode(String code) {
		return movieRepository.findById(code).orElse(null);
	}

}
