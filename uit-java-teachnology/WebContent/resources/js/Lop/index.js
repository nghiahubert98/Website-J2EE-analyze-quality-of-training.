"use strict";

function deleteLop(id, callback) {
	$.ajax({
		url: ctx+'/lop-delete?id='+id,
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
	deleteLop(id, function(err, res) {
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
	$("#maLop").val(url.searchParams.get("maLop"));
	$("#hocKy").val(url.searchParams.get("hocKy"));
	$("#monHoc").val(url.searchParams.get("monHoc"));
	$("#giangVien").val(url.searchParams.get("giangVien"));
	
	makePaginationById("pagination");	

	$("#deleteErrorModal, #successDeleteModal").on('show.bs.modal', function() {
		setTimeout(function(){
		   $("body").addClass("modal-open");
	    }, 300);		
	});
})