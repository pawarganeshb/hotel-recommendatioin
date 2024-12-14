package com.hotel.client.service;

import com.hotel.client.entity.LoginEntity;
import com.hotel.client.repo.ILoginRepo;
import com.hotel.client.repo.LoginRepoImpl;

public class LoginServiceImpl implements ILoginService {
private ILoginRepo iLoginRepo=new LoginRepoImpl();
	@Override
	public boolean isAddNewUser(LoginEntity entity) {
		
		return iLoginRepo.isAddNewUser(entity);
	}
	@Override
	public String checkType(LoginEntity le) {
		// TODO Auto-generated method stub
		return iLoginRepo.checkType(le);
	}


}
