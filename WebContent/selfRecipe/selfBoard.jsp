
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../jsp/menu.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" name="viewport"
	content="width=device-width, initial-scale=1" />
<title>selfReview Board</title>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="/semiRecipe/assets/js/jquery.min.js"></script>
<script src="/semiRecipe/assets/js/jquery.poptrox.min.js"></script>
<script src="/semiRecipe/assets/js/skel.min.js"></script>
<script type="text/javascript"
	src="/semiRecipe/selfRecipe/js/self_board.js"></script>
	
<link rel="stylesheet" href="/semiRecipe/selfRecipe/css/self_board.css">


<link
	href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:200i,300,300i,400,400i"
	rel="stylesheet">
<link rel="stylesheet"
	href="/semiRecipe/fontello-ea422c41/css/fontello.css">

<!-- 드래그/드랍 + 한글-->
<script src="/semiRecipe/js/plugin/jquery-ui.min.js"></script>
</head>
<body>
	<!-- hidden div -->
	<div id="hidden"></div>
	<div id="selected_recipe"></div>

	<!-- Wrapper -->
	<div id="wrapper">
		<!-- 한식 양식 퓨전 등 표시해주는  -->
		<section id="category"></section>

		<!-- Main -->
		<section id="main">
			<!-- 검색 부분  시작 -->
			<div id="searchLine">
				<select name="searchDrop" id="searchDrop">
					<option id="both" value="both" selected>전체</option>
					<option id="recipe_nm_ko" value="recipe_nm_ko">레시피이름</option>
					<option id="irdnt_nm" value="user_id">사용자명</option>
				</select> <input type="text" name="recipe_nm_ko" id="searchText" /> <input
					type="button" class="icon-search" value="검색">
			</div>
			<!-- 검색 부분  끝 -->

			<!-- 정렬부분 시작 -->
			<a href="#" class="sort" id="sortView"> <input type="hidden"
				class="column" value="self_views"> <input type="hidden"
				class="order" value="desc"> <input type="hidden"
				class="nation_nm" value=""> 조회수순
			</a> <a href="#" class="sort" id="sortRate"> <input type="hidden"
				class="column" value="rating"> <input type="hidden"
				class="order" value="desc"> <input type="hidden"
				class="nation_nm" value=""> 별점순
			</a> <a href="#" class="sort" id="sortNM"> <input type="hidden"
				class="column" value="recipe_nm_ko"> <input type="hidden"
				class="order" value="asc"> <input type="hidden"
				class="nation_nm" value=""> 가나다순
			</a>
			<!-- 정렬부분 끝 -->
			<!-- Thumbnails -->

			<section class="thumbnails">
			<div id="listSize">
				<c:forEach items="${requestScope.prList}" var="prList" varStatus="i">
					<div class="js-load" id="${prList.recipe_id }">
						<span><a href="#"> <img src="/semiRecipe/selfRecipe/img_self/${prList.img_url}" alt="main_image" />
								<h3>${prList.recipe_nm_ko}</h3></a> </span> 
						<span> <c:set var="recipe_id" value="${prList.recipe_id }" /> <p>작성자 : ${prList.user_nickname }</p>
							<p>${prList.self_date }</p> 
							<input type="hidden" name="recipe_id" value="${prList.recipe_id }" /> <span>
							<img alt="" src="/semiRecipe/img/views.png"> ${prList.self_views }</span>&nbsp;&nbsp; <span>
							<img alt="" src="/semiRecipe/img/rating.png"> ${prList.rating }</span>
							<input type="hidden" id="${prList.nation_nm }" value="${prList.nation_nm }" /> 
							
							<c:if
								test="${not empty sessionScope.loginID && prList.user_id==sessionScope.loginID }">
								<input name ='radi' type="radio"  id="${prList.recipe_id }" value="${prList.recipe_id }"/>
							</c:if>
						</span>
					</div>
				</c:forEach>
				</div>
			</section>
		</section>

		<!-- 화면 위로 올리기 시작	 -->
		<div id="quick_menu">
			<p><a href="#">top</a></p>
			<p><a href="selfRecipe""> 목록</a></p>
			<!-- 	<p><a href="selfInsert" > 쓰기</a></p> -->
			<c:if test="${not empty sessionScope.loginID }">
				<p>
					<a class="aHide" href="selfInsert"> 쓰기</a><br/>
					<a class="aHide" href="selfUpdate" id="update"> 수정</a><br/>
					<!-- <a class="aHide" onClick="selfDelete();" id="del"> 삭제</a> -->
					<a class="aHide"  id="del"> 삭제</a>
				</p>
			</c:if>
		</div>
		<!-- 화면 위로 올리기 끝 -->

	</div>
</body>
</html>