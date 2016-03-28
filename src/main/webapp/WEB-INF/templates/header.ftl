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

<link href="/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<link href="/assets/kuaiba/jquery.mmenu.all.css" rel="stylesheet" />
<script src="//cdn.bootcss.com/jquery/2.1.4/jquery.min.js"></script>
<script src="/bootstrap/js/bootstrap.min.js"></script>
<script src="/assets/kuaiba/layout.js"></script>
<script src="/assets/kuaiba/jquery.mmenu.all.min.js"></script>
</head>
<body>
<div class="navbar navbar-light bg-faded">
  <div class="container">
    <a class="navbar-brand" href="#menu">快吧</a>
    <ul class="nav navbar-nav pull-left">
      <li class="nav-item">
        <form class="form-inline navbar-form" action="/search" method="get">
          <input name="q"value="${q!}" class="form-control" placeholder="搜索" autocomplete="off">
        </form>
      </li>
    </ul>
    <ul class="nav navbar-nav pull-right">
      <li class="nav-item">
        <a class="btn btn-primary" href="/login">登录</a>
      </li>
      <#if roles == "admin">
        <li class="nav-item">
          <a class="btn btn-warning" href="/cms" target="_blank">后台管理</a>
        </li>
      </#if>
    </ul>
  </div>
</div>

<nav id="menu">
  <ul class="listview-icons">
    <li>
      <a href="/">首页</a>
    </li>
    <li>
      <a href="/groups">群组</a>
    </li>
    <li>
      <a href="/share">推荐站点</a>
    </li>
    <#list orgs as org>
      <li>
        <span>${org.title}</span>
        <ul>
          <#list org.cates as c>
            <li>
              <a href="/cates/${c.id}" title="${c.description}">${c.title}</a>
              <em class="Counter badge">${c.count}</em>
            </li>
          </#list>
        </ul>
      </li>
    </#list>
  </ul>
</nav>