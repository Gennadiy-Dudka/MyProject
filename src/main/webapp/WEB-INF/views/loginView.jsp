<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>MyProject ImageBoard</title>
<link href="${pageContext.request.contextPath}/resources/mainStyle.css" rel="stylesheet" type="text/css">
<link href="https://fonts.googleapis.com/css2?family=Source+Code+Pro:ital,wght@1,500&display=swap" rel="stylesheet">
</head>
<body>
	<jsp:include page="_header.jsp"></jsp:include>
	<div class="big-block">
		<h3>Log in Page</h3>
		<p class="errorMessage">${errorString}</p>
		<form method="POST" action="${pageContext.request.contextPath}/logIn">
			<table border=0>
				<tr>
					<td>User Name</td>
					<td><input type="text" name="userName" value="${user.userName}"/> </td>
				</tr>
				<tr>
					<td>Password</td>
					<td><input type="password" name="password" value="${user.password}"/> </td>
				</tr>
				<tr>
					<td colspan="2">
						<input type="submit" value = "Submit"/>
						<a href="${pageContext.request.contextPath}/">Cancel</a>
					</td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>