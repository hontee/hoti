<@override name="body">
<div class="blankslate">
  <div class="container">
  <h1>${u.title!}</h1>
  <p>${u.description!}</p>
  </div>
</div>

<div class="toolbar">
<div class="container">
<form id="form" action="javascript:void(0);">
  <dl class="form-group">
    <dt><label>昵称</label></dt>
    <dd><input class="form-control" name="title" value="${u.title!}" required autocomplete="off"></dd>
  </dl>
  
  <dl class="form-group">
    <dt><label>简介</label></dt>
    <dd>
      <textarea class="form-control" style="width: 440px;" name="description">${u.description!}</textarea>
    </dd>
  </dl>
  <dl class="form-group">
    <dd>
      <button class="btn btn-primary" id="submit">保存设置</button>
    </dd>
  </dl>
</form>
</div>
</div>
</@override>

<@override name="footer">
  <@super/>
  <script src="/assets/js/module/settings.js"></script>
</@override>

<@extends name="module/base.ftl"/>