<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" name="viewport"
	content="width=device-width, initial-scale=1" />
<title>Review Board</title>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		$('.listLine a').on('click', function() {
			$.ajax({
				type : 'POST',
				dataType : 'text',
				data : 'review_num=' + $(this).prev('#rev_num').val(),
				url : 'reviewview',
				success : function(res) {
					$('#view').html(res);
					$('html, body').animate({
						scrollTop : 500
					}, 400);
				}
			});
			return false;
		});

		$('#searchBtn').on('click', function() {
			$('form').attr('action', '/semiRecipe/recipe/review');
			$('form').submit();
		});

		$('[name=searchWord]').val('${pdto.searchWord}');

		if ('${pdto.searchKey}' == 'null') {
			$('[name=searchWord]').val('');
		} else {
			switch ('${pdto.searchKey}') {
			case 'all':
				$('[value=all]').prop('selected', 'selected');
				$('[name=searchWord]').val('');
				break;
			case 'subject':
				$('[value=subject]').prop('selected', 'selected');
				break;
			case 'content':
				$('[value=content]').prop('selected', 'selected');
				break;
			case 'writer':
				$('[value=writer]').prop('selected', 'selected');
				break;
			}
		}

	});
</script>
<link
	href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:200i,300,300i,400,400i"
	rel="stylesheet">
<link rel="stylesheet" href="/semiRecipe/fontello-icon/css/fontello.css">
<link rel="stylesheet" href="/semiRecipe/css/reviewBoardcss.css">

<style>
#searchKey{
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
</style>

</head>

<%
	String uri = request.getRequestURI();
	String path = uri.substring(uri.lastIndexOf("/"));
%>
<body>
	<div>
		<c:set value="<%=path%>" var="i" />
		<c:if test="${i == '/reviewBoard.jsp'}">
			<%@ include file="../jsp/menu.jsp"%>
		</c:if>

	</div>
	<!-- hidden div -->
	<div id="hidden"></div>

	<div id="view"></div>

	<!-- Wrapper -->
	<div id="wrapper">

		<!-- Main -->
		<section id="main">

			<!-- Thumbnails -->

			<section class="thumbnails">
				<div class="listLine">
					<c:set value="${requestScope.imgList}" var="imgList" />
					<c:forEach items="${requestScope.reviewList}" var="revList"
						varStatus="status">
						<c:if test="${revList.rn%4==1}">
							<input type="hidden" id="rev_num" value="${revList.review_num }">
							<a href="#" id="${revList.rn}"> <img
								src="${imgList[status.index]}" alt="" />
								<h3>${revList.review_subject}
									<br /> <font color="#BC8F8F">${revList.user_nickname}</font>
								</h3>
							</a>
						</c:if>
					</c:forEach>
				</div>

				<div class="listLine">
					<c:set value="${requestScope.imgList}" var="imgList" />
					<c:forEach items="${requestScope.reviewList}" var="revList"
						varStatus="status">
						<c:if test="${revList.rn%4==2}">
							<input type="hidden" id="rev_num" value="${revList.review_num }">
							<a href="#" id="${revList.rn}"> <img
								src="${imgList[status.index]}" alt="" />
								<h3>${revList.review_subject}
									<br /> <font color="#BC8F8F">${revList.user_nickname}</font>
								</h3>
							</a>
						</c:if>
					</c:forEach>
				</div>

				<div class="listLine">
					<c:set value="${requestScope.imgList}" var="imgList" />
					<c:forEach items="${requestScope.reviewList}" var="revList"
						varStatus="status">
						<c:if test="${revList.rn%4==3}">
							<input type="hidden" id="rev_num" value="${revList.review_num }">
							<a href="#" id="${revList.rn}"> <img
								src="${imgList[status.index]}" alt="" />
								<h3>${revList.review_subject}
									<br /> <font color="#BC8F8F">${revList.user_nickname}</font>
								</h3>
							</a>
						</c:if>
					</c:forEach>
				</div>

				<div class="listLine">
					<c:set value="${requestScope.imgList}" var="imgList" />
					<c:forEach items="${requestScope.reviewList}" var="revList"
						varStatus="status">
						<c:if test="${revList.rn%4==0}">
							<input type="hidden" id="rev_num" value="${revList.review_num }">
							<a href="#" id="${revList.rn}"> <img
								src="${imgList[status.index]}" alt="" />
								<h3>${revList.review_subject}
									<br /> <font color="#BC8F8F">${revList.user_nickname}</font>
								</h3>
							</a>
						</c:if>
					</c:forEach>
				</div>

			</section>
			<form action="" method="post" id="searchform">
				<div id="searchLine">
					<select name="searchKey" id="searchKey">
						<option value="all" selected>검색</option>
						<option value="review_subject">제목</option>
						<option value="review_content">내용</option>
						<option value="user_nickname">글쓴이</option>
					</select> <input type="text" name="searchWord" id="searchWord" />
					<button class="icon-search">검색</button>
				</div>
			</form>
			<div id="buttonLine">
				<a href="review" class="icon-th-thumb-empty"> 목록</a> <a
					href="reviewwrite" class="icon-comment"> 쓰기</a>
			</div>
		</section>
	</div>

	<!-- Scripts -->
	<script src="../assets/js/jquery.min.js"></script>
	<script src="../assets/js/jquery.poptrox.min.js"></script>
	<script src="../assets/js/skel.min.js"></script>
	<script src="/semiRecipe/assets/js/jquery.min.js"></script>
	<script src="/semiRecipe/assets/js/jquery.poptrox.min.js"></script>
	<script src="/semiRecipe/assets/js/skel.min.js"></script>
</body>
</html>