<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="easyui-layout" data-options="fit:true">
  <div data-options="region:'center', border:false" class="cms-wbox">
  <form id="recmds-add-form" action="/cms/recommends/new" method="post">
    <div class="cms-mb20">
      <div class="cms-mb5">网址:</div>
      <input class="easyui-textbox" name="url" data-options="required:true" style="width:100%; height:32px">
    </div>
    <button class="easyui-linkbutton" onclick="recmdsAddSubmitForm()" style="width:100%;height:32px">提交</button>
  </form>
  </div>
</div>
<script>
function recmdsAddSubmitForm(){
  $('#recmds-add-form').form({
    success: function(data) {
    	CMS.addSubmitHandler(data, recmdsEL);
    }
  });
}
</script>
</body>