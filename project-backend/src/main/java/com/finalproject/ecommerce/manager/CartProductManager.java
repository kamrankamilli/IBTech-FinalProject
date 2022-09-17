package com.finalproject.ecommerce.manager;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.finalproject.ecommerce.entity.shopping.CartProduct;

public class CartProductManager extends BaseManager<CartProduct> {
	public void createTable() throws SQLException {
		connect();
		String cartTable = "CREATE TABLE IF NOT EXISTS cartproduct(cart_product_id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY NOT NULL, cart_id BIGINT NOT NULL, product_id BIGINT NOT NULL, sale_quantity NUMERIC NOT NULL, CONSTRAINT fk_cart_id FOREIGN KEY(cart_id) REFERENCES cart(cart_id) ON DELETE CASCADE,CONSTRAINT fk_product_id FOREIGN KEY(product_id) REFERENCES product(product_id) ON DELETE CASCADE)";
		PreparedStatement statement = connection.prepareStatement(cartTable);
		statement.executeUpdate();
		disconnect();
	}

	public boolean insert(CartProduct cartProduct) throws SQLException {
		connect();
		String sql = "INSERT INTO cartproduct(cart_id,product_id,sale_quantity) VALUES (?,?,?)";
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setLong(1, cartProduct.getCartId());
		statement.setLong(2, cartProduct.getProducId());
		statement.setInt(3, cartProduct.getSaleQuantity());
		int affected = statement.executeUpdate();
		disconnect();
		return affected > 0;
	}

	public CartProduct getCartProduct(long cartId, long productId) throws SQLException {
		connect();
		CartProduct cartProduct = null;
		String sql = "SELECT * FROM cartproduct WHERE cart_id=? AND product_id=?";
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setLong(1, cartId);
		statement.setLong(2, productId);
		ResultSet resultSet = statement.executeQuery();
		if (resultSet.next()) {
			cartProduct = parse(resultSet);
		}
		disconnect();
		return cartProduct;
	}

	public List<CartProduct> getCartItems(long cartId) throws SQLException {
		connect();
		List<CartProduct> cartProductList = new ArrayList<>();
		String sql = "SELECT * FROM cartproduct WHERE cart_id=?";
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setLong(1, cartId);
		ResultSet resultSet = statement.executeQuery();
		cartProductList = parseList(resultSet);
		disconnect();
		return cartProductList;
	}

	public boolean update(CartProduct cartProduct) throws SQLException {
		connect();
		String sql = "UPDATE cartproduct SET sale_quantity=? WHERE cart_id=? AND product_id=?";
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setInt(1, cartProduct.getSaleQuantity());
		statement.setLong(2, cartProduct.getCartId());
		statement.setLong(3, cartProduct.getProducId());
		int affected = statement.executeUpdate();
		disconnect();
		return affected > 0;
	}
	public boolean delete(CartProduct cartProduct) throws SQLException {
		connect();
		String sql = "DELETE FROM cartproduct WHERE cart_product_id=?";
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setLong(1, cartProduct.getCartProductId());
		int affected = statement.executeUpdate();
		disconnect();
		return affected > 0;
	}
	@Override
	protected CartProduct parse(ResultSet resultSet) throws SQLException {
		long cartProductId = resultSet.getLong("cart_product_id");
		int saleQuantity = resultSet.getInt("sale_quantity");
		long productId = resultSet.getLong("product_id");
		long cartId = resultSet.getLong("cart_id");
		CartProduct cartProduct = new CartProduct(cartProductId,saleQuantity,cartId,productId);
		
		return cartProduct;
	}
}
