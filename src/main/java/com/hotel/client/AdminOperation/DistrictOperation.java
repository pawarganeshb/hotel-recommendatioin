package com.hotel.client.AdminOperation;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.hotel.client.entity.DistrictEntity;
import com.hotel.client.entity.StateEntity;
import com.hotel.client.service.DistrictServiceImpl;
import com.hotel.client.service.IStateServices;
import com.hotel.client.service.StateServices;

public class DistrictOperation {
	static IStateServices iStateServices = new StateServices();
	static DistrictServiceImpl districtService = new DistrictServiceImpl();
	static DistrictEntity de = new DistrictEntity();
	static Scanner sc = new Scanner(System.in);

	public DistrictOperation() {

		int choice = 0;
		do {
			System.out.println("");
			System.out.println("1)Add District");
			System.out.println("2)Show All district");
			System.out.println("3)Update State");
			System.out.println("4)Delete State");
			System.out.println("5)Search State");
			System.out.println("6)Exit");
			System.out.println("Enter your choice");
			choice = sc.nextInt();
			sc.nextLine();
			switch (choice) {
			case 1:
				List<StateEntity> al = new ArrayList<StateEntity>();
				al = iStateServices.getAllStates();
				System.out.println("*****************STATES********************");
				System.out.println("State_id\t State_Name");
				al.forEach((t) -> System.out.println(t.getS_name()));

				System.out.println("Enter the state where you add district");
				String statename = sc.nextLine();
				int stateId = iStateServices.getSatteIdByName(statename);
				if (stateId != 0) {
					System.out.println("Enter the District name");
					String distname = sc.nextLine();
					de.setS_id(stateId);
					de.setDistName(distname);

					int distId = districtService.getDistIdByName(distname);
					de.setDistId(distId);
					if (distId == 0) {
						boolean b = districtService.distAddedByProcedure(de);
						if (b) {
							System.out.println("Data Added Successfully");
						} else {
							System.out.println("somthing is wrong...");
						}
					} else if (districtService.checkDIstrikIsPreset(de)) {
						boolean b = districtService.insertIntoStateDistrict(de);
						if (b) {
							System.out.println("Data Added Successfully");
						} else {
							System.out.println("somthing is wrong...");
						}
					} else {
						System.out.println("Data Is alredy present");
					}
				}

				else {
					System.err.println("You enter the erong state name");
				}
				break;
			case 2:
				showDistrict();
				break;
			case 3:
				updateDistrict();
				break;
			case 4:
				deleteDist();
				break;
			case 5:
				serach();
				break;
			case 6:

				break;
			default:
				System.out.println("Invalid operation.....");
				break;
			}
		} while (choice != 6);

	}

	public static void showDistrict() {
		List<StateEntity> al = new ArrayList<StateEntity>();
		al = iStateServices.getAllStates();
		System.out.println("*****************STATES********************");
		System.out.println("State_id\t State_Name");
		al.forEach((t) -> System.out.println(t.getS_name()));

		System.out.println("Enter the state to see Districts");
		String statename = sc.nextLine();
			int stateId = iStateServices.getSatteIdByName(statename);
			if (stateId!=0) {
			List<DistrictEntity> dist = new ArrayList<DistrictEntity>();
			dist = districtService.showAllDistrcitWhitState(stateId);
			System.out.println("*****************District********************");
			System.out.println("District_id\t District_Name");
			dist.forEach((t) -> System.out.println(t.getDistId() + "\t" + t.getDistName()));
		}
		else {
			System.err.println("you enter wrong state name");
		}

	}

