
<header
	class="d-flex flex-wrap justify-content-center py-3 mb-4 border-bottom">
	<a href="/"
		class="d-flex align-items-center mb-3 mb-md-0 me-md-auto text-dark text-decoration-none">
		<svg class="bi me-2" width="40" height="32">
				<use xlink:href="#bootstrap"></use></svg> <span class="fs-4">Welcome
			admin</span>
	</a>
	<nav>
		<ul class="nav nav-pills">
			<li class="nav-item"><a href="AdminPage.jsp"
				class="nav-link${pageContext.request.requestURI eq '/project-backend/admin/AdminPage.jsp' ? ' active' : ''}"
				aria-current="page">Home</a></li>
			<li class="nav-item"><a href="CategorySummary.jsp"
				class="nav-link${pageContext.request.requestURI eq '/project-backend/admin/CategorySummary.jsp' ? ' active' : ''}">Category
					Summary</a></li>
			<li class="nav-item"><a href="ProductSummary.jsp"
				class="nav-link${pageContext.request.requestURI eq '/project-backend/admin/ProductSummary.jsp' ? ' active' : ''}">Product
					Summary</a></li>
			<li class="nav-item"><a href="UserSummary.jsp"
				class="nav-link${pageContext.request.requestURI eq '/project-backend/admin/UserSummary.jsp' ? ' active' : ''}">User
					Summary</a></li>
			<li class="nav-item"><a href="CartSummary.jsp"
				class="nav-link${pageContext.request.requestURI eq '/project-backend/admin/CartSummary.jsp' ? ' active' : ''}">Cart
					Summary</a></li>
		</ul>
	</nav>
</header>