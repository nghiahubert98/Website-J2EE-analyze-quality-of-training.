<%@page import="uit.model.ChuanLO"%>
<%@page import="uit.modelview.IndexModelView"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<% String pathWebcontent = request.getContextPath(); %> 
<%@page import="uit.utils.Constants"%>
<c:set value="<%= Constants.ADMIN_ROLE %>" var="ADMIN_ROLE"></c:set>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta content="width=device-width, initial-scale=1, maximum-scale=1, shrink-to-fit=no" name="viewport">
  <title>Chuẩn LO từng ngành</title>
  <!-- Template CSS -->
  <jsp:include page="../fragments/css.jsp" />  
  <link href="<%= pathWebcontent %>/resources/css/jquery-ui.min.css" rel="stylesheet">  
  <script>var ctx = "<%= pathWebcontent %>" </script>
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
            <h1>Các chuẩn LO</h1>
            <div class="section-header-breadcrumb">
                <div class="breadcrumb-item active"><a href="<%= pathWebcontent %>">Trang chủ</a></div>
                <div class="breadcrumb-item active"><a href="<%= pathWebcontent %>/nganh">Ngành</a></div>
                <div class="breadcrumb-item">LO</div>
            </div> 
          </div>    
          
         <!-- ### Message  ###  -->
           <!-- Message alert -->
           <jsp:include page="../fragments/message.jsp"></jsp:include>
         <!-- ### End Message  ###  -->
           
          <!-- Page content -->
          <div class="section-body">
             <div class="card author-box card-primary">
                <div class="card-header">
                    <h4>Danh sách LO ngành <%=request.getParameter("nganh-name") %></h4>
                    <c:if test="${loginedUser.roles.contains(ADMIN_ROLE)}">
                    <div class="ml-auto">
                         <a class="btn btn-primary" data-toggle="modal" data-target="#addLOModal" href="#">Thêm mới</a>
                    </div>
                    </c:if>
                </div>
                <div class="card-body table-responsive p-0">
                     <form method="get" action="<%=pathWebcontent%>/nganh">
	                     <table class="table table-striped">
	                        <thead>
	                            <tr>
	                                <th width="50px">STT</th>
	                                <th width="20%">Chuẩn LO</th>
	                                <th>Mô tả</th>
	                                <th width="150px">Thao tác</th>
	                            </tr>	
	                        </thead>
	                        <tbody>
	                            <c:set value="${(data.currentPage - 1) * data.limit}" var="index"></c:set>
	                             <c:forEach items = "${data.items}"  var = "lo">
	                                <tr>
	                                    <td> 
	                                        <c:set value="${index + 1}" var="index"/>
	                                        ${index}   
	                                    </td>
	                                    <td>${lo.maLO}</td>
	                                    <td>${lo.mota}</td>
	                                    <td>
                                            <c:if test="${loginedUser.roles.contains(ADMIN_ROLE)}">
	                                        <a href="#" class="btn btn-danger btn-sm btn-delete" 
	                                           data-confirm="Xóa ${lo.maLO}?|Bạn thật sự muốn xóa ${lo.maLO} không?" 
	                                           data-confirm-yes="confirmDelete(${data.id}, ${lo.id}, this)"
	                                        >Xóa</a>
                                            </c:if>
	                                    </td>
	                                </tr>
	                             </c:forEach>
	                             
	                              <c:if test="${data.items.size() == 0}">
	                                <tr>
	                                    <td colspan="4" class="text-center"><i style="color: red">Không có LO nào được tạo</i></td>
	                                </tr>
	                             </c:if>
	                        </tbody>
	                    </table>
                    </form>
                    <div class="ml-1">
                        <div id="pagination" 
                            data-current-page="${data.currentPage}"
                            data-total-page="${data.numberPage}"
                            data-number-items="${ data.totalItems}" 
                            data-limit="${data.limit}"></div>    
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
  	
  	<!-- START ADD MODAL -->
  	<div class="modal fade" tabindex="-1" role="dialog" id="addLOModal" aria-modal="true">
		<div class="modal-dialog modal-lg" role="document" style="height: 90%">
		  <div class="modal-content" style="height: 90%">
		    <div class="modal-header">
		    	<h5 class="modal-title">Danh sách các chuẩn LO</h5>
			    <div class="form-group" style="margin:-5px 20px">
				    <select id="nk" class="custom-select" required>
				      <option value=""> Chọn niên khóa </option>
				      <option value="nk1">2014-2018</option>
				      <option value="nk2">2015-2019</option>
				      <option value="nk3">2016-2020</option>
				      <option value="nk4">2017-2021</option>
				      <option value="nk5">2018-2022</option>
				    </select>
				    <div class="invalid-feedback">Chọn niên khóa</div>
				</div>
				<button type="button" class="close" data-dismiss="modal" aria-label="Close">
	        		<span aria-hidden="true">&times;</span>
	      		</button>
		    </div>
		    <div class="modal-body" style="max-height: calc(100% - 100px); overflow-y: scroll">
		      <div class="card-body table-responsive p-0">
		         <form method="POST" action="" class="needs-validation" novalidate>
		           	<table class="table table-striped">
	                  <thead>
	                      <tr>
	                          <th width="50px">STT</th>
	                          <th width="20%">Chuẩn LO</th>
	                          <th>Mô tả</th>
	                          <th width="15%">Thao tác</th>
	                      </tr>	
	                  </thead>
	                  <tbody>
                  		<c:set value='0' var="addindex"/>
	                       <c:forEach items = "${newlo.items}" var = "newlo">
	                          <tr>
	                              <td> 
	                                  <c:set value="${addindex + 1}" var="addindex"/>
	                                  ${addindex}   
	                              </td>
	                              <td>${newlo.maLO}</td>
	                              <td>${newlo.mota}</td>
	                              <td>
	                                  <button type="submit" class="btn btn-sm btn-success" onclick="addLO2Nganh(<%=request.getParameter("nganh-id") %>, ${newlo.id})">Thêm</button>
	                              </td>
	                          </tr>
	                       </c:forEach>
	                       
	                       <c:if test="${newlo.items.size() == 0}">
	                          <tr>
	                              <td colspan="4" class="text-center"><i style="color: red">Không có LO nào được tạo</i></td>
	                          </tr>
	                       </c:if>
	                    </tbody>
		              </table>
		           	</form>        
		          </div>
		    </div>
		    <div class="modal-footer bg-whitesmoke br">
		      <button type="button" class="btn btn-primary" data-dismiss="modal">Đóng</button>
		    </div>
		  </div>
		</div>
	</div>
  	<!--  END ADD MODAL  -->
  	
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
            <p>Xảy ra lỗi, không thể xóa LO này được.</p>
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
            <p>Đã thực hiện xóa LO thành công</p>
          </div>
          <div class="modal-footer bg-whitesmoke br">
            <button type="button" class="btn btn-primary" data-dismiss="modal" onclick="window.location.reload();">Đóng</button>
          </div>
        </div>
      </div>
    </div>
    
    <!-- Show error add message -->
    <div class="modal fade bd-example-modal-sm" id="addErrorModal" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel" aria-hidden="true">
	  <div class="modal-dialog modal-sm">
	    <div class="modal-content">
	      <!-- ERROR MESSAGE -->
	    </div>
	  </div>
	</div>
     
    <!-- Show success add message -->
    <div class="modal fade bd-example-modal-sm" id="addSuccessModal" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel" aria-hidden="true">
	  <div class="modal-dialog modal-sm">
	    <div class="modal-content">
	      <!-- SUCCESS MESSAGE -->
	    </div>
	  </div>
	</div>
    
  <jsp:include page="../fragments/logoutModal.jsp" />  
  <!-- Page Specific JS File -->
 <jsp:include page="../fragments/scripts.jsp" />   
 <script src="<%= pathWebcontent %>/resources/js/jquery-ui.js"></script>
 <script src="<%= pathWebcontent %>/resources/js/pagination.min.js"></script>
 <script src="<%= pathWebcontent %>/resources/js/pagination.make.js"></script>
 <script src="<%= pathWebcontent %>/resources/js/lo/index.js"></script>
</body>
</html>





