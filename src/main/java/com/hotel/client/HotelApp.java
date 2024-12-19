package com.hotel.client;

import java.util.Scanner;

import com.hotel.client.AdminOperation.AccommodationOperation;
import com.hotel.client.AdminOperation.AmminitiesOperatin;
import com.hotel.client.AdminOperation.DistrictOperation;
import com.hotel.client.AdminOperation.StateOperation;
import com.hotel.client.entity.LoginEntity;
import com.hotel.client.entity.StateEntity;
import com.hotel.client.service.ILoginService;
import com.hotel.client.service.IStateServices;
import com.hotel.client.service.LoginServiceImpl;
import com.hotel.client.service.StateServices;


public class HotelApp {
	static int count = 0;
	// entity class object
	static LoginEntity le = new LoginEntity();
	static StateEntity se = new StateEntity();
	// Service object
	static ILoginService iLoginService = new LoginServiceImpl();
	static IStateServices iStateServices = new StateServices();
	static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {

		System.out.println("**************Welcome To Our System***************");
		do {
			int choise = 0;
			System.out.println("\n");
			System.out.println("1)Login as Admin");
			System.out.println("2)Login as User");
			System.out.println("3)Create account for new user");
			System.out.println("4)Exit");
			System.out.println("\nEnter your choice");
			choise = sc.nextInt();
			sc.nextLine();
			switch (choise) {
			case 1:
				System.out.println("Enter your username");
				System.out.println("refrelct the effect");
				String username = sc.nextLine();
				System.out.println("Enter your password");
				String password = sc.nextLine();
				le.setUsername(username);
				le.setPassword(password);
				le = iLoginService.checkType(le);
				if (le.getType().equals("Admin")) {
					System.out.println("**************Welcome " + le.getName().toUpperCase() + "***************");
					System.out.println();
					int choice=0;
					do {
						System.out.println("1)State Operation");
						System.out.println("2)District operation");
						System.out.println("3)City operation");
						System.out.println("4)Accommodation operation");
						System.out.println("5)Aminity Operation");
						System.out.println("6)User operation");
						System.out.println("7)Exit");
						System.out.println("Enter your Choice");
						choice = sc.nextInt();
						switch (choice) {
						case 1:
							new StateOperation();
							System.out.println();
							break;
						case 2:
							new DistrictOperation();
							System.out.println();
							break;
						case 3:
							
							break;
						case 4:
							new AccommodationOperation();
							System.out.println();
							break;
						case 5:
							new AmminitiesOperatin();
							System.out.println();
							break;
						case 6:
							
							break;
						case 7:
							System.out.println("why worikin");
							break;
						default:
							System.out.println("Enter the valid operation...");
							break;
						}
					} while (choice!=7);

				} else {
					System.out.println("User Not Found........");
				}
				break;
			case 2:
				System.out.println("Enter your username");
				
				username = sc.nextLine();
				System.out.println("Enter your password");
				password = sc.nextLine();
				le.setUsername(username);
				le.setPassword(password);
				le = iLoginService.checkType(le);
				if (le.getType().equals("User")) {
					System.out.println(
							"**************Welcome " +  le.getName().toUpperCase() + "***************");
				} else {
					System.out.println("User Not Found........");
				}
				break;
			case 3:
				loginForNewUser();
				break;
			case 4:
				System.out.println("Thank You.... ");
				System.exit(0);
				break;
			default:
				System.out.println("Invalid operation");
				break;
			}
		} while (true);
	}

	public static void loginForNewUser() {
		
		System.out.println("\nEnter the name of user");
		String name = sc.nextLine();
		System.out.println("Enter the email of user");
		String email = sc.nextLine();
		System.out.println("Enter the contact number");
		if (email.toLowerCase().endsWith("@gmail.com")) {
			String conatct = sc.nextLine();
			System.out.println("Enter username");
			if (conatct.length()==10) {
				String username = sc.nextLine();
				System.out.println("Enter the password");
				String password = sc.nextLine();
				System.out.println("Re-Enter the password");
				String rePassword = sc.nextLine();
				if (password.equals(rePassword) && password.length()>4) {
					le.setConatct_no(conatct);
					le.setEmail(email);
					le.setName(name);
					le.setUsername(username);
					le.setPassword(password);
					String msg = iLoginService.isAddNewUser(le) ? "User registration successfuly!"
							: "Unable to registration!";
					System.out.println(msg);
				}
				else {
					System.err.println("Mishmatch the password or length is greater than 4");
				}
			}
			else {
				System.err.println("Enter the valid contact number");
			}
		}else {
			System.err.println("Enter the valid email that end @gmail.com");
		}
			
	}

	
}