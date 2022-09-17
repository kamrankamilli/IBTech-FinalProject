<%@page import="com.finalproject.ecommerce.entity.Cart"%>
<%@page import="com.finalproject.ecommerce.utils.StreamUtils"%>
<%@page import="com.finalproject.ecommerce.entity.Product"%>
<%@page import="java.io.InputStream"%>

<%@page import="com.finalproject.ecommerce.client.Client"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%

String productId = request.getParameter("productId");
Product product = null;
String username = "Guest";
if (productId != null && !productId.equals("")) {
	product = Client.getProduct("http://localhost:8080/project-backend/api/product?productId=" + productId);
	if (product == null) {
		response.sendError(404);
	}
} else {
	response.sendError(404);
}

if(request.getParameter("add-to-cart")!=null){
	if(session.getAttribute("cartId")==null){
		String address = "http://localhost:8080/project-backend/api/cart/create";
		if(session.getAttribute("username")!=null){
			username = (String) session.getAttribute("username");
		}
		Cart cart = Client.postCart(address, username);
		session.setAttribute("cartId", cart.getCartId());
	}
	long cartId =(long)session.getAttribute("cartId"); 
	StreamUtils.post("http://localhost:8080/project-backend/api/cart/add", "<Cart id='"+cartId+"'><Product id='"+product.getProductId()+"'><Quantity>1</Quantity></Product></Cart>");
	
}
%>
<jsp:include page="../WEB-INF/template/head.jsp">
	<jsp:param name="title" value="Product Details" />
</jsp:include>
<div class="container">
	<jsp:include page="../WEB-INF/template/header.jsp" />
	<div class="card">
		<div class="container-fliud">
			<div class="wrapper row">
				<div class="preview col-md-6">
					<div class="preview-pic tab-content">
						<div class="tab-pane active" id="pic-1">
							<img
								src="http://localhost:8080/project-backend/api/images?productId=<%=product.getProductId()%>" />
						</div>
					</div>
				</div>
				<div class="details col-md-6">
					<h3 class="product-title"><%=product.getProductName()%></h3>
					<p class="product-description"><%=product.getDescription()%></p>
					<h4 class="price">
						current price: <span><%=product.getSalesPrice()%>&#8378;</span>
					</h4>
					<form method="POST">
						<div class="action">
							<button type="submit" name="add-to-cart" class="btn btn-primary">Add to cart</button>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
</div>
<jsp:include page="../WEB-INF/template/footer.jsp" />