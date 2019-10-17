<%@page import="java.util.ArrayList"%>
<%@page import="java.io.File"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../jsp/menu.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>마이페이지</title>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script type="text/javascript" src="/semiRecipe/js/myPage.js"></script>
<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
<style type="text/css">
body {
	background-color: #FFF5E6;
	overflow-y:hidden;
}

* {
	margin: 0;
	padding: 0;
}

#myPageBody {
	margin: 0 auto;
	width: 800px;
	border-top: 1px solid #e8e9ed;
	border-collapse: collapse;
}

#myPageBody caption {
	font-size: 30px;
	font-weight: bold;
	padding: 40px;
}

#myPageBody>tbody>tr>td {
	border-bottom: 1px solid #e8e9ed;
	width: 100px;
	height: 130px;
	color: #a4a4a4;
	font-size: 13px;
}

#myPageBody td.profile, #myPageBody td.private, #myPageBody td.myPageList
	{
	font-weight: bold;
	width: 12%;
	color: black;
	font-size: 15px;
}

input {
	height: 23px;
}

#finish {
	text-align: center;
	padding: 15px;
	margin-bottom: 50px;
}

td p {
	height: 25px;
}

td p input {
	height: 20px;
}

#yy, #mm, #dd {
	width: 80px;
	height: 30px;
	float: left;
	margin-right: 10px;
}

#user_icon>img {
	width: 100px;
	height: 100px;
}

.chgbtns { /* 변경 버튼 */
	border-radius: 5px;
	color: #ffffff;
	text-decoration: none;
	border: 1px solid #11bef1;
	background: linear-gradient(#11bef1, #029be0);
	padding: 6px 24px;
}

.canbtns { /* 취소 버튼 */
	border-radius: 5px;
	color: #58595b;
	text-decoration: none;
	border: 1px solid #a6a8ab;
	background: linear-gradient(#f0f0f1, #e0e0e2);
	padding: 6px 24px;
}

.iconHide img {
	width: 35px;
	height: 35px;
}

tr:nth-child(2) {
	width: 10px;
}
</style>
</head>
<body>
	<%
		String file = application.getRealPath("/img/icon");
		File f = new File(file);
		String[] fileNames = f.list();
		File[] fileObjects = f.listFiles();

		ArrayList<String> fList = new ArrayList<String>();

		for (int i = 0; i < fileObjects.length; i++) {
			if (!fileObjects[i].isDirectory()) {
				if (fileNames[i].indexOf("cooker") > -1)
					fList.add(fileNames[i]);
			}
		}
	%>
	<div id='myPageWrap'>
	<c:set value="${requestScope.dto}" var="dto" />

		<table id="myPageBody">
			<caption>마이페이지</caption>
			<tr class="content">
				<!-- 아이디 -->
				<td class="profile" rowspan="3">프로필</td>
				<td class="myPageList">아이디</td>
				<td id="loginId" colspan="3">${dto.user_id}</td>
			</tr>
			<tr class="content">
				<!-- 아이콘 -->
				<td class="myPageList">아이콘</td>
				<td id="user_icon">
					<img src="/semiRecipe/img/icon/${dto.user_icon}"></td>
				<td>
					<div class="iconHide" style="width: 220px;">
						<c:forEach var="iconArr" items="<%=fList%>" varStatus="status" begin="0" end="<%=fList.size()%>">
							<c:if test="${iconArr!=dto.user_icon}">
								<a class="icon" href=""><img alt=""src="/semiRecipe/img/icon/${iconArr}"></a>
							</c:if>
						</c:forEach>
					</div>
				</td>
				<td><a href="" id="iconChangeBtn" class="chgbtns">변경</a> <a
					href="" id="iconChangeCancleBtn" class="iconHide canbtns">취소</a></td>
			</tr>
			<tr class="content">
				<!-- 닉네임 -->
				<td class="myPageList">닉네임</td>
				<td id="nickname"><span id="nValue">${dto.user_nickname}</span>
				</td>
				<td><input type="text" class="nHide" id="nBox"
					value="${dto.user_nickname}" /></td>
				<td><a href="" id="nChangeBtn" class="chgbtns">변경</a> <a href=""
					id="nChangeCancleBtn" class="nHide canbtns">취소</a></td>
			</tr>
			<tr class="content">
				<!-- 비밀번호 -->
				<td class="private" rowspan="2">개인정보</td>
				<td class="myPageList">비밀번호</td>
				<td colspan="1">
					<div class="pwHide">
						<p>현재 비밀번호 :</p>
						<p>변경 후 비밀번호 :</p>
						<p>변경 후 비밀번호 확인 :</p>
					</div>
				</td>
				<td>
					<div class="pwHide">
						<p>
							<input type="password" id="now_pw">
						</p>
						<p>
							<input type="password" id="new_pw">
						</p>
						<p>
							<input type="password" id="new_pw_confirm">
						</p>
					</div>
				</td>
				<td><a href="" id="pwChangeBtn" class="chgbtns">변경</a> <a
					href="" id="pwChangeCancleBtn" class="pwHide canbtns">취소</a></td>
			</tr>
	
			<tr class="content">
				<!-- 생년월일 -->
				<td class="myPageList">생년월일</td>
				<td><span id="birthValue">${dto.user_birthday}</span></td>
	
				<td>
					<div class="birthHide">
						<div class="bir_wrap">
							<div class="bir_yy">
								<span class="ps_box"> <select id="yy" name="yy"
									class="sel" aria-label="년" required="required">
										<option value="">년</option>
								</select>
								</span>
							</div>
	
							<div class="bir_mm">
								<span class="ps_box"> <select id="mm" name="mm"
									class="sel" aria-label="월" required="required">
										<option value="">월</option>
								</select>
								</span>
							</div>
	
							<div class=" bir_dd">
								<span class="ps_box"> <select id="dd" name="dd"
									class="sel" aria-label="일" required="required">
										<option value="">일</option>
								</select>
								</span>
							</div>
						</div>
					</div>
				</td>
	
				<td><a href="" id="birthChangeBtn" class="chgbtns">변경</a> <a
					href="" id="birthChangeCancleBtn" class="birthHide canbtns">취소</a></td>
	
			</tr>
		</table>
	
		<div id="finish">
			<a href="" id="modCancleBtn" class="canbtns">취소</a> <a href=""
				id="modFinishBtn" class="chgbtns">저장</a>
		</div>
</div>



</body>
</html>