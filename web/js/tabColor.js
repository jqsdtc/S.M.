$(function(){
	$('#tool-content div ul li').click(function(){
		$('#tool-content div ul li').removeClass('on');
		$(this).addClass('on');
	})
})