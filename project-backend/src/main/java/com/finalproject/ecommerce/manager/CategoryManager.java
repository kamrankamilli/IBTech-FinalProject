package com.finalproject.ecommerce.manager;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.finalproject.ecommerce.entity.inventory.Category;

public class CategoryManager extends BaseManager<Category> {

	public void createTable() throws SQLException {
		connect();
		String categoryTable = "CREATE TABLE IF NOT EXISTS category(category_id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY NOT NULL, category_name VARCHAR(100) NOT NULL)";
		PreparedStatement statement = connection.prepareStatement(categoryTable);
		statement.executeUpdate();
		disconnect();
	}

	public Category find(long categoryId) throws SQLException {
		connect();
		Category category = null;
		String sql = "SELECT * FROM category WHERE category_id=?";
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setLong(1, categoryId);
		ResultSet resultSet = statement.executeQuery();
		if (resultSet.next()) {
			category = parse(resultSet);
		}

		disconnect();
		return category;
	}

	public boolean insert(Category category) throws SQLException {
		connect();
		String sql = "INSERT INTO category(category_name) VALUES(?)";
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, category.getCategoryName());
		int affected = statement.executeUpdate();
		disconnect();

		return affected > 0;
	}

	public boolean update(Category category) throws SQLException {
		connect();
		String sql = "UPDATE category SET category_name=? WHERE category_id=?";
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, category.getCategoryName());
		statement.setLong(2, category.getCategoryId());
		int affected = statement.executeUpdate();
		disconnect();

		return affected > 0;
	}

	public List<Category> list() throws SQLException {
		connect();

		List<Category> categoryList = new ArrayList<>();
		String sql = "SELECT * FROM category ORDER BY category_name";
		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet resultSet = statement.executeQuery();
		categoryList = parseList(resultSet);

		disconnect();
		return categoryList;
	}

	public boolean delete(long categoryId) throws SQLException {
		connect();
		String sql = "DELETE FROM category WHERE category_id=?";
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setLong(1, categoryId);
		int affected = statement.executeUpdate();
		disconnect();
		return affected > 0;
	}

	@Override
	protected Category parse(ResultSet resultSet) throws SQLException {
		long categoryId = resultSet.getLong("category_id");
		String categoryName = resultSet.getString("category_name");

		Category category = new Category(categoryId, categoryName);

		return category;
	}

}
