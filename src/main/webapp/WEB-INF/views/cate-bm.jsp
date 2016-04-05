<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/WEB-INF/site.tld" prefix="site" %>
<site:header title="${record.title }"/>
<site:category record="${record}" f="${f}"/>

<c:if test="${f == 'site'}">
<site:bookmarks list="${records}"/>
</c:if>
<c:if test="${f != 'site'}">
<site:groups list="${records}"/>
</c:if>
<site:pager page="${page}"/>
</body>
</html>