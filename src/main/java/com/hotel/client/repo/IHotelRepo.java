package com.hotel.client.repo;

import com.hotel.client.entity.CityEntity;
import com.hotel.client.entity.HotelEntity;

public interface IHotelRepo {
	
	public int getLocationId(CityEntity ce);
	public boolean insertIntoHotel(HotelEntity he);
	public int hotelId();
	public boolean insertIntoAminityJoin(int hid,int amid);
	
}
