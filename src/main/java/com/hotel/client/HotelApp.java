package com.hotel.client;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.hotel.client.AdminOperation.AccommodationOperation;
import com.hotel.client.AdminOperation.AmminitiesOperatin;
import com.hotel.client.AdminOperation.CityOperation;
import com.hotel.client.AdminOperation.DistrictOperation;
import com.hotel.client.AdminOperation.HotelOperation;
import com.hotel.client.AdminOperation.StateOperation;
import com.hotel.client.AdminOperation.UserOperation;
import com.hotel.client.entity.CityEntity;
import com.hotel.client.entity.DistrictEntity;
import com.hotel.client.entity.HotelEntity;
import com.hotel.client.entity.LoginEntity;
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
import com.hotel.client.service.ILoginService;
import com.hotel.client.service.IStateServices;
import com.hotel.client.service.IUserService;
import com.hotel.client.service.LoginServiceImpl;
import com.hotel.client.service.StateServices;
import com.hotel.client.service.UserSeviceImpl;

public class HotelApp {
	static int count = 0;
	static LoginEntity le = new LoginEntity();
	static StateEntity se = new StateEntity();
	static DistrictServiceImpl districtService = new DistrictServiceImpl();
	static HotelEntity he = new HotelEntity();
	static IHotelService hs = new HotelServiceImpl();
	static CityEntity ce = new CityEntity();
	static ICityService cs = new CityServiceImple();
	static IAccommodationService as = new AccommodationServiceImple();
	static IAmminitiesService ams = new AmminitiesServiceImple();
	static ILoginService iLoginService = new LoginServiceImpl();
	static IStateServices iStateServices = new StateServices();
	static IUserService us=new UserSeviceImpl();
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
				String username = sc.nextLine();
				System.out.println("Enter your password");
				String password = sc.nextLine();
				le.setUsername(username);
				le.setPassword(password);
				le = iLoginService.checkType(le);
				if (le.getType().equals("Admin")) {
					System.out.println("**************Welcome " + le.getName().toUpperCase() + "***************");
					System.out.println();
					int choice = 0;
					do {
						System.out.println("1)State Operation");
						System.out.println("2)District operation");
						System.out.println("3)City operation");
						System.out.println("4)Accommodation operation");
						System.out.println("5)Aminity Operation");
						System.out.println("6)Hotel Operation");
						System.out.println("7)User operation");
						System.out.println("8)Exit");
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
							new CityOperation();
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
							new HotelOperation();
							System.out.println("");
							break;
						case 7:
							new UserOperation();
							System.out.println("");
							break;
						case 8:

							break;
						default:
							System.out.println("Enter the valid operation...");
							break;
						}
					} while (choice != 8);

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
				String pas=le.getPassword();
				
				le = iLoginService.checkType(le);
				if (le.getType().equals("User")) {
					if (le.getStatus().equals("open")) {
						
						System.out.println(
								"**************Welcome " + le.getName().toUpperCase() + "***************");
						System.out.println();
						do {
							System.out.println("1) View profile");
							System.out.println("2) Update profile");
							System.out.println("3) Search hotel");
							System.out.println("4) Exit");
							System.out.println();
							choise=sc.nextInt();
							sc.nextLine();
							switch (choise) {
							case 1:
								viewProfile(pas);
								System.out.println("");
								break;
							case 2:
								updateProfile(pas);
								System.out.println("");
								break;	
							case 3:
								serachHotel();
								break;
							case 4:
								
								break;
							default:
								System.out.println("you enter invalid operation..");
								break;
							}
						} while (choise!=4);
					}
					else {
						System.out.println("user block by admine....");
					}
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
		String name = sc.nextLine().trim();
		System.out.println("Enter the email of user");
		String email = sc.nextLine().trim();
		System.out.println("Enter the contact number");
		if (email.toLowerCase().endsWith("@gmail.com")) {
			String conatct = sc.nextLine().trim();
			System.out.println("Enter username");
			if (conatct.length() == 10) {
				String username = sc.nextLine().trim();
				System.out.println("Enter the password");
				String password = sc.nextLine().trim();
				System.out.println("Re-Enter the password");
				String rePassword = sc.nextLine().trim();
				if (password.equals(rePassword) && password.length() > 4) {
					le.setConatct_no(conatct);
					le.setEmail(email);
					le.setName(name);
					le.setUsername(username);
					le.setPassword(password);
					String msg = iLoginService.isAddNewUser(le) ? "User registration successfuly!"
							: "Unable to registration!";
					System.out.println(msg);
				} else {
					System.out.println("Mishmatch the password or length is greater than 4");
				}
			} else {
				System.out.println("Enter the valid contact number");
			}
		} else {
			System.out.println("Enter the valid email that end @gmail.com");
		}

	}

