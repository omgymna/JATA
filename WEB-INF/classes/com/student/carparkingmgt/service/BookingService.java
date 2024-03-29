package com.student.carparkingmgt.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.student.carparkingmgt.dao.BookingDAO;
import com.student.carparkingmgt.dao.SlotDAO;
import com.student.carparkingmgt.dto.BookingDTO;
import com.student.carparkingmgt.dto.ParkingDTO;
import com.student.carparkingmgt.dto.SlotDTO;


@Service
public class BookingService {
	
	
	@Autowired
	public BookingDAO dao;

	@Autowired
	public SlotDAO slotDAO;

	public BookingDTO Add(BookingDTO dto) {
	    System.out.println("Requested Slot Number : "+dto.getSlot()+ " Requested Slot Id: "+dto.getSlotId());
	    System.out.println("DTO before Save: "+dto);
	    
	    BookingDTO booking = dao.save(dto);	   
	    
	    System.out.println("Booked Slot id: "+booking.getSlotId());
	    System.out.println("Booking After Save: "+booking);
	    
	    SlotDTO slot = slotDAO.findById(booking.getSlotId());
	    slot.setStatus(false);
	    slotDAO.saveAndFlush(slot);
       return  booking;
	}
	
	public List<BookingDTO> list(){
		List<BookingDTO> dto = dao.findAll();
		return dto;
	}
	
	public List<BookingDTO> findBookingByEmail(String email){
		List<BookingDTO> dto = dao.findByEmail(email);
		return dto;
	}
	
	public SlotDTO findSlotById(long id){
		SlotDTO dto = slotDAO.findById(id);
		return dto;
	}

}
