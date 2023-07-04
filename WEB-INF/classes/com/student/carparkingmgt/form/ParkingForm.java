package com.student.carparkingmgt.form;

import javax.validation.constraints.NotEmpty;

import com.student.carparkingmgt.dto.BaseDTO;
import com.student.carparkingmgt.dto.ParkingDTO;
import com.student.carparkingmgt.dto.UserDTO;
import com.student.carparkingmgt.utility.DataUtility;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ParkingForm extends BaseDTO{
	
	@NotEmpty(message = "Parking name is required")
	private String parkingName;

	@NotEmpty(message = "Address is required")
	private String address;
	
	@NotEmpty(message = "Number of slot is required")
	private String numberOfSlot;
	
	
	public ParkingDTO getDTO() {
		ParkingDTO bean=new ParkingDTO();
		bean.setId(id);
		bean.setParkingName(parkingName);
		bean.setAddress(address);
		bean.setNumberOfSlot(DataUtility.getLong(numberOfSlot));
		return bean;
	}

	public void populate(ParkingDTO bean) {
		id = bean.getId();
		parkingName=bean.getParkingName();
		address=bean.getAddress();
		numberOfSlot = DataUtility.getStringData(bean.getNumberOfSlot());
	}


	

}
