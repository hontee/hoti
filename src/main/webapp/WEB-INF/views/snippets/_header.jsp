<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<link href="//cdn.bootcss.com/bootstrap/4.0.0-alpha/css/bootstrap.min.css" rel="stylesheet">
<script src="//cdn.bootcss.com/jquery/2.1.4/jquery.min.js"></script>
<script src="//cdn.bootcss.com/bootstrap/4.0.0-alpha/js/bootstrap.min.js"></script>
</head>
<body>
<nav class="navbar navbar-light bg-faded">
<div class="container">
  <a class="navbar-brand" href="/">快吧</a>
  <ul class="nav navbar-nav pull-left">
    <li class="nav-item active">
      <form class="form-inline navbar-form" action="/search" method="get">
	    <input name="q" value="${q}" class="form-control" placeholder="搜索站点">
	  </form>
    </li>
  </ul>
  <ul class="nav navbar-nav pull-right">
  	<li class="nav-item">
      <a class="nav-link" href="/groups">所有分类</a>
    </li>
    <li class="nav-item">
      <a class="nav-link" href="/share">推荐</a>
    </li>
    <li class="nav-item">
      <a class="nav-link" href="/about">关于我们</a>
    </li>
    <li class="nav-item">
      <a class="btn btn-success-outline" href="/login">登录</a>
    </li>
  </ul>
</div>
</nav>