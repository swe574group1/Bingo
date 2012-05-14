
	function Show()
	{
		var ctrl = $("div#notification_items");
		ctrl.css("visibility","visible");
		HideCounter();
	}
	function Hide()
	{
		var ctrl = $("div[func='notification_items']");
		ctrl.css("visibility","hidden");
	}

	function HideCounter(){
		$('div span#counter').css("visibility","hidden");
	}
	function ShowCounter(){
		$('div span#counter').css("visibility","visible");
	}
	
	function IsVisible(){
		var ctrl = $("div[func='notification_items']");
		return ctrl.css("visibility") == "visible";
	}

	$(document).ready(function() {
		$('div span#counter').click(function() {
			if(IsVisible()) 
				Hide();
			else
				Show();
		});
		
		$("div[func='notification_items']").mouseout(function(){
			//Hide();
			//ShowCounter();
		});
		
		$(document).click(function(e){
			if (IsVisible()){
				//Hide();
				//ShowCounter();	
			}
			
		});
		
	});
