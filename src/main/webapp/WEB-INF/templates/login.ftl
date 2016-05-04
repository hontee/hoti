<@override name="resources">
<@super/>
<style>
body {background: #f5f5f5; padding-left:20px;}
h1 {margin-bottom: 40px;}
.container {
    max-width: 360px;
    margin-left: auto;
    margin-right: auto;
    margin-top: 80px;
}
.form {
    border: 1px solid #ddd;
    border-radius: 3px;
    padding: 5px 20px;
    background: #fff;
    margin-top: 20px;
}
</style>
</@override>

<@override name="navbar"></@override>
<@override name="mmenu"></@override>

<@override name="body">
<div class="container">
<h1 class="text-center">请登录</h1>
<form class="form" id="loginForm" action="javascript:void(0)">
  <input type="hidden" name="redirect" value="${record}">
  <dl class="form-group warn">
    <dt><label for="username">用户名</label></dt>
    <dd><input class="form-control" id="username" name="username" type="text" placeholder="用户名" required autofocus autocomplete="off"></dd>
  </dl>
  <br>
  <dl class="form-group warn" style="position: relative; top: -20px;">
    <dt><label for="password">密码</label></dt>
    <dd><input class="form-control" id="password" name="password" type="password" placeholder="密码" required></dd>
  </dl>
  <dl class="form-group warn">
    <button class="btn btn-primary btn-block" id="login">登录</button>
  </dl>
</form>
</div>
</@override>

<@override name="footer">
<script>
$(function() {
  $("#login").click(function() {
    $.post("/login", $("#loginForm").serialize(), function(data) {
      var r = $.parseJSON(data);
      if (r.success) {
        KYER.redirect(r.result);
      } else {
        alert(r.error.message);
      }
    });
  });
});
</script>
</@override>

<@extends name="module/base.ftl"/>