package com.finalproject.ecommerce.manager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public abstract class BaseManager<E> {
	protected String url = "jdbc:postgresql://localhost/ecommerce";
	protected String user = "postgres";
	protected String password = "Kamran7814";
	protected String driver = "org.postgresql.Driver";

	protected Connection connection;

	public BaseManager() {
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

	}

	protected void connect() throws SQLException {
		connection = DriverManager.getConnection(url, user, password);
	}

	protected void disconnect() throws SQLException {
		connection.close();
	}

	protected List<E> parseList(ResultSet resulSet) throws SQLException {
		List<E> entityList = new ArrayList<>();
		while (resulSet.next()) {
			entityList.add(parse(resulSet));
		}
		return entityList;
	}

	protected abstract E parse(ResultSet resultSet) throws SQLException;

}