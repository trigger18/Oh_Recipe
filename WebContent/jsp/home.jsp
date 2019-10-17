
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../jsp/menu.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<!--웹폰트-->
<link href="https://fonts.googleapis.com/css?family=Noto+Sans+KR:100&display=swap" rel="stylesheet">

<!--home js/css  -->
<link rel="stylesheet" href="/semiRecipe/css/home_container.css">
<script type="text/javascript" src="/semiRecipe/js/home_container.js"></script>
<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>

<!-- 드래그/드랍 + 한글-->
<script src="/semiRecipe/js/plugin/jquery-ui.min.js"></script>
<script src="/semiRecipe/js/plugin/hangul.js"></script>

<style type="text/css">
*{
	margin:0;
	padding: 0;
	font-family: 'Noto Sans KR';
}

</style>
</head>
<body>
	<div id='wrap' class='test'>
		<div id='container' class='test'>
			<div id='main_contents'>
				<div id='selected_ing_div'>
					<span id='tt'>이곳에 재료를 끌어다 놓으세요</span>
					<div id='selected_ing_list'></div>
				</div>
				<div id='selected_recipe_view'></div>
				<div id='selected_recipe'></div>
			</div>
			<jsp:include page="../jsp/sideMenu.jsp"/>			
		</div>
		<footer></footer>
	</div>
</body>
</html>