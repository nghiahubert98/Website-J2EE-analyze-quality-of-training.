<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<% String pathWebcontent = request.getContextPath(); %> 

<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta content="width=device-width, initial-scale=1, maximum-scale=1, shrink-to-fit=no" name="viewport">
  <title>Đổi mật khẩu</title>
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
            <h1>Cài đặt</h1>
            <div class="section-header-breadcrumb">
                <div class="breadcrumb-item active"><a href="<%= pathWebcontent %>">Trang chủ</a></div>
                <div class="breadcrumb-item"><a href="<%= pathWebcontent %>/features-settings">Cài đặt</a></div>
                <div class="breadcrumb-item">Đổi mật khẩu</div>
            </div> 
          </div>    
          <jsp:include page="../fragments/message.jsp"></jsp:include> 
          <!-- Page content -->
          <div class="section-body">
             <div class="card author-box card-primary">
                <div class="card-header">
                  <h4>Đổi mật khẩu</h4>     
                  <div class="ml-auto">
                      <a class="btn btn-white rounded btn-lg btn-icon icon-left"
                         href="<%= pathWebcontent %>/features-settings">
                            <i class="fas fa-chevron-left"></i>
                            Quay lại
                      </a>
                  </div>
                </div>
                <div class="card-body">
                    <div class="row">
                        
                      <div class="col-md-6">
                        <form action="<%= pathWebcontent %>/edit-pwd" method="POST" class="needs-validation" novalidate>
                        
                          <div class="form-group">
                            <label>Mật khẩu hiện tại</label>
                            <input type="password" name="oldPwd" class="form-control" 
                            required>
                            <div class="invalid-feedback">
                              Không được để trống trường này
                             </div>
                             <c:if test="${not empty error}">
                                <c:if test='${not empty error["oldPwd"] }'>
                                    <label class="text-danger">${error["oldPwd"] }</label>
                                </c:if>
                             </c:if>
                          </div>
                          
                          <div class="form-group">
                            <label>Mật khẩu mới</label>
                            <input type="password" name="newPwd" class="form-control"
                             value="" required>
                             <div class="invalid-feedback">
                              Không được để trống trường này
                             </div>
                            <c:if test="${not empty error}">
                                <c:if test='${not empty error["newPwd"] }'>
                                    <label class="text-danger">${error["newPwd"] }</label>
                                </c:if>
                             </c:if>
                          </div>
                          
                          <div class="form-group">
                            <label>Nhập lại mật khẩu mới</label>
                            <input type="password" name="confirmPwd" class="form-control" value="" required>
                             <div class="invalid-feedback">
                              Không được để trống trường này
                             </div>
                            <c:if test="${not empty error}">
                                <c:if test='${not empty error["confirmPwd"] }'>
                                    <label class="text-danger">${error["confirmPwd"] }</label>
                                </c:if>
                             </c:if>
                          </div>
                          
                          <div class="form-group">   
                            <button class="btn btn-primary mr-2" type="submit">Lưu</button>
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
  
</body>
</html>