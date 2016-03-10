<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="snippets/_header.jsp" %>
<div class="container">
	<div>${record.title}</div>
	<div>${record.description}</div>
	<div>关注：${record.stars} - 站点数：${record.count}</div>
</div>
</body>
</html>
