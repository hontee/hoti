<@override name="body">
<div class="blankslate">
  <div class="container">
  <h1>创建主题</h1>
  <p>更好地内容组织者</p>
  </div>
</div>

<div class="container">
<form id="form" action="javascript:void(0);" style="width: 440px; padding-bottom: 20px;">
  <dl class="form-group">
    <dt><label>主题名称</label></dt>
    <dd><input class="form-control" name="title" placeholder="主题名称"></dd>
  </dl>
  <dl class="form-group">
    <dt><label>描述</label></dt>
    <dd>
      <textarea class="form-control" name="description" placeholder="描述..."></textarea>
    </dd>
  </dl>
  <div class="form-actions">
    <button class="btn btn-primary" id="submit">创建主题</button>
  </div>
</form>
</div>
</@override>

<@override name="footer">
<script>
$(function() {
	$("#submit").click(function() {
		$.post("/topics/new", $("#form").serialize(), function(data) {
			var r = $.parseJSON(data);
			if (r.success) {
			    alert('已提交，请耐心等待审核！');
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