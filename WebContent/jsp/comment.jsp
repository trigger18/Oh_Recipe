
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link rel="stylesheet" href="/semiRecipe/css/comment.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script type="text/javascript" src="/semiRecipe/js/comment.js"></script>

<link
	href="https://fonts.googleapis.com/css?family=Noto+Sans+KR:100&display=swap"
	rel="stylesheet">



<!-- ************************************************* -->
<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
<!-- ************************************************* -->

<div class="insertCom">
	<div class='CMT_icon'><img alt="" src="/semiRecipe/img/icon/${sessionScope.loginICON}"></div>
	<input type="hidden" id="user_id" value="${sessionScope.loginID }">

	<textarea id='CMT_input' autocomplete='off' placeholder="댓글을 추가하세요"
		wrap="hard" maxlength="200"></textarea>

	<div id='star_div'>
	<span>만족도를 <br>평가해주세요</span>
		<div class='star' id='star_5'></div>
		<div class='star' id='star_4'></div>
		<div class='star' id='star_3'></div>
		<div class='star' id='star_2'></div>
		<div class='star' id='star_1'></div>
		<input type="hidden" name='rate' id='rate'>
	</div>

	<br> <input type='button' id='CMT_submit' value='댓글' onclick="insertCom()"> <input
		type="reset" id='CMT_reset' value='취소'>
</div>

<c:forEach items="${ comList}" var='cmt' varStatus="i">
	<div class='CMT_comment'>
		<div class='CMT_icon'><img alt="" src="/semiRecipe/img/icon/${cmt.user_icon }"></div>
		<div class='CMT_info'>
			<span class='CMT_ID'>${cmt.user_nickname }</span><span class='CMT_date'>${cmt.com_time}</span>
		</div>
		<div class='rated_div' id="${i.index+1 }" >
			<div class= 'rate_num'  id='${cmt.rating }'></div>
			<div class='rated' id='rate_5'></div>
			<div class='rated' id='rate_4'></div>
			<div class='rated' id='rate_3'></div>
			<div class='rated' id='rate_2'></div>
			<div class='rated' id='rate_1'></div>
			
		</div>
		<p class='CMT_text'>${cmt.com_content }</p>
	</div>
</c:forEach>

<input type="button" id="showMore" value="더보기">
