package com.hotel.client.repo;

import com.hotel.client.entity.CityEntity;
import com.hotel.client.entity.HotelEntity;

public interface IHotelRepo {
	
	public int getLocationId(CityEntity ce);
	public boolean insertIntoHotel(HotelEntity he);
	
}
