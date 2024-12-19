package com.hotel.client.repo;

import java.util.ArrayList;
import java.util.List;

import com.hotel.client.config.Database_Connection;
import com.hotel.client.entity.AmminitiesEntity;

public class AmminityRepoImple extends Database_Connection implements IAmminityRepo {

	@Override
	public boolean addAmminity(AmminitiesEntity amminitiesEntity) {
		String SQL = "INSERT INTO aminities VALUES(NULL,?,?)";
		try {
			pst = con.prepareStatement(SQL);
			pst.setString(1, amminitiesEntity.getAmminitiesName());
			pst.setDouble(2, amminitiesEntity.getAmmnitiesPrice());
			int executeUpdate = pst.executeUpdate();
			return executeUpdate > 0;
		} catch (Exception e) {
			System.out.println("AmminityRepoImple.addAmminity()");
			System.out.println(e.getMessage());
			return false;
		}
	}

	@Override
	public List<AmminitiesEntity> showAmminities() {
		String SQL = "SELECT * FROM aminities";
		List<AmminitiesEntity> list = new ArrayList<>();

		try {
			pst = con.prepareStatement(SQL);
			rs = pst.executeQuery();

			while (rs.next()) {
				int amminityId = rs.getInt("am_id");
				String ammnityName = rs.getString("am_name");
				double amminityPrice = rs.getDouble("am_price");
				list.add(new AmminitiesEntity(amminityId, ammnityName, amminityPrice));
			}
			return list;
		} catch (Exception e) {
			System.out.println("AmminityRepoImple.showAmminities()");
			System.out.println(e.getMessage());
		}
		return null;
	}

	@Override
	public boolean updateAmminity(AmminitiesEntity amminitiesEntity) {
		String SQL = "UPDATE aminities SET am_name = ?,am_price = ? WHERE am_id = ? ";
		try {
			pst = con.prepareStatement(SQL);
			pst.setString(1, amminitiesEntity.getAmminitiesName());
			pst.setDouble(2, amminitiesEntity.getAmmnitiesPrice());
			pst.setInt(3, amminitiesEntity.getAminitiesID());
			return pst.executeUpdate() > 0;
		} catch (Exception e) {
			System.out.println("AmminityRepoImple.updateAmminity()");
			System.out.println(e.getMessage());
			return false;
		}
	}

	@Override
	public boolean deleteAmminity(int amminitiesEntity) {
		String SQL = "DELETE FROM aminities WHERE am_id = ?";
		try {
			pst = con.prepareStatement(SQL);
			pst.setInt(1, amminitiesEntity);
			return pst.executeUpdate() > 0;
		} catch (Exception e) {
			System.out.println("AmminityRepoImple.deleteAmminity()");
			System.out.println(e.getMessage());
			return false;
		}
	}

}