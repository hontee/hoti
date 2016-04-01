<!DOCTYPE html>
<html class="no-js" lang="zh-CN">
<head>
<meta charset="utf-8">
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<link rel="shortcut icon" href="/favicon.ico">

<title>${title}</title>
<#if keywords??>
<meta name="keywords" content="${keywords!}">
</#if>
<#if description??>
<meta name="description" content="${description!}">
</#if>
<meta name="author" content="www.kuaiba.me" />
<link href="/primer/css/primer.css" rel="stylesheet">
<script src="//cdn.bootcss.com/jquery/2.1.4/jquery.min.js"></script>
</head>
<body>
<nav class="navbar">
  <div class="container">
    <a class="navbar-brand" href="/">快吧</a>
    <form class="left" action="/search" method="get">
      <input class="form-control navbar-search" name="q" value="${q!}" placeholder="搜索 站点与主题">
    </form>
    <ul class="navbar-ul right">
      <li><a class="link" href="/">站点</a></li>
      <li><a class="link" href="/groups">主题</a></li>
      <li><a class="link" href="/cates">类别</a></li>
      <#if user != "">
      <li><a class="btn" href="/">你好，${user}</a></li>
      <#else>
      <li><a class="btn btn-primary" href="/login">快速登录</a></li>
      </#if>
    </ul>
  </div>
</nav>