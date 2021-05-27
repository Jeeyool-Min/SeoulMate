/**
 * 
 */

var chooseAreaPopup = {
	init:function(){
		this.dimClickEv();
		this.activeBtn;
	},
	layerShow:function(targetLayer,input){
		this.activeBtn = $(document.activeElement);
		this.wrap = $('#'+ targetLayer);
		this.wrap.append('<div class="dimmed"></div>');
		this.popup = this.wrap.find('.layerpop');

		// (공통)더보기
		if(this.wrap.show()){
			$('.list_thumType li .btn_view').removeClass('on');
			$('.list_reply li .btn_view').removeClass('on');
			$('.tit_cont .btn_titview').removeClass('on');
			$('.list_board1 li .btn_view').removeClass('on');
		}

		function popResize(){
			this.wrap = $('#'+ targetLayer);
			this.wrap.css('position','absolute');
			this.wrap2 = $('#'+ input);
			this.popup = this.wrap.find('.layerpop');

			var popupTop = wrap2.offset().top;
			
			var height1 = popupTop+wrap2.height();
			var height2 = height1 /2;
			
			// default x, y 
			this.popup.css({ //	0203 수정
				'left' : '50%',
				'top' : popupTop+wrap2.height()+5+'px',
			});
			
		}
		popResize();

		$(window).resize(function(){
			popResize();
		})
		// 마이페이지 > 코스 > 이용가이드 레이어팝업 slider
		popCugSlider();
	},
	layerHide:function(targetLayer){
		this.wrap.removeAttr('tabindex').hide();
		$('.dimmed').remove();
		$('.dimmed2').remove();
		this.activeBtn.focus();
		
		if(targetLayer == 'detailPop')
			$("meta[name=viewport]").attr("content", "width-device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no");
			
		$('body').css({
			'overflow' : 'auto'
		});
	},
	dimClickEv:function(){
		$(document).on('click touchend', '.dimmed', function(){
			$('.wrap_layerpop').hide();
			$(this).remove();
			$('.dimmed2').remove();

			$('body').css({
				'overflow' : 'auto'
			});
		})
	}
}