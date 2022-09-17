<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="com.finalproject.ecommerce.manager.CategoryManager"%>
<%@page import="java.util.List"%>
<%@page import="com.finalproject.ecommerce.entity.inventory.Category"%>

<%
request.setCharacterEncoding("UTF-8");
CategoryManager categoryManager = new CategoryManager();
List<Category> categoryList = categoryManager.list();
String categoryName = "";
String message = "";
if (request.getParameter("btn-add") != null) {
	categoryName = request.getParameter("categoryName");
	if (categoryName != null && !categoryName.equals("")) {
		System.out.println(categoryName);
		Category category = new Category(0, categoryName);
		boolean isInserted = categoryManager.insert(category);
		if (isInserted) {
			response.sendRedirect("CategorySummary.jsp");
		} else {
			message = "Category cannot be inserted!";
		}
	} else {
		message = "Category name cannot be empty!";
	}

}
%>
<jsp:include page="../WEB-INF/template/head.jsp">
	<jsp:param name="title" value="Category Summary" />
</jsp:include>
<div class="container">
	<jsp:include page="../WEB-INF/template/header.jsp" />
	<ul class="list-group">
		<%
		for (Category category : categoryList) {
		%>
		<li class="list-group-item"><a
			href="CategoryDetail.jsp?categoryId=<%=category.getCategoryId()%>"><%=category.getCategoryName()%></a>
			<a style="float: right;"
			href="CategoryDelete.jsp?categoryId=<%=category.getCategoryId()%>"><i
				class="fa-solid fa-trash"></i></a></li>
		<%
		}
		%>
	</ul>
	<form action="CategorySummary.jsp" method="POST" class="mt-5">
		<div class="form-group col-md-6">
			<input type="text" class="form-control" id="categoryName"
				name="categoryName" placeholder="Enter a category name"
				value=<%=categoryName%>> <span style="color: red"><%=message%></span>
		</div>
		<button type="submit" class="btn btn-primary mt-2" name="btn-add">Add
			a category</button>
	</form>
</div>
<jsp:include page="../WEB-INF/template/footer.jsp" />