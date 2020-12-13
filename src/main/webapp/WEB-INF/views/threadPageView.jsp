<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

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
		<p>User: ${thread.user.userName }</p>
		<p>Creation Date: ${com.formatDate }</p>
		<h3>${thread.theme}</h3>
		<div>${thread.value}</div>
	</div>
	<div class="big-block">
		<p align="center">Comments</p>
		<form method="POST" action="${pageContext.request.contextPath}/thread/saveComment/${thread.threadId}">
			<textarea name="value" cols="40" rows="5">${comment.value }</textarea>
			<input type="submit" value = "Send Comment"/>
		</form>
		<c:forEach items="${comments}" var="com">
			<div class="comment-block">
				<p>User: ${com.user.userName} </p>
				<p>Creation Date: ${com.formatDate} </p>
				<div>${com.value}</div>
			</div>
		</c:forEach>
	</div>
</body>
</html>