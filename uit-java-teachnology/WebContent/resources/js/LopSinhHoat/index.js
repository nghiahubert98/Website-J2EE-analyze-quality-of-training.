"use strict";

function deleteLopSinhHoat(id, callback) {
	$.ajax({
		url: ctx+'/lopsinhhoat-delete?id='+id,
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
	deleteLopSinhHoat(id, function(err, res) {
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
	$("#batDau").val(url.searchParams.get("batDau"));
	$("#totVghiep").val(url.searchParams.get("totNghiep"));
	$("#ten").val(url.searchParams.get("ten"));
	$("#coVan").val(url.searchParams.get("coVan"));
	
	makePaginationById("pagination");	

	$("#deleteErrorModal, #successDeleteModal").on('show.bs.modal', function() {
		setTimeout(function(){
		   $("body").addClass("modal-open");
	    }, 300);		
	});
})