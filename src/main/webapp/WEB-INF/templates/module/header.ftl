<!DOCTYPE html>
<html class="no-js" lang="zh-CN">
<head>
<meta charset="utf-8">
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<link rel="shortcut icon" href="/favicon.ico">

<title>${title!"红提 | 为开发者而生"}</title>
<meta name="keywords" content="${keywords!"红提,HOTI,开发者,开发者服务,资源,文档,开发工具,开源项目,SDK,API,数据平台"}">
<meta name="description" content="${description!"红提 HOTI.io 为开发者而生！汇聚国内外最优质资源，打造最有影响力的开发者服务中心。"}">

<@block name="resources">
<link href="/primer/css/primer.css" rel="stylesheet">
<script src="/primer/js/jquery.min.js"></script>
<script src="/primer/js/jquery.mmenu.all.min.js"></script>
<script src="/primer/js/layout.js"></script>
</@block>
</head>

<body>
<@block name="navbar">
<nav class="navbar">
  <div class="container">
    <a class="navbar-brand" href="/">红提</a>
    <form class="left" action="/search" method="get">
      <input class="form-control navbar-search" name="q" value="${q!}" placeholder="搜索 产品与主题" autocomplete="off">
    </form>
    <ul class="navbar-ul right">
      <li><a class="link" href="/">发现</a></li>
      <li><a class="link" href="/groups">主题</a></li>
      <li><a class="link" href="/category">分类</a></li>
      <#if user??>
      <li><a class="btn" href="/">你好，${user!}</a></li>
      <#else>
      <li><a class="btn btn-primary" href="/login">快速登录</a></li>
      </#if>
    </ul>
  </div>
</nav>
</@block>

<@block name="body"></@block>

<@block name="footer"></@block>

</body>
</html>