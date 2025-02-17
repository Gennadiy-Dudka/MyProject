<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>MyProject ImageBoard</title>
	<link href="${pageContext.request.contextPath}/styles/mainStyle.css" rel="stylesheet" type="text/css">
	<link href="https://fonts.googleapis.com/css2?family=Source+Code+Pro:ital,wght@1,500&display=swap" rel="stylesheet">
</head>
<body>
	<jsp:include page="_header.jsp"/>
	<div class="big-block">
		<form method="POST" action="${pageContext.request.contextPath}/thread/save">
			<table border=0>
			<tr>
				<td>Thread topic</td>
				<td><textarea name="theme" cols="40" rows="3" >${thread.theme }</textarea> </td>
			</tr>
			<tr>
				<td>Thread body</td>
				<td><textarea name="value" cols="40" rows="5" >${thread.value }</textarea> </td>
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