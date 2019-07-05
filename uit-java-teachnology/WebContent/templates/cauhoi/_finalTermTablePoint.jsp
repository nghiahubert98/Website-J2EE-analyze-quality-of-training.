<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
    String pathWebcontent = request.getContextPath();
%>
<div class="header clearfix mt-2 border-top py-4">
    <h6 class="text-dark d-inline">Bảng Điểm</h6>
</div>
<hr>
<c:if test="${loginedUser.code == data[0].maGV}">
  <div class="clearfix mb-1">
      <a class="btn btn-light float-left btn-icon icon-left" 
          href="<%=pathWebcontent%>/excel-diem?lid=${data[0].lopId}&dethiId=${deCKID}">
            <i class="fas fa-download"></i>
            Download file mẫu
       </a>
       <button class="btn btn-info float-left btn-icon icon-left ml-2" onclick="getThongkeLop(${data[0].lopId})"><i class="fas fa-chart-line"></i>Thống kê</button>
      <form class="form-inline float-right" action="<%=pathWebcontent%>/excel-diem" 
          method="POST" enctype="multipart/form-data">                
          <div class="form-group">
              <input type="hidden" value="${deCKID}" name="detId">
              <input type="hidden" value="${data[0].lopId}" name="lopId">
              <div class="custom-file">
              <input type="file" name="excel"  accept=".xls, .xlsx"  required class="custom-file-input" id="customFile">
              <label class="custom-file-label" for="customFile">Chọn file Excel</label>
              </div>                    
          </div>    
          <div class="form-group ml-2">
              <button class="btn btn-primary mr-2" type="submit">Import</button>                    
          </div>      
      </form>
  </div>
</c:if>
<c:if test="${not empty cauHoiCK && cauHoiCK.size() > 0}">
  <table class="table table-striped table-bordered">
      <thead>
          <tr>
              <th width="20%">MSSV</th>
              <th>Họ Tên</th>
              <c:forEach items="${cauHoiCK}" var="ch">
                  <th>Câu hỏi ${ch.order}</th>
              </c:forEach>                       
          </tr>
      </thead>
      <tbody>
          <c:set value="0" var="index"></c:set>
          <c:forEach items="${diemDeCK}" var="dCK" varStatus="loop">  
              <c:if test="${(empty preMSSV or preMSSV != dCK.mssv) && loop.index == index}">
                <c:set value="${dCK.mssv}" var="preMSSV"></c:set>    
                  <tr>
                      <td>${dCK.mssv}</td>
                      <td>${dCK.tenSV}</td>
                      <c:forEach items="${diemDeCK}" begin="${index}" end="${cauHoiCK.size() + index - 1}" var="dCK1" varStatus="loop1">
                          <c:if test="${preMSSV == dCK1.mssv}">
                              <td>${dCK1.diem}</td>
                              <c:set value="${loop1.index + 1}" var="index"></c:set>
                          </c:if>                         
                      </c:forEach>
                  </tr>
              </c:if>   
          </c:forEach>
      </tbody>
  </table>
</c:if>
<c:if test="${empty cauHoiCK || cauHoiCK.size() <= 0}">
  <div class="text-center">Chưa nhập điểm cho sinh viên</div>
</c:if>