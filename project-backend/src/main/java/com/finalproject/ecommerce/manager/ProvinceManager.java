package com.finalproject.ecommerce.manager;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.finalproject.ecommerce.entity.shopping.Province;

public class ProvinceManager extends BaseManager<Province> {

	public void createTable() throws SQLException {
		connect();
		String productTable = "CREATE TABLE IF NOT EXISTS province(province_id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY NOT NULL, province_name VARCHAR(100) NOT NULL";
		PreparedStatement statement = connection.prepareStatement(productTable);
		statement.executeUpdate();
		disconnect();
	}
	@Override
	protected Province parse(ResultSet resultSet) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}
