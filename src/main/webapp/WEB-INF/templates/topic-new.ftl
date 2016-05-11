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
    <button class="btn btn-primary">创建主题</button>
  </div>
</form>
</div>
</@override>

<@override name="footer">
  <@super/>
  <script src="/assets/js/module/topic-new.js"></script>
</@override>

<@extends name="module/base.ftl"/>