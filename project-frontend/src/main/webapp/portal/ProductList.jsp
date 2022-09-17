<%@page import="com.finalproject.ecommerce.entity.Product"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.finalproject.ecommerce.client.Client"%>

<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
String categoryId = request.getParameter("categoryId");
List<Product> productList = new ArrayList<>();
if (categoryId != null && !categoryId.equals("")) {
	productList = Client.getProducts("http://localhost:8080/project-backend/api/products?categoryId=" + categoryId);
} else {
	response.sendError(404);
}
%>
<jsp:include page="../WEB-INF/template/head.jsp">
	<jsp:param name="title" value="Main Page" />
</jsp:include>

<div class="container" style="display: flex; flex-direction: column;">
	<jsp:include page="../WEB-INF/template/header.jsp" />
	<div style="display:flex;" class="cards">
	<%
	for (Product product : productList) {
	%>
	

	<div class="card" style="width: 20rem; margin-right: 20px;">
		<a style="text-decoration: none; color: black;"
			href="ProductView.jsp?productId=<%=product.getProductId()%>"> <img
			src="http://localhost:8080/project-backend/api/images?productId=<%=product.getProductId()%>"
			class="card-img-top" alt="">
			<div class="card-body">
				<h5 class="card-title"><%=product.getProductName()%></h5>
				<p class="card-text"><%=product.getSalesPrice()%>&#8378;</p>
			</div>
		</a>
	</div>

	<%
	}
	%>
	</div>
</div>
<jsp:include page="../WEB-INF/template/footer.jsp" />
