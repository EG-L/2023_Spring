<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<style type="text/css">
	.container{
		margin-top:50px;
	}
	.row{
		margin:0px auto;
		width:960px;
	}
</style>
</head>
<body>
	<div class="container">
		<div class="row">
			<table class="table">
				<tr>
					<td width=30% class="text-center" rowspan="6">
						<img src="https://www.menupan.com${vo.poster }" style="width:100%">
					</td>
					<td colspan="2"><h3>${vo.name }</h3></td>
				</tr>
				<tr>
					<th width=10%>주소</th>
					<td width=60%>${vo.address }</td>
				</tr>
				<tr>
					<th width=10%>영업시간</th>
					<td width=60%>${vo.time }</td>
				</tr>
				<tr>
					<th width=10%>가격대</th>
					<td width=60%>${vo.price }</td>
				</tr>
				<tr>
					<th width=10%>음식종류</th>
					<td width=60%>${vo.type }</td>
				</tr>
				<tr>
					<td colspan="3">${vo.content }</td>
				</tr>
				<tr>
					<td colspan="3" class="text-right">
						<input type="button" class="btn-sm btn-danger" value="목록" onclick="javascript:history.back()">
					</td>
				</tr>
			</table>
		</div>
	</div>
</body>
</html>