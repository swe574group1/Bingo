$(document).ready(function(){
	// message sending.
	$("#pm").dialog({
		autoOpen: false,
		modal: true
	});
	
	// attach handlers for message forms.
	$(".pm").click(function() {
		$("#pm").dialog("open");
		
		return false;
	});
});