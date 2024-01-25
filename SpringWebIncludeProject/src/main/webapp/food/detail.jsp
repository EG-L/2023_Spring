<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
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
						<a href="../main/main.do" class="btn btn-xs btn-primary">목록</a>
					</td>
				</tr>
			</table>
		</div>
	</div>
</body>
</html>