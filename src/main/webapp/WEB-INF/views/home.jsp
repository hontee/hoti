<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="snippets/_header.jsp" %>
<div class="container" style="margin-top: 20px;">
	<div class="card-columns">
	  <div class="card">
		  <div class="card-header">
		    Featured
		  </div>
	      <div class="list-group list-group-flush">
		    <c:forEach items="${list}" var="w">
		    <a href="${w.url}" class="list-group-item" target="_blank">${w.title}</a>
		    </c:forEach>
		  </div>
	  </div>
	</div>
</div>
</body>
</html>
