package com.douglashdezt.library.models.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity(name = "category")
public class Category {

	@Id
	@Column(name = "code")
	private String code;
	
	@Column(name  = "name")
	private String name;
	
	@OneToMany(mappedBy = "category", fetch = FetchType.LAZY, cascade = { CascadeType.MERGE })
	@JsonIgnore
	private List<Movie> movies;

	public Category(String code, String name) {
		super();
		this.code = code;
		this.name = name;
	}

	public Category() {
		super();
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Movie> getMovies() {
		return movies;
	}

	public void setMovies(List<Movie> movies) {
		this.movies = movies;
	}
}
