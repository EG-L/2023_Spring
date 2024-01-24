<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div class="row">
			<h3 class="text-center">인기 게시물 Top5</h3>
			<table class="table">
				<c:forEach var="vo" items="${tList }">
					<tr>
						<td class="text-center">${vo.name }</td>
						<td><a href="detail.do?no=${vo.no }">${vo.subject }</a></td>
					</tr>
				</c:forEach>
			</table>
		</div>
</body>
</html>