<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://shiro.apache.org/tags" prefix="shiro" %>
<%@ taglib uri="/WEB-INF/site.tld" prefix="site" %>
<site:header title="快吧主题 - 更好的管理站点"/>

<div class="toolbar">
  <div class="container">
    <div class="btn-group left">
      <shiro:authenticated>
      <a class="btn btn-outline <c:if test="${f=='my'}">selected</c:if>" href="/groups?f=my">我的主题</a>
      </shiro:authenticated>
      <a class="btn btn-outline <c:if test="${f=='like'}">selected</c:if>" href="/groups?f=like">猜你喜欢</a>
      <a class="btn btn-outline <c:if test="${f=='new'}">selected</c:if>" href="/groups?f=new">最新</a>
      <a class="btn btn-outline <c:if test="${f=='hot'}">selected</c:if>" href="/groups?f=hot">最热</a>
      <a class="btn btn-outline <c:if test="${f=='pick'}">selected</c:if>" href="/groups?f=pick">精选</a>
    </div>

    <!-- <div class="right">
      <a class="btn btn-primary" href="/group/new">创建主题</a>
    </div> -->
  </div>
</div>

<site:groups list="${records}"/>
<site:pager page="${page}"/>
</body>
</html>
