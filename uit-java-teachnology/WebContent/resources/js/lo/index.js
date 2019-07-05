"use strict";

function deleteLOOfNganh(nganhid, loid, callback) {
	$.ajax({
		url: ctx+'/lo-delete?nganh-id='+nganhid+'&lo-id='+loid,
		type: "DELETE",
		success: function(res) {
			callback(null, res);
		},
		error: function(err) {
			callback(err, null);
		}
	});
}

function deleteLO(loid, callback) {
	event.preventDefault();
	$.ajax({
		url: ctx+'/lo-delete?lo-id='+loid,
		type: "DELETE",
		success: function(res) {
			callback(null, res);
		},
		error: function(err) {
			callback(err, null);
		}
	});
}

function addLO2Nganh(nid, loid){
	event.preventDefault();
	//Variables
	var nk = $('#nk option:selected').text();
	if($('#nk option:selected').val() === ""){
		$("#addErrorModal").modal()
		.find('.modal-content')
		.html('<span style="color: red; padding: 13px 5px; text-align: center;' + 
				'font-family: Nunito; font-size:16px">'+
				'Niên khóa không được để trống!</span>');
		return;
	}
	
	$.ajax({
		url: ctx + '/lo-create',
		type:"POST",
		data: {'nk': nk,
			'nganh-id': nid,
			'lo-id': loid },
		success: function(res) {
			setTimeout(function(){
				$("#addSuccessModal").modal()
				.find('.modal-content')
				.html('<span style="color: green; padding: 13px 5px; text-align: center;' + 
						'font-family: Nunito; font-size:16px">'+res+'</span>');
			}, 1000);
		},
		error: function(err) {
			setTimeout(function(){
				$("#addErrorModal").modal()
				.find('.modal-content')
				.html('<span style="color: red; padding: 13px 5px; text-align: center;' + 
						'font-family: Nunito; font-size:16px">'+err.responseText+'</span>');
			}, 1000);
		}
	});
}

//Delete LO of Nganh
function confirmDelete(nganhid, loid, el) {
	deleteLOOfNganh(nganhid, loid, function(err, res) {
		$(el).siblings().click();  
		if (err) {			
			$("#deleteErrorModal").modal();	
		} else {	
			$("#successDeleteModal").modal();			
		}
	})
}

//Delete LO
function confirmDeleteLO(loid, el) {
	deleteLO(loid, function(err, res) {
		$(el).siblings().click();  
		if (err) {			
			$("#deleteErrorModal").modal();	
		} else {	
			$("#successDeleteModal").modal();			
		}
	})
}

$(function() {
	makePaginationById("pagination");	
	
	$("#deleteErrorModal, #successDeleteModal, #addSuccessModal, #addErrorModal").on('show.bs.modal', function() {
		setTimeout(function(){
		   $("body").addClass("modal-open");
	    }, 1000);
	});
	
	$("#deleteErrorModal, #successDeleteModal, #addLOModal").on('hidden.bs.modal', function () {
		location.reload();
	})
})




