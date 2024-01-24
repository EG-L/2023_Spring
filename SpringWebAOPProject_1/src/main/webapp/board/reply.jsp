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
			<h3 class="text-center">답변하기</h3>
			<form method="post" action="reply_ok.do">
			<table class="table">
				<tr>
					<th width=15% class="text-right">이름</th>
					<td width=85%>
						<input type="text" name=name size=15 class="input-sm">
						<input type="hidden" name=pno value="${pno}">
					</td>
				</tr>
				<tr>
					<th width=15% class="text-right">제목</th>
					<td width=85%>
						<input type="text" name=subject size=15 class="input-sm">
					</td>
				</tr>
				<tr>
					<th width=15% class="text-right">내용</th>
					<td width=85%>
						<textarea rows="5" cols="52" name=content></textarea>
					</td>
				</tr>
				<tr>
					<th width=15% class="text-right">비밀번호</th>
					<td width=85%>
						<input type="password" name=pwd size=10 class="input-sm">
					</td>
				</tr>
				<tr>
					<td colspan="2" class="text-center">
						<input type="submit" value="답변" class="btn-sm btn-success">
						<input type="button" value="취소" class="btn-sm btn-warning" onclick="javascript:history.back()">
					</td>
 				</tr>
			</table>
			</form>
		</div>
		<div style="height:20px"></div>
		<jsp:include page="top.jsp"></jsp:include>
	</div>
</body>
</html>