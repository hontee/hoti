<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/site.tld" prefix="site" %>
<site:header title="${record.title}"/>
<site:group record="${record}" f="${f}"/>
<site:bookmarks list="${record.bookmarks}"/>
<site:pager page="${page}"/>
</body>
</html>