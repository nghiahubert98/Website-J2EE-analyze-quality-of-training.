"use strict";

function deleteSinhVien(id, callback) {
	$.ajax({
		url: ctx+'/sinhvien-delete?id='+id,
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
	deleteSinhVien(id, function(err, res) {
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
	$("#mssv").val(url.searchParams.get("mssv"));
	$("#ten").val(url.searchParams.get("ten"));
	$("#maLopSH").val(url.searchParams.get("maLopSH"));
	
	makePaginationById("pagination");	

	$("#deleteErrorModal, #successDeleteModal").on('show.bs.modal', function() {
		setTimeout(function(){
		   $("body").addClass("modal-open");
	    }, 300);		
	});
})