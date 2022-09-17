<%@page import="java.util.List"%>
<%@page import="com.finalproject.ecommerce.entity.inventory.Category"%>
<%@page import="com.finalproject.ecommerce.manager.CategoryManager"%>
<%@page import="com.finalproject.ecommerce.entity.inventory.Product"%>
<%@page import="java.sql.SQLException"%>
<%@page import="com.finalproject.ecommerce.manager.ProductManager"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
String productId = request.getParameter("productId");
ProductManager productManager = new ProductManager();
Product product = null;
String message = "";
CategoryManager categoryManager = new CategoryManager();
List<Category> categoryList = categoryManager.list();
if (productId != null && !productId.equals("")) {
	try {
		product = productManager.find(Long.parseLong(productId));
	} catch (NumberFormatException e) {
		e.printStackTrace();
		response.sendError(404);
	} catch (SQLException e) {
		response.sendError(500);
	}

}
if(request.getParameter("btn-edit")!=null){
	
	productManager.update(product);
}
%>
<jsp:include page="../WEB-INF/template/head.jsp">
	<jsp:param name="title" value="Product Detail" />
</jsp:include>
<div class="container">
	<jsp:include page="../WEB-INF/template/header.jsp" />
	<form action="../create-product" method="post"
		enctype="multipart/form-data">
		<div class="form-group col-md-6">
		<div class="form-floating mb-3">
				<input type="hidden" class="form-control"
					name="productId" 
					value="<%=product.getProductId()%>">
			</div>
			<div class="form-floating mb-3">
				<input type="text" class="form-control" id="productName"
					name="productName" placeholder="Enter product name"
					value="<%=product.getProductName()%>"> <label
					class="form-label" for="productName">Product Name</label>
			</div>
			<div class="form-floating mb-3">
				<textarea class="form-control" name="description"
					placeholder="Write a description" id="description"
					style="height: 100px" ><%=product.getDescription()%></textarea>
				<label for="description">Description</label>
			</div>
			<div class="form-floating mb-3">
				<input type="number" class="form-control" id="salePrice"
					name="salePrice" step="any" placeholder="Enter product sale price"
					value="<%=product.getSalesPrice()%>"> <label
					class="form-label" for="salePrice">Sale Price</label>
			</div>
			<div class="input-group mb-3">
				<label class="input-group-text" for="inputGroupFile">Upload
					image</label> <input type="file" class="form-control" name="file"
					id="inputGroupFile" value=<%=product.getImagePath()%>>
			</div>
			<span style="color: red"></span>

			<div class="form-floating">
				<select class="form-select" id="category" name="category">
					<option selected disabled>Categories</option>
					<%
					for (Category category : categoryList) {
					%>
					<option value="<%=category.getCategoryId()%>"  <%if((category.getCategoryId())==product.getCategory().getCategoryId()){%> selected <%}%>><%=category.getCategoryName()%></option>
					<%
					}
					%>
				</select> <label for="floatingSelect">Select a category</label>
			</div>
			<span style="color: red"><%=message%></span>
		</div>
		<button type="submit" class="btn btn-primary mt-2" name="btn-edit">Edit
			a product</button>
	</form>

</div>
<jsp:include page="../WEB-INF/template/footer.jsp" />