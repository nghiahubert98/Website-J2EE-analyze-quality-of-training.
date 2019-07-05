<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<% String pathWebcontent = request.getContextPath(); %> 

<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta content="width=device-width, initial-scale=1, maximum-scale=1, shrink-to-fit=no" name="viewport">
  <title>Chỉnh sửa thông tin sinh viên</title>
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
                <div class="breadcrumb-item"><a href="<%= pathWebcontent %>/sinhvien">Sinh viên</a></div>
                <div class="breadcrumb-item">Chỉnh sửa</div>
            </div> 
          </div>    
                
          <!-- Page content -->
          <div class="section-body">
             <div class="card card-primary">
                <div class="card-header">
                  <h4>Chỉnh sửa thông tin sinh viên</h4>     
                  <div class="ml-auto">
                      <a class="btn btn-white rounded btn-lg btn-icon icon-left"
                         href="<%= pathWebcontent %>/sinhvien?page=${backPage}">
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
                        <form action="<%= pathWebcontent %>/sinhvien-edit?page=${backPage}" method="POST">
                          <input type="hidden" name="id" value="${data.id}">
                          
                          <div class="form-group">
                            <label>Mã số Sinh viên</label>
                            <input type="text" name="mssv" class="form-control" 
                             value="${not empty error ? oldData.mssv : data.mssv}">
                             <c:if test="${not empty error}">
                                <c:if test='${not empty error["mssv"] }'>
                                    <label class="text-danger">${error["mssv"] }</label>
                                </c:if>
                             </c:if>
                          </div>
                          
                         <div class="form-group">
                              <label>Mã lớp sinh hoạt</label>
                              <input id="LSHName" name="lopsinhhoat" value="${data.maLopSH}" type="hidden">
                              <div class="dropdown" id="dropdownLSHList">
                                  <button class="form-control dropdown-toggle text-left" type="button" 
                                      id="btnDropdownLSHList" data-toggle="dropdown" 
                                      aria-haspopup="true" aria-expanded="false">
                                      ${data.codeLSH}
                                  </button>
                                  <div class="dropdown-menu dropdown-menu-right" aria-labelledby="btnDropdownLSHList" 
                                    style="overflow-y: scroll; max-height: 200px; width:100%">
                                    <c:forEach items="${listLSH.items}" var="LSH">
                                        <a class="dropdown-item itemLSH"  href="#" data-id="${LSH.id}">${LSH.maLop}</a>
                                    </c:forEach>
                                    <div class="dropdown-divider" id="dropdown-divider-LSH"></div>
                                     <a class="dropdown-item btn-light more text-center" href="#"  id ="btnLoadMoreLSH"
                                        data-page="${listLSH.currentPage}"
                                        data-total-page="${listLSH.numberPage}"
                                        data-number-items="${listLSH.totalItems}" 
                                        data-limit="${listLSH.limit}">
                                        --- Xem thêm ---
                                      </a>
                                  </div>
                              </div>
                           </div>
                           
                          <div class="form-group">
                            <label>Tên sinh viên</label>
                            <input type="text" name="ten_sinhvien" class="form-control"
                             value="${not empty error ? oldData.ten : data.ten}">
                            <c:if test="${not empty error}">
                                <c:if test='${not empty error["ten_sinhvien"] }'>
                                    <label class="text-danger">${error["ten_sinhvien"] }</label>
                                </c:if>
                             </c:if>
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
	  	$(".dropdown-item.itemLSH").not(".more").on('click', function(e) {
			$("#btnDropdownLSHList").html($(this).html());
			$("#LSHName").val($(this).data().id);
		});
	}
	
  	$(function() {
  		setDropDownClickEvent();
  		$(".dropdown-item.more").on('click', function(e) {
  	  	    event.stopPropagation();
  	  	    var totalPage = $(this).data().totalPage;
  	  	    $.ajax({
  	  	    	url: ctx + '/ajax-get-lsh',
  	  	    	type: 'GET',
  	  	    	data: {page:  parseInt($(this).data().page) + 1},
  	  	    	success: function(res) {
  	  	    		if (res.currentPage == totalPage) {
  	  	    			$("#btnLoadMoreLSH").hide();
  	  	    		}  	    		
  					res.items.map(item => {
  						$('<a class="dropdown-item" href="#" data-id="'+ item.id +'"">' + item.maLop +'</a>').insertBefore('#dropdown-divider-LSH')
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

