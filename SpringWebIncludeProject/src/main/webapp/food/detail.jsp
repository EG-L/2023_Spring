<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
<script type="text/javascript">
	let bclick =true;
	$(function(){
		//span 
		$('.updates').click(function(){
			$('.ups').hide();
			let no = $(this).attr("data-no");
			if(bclick==true){
				bclick = false;
				$(this).text("취소");
				$('#u'+no).show();
			}
			else{
				bclick = true;
				$(this).text("수정");
				$('#u'+no).hide();
			}
		})
		
	})
</script>
</head>
<body>
	<div class="container">
		<div class="row">
			<table class="table">
				<tr>
					<td width=30% class="text-center" rowspan="8">
						<img src="https://www.menupan.com${vo.poster }" style="width:100%">
					</td>
					<td colspan="2">
						<h3>${vo.name }&nbsp;<span style="color:orange;">${vo.score }</span></h3>
					</td>
				</tr>
				<tr>
					<th width=20% class="text-right">주소</th>
					<td width=50%>${vo.address }</td>
				</tr>
				<tr>
					<th width=20% class="text-right">전화</th>
					<td width=50%>${vo.phone }</td>
				</tr>
				<tr>
					<th width=20% class="text-right">가격대</th>
					<td width=50%>${vo.price }</td>
				</tr>
				<tr>
					<th width=20% class="text-right">영업시간</th>
					<td width=50%>${vo.time }</td>
				</tr>
				<tr>
					<th width=20% class="text-right">테마</th>
					<td width=50%>${vo.theme }</td>
				</tr>
				<tr>
					<th width=20% class="text-right">음식종류</th>
					<td width=50%>${vo.type }</td>
				</tr>
				<tr>
					<th width=20% class="text-right">좌석</th>
					<td width=50%>${vo.seat }</td>
				</tr>
				<tr>
					<td colspan="3">
						<pre style="white-space : pre-wrap;border:none;background-color: white">${vo.content }</pre>
					</td>
				</tr>
				<tr>
					<td colspan="3" class="text-right">
						<a href="javascript:history.back()" class="btn btn-xs btn-primary">목록</a>
					</td>
				</tr>
			</table>
		</div>
		<div style="height:20px"></div>
		<div class="col-sm-8">
			<table class="table">
				<tr>
					<td>
						<c:forEach var="rvo" items="${rList }">
							<table class="table">
								<tr>
									<td class="text-left">♠${rvo.name }(${rvo.dbday })</td>
									<td class="text-right">
										<c:if test="${rvo.id==sessionScope.id }">
											<span class="btn btn-xs btn-info updates" data-no="${rvo.no }">수정</span>
											<a href="../reply/reply_delete.do?no=${rvo.no }&fno=${vo.fno}" class="btn btn-xs btn-success">삭제</a>
										</c:if>
									</td>
								</tr>
								<tr>
									<td colspan="2" class="text-left" valign="top">
										<pre style="white-space:pre-wrap;border:none;background-color: white">${rvo.msg }</pre>
									</td>
								</tr>
								<tr style="display:none;" id="u${rvo.no }" class="ups">
									<td colspan="2">
										<form method="post" action="../reply/reply_update.do">
											<input type="hidden" name="fno" value="${vo.fno }">
											<input type="hidden" name="no" value="${rvo.no }">
											<textarea rows="4" cols="65" name="msg" style="float:left">${rvo.msg }</textarea>
											<button class="btn-primary" style="width:100px;height:95px;float:left;">댓글 수정</button>
										</form>
									</td>
								</tr>
							</table>
						</c:forEach>
					</td>
				</tr>
			</table>
			<c:if test="${sessionScope.id!=null }">
				<table class="table">
					<tr>
						<td>
							<form method="post" action="../reply/reply_insert.do">
								<input type="hidden" name="fno" value="${vo.fno }">
								<textarea rows="4" cols="65" name="msg" style="float:left"></textarea>
								<button class="btn-primary" style="width:100px;height:95px;float:left;">댓글 쓰기</button>
							</form>
						</td>
					</tr>
				</table>
			</c:if>
		</div>
		
	</div>
</body>
</html>