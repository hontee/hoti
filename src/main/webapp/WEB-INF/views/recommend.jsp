<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="snippets/_header.jsp" %>
<div class="container">
<form id="form" action="javascript:void(0)">
  <fieldset class="form-group">
    <label for="url">推荐网址</label>
    <input name="url" class="form-control" id="url" placeholder="网址">
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