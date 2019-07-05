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
  <title>Danh sách ngành học</title>
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
            <h1>Chi tiết tài khoản</h1>
            <div class="section-header-breadcrumb">
                <div class="breadcrumb-item active"><a href="<%= pathWebcontent %>">Trang chủ</a></div>
                <div class="breadcrumb-item"><a href="<%= pathWebcontent %>/tai-khoan">Tài khoản</a></div>
                 <div class="breadcrumb-item">Chi tiết</div>
            </div> 
          </div>    
          
         <!-- ### Message  ###  -->
          <jsp:include page="../fragments/message.jsp"></jsp:include>
         <!-- ### End Message  ###  -->
           
          <!-- Page content -->
          <div class="section-body">
             <div class="card card-primary">
                <div class="card-header">
                    <h4>Thông tin tài khoản</h4>  
                    <div class="ml-auto">
                      <a class="btn btn-white rounded btn-lg btn-icon icon-left"
                         href="<%= pathWebcontent %>/tai-khoan">
                            <i class="fas fa-chevron-left"></i>
                            Quay lại
                      </a>
                  </div>                  
                </div>
                <div class="card-body">
                    <div class="<%= pathWebcontent %>/user-detail">
                    <form action="" method="POST">
                        <input type="hidden" name="id" value="${user.id}">
                        <table class="table table-striped">
                            <tbody>
                                <tr>
                                    <td width="200px" class="font-weight-bold text-uppercase">MÃ SỐ: </td>
                                    <td class="text-left">${user.code}</td>                               
                                </tr>
                                <tr>
                                     <td class="font-weight-bold text-uppercase">Họ tên: </td>
                                    <td class="text-left">${user.name}</td>
                                </tr>
                                <tr>
                                    <td class="font-weight-bold text-uppercase">Trạng thái: </td>
                                    <td class="text-left">
                                        <select name="status" id="status" class="form-control form-control-sm">
                                            <option value="true"  ${user.status == true ? "selected" : "" }>Hoạt động</option>
                                            <option value="false" ${user.status == false ? "selected" : "" }>Vô hiệu hóa</option>
                                        </select>
                                    </td>                                
                                </tr>
                                <tr>
                                    <td class="font-weight-bold text-uppercase">Chức vụ: </td>
                                    <td class="text-left">
                                       <c:if test="${user.userType == GV_USER}">
                                            <div class="badge badge-pill badge-primary p-1">Giảng Viên</div>
                                        </c:if>
                                        <c:if test="${user.userType == SV_USER}">
                                            <div class="badge badge-pill badge-info p-1">Sinh Viên</div>
                                        </c:if>
                                    </td>
                                </tr>
                                <tr>
                                    <td class="font-weight-bold text-uppercase">Quyền</td>
                                    <td class="text-left">
                                        <select name="roles" id="roles" multiple="multiple" required="required">
                                            <option value="${ADMIN_ROLE}" ${user.roles.contains(ADMIN_ROLE) ? "selected" : "" }>Admin</option>
                                            <c:if test="${user.userType == SV_USER }">                                           
                                                <option selected="selected">Sinh viên</option>
                                            </c:if> 
                                             <c:if test="${user.userType == GV_USER }">   
                                                <option value="${LECTURER_ROLE}" selected="selected">Giảng viên</option>
                                                <option value="${HEAD_FACULY_ROLE}" 
                                                    ${user.roles.contains(HEAD_FACULY_ROLE) ? "selected" : "" }>
                                                Trưởng Khoa
                                                </option>
                                             </c:if>      
                                        </select>
                                    </td>
                                </tr>
                                <tr>
                                   <td colspan="2">
                                      <button class="btn btn-primary" id="btnSave">Lưu lại</button>
                                   </td>
                                </tr>
                            </tbody>
                        </table>
                    </form>
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
 <script type="text/javascript" src="<%=pathWebcontent%>/resources/js/BsMultiSelect.min.js"></script>
 <script type="text/javascript">
 	$("#roles").bsMultiSelect({
 		selectedPanelFocusValidBoxShadow: '0 0 0 0.2rem rgba(40, 167, 69, 0.25)',  // valid foxus shadow
        selectedPanelFocusInvalidBoxShadow: '0 0 0 0.2rem rgba(220, 53, 69, 0.25)',  // invalid foxus shadow
 		selectedPanelClass: 'form-control form-control-sm'
 	});
</script>
</body>
</html>