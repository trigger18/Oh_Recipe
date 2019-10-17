
var name = "";
var isopen = false;
$(document)
.ready(
		function() {
			var sideBtnAnim = setInterval(function() {
				if(!isopen)
					$('#side_btn').animate({'margin-left':'-120px'},500).animate({'margin-left':'-101px'},500);
			},1000);
			
			$('.ingredients').on('mouseover',function(){
				$(this).clearQueue().animate({'border-radius':'66px','background-color':'rgb(30,30,29,0.9)'},200);

			})
			
			$('.ingredients').on('mouseleave',function(){
				$(this).animate({'border-radius':'12px','background-color':'rgb(10,10,10,0.6)'},200);
			})


			//재로 사이드메뉴 열기/닫기
			$('#side_btn').on('click', function() {
				if (isopen) {
					sideBtnAnim = setInterval(function() {
						if(!isopen)
							$('#side_btn').animate({'margin-left':'-120px'},500).animate({'margin-left':'-101px'},500);
					},1000);
					isopen = false;
					console.log('닫힘');
					$('#side_menu').animate({
						right : -700
					}, 800);
					$('#side_btn img').css({
						'transform' : 'rotate(180deg)'
					});
					$('#side_btn').animate({
						'margin-left' : -100
					}, 1200);

				} else {
					isopen = true;
					console.log('열림');

					$('#side_btn').stop();
					clearInterval(sideBtnAnim);

					var ig= $('.ingredients');
					console.log(ig.length);
					for(var i=0;i<ig.length;i++){
						if(ig.eq(i).attr('id').length>=5){
							ig.eq(i).css({
								'padding-top':'28px'
							});								
						}if(ig.eq(i).attr('id').length>=9){
							ig.eq(i).css({
								'padding-top':'32px',
								'font-size':'15px'
							});					
						}			

					}

					$('#side_menu').animate({
						right : 0
					}, 800);
					$('#side_btn img').css({
						'transform' : 'rotate(0)'
					});
					$('#side_btn').animate({
						'margin-left' : 0
					}, 300);
				}
			});

			//드래그하는 부분
			$('.ingredients').on('mousedown', function() {
				name = $(this).prop('id');
				$('#selected_ing_div').clearQueue().animate({'opacity':1},300);
			}).draggable({
				scroll : false,
				helper : "clone",
				revert : true,
				opacity : 0.90
			});

			//더블클릭하는 부분
			$('.ingredients')
			.on(
					'dblclick',
					function() {
						select_num++;
						if (select_num < 6) {
							var selected = $(
							'#selected_ing_div')
							.html()
							+ " <div class='selected_ing'><button class='close_ing'></button><p>"
							+ name + "</p></div>";
							$('#selected_ing_div').html(
									selected);
							viewList();
						} else {
							swal("재료는 5개까지만 선택 가능합니다..","다른 재료를 선택해주세요.","info");
						}

						//드롭된 재료들 삭제
						$('.close_ing')
						.on(
								'click',
								function() {
									$(this)
									.parents(
									'.selected_ing')
									.remove();
									select_num--;
									viewList();
								})
					});

			//검색기능 구현
			var ingredients_num = $('.ingredients').length;//처음 DB에서 받아온 갯수를 저장
			var ingredients_name = new Array();
			for (var i = 0; i < ingredients_num; i++) {
				ingredients_name[i] = $('.ingredients').eq(i).attr(
				'id');
			} //배열에 이름들을 저장

			$('#side_menu input')
			.on(
					'keyup',
					function() {
						$('#ingredients_menu_div ul li').removeClass('click');//선택된 항목을 삭제함
						var search = $(this).val();//현재 인풋창의 값을 받아옴
						var search_len = Hangul
						.disassemble(search).length;//현재 인풋창의 자음+모음수
						search = Hangul.assemble(search);

						$('.ingredients').hide();
						for (var i = 0; i < ingredients_num; i++) {
							//맞는것 실행시켜줌
							var ing_id = Hangul
							.disassemble(ingredients_name[i]
							+ "");//i번쨰 재료의 이름 를 자름
							var ing_len = ing_id.length;
							var ing_hangul = "";

							for (var j = 0; j < search_len; j++) {
								if (j < ing_len)
									ing_hangul += ing_id[j];
							}
							ing_hangul = Hangul
							.assemble(ing_hangul);//타자 친 숫자만큼 이름에서 가져옴            	
							if (search == ing_hangul) {
								$('#' + ingredients_name[i])
								.show();
							}

						}
					});


			//재료 항목 이동 버튼
			var ing_m_cnt=0;
			$('#ing_m_right').on('click',function(){
				var left = $('#ing_menu').css('margin-left').split('p')[0];
				if(ing_m_cnt>-4)
					ing_m_cnt--;
				$('#ing_menu').animate({'margin-left':(ing_m_cnt*150)+'px'},200);
			});

			$('#ing_m_left').on('click',function(){
				var left = $('#ing_menu').css('margin-left').split('p')[0];
				if(ing_m_cnt<0)
					ing_m_cnt++;
				$('#ing_menu').animate({'margin-left':(ing_m_cnt*150)+'px'},200);
			});




			//재료 항목 별 클릭
			$('#ingredients_menu_div ul li').click(function(){
				$('#ingredients_menu_div ul li').removeClass('click');
				$(this).addClass('click');
				var ty = $(this).attr('id');
				$('.ingredients').hide();
				$('.ingredients input').each(function(i,v){
					if($(v).attr('id')==ty){
						$(v).parent().show();
					}
				})
			})
		})
