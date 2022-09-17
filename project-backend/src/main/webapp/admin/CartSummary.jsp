<%@page import="com.finalproject.ecommerce.entity.shopping.Cart"%>
<%@page import="java.util.List"%>
<%@page import="com.finalproject.ecommerce.manager.CartManager"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
CartManager cartManager = new CartManager();
List<Cart> cartList = cartManager.list();
%>
<jsp:include page="../WEB-INF/template/head.jsp">
	<jsp:param name="title" value="Cart Summary" />
</jsp:include>
<div class="container">
	<jsp:include page="../WEB-INF/template/header.jsp" />
	<ul class="list-group">
		<%
		for (Cart cart : cartList) {
		%>
		<li class="list-group-item"><a
			href="CartDetail.jsp?cartId=<%=cart.getCartId()%>"><%=cart.getCustomerName()%></a>
		<div style="float: right;">Total Amount: <%=cart.getTotalAmount()%></div></li>
		<%
		}
		%>
	</ul>
</div>
<jsp:include page="../WEB-INF/template/footer.jsp" />