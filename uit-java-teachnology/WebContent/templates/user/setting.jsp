<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<% String pathWebcontent = request.getContextPath(); %>

<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta content="width=device-width, initial-scale=1, maximum-scale=1, shrink-to-fit=no" name="viewport">
  <title>Setting</title>
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
          <div class="section-header">
            <h1>Cài đặt</h1>
          </div>
          <!-- Breadcumb -->
          <div class="section-body">
            <div class="col-lg-6">
                <div class="card card-large-icons">
                  <div class="card-icon bg-primary text-white">
                    <i class="fas fa-lock"></i>
                  </div>
                  <div class="card-body">
                    <h4>Đổi mật khẩu</h4>
                    <p>Thay đổi mật khẩu cho tài khoản của bạn</p>
                    <a href="<%= pathWebcontent %>/edit-pwd" class="card-cta">Thay đổi <i class="fas fa-chevron-right"></i></a>
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
  
</body>
</html>