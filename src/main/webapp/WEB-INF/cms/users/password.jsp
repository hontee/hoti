<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="easyui-layout" data-options="fit:true">
  <div data-options="region:'center', border:false" class="cms-wbox">
  <form id="users-password-form" action="/cms/users/${record.id}/password" method="post">
    <div class="cms-mb20">
      <div class="cms-mb5">名称:</div>
      <input class="easyui-textbox" name="name" value="${record.name}" data-options="required:true, readonly: true" style="width:100%; height:32px">
    </div>
    <div class="cms-mb20">
      <div class="cms-mb5">新密码:</div>
      <input class="easyui-textbox" type="password" name="password" data-options="required:true" style="width:100%;height:32px">
    </div>
    <button class="easyui-linkbutton" onclick="usersEditSubmitForm()" style="width:100%;height:32px">修改</button>
  </form>
  </div>
</div>
<script>
function usersEditSubmitForm(){
  $('#users-password-form').form({
    success: function(data) {
    	CMS.editSubmitHandler(data, usersEL);
    }
  });
}
</script>
</body>