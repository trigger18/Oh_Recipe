$(document).ready(function() {
	insertImg();
	numberClick();
	selfRecipeDivAddDel(); 
	cookingTime();
	irdntDrop();
	totalCalorie();

	
	$('.close_ing').on('click',function() {
		$(this).parents('.selected_ing').remove();
			viewList();
	})


	
	$("#imgInput").change(function(){
		imagePreview(this);
	});
	
	checkfiile();
	insertImgCheck();
	
	/*음식 분류 요리 난이도*/
	$('.natioin_nm').each(function(i,v){
		if($(v).val()==$('#nation_nmH').val()){
			
			$(v).attr('checked','checked');
		}
	})
	
	$('.level_nm').each(function(i,v){
		if($(v).val() == $('#level_nmH').val()){
			$(v).attr('checked', 'checked');
		}
	})
	
	$('.selected_ing #imp option').each(function(i,v){
		if($(v).val() == $('.selected_ing #impH').val()){
			$(v).attr('selected', 'selected');
		}
	})
});// end ready()
/*이미지 삽입 버튼*/
function insertImg(){
	$('#insertImg').on('click', function(){
		$('#imgInput').click();
	})
	
}

//이미지삽입 체크
function insertImgCheck(){ 
	$('form').submit(function(){
		var str = $('#imgInput').val();
		if(str.length < 1){
			alert('이미지를 선택하세요.');
			return false;
		}
	});
}



/*이미지 확장자 체크*/
function checkfiile(){
	var pattern = /([^\s]+(?=\.(jpg|gif|png|bmp|jpeg))\.\2)/;
	var file = $('#imgInput');
	$(file).on('change propertychange', function(){
		if (!pattern.test(file.val()))
			alert("이미지 파일만 등록 가능합니다.");
			return false;
	});
	
/*	if(this.files[0].size > 50000000) {
		alert('5MB 이하만 첨부할 수 있습니다.');
		$(file).val();
		return false;
	}*/
}
	
/* 이미지 업로드시 이미지 바로보이기 */
function imagePreview(input) {	
	if (input.files && input.files[0]) {		
		var reader = new FileReader();
		
		reader.onload = function(e) {
			console.log("test:"+e.target.result)
			$('#image_preview').attr('src', e.target.result);
		}
		reader.readAsDataURL(input.files[0]);
	}
}





	/*칼로리*/
function totalCalorie(){
	$('.R_cal').on('mousedown', function(){
		var totalCalorie;
		var four = $('#R_cal_four').text();
		var three = $('#R_cal_three').text();
		var two = $('#R_cal_two').text();
		var one = $('#R_cal_one').text();
		totalCalorie = four + three + two + one;
		$('#calorie').val(totalCalorie);
		console.log(totalCalorie);
	});
};
	/*요리시간*/
function cookingTime(){
	$('.cook_time').on('mousedown', function(){
		var time = 0;
		if($('#R_hour').text()!='0' ||  $('#R_minute_two').text()!='0' || $('#R_minute_one').text()!='0'){
		var min = ($('#R_minute_two').text() + $('#R_minute_one').text())*1;
			time = $('#R_hour').text()*60 + min;
		}	
		$('#cooking_time').val(time);
		console.log(time);
		})	
}

function cookingTeimView(){
	var calorieH = $('#calorieH').val();
	var h = 0;
	var m = 0;
	
	h = calorieH / 60;
	m = calorieH % 60;
	$('#R_hour').text(h);
	$('#R_minute_two').text(m);
	
	
	
}

	/*재료 드랍하기*/
