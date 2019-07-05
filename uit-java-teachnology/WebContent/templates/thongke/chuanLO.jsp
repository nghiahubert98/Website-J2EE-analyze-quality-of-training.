<%@ page import="uit.model.ListLO"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<% String pathWebcontent = request.getContextPath(); %>
<div id="item-lo" data-tab-group="item-tab">
	<div class="header clearfix">
	    <h6 class="text-dark d-inline">Các chuẩn LO đã đạt</h6>
	</div>
	<hr>
	<table class="table table-striped" id="sortable-table">
		<thead>
			<tr>
				<th style="width:20%">Mã LO</th>
				<th style="width:80%">Mô tả</th>
			</tr>
		</thead>
	   	<tbody>
			<c:forEach items = "${data_lo}" var = "lo">
				<tr>
					<td>${lo.maLO}</td>
					<td>${lo.moTa}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</div>