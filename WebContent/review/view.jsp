<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" name="viewport"
	content="width=device-width, initial-scale=1" />
<title>Review Board</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script>
$(document).ready(function(){
	$('#deleteBtn').on('click',function(){
		$.ajax({
			type : 'POST',
			url : '/semiRecipe/recipe/reviewdelete',
			data : 'review_num='+$('#review_num').val(),
			success : function() {
				location.href="/semiRecipe/recipe/review";
			}
		});
		
		return false;
	});
	
});
</script>
<link rel="stylesheet" href="/semiRecipe/fontello-icon/css/fontello.css"/>
<style>

   #view_post{
   	height : 100%;
   	min-height :300px;
   	width : 800px;
   	margin : 0 auto;
   	border: solid 1px rgb(200, 200, 200);
   	background: rgb(250, 250, 250);
   	margin-bottom : 20px;
   	margin-top : 20px;
   }
   .viewerTable, .viewerTableSecond{
   	margin-left: 20px;
   	margin-right: 20px;
    width :760px;
	text-align: center;
   }
   #review_title_first{
   	 height: 50px;
   }
   #review_title_second{
   	 height:50px;
   }
   
   #review_content{
   	 height: 100%;
   	 min-height : 300px;
   }
   
   #revTitle, #revTitleSpace{
     font-weight: 800;
   }
   #revContentSpace{
   	 border-top: solid 1.5px #000000;
   	 padding-top : 8px;
     word-break:break-all;
   	}
   	#review_content #revContentSpace img {
   	  max-width: 80%
   	}
   	.viewBtn{
   		display : inline-block;
   		float: right;
   	}
   	#modifyBtn, #deleteBtn{
   	padding: 3px;
	text-decoration: none;
	color: #FFF5E6;
	background-color: rgba(242, 159, 5, 0.8);
	border: 1px solid #F29F05;
	border-radius: 0.2rem;
	font-size: 15px;
   	}

#revContentSpace {
	border-top: solid 1.5px #000000;
	padding-top: 8px;
}

#review_content #revContentSpace img {
	max-width: 80%
}
</style>

</head>
<body>
	<%-- 
	<div>
		<jsp:include page="../jsp/menu.jsp"></jsp:include>
	</div> --%>

	<div id="view_post">
		<c:set value="${requestScope.viewer}" var="viewer" />
		<input name="review_num" id="review_num" type="hidden" value="${viewer.review_num}" />
		<table class="viewerTable">
			<tr id="review_title_first">
				<td width="555px" colspan="4" align="left" id="revTitleSpace">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					${viewer.review_subject}</td>
				<td width="50px" id="revViewcount" class="icon-book-open"></td>
				<td width="50px" id="revViewcountSpace">${viewer.review_views}</td>
			</tr>
		</table>
		<table class="viewerTableSecond">

			<tr id="review_title_second">
				<td id="revWriter" class="icon-pencil" width="50px"></td>
				<td id="revWriterSpace" width="200px" align="left">&nbsp;&nbsp;${viewer.user_nickname}</td>

				<td id="recipeID" width="300px"><a href="http://192.168.30.72:8090/semiRecipe/recipe/showRecipe?recipe_id=${viewer.recipe_id}" onclick="window.open(this.href, '_blank', 'width=body.50%, height=this.100%');return false;" >${viewer.recipe_nm_ko}</td>

				<td id="revRate" class="icon-star" width="50px"></td>
				<td id="revRateSpace" width="50px">${viewer.review_rate}</td>
				<td id="revDate" width="100px">${viewer.review_date}</td>
			</tr>
			<tr id="review_content">
				<td id="revContentSpace" align="center" colspan="6">${viewer.review_content}</td>
			</tr>
		</table>
		<c:if test="${sessionScope.loginID eq viewer.user_id}" >
			<table>
				<tr>
					<td width="800px">
						<div class="viewBtn">
							<button id="deleteBtn">삭제</button>
							&nbsp;&nbsp;&nbsp;&nbsp;
						</div>
					</td>
				</tr>
			</table>
		</c:if>
		
		

	</div>

	<%--<div>
		<jsp:include page="../review/reviewBoard.jsp"></jsp:include>
	</div> --%>
</body>

</html>