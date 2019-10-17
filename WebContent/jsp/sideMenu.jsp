
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<link rel="stylesheet" href="/semiRecipe/css/sideMenu.css">
<script type="text/javascript" src="/semiRecipe/js/sideMenu.js"></script>
<script src="/semiRecipe/js/plugin/jquery-ui.min.js"></script>
<script src="/semiRecipe/js/plugin/hangul.js"></script>

<div id='side_menu'>
	<div id='side_btn'>
		<img src="/semiRecipe/img/home_open_btn.png">
	</div>
	<div id='search_div'><input type="text"
			name="search" autocomplete="off" placeholder="재료를 검색하세요">
	</div>
	<div id='ingredients_div'>
		<div id='ingredients_menu_div'>
			<img src="/semiRecipe/img/home_arrow.png" id='ing_m_left'> <img
				src="/semiRecipe/img/home_arrow.png" id='ing_m_right'>
			<ul id='ing_menu'>
				<c:forEach items="${requestScope.tList }" var="ty">
					<li class='ing_menu_li' id="${ty }">${ty }</li>
				</c:forEach>
			</ul>
		</div>
		<div id='ing_maker'>
			<c:forEach items="${requestScope.aList }" var="dto">
				<div class='ingredients' id='${dto.irdnt_nm}'>
					${dto.irdnt_nm}
					<input type="hidden" id='${dto.ty_nm }' />
				</div>
			</c:forEach>
		</div>
	</div>
</div>