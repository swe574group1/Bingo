 $(document).ready(function(){
	 
	 
	 
 });
 
 //date_input eklentisi turkce yama
  jQuery.extend(DateInput.DEFAULT_OPTS, {
      
	  stringToDate: function(string) {
		var matches;
		if (matches = string.match(/^(\d{4,4})-(\d{2,2})-(\d{2,2})$/)) {
		  return new Date(matches[1], matches[2] - 1, matches[3]);
		} else {
		  return null;
		};
	  },
	
	  dateToString: function(date) {
		var month = (date.getMonth() + 1).toString();
		var dom = date.getDate().toString();
		if (month.length == 1) month = "0" + month;
		if (dom.length == 1) dom = "0" + dom;
		return date.getFullYear() + "-" + month + "-" + dom;
	  }
    });
 
 
 $(function() {
      $(".date_pick").date_input();
    });