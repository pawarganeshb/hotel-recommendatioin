package com.hotel.client.service;

import java.util.List;

import com.hotel.client.entity.StateEntity;
import com.hotel.client.repo.ISateRepo;
import com.hotel.client.repo.StateRepoImple;

public class StateServiceImp implements IStateService {
	ISateRepo stateRepo = new StateRepoImple();

	@Override
	public boolean addState(StateEntity stateEntity) {
		return stateRepo.addState(stateEntity);
	}

	@Override
	public boolean deleteState(int stateId) {
		return stateRepo.deleteState(stateId);
	}

	@Override
	public boolean updateState(StateEntity stateEntity) {
		return stateRepo.updateState(stateEntity);
	}

	@Override
	public List<StateEntity> getAllStates() {
		return stateRepo.getAllStates();
	}

}
