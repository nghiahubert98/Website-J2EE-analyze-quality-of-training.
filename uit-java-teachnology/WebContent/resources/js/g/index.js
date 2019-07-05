"use strict";

function deleteGOfLO(gid, loid, callback) {
	$.ajax({
		url: ctx+'/g-delete?lo-id='+loid+'&g-id='+gid,
		type: "DELETE",
		success: function(res) {
			callback(null, res);
		},
		error: function(err) {
			callback(err, null);
		}
	});
}

function deleteG(gid, callback) {
	event.preventDefault();
	$.ajax({
		url: ctx+'/g-delete?g-id='+gid,
		type: "DELETE",
		success: function(res) {
			callback(null, res);
		},
		error: function(err) {
			callback(err, null);
		}
	});
}

function addG2LO(loid, gid){
	event.preventDefault();
	$.ajax({
		url: ctx + '/g-create',
		type:"POST",
		data: {'lo-id': loid,
			'g-id': gid },
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

//Delete G of LO
function confirmDelete(gid, loid, el) {
	deleteGOfLO(gid, loid, function(err, res) {
		$(el).siblings().click();  
		if (err) {			
			$("#deleteErrorModal").modal();	
		} else {	
			$("#successDeleteModal").modal();			
		}
	})
}

//Delete G
function confirmDeleteG(gid, el) {
	deleteG(gid, function(err, res) {
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




