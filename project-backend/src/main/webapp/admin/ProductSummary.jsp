<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
	import="com.finalproject.ecommerce.manager.ProductManager,com.finalproject.ecommerce.manager.CategoryManager,com.finalproject.ecommerce.entity.inventory.Product,com.finalproject.ecommerce.entity.inventory.Category, java.util.List,java.io.File"%>
<%
ProductManager productManager = new ProductManager();
CategoryManager categoryManager = new CategoryManager();
List<Category> categoryList = categoryManager.list();
List<Product> productList = productManager.list();
String message = "";

%>
<jsp:include page="../WEB-INF/template/head.jsp">
	<jsp:param name="title" value="Product Summary" />
</jsp:include>
<div class="container">
	<jsp:include page="../WEB-INF/template/header.jsp" />
	<table class="table">
		<thead>
			<tr>
				<th scope="col"></th>
				<th scope="col"></th>
				<th scope="col">Product Name</th>
				<th scope="col">Category</th>
				<th scope="col">Sale Price</th>
				<th scope="col"></th>
			</tr>
		</thead>
		<tbody>
			<%
			for (int i = 0; i < productList.size(); i++) {
				Product product = productList.get(i);
			%>
			<tr>
				<th scope="row"><%=i + 1%></th>
				<td><img style="width:50px;height:50px;"src="http://localhost:8080/project-backend/api/images?productId=<%=product.getProductId() %>"></td>
				<td><%=product.getProductName()%></td>
				<td><%=product.getCategory().getCategoryName()%></td>
				<td><%=product.getSalesPrice()%>&#8378;</td>
				<td>
				<a
					href="ProductDetail.jsp?productId=<%=product.getProductId()%>">Edit</a><a style="float:right;"
					href="ProductDelete.jsp?productId=<%=product.getProductId()%>"><i
						class="fa-solid fa-trash"></i></a></td>
			</tr>
			<%
			}
			%>
		</tbody>
	</table>
	<form action="../create-product" method="post"
		enctype="multipart/form-data">
		<div class="form-group col-md-6">
			<div class="form-floating mb-3">
				<input type="text" class="form-control" id="productName"
					name="productName" placeholder="Enter product name" value="">
				<label class="form-label" for="productName">Product Name</label>
			</div>
			<div class="form-floating mb-3">
				<textarea class="form-control" name="description"
					placeholder="Write a description" id="description"
					style="height: 100px"></textarea>
				<label for="description">Description</label>
			</div>
			<div class="form-floating mb-3">
				<input type="number" class="form-control" id="salePrice"
					name="salePrice" step="any" placeholder="Enter product sale price"
					value=""> <label class="form-label" for="salePrice">Sale
					Price</label>
			</div>
			<div class="input-group mb-3">
				<label class="input-group-text" for="inputGroupFile">Upload
					image</label> <input type="file" class="form-control" name="file"
					id="inputGroupFile">
			</div>
			<span style="color: red"></span>

			<div class="form-floating">
				<select class="form-select" id="category" name="category">
					<option selected disabled>Categories</option>
					<%
					for (Category category : categoryList) {
					%>
					<option value="<%=category.getCategoryId()%>"><%=category.getCategoryName()%></option>
					<%
					}
					%>
				</select> <label for="floatingSelect">Select a category</label>
			</div>
			<span style="color: red"><%=message%></span>
		</div>
		<button type="submit" class="btn btn-primary mt-2" name="btn-add">Add
			a product</button>
	</form>
</div>
<jsp:include page="../WEB-INF/template/footer.jsp" />