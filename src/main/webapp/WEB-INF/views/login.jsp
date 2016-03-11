<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<link href="/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<script src="//cdn.bootcss.com/jquery/2.1.4/jquery.min.js"></script>
<script src="/bootstrap/js/bootstrap.min.js"></script>
<style>
body {
  padding-top: 80px;
  padding-bottom: 40px;
  background-color: #eee;
}
.form-signin {
  max-width: 360px;
  padding: 15px;
  margin: 0 auto;
}
.form-signin .form-signin-heading,
.form-signin .checkbox {
  margin-bottom: 10px;
}
.form-signin .checkbox {
  font-weight: normal;
}
.form-signin .form-control {
  position: relative;
  height: auto;
  -webkit-box-sizing: border-box;
          box-sizing: border-box;
  padding: 10px;
  font-size: 16px;
}
.form-signin .form-control:focus {
  z-index: 2;
}
.form-signin input[type="text"] {
  margin-bottom: -1px;
  border-bottom-right-radius: 0;
  border-bottom-left-radius: 0;
}
.form-signin input[type="password"] {
  margin-bottom: 10px;
  border-top-left-radius: 0;
  border-top-right-radius: 0;
}</style>
</head>
<body>
<div class="container">
<form class="form-signin" id="form" action="javascript:void(0)">
  <h2 class="form-signin-heading text-center">请登录</h2>
  <label for="username" class="sr-only">用户名</label>
  <input type="text" id="username" name="username" class="form-control" placeholder="用户名" required autofocus>
  <label for="password" class="sr-only">密码</label>
  <input type="password" id="password" name="password" class="form-control" placeholder="密码" required>
  <button class="btn btn-lg btn-primary btn-block" id="submit">登录</button>
</form>
</div>

<script>
$(function() {
	$("#submit").click(function() {
		$.post("/login", $("#form").serialize(), function(data) {
			var r = $.parseJSON(data);
			if (r.success) {
				window.location.href = "/";
			} else {
				alert(r.error.message);
			}
		});
	});
});
</script>
</body>
</html>