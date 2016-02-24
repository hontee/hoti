<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="snippets/_header.jsp" %>
<div class="container">
<form id="form" action="javascript:void(0)">
  <fieldset class="form-group">
    <label for="username">用户名</label>
    <input name="username" class="form-control" id="username" placeholder="用户名 / 邮箱">
  </fieldset>
  <fieldset class="form-group">
    <label for="password">密码</label>
    <input type="password" name="password" class="form-control" id="password" placeholder="密码">
  </fieldset>
  <button id="submit" class="btn btn-primary">登 录</button>
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