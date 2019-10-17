<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<style>
.recipeBtn{
	display:none;
}
</style>

<div class="recipeBtn" id="nextBtn"><img alt="" src="/semiRecipe/img/home_open_btn.png" ></div>
<div class="recipeBtn" id="prevBtn"><img alt="" src="/semiRecipe/img/home_open_btn.png" style="transform: rotate(180deg);"></div>

<c:forEach items="${requestScope.viewPrim }" var="dto">
	<%-- <div class="selected_recipe" id="${dto.RECIPE_ID }" onclick="">
		<img alt="" src="${dto.IMG_URL }">
		<p>${dto.RECIPE_NM_KO }</p>
	</div> --%>
	<input class="selected_recipe" type="hidden" value="${dto.RECIPE_ID }">
</c:forEach>
<script src="/semiRecipe/js/showRecipe.js"></script>
<script type="text/javascript">
var recipes = $(".selected_recipe");
var len = recipes.length;
var cur = recipes[0];
var inx = 0;

$('.recipeBtn').on('mouseover',function(){
	$(this).clearQueue().animate({'opacity':1},300);
}).on('mouseleave',function(){
	$(this).clearQueue().animate({'opacity':0.5},300);
})


$('#nextBtn').click(function(){
	if(inx==len-1){
		inx = 0;
		cur = recipes[inx];
	}else{
		inx++;
		cur = recipes[inx];
	}
	showRecipe($(cur).val(),"#selected_recipe");
})
$('#prevBtn').click(function(){
	if(inx==0){
		inx = len-1;
		cur = recipes[inx];
	}else{
		inx--
		cur = recipes[inx];
	}
	showRecipe($(cur).val(),"#selected_recipe");
})
showRecipe($(cur).val(),"#selected_recipe");
</script>
