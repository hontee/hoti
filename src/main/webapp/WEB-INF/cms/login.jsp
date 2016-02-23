<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="_header.jsp" %>
<title>登录 - 快吧后台管理系统</title>
</head>
<body>
<div id="cms-login-window" class="easyui-layout">
  <div data-options="region:'center', border:false" class="cms-wbox">
    <form id="cms-login-form" action="/cms/login" method="post">
    <div class="cms-mb20">
      <div class="cms-mb5">用户名:</div>
      <input class="easyui-textbox" name="username" data-options="required:true" style="width:100%;height:32px">
    </div>
    <div class="cms-mb20">
      <div class="cms-mb5">密码:</div>
      <input class="easyui-textbox" type="password" name="password" data-options="required:true" style="width:100%;height:32px">
    </div>
    <button class="easyui-linkbutton" onclick="cmsLogin()" style="width:100%;height:32px; margin-top: 20px;">登  录</button>
    </form>
  </div>
</div>
<script>
$('#cms-login-window').window({
    width:400,
    height:280,
    title: "请登录",
    modal: true,
    collapsible: false,
    minimizable: false,
    maximizable: false,
    closable: false
});
function cmsLogin(){
  $('#cms-login-form').form({
    success: function(data) {
    	console.log(data);
    }
  });
}
</script>
</body>
</html>