<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<% String pathWebcontent = request.getContextPath(); %> 

<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta content="width=device-width, initial-scale=1, maximum-scale=1, shrink-to-fit=no" name="viewport">
  <title>Import lớp bằng excel</title>
  <!-- Template CSS -->
  <jsp:include page="../fragments/css.jsp" />  

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
            <h1>Tạo mới bằng Excel</h1>
            <div class="section-header-breadcrumb">
                <div class="breadcrumb-item active"><a href="<%= pathWebcontent %>">Trang chủ</a></div>
                <div class="breadcrumb-item"><a href="<%= pathWebcontent %>/lop">lớp</a></div>
                <div class="breadcrumb-item">Tạo mới bằng Excel</div>
            </div> 
          </div>    
                
          <!-- Page content -->
          <div class="section-body">
             <div class="card card-primary col-md-6 offset-md-3">
                <div class="card-header">
                  <h4>Nhập lớp mới bằng Excel</h4>     
                  <div class="ml-auto">
                      <a class="btn btn-white rounded btn-lg btn-icon icon-left"
                         href="<%= pathWebcontent %>/lop">
                            <i class="fas fa-chevron-left"></i>
                            Quay lại
                      </a>
                  </div>
                </div>
                <div class="card-body">
                    <div class="row">
                    
                       <!-- Message alert -->
                       <jsp:include page="../fragments/message.jsp"></jsp:include>        
                                     
                      <div class="col-md-12">
                        <form action="<%= pathWebcontent %>/lop-excel" method="POST" enctype="multipart/form-data">
                           <div class="form-group">
                              <label>Học kỳ</label>
                              <input id="hkName" name="hocky" value="${listHK.items[0].id}" type="hidden">
                              <div class="dropdown" id="dropdownHKList">
                                  <button class="form-control dropdown-toggle text-left" type="button" 
                                      id="btnDropdownHKList" data-toggle="dropdown" 
                                      aria-haspopup="true" aria-expanded="false">
                                      ${listHK.items[0].name}
                                  </button>
                                  <div class="dropdown-menu dropdown-menu-right" aria-labelledby="btnDropdownHKList" 
                                    style="overflow-y: scroll; max-height: 200px; width:100%">
                                    <c:forEach items="${listHK.items}" var="hk">
                                        <a class="dropdown-item" href="#" data-id="${hk.id}">${hk.name}</a>
                                    </c:forEach>
                                    <div class="dropdown-divider" id="dropdown-divider"></div>
                                     <a class="dropdown-item btn-light more text-center" href="#"  id ="btnLoadMore"
                                        data-page="${listHK.currentPage}"
                                        data-total-page="${listHK.numberPage}"
                                        data-number-items="${listHK.totalItems}" 
                                        data-limit="${listHK.limit}">
                                        --- Xem thêm ---
                                      </a>
                                  </div>
                              </div>
                           </div>
                           <div class="form-group">
                              <label>Chọn một file Excel</label>
                              <div class="custom-file">
                                  <input type="file" name="excel" 
                                  accept=".xls, .xlsx"  required
                                  class="custom-file-input" id="customFile">
                                  <label class="custom-file-label" for="customFile">Chọn file Excel</label>
                               </div>
                               <c:if test="${not empty error}">
                                   <c:if test='${not empty error["excelFile"] }'>
                                      <div class="text-danger">${error["excelFile"] }</div>
                                   </c:if>
                               </c:if>
                           </div>
                           <div class="form-group">
                               <button class="btn btn-primary mr-2" type="submit">Lưu lại</button>
                               <a class="btn btn-light btn-icon icon-left" target="_blank" 
                               href="<%= pathWebcontent %>/resources/excel/LopImport.xls">
                              <i class="fas fa-download"></i>
                               Download file mẫu</a>
                           </div>
                        </form>
                      </div>
                    </div>                   
                </div>
             </div>
          </div>          
        </section>
      </div>
      <!-- #### End Main Content -->
      
      <!-- #### Footer #### -->
      <jsp:include page="../fragments/footer.jsp" />  
      <!-- #### End Footer #### -->
      
    </div>
  </div>
  
  <jsp:include page="../fragments/logoutModal.jsp" />  
  <!-- Page Specific JS File -->
  <jsp:include page="../fragments/scripts.jsp" />  
  <script>
  	$(".custom-file-input").on("change", function () {
	    let file = this.files[0];
	    let fileType = file['type'];
	    var fileName = $(this).val().split("\\").pop();
	    $(this).siblings(".custom-file-label").addClass("selected").html(fileName);
	 });
  	
	function setDropDownClickEvent() {
	  	$(".dropdown-item").not(".more").on('click', function(e) {
			$("#btnDropdownHKList").html($(this).html());
			$("#hkName").val($(this).data().id);
		});
	}
  	
  	
  	$(function() {
  		setDropDownClickEvent();  		
  		$(".dropdown-item.more").on('click', function(e) {
  	  	    event.stopPropagation();
  	  	    var totalPage = $(this).data().totalPage;
  	  	    $.ajax({
  	  	    	url: ctx + '/ajax-get-hk',
  	  	    	type: 'GET',
  	  	    	data: {page:  parseInt($(this).data().page) + 1},
  	  	    	success: function(res) {
  	  	    		if (res.currentPage == totalPage) {
  	  	    			$("#btnLoadMore").hide();
  	  	    		}  	    		
  					res.items.map(item => {
  						$('<a class="dropdown-item" href="#" data-id="'+ item.id +'"">' + item.name +'</a>').insertBefore('#dropdown-divider')
  					});					
  					
  					setDropDownClickEvent();
  				},
  				error: function(err) {
  					
  				}
  	  	    })
  		});
  	})
  	
  </script>
</body>
</html>

