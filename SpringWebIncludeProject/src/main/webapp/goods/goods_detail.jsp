<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="style.css">
<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
<script type="text/javascript" src="https://cdn.iamport.kr/js/iamport.payment-1.2.0.js"></script>
</head>
<body>
<div class="container">
	<div class="row">
		<h1 class="text-center">상품 상세보기</h1>
		<div style="height: 30px;"></div>
		<table class="table">
				<tr>
					<td width="35%" align="center" rowspan="9">
						<img src="${vo.poster }" id="image">
					</td>
					<td width="65%">
						<span id="title">
							${vo.name }
						</span>
					</td>
				</tr>
				<tr>
					<td width="65%">
						<span id="sub">
							${vo.sub }
						</span>
					</td>
				</tr>
				<tr>
					<td width="65%">
						<span id="percent">${vo.discount }%</span>&nbsp;
						<span id="price" data-price="${vo.price }">${vo.price }</span>
						<p>
							<del id="psub">${vo.price }</del>
						</p>
					</td>
				</tr>
				<tr>
					<td width="65%">
						<span id="label">첫구매할인가</span>
						<span id="price2">${vo.first_price }</span>
					</td>
				</tr>
				<tr>
					<td width="65%">
						<span id="star">★★★★★</span>&nbsp;
						<span id="bold">4.5</span>
						<span id="count">(299)</span>
					</td>
				</tr>
				<tr>
					<td width="65%">
						<img src="https://recipe1.ezmember.co.kr/img/mobile/icon_delivery3.png">
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<span id="delivery">${vo.delivery }</span>
					</td>
				</tr>
				<tr>
					<td width="65%">
						수량<select id="sel">
							<option value="1">1개</option>
							<option value="2">2개</option>
							<option value="3">3개</option>
							<option value="4">4개</option>
							<option value="5">5개</option>
						</select>
					</td>
				</tr>
				<tr>
					<td width=65% class="text-right">
						총구매액:<span id="total">${vo.price }</span>원
					</td>
				</tr>
				<tr>
					<td width="65%">
						<c:if test="${sessionScope.id!=null }">
							<input type="button" value="장바구니" id="cart" data-no="${vo.no }" data-type="${type }">
							<input type="button" value="바로구매" id="buy">
						</c:if>
						<input type="button" value="목록" id="cart" onclick="javascript:history.back()">
					</td>
				</tr>
			</table>
	</div>
	</div>
</body>
</html>