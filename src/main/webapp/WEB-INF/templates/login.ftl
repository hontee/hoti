<@override name="resources">
<@super/>
<style>
body {background: #f5f5f5; padding-left:0;}
h2 {margin-bottom: 20px;}
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
<@override name="menu"></@override>

<@override name="body">
<div class="container">
<div class="columns">
<div class="one-third column centered">
<h2 class="text-center">请登录</h2>

<form class="form" id="form" action="javascript:void(0)">
  <dl class="form-group warn">
    <dt><label for="username">用户名</label></dt>
    <dd><input class="form-control" id="username" name="username" type="text" placeholder="用户名" required autofocus></dd>
  </dl>
  <br>
  <dl class="form-group warn" style="position: relative; top: -20px;">
    <dt><label for="password">密码</label></dt>
    <dd><input class="form-control" id="password" name="password" type="password" placeholder="密码" required></dd>
  </dl>
  <dl class="form-group warn">
    <button class="btn btn-primary btn-block" id="submit">登录</button>
  </dl>
</form>
</div>
</div>
</div>
</@override>

<@override name="footer">
<script>
$(function() {
	$("#submit").click(function() {
		$.post("/login", $("#form").serialize(), function(data) {
			var r = $.parseJSON(data);
			if (r.success) {
				console.log(r.result);
				window.location.href = "/";
			} else {
				alert(r.error.message);
			}
		});
	});
});
</script>
</@override>

<@extends name="module/header.ftl"/>