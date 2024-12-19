package com.hotel.client.AdminOperation;

import com.hotel.client.entity.StateEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DistrictEntity extends StateEntity{
	
	
	private int distId;
	private String distName;
}

