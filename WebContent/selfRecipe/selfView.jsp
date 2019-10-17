
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib  prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<link rel="stylesheet" href="/semiRecipe/css/recipe.css">
<link rel="stylesheet" href="/semiRecipe/selfRecipe/css/self_board.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script type="text/javascript">
var rate = $('.R_rate');
for(var i=0; i<rate.length;i++){
	$(rate.eq(i)).css({'width':($(rate.eq(i)).attr('id')*100/4+'%'),'opacity':($(rate.eq(i)).attr('id')/4)});
}
</script>

<div id='R_header' class='R_div'>
		
	<span id='user_id'>${requestScope.prdto.user_nickname } </span>|
	<span id='self_views'> &nbsp;<img width="14" height="14" alt="조회수" src="/semiRecipe/img/views.png"> ${requestScope.prdto.self_views } &nbsp;</span>|
	<span id='self_date'>${requestScope.prdto.self_date } </span>

	<div id='R_image'>
		<img src="/semiRecipe/selfRecipe/img_self/${requestScope.prdto.img_url }">
	
	</div>
	<h1>${requestScope.prdto.recipe_nm_ko }</h1>
	<h2>${requestScope.prdto.sumry }</h2>
</div>
<div id='R_info' class='R_div'>
	<!------------------ 아이디바꿔야함 -------------->
	<div id='food_class'>
		<p>음식분류</p>
		<span>${requestScope.prdto.nation_nm }</span>
	</div>
	<div id='cook_time'>
		<p>조리시간</p>
		<span id="R_hour">${requestScope.hour }</span> <span>h</span> 
		<span id="R_minute">${requestScope.miute }</span> <span>m</span>
	</div>
	<div id='calorie'>
		<p>칼로리</p>
		<span id="R_cal">${requestScope.prdto.calorie }</span> <span>kcal</span>
	</div>
	<div id='grade'>
		<p>난이도</p>
		<span>${requestScope.prdto.level_nm }</span>
	</div>
</div>



<div id='R_importance' class='R_div'>
	<h3>재료 중요도</h3>
	<div id='normal_irdnt' class='R_ig_div'>
		<c:forEach items="${requestScope.irList }" var="irList">
		<div class='R_ing'>
			<span>${irList.IRDNT_NM }</span>
			<div class='R_rate_div'>
				<div class="R_rate" id="${irList.IMPORTANCE }"></div>
			</div>
		</div>
		</c:forEach>
	</div>
	<div id='seasonig' class='R_ig_div'>
	<c:forEach items="${requestScope.subIrList }" var="subIrList">
		<div class='R_ing'>
			<span>${subIrList.IRDNT_NM }</span>
			<div class='R_rate_div'>
				<div class="R_rate" id="${subIrList.IMPORTANCE }"></div>
			</div>
		</div>
		</c:forEach>
	</div>
</div>

<div id='R_recipeDiv' class='R_div'>
	<c:forEach items="${requestScope.stList }" var="stList">
		<div class='R_recipe'>
		<span class='R_num'>${stList.COOKING_NO }</span><span>${stList.COOKING_DC }</span>
		<hr>
	</div>
	</c:forEach>
</div>

<div id='CMT' class='R_div'>
	<select name="order" id="order">
		<option value="com_time">최근날짜순</option>
		<option value="rating">별점순</option>
	</select>
	<input type="hidden" id="key" value="${prdto.recipe_id}">
	<div id="selfrecipe">

		
	</div>	
</div>
	<link rel="stylesheet" href="/semiRecipe/css/comment.css">
<script type="text/javascript" src="/semiRecipe/js/comment.js"></script>
	<script type="text/javascript">
	comList();
	</script>
