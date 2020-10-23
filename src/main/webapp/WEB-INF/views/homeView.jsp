<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>MyProject ImageBoard</title>
<link href="${pageContext.request.contextPath}/styles/mainStyle.css" rel="stylesheet" type="text/css">
<link href="https://fonts.googleapis.com/css2?family=Source+Code+Pro:ital,wght@1,500&display=swap" rel="stylesheet">
</head>
<body>
	<jsp:include page="_header.jsp"></jsp:include>
	<div class="big-block">
		<a href="${pageContext.request.contextPath}/threadCreate" class="button-link">Create New Thread</a>
		<div class="thread-container">
			<c:forEach items="${requestScope.threadList}" var="thr">
				<div class="thread-block">
					<p><b>${thr.theme}</b></p>
					<p>
						${fn:substring(thr.value, 0, 25)}
						<c:if test="${fn:length(thr.value)> 25}">
							...
						</c:if>
					</p>
					<a href="${pageContext.request.contextPath}/threadPage?id=${thr.threadId}">read thread...</a>
				</div>
			</c:forEach>
		</div>
	</div>
</body>
</html>