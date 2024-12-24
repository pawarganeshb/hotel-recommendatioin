package com.hotel.client.service;

import java.util.List;

import com.hotel.client.entity.CityEntity;
import com.hotel.client.entity.HotelEntity;
import com.hotel.client.repo.HotelRepoImpl;
import com.hotel.client.repo.IHotelRepo;

public class HotelServiceImpl implements IHotelService {
	IHotelRepo hs=new  HotelRepoImpl();
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
	@Override
	public List<HotelEntity> showAllHotel(int l_id) {
		// TODO Auto-generated method stub
		return hs.showAllHotel(l_id);
	}

}
