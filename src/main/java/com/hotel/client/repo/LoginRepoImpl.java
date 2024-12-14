package com.hotel.client.repo;
import java.sql.SQLException;

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

	@Override
	public String checkType(LoginEntity le) {
		// TODO Auto-generated method stub
		try {
			pst=con.prepareStatement("select login_type from login where username=? and password=?");
			pst.setString(1, le.getUsername());
			pst.setString(2, le.getPassword());
			rs=pst.executeQuery();
			String type="";
			
			while (rs.next()) {
				type=rs.getString(1);
				
			}
			return type;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

}
