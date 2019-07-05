<%@page import="uit.utils.Constants"%>
<%@page import="uit.model.User"%>
<%@page import="uit.modelview.IndexModelView"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<% String pathWebcontent = request.getContextPath(); %> 

<c:set value="<%= Constants.GV_USER %>" var="GV_USER"></c:set>
<c:set value="<%= Constants.SV_USER %>" var="SV_USER"></c:set>
<c:set value="<%= Constants.ADMIN_ROLE %>" var="ADMIN_ROLE"></c:set>
<c:set value="<%= Constants.STUDENT_ROLE %>" var="STUDENT_ROLE"></c:set>
<c:set value="<%= Constants.LECTURER_ROLE %>" var="LECTURER_ROLE"></c:set>
<c:set value="<%= Constants.HEAD_FACULY_ROLE %>" var="HEAD_FACULY_ROLE"></c:set>

<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta content="width=device-width, initial-scale=1, maximum-scale=1, shrink-to-fit=no" name="viewport">
  <title>Danh sách người dùng</title>
  <!-- Template CSS -->
  <jsp:include page="../fragments/css.jsp" />  
  <link href="<%= pathWebcontent %>/resources/css/jquery-ui.min.css" rel="stylesheet">  
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
            <h1>Danh sách người dùng</h1>
            <div class="section-header-breadcrumb">
                <div class="breadcrumb-item active"><a href="<%= pathWebcontent %>">Trang chủ</a></div>
                <div class="breadcrumb-item">Tài Khoản</div>
            </div> 
          </div>    
          
         <!-- ### Message  ###  -->
           <!-- Message alert -->
           <jsp:include page="../fragments/message.jsp"></jsp:include>
         <!-- ### End Message  ###  -->
           
          <!-- Page content -->
          <div class="section-body">
             <div class="card card-primary">
                <div class="card-header">
                    <h4>Danh sách tài khoản</h4>                    
                </div>
                <div class="card-body table-responsive p-0">
                    <form method="get" action="<%=pathWebcontent%>/tai-khoan">
                    <table class="table table-striped">
                        <thead>
                            <tr>
                                <th width="100px">STT</th>
                                <th width="200px">Mã Số</th>
                                <th >Tên</th>
                                <th width="200px">Trạng Thái</th>
                                <th width="200px">Loại tài khoản</th>
                                <th >Quyền</th>
                                <th></th>
                            </tr>
                        </thead>
                        <tbody>                            
                            <tr class="bg-white border-bottom">
                               <td class="pr-0">
                                  <a style="width: 52px;" href="<%=pathWebcontent%>/tai-khoan" class="btn btn-sm btn-danger">Bỏ lọc</a>
                               </td>
                               <td><input id="code" name="code" type="text"></td>
                               <td><input id="name" name="name" type="text"></td>
                               <td>
                                 <select id="status" name="status">
                                   <option value="">Tất cả</option>
                                   <option value="true">Hoạt động</option>
                                   <option value="false">Vô hiệu hóa</option>
                                 </select>               
                               </td>
                               <td>
                                 <select id="type" name="type">
                                   <option value="">Tất cả</option>
                                   <option value="${GV_USER}">Giảng viên</option>
                                   <option value="${SV_USER }">Sinh viên</option>
                                 </select>                               
                               </td>
                               <td>
                                 <select id="role" name="role">
                                   <option value="">Tất cả</option>
                                   <option value="${LECTURER_ROLE}">Giảng viên</option>
                                   <option value="${STUDENT_ROLE}">Sinh viên</option>
                                   <option value="${HEAD_FACULY_ROLE}">Trưởng khoa</option>
                                   <option value="${ADMIN_ROLE}">Quản trị viên</option>
                                 </select>                                 
                               </td>
                               <td>
                                  <button class="btn btn-primary btn-sm" type="submit">Lọc</button>                                  
                               </td>
                            </tr>
                            <c:set value="${(data.currentPage - 1) * data.limit}" var="index"></c:set>
                            <c:forEach items="${data.items}" var="user">
                               <tr>
                                  <td>
                                    <c:set value="${index + 1}" var="index"/>
                                    ${index}                                  
                                  </td>
                                  <td>${user.code}
                                    <div class="table-links">
                                       <a href="<%= pathWebcontent %>/user-detail?id=${user.id}">Xem chi tiết/Chỉnh sửa</a>
                                     </div>
                                  </td>
                                  <td>${user.name}</td>
                                  <td>
                                    <c:if test="${user.status == true }">
                                       <div class="badge badge-pill badge-success p-1">Hoạt động</div>
                                    </c:if>
                                    <c:if test="${user.status == false }">
                                        <div class="badge badge-pill badge-danger p-1">Vô hiệu hóa</div>
                                    </c:if>
                                  </td>
                                  <td>
                                    <c:if test="${user.userType == GV_USER}">
                                        <div class="badge badge-pill badge-primary p-1">Giảng Viên</div>
                                    </c:if>
                                    <c:if test="${user.userType == SV_USER}">
                                        <div class="badge badge-pill badge-info p-1">Sinh Viên</div>
                                    </c:if>
                                  </td>
                                  <td colspan="2">
                                    <c:forEach items="${user.roles}" var="roles">
                                      <div class="badge badge-pill badge-info p-1">
                                        <c:if test="${roles == ADMIN_ROLE}">Admin</c:if>
                                        <c:if test="${roles == STUDENT_ROLE}">Sinh viên</c:if>
                                        <c:if test="${roles == LECTURER_ROLE}">Giảng viên</c:if>
                                        <c:if test="${roles == HEAD_FACULY_ROLE}">Trưởng khoa</c:if>
                                      </div>
                                    </c:forEach>
                                  </td>
                               </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                    </form>
                    <div class="ml-1">
                        <div id="pagination"  data-current-page="${data.currentPage}" data-total-page="${data.numberPage}"
                            data-number-items="${ data.totalItems}"  data-limit="${data.limit}"></div>    
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
 <script src="<%= pathWebcontent %>/resources/js/jquery-ui.js"></script>
 <script src="<%= pathWebcontent %>/resources/js/pagination.min.js"></script>
 <script src="<%= pathWebcontent %>/resources/js/pagination.make.js"></script>
 <script type="text/javascript">
 	$(function() {
 		var url = new URL(window.location.href);
 		$("#code").val(url.searchParams.get("code"));
 		$("#name").val(url.searchParams.get("name"));
 		$("#status").val(url.searchParams.get("status"));
 		$("#type").val(url.searchParams.get("type"));
 		$("#role").val(url.searchParams.get("role")); 
 		
 		makePaginationById("pagination");
 	});
 </script>
</body>
</html>