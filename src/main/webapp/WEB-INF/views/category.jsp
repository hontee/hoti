<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/WEB-INF/web.tld" prefix="k" %>
<k:header title="所有分类"/>
<div class="container">
	<div class="card-columns" style="margin-top: 20px;">
	<c:forEach items="${orgs}" var="org">
	  <div class="card" style="border:none;">
		<div class="list-group">
		  <a class="list-group-item active">
		    ${org.title}
		  </a>
		  <c:forEach items="${org.cates}" var="c">
		  <a href="/cates/${c.id}" title="${c.description}" class="list-group-item">${c.title}
		  <span class="label label-default label-pill pull-right">${c.count}</span>
		  </a>
		  </c:forEach>
		</div>
	    </div>
	   </c:forEach>
	</div>
</div>
</body>
</html>