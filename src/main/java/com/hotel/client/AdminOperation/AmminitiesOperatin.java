package com.hotel.client.AdminOperation;

import java.util.HashMap;
import java.util.Scanner;

import com.hotel.client.entity.AmminitiesEntity;
import com.hotel.client.service.AmminitiesServiceImple;
import com.hotel.client.service.IAmminitiesService;

public class AmminitiesOperatin {

	static Scanner scanner = new Scanner(System.in);
	static IAmminitiesService ammnityService = new AmminitiesServiceImple();

	public AmminitiesOperatin() {
		do {
			System.out.println("1) Add Amminity");
			System.out.println("2) Show Amminity");
			System.out.println("3) Update Amminity");
			System.out.println("4) Delete Amminity");
			System.out.println("5) Exit From Amminity Operation");

			System.out.print("Enter your choice: ");
			int choice = scanner.nextInt();
			scanner.nextLine();

			switch (choice) {
			case 1:
				addAmminity();
				break;

			case 2:
				showAmminities();
				break;

			case 3:
				updateAmminity();
				break;

			case 4:
				deleteAmminity();
				break;

			case 5:
				System.out.println("Exiting from Amminity Operations...");
				scanner.close();
				System.exit(0);

			default:
				System.out.println("Invalid choice! Please select a valid option.");
			}
		} while (true);
	}

	private void addAmminity() {
		System.out.print("Enter Amminity Name: ");
		String name = scanner.nextLine();

		System.out.println("Amminity Price: ");
		double price = scanner.nextDouble();

		AmminitiesEntity amminitiesEntity = new AmminitiesEntity(null, name, price);

		System.out.println(ammnityService.addAmminity(amminitiesEntity) ? "Amminity added successfully!"
				: "Unable to add amminity.");
	}

	private void showAmminities() {
		if (ammnityService.showAmmnity().isEmpty()) {
			System.out.println("No Available Amminity!");
		} else {
			ammnityService.showAmmnity().forEach(System.out::println);
		}
	}

	private void updateAmminity() {
		showAmminities();
		System.out.print("Enter Amminity ID to Update: ");
		int updateId = scanner.nextInt();

		System.out.println("Enter new Amminity: ");
		String amminityName = scanner.nextLine();

		System.out.println("Enter new Price: ");
		double amminityPrice = scanner.nextDouble();

		System.out.println(ammnityService.updateAmminity(new AmminitiesEntity(updateId, amminityName, amminityPrice))
				? "Successfully Updated Record!"
				: "Unable to update Record");
	}

	private void deleteAmminity() {
		showAmminities();
		System.out.println("Enter amminity id to delete: ");
		int deleteID = scanner.nextInt();
		
		System.out.println(ammnityService.deleteAmminity(deleteID)?"Successfully Deleted Record!":"Unable to Delete Record");
	}
}
