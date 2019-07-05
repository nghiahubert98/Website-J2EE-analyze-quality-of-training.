<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<% String pathWebcontent = request.getContextPath(); %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta content="width=device-width, initial-scale=1, maximum-scale=1, shrink-to-fit=no" name="viewport">
  <title>Tài Khoản</title>
  <jsp:include page="./fragments/css.jsp"></jsp:include>
</head>

<body>
  <div id="app">
    <section>
        <div class="bg-info text-white p-3 pt-4">
            <h4>Thông Báo</h4>
            Đây là lần đầu tiên đăng nhập của bạn, Xin hãy nhập một mật khẩu mới để tiếp tục xử dụng hệ thống. 
            Từ lần tiếp theo ban có thể đăng nhập với mã số và mật khẩu của bạn
        </div>
        <div>
            <div class="col-md-12">
                <jsp:include page="./fragments/message.jsp"></jsp:include>
            </div>
            <div class="col-md-6 offset-md-3">
                <div class="card card-primary mt-4">
                    <div class="card-header">
                        <h4>Nhập Mật Khẩu</h4>
                    </div>
                    <div class="card-body">
                        <form action="<%= pathWebcontent %>/first-login" method="POST">
                           <div class="form-group">
                            <label>Mật Khẩu</label>
                            <input class="form form-control" type="password" name="pwd" required="required">
                            <c:if test="${not empty error}">
                                <c:if test='${not empty error["pwd"] }'>
                                    <label class="text-danger">${error["pwd"] }</label>
                                </c:if>
                             </c:if>
                            </div>
                            <div class="form-group">
                                <label>Nhập lại mật khẩu</label>
                                <input class="form form-control" type="password" name="pwd_confirm" required="required">
                            </div>
                            <button type="submit" class="btn btn-primary">Lưu Lại</button>
                        </form>  
                    </div>
                </div>
            </div>
        </div>   
    </section>
  </div>
  <jsp:include page="./fragments/scripts.jsp"></jsp:include>
</body>
</html>