package com.douglashdezt.library.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.douglashdezt.library.models.entities.Schedule;

public interface ScheduleRepository extends JpaRepository<Schedule, Long>{
	//List<Schedule> findByTimestamp;
}
