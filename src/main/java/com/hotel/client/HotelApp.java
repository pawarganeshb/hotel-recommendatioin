package com.hotel.client;

import java.util.Scanner;

import com.hotel.client.entity.LoginEntity;
import com.hotel.client.entity.StateEntity;
import com.hotel.client.service.ILoginService;
import com.hotel.client.service.IStateService;
import com.hotel.client.service.LoginServiceImpl;
import com.hotel.client.service.StateServiceImp;

public class HotelApp {
	static int count = 0;
	// entity class object
	static LoginEntity le = new LoginEntity();
	// Service object
	static ILoginService iLoginService = new LoginServiceImpl();
	static IStateService iStateService = new StateServiceImp();
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
				LoginEntity type = iLoginService.checkType(le);
			
				if (type.getType().equals("Admin")) {
					System.out.println("**************Welcome " + type.getName().toUpperCase() + "***************");
					do {
						System.out.println("1] Add State");
						System.out.println("2] Delete State");
						System.out.println("3] Update State");
						System.out.println("4] View State");
						System.out.println("5] Exit");
						System.out.println("Enter you choice: ");
						int choice = sc.nextInt();
						switch (choice) {
						case 1:
							System.out.println("Enter the state:");
							sc.nextLine();
							String state = sc.nextLine();
							StateEntity entity = new StateEntity(null, state);
							boolean state1 = iStateService.addState(entity);
							System.out.println(state1 ? "State is added." : "Unable to added state.");
							break;
						case 2:
							iStateService.getAllStates().forEach(System.out::println);
							System.out.println("Enter state ID: ");
							int id = sc.nextInt();
							System.out.println(iStateService.deleteState(id) ? "State has been deleted"
									: "Unable to delete state.");
							break;
						case 3:
							iStateService.getAllStates().forEach(System.out::println);
							System.out.println("Enter the id: ");
							int s_id = sc.nextInt();
							System.out.println("Enter the state: ");
							sc.nextLine();
							String s_name = sc.nextLine();
							StateEntity entity1 = new StateEntity(s_id, s_name);
							System.out
									.println(iStateService.updateState(entity1) ? "State has been updated successfully!"
											: "Unable to update.");

							break;
						case 4:
							iStateService.getAllStates().forEach(System.out::println);
							break;
						case 5:
							System.out.println("Exit..");
							System.exit(0);
							break;
						default:
							System.out.println("Invalid Operation");
							break;
						}
					} while (true);

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
				type = iLoginService.checkType(le);
				if (type.equals("User")) {
					System.out.println("**************Welcome " + username.toUpperCase() + "***************");
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
		System.out.println("\nEnter username");
		String username = sc.nextLine();
		System.out.println("Enter the password");
		String password = sc.nextLine();
		System.out.println("Re-Enter the password");
		String rePassword = sc.nextLine();
		if (password.equals(rePassword) && password.length() > 4) {
			le.setUsername(username);
			le.setPassword(password);
			String msg = iLoginService.isAddNewUser(le) ? "User registration successfuly!" : "Unable to registration!";
			System.out.println(msg);
		} else {
			System.out.println("Password Not Match or password should be greater than 4");
		}
	}

}
