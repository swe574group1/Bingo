 $(document).ready(function(){
	 $('.user_tabs a').click(function() {
		$('.index_tab_content').slideUp('fast');
		$('#index_tab_content_'+$(this).attr('no')).slideDown('fast');
		$('.user_tabs a').removeClass('active');
		$(this).addClass('active');
	 })	 
 });
 