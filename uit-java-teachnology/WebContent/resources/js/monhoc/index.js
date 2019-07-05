"use strict";

function deleteMonHoc(id, callback) {
	$.ajax({
		url: ctx+'/monhoc-delete?id='+id,
		type: "DELETE",
		success: function(res) {
			callback(null, res);
		},
		error: function(err) {
			callback(err, null);
		}
	});
}

function confirmDelete(id, el) {
	deleteMonHoc(id, function(err, res) {
		$(el).siblings().click();  
		if (err) {			
			$("#deleteErrorModal").modal();	
		} else {	
			$("#successDeleteModal").modal();			
		}
	})
}

$(function() {
	var url = new URL(window.location.href);
	$("#name").val(url.searchParams.get("name"));
	$("#maMonHoc").val(url.searchParams.get("maMonHoc"));
	
	makePaginationById("pagination");	
	
	$("#deleteErrorModal, #successDeleteModal").on('show.bs.modal', function() {
		setTimeout(function(){
		   $("body").addClass("modal-open");
	    }, 300);		
	});
})




