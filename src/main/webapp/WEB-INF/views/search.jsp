<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/WEB-INF/site.tld" prefix="site" %>
<site:header title="${page.q} - 搜索结果" q="${page.q}"/>

<div class="toolbar">
  <div class="container">
    <div class="btn-group left">
      <a class="btn btn-outline <c:if test="${f == 'site'}">selected</c:if>" href="/search?q=${page.q}">站点 / ${bcount}</a>
      <a class="btn btn-outline <c:if test="${f != 'site'}">selected</c:if>" href="/search?f=group&q=${page.q}">主题 / ${gcount}</a>
    </div>
  </div>
</div>

<c:if test="${f == 'site'}">
<site:bookmarks list="${records}"/>
</c:if>
<c:if test="${f != 'site'}">
<site:groups list="${records}"/>
</c:if>
<site:pager page="${page}"/>
</body>
</html>