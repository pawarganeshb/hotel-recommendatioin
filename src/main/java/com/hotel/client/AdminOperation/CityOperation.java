package com.hotel.client.AdminOperation;

import java.util.Scanner;

import com.hotel.client.entity.CityEntity;
import com.hotel.client.service.CityServiceImple;
import com.hotel.client.service.ICityService;

public class CityOperation {
	static Scanner sc=new Scanner(System.in);
	static ICityService cs=new CityServiceImple();
	static CityEntity ce=new CityEntity();
	CityOperation(){
		
	}
}