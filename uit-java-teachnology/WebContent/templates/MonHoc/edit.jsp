<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<% String pathWebcontent = request.getContextPath(); %> 

<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta content="width=device-width, initial-scale=1, maximum-scale=1, shrink-to-fit=no" name="viewport">
  <title>Chỉnh sửa thông tin môn học</title>
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
                <div class="breadcrumb-item"><a href="<%= pathWebcontent %>/monhoc">môn học</a></div>
                <div class="breadcrumb-item">Chỉnh sửa</div>
            </div> 
          </div>    
                
          <!-- Page content -->
          <div class="section-body">
             <div class="card card-primary">
                <div class="card-header">
                  <h4>Chỉnh sửa thông tin môn học</h4>     
                  <div class="ml-auto">
                      <a class="btn btn-white rounded btn-lg btn-icon icon-left"
                         href="<%= pathWebcontent %>/monhoc?page=${backPage}">
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
                        <form action="<%= pathWebcontent %>/monhoc-edit?page=${backPage}" method="POST">
                          <input type="hidden" name="id" value="${data.id}">
                          <div class="form-group">
                            <label>Mã môn học</label>
                            <input type="text" name="maMonHoc" class="form-control" 
                             value="${not empty error ? oldData.maMonHoc: data.maMonHoc}">
                             <c:if test="${not empty error}">
                                <c:if test='${not empty error["maMonHoc"] }'>
                                    <label class="text-danger">${error["maMonHoc"] }</label>
                                </c:if>
                             </c:if>
                          </div>
                          <div class="form-group">
                            <label>Tên môn học</label>
                            <input type="text" name="name" class="form-control"
                             value="${not empty error ? oldData.name: data.name}">
                            <c:if test="${not empty error}">
                                <c:if test='${not empty error["name"] }'>
                                    <label class="text-danger">${error["name"] }</label>
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
  
</body>
</html>

