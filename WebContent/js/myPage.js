$(document).ready(function() {
	$(document).scrollTop($(document).height());	// 스크롤 맨 밑으로 내리기

	$('#myPageWrap').on('scroll touchmove mousewheel', function(e) {
		   e.preventDefault();
		   e.stopPropagation(); 
		   return false;
	})	// 스크롤 고정
	
	
	$('.pwHide').css('visibility','hidden');
	$('.nHide').css('visibility','hidden');
	$('.birthHide').css('visibility','hidden');
	$('.iconHide').css('visibility','hidden');
	
	var preImgSrc = $('#user_icon img').attr('src');
	
	$('#pwChangeBtn').on('click', function() {
		if ($('.pwHide').css('visibility') == "hidden") { // 변경버튼 처음 눌렀을 때
			$('.pwHide').css('visibility','visible');
			return false;
		} else { 
			var now_pw = $('#now_pw').val();
			var new_pw = $('#new_pw').val();
			var new_pw_confirm = $('#new_pw_confirm').val();
			var isPW = /^[A-Za-z0-9`\-=\\\[\];',\./~!@#\$%\^&\*\(\)_\+|\{\}:"<>\?]{8,16}$/;
			
			if (now_pw == "" || new_pw == "") {
				//swal("현재 비밀번호와 변경 비밀번호를 모두 입력하셔야 합니다.");
				swal("현재 비밀번호와 변경 비밀번호를 모두 입력하셔야 합니다.");
			} else if (!isPW.test(new_pw)) {
				//swal("8~16자 영문 대 소문자, 숫자, 특수문자를 사용하세요.");
				swal("8~16자 영문 대 소문자, 숫자, 특수문자를 사용하세요.");
			} else if (new_pw!=new_pw_confirm) {
				//swal("비밀번호를 다시 확인해주세요.");
				swal("비밀번호를 다시 확인해주세요.");
			} else {
				$.ajax({
					type : 'POST',
					dataType : 'text',
					url : '/semiRecipe/recipe/checkNowPw',
					data : 'loginId='+$('#loginId').text()+'&now_pw=' + now_pw,
					success : function(res) {
						if (res != "") {
							swal(res);
						} else {
							/*var chk = confirm('수정하시겠습니까?');
							if (chk) {				// 확인을 누르면
								pwChangeAfter();
							}*/
							swal({
								  title: "수정하시겠습니까?",
								  icon: "warning",
								  buttons: true,
								  dangerMode: true,
								})
								.then((willDelete) => {
								  if (willDelete) {
									  pwChangeAfter();
								  } 
								});
						}
					}
				});
			}
			
			return false;
		}
	});
	
	$('#pwChangeCancleBtn').on('click', function() {
		$('.pwHide').css('visibility','hidden');
		return false;
	});
	
	$('.iconHide a').on('click',function() {
		var src = $(this).children().attr('src');
		$('#user_icon img').attr('src', src);
		return false;
	});
	
	
	$('#iconChangeBtn').on('click', function() { // 아이콘 변경버튼 눌렀을 때
		if ($('.iconHide').css('visibility') == "hidden") { // 변경버튼 처음 눌렀을 때
			$('.iconHide').css('visibility','visible');
		} else { // 변경버튼 눌러서 입력 값 설정한 후 다시 변경버튼 눌렀을 때
			$('.iconHide').css('visibility','hidden');
		}
		return false;

	});
	
	$('#iconChangeCancleBtn').on('click', function() {
		$('.iconHide').css('visibility','hidden');
		$('#user_icon img').attr('src', preImgSrc);
		return false;
	});
	
	
	$('#nChangeBtn').on('click', function() { // 닉네임 변경버튼 눌렀을 때
		if ($('.nHide').css('visibility') == "hidden") { // 변경버튼 처음 눌렀을 때
			$('.nHide').css('visibility','visible');
			return false;
		} else { // 변경버튼 눌러서 입력 값 설정한 후 다시 변경버튼 눌렀을 때
			var nickname = $('#nBox').val();
			var nonchar = /^[가-힣a-zA-Z0-9]{2,10}$/;

			if (nickname == "") {
				swal("필수 정보입니다.");
			} else if (!nonchar.test(nickname)) {
				swal("2~10자의 한글, 영문, 숫자만 사용할 수 있습니다.");
			} else if (nickname == '${dto.nickname}') {
				swal("현재 사용중인 닉네임입니다.");
			} else {
				$.ajax({
					type : 'POST',
					dataType : 'text',
					url : '/semiRecipe/recipe/checkNickname',
					data : 'nickname=' + nickname,
					success : function(res) {
						if (res != "") {
							swal(res);
						} else {
							/*var chk = confirm('수정하시겠습니까?');
							if (chk) {				// 확인을 누르면
								$('#nValue').text(nickname);
								nChangeAfter();
							}*/
							swal({
								  title: "수정하시겠습니까?",
								  icon: "warning",
								  buttons: true,
								  dangerMode: true,
								})
								.then((willDelete) => {
								  if (willDelete) {
									  $('#nValue').text(nickname);
										nChangeAfter();
								  } 
								});
						}

					}
				});
			}

			return false;
		}

	});
	
	$('#nChangeCancleBtn').on('click', function() {
		$('.nHide').css('visibility','hidden');
		return false;
	});

	$('#birthChangeBtn').on('click', function() {		// 생년월일 수정 버튼 눌렀을 때
		var year = $('#yy').val();
		var month = $('#mm').val();
		var day = $('#dd').val();
		
		if($('.birthHide').css('visibility') == "hidden"){		// 처음 눌렀을 때 (수정 전)
			$('.birthHide').css('visibility','visible');
			
			removeYear();
			removeMonth();
			removeDay();
			getBirthday();

			return false;
		} else {									// 수정 후에 눌렀을 때
			if(year!="" && month!="" && day!="") {
				if(month<10){
					month = "0"+month;
				}
				if(day<10){
					day = "0"+day;
				}
				
				/*var chk = confirm('수정하시겠습니까?');
				if (chk) {				// 확인을 누르면
					$('#birthValue').text(year+"-"+month+"-"+day);
					birthChangeAfter();
					return false;
				}*/
				swal({
					  title: "수정하시겠습니까?",
					  icon: "warning",
					  buttons: true,
					  dangerMode: true,
					})
					.then((willDelete) => {
					  if (willDelete) {
						  $('#birthValue').text(year+"-"+month+"-"+day);
							birthChangeAfter();
					  } 
					});
				return false;
			}else {
				swal("생년월일을 정확하게 선택해주세요.");
				return false;
			}
		}
		
	});
	
	$('#birthChangeCancleBtn').on('click', function() {			// 생일 변경 취소 버튼 눌렀을 때
		$('.birthHide').css('visibility','hidden');
		return false;
	});
	
	$('#yy').change(function(){
		$('#mm').val("");
		$('#dd').val("");
		removeMonth();
		removeDay();
		appendMonth();
		return false;
	});
	
	$('#mm').change(function(){
		$('#dd').val("");
		var day = validDate($('#yy').val(), $('#mm').val());
		removeDay();
		appendDay(day);
		return false;
	});
	
	
	$('#modCancleBtn').on('click', function() {				// 제일 하단에 취소 버튼 눌렀을 때
/*		var cancleCheck = confirm('수정을 취소하시겠습니까?'); 
		if(cancleCheck) {
			location.href=document.referrer;
		} */
		swal({
			  title: "수정을 취소하시겠습니까?",
			  icon: "warning",
			  buttons: true,
			  dangerMode: true,
			})
			.then((willDelete) => {
			  if (willDelete) {
				  location.href=document.referrer;
			  } 
			});
		return false;
	});
	
	$('#modFinishBtn').on('click', function() {				// 제일 하단에 수정 완료 버튼 눌렀을 때
		var icon_src = $('#user_icon img').attr('src').split("/");
		var new_icon = $('#user_icon img').attr('src').split("/")[icon_src.length-1];
		
		var new_nickname = $('#nValue').text();
		var new_pw = $('#new_pw').val();
		var new_birth = $('#birthValue').text();
		
		if($('.pwHide').css('visibility')=='visible'){
			swal('비밀번호 변경을 확인해주세요.');
			return false;
		}
		
		if($('.nHide').css('visibility')=='visible'){
			swal('닉네임 변경을 확인해주세요.');
			return false;
		}
		
		swal({
			  title: "수정하시겠습니까?",
			  icon: "warning",
			  buttons: true,
			  dangerMode: true,
			})
			.then((willDelete) => {
			  if (willDelete) {			// 확인 버튼을 눌렀으면
						if(new_nickname!="") {
							updateNickname(new_nickname);
						}
						
						if(new_pw!="") {
							updatePassword(new_pw);
						}
						
						if(new_birth!="") {
							updateBirthday(new_birth);
						}
						
						if(preImgSrc!=new_icon){
							updateIcon(new_icon);
						}
						
						
						swal('수정 완료되었습니다.');
						location.href=document.referrer;
						
				
			  } 
			  
			});
		return false;
		/*if(modCheck) {			// 확인 버튼을 눌렀으면
			if(new_nickname!="") {
				updateNickname(new_nickname);
			}
			
			if(new_pw!="") {
				updatePassword(new_pw);
			}
			
			if(new_birth!="") {
				updateBirthday(new_birth);
			}
			
			if(preImgSrc!=new_icon){
				updateIcon(new_icon);
			}
			
			
			swal('수정 완료되었습니다.');
			location.href=document.referrer;
			
		} */
	});
	

});

function nChangeAfter() {
	$('.nHide').css('visibility','hidden');
} 

function pwChangeAfter() {
	$('.pwHide').css('visibility','hidden');
}

function birthChangeAfter() {
	$('.birthHide').css('visibility','hidden');
} 


function updatePassword(new_pw) {
	$.ajax({
		type : 'POST',
		dataType : 'text',
		url : '/semiRecipe/recipe/infoUpdate/pw',
		data : 'pw=' + new_pw,
		success : function() {
		}
	});
}

function updateNickname(new_nickname) {
	$.ajax({
		type : 'POST',
		dataType : 'text',
		url : '/semiRecipe/recipe/infoUpdate/nickname',
		data : 'nickname=' + new_nickname,
		success : function() {
		}
	});
}

function updateBirthday(new_birth) {
	$.ajax({
		type : 'POST',
		dataType : 'text',
		url : '/semiRecipe/recipe/infoUpdate/birthday',
		data : 'birthday=' + new_birth,
		success : function() {
		}
	});
}

function updateIcon(new_icon) {
	$.ajax({
		type : 'POST',
		dataType : 'text',
		url : '/semiRecipe/recipe/infoUpdate/icon',
		data : 'icon=' + new_icon,
		success : function() {
		}
	});
}

function getYear() {
	var birthValue = $('#birthValue').text();
	var beforeYear = birthValue.split("-")[0];

	var date = new Date();
	var year = date.getFullYear();

	for(var i=year;i>=1900;i--) {
		$('#yy').append(new Option(i+"년",i));                        
	}
	
	$("#yy").val(beforeYear).prop("selected", true);
}

function getMonth() {
	var birthValue = $('#birthValue').text();
	var beforeMonth = birthValue.split("-")[1];
	
	for(var i=1;i<=12;i++){
		$('#mm').append(new Option(i+"월",i));
	}
	
	$("#mm").val(Number(beforeMonth)).prop("selected", true);
}

function getDay(day) {
	var birthValue = $('#birthValue').text();
	var beforeDay = birthValue.split("-")[2];
	
	for(var i=1;i<=day;i++) {
		$('#dd').append(new Option(i+"일",i));
	}
	
	$("#dd").val(Number(beforeDay)).prop("selected", true);
}

function getBirthday() {
	var birthValue = $('#birthValue').text();
	var year = birthValue.split("-")[0];
	var month = birthValue.split("-")[1];
	
	getYear();
	getMonth();
	var day = validDate(year, month);
	getDay(day);
}

function appendYear() {
	var date = new Date();
	var year = date.getFullYear();
	var selectValue = document.getElementById("yy");

	for(var i=1900;i<=year;i++) {
		selectValue.add(new Option(i+"년",i));                        
	}
}

function removeYear() {
	$("#yy.sel option:gt(0)").remove();
}

function appendMonth() {
	var selectValue = document.getElementById("mm"); 

	for(var i=1;i<=12;i++){
		selectValue.add(new Option(i+"월",i));
	}
}

function removeMonth() {
	$("#mm.sel option:gt(0)").remove();
}


function validDate(year, month) {
    var monthArr = [31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31];
    var day;
    if(month !== "2" && $.isNumeric(month)) {
        day = monthArr[Number(month) - 1];
    }
    else {
        if($.isNumeric(year)) {
            var intYear = Number(year);
            if((intYear%4 === 0 && intYear%100 !== 0) || intYear%400 === 0) {
                day = 29;
            }
            else {
                day = 28;
            }
        }
        else {
            day = 31;
        }
    }
    return day;
};

function appendDay(day) {
	var selectValue = document.getElementById("dd");

	for(var i=1;i<=day;i++) {
			selectValue.add(new Option(i+"일",i));
	}
} 

function removeDay() {
	$("#dd.sel option:gt(0)").remove();
}
