<!DOCTYPE html>
<html class="no-js" lang="zh-CN">
<head>
<meta charset="utf-8">
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<link rel="shortcut icon" href="/favicon.ico">

<title>${title!"快吧"}</title>
<meta name="keywords" content="${keywords!"快吧"}">
<meta name="description" content="${description!"关注你喜欢的站点"}">

<@block name="resources">
<link href="/assets/css/primer.css" rel="stylesheet">
<script src="/assets/js/jquery.min.js"></script>
<script src="/assets/js/jquery.mmenu.all.min.js"></script>
<script src="/assets/js/layout.js"></script>
</@block>
</head>

<body>
<@block name="navbar">
<nav class="navbar">
  <div class="container-self">
    <a class="navbar-brand" href="/">快吧</a>
    <form class="left" action="/search" method="get">
      <input class="form-control navbar-search" name="q" value="${q!}" placeholder="搜索 站点与主题">
    </form>
    <ul class="navbar-ul right">
      <li><a class="link" href="/">站点</a></li>
      <li><a class="link" href="/groups">主题</a></li>
      <#if user??>
      <li><a class="btn" href="/">你好，${user!}</a></li>
      <#else>
      <li><a class="btn btn-primary" href="/login">快速登录</a></li>
      </#if>
    </ul>
  </div>
</nav>
</@block>

<@block name="menu">
<nav id="menu">
  <ul class="listview-icons">
    <#list domains as r>
    <li>
      <span>${r.title!}</span>
      <ul>
        <#list r.cates as c>
        <li>
          <a href="/cates/${c.id}">${c.title!}</a>
          <em class="mm-counter mm-badge">${c.count}</em>
        </li>
        </#list>
      </ul>
    </li>
    </#list>
  </ul>
</nav>
</@block>

<@block name="body"></@block>

<@block name="footer"></@block>

</body>
</html>