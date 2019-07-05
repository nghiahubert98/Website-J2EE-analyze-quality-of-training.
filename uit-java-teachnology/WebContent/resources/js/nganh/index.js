"use strict";

function deleteNganh(id, callback) {
	$.ajax({
		url: ctx+'/nganh-delete?id='+id,
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
	deleteNganh(id, function(err, res) {
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
	$("#manganh").val(url.searchParams.get("manganh"));
	
	makePaginationById("pagination");	
	
	$("#deleteErrorModal, #successDeleteModal").on('show.bs.modal', function() {
		setTimeout(function(){
		   $("body").addClass("modal-open");
	    }, 300);		
	});
})




