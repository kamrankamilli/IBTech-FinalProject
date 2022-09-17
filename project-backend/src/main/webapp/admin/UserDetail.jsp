<%@page import="com.finalproject.ecommerce.entity.auth.User"%>
<%@page import="com.finalproject.ecommerce.manager.UserManager"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String userId = request.getParameter("userId");
User user = null;
if(userId!=null && !userId.equals("")){
	UserManager userManager = new UserManager();
	user = userManager.find(Long.parseLong(userId));
}else{
	response.sendError(404);
}
%>
<jsp:include page="../WEB-INF/template/head.jsp">
	<jsp:param name="title" value="User Detail" />
</jsp:include>
<div class="container">
	<jsp:include page="../WEB-INF/template/header.jsp" />
	<h2>User Details</h2>
	<h3>Username: <%=user.getUserName()%></h3>
</div>
<jsp:include page="../WEB-INF/template/footer.jsp" />