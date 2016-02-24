<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="snippets/_header.jsp" %>
<div class="container">
<div class="card">
  <div class="card-block">
    <h4 class="card-title">Card title</h4>
    <p class="card-text">Some quick example text to build on the card title and make up the bulk of the card's content.</p>
  </div>
  <div class="list-group list-group-flush">
    <c:forEach items="${list}" var="w">
    <a href="${w.url}" class="list-group-item" target="_blank">${w.title}</a>
    </c:forEach>
  </div>
</div>
</div>
</body>
</html>
