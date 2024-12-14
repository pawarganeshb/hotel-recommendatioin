package com.hotel.client.repo;

import com.hotel.client.config.Database_Connection;
import com.hotel.client.entity.StateEntity;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class StateRepoImple extends Database_Connection implements ISateRepo {

	@Override
	public boolean addState(StateEntity stateEntity) {
		String SQL = "INSERT INTO states (stateName) VALUES (?)";
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
	public List<StateEntity> getStates() {
		String SQL = "SELECT * FROM states";
		try {
			pst = con.prepareStatement(SQL);
			ResultSet rs = pst.executeQuery();
			List<StateEntity> stateEntities = null;
			if (rs.next()) {
				StateEntity entity = new StateEntity();
				rs.getInt(entity.getS_id());
				rs.getInt(entity.getS_name());
				stateEntities.add(rs);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return stateEntities;
	}

	@Override
	public List<StateEntity> getAllStates() {
		String SQL = "SELECT * FROM states";
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
		String SQL = "UPDATE states SET stateName = ? WHERE stateId = ?";
		try {
			pst = con.prepareStatement(SQL);
			pst.setString(1, stateEntity.getS_name());
			pst.setInt(2, stateEntity.getStateId());
			int value = pst.executeUpdate();
			return value > 0;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean deleteState(int stateId) {
		String SQL = "DELETE FROM states WHERE stateId = ?";
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
}
