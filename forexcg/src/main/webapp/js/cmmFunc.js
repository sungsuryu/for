
	
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

	// 도움말 불러오기
	function selectGuide(){
		var uiId = $("#uiId").val();
		$.ajax({
	        type:"POST",
	        url:"/help/guide.ajax",
	        data : {
	        	"uiId":uiId
	        },
	        success: function(data){
	        	if(data.result.status == "SUCCESS"){
	        		if(data.result.levelCheck == "N"){
	        			$("#updateGuide").css("display", "none");
	        			$("#guideContent").attr("readonly", true);
	        			$("#guideContent").attr("disable", true);
	        		}
	        		if(data.result.isYn == "Y"){
	        			$("#guideContent").val(data.result.guideVO.guideContent);
	        		}
	        	}
	        	else{
	        		alert("도움말 불러오기 실패");
	        	}
	        },
			error: function(e){
				alert("도움말 불러오기 실패");
			}			
    	});
	}
	
	// 도움말 가져오기
	function updateGuide(){
		var uiId = $("#uiId").val();
		var guideContent = $("#guideContent").val();
		$.ajax({
	        type:"POST",
	        url:"/help/updateGuide.ajax",
	        data : {
	        	"uiId":uiId,
	        	"guideContent":guideContent
	        },
	        success: function(data){
	        },
			error: function(e){
				alert("도움말 수정 실패");
			}			
    	});
	}