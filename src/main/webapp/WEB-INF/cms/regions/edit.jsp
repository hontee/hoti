<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="easyui-layout" data-options="fit:true">
  <div data-options="region:'center', border:false" class="cms-wbox">
  <form id="regions-edit-form" action="/cms/regions/${record.id}/edit" method="post">
    <div class="cms-mb20">
      <div class="cms-mb5">编号:</div>
      <input class="easyui-textbox" name="id" value="${record.id}" data-options="required:true" style="width:100%; height:32px">
    </div>
    <div class="cms-mb20">
      <div class="cms-mb5">名称:</div>
      <input class="easyui-textbox" name="name" value="${record.name}" data-options="required:true" style="width:100%; height:32px">
    </div>
    <div class="cms-mb20">
      <div class="cms-mb5">标题:</div>
      <input class="easyui-textbox" name="title" value="${record.title}" data-options="required:true" style="width:100%;height:32px">
    </div>
    <div class="cms-mb20">
      <div class="cms-mb5">父节点:</div>
      <input class="easyui-textbox" name="parent" value="${record.parent}" data-options="required:true" style="width:100%;height:32px">
    </div>
    <div class="cms-mb20">
      <div class="cms-mb5">城市代码:</div>
      <input class="easyui-textbox" name="code" value="${record.code}" data-options="required:true" style="width:100%;height:32px">
    </div>
    <div class="cms-mb20">
      <div class="cms-mb5">级别:</div>
      <input class="easyui-textbox" name="level" value="${record.level}" data-options="required:true" style="width:100%;height:32px">
    </div>
    <div class="cms-mb20">
      <div class="cms-mb5">权重:</div>
      <input class="easyui-textbox" name="weight" value="${record.weight}" style="width:100%;height:32px">
    </div>
    <div class="cms-mb20">
      <div class="cms-mb5">状态:</div>
      <select class="easyui-combobox" name="state" data-options="panelHeight:'auto',editable: false" style="width:100%; height:32px">
        <option value="1" <c:if test="${record.state == 1}">selected</c:if>>启动</option>
        <option value="0" <c:if test="${record.state == 0}">selected</c:if>>禁用</option>
      </select>
    </div>
    <button class="easyui-linkbutton" onclick="regionsEditSubmitForm()" style="width:100%;height:32px">更新</button>
  </form>
  </div>
</div>
<script>
function regionsEditSubmitForm(){
  $('#regions-edit-form').form({
    success: function(data) {
    	CMS.editSubmitHandler(data, regionsEL);
    }
  });
}
</script>
</body>