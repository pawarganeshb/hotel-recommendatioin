package com.hotel.client.AdminOperation;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.hotel.client.entity.CityEntity;
import com.hotel.client.entity.DistrictEntity;
import com.hotel.client.entity.HotelEntity;
import com.hotel.client.entity.StateEntity;
import com.hotel.client.service.AccommodationServiceImple;
import com.hotel.client.service.AmminitiesServiceImple;
import com.hotel.client.service.CityServiceImple;
import com.hotel.client.service.DistrictServiceImpl;
import com.hotel.client.service.HotelServiceImpl;
import com.hotel.client.service.IAccommodationService;
import com.hotel.client.service.IAmminitiesService;
import com.hotel.client.service.ICityService;
import com.hotel.client.service.IHotelService;
import com.hotel.client.service.IStateServices;
import com.hotel.client.service.StateServices;

public class HotelOperation {
	static IStateServices iStateServices = new StateServices();
	static DistrictServiceImpl districtService = new DistrictServiceImpl();
	static Scanner sc = new Scanner(System.in);
	static HotelEntity he = new HotelEntity();
	static IHotelService hs = new HotelServiceImpl();
	static CityEntity ce = new CityEntity();
	static ICityService cs = new CityServiceImple();
	static IAccommodationService as = new AccommodationServiceImple();
	static IAmminitiesService ams = new AmminitiesServiceImple();

	public HotelOperation() {

		int choice = 0;
		do {
			System.out.println("");
			System.out.println("1)Add Hotel");
			System.out.println("2)Show All Hotel");
			System.out.println("3)Update Hotel");
			System.out.println("4)Delete Hotel");
			System.out.println("5)Search Hotel");
			System.out.println("6)Exit");
			System.out.println("Enter your choice");
			choice = sc.nextInt();
			sc.nextLine();
			switch (choice) {
			case 1:
				addHotel();
				break;
			case 2:
				showHotesl();
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

	private void addHotel() {
		List<StateEntity> al = new ArrayList<StateEntity>();
		al = iStateServices.getAllStates();
		System.out.println("*****************STATES********************");
		System.out.println("State_id\t State_Name");
		al.forEach((t) -> System.out.println(t.getS_id() + "\t\t" + t.getS_name()));

		System.out.println("Enter the state to see Districts");
		String statename = sc.nextLine();
		int stateId = iStateServices.getSatteIdByName(statename);
		if (stateId != 0) {
			List<DistrictEntity> dist = new ArrayList<DistrictEntity>();
			dist = districtService.showAllDistrcitWhitState(stateId);
			if (dist.size() != 0) {
				System.out.println("*****************District********************");
				System.out.println("District_id\t District_Name");
				dist.forEach((t) -> System.out.println(t.getDistId() + "\t\t" + t.getDistName()));
				System.out.println("Enter the District to see cities");
				String distName = sc.nextLine();
				int distId = districtService.getDistIdByName(distName);
				if (distId != 0) {
					ce.setS_id(stateId);
					ce.setDistId(distId);

					List<CityEntity> cities = new ArrayList<CityEntity>();
					cities = cs.showAllCities(ce);
					if (cities.size() != 0) {
						System.out.println("***************Cities****************");
						System.out.println("city_id \t city_Name");
						cities.forEach((t) -> System.out.println(t.getCityId() + "\t\t" + t.getCityName()));

						System.out.println("Eneter city name to add hotel");
						String cityNamw = sc.nextLine();
						ce.setCityName(cityNamw);
						int cityId = cs.getCityIdByName(ce);
						ce.setS_id(stateId);
						ce.setDistId(distId);
						ce.setCityId(cityId);
						if (cityId != 0) {
							int lid = hs.getLocationId(ce);
							System.out.println("Enter the hotel name");
							String hname = sc.nextLine();
							System.out.println("Enetr the hotel Address");
							String hadd = sc.nextLine();
							System.out.println("Enter the contact number of hotel");
							String hconatct = sc.nextLine();
							if (hconatct.length() == 10) {
								System.out.println("Enter the price of hotel");
								int price = sc.nextInt();
								sc.nextLine();
								AccommodationOperation.showAccommodation();
								System.out.println("Enter the Id of  Accommodation");
								int aid = sc.nextInt();
								sc.nextLine();
								if (as.check(aid)) {
									he.setHname(hname);
									he.setHaddress(hadd);
									he.setHconatct(hconatct);
									he.setLid(lid);
									he.setHprice(price);
									he.setAccommodationID(aid);

									if (hs.insertIntoHotel(he)) {
										String msg = "";
										System.out.println("Do you want to add aminity");
										System.out.println("Enter yes or no");
										msg = sc.nextLine().toLowerCase();
										if (msg.equals("yes")) {
											do {
												int hid = hs.hotelId();
												AmminitiesOperatin.showAmminities();
												System.out.println("Eneter the Aminity Id to add");
												int ami = sc.nextInt();
												sc.nextLine();
												if (ams.checkAminity(ami)) {
													if (hs.insertIntoAminityJoin(hid, ami)) {
														System.out.println("Data is filled.....");
													} else {
														System.out.println("failed to insert data");
													}
												} else {
													System.out.println("you enter wrong aminity id");
												}
												System.out.println("do you want to add more animity");
												System.out.println("Enter yes or no");
												msg = sc.nextLine().toLowerCase();
											} while (msg.equals("yes"));
										} else {
											System.out.println("Thank You....");
										}
									} else {
										System.out.println("data in not inserted");
									}
								} else {
									System.out.println("you enter the wrong ID");
								}

							} else {
								System.out.println("you enter the wrong number");
							}
						} else {
							System.out.println("city is not found");
						}
					} else {
						System.out.println("assoicate district have no cities");
					}
				} else {
					System.out.println("District is not found");
				}
			} else {
				System.out.println("associate state have no districts");
			}
		} else {
			System.err.println("you enter wrong state name");
		}
	}
	
	private void showHotesl() {
		
	}
}
