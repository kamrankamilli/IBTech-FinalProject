<%@page import="com.finalproject.ecommerce.entity.Product"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.finalproject.ecommerce.entity.CartProduct"%>
<%@page import="org.w3c.dom.Document"%>
<%@page import="com.finalproject.ecommerce.utils.StreamUtils"%>
<%@page import="com.finalproject.ecommerce.entity.Cart"%>
<%@page import="com.finalproject.ecommerce.client.Client"%>
<%@page import="java.io.InputStream"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
Cart cart = null;
String username="Guest";
List<CartProduct> cartProductList = new ArrayList<>();
Product product = null;

if (session.getAttribute("cartId") != null) {
	long cartId = (long) session.getAttribute("cartId");
	String address = "http://localhost:8080/project-backend/api/cart/view?cartId=" + cartId;
	Document document = Client.getCart(address,cartId);
	cart = Client.parseCartXml(document);
	cartProductList = Client.parseCartProductXml(document);
} else {
	String address = "http://localhost:8080/project-backend/api/cart/create";
	if(session.getAttribute("username")!=null){
		username=(String)session.getAttribute("username");
	}
	cart = Client.postCart(address, username);
	session.setAttribute("cartId", cart.getCartId());
}

%>
<jsp:include page="../WEB-INF/template/head.jsp">
	<jsp:param name="title" value="Cart View" />
</jsp:include>
<div class="container">
	<jsp:include page="../WEB-INF/template/header.jsp" />
	<section class="h-100 h-custom" style="background-color: blue;">
		<div class="container py-5 h-100">
			<div
				class="row d-flex justify-content-center align-items-center h-100">
				<div class="col-12">
					<div class="card card-registration card-registration-2"
						style="border-radius: 15px;">
						<div class="card-body p-0">
							<div class="row g-0">
								<div class="col-lg-8">
									<div class="p-5">
										<div
											class="d-flex justify-content-between align-items-center mb-5">
											<h1 class="fw-bold mb-0 text-black">Shopping Cart</h1>
											<h6 class="mb-0 text-muted"><%=cartProductList.size()%>
												items
											</h6>
										</div>
										<hr class="my-4">
										<%
										for (CartProduct cartProduct : cartProductList) {
											
											product = Client.getProduct(
											"http://localhost:8080/project-backend/api/product?productId=" + cartProduct.getProducId());
											
										%>
										<div class="row mb-4 d-flex justify-content-between
										align-items-center">
										<div class="col-md-2 col-lg-2 col-xl-2">
											<img
												src="http://localhost:8080/project-backend/api/images?productId=<%=product.getProductId()%>"
												class="img-fluid rounded-3">
										</div>
										<div class="col-md-3 col-lg-3 col-xl-3">
											<a style="text-decoration: none;" href="ProductView.jsp?productId=<%=product.getProductId()%>"><h6 class="text-black mb-0"><%=product.getProductName()%></h6></a>
										</div>
										<div class="col-md-3 col-lg-3 col-xl-2 d-flex">
										<form method="POST" class="d-flex align-items-center" >
											<div>x<%=cartProduct.getSaleQuantity()%></div>
										</form>
										</div> 
										<div class="col-md-3 col-lg-2 col-xl-2 offset-lg-1">
											<h6 class="mb-0"><%=product.getSalesPrice()%></h6>
										</div>
										<div class="col-md-1 col-lg-1 col-xl-1 text-end">
											<a
												href="CartRemove.jsp?productId=<%=product.getProductId()%>"
												class="text-muted"><i class="fas fa-times"></i></a>
										</div>
									</div>
									<%
									}
									%>

									<hr class="my-4">

									<div class="pt-5">
										<h6 class="mb-0">
											<a href="MainPage.jsp" class="text-body"><i
												class="fas fa-long-arrow-alt-left me-2"></i>Back to shop</a>
										</h6>
									</div>
								</div>
							</div>
							<div class="col-lg-4 bg-grey">
								<div class="p-5">
									<h3 class="fw-bold mb-5 mt-2 pt-1">Summary</h3>
									<div class="d-flex justify-content-between mb-5">
										<h5 class="text-uppercase">Total price</h5>
										<h5>
											€
											<%=cart.getTotalAmount()%></h5>
									</div>
									<a class="btn btn-dark btn-block btn-lg"
										href="CartCheckout.jsp">Checkout</a>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
</div>
</section>

</div>
<jsp:include page="../WEB-INF/template/footer.jsp" />