<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/WEB-INF/web.tld" prefix="k" %>
<k:header title="${record.title }"/>
<div class="jumbotron jumbotron-fluid">
  <div class="container">
    <h1 class="display-3">${record.title }</h1>
    <p class="lead">${record.description }</p>
  </div>
</div>
<div class="container">
	<div class="card-columns">
	<c:forEach items="${record.bookmarks}" var="r">
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
