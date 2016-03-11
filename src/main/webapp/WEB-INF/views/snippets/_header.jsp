<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<link href="/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<script src="//cdn.bootcss.com/jquery/2.1.4/jquery.min.js"></script>
<script src="/bootstrap/js/bootstrap.min.js"></script>
</head>
<body>
<nav class="navbar navbar-light bg-faded">
<div class="container">
  <a class="navbar-brand" href="/">快吧</a>
  <ul class="nav navbar-nav pull-left">
    <li class="nav-item">
      <form class="form-inline navbar-form" action="/search" method="get">
	    <input name="q" value="${q}" class="form-control" placeholder="搜索" autocomplete="off">
	  </form>
    </li>
    <li class="nav-item">
      <a class="nav-link" href="/cates">类别</a>
    </li>
    <li class="nav-item">
      <a class="nav-link" href="/groups">群组</a>
    </li>
    <li class="nav-item">
      <a class="nav-link" href="/share">推荐</a>
    </li>
  </ul>
  <ul class="nav navbar-nav pull-right">
    <li class="nav-item">
      <a class="btn btn-primary" href="/login">登录</a>
    </li>
    <c:if test="${admin}">
    <li class="nav-item">
      <a class="btn btn-warning" href="/cms" target="_blank">后台管理</a>
    </li>
    </c:if>
  </ul>
</div>
</nav>