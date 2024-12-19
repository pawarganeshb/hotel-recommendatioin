package com.hotel.client.service;

import java.util.List;

import com.hotel.client.entity.CityEntity;

public interface ICityService {
	public int getCityIdByName(CityEntity ce);
	public boolean insertDataWithProcedure(CityEntity ce);
	public boolean checkPrsentOrNot(CityEntity ce);
	public boolean filledInJoin(CityEntity ce);
	public List<CityEntity> showAllCities(CityEntity ce);
}
