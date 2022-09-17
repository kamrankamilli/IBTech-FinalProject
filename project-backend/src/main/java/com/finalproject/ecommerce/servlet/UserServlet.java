package com.finalproject.ecommerce.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import com.finalproject.ecommerce.entity.auth.User;
import com.finalproject.ecommerce.manager.UserManager;
import com.finalproject.ecommerce.response.Response;
import com.finalproject.ecommerce.xml.UserXml;

@WebServlet(name = "UserServlet", urlPatterns = { "/api/user/create", "/api/user/check" })
public class UserServlet extends InitServlet {

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("application/xml; charset=UTF-8");
		String servletPath = request.getServletPath();
		if (servletPath.equals("/api/user/create")) {
			try {
				User user = UserXml.parseXml(request.getInputStream());
				UserManager userManager = getUserManager();
				User existingUser = userManager.find(user.getUserName());
				if (existingUser == null) {
					boolean isCreated = userManager.createUser(user);
					if (isCreated) {
						Response.sendResponse(response, 201, "");
					} else {
						Response.sendResponse(response, 500, "Could not register user");
					}
				} else {
					Response.sendResponse(response, 200, "Username already exists");
				}
			} catch (ParserConfigurationException e) {
				e.printStackTrace();
			} catch (SAXException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else if (servletPath.equals("/api/user/check")) {
			try {
				User user = UserXml.parseXml(request.getInputStream());
				UserManager userManager = getUserManager();
				User existingUser = userManager.find(user.getUserName());
				if (existingUser != null) {
					if(existingUser.getUserPassword().equals(user.getUserPassword())) {
						Response.sendResponse(response, 200, "");
					}
					else {
						Response.sendResponse(response, 200, "Wrong credentials");
					}

				}
				else {
					Response.sendResponse(response, 200, "Username does not exists");
				}
			} catch (ParserConfigurationException | SAXException | IOException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}
}
