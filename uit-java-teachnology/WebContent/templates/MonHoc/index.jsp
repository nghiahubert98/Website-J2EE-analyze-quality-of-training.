<%@page import="uit.model.MonHoc"%>
<%@page import="uit.modelview.IndexModelView"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<% String pathWebcontent = request.getContextPath(); %> 

<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta content="width=device-width, initial-scale=1, maximum-scale=1, shrink-to-fit=no" name="viewport">
  <title>Danh sách môn học </title>
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
            <h1>Danh sách môn học</h1>
            <div class="section-header-breadcrumb">
                <div class="breadcrumb-item active"><a href="<%= pathWebcontent %>">Trang chủ</a></div>
                <div class="breadcrumb-item">Môn học</div>
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
                    <h4>Danh sách môn học</h4>
                    <div class="ml-auto">
                        <div class="dropdown">
                        <button type="button" id="dropdownMenuButton"
                            class="btn btn-white rounded-circle text-center" 
                            data-toggle="dropdown" title="Các thao tác với danh sách môn học"
                            aria-haspopup="true" aria-expanded="false">
                            <i class="fas fa-ellipsis-v"></i>
                        </button>
                        <div class="dropdown-menu dropdown-menu-right"  aria-labelledby="dropdownMenuButton">
                            <a class="dropdown-item" href="<%= pathWebcontent %>/monhoc-create">Thêm mới</a>
                            <a class="dropdown-item" href="<%= pathWebcontent %>/monhoc-excel">Thêm bằng Excel</a>                        
                          </div>
                        </div>
                    </div>
                </div>
                <div class="card-body table-responsive p-0">
                     <form method="get" action="<%=pathWebcontent%>/monhoc">
                     <table class="table table-striped">
                        <thead>
                            <tr>
                                <th width="50px">STT</th>
                                <th width="20%">Mã môn học</th>
                                <th>Tên</th>
                                <th width="150px">Thao tác</th>
                            </tr>
                        </thead>
                        <tbody>
                        	<tr class="bg-white border-bottom">
                               <td class="pr-0">
                                  <a style="width: 52px;" href="<%=pathWebcontent%>/monhoc" class="btn btn-sm btn-danger">Bỏ lọc</a>
                               </td>
                               <td><input id="maMonHoc" name="maMonHoc" class="form-control form-control-sm" type="text"></td>
                               <td><input id="name" name="name" type="text" class="form-control form-control-sm"></td>
                               <td>
                                  <button class="btn btn-primary btn-sm" type="submit">Lọc</button>                                  
                               </td>
                            </tr>
                            <c:set value="${(data.currentPage - 1) * data.limit}" var="index"></c:set>
                             <c:forEach items = "${data.items}"  var = "MonHoc">
                                <tr>
                                    <td> 
                                        <c:set value="${index + 1}" var="index"/>
                                        ${index}   
                                    </td>
                                    <td>${MonHoc.maMonHoc}
                                        <div class="table-links">
                                             <a href="<%= pathWebcontent %>/thong-ke?idMon=${MonHoc.id}">Xem chi tiết</a>
                                         </div>
                                    </td>
                                    <td>${MonHoc.name}</td>
                                    <td>
                                        <a href="<%= pathWebcontent %>/monhoc-edit?id=${MonHoc.id}&backPage=${data.currentPage}" 
                                            class="btn btn-primary btn-sm"  data-id="${MonHoc.id}">Sửa</a>
                                        <a href="#" class="btn btn-danger btn-sm btn-delete" 
                                           data-confirm="Xóa môn học?|Bạn thật sự muốn xóa môn học này không?" 
                                           data-confirm-yes="confirmDelete(${MonHoc.id},this)"
                                        >Xóa</a>
                                    </td>
                                </tr>
                             </c:forEach>
                             
                              <c:if test="${data.items.size() == 0}">
                                <tr>
                                    <td colspan="4" class="text-center">Không có môn học nào được tạo</td>
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
            <p>Xảy ra lỗi, không thể xóa môn học này được.</p>
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
            <p>Đã thực hiện xóa môn học thành công</p>
          </div>
          <div class="modal-footer bg-whitesmoke br">
            <button type="button" class="btn btn-primary" data-dismiss="modal" onclick="window.location.reload();">Đóng</button>
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
 <script src="<%= pathWebcontent %>/resources/js/monhoc/index.js"></script>
 
</body>
</html>





