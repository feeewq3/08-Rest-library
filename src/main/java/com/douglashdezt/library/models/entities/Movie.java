package com.douglashdezt.library.models.entities;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity(name = "movie")
public class Movie {
	@Id
	@Column(name = "code")
	private String code;
	
	@Column(name = "title")
	private String title;
	
	@Column(name = "length")
	private Integer length;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_category", nullable = true)
	@JsonIgnore
	private Category category;
	
	@OneToMany(mappedBy = "movie", fetch = FetchType.LAZY, cascade = { CascadeType.MERGE })
	@JsonIgnore
	private List<Schedule> schedules;

	public Movie(String code, String title, Integer length) {
		super();
		this.code = code;
		this.title = title;
		this.length = length;
		
		
	}

	public Movie() {
		super();
	}


	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Integer getLength() {
		return length;
	}

	public void setLength(Integer length) {
		this.length = length;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public List<Schedule> getSchedule() {
		return schedules;
	}

	public void setSchedule(List<Schedule> schedule) {
		this.schedules = schedule;
	}
	
	
}
