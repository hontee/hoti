<@override name="body">
<div class="blankslate">
  <div class="container">
  <h1>推荐</h1>
  <p>分享你喜欢的技术文章和产品</p>
  </div>
</div>

<div class="container">
<form id="form" action="javascript:void(0);" style="width: 440px; padding-bottom: 20px;">
  <dl class="form-group">
    <dt><label for="url">* URL (必填)</label></dt>
    <dd>
        <input class="form-control" name="url" placeholder="http(s)://" autocomplete="off">
    </dd>
  </dl>
  
  <dl class="form-group">
    <dt><label>标题</label></dt>
    <dd><input class="form-control" name="title" placeholder="标题"></dd>
  </dl>
  
  <dl class="form-group">
    <dt><label>标签</label></dt>
    <dd><input class="form-control" name="keywords" placeholder="标签"></dd>
  </dl>

  <dl class="form-group">
    <dt><label>选择主题</label></dt>
    <dd>
      <select class="form-select form-control" name="tid">
        <#list topics as t>
        <option value="${t.id}">${t.title}</option>
        </#list>
      </select>
    </dd>
  </dl>

  <dl class="form-group">
    <dt><label>描述</label></dt>
    <dd>
      <textarea class="form-control" name="description" placeholder="描述..."></textarea>
    </dd>
  </dl>
  
  <div class="form-actions">
    <button id="submit" class="btn btn-primary">确认推荐</button>
  </div>
</form>
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