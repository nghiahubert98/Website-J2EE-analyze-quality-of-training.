<%@ page import="uit.model.TiLeG"%>
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
            <h1>Thống kê</h1>
          </div>
          
          <!-- ### Message  ###  -->
          <!-- Message alert -->
          	<jsp:include page="../fragments/message.jsp"></jsp:include>
          <!-- ### End Message  ###  -->
         
          <!-- Breadcumb -->
          <div class="section-body">
          	<div class="card-body p-0">
              <div class="table-responsive">
                 <div class="card card-primary">
                	<div class="card-header">
                  		<h4>Chuẩn</h4>    
                  		<div class="card-header-action">
			            	<a class="btn active" href="#item-g" data-tab="item-tab">G</a>
			            	<a class="btn" href="#item-lo" data-tab="item-tab">LO</a>
                 		</div>                
                	</div>
	                <div class="card-body">
	                  <div class="row">
	                    <div class="col-md-12 mt-3">
	                      <jsp:include page="./chuanG.jsp" flush="false"></jsp:include>
	                      <jsp:include page="./chuanLO.jsp" flush="false"></jsp:include>                      
	                    </div>
	                  </div>
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
  <script src="<%= pathWebcontent %>/resources/js/jquery-ui.js"></script>
  <script src="<%= pathWebcontent %>/resources/js/pagination.min.js"></script>
  <script src="<%= pathWebcontent %>/resources/js/pagination.make.js"></script>
  <script src="<%= pathWebcontent %>/resources/js/components-table.js"></script>
</body>
</html>