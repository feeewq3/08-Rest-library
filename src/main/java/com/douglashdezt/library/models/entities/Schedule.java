package com.douglashdezt.library.models.entities;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity(name = "schedule")
public class Schedule {

	@Id
	@Column(name = "id")
	@SequenceGenerator(name = "schedule_id_gen", sequenceName = "schedule_id_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "schedule_id_gen")
	private Long id;
	
	@Column(name = "timestamp")
	private Timestamp timestamp;
	
	@Column(name = "price")
	private Double price;
	
	@Column(name = "capacity")
	private Integer capacity;
	
	@OneToMany(mappedBy = "booking", fetch = FetchType.LAZY, cascade = { CascadeType.MERGE })
	@JsonIgnore
	private List<Booking> bookings;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_movie", nullable = true)
	private Movie movie;
 
	public Schedule(Long id, Timestamp timestamp, Double price, Integer capacity) {
		super();
		this.id = id;
		this.timestamp = timestamp;
		this.price = price;
		this.capacity = capacity;
		
	}	
	
	public Schedule() {
		super();
	}
	
	

	public Long getId() {
		return id;
	}

	public Timestamp getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Integer getCapacity() {
		return capacity;
	}

	public void setCapacity(Integer capacity) {
		this.capacity = capacity;
	}

	public Movie getMovie() {
		return movie;
	}

	public void setMovie(Movie movie) {
		this.movie = movie;
	}

	public List<Booking> getBookings() {
		return bookings;
	}

	public void setBookings(List<Booking> bookings) {
		this.bookings = bookings;
	}
	
	
}
