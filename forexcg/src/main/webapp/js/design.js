$(function(){	
	$('#header .btn_sh').click(function(){
		$('#header').toggleClass('on');
	});
	$('#aside_left .btn_sh').click(function(){
		$('#aside_left').toggleClass('on');
	});
	$('#header .btn_help').click(function(){
		$('#aside_right').addClass('on');
	});
	$('#aside_right .btn_close').click(function(){
		$('#aside_right').removeClass('on');
	});
	$('#contents').click(function(){
		$('#header').removeClass('on');
		$('#aside_left').removeClass('on');
		$('#aside_right').removeClass('on');
	});
	$('#aside_left .gnb .depth1 > a').click(function(){
		$('#aside_left .gnb .depth1').removeClass('on');
		$(this).parent('.depth1').toggleClass('on');
	});
	$('#aside_left .gnb .depth2 > a').click(function(){
		$('#aside_left .gnb .depth2').removeClass('on');
		$(this).parent('.depth2').toggleClass('on');
	});
});