<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<% String pathWebcontent = request.getContextPath(); %>

<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta content="width=device-width, initial-scale=1, maximum-scale=1, shrink-to-fit=no" name="viewport">
  <title>Thống kê</title>
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
            <h1>Tỉ lệ đạt chuẩn</h1>
          </div>
          <!-- Breadcumb -->
          <div class="section-body">
            <div class="col-lg-6">
                <div class="card card-large-icons">
                  <div class="card-icon bg-primary text-white">
                    <i class="fas fa-chart-bar"></i>
                  </div>
                  <div class="card-body">
                    <h4>Theo lớp</h4>
                    <p>Tỉ lệ đạt chuẩn theo từng lớp</p>
                    <a href="<%= pathWebcontent %>/monhoc?check=1" class="card-cta">Xem <i class="fas fa-chevron-right"></i></a>
                  </div>
                </div>
              </div>
              <div class="col-lg-6">
                <div class="card card-large-icons">
                  <div class="card-icon bg-primary text-white">
                    <i class="fas fa-chart-bar"></i>
                  </div>
                  <div class="card-body">
                    <h4>Tích luỹ</h4>
                    <p>Tỉ lệ đạt chuẩn hiện tại</p>
                    <a href="<%= pathWebcontent %>/thong-ke" class="card-cta">Xem <i class="fas fa-chevron-right"></i></a>
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