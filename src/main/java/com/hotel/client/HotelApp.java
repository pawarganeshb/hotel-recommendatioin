package com.hotel.client;

import java.util.Scanner;

import com.hotel.client.entity.LoginEntity;
import com.hotel.client.service.ILoginService;
import com.hotel.client.service.LoginServiceImpl;

public class HotelApp {
	static int count = 0;
	// entity class object
	static LoginEntity le = new LoginEntity();
	// Service object
	static ILoginService iLoginService = new LoginServiceImpl();
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
			switch (choise) {
			case 1:
				System.out.println("Enter your username");
				sc.nextLine();
				String username = sc.nextLine();
				System.out.println("Enter your password");
				String password = sc.nextLine();
				le.setUsername(username);
				le.setPassword(password);
				le= iLoginService.checkType(le);
				if (le.getType().equals("Admin")) {
					System.out.println("**************Welcome " + username.toUpperCase() + "***************");
					System.out.println();
					System.out.println("1] Add State");
					System.out.println("2] Update State");
					System.out.println("3] Delete State");
					System.out.println("4] View State");
					System.out.println("5] Exit");

				} else {
					System.out.println("User Not Found........");
				}
				break;
			case 2:
				System.out.println("Enter your username");
				sc.nextLine();
				username = sc.nextLine();
				System.out.println("Enter your password");
				password = sc.nextLine();
				le.setUsername(username);
				le.setPassword(password);
				le= iLoginService.checkType(le);
				if (le.getType().equals("Admin")) {
					System.out.println(
							"**************Welcome " + username.toUpperCase() + le.getName() + "***************");
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
		sc.nextLine();
		System.out.println("\nEnter the name of user");
		String name =sc.nextLine();
		System.out.println("Enter the email of user");
		String email=sc.nextLine();
		System.out.println("Enter the contact number");
		String conatct=sc.nextLine();
		System.out.println("Enter username");
		String username = sc.nextLine();
		System.out.println("Enter the password");
		String password = sc.nextLine();
		System.out.println("Re-Enter the password");
		String rePassword = sc.nextLine();
		if (password.equals(rePassword) && password.length() > 4 ) {
			if (conatct.length()==10) {
				le.setConatct_no(conatct);
				le.setEmail(email);
				le.setName(name);
				le.setUsername(username);
				le.setPassword(password);
				String msg = iLoginService.isAddNewUser(le) ? "User registration successfuly!"
						: "Unable to registration!";
				System.out.println(msg);
			}else {
				System.out.println("contact number must have 10 numbers");
			}
		} else {
			System.out.println("Password Not Match or password should be greater than 4");
		}
	}

}
