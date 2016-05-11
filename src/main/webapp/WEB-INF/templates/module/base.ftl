<!DOCTYPE html>
<html class="no-js" lang="zh-CN">
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
  <meta name="renderer" content="webkit">
  <meta property="ht:user" content="${user!}">
  <meta name="keywords" content="${keywords!"快椰,ikyer,开发者,开发者服务,资源,文档,开发工具,开源项目,SDK,API,数据平台"}">
  <meta name="description" content="${description!"快椰（ikyer.com）是一个基于学习和分享的开发者社区，我们以汇聚国内外最优质资源，打造最有影响力的开发者平台为目标，专注于为开发者服务。"}">

  <title>${title!"专注为开发者服务  · 快椰 ikyer.com"}</title>
  <link rel="apple-touch-icon" href="/apple-touch-icon.png">
  <link rel="shortcut icon" href="/favicon.ico">
  <@block name="resources">
  <link href="/assets/css/primer.css" rel="stylesheet">
  </@block>
</head>

<body>
<!--[if lt IE 8]>
<div class="toolbar">
  <div class="container">
    <div class="flash flash-warn">您使用的浏览器版本过低，为获得更好的体验，请升级您的浏览器！</div>
  </div>
</div>
<![endif]-->

<@block name="navbar">
<nav class="navbar">
  <div class="container">
    <a class="navbar-brand" href="/">快椰</a>
    <ul class="navbar-ul right">
      <li><a class="link" href="/">发现</a></li>
      <li><a class="link" href="/topics">主题</a></li>
      <li><a class="link" href="/about">关于</a></li>
      <#if user??>
      <li><a class="link" href="/${user!}/dashbord">${user!}</a></li>
      <#else>
      <li><a class="link" href="/login">登录</a></li>
      </#if>
    </ul>
    <form class="left" action="/search" method="get">
      <input class="form-control navbar-search" name="q" value="${q!}" placeholder="搜索 产品与主题" autocomplete="off">
    </form>
  </div>
</nav>
</@block>

<@block name="body"></@block>

<@block name="footer">
  <script src="/assets/js/vendor/modernizr-2.8.3.min.js"></script>
  <script src="/assets/js/vendor/jquery-2.1.4.min.js"></script>
  <script src="/assets/js/vendor/validator/jquery.validator.min.js?local=zh-CN"></script>
  <script src="/assets/js/module/base.js"></script>
</@block>
</body>
</html>