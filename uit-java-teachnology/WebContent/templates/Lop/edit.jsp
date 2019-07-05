<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<% String pathWebcontent = request.getContextPath(); %> 

<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta content="width=device-width, initial-scale=1, maximum-scale=1, shrink-to-fit=no" name="viewport">
  <title>Chỉnh sửa thông tin lớp</title>
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
            <h1>Chỉnh sửa</h1>
            <div class="section-header-breadcrumb">
                <div class="breadcrumb-item active"><a href="<%= pathWebcontent %>">Trang chủ</a></div>
                <div class="breadcrumb-item"><a href="<%= pathWebcontent %>/lop">lớp</a></div>
                <div class="breadcrumb-item">Chỉnh sửa</div>
            </div> 
          </div>    
                
          <!-- Page content -->
          <div class="section-body">
             <div class="card card-primary">
                <div class="card-header">
                  <h4>Chỉnh sửa thông tin lớp</h4>     
                  <div class="ml-auto">
                      <a class="btn btn-white rounded btn-lg btn-icon icon-left"
                         href="<%= pathWebcontent %>/lop?page=${backPage}">
                            <i class="fas fa-chevron-left"></i>
                            Quay lại
                      </a>
                  </div>
                </div>
                <div class="card-body">
                    <div class="row">
                       <!-- Message alert -->
                       <jsp:include page="../fragments/message.jsp"></jsp:include>
                        
                      <div class="col-md-6">
                        <form action="<%= pathWebcontent %>/lop-edit?page=${backPage}" method="POST">
                          <input type="hidden" name="id" value="${data.id}">      
                          <div class="form-group">
                            <label>Mã lớp</label>
                            <input type="text" name="maLop" class="form-control" 
                            required
                             value="${not empty error ? oldData.maLop : data.maLop}">
                            <div class="invalid-feedback">
                              Không được để trống trường này
                             </div>
                             <c:if test="${not empty error}">
                                <c:if test='${not empty error["maLop"] }'>
                                    <label class="text-danger">${error["maLop"] }</label>
                                </c:if>
                             </c:if>
                          </div>
                            <div class="form-group">
                              <label>Học kỳ</label>
                              <input id="hkName" name="hocky" value="${data.hocKyID}" type="hidden">
                              <div class="dropdown" id="dropdownHKList">
                                  <button class="form-control dropdown-toggle text-left" type="button" 
                                      id="btnDropdownHKList" data-toggle="dropdown" 
                                      aria-haspopup="true" aria-expanded="false">
                                      ${data.hocKy}
                                  </button>
                                  <div class="dropdown-menu dropdown-menu-right" aria-labelledby="btnDropdownHKList" 
                                    style="overflow-y: scroll; max-height: 200px; width:100%">
                                    <c:forEach items="${listHK.items}" var="hk">
                                        <a class="dropdown-item itemHK" href="#" data-id="${hk.id}">${hk.name}</a>
                                    </c:forEach>
                                    <div class="dropdown-divider" id="dropdown-divider-hk"></div>
                                     <a class="dropdown-item btn-light more text-center" href="#"  id ="btnLoadMoreHK"
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
                              <label>Mã môn học</label>
                              <input id="mhName" name="monhoc" value="${data.monHocID}" type="hidden">
                              <div class="dropdown" id="dropdownMHList">
                                  <button class="form-control dropdown-toggle text-left" type="button" 
                                      id="btnDropdownMHList" data-toggle="dropdown" 
                                      aria-haspopup="true" aria-expanded="false">
                                      ${data.monHoc}
                                  </button>
                                  <div class="dropdown-menu dropdown-menu-right" aria-labelledby="btnDropdownMHList" 
                                    style="overflow-y: scroll; max-height: 200px; width:100%">
                                    <c:forEach items="${listMH.items}" var="mh">
                                        <a class="dropdown-item itemMH" href="#" data-id="${mh.id}">${mh.name}</a>
                                    </c:forEach>
                                    <div class="dropdown-divider" id="dropdown-divider-mh"></div>
                                     <a class="dropdown-item btn-light more text-center" href="#"  id ="btnLoadMoreMH"
                                        data-page="${listMH.currentPage}"
                                        data-total-page="${listMH.numberPage}"
                                        data-number-items="${listMH.totalItems}" 
                                        data-limit="${listMH.limit}">
                                        --- Xem thêm ---
                                      </a>
                                  </div>
                              </div>
                           </div>
                          <div class="form-group">
                              <label>Mã giảng viên</label>
                              <input id="gvName" name="giangvien" value="${data.gvID}" type="hidden">
                              <div class="dropdown" id="dropdownGVList">
                                  <button class="form-control dropdown-toggle text-left" type="button" 
                                      id="btnDropdownGVList" data-toggle="dropdown" 
                                      aria-haspopup="true" aria-expanded="false">
                                      ${data.giangVien}
                                  </button>
                                  <div class="dropdown-menu dropdown-menu-right" aria-labelledby="btnDropdownHKList" 
                                    style="overflow-y: scroll; max-height: 200px; width:100%">
                                    <c:forEach items="${listGV.items}" var="gv">
                                        <a class="dropdown-item itemGV"  href="#" data-id="${gv.id}">${gv.name}</a>
                                    </c:forEach>
                                    <div class="dropdown-divider" id="dropdown-divider-gv"></div>
                                     <a class="dropdown-item btn-light more text-center" href="#"  id ="btnLoadMoreGV"
                                        data-page="${listGV.currentPage}"
                                        data-total-page="${listGV.numberPage}"
                                        data-number-items="${listGV.totalItems}" 
                                        data-limit="${listGV.limit}">
                                        --- Xem thêm ---
                                      </a>
                                  </div>
                              </div>
                           </div>
                          <div class="form-group">   
                            <button class="btn btn-primary mr-2" type="submit">Lưu lại</button>
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
	function setDropDownClickEvent() {
	  	$(".dropdown-item.itemHK").not(".more").on('click', function(e) {
			$("#btnDropdownHKList").html($(this).html());
			$("#hkName").val($(this).data().id);
		});
	  	$(".dropdown-item.itemMH").not(".more").on('click', function(e) {
			$("#btnDropdownMHList").html($(this).html());
			$("#mhName").val($(this).data().id);
		});
	  	$(".dropdown-item.itemGV").not(".more").on('click', function(e) {
			$("#btnDropdownGVList").html($(this).html());
			$("#gvName").val($(this).data().id);
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
  	  	    			$("#btnLoadMoreHK").hide();
  	  	    		}  	    		
  					res.items.map(item => {
  						$('<a class="dropdown-item" href="#" data-id="'+ item.id +'"">' + item.name +'</a>').insertBefore('#dropdown-divider-hk')
  					});					
  					
  					setDropDownClickEvent();
  				},
  				error: function(err) {
  					
  				}
  	  	    })
  		});
  		$(".dropdown-item.more").on('click', function(e) {
  	  	    event.stopPropagation();
  	  	    var totalPage = $(this).data().totalPage;
  	  	    $.ajax({
  	  	    	url: ctx + '/ajax-get-mh',
  	  	    	type: 'GET',
  	  	    	data: {page:  parseInt($(this).data().page) + 1},
  	  	    	success: function(res) {
  	  	    		if (res.currentPage == totalPage) {
  	  	    			$("#btnLoadMoreMH").hide();
  	  	    		}  	    		
  					res.items.map(item => {
  						$('<a class="dropdown-item" href="#" data-id="'+ item.id +'"">' + item.name +'</a>').insertBefore('#dropdown-divider-mh')
  					});					
  					
  					setDropDownClickEvent();
  				},
  				error: function(err) {
  					
  				}
  	  	    })
  		});
  		$(".dropdown-item.more").on('click', function(e) {
  	  	    event.stopPropagation();
  	  	    var totalPage = $(this).data().totalPage;
  	  	    $.ajax({
  	  	    	url: ctx + '/ajax-get-gv',
  	  	    	type: 'GET',
  	  	    	data: {page:  parseInt($(this).data().page) + 1},
  	  	    	success: function(res) {
  	  	    		if (res.currentPage == totalPage) {
  	  	    			$("#btnLoadMoreGV").hide();
  	  	    		}  	    		
  					res.items.map(item => {
  						$('<a class="dropdown-item" href="#" data-id="'+ item.id +'"">' + item.name +'</a>').insertBefore('#dropdown-divider-gv')
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

