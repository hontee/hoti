<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/WEB-INF/web.tld" prefix="k" %>
<k:header title="快吧导航"/>
<div class="container">
	<div class="card-columns" style="margin-top: 20px;">
	<c:forEach items="${records}" var="r">
		  <div class="card card-block">
		  	<h4 class="card-title"><a href="/bookmarks/${r.id}/hit" title="${r.description}" target="_blank">${r.title}</a></h4>
		    <blockquote class="card-blockquote">
		      <p>${r.description}</p>
		      <footer>
		        <small class="text-muted">${r.star} 关注</small> · 
		        <small class="text-muted">${r.hit} 点击</small> · 
		        <small class="text-muted">已关注</small>
	          </footer>
		    </blockquote>
		  </div>
	</c:forEach>
	</div>
</div>
</body>
</html>
