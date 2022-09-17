<%@page import="com.finalproject.ecommerce.entity.auth.User"%>
<%@page import="java.util.List"%>
<%@page import="com.finalproject.ecommerce.manager.UserManager"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
UserManager userManager = new UserManager();
List<User> userList = userManager.list();
%>
<jsp:include page="../WEB-INF/template/head.jsp">
	<jsp:param name="title" value="User Summary" />
</jsp:include>
<div class="container">
	<jsp:include page="../WEB-INF/template/header.jsp" />
	<ul class="list-group">
		<%
		for (User user : userList) {
		%>
		<li class="list-group-item"><a href="UserDetail.jsp?userId=<%=user.getUserId()%>"><%=user.getUserName()%></a></li>
		<%
		}
		%>
	</ul>
</div>
<jsp:include page="../WEB-INF/template/footer.jsp" />