<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="easyui-layout" data-options="fit:true">
  <div data-options="region:'center', border:false" class="cms-wbox">
  <form id="websites-edit-form" action="/cms/websites/${record.id}/edit" method="post">
    <div class="cms-mb20">
      <div class="cms-mb5">网址:</div>
      <input class="easyui-textbox" name="url" value="${record.url}" data-options="required:true" style="width:100%; height:32px">
    </div>
    <div class="cms-mb20">
      <div class="cms-mb5">标题:</div>
      <input class="easyui-textbox" name="title" value="${record.title}" data-options="required:true" style="width:100%;height:32px">
    </div>
    <div class="cms-mb20">
      <div class="cms-mb5">来源:</div>
      <input class="easyui-textbox" name="reffer" value="${record.reffer}" style="width:100%;height:32px">
    </div>
    <div class="cms-mb20">
      <div class="cms-mb5">所属分类:</div>
      <input id="cc" class="easyui-combobox" name="category"
    	data-options="panelHeight:'auto',editable: false, value: '${record.category}', valueField:'id',textField:'title',url:'/cms/cates/datalist'" 
    	style="width:100%; height:32px">
    </div>
    <div class="cms-mb20">
      <div class="cms-mb5">状态:</div>
      <select class="easyui-combobox" name="state" data-options="panelHeight:'auto',editable: false" style="width:100%; height:32px">
        <option value="1" <c:if test="${record.state == 1}">selected</c:if>>启动</option>
        <option value="0" <c:if test="${record.state == 0}">selected</c:if>>禁用</option>
      </select>
    </div>
    <div class="cms-mb20">
      <div class="cms-mb5">描述:</div>
      <input class="easyui-textbox" name="description"  value="${record.description}" data-options="multiline:true" style="width:100%;height:64px">
    </div>
    <button class="easyui-linkbutton" onclick="websitesEditSubmitForm()" style="width:100%;height:32px">更新</button>
  </form>
  </div>
</div>
<script>
function websitesEditSubmitForm(){
  $('#websites-edit-form').form({
    success: function(data) {
    	CMS.editSubmitHandler(data, websitesEL);
    }
  });
}
</script>
</body>