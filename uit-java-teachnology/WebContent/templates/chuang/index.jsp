<%@page import="uit.model.ChuanG"%>
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
  <title>Danh sách G</title>
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
            <h1>Danh sách chuẩn G</h1>
            <div class="section-header-breadcrumb">
                <div class="breadcrumb-item active"><a href="<%= pathWebcontent %>">Trang chủ</a></div>
                <div class="breadcrumb-item">G</div>
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
                    <h4>Danh sách G</h4>
                    <c:if test="${loginedUser.roles.contains(ADMIN_ROLE)}">
                    <div class="ml-auto">
                        <div class="dropdown">
	                        <button type="button" id="dropdownMenuButton"
	                            class="btn btn-white rounded-circle text-center" 
	                            data-toggle="dropdown" title="Các thao tác với danh sách G"
	                            aria-haspopup="true" aria-expanded="false">
	                            <i class="fas fa-ellipsis-v"></i>
	                        </button>
	                        <div class="dropdown-menu dropdown-menu-right"  aria-labelledby="dropdownMenuButton">
	                            <a class="dropdown-item" href="<%= pathWebcontent %>/g-create">Thêm mới</a>
	                          	<a class="dropdown-item" href="<%= pathWebcontent %>/g-excel">Thêm bằng Excel</a>
	                         </div>
                        </div>
                    </div>
                    </c:if>
                </div>
                <div class="card-body table-responsive p-0">
                     <form method="get" action="<%=pathWebcontent%>/nganh">
	                     <table class="table table-striped">
	                        <thead>
	                            <tr>
	                                <th width="50px">STT</th>
	                                <th width="20%">Chuẩn G</th>
	                                <th>Mô tả</th>
	                                <th width="150px">Thao tác</th>
	                            </tr>	
	                        </thead>
	                        <tbody>
	                            <c:set value="${(data.currentPage - 1) * data.limit}" var="index"></c:set>
	                             <c:forEach items = "${data.items}" var = "g">
	                                <tr>
	                                    <td> 
	                                        <c:set value="${index + 1}" var="index"/>
	                                        ${index}   
	                                    </td>
	                                    <td>${g.maG}</td>
	                                    <td>${g.getMota()}</td>
	                                    <td>
                                            <c:if test="${loginedUser.roles.contains(ADMIN_ROLE)}">
	                                    	<a href="<%= pathWebcontent %>/g-edit?g-id=${g.id}&backPage=${data.currentPage}" class="btn btn-primary btn-sm">Sửa</a>
	                                        <a href="#" class="btn btn-danger btn-sm btn-delete" 
	                                           data-confirm="Xóa ${g.maG}?|Bạn thật sự muốn xóa ${g.maG} không?" 
	                                           data-confirm-yes="confirmDeleteG(${g.id}, this)"
	                                        >Xóa</a>
                                            </c:if>
	                                    </td>
	                                </tr>
	                             </c:forEach>
	                             
	                              <c:if test="${data.items.size() == 0}">
	                                <tr>
	                                    <td colspan="4" class="text-center"><i style="color: red">Không có G nào được tạo</i></td>
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
            <p>Xảy ra lỗi, không thể xóa G này được.</p>
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
            <p>Đã thực hiện xóa G thành công</p>
          </div>
          <div class="modal-footer bg-whitesmoke br">
            <button type="button" class="btn btn-primary" data-dismiss="modal" onclick="window.location.reload();">Đóng</button>
          </div>
        </div>
      </div>
    </div>
    </div>
  </div>
  <jsp:include page="../fragments/logoutModal.jsp" />  
  <!-- Page Specific JS File -->
 <jsp:include page="../fragments/scripts.jsp" />   
 <script src="<%= pathWebcontent %>/resources/js/jquery-ui.js"></script>
 <script src="<%= pathWebcontent %>/resources/js/pagination.min.js"></script>
 <script src="<%= pathWebcontent %>/resources/js/pagination.make.js"></script>
 <script src="<%= pathWebcontent %>/resources/js/g/index.js"></script>
</body>
</html>





