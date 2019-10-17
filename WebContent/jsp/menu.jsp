<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="URI" value="${pageContext.request.requestURI}" />

<!DOCTYPE html>
<html>
<head>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>

<link
	href="https://fonts.googleapis.com/css?family=Noto+Sans+KR:100&display=swap"
	rel="stylesheet">
<link rel="stylesheet" href="/semiRecipe/css/menu.css">
<script type="text/javascript" src="/semiRecipe/js/menu.js"></script>

<style type="text/css">
* {
	margin: 0;
	padding: 0;
}
</style>

</head>
<body>
	<header id='header'>
		<div id='menu_bar'>
			<img src="/semiRecipe/img/menu_back.jpg">
		</div>
		<div class="home" id='logo_container'>
			<img src="/semiRecipe/img/LOGO_white.png">
		</div>
		<div id='log_container'>
			<a href="/semiRecipe/recipe/myPage" id='myPage'>MY PAGE</a>

			<c:choose>
				<c:when test="${empty sessionScope.loginID}">
					<a href="/semiRecipe/recipe/loginForm" id='login'>LOGIN</a>
				</c:when>
				<c:otherwise>
					<a href="/semiRecipe/recipe/logout" id='logout'>LOGOUT</a>
				</c:otherwise>
			</c:choose>

		</div>
		<div id='menu_container'>
			<ul id="menu_ul">
				<li class="menu_li" id='list'><img
					src="/semiRecipe/img/menu01.PNG"></li>
				<li class="menu_li" id='review'><img
					src="/semiRecipe/img/menu02.PNG"></li>
				<li class="menu_li" id='selfRecipe'><img
					src="/semiRecipe/img/menu03.PNG"></li>
			</ul>
		</div>
		<div id='info'><!-------------------------------------- 여기에 글씨 바꾸면 됩니다------------------------------- -->
			<h1 id='title'>
				이렇게나 많은<br>레시피가 있습니다.
			</h1>
			<h3 id='sub'>이중에서 고르세요.</h3>
		</div>

		<div id='test'></div>
	</header>
</body>
</html>