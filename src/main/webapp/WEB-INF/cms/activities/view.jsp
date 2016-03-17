<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<ul class="cms-view-ul">
  <li><label>ID：</label>${record.id}</li>
  <li><label>操作：</label>${record.name}</li>
  <li><label>描述：</label>${record.description}</li>
  <li><label>对象：</label>${record.tbl}</li>
  <li><label>IP地址：</label>${record.ip}</li>
  <li><label>操作时间：</label><fmt:formatDate value="${record.created}" pattern="yyyy-MM-dd HH:mm:ss"/></li>
  <li><label>操作人：</label>${record.creator}</li>
  <li><label>类型：</label>${record.userType}</li>
</ul>
</body>