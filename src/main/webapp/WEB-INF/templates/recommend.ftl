<@override name="body">
<div class="toolbar">
<div class="container">
<form id="form" action="javascript:void(0);">
  <dl class="form-group">
    <dt><label for="url">请输入你要推荐的资源、文档、技术或开源项目</label></dt>
    <dd>
      <div class="left">
        <input class="form-control" id="url"name="url" placeholder="http(s)://" autocomplete="off">
      </div>
      <div class="form-actions left">
	    <button id="submit" class="btn">推荐</button>
	  </div>
    </dd>
  </dl>
</form>
</div>
</div>
</@override>

<@override name="footer">
<script>
$(function() {
	$("#submit").click(function() {
		$.post("/recommend", $("#form").serialize(), function(data) {
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
</@override>

<@extends name="module/base.ftl"/>