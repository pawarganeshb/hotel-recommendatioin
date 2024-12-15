package com.hotel.client.repo;

import java.util.List;

import com.hotel.client.entity.StateEntity;

public interface ISateRepo {
	public boolean addState(StateEntity stateEntity);

	public List<StateEntity> getStates();

	public boolean deleteState(int stateId);

	public boolean updateState(StateEntity stateEntity);
	
	public List<StateEntity> getAllStates();
}
