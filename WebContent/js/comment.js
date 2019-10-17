var star_rate=0;
var star_num=0;
var rownum=5;

$(document).ready(function(){	
	$('#CMT_input').on('focus',function(){
		if($(this).val()==""){
			$(this).css({'width':'0px','border-bottom':'1px solid rgba(10,10,10,0.8)'}).animate({'width':'520px'},220);
			$('#CMT_submit').show();
			$('#CMT_reset').show();
		}
	}).on('focusout',function(){
		if($(this).val()==""){
			$(this).css({'border-bottom':'1px solid rgba(10,10,10,0.2)'});				
			$('#CMT_submit').hide();
			$('#CMT_reset').hide();
		}		
	}).on('keydown keyup', function () {
		$(this).height(1).height( $(this).prop('scrollHeight')+12 );	
	});



	$('#CMT_reset').on('click',function(){
		$('#CMT_input').css({'border-bottom':'1px solid rgba(10,10,10,0.2)'});				
		$('#CMT_submit').hide();
		$('#CMT_reset').hide();
		$('#CMT_input').val('');
		star_num=0;
		rating(star_num);
	})

	
	$('.star').on('click',function(){
		star_num=$(this).attr('id').split('_')[1];
		rating(star_num,'#star_');
		$('#rate').attr('value',star_num);
	})
	
	$('.star').on('mouseover',function(){
		star_rate=$(this).attr('id').split('_')[1];
		rating(star_rate,'#star_');
	})	

	$('#star_div').on('mouseleave',function(){
		rating(star_num,'#star_');
	})
	
	var con = $('.rated_div');
	for(var i=0;i<con.length;i++ ){
		var num = con.eq(i).find('.rate_num').attr('id');
		var id = '#'+con.eq(i).attr('id')+".rated_div #rate_";
		rating(num,id)
		
	}
	

	
	$('#CMT #order').change(function(){
		comList();
	})
	
	$('#showMore').click(function(){
		rownum = parseInt($('#CMT .rated_div').last().attr('id'))+5;
		
		comList();
	})
})
function rating(num,id){
	var classNM = ".rated";
	if(id=='#star_'){
		classNM='';
	}
	for(var i=1;i<=5;i++){
		if(i<=num)
		$(id+i+classNM).css('opacity','0.9');
		else
			$(id+i+classNM).css('opacity','0.3');
	}
}
function comList(){
	
	
	if(typeof rownum == "undefined" || rownum == null || rownum == "")
		rownum = 5;
	$.ajax({
		type:'POST',
		dataType:'text',
		data:'com_board='+$('#CMT>div').attr('id')+'&key='+$('#CMT #key').val()+'&order='+$('#CMT #order').val()+'&rownum='+rownum,
		url:'comList',
		success: function(res){
			$('#CMT>div').empty();
			$('#CMT>div').html(res);
		}
	})
}
function insertCom(){
	var com_content = $('.insertCom #CMT_input').val();
	var user_id = $('.insertCom #user_id').val();
	var rating = $('.insertCom #rate').val();
	var com_board = $('#CMT>div').attr('id');
	var key = $('#CMT #key').val();
	if(user_id==''){
		//alert('먼저 로그인을 해주세요');
		swal({
			  title: "먼저 로그인을 해주세요!!><",
			  text: "로그인 창으로 이동하시겠습니까?",
			  icon: "error",
			  buttons: true,
			  dangerMode: true,
			})
			.then((willDelete) => {
			  if (willDelete) {
			    location.href='loginForm';
			  } 
			});
		return;
	}
	$.ajax({
		type:'GET',
		dataType:'text',
		data:'com_board='+com_board+'&key='+key
		+'&user_id='+user_id+'&rating='+rating+'&com_content='+com_content,
		url:'insertCom',
		success: function(res){
			comList();
			var tot = $('#total').text();
			tot = parseInt(tot)+1;
			$('#total').text(tot);
		}
	})
}
