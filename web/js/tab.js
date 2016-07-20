$(function(){
	$('#tool-content div ul li a').click(function(){
		$('#tool-content div ul li.on').removeClass('on');
		$(this).parent().addClass('on');
	})
})

$(document).ready(function(){
	$('.tabs a').click(function(){
		var $this = $(this);
		$('.panel').hide();
	    $('.tabs a.active').removeClass('active');
		$this.addClass('active').blur();
		var panel = $this.attr('href');
		$(panel).fadeIn(250);
		return false;
	})
	$('.tabs li:first a').click();
})