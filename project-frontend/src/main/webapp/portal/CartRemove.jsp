<%@page import="com.finalproject.ecommerce.utils.StreamUtils"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% if(session.getAttribute("cartId")!=null){
	String cartId = session.getAttribute("cartId").toString();
	String productId = (String)request.getParameter("productId").toString();
	StreamUtils.get("http://localhost:8080/project-backend/api/cart/remove?cartId="+cartId+"&productId="+productId);
	response.sendRedirect("CartView.jsp");
}
%>