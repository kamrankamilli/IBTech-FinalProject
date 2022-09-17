<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
	import="com.finalproject.ecommerce.manager.CategoryManager"%>
<%
String categoryId = request.getParameter("categoryId");
if (categoryId != null && !categoryId.equals("")) {
	CategoryManager categoryManager = new CategoryManager();
	boolean isDeleted = categoryManager.delete(Long.parseLong(categoryId));
	if(isDeleted){
		response.sendRedirect("CategorySummary.jsp");
	}
}
%>