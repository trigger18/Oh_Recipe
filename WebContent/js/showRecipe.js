
$(document).ready(function(){
	$('.thumbnails>div>div').on('click',function(){
		showRecipe($(this).attr('id'),"#selected_recipe");
		return false;
	});
	
	
})

function showRecipe(selected,selector){
	$.ajax({
		type:'GET',
		dataType:'text',
		data:'recipe_id='+selected,
		url:'showRecipe',
		success: function(res){
			$(selector).html(res);
			
		}
	})
	var offset = $(selector).offset();
    $('html, body').animate({scrollTop : offset.top}, 400);    
}


