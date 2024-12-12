package com.hotel.client.repo;
import com.hotel.client.config.Database_Connection;
import com.hotel.client.entity.LoginEntity;

public class LoginRepoImpl extends Database_Connection implements ILoginRepo {

	@Override
	public boolean isAddNewUser(LoginEntity le) {

		String SQL = "insert into login values(0,?,?,'User')";
		try {
			pst = con.prepareStatement(SQL);
			pst.setString(1, le.getUsername());
			pst.setString(2, le.getPassword());
			int value=pst.executeUpdate();
			return value>0?true:false;
			
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

}
