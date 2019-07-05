<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<% String pathWebcontent = request.getContextPath(); %> 
<!-- Show error delete message -->
 <div class="modal fade" tabindex="-1" role="dialog" id="logoutModal" aria-modal="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title">Thông báo</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
        <p>Bạn có chắc muốn đăng xuất khỏi hệ thống không?.</p>
      </div>
      <div class="modal-footer bg-whitesmoke br">
        <form action="<%= pathWebcontent %>/logout" method="POST">
           <button type="submit" class="btn btn-primary" >Đăng xuất</button>
        </form>
        <button type="button" class="btn btn-secondary" data-dismiss="modal">Đóng</button>
      </div>
    </div>
  </div>
</div>