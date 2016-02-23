<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="easyui-layout" data-options="fit:true">
  <div data-options="region:'center', border:false" class="cms-wbox">
  <form id="recmds-edit-form" action="/cms/recmds/${record.id}/edit" method="post">
  	<div class="cms-mb20">
      <div class="cms-mb5">网址:</div>
      <input class="easyui-textbox" name="url" value="${record.url}" data-options="readonly:true" style="width:100%; height:32px">
    </div>
    <div class="cms-mb20">
      <div class="cms-mb5">标题:</div>
      <input class="easyui-textbox" name="title" value="${record.title}" style="width:100%;height:32px">
    </div>
    <div class="cms-mb20">
      <div class="cms-mb5">关键词:</div>
      <input class="easyui-textbox" name="keywords" value="${record.keywords}" style="width:100%;height:32px">
    </div>
    <div class="cms-mb20">
      <div class="cms-mb5">状态:</div>
      <select class="easyui-combobox" name="state" data-options="panelHeight:'auto',editable: false" style="width:100%; height:32px">
        <option value="1" <c:if test="${record.state == 1}">selected</c:if>>待审核</option>
        <option value="2" <c:if test="${record.state == 2}">selected</c:if>>审核通过</option>
        <option value="3" <c:if test="${record.state == 3}">selected</c:if>>审核未通过</option>
      </select>
    </div>
    <div class="cms-mb20">
      <div class="cms-mb5">描述:</div>
      <input class="easyui-textbox" name="description"  value="${record.description}" data-options="multiline:true" style="width:100%;height:64px">
    </div>
    <button class="easyui-linkbutton" onclick="recmdsEditSubmitForm()" style="width:100%;height:32px">更新</button>
  </form>
  </div>
</div>
<script>
function recmdsEditSubmitForm(){
  $('#recmds-edit-form').form({
    success: function(data) {
    	CMS.editSubmitHandler(data, recmdsEL);
    }
  });
}
</script>
</body>