$(function(){
	$('#custom-content div ul li a').click(function(){
		$('#custom-content div ul li a').removeClass('on');
		$(this).addClass('on');
	})
})