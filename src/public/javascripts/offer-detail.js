 $(document).ready(function(){
	 
	 $("a[rel=grouped]").fancybox({
			'transitionIn'	: 'elastic',
			'transitionOut'	: 'elastic',
			'titlePosition' 	: 'over',
			'titleFormat'		: function(title, currentArray, currentIndex, currentOpts) {
				return '<span id="fancybox-title-over">Image ' + (currentIndex + 1) + ' / ' + currentArray.length + (title.length ? ' &nbsp; ' + title : '') + '</span>';
			}
		});
	 
 });
 