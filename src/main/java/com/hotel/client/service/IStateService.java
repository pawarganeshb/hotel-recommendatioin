package com.hotel.client.service;

import java.util.List;

import com.hotel.client.entity.StateEntity;

public interface IStateService {
	public boolean addState(StateEntity stateEntity);

	public boolean deleteState(int stateId);

	public boolean updateState(StateEntity stateEntity);

	public List<StateEntity> getAllStates();
}
