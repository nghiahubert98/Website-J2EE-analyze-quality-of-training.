<%@ page import="uit.model.TiLeG"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<% String pathWebcontent = request.getContextPath(); %>
<div id="item-g" data-tab-group="item-tab" class="active">
	<div class="header clearfix">
	    <h6 class="text-dark d-inline">Các chuẩn G đã đạt</h6>
	</div>
	<hr>
	<table class="table table-striped" id="sortable-table">
		<thead>
			<tr>
				<th style="width:10%">Mã G</th>
					<th style="width:60%">Mô tả</th>
					<th>Tỉ lệ hoàn thành</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items = "${data_g}" var = "g">
				<tr>
					<td>${g.maG}</td>
						<td>${g.moTa}</td>
						<td>
							<div class="progress" data-height="25">
								<div class="progress-bar bg-success" role="progressbar" data-width="${g.tiLe}%" aria-valuenow="${g.tiLe}"
								 	aria-valuemin="0" aria-valuemax="100">${g.tiLe}%</div>
	                    	</div>
	                    </td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</div>