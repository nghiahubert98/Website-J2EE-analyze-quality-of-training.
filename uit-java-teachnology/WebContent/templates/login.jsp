<%@page import="uit.utils.PropertiesUtil"%>
<%@page import="uit.utils.GoogleUtil"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<% String pathWebcontent = request.getContextPath(); %>
<%
    String gooleLoginURL = PropertiesUtil.getProperties().get("GOOGLE_AUTH_URI") + "?scope=email"
            + "&redirect_uri=" + PropertiesUtil.getProperties().get("GOOGLE_REDIRECT_URI") + "&client_id="
            + PropertiesUtil.getProperties().get("GOOGLE_CLIENT_ID") + "&hd="
            + PropertiesUtil.getProperties().getProperty("GOOGLE_HD")
            + "&approval_prompt=force&response_type=code";
%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta content="width=device-width, initial-scale=1, maximum-scale=1, shrink-to-fit=no" name="viewport">
  <title>Đăng nhâp</title>
  <jsp:include page="./fragments/css.jsp"></jsp:include>
</head>

<body>
  <div id="app">
    <section class="section">
      <div class="d-flex flex-wrap align-items-stretch">
        <div class="col-lg-4 col-md-6 col-12 order-lg-1 min-vh-100 order-2 bg-white">
          <div class="p-4 m-3 mt-5">
            <h4 class="text-dark font-weight-normal mt-3"><span class="font-weight-bold">Đăng nhập</span></h4>
            <form method="POST" action="<%= pathWebcontent %>/login" class="needs-validation" novalidate>
              <div class="form-group">
              
                <!-- Message alert -->
                <jsp:include page="./fragments/message.jsp"></jsp:include>
                
                <label for="email">Tên định danh</label>
                <input id="code" type="text" value="${oldData}" class="form-control" name="code" tabindex="1" required autofocus>
                <div class="invalid-feedback" >
                  Xin hãy điền tên định danh của bạn
                </div>
                 <c:if test="${not empty error}">
                     <c:if test='${not empty error["code"] }'>
                         <label class="text-danger">${error["code"] }</label>
                     </c:if>
                 </c:if>
              </div>

              <div class="form-group">
                <div class="d-block">
                  <label for="password" class="control-label">Password</label>
                </div>
                <input id="password" type="password" class="form-control" name="pwd" tabindex="2" required>
                <div class="invalid-feedback">
                  Xin hãy nhập mật khẩu của bạn
                </div>
                <c:if test="${not empty error}">
                     <c:if test='${not empty error["pwd"] }'>
                         <label class="text-danger">${error["pwd"] }</label>
                     </c:if>
                 </c:if>
              </div>
                
              <!-- Group Button -->
              <div class="form-group">
                <button type="submit" class="btn btn-block btn-primary btn-lg btn-icon icon-right" tabindex="4">
                  Đăng Nhập
                </button>                              
              </div>
              <div class="px-5">
                 <div class="text-center text-dark mt-3 mb-4" 
                    style="height: 20px; line-height: 40px; border-bottom: 1px solid;">
                    <span style="background: white;" class="px-2">Hoặc</span>
                </div> 
              </div>
              <div class="text-center">
                <a href="<%= gooleLoginURL%>"  class="btn btn-danger btn-lg btn-icon icon-right mt-2">
                     Đăng Nhập Bằng Google
                </a>
              </div>
              <!-- /Group Button -->
              
            </form>
            
            <div class="text-center mt-5 text-small">
              Copyright &copy; UIT-TEAM
              <div class="mt-2">
                <a href="#">Privacy Policy</a>
                <div class="bullet"></div>
                <a href="#">Terms of Service</a>
              </div>
            </div>
            
          </div>
        </div>
        <div class="col-lg-8 col-12 order-lg-2 order-1 min-vh-100 background-walk-y position-relative overlay-gradient-bottom"
         data-background="./resources/img/unsplash/login-bg.jpg">
          <div class="absolute-bottom-left index-2">
            <div class="text-light p-5 pb-2">
              <div class="mb-5 pb-3">
                <h1 class="mb-2 display-4 font-weight-bold">Chào mừng đến với</h1>
                <h5 class="font-weight-normal text-muted-transparent">Hệ thống phân tích chất lượng đào tạo</h5>
              </div>
            </div>
          </div>
        </div>
      </div>
    </section>
  </div>
  <jsp:include page="./fragments/scripts.jsp"></jsp:include>
</body>
</html>
