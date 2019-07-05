<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>    
<% String pathWebcontent = request.getContextPath(); %>  
<!-- Servlet context path -->
<script>var ctx = "<%= pathWebcontent %>"</script> 
<!-- General JS Scripts -->
<script src="<%= pathWebcontent %>/resources/js/jquery-3.3.1.min.js"></script>
<script src="<%= pathWebcontent %>/resources/js/popper.min.js"></script>
<script src="<%= pathWebcontent %>/resources/js/bootstrap.min.js"></script>
<script src="<%= pathWebcontent %>/resources/js/jquery.nicescroll.min.js"></script>
<script src="<%= pathWebcontent %>/resources/js/moment.min.js"></script>
<script src="<%= pathWebcontent %>/resources/js/stisla.js"></script>
<!-- Template JS File -->
<script src="<%= pathWebcontent %>/resources/js/scripts.js"></script>
<script src="<%= pathWebcontent %>/resources/js/custom.js"></script>
