<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="easyui-layout" data-options="fit:true">
  <div data-options="region:'center', border:false" class="cms-wbox">
  <form id="regions-add-form" action="/cms/regions/new" method="post">
    <div class="cms-mb20">
      <div class="cms-mb5">编号:</div>
      <input class="easyui-textbox" name="id" data-options="required:true" style="width:100%; height:32px">
    </div>
    <div class="cms-mb20">
      <div class="cms-mb5">名称:</div>
      <input class="easyui-textbox" name="name" data-options="required:true" style="width:100%; height:32px">
    </div>
    <div class="cms-mb20">
      <div class="cms-mb5">标题:</div>
      <input class="easyui-textbox" name="title" data-options="required:true" style="width:100%;height:32px">
    </div>
        <div class="cms-mb20">
      <div class="cms-mb5">父节点:</div>
      <input class="easyui-textbox" name="parent" data-options="required:true" style="width:100%;height:32px">
    </div>
    <div class="cms-mb20">
      <div class="cms-mb5">城市代码:</div>
      <input class="easyui-textbox" name="code" data-options="required:true" style="width:100%;height:32px">
    </div>
    <div class="cms-mb20">
      <div class="cms-mb5">级别:</div>
      <input class="easyui-textbox" name="level" data-options="required:true" style="width:100%;height:32px">
    </div>
    <div class="cms-mb20">
      <div class="cms-mb5">权重:</div>
      <input class="easyui-textbox" name="weight" style="width:100%;height:32px">
    </div>
    <div class="cms-mb20">
      <div class="cms-mb5">状态:</div>
      <select class="easyui-combobox" data-options="panelHeight:'auto',editable: false" name="state" style="width:100%; height:32px">
        <option value="1">启动</option>
        <option value="0">禁用</option>
      </select>
    </div>
    <button class="easyui-linkbutton" onclick="regionsAddSubmitForm()" style="width:100%;height:32px">创建</button>
  </form>
  </div>
</div>
<script>
function regionsAddSubmitForm(){
  $('#regions-add-form').form({
    success: function(data) {
    	CMS.addSubmitHandler(data, regionsEL);
    }
  });
}
</script>
</body>