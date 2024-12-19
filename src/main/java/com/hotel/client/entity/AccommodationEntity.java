package com.hotel.client.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccommodationEntity {
	private Integer accommodationID;
	private String typeOfAccommodation;
}