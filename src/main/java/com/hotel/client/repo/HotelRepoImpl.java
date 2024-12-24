package com.hotel.client.repo;

import java.util.ArrayList;
import java.util.List;

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

	@Override
	public List<HotelEntity> showAllHotel(int l_id) {
		try {
			pst=con.prepareStatement(" select h.h_id,h.h_name,h.h_address,ac.a_type,GROUP_CONCAT(a.am_name ORDER BY a.am_name) AS aminities,(h.price + IFNULL(SUM(a.am_price), 0)) AS total_price from hotel h INNER JOIN accommodation ac ON ac.a_id = h.type left join hotel_aminity_join ha ON h.h_id = ha.h_id left join  aminities a ON ha.am_id = a.am_id where h.l_id = ? group by h.h_id, h.h_name, h.h_address, ac.a_type, h.price");
			pst.setInt(1, l_id);
			rs=pst.executeQuery();
			List<HotelEntity> al=new ArrayList<HotelEntity>();
			while (rs.next()) {
				HotelEntity he=new HotelEntity();
				he.setHid(rs.getInt(1));
				he.setHname(rs.getString(2));
				he.setHaddress(rs.getString(3));
				he.setTypeOfAccommodation(rs.getString(4));
				String amenities = rs.getString("aminities");
                if (amenities == null) {
                    he.setAmminitiesName("No amenities");
                } else {
                    he.setAmminitiesName(amenities);
                }
				he.setHprice(rs.getInt(6));
				al.add(he);
				
			}
			return al;
		} catch (Exception e) {
			
			System.out.println(e);
			return null;
		}
	}

	@Override
	public List<HotelEntity> serchHotel(int l_id, String name) {
		try {
			pst=con.prepareStatement(" select h.h_id,h.h_name,h.h_address,ac.a_type,GROUP_CONCAT(a.am_name ORDER BY a.am_name) AS aminities,(h.price + IFNULL(SUM(a.am_price), 0)) AS total_price from hotel h INNER JOIN accommodation ac ON ac.a_id = h.type left join hotel_aminity_join ha ON h.h_id = ha.h_id left join  aminities a ON ha.am_id = a.am_id where h.l_id = ? and h.h_name like ? group by h.h_id, h.h_name, h.h_address, ac.a_type, h.price");
			pst.setInt(1, l_id);
			pst.setString(2, "%"+name+"%");
			rs=pst.executeQuery();
			List<HotelEntity> al=new ArrayList<HotelEntity>();
			while (rs.next()) {
				HotelEntity he=new HotelEntity();
				he.setHid(rs.getInt(1));
				he.setHname(rs.getString(2));
				he.setHaddress(rs.getString(3));
				he.setTypeOfAccommodation(rs.getString(4));
				String amenities = rs.getString("aminities");
                if (amenities == null) {
                    he.setAmminitiesName("No amenities");
                } else {
                    he.setAmminitiesName(amenities);
                }
				he.setHprice(rs.getInt(6));
				al.add(he);
				
			}
			return al;
		} catch (Exception e) {
			
			System.out.println(e);
			return null;
		}
	}

	@Override
	public boolean deleteHotel(int hotelId) {
		boolean isDeleted = false;
		try {
			pst=con.prepareStatement("DELETE FROM hotel WHERE h_id = ?");
			pst.setInt(1, hotelId);
	        int rowsAffected = pst.executeUpdate();
	        if (rowsAffected > 0) {
	            isDeleted = true;
	        }
		} catch (Exception e) {
			System.out.println(e);
		}
		return isDeleted;
	}

	
}
