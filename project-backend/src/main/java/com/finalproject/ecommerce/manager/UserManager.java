package com.finalproject.ecommerce.manager;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.finalproject.ecommerce.entity.auth.User;

public class UserManager extends BaseManager<User> {

	public void createTable() throws SQLException {
		connect();
		String userTable = "CREATE TABLE IF NOT EXISTS users(user_id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY NOT NULL, user_name VARCHAR(100) NOT NULL, user_password VARCHAR(128))";
		PreparedStatement statement = connection.prepareStatement(userTable);
		statement.executeUpdate();
		disconnect();
	}

	@Override
	protected User parse(ResultSet resultSet) throws SQLException {
		long userId = resultSet.getLong("user_id");
		String userName = resultSet.getString("user_name");
		String userPassword = resultSet.getString("user_password");

		User user = new User(userId, userName, userPassword);

		return user;
	}

	public User find(String userName) throws SQLException {
		User user = null;
		connect();

		String sql = "SELECT * FROM users WHERE user_name = ?";
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, userName);
		ResultSet resultSet = statement.executeQuery();

		if (resultSet.next()) {
			user = parse(resultSet);
		}
		disconnect();
		return user;

	}
	
	public User find(long userId) throws SQLException {
		User user = null;
		connect();

		String sql = "SELECT * FROM users WHERE user_id = ?";
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setLong(1, userId);
		ResultSet resultSet = statement.executeQuery();

		if (resultSet.next()) {
			user = parse(resultSet);
		}
		disconnect();
		return user;

	}

	public boolean createUser(User user) throws SQLException {
		int affected = 0;
		connect();
		String sql = "INSERT INTO users(user_name, user_password) VALUES(?,?)";
		PreparedStatement statement = connection.prepareStatement(sql);

		statement.setString(1, user.getUserName());
		statement.setString(2, user.getUserPassword());

		affected = statement.executeUpdate();
		disconnect();
		return affected > 0;
	}

	public List<User> list() throws SQLException {
		connect();
		List<User> userList = new ArrayList<>();
		String sql = "SELECT * FROM users";
		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet resultSet = statement.executeQuery();
		userList = parseList(resultSet);
		disconnect();

		return userList;
	}
}
