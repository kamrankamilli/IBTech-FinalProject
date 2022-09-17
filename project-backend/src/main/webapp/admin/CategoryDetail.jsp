<%@page import="com.finalproject.ecommerce.entity.inventory.Category"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
	import="com.finalproject.ecommerce.manager.CategoryManager"%>
<%
String categoryId = request.getParameter("categoryId");
String categoryName = "";
String message = "";

if(categoryId == null || "".equals(categoryId)){
	response.sendError(404);
}
else{
	CategoryManager categoryManager = new CategoryManager();
	Category category = categoryManager.find(Long.parseLong(categoryId));
	if(category!=null){
		categoryName=category.getCategoryName();
	}else{
		response.sendError(404);
	}
	if(request.getParameter("btn-submit")!=null){
		categoryName = request.getParameter("categoryName");
		if(categoryName!=null && !categoryName.equals("")){
			category = new Category(Long.parseLong(categoryId),categoryName);
			boolean isUpdated = categoryManager.update(category);
			if(isUpdated){
				response.sendRedirect("CategorySummary.jsp");
			}else{
				message="Cannot update";
			}
		}else{
			message="Category name cannot be empty";
		}
		
	}
}

%>
<jsp:include page="../WEB-INF/template/head.jsp">
	<jsp:param name="title" value="Category Detail" />
</jsp:include>
<div class="container">
	<jsp:include page="../WEB-INF/template/header.jsp" />
	<h3>Edit Category</h3>
	<form action="" method="POST" class="mt-5">
		<div class="form-group col-md-6">
			<label for="categoryName">Category name</label> <input type="text"
				class="form-control" id="categoryName" name="categoryName"
				placeholder="Enter a category name" value="<%=categoryName%>">
			<span style="color: red"><%=message%></span>
		</div>
		<button type="submit" class="btn btn-primary mt-2" name="btn-submit">Submit</button>
	</form>
</div>
<jsp:include page="../WEB-INF/template/footer.jsp" />