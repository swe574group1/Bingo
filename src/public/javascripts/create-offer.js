var TagManager = function() {};

TagManager.prototype.compareItems = function (item1, item2) {
		return item1 && item2 && (item1.guid == item2.guid);
	};
	
TagManager.prototype.filter = function (list, query) {
	var newList = [];
	for (item in list) {
		if (item.name && item.name.indexOf(query) !== -1) {
			newList.push(item);
		}
	}
	return newList;
};
	
TagManager.prototype.init = function (core) {
	console.log("starting tag manager...");
};

TagManager.prototype.itemContains = function (item, needle) {
	return item.name.indexOf(needle) !== -1;
};

TagManager.prototype.itemToString = function (item) {
	return item.name + " - " + item.type[0].name;
};

TagManager.prototype.stringToItem = function (str) {
	return str;
};

$(document).ready(function(){
	// tagging system.
	/*
	$("#tags").textext({
		itemManager: TagManager,
		plugins: "autocomplete ajax tags filter",
		ajax: {
			url: "/tag/search",
			dataType: "json",
			cacheResults: false
		}
	});
	*/
	$("#tags").tagedit({
		allowEdit: false,
		autocompleteOptions: {
			minLength: 2,
			source: function (request, response) {
				$.getJSON("/tag/search?term=" + request.term, function(data) {
					var list = [];
					$.each(data, function(i, v) {
						if (! v.name) {
							return;
						}
						var item = {
							label: v.name + " - " + v.type[0].name,
							value: v.name,
							id: v.guid
						};
						list.push(item);
					});
					response(list);
				});
			},
			select: function (e, ui) {
				$(this).val(ui.item.value).trigger('transformToTag', [ui.item.id]);
				return false;
			}
		}
	});
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