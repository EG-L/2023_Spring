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
<div class="container">
		<div class="row">
			<form method="post" action="../goods/goods_find.do">
			<input type="text" name="ss" size=20 class="input-sm" value="${ss }">
			<input type="submit" value="검색" class="btn-sm btn-danger">
			</form>
		</div>
		<div style="height:20px"></div>
		<div class="row">
			<c:forEach var="vo" items="${list }">
				<div class="col-sm-3">
					<a href="../goods/goods_detail.do?no=${vo.no }">
						<div class="panel panel-danger">
							<div class="panel-heading">${vo.name }</div>
							<div class="panel-body text-center">
								<img src="${vo.poster }" style="width:180px;height:200px">
							</div>
						</div>
					</a>
				</div>
			</c:forEach>
		</div>
		<div style="heigth:20px"></div>
	<div class="row">
		<div class="text-center">
			<ul class="pagination">
				<c:if test="${startPage>1 }">
				  <li><a href="../goods/goods_find.do?page=${startPage-1 }&ss=${ss}">&lt;</a></li>
				</c:if>
			  <c:forEach var="i" begin="${startPage }" end="${endPage }">
			  	<li ${curpage==i?"class=active":"" }><a href="../goods/goods_find.do?page=${i }&ss=${ss}">${i }</a></li>
			  </c:forEach>
			  <c:if test="${endPage<totalpage }">
			  	<li><a href="../goods/goods_find.do?page=${endPage+1 }&ss=${ss}">&gt;</a></li>
			  </c:if>
			</ul>
		</div>
	</div>
</body>
</html>