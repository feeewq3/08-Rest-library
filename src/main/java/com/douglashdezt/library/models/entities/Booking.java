package com.douglashdezt.library.models.entities;

import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

@Entity(name = "booking")
public class Booking {
	
	@Id
	@Column(name = "id")
	@SequenceGenerator(name = "booking_id_gen", sequenceName = "booking_id_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "booking_id_gen")
	private Long id;
	
	@Column(name = "timestamp")
	private Timestamp timestamp;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_user", nullable = true)
	private User user;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_schedule", nullable = true)
	private Schedule schedule;
	
	public Booking(Long id, Timestamp timestamp) {
		super();
		this.id = id;
		this.timestamp = timestamp;
		
	}
	
	public Booking() {
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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Schedule getSchedule() {
		return schedule;
	}

	public void setSchedule(Schedule schedule) {
		this.schedule = schedule;
	}
	
	
	
}
