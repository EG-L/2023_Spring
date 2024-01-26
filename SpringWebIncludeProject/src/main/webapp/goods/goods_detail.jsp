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
			<div class="col-sm-8">
			<table class="table">
				<tr>
					<td>
						<c:forEach var="rvo" items="${gList }">
							<table class="table">
								<tr>
									<td class="text-left">♠${rvo.name }(${rvo.dbday })</td>
									<td class="text-right">
										<c:if test="${rvo.id==sessionScope.id }">
											<span class="btn btn-xs btn-info updates" data-no="${rvo.no }">수정</span>
											<a href="../goods/reply_delete.do?no=${rvo.no }&fno=${vo.no}" class="btn btn-xs btn-success">삭제</a>
										</c:if>
									</td>
								</tr>
								<tr>
									<td colspan="2" class="text-left" valign="top">
										<pre style="white-space:pre-wrap;border:none;background-color: white">${rvo.msg }</pre>
									</td>
								</tr>
								<%-- <tr style="display:none;" id="u${rvo.no }" class="ups">
									<td colspan="2">
										<form method="post" action="../goods/reply_update.do">
											<input type="hidden" name="fno" value="${vo.no }">
											<input type="hidden" name="no" value="${rvo.no }">
											<textarea rows="4" cols="65" name="msg" style="float:left">${rvo.msg }</textarea>
											<button class="btn-primary" style="width:100px;height:95px;float:left;">댓글 수정</button>
										</form>
									</td>
								</tr> --%>
							</table>
						</c:forEach>
					</td>
				</tr>
			</table>
			<c:if test="${sessionScope.id!=null }">
				<table class="table">
					<tr>
						<td>
							<form method="post" action="../goods/reply_insert.do">
								<input type="hidden" name="fno" value="${vo.no }">
								<textarea rows="4" cols="65" name="msg" style="float:left"></textarea>
								<button class="btn-primary" style="width:100px;height:95px;float:left;">댓글 쓰기</button>
							</form>
						</td>
					</tr>
				</table>
			</c:if>
		</div>
	</div>
	</div>
</body>
</html>