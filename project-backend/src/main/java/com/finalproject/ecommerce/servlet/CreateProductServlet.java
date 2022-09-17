package com.finalproject.ecommerce.servlet;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.finalproject.ecommerce.entity.inventory.Category;
import com.finalproject.ecommerce.entity.inventory.Product;
import com.finalproject.ecommerce.utils.FileUploader;

@WebServlet("/create-product")
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 1024 * 1024 * 5, maxRequestSize = 1024 * 1024 * 5 * 5)
public class CreateProductServlet extends InitServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String productName = request.getParameter("productName");
		String description = request.getParameter("description");
		String salePrice = request.getParameter("salePrice");
		String categoryId = request.getParameter("category");
		Part filePart = request.getPart("file");

		if (productName != null && !productName.equals("") && salePrice != null && !salePrice.equals("")
				&& filePart != null && categoryId != null && !categoryId.equals("")) {
			File file = FileUploader.uploadFile("images", filePart, request);
			if (file != null) {
				Category category = new Category(Long.parseLong(categoryId),"");
				Product product = new Product(0, productName, description, Double.parseDouble(salePrice),
						file.getName());
				product.setCategory(category);
				try {
					if(request.getParameter("btn-add")!=null) {
						boolean isInserted = getProductManager().insert(product);
						response.sendRedirect(request.getContextPath() + "/admin/ProductSummary.jsp");
						if (!isInserted) {
							request.setAttribute("message", "Product cannot be inserted!");
						}
					}else if(request.getParameter("btn-edit")!=null) {
						long productId=Long.parseLong(request.getParameter("productId"));
						product.setProductId(productId);
						boolean isUpdated = getProductManager().update(product);
						response.sendRedirect(request.getContextPath() + "/admin/ProductSummary.jsp");
						if (!isUpdated) {
							request.setAttribute("message", "Product cannot be updated!");
						}
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			} else {
				response.sendRedirect(request.getContextPath() + "/admin/ProductSummary.jsp");
				request.setAttribute("message", "Could not import image");
				
			}

		} else {
			response.sendRedirect(request.getContextPath() + "/admin/ProductSummary.jsp");
			request.setAttribute("message", "Fill all requered fields");
			
		}

	}
}
