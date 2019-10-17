
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../jsp/menu.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8"/>
 <!-- name="viewport"
	content="width=device-width, initial-scale=1"  -->
<title></title>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<link
	href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:200i,300,300i,400,400i"
	rel="stylesheet">
<link rel="stylesheet" href="/semiRecipe/fontello-icon/css/fontello.css">

<link rel="stylesheet" href="/semiRecipe/css/reviewBoardcss.css">
<script src="/semiRecipe/js/primList.js"></script>
<script src="/semiRecipe/js/showRecipe.js"></script>
<style>
*:focus {
	outline: none;
}

#main .thumbnails {
    display: inline-block;
    text-align: center;
    overflow: hidden;
    height: 1100px;
}

#main .thumbnails div{
    max-width: none;
}

#main .thumbnails img{
	width: 200px;
}

.button {
	display: inline-block;
	width: 200px;
	height: 54px;
	text-align: center;
	text-decoration: none;
	line-height: 54px;
	outline: none;
	position: relative;
	z-index: 2;
	background-color: #333;
	border: 2px solid #333;
	color: #fff;
	line-height: 50px;
	margin-bottom: 4rem;
}

.js-load{
	display: inline-block;
	margin: 10px 10px;
}

#quick_menu {
    width: 65px;
    height: 36px;
	background:#F29F05;
	text-align: center;
	position: absolute;
	top: 80%;
	left: 90%;
}

#quick_menu a{
	font-size: 20px;
    text-decoration: none;
    color: rgb(250,250,250);
}


#ing_menu {
	list-style-type: none;
	height: 50px;
	width: 900px;
}


.sort{
	float: right;
	margin-right: 20px;
	text-decoration: none;
	color: inherit;
	font-size: 14px;
}

#category{
	margin-top: 30px;
	margin-left: 10%;
	display: inline-block;
}

.js-load span{
	color:#8D4738;
	font-size: 0.75rem;
	display: inline-block;
}

#main .thumbnails .js-load span>img{
	width: 20px;
	display: inline;
	vertical-align: bottom;
}

/*******************이해람바꿈********************/
#searchDrop{
	width: 142px;
    height: 39px;
    -webkit-appearance: none;
    border: none;
    font-size: 18px;
    padding-left: 0px;
    background: rgb(255, 245, 230);
    margin-left: -4px;
    margin-top: 8px;
    background-image: url(/semiRecipe/img/comment_1.png);
    background-size: 133px;
    color: #2d2d2dcf;
    font-style: oblique;
    font-weight: bold;
    background-position-x: -8px;
}

#searchText{
	font-size: 18px;
    width: 191px;
    height: 34px;
    border: none;
    font-weight: 100;
    text-align: center;
    box-shadow: 0 0 9px 0px rgba(56, 56, 56, 0.06);
}


.ing_menu_li {
    width: 140px;
    height: 39px;
    float: left;
    font-size: 21px;
    text-align: center;
    margin-right: 10px;
    cursor: pointer;
    padding-top: 5px;
    background: rgba(13, 13, 13, 0.67);
    color: #f1f1f1;
}
/*******************이해람바꿈********************/

</style>

</head>
<body>
	<!-- hidden div -->
	<div id="hidden"></div>
	
	<div id="searchLine">
				<select name="searchDrop" id="searchDrop">
					<option id="both" value="both" selected>전체</option>
					<option id="recipe_nm_ko" value="recipe_nm_ko">레시피이름</option>
					<option id="irdnt_nm" value="irdnt_nm">재료이름</option>
				</select>
				<input type ="text" name = "recipe_nm_ko" id ="searchText"/>
				<input type="button" class="icon-search" value="검색">
			</div>

	
	<div id='selected_recipe'></div>
	
	
	<!-- Wrapper -->
	<div id="wrapper">
<a href="#" class="sort" id="sortView"> <input type="hidden"
		class="column" value="prim_views"> <input type="hidden"
		class="order" value="desc"> <input type="hidden"
		class="nation_nm" value=""> 조회수순
	</a>
	<a href="#" class="sort" id="sortRate"> <input type="hidden"
		class="column" value="rating"> <input type="hidden"
		class="order" value="desc"> <input type="hidden"
		class="nation_nm" value=""> 별점순
	</a>
	<a href="#" class="sort" id="sortNM"> <input type="hidden"
		class="column" value="recipe_nm_ko"> <input type="hidden"
		class="order" value="asc"> <input type="hidden"
		class="nation_nm" value=""> 가나다순
	</a>
		<section id="category">
			<ul id='ing_menu'>
				<c:forEach items="${requestScope.nation_nm }" var="ty">
					<li class='ing_menu_li' id="${ty}">${ty}</li>
				</c:forEach>
			</ul>
		</section>

		<!-- Main -->
		<section id="main">

			<!-- Thumbnails -->

		<section class="thumbnails">
				<div id="listSize">
				<%@include file="../ajax/listResult.jsp"%>
</div>

			</section>


			<!-- <div id="js-btn-wrap" class="btn-wrap">
				<a href="javascript:;" class="button">더보기</a>
			</div> -->
		</section>
	</div>
	<div id="quick_menu">
		<a href="#">top</a>
	</div>

	<!-- Scripts -->
	<!-- <script src="/semiRecipe/assets/js/jquery.min.js"></script> -->
	<!-- <script src="/semiRecipe/assets/js/jquery.poptrox.min.js"></script> -->
	<!-- <script src="/semiRecipe/assets/js/skel.min.js"></script> -->
	
</body>
</html>