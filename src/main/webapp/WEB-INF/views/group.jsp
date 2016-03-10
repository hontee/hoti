<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="snippets/_header.jsp" %>
<div class="container">
	<div class="list-group">
	  <c:forEach items="${groups}" var="g">
	  <a href="/groups/${g.id}" title="${g.description}" class="list-group-item">${g.title}
	  <span class="label label-default label-pill pull-right">${g.count}</span>
	  </a>
	  </c:forEach>
	</div>
</div>
</body>
</html>
