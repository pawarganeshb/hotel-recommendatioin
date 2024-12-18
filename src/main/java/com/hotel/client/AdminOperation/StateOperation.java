package com.hotel.client.AdminOperation;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.hotel.client.entity.StateEntity;
import com.hotel.client.service.IStateServices;
import com.hotel.client.service.StateServices;

public class StateOperation {
	static Scanner sc=new Scanner(System.in);
	static IStateServices iStateServices = new StateServices();
	static StateEntity se = new StateEntity();
	public StateOperation() {
		
		int choice=0;
		do {
			System.out.println("");
			System.out.println("1)Add State");
			System.out.println("2)Show All State");
			System.out.println("3)Update State");
			System.out.println("4)Delete State");
			System.out.println("5)Search State");
			System.out.println("6)Exit");
			System.out.println("Enter your choice");
			choice = sc.nextInt();
			sc.nextLine();
			switch (choice) {
			case 1:
				System.out.println("Enter the state name");

				String stateName = sc.nextLine();
				se.setS_name(stateName);
				boolean check = iStateServices.addState(se);
				if (check) {
					System.out.println("State is Added Successfully......");
				} else {
					System.out.println("Something is not write....");
				}
				break;
			case 2:
				List<StateEntity> al = new ArrayList<StateEntity>();
				al = iStateServices.getAllStates();
				System.out.println("*****************STATES********************");
				System.out.println("State_id\t State_Name");
				al.forEach((t) -> System.out.println(t.getS_id() + "\t\t" + t.getS_name()));
				break;
			case 3:
				System.out.println("Enter the  State that we want to insert ");
				stateName = sc.nextLine();
				System.out.println("Enter the Id where we want to change");
				int s_id = sc.nextInt();
				se.setS_id(s_id);
				se.setS_name(stateName);
				check = iStateServices.updateState(se);
				if (check) {
					System.out.println("State Upadated");
				} else {
					System.err.println("Id is not found");
				}
				break;
			case 4:
				System.out.println("Enter the state Id for delete");
				s_id = sc.nextInt();
				check = iStateServices.deleteState(s_id);
				if (check) {
					System.out.println("State is deleted successfully...");
				} else {
					System.err.println("Id is not found");
				}
				break;
			case 5:
				System.out.println("Enter the state name that we want to search");
				stateName = sc.nextLine();
				al = new ArrayList<StateEntity>();
				al = iStateServices.searchState(stateName);
				
				if (al.size()!=0) {
					System.out.println("*****************STATES********************");
					System.out.println("State_id\t State_Name");
					al.forEach((t) -> System.out.println(t.getS_id() + "\t\t" + t.getS_name()));
				} else {
					System.out.println("State is not found.....");
				}
				break;
			case 6:
				
				break;
			default:
				System.out.println("Invalid operation.....");
				break;
			}
		} while (choice!=6);

	}
}