<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <% String pathWebcontent = request.getContextPath(); %>
 
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta content="width=device-width, initial-scale=1, maximum-scale=1, shrink-to-fit=no" name="viewport">
  <title>500</title>  
  <jsp:include page="fragments/css.jsp" /> 
</head>
<body>
  <div id="app">
    <section class="section">
      <div class="container mt-5">
        <div class="page-error">
          <div class="page-inner">
            <h1>500</h1>
            <div class="page-description">
              Server Error
            </div>
          </div>   
        </div>
        <div class="col-12">
          <div class="mt-3 text-center">
               <a href="<%= pathWebcontent %>" class="text-center">Quay về trang chủ</a>
           </div>
        </div>
        <div class="simple-footer mt-5">
          Copyright &copy; UIT-TEAM
        </div>
      </div>
    </section>
  </div>
</body>
</html>
