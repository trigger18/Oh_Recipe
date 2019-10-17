
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>



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