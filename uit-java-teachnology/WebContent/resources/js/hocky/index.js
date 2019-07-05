"use strict";

function deleteHocKy(id, callback) {
	$.ajax({
		url: ctx+'/hocky-delete?id='+id,
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
	deleteHocKy(id, function(err, res) {
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
	$("#loai").val(url.searchParams.get("loai"));
	$("#namBatDau").val(url.searchParams.get("namBatDau"));
	$("#namKetThuc").val(url.searchParams.get("namKetThuc"));
	
	makePaginationById("pagination");	

	$("#deleteErrorModal, #successDeleteModal").on('show.bs.modal', function() {
		setTimeout(function(){
		   $("body").addClass("modal-open");
	    }, 300);		
	});
})