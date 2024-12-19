package com.hotel.client.AdminOperation;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.hotel.client.entity.CityEntity;
import com.hotel.client.entity.DistrictEntity;
import com.hotel.client.entity.StateEntity;
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

	public CityOperation() {
		int choice=0;
		do {
			System.out.println("");
			System.out.println("1)Add city");
			System.out.println("2)Show All city");
			System.out.println("3)Update city");
			System.out.println("4)Delete city");
			System.out.println("5)Search city");
			System.out.println("6)Exit");
			System.out.println("Enter your choice");
			choice = sc.nextInt();
			sc.nextLine();
			switch (choice) {
			case 1:
				addCity();
				break;
			case 2:
				showCities();
				break;
			case 3:
				
				break;
			case 4:
				
				break;
			case 5:
				
				break;
			case 6:

				break;
			default:
				System.out.println("Invalid operation.....");
				break;
			}
		} while (choice != 6);

	}
	
	public static  void addCity() {
		List<StateEntity> al = new ArrayList<StateEntity>();
		al = iStateServices.getAllStates();
		System.out.println("*****************STATES********************");
		System.out.println("State_id\t State_Name");
		al.forEach((t) -> System.out.println(t.getS_id()+"\t\t"+t.getS_name()));

		System.out.println("Enter the state to see Districts");
		String statename = sc.nextLine();
			int stateId = iStateServices.getSatteIdByName(statename);
			if (stateId!=0) {
			List<DistrictEntity> dist = new ArrayList<DistrictEntity>();
			dist = districtService.showAllDistrcitWhitState(stateId);
			System.out.println("*****************District********************");
			System.out.println("District_id\t District_Name");
			dist.forEach((t) -> System.out.println(t.getDistId() + "\t\t" + t.getDistName()));
			System.out.println("Enter the District name to add city");
			String distName=sc.nextLine();
			int distId=districtService.getDistIdByName(distName);
			if (distId!=0) {
				System.out.println("Enter the city name");
				String cityname=sc.nextLine();
				ce.setCityName(cityname);
				ce.setS_id(stateId);
				ce.setDistId(distId);
				int cityId=cs.getCityIdByName(ce);
				ce.setCityId(cityId);
				if (cityId==0) {
					if (cs.insertDataWithProcedure(ce)) {
						System.out.println("data is filled");
					}else {
						System.out.println("somthing is not workin");
					}
				} 
				else if (cs.checkPrsentOrNot(ce)) {
					if (cs.filledInJoin(ce)) {
						System.out.println("data is filled");
					}else {
						System.out.println("somthing is not workin");
					}
						
				}else {
					System.out.println("alredy prsent");
				}
				
				
				
			} else {
				System.out.println("District is not found");
			}
		}
		else {
			System.err.println("you enter wrong state name");
		}

	}
	
	
	public static void showCities() {
		List<StateEntity> al = new ArrayList<StateEntity>();
		al = iStateServices.getAllStates();
		System.out.println("*****************STATES********************");
		System.out.println("State_id\t State_Name");
		al.forEach((t) -> System.out.println(t.getS_id()+"\t\t"+t.getS_name()));

		System.out.println("Enter the state to see Districts");
		String statename = sc.nextLine();
			int stateId = iStateServices.getSatteIdByName(statename);
			if (stateId!=0) {
			List<DistrictEntity> dist = new ArrayList<DistrictEntity>();
			dist = districtService.showAllDistrcitWhitState(stateId);
			System.out.println("*****************District********************");
			System.out.println("District_id\t District_Name");
			dist.forEach((t) -> System.out.println(t.getDistId() + "\t\t" + t.getDistName()));
			System.out.println("Enter the District to see cities");
			String distName=sc.nextLine();
			int distId=districtService.getDistIdByName(distName);
			if (distId!=0) {
				ce.setS_id(stateId);
				ce.setDistId(distId);
				
				List<CityEntity> cities=new ArrayList<CityEntity>();
				cities=cs.showAllCities(ce);
				if (cities!=null) {
					System.out.println("***************Cities****************");
					System.out.println("city_id \t city_Name");
					cities.forEach((t)->System.out.println(t.getCityId()+"\t\t"+t.getCityName()));
				} else {
					System.out.println("assoicate district have no cities");
				}
			} else {
				System.out.println("District is not found");
			}
		}
		else {
			System.err.println("you enter wrong state name");
		}
	}
}
