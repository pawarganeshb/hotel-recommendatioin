package com.hotel.client.service;

import com.hotel.client.entity.LoginEntity;

public interface ILoginService {
	public boolean isAddNewUser(LoginEntity entity);
	public LoginEntity checkType(LoginEntity le);
}
