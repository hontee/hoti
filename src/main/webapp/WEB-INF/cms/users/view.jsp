<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<ul class="cms-view-ul">
  <li><label>ID：</label>${record.id}</li>
  <li><label>用户名：</label>${record.name}</li>
  <li><label>介绍：</label>${record.description}</li>
  <li><label>用户类型：</label>${record.userType}
    <c:if test="${record.userType == 1}">普通用户</c:if>
    <c:if test="${record.userType == 2}">管理员</c:if>
  </li>
  <li><label>状态：</label>${record.state}
    <c:if test="${record.state == 3}">已删除</c:if>
    <c:if test="${record.state == 2}">锁定</c:if>
    <c:if test="${record.state == 1}">启用</c:if>
    <c:if test="${record.state == 0}">禁用</c:if>
  </li>
  <li><label>创建时间：</label><fmt:formatDate value="${record.created}" pattern="yyyy-MM-dd HH:mm:ss"/></li>
  <li><label>更新时间：</label><fmt:formatDate value="${record.lastModified}" pattern="yyyy-MM-dd HH:mm:ss"/></li>
</ul>
</body>