	private static void viewProfile(String pas) {
		le= us.viewProfile(pas);
		System.out.println("Name = "+le.getName());
		System.out.println("Email = "+le.getEmail());
		System.out.println("Conatct No = "+le.getConatct_no());
		
	}
	
	private static void updateProfile(String pas) {
			viewProfile(pas);
			System.out.println("");
			System.out.println("what do you want to update");
			System.out.println("");
			System.out.println("1) Name");
			System.out.println("2) Email");
			System.out.println("3) Conatct Number");
			int choice=0;
			choice=sc.nextInt();
			sc.nextLine();
			switch (choice) {
			case 1:
				System.out.println("Enter the New name");
				String newName=sc.nextLine();
				if (us.updateName( newName, pas)) {
					System.out.println("Update Name Successfully.....");
				} else {
					System.out.println("Failed to Update Name.....");
				}
				break;
			case 2:
				System.out.println("Enter New Email");
				String email=sc.nextLine();
				if (email.endsWith("@gmail.com")) {
					if (us.updateEmail(email, pas)) {
						System.out.println("Update Email Scuccessfully....");
					} else {
						System.out.println("Failed to Update Email.....");
					}
				} else {
					System.out.println("you enter wromg email");
				}
				break;
			case 3:
				System.out.println("Enter New Conatct Number");
				String contact=sc.nextLine();
				if (contact.length()==10) {
					if (us.updateContact(contact, pas)) {
						System.out.println("Update Conatct Number Scuccessfully....");
					} else {
						System.out.println("Failed to Update Conatct Number.....");
					}
				}
				else {
					System.out.println("You enter the wrong contact number");
				}
				break;
			default:
				System.out.println("Enter valid operation");
				break;
			}
	}
	
	public static void serachHotel() {
		List<StateEntity> al = new ArrayList<StateEntity>();
		al = iStateServices.getAllStates();
		System.out.println("*****************STATES********************");
		System.out.println("State_id\t State_Name");
		al.forEach((t) -> System.out.println(t.getS_id() + "\t\t" + t.getS_name()));

		System.out.println("Enter the state name");
		String statename = sc.nextLine();
		int stateId = iStateServices.getSatteIdByName(statename);
		if (stateId != 0) {
			List<DistrictEntity> dist = new ArrayList<DistrictEntity>();
			dist = districtService.showAllDistrcitWhitState(stateId);
			if (dist.size() != 0) {
				System.out.println("*****************District********************");
				System.out.println("District_id\t District_Name");
				dist.forEach((t) -> System.out.println(t.getDistId() + "\t\t" + t.getDistName()));
				System.out.println("Enter the District name");
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

						System.out.println("Eneter city name to find hotels hotel");
						String cityNamw = sc.nextLine();
						ce.setCityName(cityNamw);
						int cityId = cs.getCityIdByName(ce);
						ce.setS_id(stateId);
						ce.setDistId(distId);
						ce.setCityId(cityId);
						if (cityId != 0) {
							int lid = hs.getLocationId(ce);
							AccommodationOperation.showAccommodation();
							System.out.println("Enter the Id of hotel");
							int amId=sc.nextInt();
							sc.nextLine();
							if (as.check(amId)) {
								System.out.println("Enter the maximum Price Range");
								int Price=sc.nextInt();
								sc.nextLine();
								
							} else {
								System.out.println("you enter the wrong Id");
							}
//							List<HotelEntity> hotels = new ArrayList<HotelEntity>();
//							hotels = hs.showAllHotel(lid);
//							if (hotels.size() != 0) {
//								System.out.println(
//										"******************************Hotels*****************************************");
//								System.out.println("");
//								hotels.forEach((t) -> System.out.println(t.getHid() + "\t" + t.getHname() + "\t"
//										+ t.getHaddress() + "\t" + t.getTypeOfAccommodation() + "\t total price = "
//										+ t.getHprice() + "\nAminities = " + t.getAmminitiesName()
//										+ "\n----------------------------------------------------------------------------------------"));
//							} else {
//								System.out.println("their are no hotels");
//							}

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
}