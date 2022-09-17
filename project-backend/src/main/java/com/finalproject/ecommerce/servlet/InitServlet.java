package com.finalproject.ecommerce.servlet;

import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import com.finalproject.ecommerce.manager.CartManager;
import com.finalproject.ecommerce.manager.CartProductManager;
import com.finalproject.ecommerce.manager.CategoryManager;
import com.finalproject.ecommerce.manager.ProductManager;
import com.finalproject.ecommerce.manager.UserManager;

public class InitServlet extends HttpServlet {
	protected static CartManager cartManager;
	protected static ProductManager productManager;
	protected static CartProductManager cartProductManager;
	protected static CategoryManager categoryManager;
	protected static UserManager userManager;

	public static CartManager getCartManager() {
		if (cartManager == null) {
			cartManager = new CartManager();
		}
		return cartManager;
	}

	public static ProductManager getProductManager() {
		if (productManager == null) {
			productManager = new ProductManager();
		}
		return productManager;
	}

	public static CartProductManager getCartProductManager() {
		if (cartProductManager == null) {
			cartProductManager = new CartProductManager();
		}
		return cartProductManager;
	}

	public static CategoryManager getCategoryManager() {
		if (categoryManager == null) {
			categoryManager = new CategoryManager();
		}
		return categoryManager;
	}
	public static UserManager getUserManager() {
		if (userManager == null) {
			userManager = new UserManager();
		}
		return userManager;
	}


	@Override
	public void init() throws ServletException {
		try {
			getCategoryManager().createTable();
			getProductManager().createTable();
			getCartManager().createTable();
			getCartProductManager().createTable();
			getUserManager().createTable();
		} catch (SQLException e) {
			e.printStackTrace();
			System.exit(1);
		}
	}

}