function irdntDrop() {
	
	$('.R_ig_div').droppable({
		drop : function(event, ui) {
			console.log("drop")
			var aaaa= $('.selected_ing');
			var selected = false;
			for(var i=0; i<aaaa.length;i++){
				if(aaaa.eq(i).children('p').text() == name){
					selected=true;
				}
			}
			if(selected){
				alert("이미 선택된 재료입니다..");								
			}else{	
				if ($(this).children('.selected_ing').length < 5) {
					var selected = $(this).html()
					+ " <div class='selected_ing'><input type ='hidden'value='"+name+"' id='ing'>" +
							"<div class='close_ing'></div><p>"+ name
					+ "</p><select name='imp' id='imp'><option value='필수'>필수</option>" +
							"<option value='상'>상</option><option value='중'>중</option>" +
							"<option value='하'>하</option></select></div>";				
					$(this).html(selected);
				}else{
					alert("재료가 너무많아..");						
				}
			}
			
			
			
			//드롭된 재료들 삭제
			$('.close_ing') .on('click', function() {
				$(this).parents('.selected_ing').remove();
				viewList();
			})
			}
	});
};


	/* 세부 레시피 입력창 추가 삭제 */
function selfRecipeDivAddDel() {
		$('#w_add').on('click', function() {
			if ($('.R_recipe').length == 20) {return};
			$('.self_recipeDiv_part').append("<tr><td><span>"+ ($('.R_recipe').length+1) +"</span></td><td><textarea class='R_recipe' rows='3' cols='100' name='recipe_dc'></textarea></td></tr>");
		});
		
		$('#w_del').on('click', function() {
			if ($('.R_recipe').length == 1) {return};
			$(".self_recipeDiv_part tr").last().remove();
		});
	}




	/*시간, 칼로리 클릭 업다운*/
function numberClick(){
	var o = 0;
	var x = 0;
	var y = 0;
	var z = 0;
	var h = 0;
	var mt = 0;
	var mo = 0;
	
	/* 시간 */
	$('#R_hour').mousedown(function(e) {
		if (e.which == 1) {
			$('#R_hour').text(h += 1);
		}
		if (h > 0) {
			if (e.which == 3) {
				$('#R_hour').text(h -= 1);
			}
		}
	});
	/* 분(십단위) */
	$('#R_minute_two').mousedown(function(e) {
		if(mt >= 5 ) {
			mt = -1;
		}
		if (e.which == 1){
			$('#R_minute_two').text(mt += 1);
		}
		
		if (mt > 0) {
			if (e.which == 3){
				$('#R_minute_two').text(mt -= 1);
			}
		}
	});
	/* 분(일단위) */
	$('#R_minute_one').mousedown(function(e) {
		if(mo >= 9 ) {
			mo = -1;
		}
		if (e.which == 1){
			$('#R_minute_one').text(mo += 1);
		}
		
		if (mo > 0) {
			if (e.which == 3){
				$('#R_minute_one').text(mo -= 1);
			}
		}
	});

	/* 천단위 */
	$('#R_cal_four').mousedown(function(e) {
		if (o >= 9) {
			o = -1;
		}
		if (e.which == 1) {
			$('#R_cal_four').text(o += 1);
		}
		if (o > 0) {
			if (e.which == 3) {
				$('#R_cal_four').text(o -= 1);
			}
		}
	});
	
	/* 백단위 */
	$('#R_cal_three').mousedown(function(e) {
		if (x >= 9) {
			x = -1;
		}
		if (e.which == 1) {
			$('#R_cal_three').text(x += 1);
		}
		if (x > 0) {
			if (e.which == 3) {
				$('#R_cal_three').text(x -= 1);
			}
		}
	});
	
	/* 십단위 */
	$('#R_cal_two').mousedown(function(e) {
		if (y >= 9) {
			y = -1;
		}
		if (e.which == 1) {
			$('#R_cal_two').text(y += 1);
		}
		if (y > 0) {
			if (e.which == 3) {
				$('#R_cal_two').text(y -= 1);
			}
		}
	});
	
	/* 일단위 */
	$('#R_cal_one').mousedown(function(e) {
		if (z >= 9) {
			z = -1;
		}
		if (e.which == 1) {
			$('#R_cal_one').text(z += 1);
		}
		if (z > 0) {
			if (e.which == 3) {
				$('#R_cal_one').text(z -= 1);
			}
		}
	});
	
	$('#self_tilte').on('click', function() {
		$(this).val("");
	});
	$('#self_sumry').on('click', function(){
		$(this).val("");
	})
}


		  






