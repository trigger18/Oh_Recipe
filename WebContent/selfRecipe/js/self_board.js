
$(document).ready(function(){
	selfDelete();
	/*********************************************************************************/
	
	//irdntDrop();
		
		$('#quick_menu #update').click(function(){
			var chk = false;
			$('input[name="radi"]').each(function(i,v){
				if($(v).prop('checked')){
					chk=true;
				}
				
			})
			if(!chk){
				alert("글을 선택하세요");
				return false;
			}
			return true;
		})
	
		
	
	
		var list_cnt = 550;
		var column = '';
		var order = '';
		var nation_nm = '';
		var recipe_nm_ko = '';
		var searchType = '';

		$('.sort,.icon-search,#searchText').on('click keydown',
				function listAjax(key) {
					if ($(this).is('.sort')) {
						column = $(this).children('.column').val();
						order = $(this).children('.order').val();
					}
					if($(this).is('#searchText')){
						if(key.keyCode != 13){
							return;
						}
					}
					
					if(key.keyCode == 13 || $(this).is('.icon-search')){
						recipe_nm_ko = $('#searchText').val();
						searchType = $('#searchDrop').val();
					}
					
					$.ajax({
						type : 'POST',
						dataType : 'text',
						data : 'column=' + column + '&order=' + order
								 + '&recipe_nm_ko=' + recipe_nm_ko
								+ '&searchType=' + searchType,
						url : 'selfRecipe',
						success : function(res) {
							$('.thumbnails>div').empty();
							$('.thumbnails>div').html(res);
							$('.thumbnails').height(550);
						}
					})
					if (column == 'recipe_nm_ko') {
						if ($('#sortNM .order').val() == 'desc')
							$('#sortNM .order').val('asc');
						else if ($('#sortNM .order').val() == 'asc')
							$('#sortNM .order').val('desc');
					}
					return false;
				})

		function load(id, cnt) {
			var hei = $('.thumbnails').height();
			hei = hei + cnt;
			if($('.thumbnails').height()<=$('.thumbnails #listSize').height()){
				$('.thumbnails').height(hei);
			}
		}

		var defTop = parseInt($('#quick_menu').css("top"));
		$(document).scroll(
				function() {
					if ($(document).height() == ($(window).height() + $(document).scrollTop())) {
						load('.thumbnails', list_cnt);
					}
					$('#quick_menu').stop().animate({
						top : defTop + $(this).scrollTop()
					}, 100);
				})
		
	
				
	
	/*recipe_id 보내주는 부분*/
	$('.thumbnails .js-load span a').on('click', function() {
			$.ajax({
				type : 'POST',
				dataType : 'text',
				data : 'recipe_id=' + $(this).parents().parents().attr('id'),
				url : 'selfView',
				success : function(res) {
					$('#selected_recipe').html(res);
					var offset = $('#selected_recipe').offset();
				    $('html, body').animate({scrollTop : offset.top}, 400);    
					}
			});
			return false;
	});
	
		
	
		
	$('#update').on('click', function(){
		var chk = $("input[name='radi']:checked").val();
		$.ajax({
			type : 'GET',
			dataType : 'text',
			data : 'recipe_id=' + chk,
			url : 'selfUpdate',
			success : function(res) { $('#selected_recipe').html(res);}
		});
		return false;
	});

}); 


function selfDelete(){
$('#del').on('click', function(){
	var chk = false;
	$('input[name="radi"]').each(function(i,v){
		if($(v).prop('checked')){
			chk=true;
		}
		
	})
	if(!chk){
		alert("글을 선택하세요");
		return false;
	}
	var chk = $("input[name='radi']:checked").val();	
    window.location.href="selfDel?recipe_id="+chk;
});

}

