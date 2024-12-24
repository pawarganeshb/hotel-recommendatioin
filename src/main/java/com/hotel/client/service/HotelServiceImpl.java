package com.hotel.client.service;

import com.hotel.client.entity.CityEntity;
import com.hotel.client.entity.HotelEntity;

public class HotelServiceImpl implements IHotelService {
	IHotelService hs=new HotelServiceImpl();
	@Override
	public int getLocationId(CityEntity ce) {
		// TODO Auto-generated method stub
		return hs.getLocationId(ce);
	}
	@Override
	public boolean insertIntoHotel(HotelEntity he) {
		// TODO Auto-generated method stub
		return hs.insertIntoHotel(he);
	}
	@Override
	public int hotelId() {
		// TODO Auto-generated method stub
		return hs.hotelId();
	}
	@Override
	public boolean insertIntoAminityJoin(int hid, int amid) {
		// TODO Auto-generated method stub
		return hs.insertIntoAminityJoin(hid, amid);
	}

}
