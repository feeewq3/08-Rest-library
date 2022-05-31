package com.douglashdezt.library.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.douglashdezt.library.models.entities.Movie;
import com.douglashdezt.library.models.entities.Schedule;
import com.douglashdezt.library.models.entities.Booking;
import com.douglashdezt.library.models.entities.User;

public interface BookingRepository extends JpaRepository<Booking, Long>{
	List<Booking> findByUserOrderByTimestampDesc(User user);
	List<Booking> findByScheduleOrderByTimestampDesc(Schedule schedule);
	List<Booking> findByScheduleAndUserOrderByTimestampDesc(Schedule schedule, User user);
}
