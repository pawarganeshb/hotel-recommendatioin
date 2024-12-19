package com.hotel.client.AdminOperation;

import java.util.Scanner;

import com.hotel.client.entity.CityEntity;
import com.hotel.client.service.CityServiceImple;
import com.hotel.client.service.DistrictServiceImpl;
import com.hotel.client.service.ICityService;
import com.hotel.client.service.IStateServices;
import com.hotel.client.service.StateServices;

public class CityOperation {
	static IStateServices iStateServices = new StateServices();
	static DistrictServiceImpl districtService = new DistrictServiceImpl();
	static Scanner sc = new Scanner(System.in);
	static ICityService cs = new CityServiceImple();
	static CityEntity ce = new CityEntity();

	CityOperation() {

	}
}
