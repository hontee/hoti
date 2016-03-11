<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="snippets/_header.jsp" %>
<div class="container" style="margin-top: 20px;">
<form id="form" action="javascript:void(0)">
  <fieldset class="form-group">
    <input class="form-control" id="url" name="url" placeholder="http(s)://" autocomplete="off">
    <small class="text-muted">请输入你想推荐的站点</small>
  </fieldset>
  <button id="submit" class="btn btn-primary">提交</button>
</form>
</div>

<script>
$(function() {
	$("#submit").click(function() {
		$.post("/share", $("#form").serialize(), function(data) {
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