	public static void updateDistrict() {
		List<StateEntity> al = new ArrayList<StateEntity>();
		al = iStateServices.getAllStates();
		System.out.println("*****************STATES********************");
		System.out.println("State_id\t State_Name");
		al.forEach((t) -> System.out.println(t.getS_name()));

		System.out.println("Enter the state to see Districts");
		String statename = sc.nextLine();
		int stateId = iStateServices.getSatteIdByName(statename);
		if (stateId!=0) {
			List<DistrictEntity> dist = new ArrayList<DistrictEntity>();
			dist = districtService.showAllDistrcitWhitState(stateId);
			System.out.println("*****************District********************");
			System.out.println("District_id\t District_Name");
			dist.forEach((t) -> System.out.println(t.getDistId() + "\t\t" + t.getDistName()));
			System.out.println("Enter the Ditrict name  to change to new District");
			String olddist = sc.nextLine();
			int distId = districtService.getDistIdByName(olddist);
			if (distId != 0) {
				System.out.println("Enter the new DIstrict name");
				String newdist = sc.nextLine();
				de.setS_id(stateId);
				de.setDistName(newdist);
				System.out.println(de.getDistName());
				boolean flag = districtService.checkDistrictPrestent(de);
				distId = districtService.getDistIdByName(newdist);
				de.setDistId(distId);
//				System.out.println(distId + "\t" + stateId);
				System.out.println(flag);
				if (flag) {
					flag = districtService.CheckDistricAndStatePresent(de);
					System.out.println(flag);
					if (flag) {
						flag = districtService.insertDataInJoin(de);
						System.out.println(flag);
						if (flag) {
							System.out.println("Data is inserted...");
						} else {
							System.out.println("data is not inserted");
						}
					} else {
						System.err.println("Data is Alredy present");
					}
				} else {
					System.out.println("District is not present in database do you want to add");
					System.out.println("please enter yes or no");
					String msg = sc.nextLine().toLowerCase();
					if (msg.equals("yes")) {
						
						if (districtService.newDistrict(de)) {
							distId = districtService.getDistIdByName(newdist);
							de.setDistId(distId);
							flag = districtService.insertDataInJoin(de);
							System.out.println(flag);
							if (flag) {
								System.out.println("Data is inserted...");
							} else {
								System.out.println("data is not inserted");
							}
						} else {
							System.out.println("data is not inserted");
						}
					}
					else {
						System.out.println("Data is not updated");
						System.out.println("data is not found");
					}

				}
			} else {
				System.err.println("you enter wrong district");
			} 
		}
		else {
			System.err.println("state is not found");
		}

	}
	
	
	public static void serach() {
		List<StateEntity> al = new ArrayList<StateEntity>();
		al = iStateServices.getAllStates();
		System.out.println("*****************STATES********************");
		System.out.println("State_id\t State_Name");
		al.forEach((t) -> System.out.println(t.getS_name()));

		System.out.println("Enter the state to see Districts");
		String statename = sc.nextLine();
			int stateId = iStateServices.getSatteIdByName(statename);
			if (stateId!=0) {
			List<DistrictEntity> dist = new ArrayList<DistrictEntity>();
			dist = districtService.showAllDistrcitWhitState(stateId);
			System.out.println("*****************District********************");
			System.out.println("District_id\t District_Name");
			dist.forEach((t) -> System.out.println(t.getDistId() + "\t" + t.getDistName()));
			System.out.println("Enter the district name to find");
			String distName=sc.nextLine();
			de.setDistName(distName);
			de.setS_id(stateId);
			if (districtService.searchDist(de)) {
				System.out.println(distName);
			}
			else {
				System.err.println("district is not found");
			}
			
		}
		else {
			System.err.println("you enter wrong state name");
		}

	}
	
	
	
	public static void deleteDist() {
		List<StateEntity> al = new ArrayList<StateEntity>();
		al = iStateServices.getAllStates();
		System.out.println("*****************STATES********************");
		System.out.println("State_id\t State_Name");
		al.forEach((t) -> System.out.println(t.getS_name()));

		System.out.println("Enter the state to see Districts");
		String statename = sc.nextLine();
			int stateId = iStateServices.getSatteIdByName(statename);
			if (stateId!=0) {
			List<DistrictEntity> dist = new ArrayList<DistrictEntity>();
			dist = districtService.showAllDistrcitWhitState(stateId);
			System.out.println("*****************District********************");
			System.out.println("District_id\t District_Name");
			dist.forEach((t) -> System.out.println(t.getDistId() + "\t" + t.getDistName()));
			System.out.println("enter the district to delete");
			String distName=sc.nextLine();
			de.setDistName(distName);
			de.setS_id(stateId);
			int distId=0;
				distId=districtService.getDistIdByName(distName);
			if (distId!=0) {
				int count=districtService.checkDistCount(de);
				System.out.println(count);
				if (count>1) {
					if (districtService.delteFromJoin(de)) {
						System.out.println("data is delted");
					}
					else {
						System.out.println("somthing is not good");
					}
				}
				else {
					if (districtService.deleteFromTables(de)) {
						System.out.println("data is delted");
					}
					else {
						System.out.println("somthing is not good");
					}
				}
			}
			else {
				System.err.println("district is not found");
			}
			
		}
		else {
			System.err.println("you enter wrong state name");
		}
	}

}
