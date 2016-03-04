<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="snippets/_header.jsp" %>
<div class="container">
	<div class="card-columns" style="margin-top: 20px;">
	<c:forEach items="${cates}" var="c">
	  <div class="card" style="border:none;">
		<div class="list-group">
		  <a class="list-group-item active">
		    ${c.title}
		  </a>
		  <c:forEach items="${c.bookmarks}" var="w">
		  <a href="/${w.id}/hit" title="${w.description}" target="_blank" class="list-group-item">${w.title}
		  <span class="label label-default label-pill pull-right">${w.star} - ${w.extFollow}</span>
		  </a>
		  </c:forEach>
		</div>
	    </div>
	   </c:forEach>
	</div>
</div>
</body>
</html>
