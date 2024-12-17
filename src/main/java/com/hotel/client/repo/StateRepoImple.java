package com.hotel.client.repo;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import com.hotel.client.config.Database_Connection;
import com.hotel.client.entity.StateEntity;

public class StateRepoImple extends Database_Connection implements ISateRepo {

	@Override
	public boolean addState(StateEntity stateEntity) {
		String SQL = "INSERT INTO state VALUES (0,?)";
		try {
			pst = con.prepareStatement(SQL);
			pst.setString(1, stateEntity.getS_name());
			int value = pst.executeUpdate();
			return value > 0;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	

	@Override
	public List<StateEntity> getAllStates() {
		String SQL = "SELECT * FROM state";
		List<StateEntity> stateList = new ArrayList<>();
		try {
			pst = con.prepareStatement(SQL);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				stateList.add(new StateEntity(rs.getInt("stateId"), rs.getString("stateName")));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return stateList;
	}

	@Override
	public boolean updateState(StateEntity stateEntity) {
		String SQL = "UPDATE state SET stateName = ? WHERE stateId = ?";
		try {
			pst = con.prepareStatement(SQL);
			pst.setString(1, stateEntity.getS_name());
			pst.setInt(2, stateEntity.getS_id());
			int value = pst.executeUpdate();
			return value > 0;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean deleteState(int stateId) {
		String SQL = "DELETE FROM state WHERE stateId = ?";
		try {
			pst = con.prepareStatement(SQL);
			pst.setInt(1, stateId);
			int value = pst.executeUpdate();
			return value > 0;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}



	@Override
	public List<StateEntity> searchState(String stateName) {
		String SQL = "SELECT * FROM state where stateName=?";
		List<StateEntity> stateList = new ArrayList<>();
		try {
			pst = con.prepareStatement(SQL);
			pst.setString(1, stateName);
			 rs = pst.executeQuery();
			while (rs.next()) {
				stateList.add(new StateEntity(rs.getInt("stateId"), rs.getString("stateName")));
			}
			
			return stateList;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
