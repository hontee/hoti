<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://shiro.apache.org/tags" prefix="shiro" %>
<%@ taglib uri="/WEB-INF/site.tld" prefix="site" %>
<site:header title="快吧 - 关注你喜欢的站点"/>

<div class="toolbar">
  <div class="container">
    <div class="btn-group left">
      <shiro:authenticated>
      <a class="btn btn-outline <c:if test="${f=='my'}">selected</c:if>" href="/?f=my">我的站点</a>
      </shiro:authenticated>
      <a class="btn btn-outline <c:if test="${f=='like'}">selected</c:if>" href="/?f=like">猜你喜欢</a>
      <a class="btn btn-outline <c:if test="${f=='new'}">selected</c:if>" href="/?f=new">最新</a>
      <a class="btn btn-outline <c:if test="${f=='hot'}">selected</c:if>" href="/?f=hot">最热</a>
      <a class="btn btn-outline <c:if test="${f=='pick'}">selected</c:if>" href="/?f=pick">精选</a>
    </div>

    <div class="right">
      <a class="btn btn-primary" href="/share">分享新站点</a>
    </div>
  </div>
</div>

<site:bookmarks list="${records}"/>
</body>
</html>
