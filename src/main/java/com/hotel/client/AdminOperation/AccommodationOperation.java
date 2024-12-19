package com.hotel.client.AdminOperation;

import java.util.Scanner;

import com.hotel.client.entity.AccommodationEntity;
import com.hotel.client.service.AccommodationServiceImple;
import com.hotel.client.service.IAccommodationService;

public class AccommodationOperation {
	static Scanner sc = new Scanner(System.in);
	static IAccommodationService accommodationService = new AccommodationServiceImple();

	public AccommodationOperation() {
		int choice=0;
		do {
			System.out.println("1) Add Accommodation.");
			System.out.println("2) Show Accommodation.");
			System.out.println("3) Update Accommodation.");
			System.out.println("4) Delete Accommodation.");
			System.out.println("5) Exit.");

			System.out.println("Enter your choice: ");
			 choice = sc.nextInt();
			sc.nextLine();

			switch (choice) {
			case 1:
				addAccommodation();
				break;
			case 2:
				showAccommodation();
				break;
			case 3:
				updateAccommodation();
				break;
			case 4:
				deleteAccommodation();
				break;
			case 5:
				System.out.println("Exiting...");
				
				break;
			default:
				System.out.println("Invalid Choice!");
				break;
			}
		} while (choice!=5);
	}

	private void addAccommodation() {
		System.out.println("Enter Accommodation Type: ");
		String accommodationType = sc.nextLine();
		AccommodationEntity entity = new AccommodationEntity(null, accommodationType);
		System.out.println(accommodationService.addAccommodation(entity) ? "Accommodation Added Successfully!"
				: "Unable to Add Accommodation");
	}

	private void showAccommodation() {
		accommodationService.showAccommodation().forEach(System.out::println);
	}

	private void updateAccommodation() {
		showAccommodation();
		System.out.println("Enter New Accommodation Type: ");
		String newType = sc.nextLine();
		System.out.println("Enter That ID: ");
		int id = sc.nextInt();
		AccommodationEntity updatedEntity = new AccommodationEntity(id, newType);
		System.out
				.println(accommodationService.updateAccommodation(updatedEntity) ? "Accommodation Updated Successfully!"
						: "Unable to Update Accommodation");
	}

	private void deleteAccommodation() {
		showAccommodation();
		System.out.println("Enter Accommodation ID to Delete: ");
		int id = sc.nextInt();
		System.out.println(accommodationService.deleteAccommodation(id) ? "Accommodation Deleted Successfully!"
				: "Unable to Delete Accommodation");
	}

}
