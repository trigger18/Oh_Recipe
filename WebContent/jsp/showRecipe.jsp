<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link rel="stylesheet" href="/semiRecipe/css/recipe.css">
<link
	href="https://fonts.googleapis.com/css?family=Noto+Sans+KR:100&display=swap"
	rel="stylesheet">	
<script type="text/javascript" src="/semiRecipe/js/comment.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>

<script type="text/javascript">
var rate = $('.R_rate');
for(var i=0; i<rate.length;i++){
	$(rate.eq(i)).css({'width':($(rate.eq(i)).attr('id')*100/4+'%'),'opacity':($(rate.eq(i)).attr('id')/4)});
}
$(document).ready(function(){
	$('#closeShow').click(function(){
		$('.R_div').remove();
		var offset = $('#searchLine').offset();
	    $('html, body').animate({scrollTop : offset.top}, 400);    
	})
})
</script>
<div id='R_header' class='R_div'>
	<a href=""></a>
	<div id='R_image'>
		<img src="${requestScope.prim.IMG_URL }">
	</div>
	<h1>${requestScope.prim.RECIPE_NM_KO }</h1>
	<h2>${requestScope.prim.SUMRY }</h2>
</div>
<div id='R_info' class='R_div'>
	<!------------------ 아이디바꿔야함 -------------->
	<div id='음식분류'>
		<p>음식분류</p>
		<span>${requestScope.prim.NATION_NM }</span>
	</div>
	<div id='조리시간'>
		<p>조리시간</p>
		<span id="R_hour">${requestScope.hour }</span> <span>h</span> <span id="R_minute">${requestScope.minute }</span>
		<span>m</span>
	</div>
	<div id='칼로리'>
		<p>칼로리</p>
		<span id="R_cal">${requestScope.prim.CALORIE }</span> <span>kcal</span>
	</div>
	<div id='난이도'>
		<p>난이도</p>
		<span>${requestScope.prim.LEVEL_NM }</span>
	</div>
</div>



<div id='R_importance' class='R_div'>
	<h3>재료 중요도</h3>
	<div id='일반재료' class='R_ig_div'>
		<c:forEach items="${requestScope.irdntMain }" var="idto">
			<div class='R_ing'>
				<span>${idto.IRDNT_NM }</span>
				<div class='R_rate_div'>
					<div class="R_rate" id="${idto.IMPORTANCE }"></div>
				</div>
			</div>
		</c:forEach>
	</div>
	<div id='양념' class='R_ig_div'>
		<c:forEach items="${requestScope.irdntSub }" var="idto">
			<div class='R_ing'>
				<span>${idto.IRDNT_NM }</span>
				<div class='R_rate_div'>
					<div class="R_rate" id="${idto.IMPORTANCE }"></div>
				</div>
			</div>
		</c:forEach>
	</div>
</div>

<div id='R_recipeDiv' class='R_div'>
	<c:forEach items="${requestScope.step }" var="sdto">
		<div class='R_recipe'>
			<span class='R_num'>${sdto.COOKING_NO }</span><span>${sdto.COOKING_DC }</span>
			<hr>
		</div>
	</c:forEach>
</div>

<div id='CMT' class='R_div'>
		<span id='comment_num'>댓글 <span id="total">${total }</span>  개</span>
	<select name="order" id="order">
		<option value="com_time">최근날짜순</option>
		<option value="rating">별점순</option>
	</select>
	<input type="hidden" id="key" value="${recipe_id}">
	<div id="primary">
	</div>
	

</div>
<script type="text/javascript">
comList();
</script>
