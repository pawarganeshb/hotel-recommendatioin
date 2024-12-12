package com.hotel.client.repo;

import com.hotel.client.entity.LoginEntity;

public interface ILoginRepo {
	boolean isAddNewUser(LoginEntity le);
}
