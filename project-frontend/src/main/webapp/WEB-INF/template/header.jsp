
<%@page import="com.finalproject.ecommerce.entity.Category"%>
<%@page import="java.util.List"%>
<%@page import="com.finalproject.ecommerce.client.Client"%>
<%
List<Category> categoryList = Client.getCategories("http://localhost:8080/project-backend/api/categories");
%>
<header
	class="d-flex flex-wrap justify-content-center py-3 mb-4 border-bottom">
	<%
	if (session.getAttribute("username") != null) {
	%>
	<a href="/project-frontend/portal/MainPage.jsp"
		class="d-flex align-items-center mb-3 mb-md-0 me-md-auto text-dark text-decoration-none">
		<svg class="bi me-2" width="40" height="32">
				<use xlink:href="#bootstrap"></use></svg> <span class="fs-4">Welcome
			<%=session.getAttribute("username")%></span>
	</a>
	<%
	} else {
	%>
	<a href="/project-frontend/portal/MainPage.jsp"
		class="d-flex align-items-center mb-3 mb-md-0 me-md-auto text-dark text-decoration-none">
		<svg class="bi me-2" width="40" height="32">
						<use xlink:href="#bootstrap"></use></svg> <span class="fs-4">E-Commerce</span>
	</a>
	<%
	}
	%>
	<nav>
		<ul class="nav nav-pills">
			<%
			for (Category category : categoryList) {
				String categoryName = category.getCategoryName();
			%>

			<li class="nav-item "><a
				href="ProductList.jsp?categoryId=<%=category.getCategoryId()%>"
				class="nav-link <%=(request.getQueryString() != null && request.getQueryString().equals("categoryId=" + category.getCategoryId())
		? "active"
		: "")%>"
				aria-current="page"><%=category.getCategoryName()%></a></li>
			<%
			}
			if (session.getAttribute("username") == null) {
			%>
			<li class="nav-item"><a href="Register.jsp"
				class="nav-link${pageContext.request.requestURI eq '/project-frontend/portal/Register.jsp' ? ' active' : ''}">Register</a></li>
			<li class="nav-item"><a href="Login.jsp"
				class="nav-link${pageContext.request.requestURI eq '/project-frontend/portal/Login.jsp' ? ' active' : ''}">Login</a></li>
			<%}else{%>
			<li class="nav-item"><a href="Logout.jsp"
				class="nav-link">Logout</a></li>
			<%}%>
			<li class="nav-item"><a href="CartView.jsp" class="nav-link"><i
					class="fa fa-light fa-cart-shopping"></i></a></li>

		</ul>
	</nav>
</header>