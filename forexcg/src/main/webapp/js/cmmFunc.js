
	
	// 메뉴이력 삭제
	$(document).on("click", ".btnDeleteHis", function(event) {
		var thisEl = $(event.currentTarget);
		var mnuId = thisEl.parent().children("h3").children("span");

		$.ajax({
	        type:"POST",
	        url:"/common/deleteActiveHist.ajax",
	        data : {
	        	"mnuId":$(mnuId).text()
	        },
	        success: function(e){
	        	try {
	        		var rst = e.result.status;
	        		if (rst == "SUCCESS") {
	        			thisEl.parent().remove();
	        		}
	        	} catch(e) {;}
	        }
    	});
	});
	// 메뉴이력을 통한 이동
	$(document).on("click", ".btnGoHis", function(event) {
		var thisEl = $(event.currentTarget);
		var url = thisEl.children("url");
		document.location.href = $(url).text();
	});
