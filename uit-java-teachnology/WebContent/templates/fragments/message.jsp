<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- ### Message  ###  -->
<c:if test='${not empty success }'>
  <c:if test='${not empty success }'>
    <div class="row">
      <div class="message-region col-md-12">
        <div class="alert alert-success alert-dismissible show fade">
          <div class="alert-body">
            <button class="close" data-dismiss="alert">
              <span>&times;</span>
            </button>
            ${success}
          </div>
        </div>
     </div>
  </div>
  </c:if>
</c:if>
<c:if test="${not empty error}">
  <c:if test='${not empty error["svError"] }'>
    <!-- Server error -->
    <div class="row">
      <div class="col-md-12">
        <div class="alert alert-danger">${error["svError"]}</div>
      </div>
    </div>
  </c:if>
</c:if>

