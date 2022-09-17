<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
if (session.getAttribute("username") == null) {
	response.sendRedirect("Login.jsp");
}
%>
<jsp:include page="../WEB-INF/template/head.jsp">
	<jsp:param name="title" value="Welcome Page" />
</jsp:include>
<div class="container">
<jsp:include page="../WEB-INF/template/header.jsp"/>

</div>
<jsp:include page="../WEB-INF/template/footer.jsp" />
