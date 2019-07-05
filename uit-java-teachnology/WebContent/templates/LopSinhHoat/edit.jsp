<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<% String pathWebcontent = request.getContextPath(); %> 

<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta content="width=device-width, initial-scale=1, maximum-scale=1, shrink-to-fit=no" name="viewport">
  <title>Chỉnh sửa thông tin lớp sinh hoạt</title>
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
                <div class="breadcrumb-item"><a href="<%= pathWebcontent %>/lopsinhhoat">Lớp sinh hoạt</a></div>
                <div class="breadcrumb-item">Chỉnh sửa</div>
            </div> 
          </div>    
                
          <!-- Page content -->
          <div class="section-body">
             <div class="card card-primary">
                <div class="card-header">
                  <h4>Chỉnh sửa thông tin lớp sinh hoạt</h4>     
                  <div class="ml-auto">
                      <a class="btn btn-white rounded btn-lg btn-icon icon-left"
                         href="<%= pathWebcontent %>/lopsinhhoat?page=${backPage}">
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
                        <form action="<%= pathWebcontent %>/lopsinhhoat-edit?page=${backPage}" method="POST">
                          <input type="hidden" name="id" value="${data.id}">
                          
                          <div class="form-group">
                            <label>Mã lớp sinh hoạt</label>
                            <input type="text" name="maLop" class="form-control" value="${not empty error ? oldData.maLop : data.maLop}">
                             <c:if test="${not empty error}">
                                <c:if test='${not empty error["maLopt"] }'>
                                    <label class="text-danger">${error["maLop"] }</label>
                                </c:if>
                             </c:if>
                          </div>
                           <div class="form-group">
                            <label>Bắt đầu</label>
                            <input type="text" name="batDau" class="form-control" 
                             value="${not empty error ? oldData.batDau : data.batDau}">
                             <c:if test="${not empty error}">
                                <c:if test='${not empty error["batDau"] }'>
                                    <label class="text-danger">${error["batDau"] }</label>
                                </c:if>
                             </c:if>
                          </div>
                          <div class="form-group">
                            <label>Tốt nghiệp</label>
                            <input type="text" name="totNghiep" class="form-control" 
                             value="${not empty error ? oldData.totNghiep : data.totNghiep}">
                             <c:if test="${not empty error}">
                                <c:if test='${not empty error["totNghiep"] }'>
                                    <label class="text-danger">${error["totNghiep"] }</label>
                                </c:if>
                             </c:if>
                          </div>
                          <div class="form-group">
                            <label>Tên</label>
                            <input type="text" name="ten" class="form-control" 
                             value="${not empty error ? oldData.ten : data.ten}">
                             <c:if test="${not empty error}">
                                <c:if test='${not empty error["ten"] }'>
                                    <label class="text-danger">${error["ten"] }</label>
                                </c:if>
                             </c:if>
                          </div>
                        <div class="form-group">
                              <label>Cố vấn</label>
                              <input id="gvName" name="covan" value="${data.coVanID}" type="hidden">
                              <div class="dropdown" id="dropdownGVList">
                                  <button class="form-control dropdown-toggle text-left" type="button" 
                                      id="btnDropdownGVList" data-toggle="dropdown" 
                                      aria-haspopup="true" aria-expanded="false">
                                      ${data.coVan}
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
                            <label>Ngành</label>
                            <select class="form-control" name="nganh" required="required">
                                <c:forEach items="${listNganh}" var="nganh">
                                    <option value="${nganh.id}" ${data.idNganh == nganh.id ? 'selected': ''}>${nganh.manganh} - ${nganh.name}</option>
                                </c:forEach>
                            </select>
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

