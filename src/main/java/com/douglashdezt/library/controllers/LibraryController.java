package com.douglashdezt.library.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.douglashdezt.library.models.entities.Movie;
import com.douglashdezt.library.services.MovieService;

@RestController
@RequestMapping("library/")
public class LibraryController {
	
	@Autowired
	private MovieService movieService;
	
	@GetMapping("/movies")
	public ResponseEntity<List<Movie>> findAllMovies() {
		
		try {
			List<Movie> books = movieService.findAll();
			return new ResponseEntity<>(
					books,
					HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(
					null,
					HttpStatus.INTERNAL_SERVER_ERROR
					
			);
		}
	}
	
	
	@GetMapping("/book/{code}")
	public ResponseEntity<Movie> getBookByIsbn(@PathVariable("code") String code) {
		
		try {
			Movie foundBook = movieService.findOneByCode(code);
			
			if (foundBook == null) {
				return new ResponseEntity<>(
						null, HttpStatus.NOT_FOUND);
			} else {
				return new ResponseEntity<>(
						foundBook,
						HttpStatus.OK);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(
					null,
					HttpStatus.INTERNAL_SERVER_ERROR
					
			);
		}
		
	}
	
}
