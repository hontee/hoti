<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="easyui-layout" data-options="fit:true">
  <div data-options="region:'center', border:false" class="cms-wbox">
  <form id="recmds-refuse-form" action="/cms/recommends/${record.id}/refuse" method="post">
  	<div class="cms-mb20">
      <div class="cms-mb5">网址:</div>
      <input class="easyui-textbox" value="${record.url}" data-options="readonly:true" style="width:100%; height:32px">
    </div>
    <div class="cms-mb20">
      <div class="cms-mb5">标题:</div>
      <input class="easyui-textbox" value="${record.title}" data-options="readonly:true" style="width:100%;height:32px">
    </div>
    <div class="cms-mb20">
      <div class="cms-mb5">拒绝原因:</div>
      <input class="easyui-textbox" name="remark"  value="${record.remark}" data-options="required:true, multiline:true" style="width:100%;height:64px">
    </div>
    <button class="easyui-linkbutton" onclick="recmdsRefuseSubmitForm()" style="width:100%;height:32px">审核拒绝</button>
  </form>
  </div>
</div>
<script>
function recmdsRefuseSubmitForm(){
  $('#recmds-refuse-form').form({
    success: function(data) {
    	CMS.editSubmitHandler(data, recmdsEL);
    }
  });
}
</script>
</body>