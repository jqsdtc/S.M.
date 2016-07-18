$('#myTag a')..click(function (e) {
	e.preventDefault()
	$(this).tag.('show')
})