<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% String pathWebcontent = request.getContextPath(); %>
<div class="header clearfix">
    <h6 class="text-dark d-inline">Danh sách câu hỏi đề thi giữa kỳ</h6>
    <c:if test="${loginedUser.code == data[0].maGV}">
        <button class="btn btn-primary mb-2 float-right"
            onclick="showCreateModal('gk')">Thêm mới</button>
    </c:if>
</div>
<hr>
<table class="table table-striped table-bordered">
    <thead>
        <tr>
            <th width="150px">Thứ tự câu hỏi</th>
            <th>Các chuẩn G</th>
            <th width="20%">Điểm</th>
            <th width="20%">Thao tác</th>
        </tr>
    </thead>
    <tbody>
        <c:set value="0" var="order" scope="request"></c:set>
        <c:forEach items="${data}" var="item">
            <c:if test="${item.dethiType ==  DE_GK}">
                <c:set value="${item.dethiId}" var="deGKID"
                    scope="request"></c:set>

                <c:if test="${order < item.order}">
                    <tr>
                        <c:set value="${item.order}" var="order"></c:set>
                        <td>${item.order}</td>
                        <td><c:set value="0" var="flag"></c:set> <c:forEach
                                items="${data}" var="showGItem">
                                <c:if
                                    test="${showGItem.order ==  order && showGItem.dethiType ==  DE_GK}">
                                    <span
                                        class="badge badge-info py-1 px-2"
                                        style="cursor: pointer;"
                                        title="${showGItem .motaG}"
                                        data-toggle="tooltip">${showGItem.maG}</span>
                                    <c:if
                                        test="${not empty showGItem.maG }">
                                        <c:set value="1" var="flag"></c:set>
                                    </c:if>
                                </c:if>
                            </c:forEach> <c:if test="${flag == 0}">
                                <span class="text-danger">Chưa
                                    quy định chuẩn G cho câu hỏi này</span>
                            </c:if></td>
                        <td>${item.maxPoint}</td>
                        <td><c:if
                                test="${loginedUser.code == data[0].maGV}">
                                <button class="btn btn-warning btn-sm" onclick="editQuestion(${item.id}, ${item.order}, ${item.maxPoint})">Sửa</button>
                                <button class="btn btn-danger btn-sm" data-confirm="Xóa câu hỏi ${item.id}?|Bạn thật sự muốn xóa câu hỏi này không?" 
	                                           data-confirm-yes="confirmDelete(${item.id}, this)"
	                            >Xóa</button>
                            </c:if></td>
                    </tr>
                </c:if>

            </c:if>
        </c:forEach>
        <c:if test="${order == 0}">
            <tr>
                <td colspan="3">Chưa có câu hỏi nào</td>
            </tr>
        </c:if>
    </tbody>
</table>