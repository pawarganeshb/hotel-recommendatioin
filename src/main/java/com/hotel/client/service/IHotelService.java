package com.hotel.client.service;

import com.hotel.client.entity.CityEntity;
import com.hotel.client.entity.HotelEntity;

public interface IHotelService {

	public int getLocationId(CityEntity ce);
	public boolean insertIntoHotel(HotelEntity he);
}
