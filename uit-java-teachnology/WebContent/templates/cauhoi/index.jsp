<%@page import="javax.xml.crypto.Data"%>
<%@page import="uit.utils.Constants"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<% String pathWebcontent = request.getContextPath(); %> 
<c:set value="<%= Constants.DE_CK %>" var="DE_CK" scope="request"></c:set>
<c:set value="<%= Constants.DE_GK %>" var="DE_GK" scope="request"></c:set>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta content="width=device-width, initial-scale=1, maximum-scale=1, shrink-to-fit=no" name="viewport">
  <title>Danh sách người dùng</title>
  <!-- Template CSS -->
  <jsp:include page="../fragments/css.jsp" />  
  <link href="<%= pathWebcontent %>/resources/css/jquery-ui.min.css" rel="stylesheet">  
  <style type="text/css">
    ul.dropdown-menu {
        width: 90%;
    }
  </style>
</head>

<body>
  <div id="app">
    <div class="main-wrapper">
      <!-- #### TopBar #### -->
      <jsp:include page="../fragments/topbar.jsp" />  
      <!-- #### End TopBar #### -->

      <!-- #### SideBar #### -->
      <jsp:include page="../fragments/sidebar.jsp" />  
      <!-- #### End SideBar #### -->
      
      <!-- #### Main Content #### -->
      <div class="main-content">
        <section class="section">
        
          <!-- Page header -->
          <div class="section-header">
            <h1>Đề thi</h1>
            <div class="section-header-breadcrumb">
                <div class="breadcrumb-item active"><a href="<%= pathWebcontent %>">Trang chủ</a></div>
                 <div class="breadcrumb-item active"><a href="<%= pathWebcontent %>/lop">Lớp</a></div>
                <div class="breadcrumb-item">Đề thi</div>
            </div> 
          </div>    
          
         <!-- ### Message  ###  -->
           <!-- Message alert -->
           <jsp:include page="../fragments/message.jsp"></jsp:include>
         <!-- ### End Message  ###  -->
           
          <!-- Page content -->
          <div class="section-body table-responsive">
                  <!-- Dề thi -->
             <div class="card card-primary">
                <div class="card-header">
                  <h4>Đề thi - Lớp: ${data[0].malop} - ${data[0].hocKyName }</h4>    
                  <div class="card-header-action">
                    <a class="btn active" href="#item-middle-term" data-tab="item-tab">Đề giữa kỳ</a>
                    <a class="btn" href="#item-final-term" data-tab="item-tab">Đề cuối kỳ</a>
                  </div>                
                </div>
                <div class="card-body">
                  <div class="row">
                    <div class="col-md-12 mt-3">
                      <jsp:include page="./_middleTermTab.jsp" flush="false"></jsp:include>
                      <jsp:include page="./_finalTermTab.jsp" flush="false"></jsp:include>                      
                    </div>
                  </div>
                </div>               
            </div>            
          </div>
        </section>
      </div>
      <!-- #### End Main Content -->
      <input id="deCKID" value="${deCKID}" type="hidden">
      <input id="deGKID" value="${deGKID}" type="hidden">      
      <!-- #### Footer #### -->
      <jsp:include page="../fragments/footer.jsp" />  
      <!-- #### End Footer #### -->
      
      
      <c:if test="${loginedUser.code == data[0].maGV}">      
       <div class="modal fade" data-keyboard="false" data-backdrop="static" tabindex="-1" role="dialog" id="createModal" aria-modal="true">
         <div class="modal-dialog" role="document">
            <div class="modal-content">
              <div class="modal-header">
                <h5 class="modal-title">Thêm câu hỏi</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                  <span aria-hidden="true">&times;</span>
                </button>
              </div>
              <form action="<%= pathWebcontent %>/lop-detail" method="POST">
                  <div class="modal-body">
                    <input type="hidden" name="lid" value="${data[0].lopId}">
                    <input type="hidden" name="deId" id="txtDeId">
                    <div class="form-group">
                     <label>Điểm</label>
                     <input type="number" required="required" name="point" class="form-control" min="0">
                    </div>
                    <div>
                        <label>Số thứ tự câu hỏi</label>
                        <input type="number" required="required" name="num" class="form-control" min="0">
                    </div>
                    <div class="form-group">
                        <label>Chuẩn G</label>
                        <select multiple="multiple" class="form-control" name="gID">
                            <c:forEach items="${listChuanG}" var="item">
                                <option value="${item.id}">${item.maG} - ${item.mota}</option>
                            </c:forEach>
                        </select>
                    </div>
                  </div>
                  <div class="modal-footer bg-whitesmoke br">
                    <button type="submit" class="btn btn-primary" >Tạo Mới</button>
                    <a href="#" class="btn btn-secondary" data-dismiss="modal">Đóng</a>
                  </div>
              </form>
            </div>
        </div>
      </div>
    </c:if>
     
     
     <!-- START EDIT MODAL -->
     <div class="modal fade" data-keyboard="false" data-backdrop="static" tabindex="-1" role="dialog" id="editModal" aria-modal="true">
         <div class="modal-dialog" role="document">
            <div class="modal-content">
              <div class="modal-header">
                <h5 class="modal-title">Chỉnh sửa câu hỏi</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                  <span aria-hidden="true">&times;</span>
                </button>
              </div>
              <form action="<%= pathWebcontent %>/lop-detail" method="POST">
                  <div class="modal-body">
                  	<input type="hidden" name="editQ" value="">
                    <input type="hidden" name="lid" value="${data[0].lopId}">
                    <input type="hidden" name="deId" id="txtDeId">
                    <div class="form-group d-flex flex-row h-20">
                    	<div class="d-flex flex-column w-25 p-1">
                    		<label>Số thứ tự</label>
	                     	<input type="number" required="required" name="num" class="form-control" min="0">
                    	</div>
	                     
	                     <div class="d-flex flex-column w-75 p-1">
	                     	<label>Điểm</label>
	                     	<input type="number" required="required" name="point" class="form-control" min="0">
	                     </div>
                    </div>
                    <div class="form-group">
                        <label>Chuẩn G</label>
                        <select multiple="multiple" class="form-control" name="gID">
                            <c:forEach items="${listChuanG}" var="item">
                                <option value="${item.id}">${item.maG} - ${item.mota}</option>
                            </c:forEach>
                        </select>
                    </div>	
                  </div>
                  <div class="modal-footer bg-whitesmoke br">
                    <button type="submit" class="btn btn-primary" >Lưu lại</button>
                  </div>
              </form>
            </div>
        </div>
      </div>
     <!-- END EDIT MODAL -->
      
      <!-- Show error delete message -->
     <div class="modal fade" tabindex="-1" role="dialog" id="deleteErrorModal" aria-modal="true">
      <div class="modal-dialog" role="document">
        <div class="modal-content">
          <div class="modal-header">
            <h5 class="modal-title">Xảy ra lỗi</h5>
            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
              <span aria-hidden="true">&times;</span>
            </button>
          </div>
          <div class="modal-body">
            <p>Xảy ra lỗi, không thể xóa câu hỏi này được.</p>
          </div>
          <div class="modal-footer bg-whitesmoke br">
            <button type="button" class="btn btn-primary" data-dismiss="modal">Đóng</button>
          </div>
        </div>
      </div>
    </div>
    
    <!-- Show success delete message -->
     <div class="modal fade" tabindex="-1" role="dialog" id="successDeleteModal" aria-modal="true">
      <div class="modal-dialog" role="document">
        <div class="modal-content">
          <div class="modal-header">
            <h5 class="modal-title">Thành công</h5>
            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
              <span aria-hidden="true">&times;</span>
            </button>
          </div>
          <div class="modal-body">
            <p>Đã thực hiện xóa câu hỏi thành công</p>
          </div>
          <div class="modal-footer bg-whitesmoke br">
            <button type="button" class="btn btn-primary" data-dismiss="modal" onclick="window.location.reload();">Đóng</button>
          </div>
        </div>
      </div>
    </div>
    
    <!-- Modal Toggle-->
	<div class="modal fade" id="averageGClass" tabindex="-1" role="dialog" aria-labelledby="averageGClass" aria-hidden="true">
	  <div class="modal-dialog modal-dialog-centered modal-lg" role="document">
	    <div class="modal-content">
	      <div class="modal-header">
	        <h5 class="modal-title text-center text-primary" id="exampleModalCenterTitle">TỈ LỆ G TRUNG BÌNH</h5>
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
	          <span aria-hidden="true">&times;</span>
	        </button>
	      </div>
	      <div class="modal-body" style="max-height: calc(100% - 100px); overflow-y: scroll">
	       	<table class="table table-striped table-bordered">
			  <thead>
			      <tr>
			        <th width="12%"class="text-info">MÃ G</th>
			        <th class="text-info">MÔ TẢ</th>
			        <th width="25%" class="text-info">TỈ LỆ ĐẠT CHUẨN</th>           
			      </tr>
			  </thead>
			  <tbody>
			    
			  </tbody>
			</table>
	      </div>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
	      </div>
	    </div>
	  </div>
	</div>
    <!-- End Modal Toggle-->  
    </div>
  </div> 
    	
 <jsp:include page="../fragments/logoutModal.jsp" />  
  <!-- Page Specific JS File -->
 <jsp:include page="../fragments/scripts.jsp" />   
 <script src="<%= pathWebcontent %>/resources/js/jquery-ui.js"></script>
 <script src="<%= pathWebcontent %>/resources/js/pagination.min.js"></script>
 <script src="<%= pathWebcontent %>/resources/js/pagination.make.js"></script>
 <script type="text/javascript" src="<%=pathWebcontent%>/resources/js/BsMultiSelect.min.js"></script>

 <script type="text/javascript">
 	var ctx = "<%= pathWebcontent %>";
 	$(function() {
 		$('select[multiple="multiple"]').bsMultiSelect({
 			selectedPanelClass: 'form-control form-control-sm',
            selectedItemClass: 'bg-primary rounded mb-1 text-white',
            removeSelectedItemButtonClass: 'ml-1 text-danger close'
 		});
 		
 		$(".custom-file-input").on("change", function () {
 		    let file = this.files[0];
 		    let fileType = file['type'];
 		    var fileName = $(this).val().split("\\").pop();
 		    $(this).siblings(".custom-file-label").addClass("selected").html(fileName);
 		});
 		
 		$("#deleteErrorModal, #successDeleteModal, #averageGClass").on('show.bs.modal', function() {
 			setTimeout(function(){
 			   $("body").addClass("modal-open");
 		    }, 300);		
 		});
 		
 		$("#averageGClass").on('hidden.bs.modal', function(e) { 
 			$("#averageGClass tbody").html("");
	    }) ;
 	});
 	
 	function showCreateModal(type) {
 		let deID = $("#deCKID").val();
 		if (type.trim() == 'gk') {
 			deID = $("#deGKID").val();
 		}
 		$("#txtDeId").val(deID);
 		$("#createModal").modal();
 	}
 	
 	function editQuestion(id, order, point){
 		event.preventDefault();
 		$.ajax({
 			url: ctx + '/ajax-g-question?qid='+id,
 			type:"GET",
 			success: function(res){
 				$('#editModal')
 				.find("input[name=num]").val(order)
 				.end()
 				.find("input[name=point]").val(point)
 				.end()
 				.find("input[name=editQ]").val(id)
 				.end()
 				console.log(res);
 			},
 			error: function(err){
 				console.log(err);
 			}
 		});
 		
 		$("#editModal").modal();
 	}
 	
 	function deleteQuestion(id, callback){
 		event.preventDefault();
 		var lid = $("input[name=lid]").val();
 		$.ajax({
 			url: ctx + '/lop-detail?lid='+lid+'&qid='+id,
 			type: "DELETE",
 			success: function(res){
 				callback(null, res);
 			},
 			error: function(err){
 				callback(err, null);
 			}
 		});
 	}
 	
 	function confirmDelete(id, el) {
 		deleteQuestion(id, function(err, res) {
 			$(el).siblings().click();  
 			if (err) {
 				$("#deleteErrorModal").modal();	
 			} else {	
 				$("#successDeleteModal").modal();			
 			}
 		})
 	}
 	
 	function getThongkeLop(lid){
 		$.ajax({
 			url: ctx + '/ajax-gv-thongke?lid=' + lid,
 			type: 'GET',
 			success: function(res){
 				if(res.length == 0){
 					$("#averageGClass .modal-body").html('<p class="text-center text-danger">Chưa có dữ liệu để thống kê!</p>')	
 				}
 				for(var i=0; i<res.length; i++){
 					var row = "<tr><td class='text-primary'><strong>"
 					+res[i].maG
 					+"</strong></td><td>"
 					+res[i].moTa
 					+"</td><td class='text-success'><div class='progress'><div class='progress-bar progress-bar-striped bg-success progress-bar-animated' role='progressbar' aria-valuenow="
 					+res[i].tiLe+ "aria-valuemin='0' aria-valuemax='100' style='width:"
 					+res[i].tiLe
 					+"%;'>"
 					+res[i].tiLe
 					+"%</div></div>"
 					+"</td></tr>";
 					
 					$("#averageGClass tbody").append(row)
 				}
 				$("#averageGClass").modal()
 			},
 			error: function(err){
 				$("#averageGClass .modal-body").html('<p class="text-center text-danger">Không thể lấy dữ liệu để thống kê. <br>Vui lòng quay lại sau!</p>')
 			}
 			
 		});
 	}
 </script>
</body>
</html>