
var showErrorMsg="";

$(document).ready(function() {
	appendYear();
	
	$('#logo').on('click',function() {
		location.href = "/semiRecipe/recipe/home";
	});
	
	$('.iconwrap').css('visibility', 'hidden');
	
	var icon_src = $('#icon').attr('src').split("/");
	var user_icon = $('#icon').attr('src').split("/")[icon_src.length-1];

	$('#user_icon').val(user_icon);
	$('#user_icon_before').val($('#icon').attr('src'));
	
	$('.iconwrap a').click(function(){
		var src = $(this).children().attr('src');
		$('#icon').attr('src',src);
		return false;
	});	
	
	$('#iconSelectBtn').on('click', function() {	// 아이콘 선택하기 버튼 누르면
		$('#user_icon_before').val($('#icon').attr('src'));
		$('#user_icon').val($('#icon').attr('src').split("/")[icon_src.length-1]);
		
		if($('.iconwrap').css('visibility')=='hidden'){	// 맨 처음 눌렀을 때
			$('.iconwrap').css('visibility', 'visible');
			$(this).text('아이콘 선택완료');
		}else{
			$('.iconwrap').css('visibility', 'hidden');
			$(this).text('아이콘 선택하기');
		}
		
		return false;
	});
	
	$('input[value="회원가입"]').click(function(){		// 회원가입 버튼을 누르면
		if(!inputChk()) { 	// return값이 false이면
			swal('빈 칸을 모두 입력해 주세요.'); 
		}else if($('.error_next_box').text()!=""){
			swal("입력한 값을 확인해주세요.");
		}else if($('.iconwrap').css('visibility')=='visible') {
			swal("아이콘 선택을 완료해주세요");
		}else if($('#icon').attr('src').indexOf('default')>-1) {
			swal("아이콘 선택을 완료해주세요");
		}else{
			$('form').attr('action','register');
			$('form').submit();
		}
		
		return;
	});
	
	$('#yy').change(function(){
		$('#mm').val("");
		$('#dd').val("");
		removeMonth();
		removeDay();
		appendMonth();
	});
	
	$('#mm').change(function(){
		$('#dd').val("");
		var day = validDate($('#yy').val(), $('#mm').val());
		removeDay();
		appendDay(day);
	});
	
	$('input[type="reset"]').on('click', function() {
		var chk = confirm("돌아가시겠습니까?");
		if(!chk)	// 취소이면
			return false;
		else
			location.href=document.referrer;
	});
	
	
	$('#id').blur(function(){
		var eventId = $(this).attr('id');
		var id = $("#id").val();
		var isID = /^[a-z0-9][a-z0-9_\-]{4,19}$/;
		
		if (id == "") {
			showErrorMsg = "필수 정보입니다.";
			changeErrorMsg(eventId, showErrorMsg);
		} else if (!isID.test(id)) {
			showErrorMsg = "5~20자의 영문 소문자, 숫자와 특수기호(_),(-)만 사용 가능합니다.";
			changeErrorMsg(eventId, showErrorMsg);
		} else if(id != "" && isID.test(id)){
			$.ajax({
                type:'POST',
                dataType:'text',
                url:'/semiRecipe/recipe/checkID',
                data:'id='+$(this).val(),
                success: function(res) {
                	showErrorMsg = res;
                	changeErrorMsg(eventId, showErrorMsg);
                }
			});
		} else {
			showErrorMsg="";
			changeErrorMsg(eventId, showErrorMsg);
		}
		
		return false;
	});
	
	if($('#id').val()){
		$('#id').trigger('blur');
	};
	
	
	$('#pw').blur(function(){
		var eventId = $(this).attr('id');
		var pw = $('#pw').val();
		var isPW = /^[A-Za-z0-9`\-=\\\[\];',\./~!@#\$%\^&\*\(\)_\+|\{\}:"<>\?]{8,16}$/;

		if (pw == "") {
			showErrorMsg = "필수 정보입니다.";
			changeErrorMsg(eventId, showErrorMsg);
		} else if (!isPW.test(pw)) {
			showErrorMsg = "8~16자 영문 대 소문자, 숫자, 특수문자를 사용하세요.";
			changeErrorMsg(eventId, showErrorMsg);
		} else {
			showErrorMsg="";
			changeErrorMsg(eventId, showErrorMsg);
		}
		
		return false;
	});
	
	$('#pwConfirm').blur(function(){
		var eventId = $(this).attr('id');
		var pwConfirm = $('#pwConfirm').val();
		
		if ($('#pw').val()!=pwConfirm) {
			showErrorMsg = "비밀번호를 다시 확인해주세요";
			changeErrorMsg(eventId, showErrorMsg);
		} else {
			showErrorMsg="";
			changeErrorMsg(eventId, showErrorMsg);
		}
		
		return false;
	});
	
	$('#nickname').blur(function() {
		var eventId = $(this).attr('id');
		var nickname = $('#nickname').val();
		var nonchar = /^[가-힣a-zA-Z0-9]{2,10}$/;

		if (nickname == "") {
			showErrorMsg = "필수 정보입니다.";
			changeErrorMsg(eventId, showErrorMsg);
		} else if (!nonchar.test(nickname)) {
			showErrorMsg = "2~10자의 한글, 영문, 숫자만 사용할 수 있습니다.";
			changeErrorMsg(eventId, showErrorMsg);
		} else if(nickname != "" && nonchar.test(nickname)){
			$.ajax({
                type:'POST',
                dataType:'text',
                url:'/semiRecipe/recipe/checkNickname',
                data:'nickname='+$(this).val(),
                success: function(res) {
                	showErrorMsg = res;
                	changeErrorMsg(eventId, showErrorMsg);
                }
			});
		} else {
			showErrorMsg="";
			changeErrorMsg(eventId, showErrorMsg);
		}
		
		return false;
		
	});
	
	if($('#nickname').val()){
		$('#nickname').trigger('blur');
	};
	
	
	var year = $('#yy').val();
	var month = $('#mm').val();
	var day = $('#dd').val();
		
});

// option 추가 new Option("option text", "value");
function appendYear() {
	var date = new Date();
	var year = date.getFullYear();

	for(var i=year;i>=1900;i--) {
		$('#yy').append(new Option(i+"년",i));                        
	}
}

function appendMonth() {
	for(var i=1;i<=12;i++){
		$('#mm').append(new Option(i+"월",i));
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
	for(var i=1;i<=day;i++) {
		$('#dd').append(new Option(i+"일",i));
	}
} 

function removeDay() {
	$("#dd.sel option:gt(0)").remove();
}

function changeErrorMsg(eventId, showErrorMsg){
	$('#'+eventId+'Msg').text(showErrorMsg);
}

function inputChk() {
	var result = true;
	$('.val').each(function(idx, ele) {
		if ($(ele).val() == "") {
			$(ele).focus();
			result = false;
			return false;
		}
	});
	return result;
}

