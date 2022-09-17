<%@page import="com.finalproject.ecommerce.response.Response"%>
<%@page import="com.finalproject.ecommerce.response.ResponseXml"%>
<%@page import="com.finalproject.ecommerce.client.Client"%>
<%@page import="java.io.InputStream"%>
<%@page import="com.finalproject.ecommerce.utils.StreamUtils"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
String userName="";
String password="";
String message = "";
if(request.getParameter("btn-register")!=null){
	userName = request.getParameter("username");
	password = request.getParameter("password");
	if (userName != null && !userName.equals("") && password != null && !password.equals("")) {
		if (password.length() > 6) {
			String address = "http://localhost:8080/project-backend/api/user/create";
			InputStream in = StreamUtils.post(address, "<User><UserName>" + userName + "</UserName><Password>" + password + "</Password></User>");
			Response customResponse = ResponseXml.parseResponse(in);
			if(customResponse.getMessage()==""){
				response.sendRedirect("Login.jsp");
			}else{
				message = customResponse.getMessage();
			}
		}
		else{
			message="Password should be greater than 6 characters";
		}
	}
	else{
		message="fill all required fields";
	}
}

%>
<jsp:include page="../WEB-INF/template/head.jsp">
	<jsp:param name="title" value="Register" />
</jsp:include>
<div class="container">
	<jsp:include page="../WEB-INF/template/header.jsp" />
	<form class="form-signin" style="width: 50%; margin: 0 auto;"
		method="POST">
		<h1 class="h3 mb-3 font-weight-normal">Register</h1>
		<label for="inputUserName" class="sr-only">Username</label> <input
			type="text" name="username" id="inputUserName" class="form-control"
			placeholder="Username" value="<%=userName%>" required autofocus> <label
			for="inputPassword" class="sr-only">Password</label> <input
			type="password" name="password" id="inputPassword"
			class="form-control" placeholder="Password" value="<%=password%>" required>
			<div style="color: red"><%=message%></div>
		<button class="btn btn-lg btn-primary btn-block" name="btn-register" type="submit">Register</button>
	</form>
</div>
<jsp:include page="../WEB-INF/template/footer.jsp" />