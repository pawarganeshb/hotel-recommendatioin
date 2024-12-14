package com.hotel.client.repo;

import java.util.List;

import com.hotel.client.entity.StateEntity;

public interface ISateRepo {
	public boolean addState(StateEntity stateEntity);
	public List<StateEntity> getStates();
}
