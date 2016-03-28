<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/site.tld" prefix="site" %>
<site:header title="${record.title }"/>
<site:category record="${record}"/>
<site:bookmarks list="${record.bookmarks}"/>
</body>
</html>
