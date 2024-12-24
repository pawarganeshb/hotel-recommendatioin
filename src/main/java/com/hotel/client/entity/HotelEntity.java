package com.hotel.client.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HotelEntity extends AmminitiesEntity{
	private int hid;
	private String hname;
	private String hconatct;
	private String haddress;
	private int hprice;
	private int lid;
	
}
