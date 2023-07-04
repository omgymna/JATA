package com.student.carparkingmgt.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.student.carparkingmgt.dto.SlotDTO;

public interface SlotDAO extends JpaRepository<SlotDTO, Long>{

	public List<SlotDTO> findByParkingId(long id);
	public List<SlotDTO> findByParkingIdAndStatus(long id, boolean status);
	public SlotDTO findById(long id);
	
	
}
