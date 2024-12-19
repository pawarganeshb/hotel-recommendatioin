package com.hotel.client.repo;
import java.sql.SQLException;

import com.hotel.client.config.Database_Connection;
import com.hotel.client.entity.LoginEntity;

public class LoginRepoImpl extends Database_Connection implements ILoginRepo {

	@Override
	public boolean isAddNewUser(LoginEntity le) {

		String SQL = "insert into user values(0,?,?,?,?,?,'User')";
		try {
			pst = con.prepareStatement(SQL);
			pst.setString(1,le.getName());
			pst.setString(2,le.getEmail());
			pst.setString(3,le.getConatct_no());
			pst.setString(4, le.getUsername());
			pst.setString(5, le.getPassword());
			int value=pst.executeUpdate();
			return value>0?true:false;
			
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public LoginEntity checkType(LoginEntity le) {
		try {
			pst=con.prepareStatement("select Name,userType from user where username=? and password=?");
			pst.setString(1, le.getUsername());
			pst.setString(2, le.getPassword());
			rs=pst.executeQuery();
			String type="";
			String name="";
			while (rs.next()) {
				name=rs.getString(1);
				type=rs.getString(2);
				
			}
			LoginEntity le1=new LoginEntity();
			le1.setType(type);
			le1.setName(name);
			return le1;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

}