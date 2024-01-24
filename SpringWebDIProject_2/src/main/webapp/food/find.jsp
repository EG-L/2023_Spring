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
<script src="https://unpkg.com/vue@3"></script>
<script src="https://unpkg.com/axios/dist/axios.min.js"></script>
</head>
<body>
	<div class="container">
		<div class="row">
			<input type="text" size=20 class="input-sm" v-model="fd"> 
			<input type="button" value="검 색" class="btn-sm btn-danger" @click="find()">
		</div>
		<div style="height: 20px"></div>
		<div class="row">
			<div class="col-md-3" v-for="vo in find_data">
				<div class="thumbnail">
					<a href="#"> <img	
						:src="'https://www.menupan.com'+vo.poster" alt="Lights"
						style="width: 100%">
						<div class="caption">
							<p style="font-size: 9px">{{vo.name}}</p>
						</div>
					</a>
				</div>
			</div>
		</div>
	</div>
	<script>
		//뷰 시작점
		const {createApp} = Vue;
		createApp({
			data(){
				return {
					//멤버변수
					fd:'마포',
					find_data:[],
				}
			},
			//$(function(){}) => window.onload()
			mounted(){
				axios.get('http://localhost:8080/web/food/find_vue.do',{
					params:{
						fd:this.fd
					}
				}).then(response=>{
					this.find_data=response.data
					console.log(response.data);
				})
			},
			methods:{
				find(){
					axios.get('http://localhost:8080/web/food/find_vue.do',{
						params:{
							fd:this.fd
						}
					}).then(response=>{
						this.find_data=response.data
						console.log(response.data);
					})
				}
			}
		}).mount('.container')
	</script>
</body>
</html>