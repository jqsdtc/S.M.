
$(document).ready(function(){
	$('.toolTabs a').click(function(){
		var $this = $(this);
		$('.toolNo').hide();
		$('.toolTabs li.choose').removeClass('choose');
		$this.parent().addClass('choose');
		var toolNo = $this.attr('href');
		$(toolNo).fadeIn(250);
		return false;
	})
	$('.toolTabs li:first a').click();
})