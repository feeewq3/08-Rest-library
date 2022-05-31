package com.douglashdezt.library.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.douglashdezt.library.models.entities.Movie;

public interface MovieRepository extends JpaRepository<Movie, String>{
	
 
}
