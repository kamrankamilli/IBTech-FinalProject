package com.finalproject.ecommerce.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.w3c.dom.Document;

import com.finalproject.ecommerce.entity.inventory.Product;
import com.finalproject.ecommerce.manager.ProductManager;
import com.finalproject.ecommerce.response.Response;
import com.finalproject.ecommerce.utils.XmlHelper;
import com.finalproject.ecommerce.xml.ProductXml;

@WebServlet(urlPatterns = { "/api/products", "/api/product" })
public class ProductServlet extends InitServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String servletPath = request.getServletPath();

		if (servletPath.equals("/api/products")) {
			String categoryId = request.getParameter("categoryId");
			if (categoryId != null) {
				Document document;
				try {
					document = ProductXml.format(getProductManager().list(Long.parseLong(categoryId)));
					response.setContentType("application/xml; charset=UTF-8");
					XmlHelper.dump(document, response.getOutputStream());
				} catch (NumberFormatException e) {
					e.printStackTrace();
					Response.sendResponse(response, HttpServletResponse.SC_BAD_REQUEST, "Invalid parameter type");
				} catch (ParserConfigurationException e) {
					e.printStackTrace();
					Response.sendResponse(response, HttpServletResponse.SC_BAD_REQUEST, e.getMessage());
				} catch (SQLException e) {
					e.printStackTrace();
					Response.sendResponse(response, HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
							"Something went wrong!");
				} catch (TransformerException e) {
					e.printStackTrace();
					Response.sendResponse(response, HttpServletResponse.SC_BAD_REQUEST, e.getMessage());
				}

			} else {
				Response.sendResponse(response, HttpServletResponse.SC_BAD_REQUEST, "categoryId parameter is needed");
			}
		} else if (servletPath.equals("/api/product")) {

			String productId = request.getParameter("productId");
			if (productId != null) {
				try {
					Product product = getProductManager().find(Long.parseLong(productId));
					if (product != null) {
						Document document = ProductXml.format(product);
						response.setContentType("application/xml; charset=UTF-8");
						XmlHelper.dump(document, response.getOutputStream());
					} else {
						Response.sendResponse(response, HttpServletResponse.SC_NOT_FOUND, "Product not found");
					}

				} catch (NumberFormatException e) {
					e.printStackTrace();
					Response.sendResponse(response, HttpServletResponse.SC_BAD_REQUEST, "Invalid parameter type");
				} catch (ParserConfigurationException e) {
					e.printStackTrace();
				} catch (SQLException e) {
					e.printStackTrace();
					Response.sendResponse(response, HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
							"Something went wrong!");
				} catch (TransformerException e) {
					e.printStackTrace();
				}
			} else {
				Response.sendResponse(response, HttpServletResponse.SC_BAD_REQUEST, "productId parameter is needed");
			}

		}

	}
}
