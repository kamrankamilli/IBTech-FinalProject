package com.finalproject.ecommerce.servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.SQLException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.finalproject.ecommerce.entity.inventory.Product;
import com.finalproject.ecommerce.manager.ProductManager;
import com.finalproject.ecommerce.response.Response;

@WebServlet("/api/images")
public class ImageServlet extends InitServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String productId = request.getParameter("productId");
		if (productId != null && !productId.equals("")) {
			ProductManager productManager = new ProductManager();
			try {
				Product product = productManager.find(Long.parseLong(productId));
				ServletContext cntx = request.getServletContext();
				// Get the absolute path of the image
				String filename = cntx.getRealPath("images/" + product.getImagePath());
				// retrieve mimeType dynamically
				String mime = cntx.getMimeType(filename);
				if (mime == null) {
					response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
					return;
				}

				response.setContentType(mime);
				File file = new File(filename);
				response.setContentLength((int) file.length());

				FileInputStream in = new FileInputStream(file);
				OutputStream out = response.getOutputStream();

				// Copy the contents of the file to the output stream
				byte[] buf = new byte[1024];
				int count = 0;
				while ((count = in.read(buf)) >= 0) {
					out.write(buf, 0, count);
				}
				out.close();
				in.close();
			} catch (NumberFormatException e) {
				e.printStackTrace();
				Response.sendResponse(response, 400, "Numeric value needed!");
			}catch (SQLException e) {
				e.printStackTrace();
				Response.sendResponse(response, 500, "Something went wrong!");
			}
		}
		else {
			Response.sendResponse(response, 400, "product_id parameter needed!");
		}

	}

}
