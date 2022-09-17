package com.finalproject.ecommerce.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import com.finalproject.ecommerce.entity.inventory.Product;
import com.finalproject.ecommerce.entity.shopping.Cart;
import com.finalproject.ecommerce.entity.shopping.CartProduct;
import com.finalproject.ecommerce.response.Response;
import com.finalproject.ecommerce.utils.XmlHelper;
import com.finalproject.ecommerce.xml.CartProductXml;
import com.finalproject.ecommerce.xml.CartXml;

@WebServlet(name = "CartServlet", urlPatterns = { "/api/cart/create", "/api/cart/add", "/api/cart/view",
		"/api/cart/remove","/api/cart/delete" })
public class CartServlet extends InitServlet {

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String servletPath = request.getServletPath();
		if (servletPath.equals("/api/cart/create")) {
			Cart cart;
			try {
				cart = CartXml.parseXml(request.getInputStream());
				long cartId = getCartManager().create(cart);
				cart.setCartId(cartId);
				Document document = CartXml.format(cart);
				XmlResponse(response, document);
			} catch (ParserConfigurationException e) {
				e.printStackTrace();
			} catch (SAXException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (TransformerException e) {
				e.printStackTrace();
			}
		} else if (servletPath.equals("/api/cart/add")) {
			try {
				addToCart(request, response);

			} catch (ParserConfigurationException e) {
				e.printStackTrace();
			} catch (SAXException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} 
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String servletPath = request.getServletPath();
		if (servletPath.equals("/api/cart/view")) {
			String cartId = request.getParameter("cartId");
			try {
				Cart cart = getCartManager().find(Long.parseLong(cartId));
				if (cart != null) {
					List<CartProduct> cartProductList = getCartProductManager().getCartItems(cart.getCartId());
					Document document = CartProductXml.format(cart, cartProductList);
					XmlResponse(response, document);
				}
			} catch (NumberFormatException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (ParserConfigurationException e) {
				e.printStackTrace();
			} catch (TransformerException e) {
				e.printStackTrace();
			}
		}else if (servletPath.equals("/api/cart/remove")) {
			try {
				removeFromCart(request, response);
			} catch (ParserConfigurationException e) {
				e.printStackTrace();
			} catch (SAXException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}else if(servletPath.equals("/api/cart/delete")) {
			try {
				deleteCart(request, response);
			} catch (NumberFormatException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	private void XmlResponse(HttpServletResponse response, Document document)
			throws ParserConfigurationException, TransformerException, IOException {
		response.setContentType("application/xml; charset=UTF-8");
		XmlHelper.dump(document, response.getOutputStream());
	}

	private void addToCart(HttpServletRequest request, HttpServletResponse response)
			throws ParserConfigurationException, SAXException, IOException, SQLException {
		CartProduct cartProduct = CartProductXml.parseXml(request.getInputStream());
		Cart cart = getCartManager().find(cartProduct.getCartId());

		if (cart != null) {

			Product product = getProductManager().find(cartProduct.getProducId());

			if (product != null) {

				CartProduct existCartProduct = getCartProductManager().getCartProduct(cart.getCartId(),
						product.getProductId());
				if (existCartProduct != null) {
					int totalSaleQuantity = existCartProduct.getSaleQuantity() + cartProduct.getSaleQuantity();
					existCartProduct.setSaleQuantity(totalSaleQuantity);
					getCartProductManager().update(existCartProduct);

					double totalAmount = cart.getTotalAmount() + cartProduct.getSaleQuantity() * product.getSalesPrice();
					cart.setTotalAmount(totalAmount);
					getCartManager().update(cart);

					Response.sendResponse(response, 200, "OK");

				} else {
					getCartProductManager().insert(cartProduct);
					double totalAmount = cart.getTotalAmount() + cartProduct.getSaleQuantity() * product.getSalesPrice();
					cart.setTotalAmount(totalAmount);
					getCartManager().update(cart);
				}
			} else {
				Response.sendResponse(response, 200, "Product does not exist");
			}

		} else {
			Response.sendResponse(response, 200, "Cart does not exist");
		}
	}

	private void removeFromCart(HttpServletRequest request, HttpServletResponse response)
			throws ParserConfigurationException, SAXException, IOException, SQLException {
	
		long cartId= Long.parseLong(request.getParameter("cartId"));
		long productId =  Long.parseLong(request.getParameter("productId"));

		Cart cart = getCartManager().find(cartId);
		
		if (cart != null) {
			Product product = getProductManager().find(productId);
			CartProduct existingCartProduct = getCartProductManager().getCartProduct(cartId,productId);
			if(existingCartProduct!=null) {

				getCartProductManager().delete(existingCartProduct);
				double totalAmount = cart.getTotalAmount() - existingCartProduct.getSaleQuantity()*product.getSalesPrice();
				cart.setTotalAmount(totalAmount);
				getCartManager().update(cart);
				Response.sendResponse(response, 200, "OK");
			}
		}
	}
	
	private void deleteCart(HttpServletRequest request,HttpServletResponse response) throws NumberFormatException, SQLException {
		String cartId = request.getParameter("cartId");
		if(cartId!=null && !cartId.equals("")) {
			getCartManager().delete(Long.parseLong(cartId));
		}
	}
}
