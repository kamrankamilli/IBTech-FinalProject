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

import com.finalproject.ecommerce.manager.CartManager;
import com.finalproject.ecommerce.manager.CategoryManager;
import com.finalproject.ecommerce.response.Response;
import com.finalproject.ecommerce.utils.XmlHelper;
import com.finalproject.ecommerce.xml.CategoryXml;

@WebServlet("/api/categories")
public class CategoryServlet extends InitServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			Document document = CategoryXml.format(getCategoryManager().list());
			response.setContentType("application/xml; charset=UTF-8");
			XmlHelper.dump(document, response.getOutputStream());
		} catch (ParserConfigurationException | SQLException | TransformerException e) {
			e.printStackTrace();
			Response.sendResponse(response, HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Something went wrong!");
		}
	}
}
