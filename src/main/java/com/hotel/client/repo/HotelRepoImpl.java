package com.hotel.client.repo;

import com.hotel.client.config.Database_Connection;
import com.hotel.client.entity.CityEntity;
import com.hotel.client.entity.HotelEntity;

public class HotelRepoImpl extends Database_Connection implements IHotelRepo {

	@Override
	public int getLocationId(CityEntity ce) {
		try {
			pst=con.prepareStatement("select l_id from s_d_c_join where s_id=? and d_id=? and c_id=?");
			pst.setInt(1, ce.getS_id());
			pst.setInt(2, ce.getDistId());
			pst.setInt(3, ce.getCityId());
			rs=pst.executeQuery();
			int lid=0;
			while (rs.next()) {
				lid=rs.getInt(1);
			}
			return lid;
		} catch (Exception e) {
			System.out.println();
			return 0;
		}
	}

	@Override
	public boolean insertIntoHotel(HotelEntity he) {
		try {
			pst=con.prepareStatement("insert into hotel values(0,?,?,?,?,?,?)");
			pst.setString(1, he.getHname());
			pst.setString(2, he.getHconatct());
			pst.setInt(3, he.getLid());
			pst.setString(4, he.getHaddress());
			pst.setInt(5, he.getAccommodationID());
			pst.setInt(6, he.getHprice());
			int value=pst.executeUpdate();
			return value>0?true:false;
		} catch (Exception e) {
			System.out.println(e);
			return false;
		}
	}

	@Override
	public int hotelId() {
		try {
			pst=con.prepareStatement("select max(h_id) from hotel");
			rs=pst.executeQuery();
			int hid=0;
			while (rs.next()) {
				hid=rs.getInt(1);
			}
			return hid;
		} catch (Exception e) {
			System.out.println(e);
			return 0;
		}
	}

	@Override
	public boolean insertIntoAminityJoin(int hid, int amid) {
		try {
			pst=con.prepareStatement("insert into  hotel_aminity_join values(?,?)");
			pst.setInt(1, amid);
			pst.setInt(2, hid);
			int value=pst.executeUpdate();
			return value>0?true:false;
		} catch (Exception e) {
			System.out.println(e);
			return false;
		}
	}

	
}
