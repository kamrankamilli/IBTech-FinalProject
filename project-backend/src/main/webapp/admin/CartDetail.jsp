<%@page import="com.finalproject.ecommerce.entity.inventory.Product"%>
<%@page import="com.finalproject.ecommerce.manager.ProductManager"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="com.finalproject.ecommerce.manager.CartProductManager"%>
<%@page import="com.finalproject.ecommerce.entity.shopping.CartProduct"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
String cartId = request.getParameter("cartId");
List<CartProduct> cartProductList = new ArrayList<>();
if (cartId != null && !cartId.equals("")) {
	CartProductManager cartProductManager = new CartProductManager();
	cartProductList = cartProductManager.getCartItems(Long.parseLong(cartId));
}
%>
<jsp:include page="../WEB-INF/template/head.jsp">
	<jsp:param name="title" value="Cart Detail" />
</jsp:include>
<div class="container">
	<jsp:include page="../WEB-INF/template/header.jsp" />
	<ul class="list-group">

	</ul>
	<table class="table">
		<thead>
			<tr>
				<th scope="col"></th>
				<th scope="col">Product Name</th>
				<th scope="col">Sale Price</th>
				<th scope="col">Quantity</th>
			</tr>
		</thead>
		<tbody>
			<%
			for (CartProduct cartProduct : cartProductList) {
				ProductManager productManager = new ProductManager();
				Product product = productManager.find(cartProduct.getProducId());
			%>
			<tr>
				<td><img style="width: 50px; height: 50px;"
					src="http://localhost:8080/project-backend/api/images?productId=<%=product.getProductId()%>"></td>
				<td><%=product.getProductName()%></td>
				<td><%=product.getSalesPrice()%>&#8378;</td>
				<td><%=cartProduct.getSaleQuantity()%></td>
			</tr>
			<%
			}
			%>
		</tbody>
	</table>
</div>
<jsp:include page="../WEB-INF/template/footer.jsp" />