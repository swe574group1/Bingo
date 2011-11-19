 $(document).ready(function(){
	 
	 $('.tab_left a').click(function() {
	 	target_div = $(this).attr('target_content');
		
		$('.tab_left a').removeClass('active');
		$(this).addClass('active');
		
		$('.tab_right div.tab_content').slideUp('fast');
		$('#'+target_div).slideDown('fast');
		
		return false;
	 })
	 
 });
 