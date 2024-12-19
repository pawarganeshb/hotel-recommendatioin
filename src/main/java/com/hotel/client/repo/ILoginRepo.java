package com.hotel.client.repo;

import com.hotel.client.entity.LoginEntity;

public interface ILoginRepo {
	public boolean isAddNewUser(LoginEntity le);
	public LoginEntity checkType(LoginEntity le);
	
}
