package com.student.carparkingmgt.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.student.carparkingmgt.dto.BookingDTO;
import com.student.carparkingmgt.dto.ParkingDTO;

public interface BookingDAO extends JpaRepository<BookingDTO, Long>{
	
	public List<BookingDTO> findByEmail(String email);
	public BookingDTO findById(long id);

}
