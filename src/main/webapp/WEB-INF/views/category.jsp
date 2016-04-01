<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/WEB-INF/site.tld" prefix="site" %>
<site:header title="所有分类 - 快吧"/>

<div class="toolbar">
  <div class="container" style="width: 360px;">
  <c:forEach items="${records}" var="r">
    <nav class="menu">
      <div class="menu-heading">${r.title} <span class="counter">${r.count}</span></div>
      <c:forEach items="${r.cates}" var="c">
      <a class="menu-item" href="/cates/${c.id}">${c.title}
        <span class="counter">${c.count}</span>
      </a>
      </c:forEach>
    </nav>
  </c:forEach>
  </div>
</div>

</body>
</html>
