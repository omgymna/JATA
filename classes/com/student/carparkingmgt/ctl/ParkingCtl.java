package com.student.carparkingmgt.ctl;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.student.carparkingmgt.dao.SlotDAO;
import com.student.carparkingmgt.dto.ParkingDTO;
import com.student.carparkingmgt.dto.SlotDTO;
import com.student.carparkingmgt.exception.RecordNotFoundException;
import com.student.carparkingmgt.form.ParkingForm;
import com.student.carparkingmgt.service.ParkingService;


@Controller
public class ParkingCtl {
	
	@Autowired
	public ParkingService service;
	
	@Autowired
	public SlotDAO slotDao;
	
	
	@GetMapping("/parking")
	public String parking(@ModelAttribute("form")ParkingForm form, Model model) {
		return "parking";
	}
	
	@PostMapping("/addParking")
	public String Add(@Valid @ModelAttribute("form")ParkingForm form,  BindingResult bindingResult, Model model) {

		System.out.println("form: "+form);
		try {
		if (bindingResult.hasErrors()) {
			System.out.println("bindingResult : "+bindingResult);
			return "parking";
		}else {
			ParkingDTO bean = form.getDTO();
			if(form.getId()>0) {

				service.update(bean);
				model.addAttribute("success", "Parking Updated successfully");
			}else {
				service.Add(bean);
			ParkingDTO parkingDto	= service.findLastParkingId();
			System.out.println("parkingDto ID"+parkingDto.getId());
			    service.creatSlots(parkingDto.getId());
				model.addAttribute("success", "Parking successfully");
			}
			
			return "parking";
		}}catch (RecordNotFoundException e) {
			// TODO: handle exception
			model.addAttribute("error", e.getMessage());
			e.printStackTrace();
			return "parking";
		}
	}
	

	@GetMapping("/parkinglist")
	public String list(@ModelAttribute("form")ParkingForm form, Model model){
	List<ParkingDTO> list = service.list();
	model.addAttribute("list", list);
	return "parkinglist";
		
	}
	
	@GetMapping("/parkingEdit")	
	public String update(@ModelAttribute("form")ParkingForm form, Model model, @RequestParam("id") long id ){
		ParkingDTO bean = service.findParkingById(id);
		form.populate(bean);
		model.addAttribute("bean",bean);	
		return "parking";
	}
	
	@GetMapping("/parkingDelete")	
	public String delete(@ModelAttribute("form")ParkingForm form, Model model, @RequestParam("id") long id ) throws Exception{
		service.delete(id);	
		
		List<ParkingDTO> list =	service.list();
		model.addAttribute("list", list);
		model.addAttribute("success", "Parking Deleted successfully");
		return "parkinglist";
	}
	
	long parkingId = 0;
	@GetMapping("/viewparkingSlot")	
	public String viewSlot(@ModelAttribute("form")ParkingForm form, Model model, @RequestParam("id") long id ) throws Exception{
		parkingId = id;
		List<SlotDTO> slots = slotDao.findByParkingId(id);
        model.addAttribute("list", slots);
		return "viewslots";
	}
	
	@GetMapping("/updateslot")	
	public String updateSlot(@ModelAttribute("form")ParkingForm form, Model model, @RequestParam("id") long id ) throws Exception{
		SlotDTO slot = slotDao.findById(id);
		slot.setStatus(true);
		slotDao.saveAndFlush(slot);
		System.out.println("parkingId: "+parkingId);
		 List<SlotDTO> slots = slotDao.findByParkingId(parkingId);
	     model.addAttribute("list", slots);
		return "viewslots";
	}
	
	
	
	
}
