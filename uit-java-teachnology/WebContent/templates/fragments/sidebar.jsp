<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="uit.utils.Constants"%>
<%String pathWebcontent = request.getContextPath();%>
<c:set value="<%=Constants.ADMIN_ROLE%>" var="ADMIN_ROLE"></c:set>
<c:set value="<%=Constants.STUDENT_ROLE%>" var="STUDENT_ROLE"></c:set>
<c:set value="<%=Constants.LECTURER_ROLE%>" var="LECTURER_ROLE"></c:set>
<c:set value="<%=Constants.HEAD_FACULY_ROLE%>" var="HEAD_FACULY_ROLE"></c:set>
<div class="main-sidebar bg-gradient-primary bg-gradient-primary sidebar sidebar-dark accordion">
	<aside id="sidebar-wrapper bg-gradient-primary">
		<div class="sidebar-brand">
			<a href="<%=pathWebcontent%>">UIT</a>
		</div>
		<div class="sidebar-brand sidebar-brand-sm">
			<a href="<%=pathWebcontent%>">UIT</a>
		</div>
		<ul class="sidebar-menu">
			<li class="menu-header">Dashboard</li>
              <li class="nav-item">
                <a href="<%=pathWebcontent%>" class="nav-link"><i class="fas fa-fire"></i><span>Dashboard</span></a>
              </li>
			<!--  START SIDEBAR ADMIN -->
			<c:if test="${not empty loginedUser && loginedUser.roles.contains(ADMIN_ROLE) }">
              
              <li class="menu-header">QUẢN LÝ NGƯỜI DÙNG</li>
              <li class="nav-item dropdown active">
                <a href="#" class="nav-link has-dropdown" data-toggle="dropdown"><i class="fas fa-users"></i> <span>Người dùng</span></a>
                <ul class="dropdown-menu">
                  <li class="nav-link"><a class="nav-link beep beep-sidebar" href="<%=pathWebcontent%>/tai-khoan">Tất cả</a></li>
                  <li class="nav-link"><a class="nav-link" href="<%=pathWebcontent%>/giang-vien">Giảng viên</a></li>
                  <li class="nav-link"><a class="nav-link" href="<%=pathWebcontent%>/sinhvien">Sinh viên</a></li>
                </ul>
              </li>
              
              <li class="menu-header">TÁC VỤ</li>
              <li class="nav-item dropdown">
                <a href="#" class="nav-link has-dropdown"><i class="fas fa-chart-line"></i> <span>Thống kê</span></a>
                <ul class="dropdown-menu">
                  <li><a class="nav-link beep beep-sidebar" href="<%=pathWebcontent%>/giang-vien">Giảng viên</a></li>
                  <li><a class="nav-link beep beep-sidebar" href="<%=pathWebcontent%>/sinhvien">Sinh viên</a></li>
                </ul>
              </li>
              <li class="nav-item dropdown">
                <a href="#" class="nav-link has-dropdown"><i class="fas fa-cogs"></i> <span>Ngành</span></a>
                <ul class="dropdown-menu">
                  <li><a class="nav-link" href="<%=pathWebcontent%>/nganh">Danh sánh ngành</a></li>
                  <li><a class="nav-link beep beep-sidebar" href="<%=pathWebcontent%>/nganh-create">Thêm ngành</a></li>
                </ul>
              </li>
              <li class="nav-item dropdown">
                <a href="#" class="nav-link has-dropdown"><i class="fas fa-award"></i> <span>Các chuẩn LO</span></a>
                <ul class="dropdown-menu">
                  <li><a class="nav-link" href="<%=pathWebcontent%>/chuan-lo">Danh sách chuẩn LO</a></li>
                  <li><a class="nav-link beep beep-sidebar" href="<%=pathWebcontent%>/lo-create">Thêm chuẩn LO</a></li>
                </ul>
              </li>
              <li class="nav-item dropdown">
                <a href="#" class="nav-link has-dropdown"><i class="fas fa-award"></i><span>Các chuẩn G</span></a>
                <ul class="dropdown-menu">
                  <li><a href="<%=pathWebcontent%>/chuan-lo">Danh sách chuẩn G</a></li>
                  <li><a class="nav-link beep beep-sidebar" href="<%=pathWebcontent%>/g-create">Thêm chuẩn G</a></li>
                </ul>
              </li>
              <li class="nav-item dropdown">
                <a href="#" class="nav-link has-dropdown"><i class="fas fa-chalkboard-teacher"></i> <span>Lớp học</span></a>
                <ul class="dropdown-menu">
                  <li><a class="nav-link" href="<%=pathWebcontent%>/lop">Danh sách lớp học</a></li>
                  <li><a class="nav-link beep beep-sidebar" href="<%=pathWebcontent%>/lop-create">Thêm lớp học</a></li>
                </ul>
              </li>
              <li class="nav-item dropdown">
                <a href="#" class="nav-link has-dropdown"><i class="fas fa-graduation-cap"></i> <span>Môn học</span></a>
                <ul class="dropdown-menu">
                  <li><a class="nav-link" href="<%=pathWebcontent%>/monhoc">Danh sách môn học</a></li>
                  <li><a class="nav-link beep beep-sidebar" href="<%=pathWebcontent%>/monhoc-create">Thêm môn học</a></li>
                </ul>
              </li>
			</c:if>
			<!--  END SIDEBAR ADMIN -->
			
			<!--  START SIDEBAR HEAD_FACULY -->
			<c:if test="${not empty loginedUser && loginedUser.roles.contains(HEAD_FACULY_ROLE) 
			&& !loginedUser.roles.contains(ADMIN_ROLE) 
			&& !loginedUser.roles.contains(LECTURER_ROLE)}">
              
              <li class="menu-header">QUẢN LÝ NGƯỜI DÙNG</li>
              <li class="nav-item dropdown active">
                <a href="#" class="nav-link has-dropdown" data-toggle="dropdown"><i class="fas fa-users"></i> <span>Người dùng</span></a>
                <ul class="dropdown-menu">
                  <li class="nav-link"><a class="nav-link beep beep-sidebar" href="<%=pathWebcontent%>/tai-khoan">Tất cả</a></li>
                  <li class="nav-link"><a class="nav-link" href="<%=pathWebcontent%>/giang-vien">Giảng viên</a></li>
                  <li class="nav-link"><a class="nav-link" href="<%=pathWebcontent%>/sinhvien">Sinh viên</a></li>
                </ul>
              </li>
              
              <li class="menu-header">TÁC VỤ</li>
              <li class="nav-item dropdown">
                <a href="#" class="nav-link has-dropdown"><i class="fas fa-chart-line"></i> <span>Thống kê</span></a>
                <ul class="dropdown-menu">
                  <li><a class="nav-link beep beep-sidebar" href="<%=pathWebcontent%>/giang-vien">Giảng viên</a></li>
                  <li><a class="nav-link beep beep-sidebar" href="<%=pathWebcontent%>/sinhvien">Sinh viên</a></li>
                </ul>
              </li>
              <li class="nav-item dropdown">
                <a href="#" class="nav-link has-dropdown"><i class="fas fa-cogs"></i> <span>Ngành</span></a>
                <ul class="dropdown-menu">
                  <li><a class="nav-link" href="<%=pathWebcontent%>/nganh">Danh sánh ngành</a></li>
                </ul>
              </li>
              <li class="nav-item dropdown">
                <a href="#" class="nav-link has-dropdown"><i class="fas fa-award"></i> <span>Các chuẩn LO</span></a>
                <ul class="dropdown-menu">
                  <li><a class="nav-link" href="<%=pathWebcontent%>/chuan-lo">Danh sách chuẩn LO</a></li>
                </ul>
              </li>
              <li class="nav-item dropdown">
                <a href="#" class="nav-link has-dropdown"><i class="fas fa-award"></i><span>Các chuẩn G</span></a>
                <ul class="dropdown-menu">
                  <li><a href="<%=pathWebcontent%>/chuan-lo">Danh sách chuẩn G</a></li>
                </ul>
              </li>
              <li class="nav-item dropdown">
                <a href="#" class="nav-link has-dropdown"><i class="fas fa-chalkboard-teacher"></i> <span>Lớp học</span></a>
                <ul class="dropdown-menu">
                  <li><a class="nav-link" href="<%=pathWebcontent%>/lop">Danh sách lớp học</a></li>
                </ul>
              </li>
              <li class="nav-item dropdown">
                <a href="#" class="nav-link has-dropdown"><i class="fas fa-graduation-cap"></i> <span>Môn học</span></a>
                <ul class="dropdown-menu">
                  <li><a class="nav-link" href="<%=pathWebcontent%>/monhoc">Danh sách môn học</a></li>
                </ul>
              </li>
			</c:if>
			<!--  END SIDEBAR HEAD_FACULY -->
			
			<!--  START SIDEBAR GIAO VIEN -->
			<c:if test="${not empty loginedUser && loginedUser.roles.contains(LECTURER_ROLE) && !loginedUser.roles.contains(ADMIN_ROLE)}">
			  <li class="menu-header">THÔNGTIN</li>
			  <li class="nav-item dropdown active">
                <a href="#" class="nav-link has-dropdown" data-toggle="dropdown"><i class="fas fa-university"></i><span>Ngành</span></a>
                <ul class="dropdown-menu">
                  <li class="nav-link"><a class="nav-link beep beep-sidebar" href="<%=pathWebcontent%>/nganh">Danh sách ngành</a></li>
                </ul>
              </li>
              <li class="nav-item dropdown active">
                <a href="#" class="nav-link has-dropdown" data-toggle="dropdown"><i class="fas fa-user-graduate"></i> <span>Lớp học</span></a>
                <ul class="dropdown-menu">
                  <li class="nav-link"><a class="nav-link beep beep-sidebar" href="<%=pathWebcontent%>/lop">Lớp đang giảng dạy</a></li>
                </ul>
              </li>
              
              <li class="menu-header">TÁC VỤ</li>
              <li class="nav-item dropdown">
                <a href="#" class="nav-link has-dropdown"><i class="fas fa-chart-line"></i> <span>Thống kê</span></a>
                <ul class="dropdown-menu">
                  <li><a class="nav-link beep beep-sidebar" href="#">Điểm học tập của sinh viên</a></li>
                  <li><a class="nav-link beep beep-sidebar" href="<%=pathWebcontent%>/lop?idGv=${loginedUser.id}">Tỉ lệ đạt chuẩn</a>
                </ul>
              </li>
              
			</c:if>
			<!--  END SIDEBAR GIAO VIEN -->
			
			
			<!--  START SIDEBAR SINH VIEN -->
			<c:if test="${not empty loginedUser && loginedUser.roles.contains(STUDENT_ROLE) }">
			  <li class="menu-header">THÔNGTIN</li>
			  <li class="nav-item dropdown active">
                <a href="#" class="nav-link has-dropdown" data-toggle="dropdown"><i class="fas fa-university"></i> <span>Ngành</span></a>
                <ul class="dropdown-menu">
                  <li class="nav-link"><a class="nav-link beep beep-sidebar" href="<%=pathWebcontent%>/nganh">Danh sách ngành</a></li>
                </ul>
              </li>
              <li class="nav-item dropdown active">
                <a href="#" class="nav-link has-dropdown" data-toggle="dropdown"><i class="fas fa-user-graduate"></i> <span>Lớp học</span></a>
                <ul class="dropdown-menu">
                  <li class="nav-link"><a class="nav-link beep beep-sidebar" href="<%=pathWebcontent%>/lop">Lớp đang theo học</a></li>
                </ul>
              </li>
              
              <li class="menu-header">TÁC VỤ</li>
              <li class="nav-item dropdown">
                <a href="#" class="nav-link has-dropdown"><i class="fas fa-chart-line"></i> <span>Thống kê</span></a>
                <ul class="dropdown-menu">
                  <li><a class="nav-link" href="#">Điểm học tập</a></li>
                  <li><a class="nav-link beep beep-sidebar" href="<%=pathWebcontent%>/thongke-sv">Tỉ lệ đạt chuẩn</a>
                </ul>
              </li>
			</c:if>
			<!--  END SIDEBAR SINH VIEN -->
		
		</ul>
	</aside>
</div>
