
<%@page import="java.util.ArrayList"%>
<%@page import="java.io.File"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script type="text/javascript" src="/semiRecipe/js/user.js"></script>
<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>

<style type="text/css">
* {
	margin: 0;
	padding: 0;
}

body {
	background-color: #FFF5E6;
}

.wrap input {
	width: 460px;
	height: 30px;
	border: 0;
	border-bottom: 2px solid #e9e9e9;
}

.wrap {
	display: block;
	margin: 50px auto;
	width: 600px;
}


#yy, #mm, #dd {
	width: 147px;
	height: 30px;
	float: left;
	margin-right: 10px;
}

.result {
	width: 220px;
	height: 45px;
	margin-right: 18px;
	display: inline-block;
    color: #fff;
    padding: 14px 0 13px;
    text-align: center;
    border: 0; 
}

.resultBtns {
	text-align: center;
}

#cancleBtn {
	background: #a5a5a5;
}

#joinBtn {
	background: #00a1ff;
}


.error_next_box {
	display: inline-block;
	font-size: 12px;
	line-height: 14px;
	color: red;
}

.iconwrap img {
    width:40px;
    height:40px;
    border: 1px solid white;
}
.iconwrap{
	margin-top: 15px;
	margin-bottom: 12px;
}

#icon {
	width: 100px;
	height: 100px;
}

.iconBox {
	text-align: center;
}

#logo {
    width: 50%;
    margin: 0 auto;
    margin-bottom: 15px;
    display: block;
    cursor: pointer;
}

#registerForm {
	background-color: white;
	padding: 20px 35px 35px;
}
   
</style>
</head>
<%
	String file = application.getRealPath("/img/icon");
	File f = new File(file);
	String[] fileNames = f.list();
	File[] fileObjects = f.listFiles();
	
	ArrayList<String> fList = new ArrayList<String>(); 
	
	for (int i = 0; i < fileObjects.length; i++) {
		if (!fileObjects[i].isDirectory()) {
			if(fileNames[i].indexOf("cooker")>-1)
				fList.add(fileNames[i]);
		}
	}
%>

<body id="register">
	<div class="wrap">
		<img id="logo" src="/semiRecipe/img/wellcome_logo2.png">
		<form id="registerForm" method="post">
			<input type="hidden" id="kakao_id" name="kakao_id" value="${param.kakao_id}" />
			<div class="iconBox">
				<img id="icon" name="icon" src="/semiRecipe/img/icon/default.png">
				<br/>
				<button id="iconSelectBtn">아이콘 선택하기</button>
			</div>
			<input type="hidden" id="user_icon" name="user_icon" />
			<input type="hidden" id="user_icon_before" name="user_icon" />
			<div class="iconwrap">
				<c:forEach var="iconArr" items="<%=fList %>" varStatus="status">
					<a class="icon" href=""><img alt="" src="/semiRecipe/img/icon/${iconArr}"></a>
				</c:forEach>
			</div>
			<h3>
				<label for="id">아이디</label>
			</h3>
			<input class="val" type="text" id="id" name="id" maxlength="20" required="required" value="${param.id }" />
			<p>
				<span class="error_next_box" id="idMsg" role="alert"></span>
			</p>
			<h3>
				<label for="pw">비밀번호</label>
			</h3>
			<input class="val" type="password" id="pw" name="pw" maxlength="20" required="required" />
			<p>
				<span class="error_next_box" id="pwMsg" role="alert"></span>
			</p>
			<h3>
				<label for="pwConfirm">비밀번호 확인</label>
			</h3>
			<input class="val" type="password" id="pwConfirm" name="pwConfirm" maxlength="20" required="required" />
			<p>
				<span class="error_next_box" id="pwConfirmMsg" role="alert"></span>
			</p>
			<h3>
				<label for="nickname">닉네임</label>
			</h3>
			<input class="val" type="text" id="nickname" name="nickname" maxlength="20"
				required="required" value="${param.nickname }" />
			<p>
				<span class="error_next_box" id="nicknameMsg" role="alert"></span>
			</p>
			<div class="join_row join_birthday">
				<h3 class="join_title">
					<label for="yy">생년월일</label>
				</h3>
				<div class="bir_wrap">
					<div class="bir_yy">
						<span class="ps_box"> <select id="yy" name="yy" class="sel val" aria-label="년" required="required">
								<option value="">년</option>
						</select>
						</span>
					</div>

					<div class="bir_mm">
						<span class="ps_box"> <select id="mm" name="mm" class="sel val" aria-label="월" required="required">
								<option value="">월</option>
						</select>
						</span>
					</div>

					<div class=" bir_dd">
						<span class="ps_box"> <select id="dd" name="dd" class="sel val" aria-label="일" required="required">
								<option value="">일</option>
						</select>
						</span>
					</div>
				</div>
				<p>
					<span class="error_next_box" id="birthdayMsg" role="alert"></span>
				</p>
			</div>
		</form>
	</div>
	<p class=resultBtns>
		<input class="result" id="cancleBtn" type="reset" value="취소" /> 
		<input class="result" id="joinBtn" type="submit" value="회원가입" />
	</p>
</body>
</html>