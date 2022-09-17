<%@page import="com.finalproject.ecommerce.manager.ProductManager"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String productId = request.getParameter("productId");
if (productId != null && !productId.equals("")) {
	ProductManager productManager = new ProductManager();
	boolean isDeleted = productManager.delete(Long.parseLong(productId));
	if(isDeleted){
		response.sendRedirect("ProductSummary.jsp");
	}
}
%>