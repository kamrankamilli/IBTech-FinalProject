package com.finalproject.ecommerce.manager;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.finalproject.ecommerce.entity.shopping.Cart;

public class CartManager extends BaseManager<Cart> {

	public void createTable() throws SQLException {
		connect();
		String cartTable = "CREATE TABLE IF NOT EXISTS cart(cart_id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY NOT NULL, total_amount NUMERIC NOT NULL, customer_name VARCHAR(100) NOT NULL)";
		PreparedStatement statement = connection.prepareStatement(cartTable);
		statement.executeUpdate();
		disconnect();
	}

	public long create(Cart cart) throws SQLException {
		connect();
		String sql = "INSERT INTO cart(total_amount,customer_name) VALUES(?,?)";
		PreparedStatement statement = connection.prepareStatement(sql, new String[] { "cart_id" });
		statement.setDouble(1, cart.getTotalAmount());
		statement.setString(2, cart.getCustomerName());
		int affected = statement.executeUpdate();
		if (affected == 0) {
			throw new SQLException();
		}
		long cartId = 0;
		try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
			if (generatedKeys.next() && generatedKeys != null) {
				cartId = generatedKeys.getLong(1);
			} else {
				throw new SQLException();
			}
		}
		return cartId;
	}

	public boolean update(Cart cart) throws SQLException {
		connect();
		String sql = "UPDATE cart SET total_amount=? WHERE cart_id=?";
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setDouble(1, cart.getTotalAmount());
		statement.setLong(2, cart.getCartId());
		int affected = statement.executeUpdate();
		disconnect();
		return affected > 0;
	}

	public Cart getCart(long cartId) throws SQLException {
		Cart cart = null;
		connect();
		String sql = "SELECT * FROM cart WHERE cart_id=?";
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setLong(1, cartId);
		ResultSet resultSet = statement.executeQuery();
		if (resultSet.next()) {
			cart = parse(resultSet);
		}
		disconnect();
		return cart;
	
	}

	public boolean delete(long cartId) throws SQLException {
		connect();
		String sql = "DELETE FROM cart WHERE cart_id=?";
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setLong(1, cartId);
		int affected = statement.executeUpdate();

		disconnect();
		return affected > 0;
	}

	@Override
	protected Cart parse(ResultSet resultSet) throws SQLException {
		long cartId = resultSet.getLong("cart_id");
		double totalAmount = resultSet.getDouble("total_amount");
		String customerName = resultSet.getString("customer_name");

		Cart cart = new Cart(cartId, totalAmount, customerName);

		return cart;
	}

	public Cart find(long cartId) throws SQLException {
		Cart cart = null;
		connect();
		String sql = "SELECT * FROM cart WHERE cart_id=?";
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setLong(1, cartId);
		ResultSet resultSet = statement.executeQuery();
		if (resultSet.next()) {
			cart = parse(resultSet);
		}
		disconnect();
		return cart;
	}
	
	public List<Cart> list() throws SQLException{
		connect();
		List<Cart> cartList = new ArrayList<>();
		String sql = "SELECT * FROM cart";
		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet resultSet = statement.executeQuery();
		cartList=parseList(resultSet);
		return cartList;		
	}
}
