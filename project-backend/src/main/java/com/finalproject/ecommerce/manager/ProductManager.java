package com.finalproject.ecommerce.manager;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.finalproject.ecommerce.entity.inventory.Category;
import com.finalproject.ecommerce.entity.inventory.Product;

public class ProductManager extends BaseManager<Product> {

	public void createTable() throws SQLException {
		connect();
		String productTable = "CREATE TABLE IF NOT EXISTS product(product_id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY NOT NULL, product_name VARCHAR(100) NOT NULL, description TEXT, sales_price NUMERIC NOT NULL, image_path TEXT NOT NULL, category_id BIGINT, CONSTRAINT fk_category_id FOREIGN KEY(category_id) REFERENCES category(category_id) ON DELETE CASCADE )";
		PreparedStatement statement = connection.prepareStatement(productTable);
		statement.executeUpdate();
		disconnect();
	}

	public Product find(long productId) throws SQLException {
		Product product = null;
		connect();
		String sql = "SELECT p.product_id,p.product_name,p.description, p.sales_price, p.image_path,p.category_id,c.category_name FROM product p JOIN category c ON p.category_id=c.category_id  WHERE product_id=?";
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setLong(1, productId);
		ResultSet resultSet = statement.executeQuery();
		if (resultSet.next()) {
			product = parse(resultSet);
		}
		disconnect();
		return product;

	}

	public List<Product> list() throws SQLException {
		List<Product> productList = new ArrayList<>();
		connect();
		String sql = "SELECT p.product_id,p.product_name,p.description,p.sales_price,p.image_path,p.category_id,c.category_name FROM product p JOIN category c ON p.category_id=c.category_id";
		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet resultSet = statement.executeQuery();
		productList = parseList(resultSet);
		disconnect();
		return productList;

	}

	public List<Product> list(long categoryId) throws SQLException {
		List<Product> productList = new ArrayList<>();
		connect();
		String sql = "SELECT p.product_id,p.product_name,p.description,p.sales_price,p.image_path,p.category_id,c.category_name FROM product p JOIN category c ON p.category_id=c.category_id WHERE p.category_id=?";
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setLong(1, categoryId);
		ResultSet resultSet = statement.executeQuery();
		productList = parseList(resultSet);
		disconnect();
		return productList;
	}

	public boolean insert(Product product) throws SQLException {
		connect();
		String sql = "INSERT INTO product(product_name, description, sales_price, image_path, category_id) VALUES(?,?,?,?,?)";
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, product.getProductName());
		statement.setString(2, product.getDescription());
		statement.setDouble(3, product.getSalesPrice());
		statement.setString(4, product.getImagePath());
		statement.setLong(5, product.getCategory().getCategoryId());
		int affected = statement.executeUpdate();
		disconnect();
		return affected > 0;

	}

	public boolean update(Product product) throws SQLException {
		connect();
		String sql = "UPDATE product SET product_name=?,description=?, sales_price=?,image_path=?, category_id=? WHERE product_id=?";
		PreparedStatement statement = connection.prepareStatement(sql);

		statement.setString(1, product.getProductName());
		statement.setString(2, product.getDescription());
		statement.setDouble(3, product.getSalesPrice());
		statement.setString(4, product.getImagePath());
		statement.setLong(5, product.getCategory().getCategoryId());
		statement.setLong(6, product.getProductId());

		int affected = statement.executeUpdate();

		disconnect();

		return affected > 0;
	}

	public boolean delete(long productId) throws SQLException {
		connect();
		String sql = "DELETE FROM product WHERE product_id=?";
		PreparedStatement statement = connection.prepareStatement(sql);

		int affected = statement.executeUpdate();
		disconnect();

		return affected > 0;
	}

	@Override
	protected Product parse(ResultSet resultSet) throws SQLException {
		long productId = resultSet.getLong("product_id");
		String productName = resultSet.getString("product_name");
		String description = resultSet.getString("description");
		double salePrice = resultSet.getDouble("sales_price");
		String imagePath = resultSet.getString("image_path");
		long categoryId = resultSet.getLong("category_id");
		String categoryName = resultSet.getString("category_name");
		Category category = new Category(categoryId,categoryName);
		Product product = new Product(productId, productName, description, salePrice, imagePath);
		product.setCategory(category);
		return product;
	}

}
