<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="easyui-layout" data-options="fit:true">
  <div data-options="region:'center', border:false" class="cms-wbox">
  <form id="products-add-form" action="/cms/products/new" method="post">
    <div class="cms-mb20">
      <div class="cms-mb5">网址:</div>
      <input class="easyui-textbox" name="url" data-options="required:true" style="width:100%; height:32px">
    </div>
    <div class="cms-mb20">
      <div class="cms-mb5">标题:</div>
      <input class="easyui-textbox" name="title" data-options="required:true" style="width:100%;height:32px">
    </div>
    <div class="cms-mb20">
      <div class="cms-mb5">标签:</div>
      <input class="easyui-textbox" name="tags" data-options="required:true" style="width:100%;height:32px">
    </div>
    <div class="cms-mb20">
      <div class="cms-mb5">所属类别:</div>
      <input class="easyui-combobox" name="cid"
    	data-options="required:true, valueField:'id',textField:'title',url:'/cms/categories/datalist'" 
    	style="width:100%; height:32px">
    </div>
    <div class="cms-mb20">
      <div class="cms-mb5">状态:</div>
      <select class="easyui-combobox" data-options="panelHeight:'auto',editable: false" name="state" style="width:100%; height:32px">
        <option value="1">启动</option>
        <option value="0">禁用</option>
      </select>
    </div>
    <div class="cms-mb20">
      <div class="cms-mb5">描述:</div>
      <input class="easyui-textbox" name="description" data-options="multiline:true" style="width:100%;height:64px">
    </div>
    <button class="easyui-linkbutton" onclick="productsAddSubmitForm()" style="width:100%;height:32px">创建</button>
  </form>
  </div>
</div>
<script>
function productsAddSubmitForm(){
  $('#products-add-form').form({
    success: function(data) {
    	CMS.addSubmitHandler(data, productsEL);
    }
  });
}
</script>
</body>