package com.douglashdezt.library.services;

import java.util.List;

import com.douglashdezt.library.models.entities.Movie;

public interface MovieService {
	List<Movie> findAll();
	Movie findOneByCode(String code);
}
