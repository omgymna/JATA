package com.student.carparkingmgt.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.student.carparkingmgt.dto.ParkingDTO;


public interface ParkingDAO extends JpaRepository<ParkingDTO, Long>{

	public ParkingDTO findByParkingName(String parkingName);
	public ParkingDTO findById(long id);
	public ParkingDTO findTopByOrderByIdDesc();
}
