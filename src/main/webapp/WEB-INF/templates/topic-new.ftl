<@override name="body">
<div class="toolbar">
<div class="container">
<form id="form" action="javascript:void(0);">
  <dl class="form-group">
    <dt><label>主题名称</label></dt>
    <dd><input class="form-control" name="title"></dd>
  </dl>

  <dl class="form-group">
    <dt><label>选择类别</label></dt>
    <dd>
      <select class="form-select form-control" name="cid">
        <#list categories as c>
        <option value="${c.id}">${c.title}</option>
        </#list>
      </select>
    </dd>
  </dl>
  <dl class="form-group">
    <dt><label>描述</label></dt>
    <dd>
      <textarea class="form-control" style="width: 440px;" name="description"></textarea>
    </dd>
  </dl>
  <dl class="form-group">
    <dd>
      <button class="btn btn-primary" id="submit">创建主题</button>
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