package com.hotel.client.service;

import java.util.List;

import com.hotel.client.entity.CityEntity;
import com.hotel.client.entity.HotelEntity;

public interface IHotelService {

	public int getLocationId(CityEntity ce);
	public boolean insertIntoHotel(HotelEntity he);
	public int hotelId();
	public boolean insertIntoAminityJoin(int hid,int amid);
	public List<HotelEntity> showAllHotel(int l_id);
	public List<HotelEntity> serchHotel(int l_id,String name);
	public boolean deleteHotel(int hotelId);
}
