package com.hotel.client.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AmminitiesEntity extends AccommodationEntity {
	private Integer aminitiesID;
	private String amminitiesName;
	private double ammnitiesPrice;
}
