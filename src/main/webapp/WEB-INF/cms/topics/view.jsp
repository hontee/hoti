<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<ul class="cms-view-ul">
  <li><label>ID：</label>${record.id}</li>
  <li><label>名称：</label>${record.name}</li>
  <li><label>标题：</label>${record.title}</li>
  <li><label>描述：</label>${record.description}</li>
  <li><label>头像：</label>${record.avatar}</li>
  <li><label>封面：</label>${record.cover}</li>
  <li><label>关注数：</label>${record.star}</li>
  <li><label>产品数：</label>${record.product}</li>
  <li><label>审核：</label>${record.audit}
    <c:if test="${record.audit == 1}">已认证</c:if>
    <c:if test="${record.audit == 0}">未认证</c:if>
  </li>
  <li><label>精选：</label>${record.pick}
    <c:if test="${record.pick == 1}">是</c:if>
    <c:if test="${record.pick == 0}">否</c:if>
  </li>
  <li><label>类型：</label>${record.type}
    <c:if test="${record.type == 1}">用户主题</c:if>
    <c:if test="${record.type == 2}">官方主题</c:if>
    <c:if test="${record.type == 3}">组织主题</c:if>
  </li>
  <li><label>状态：</label>${record.state}
    <c:if test="${record.state == 1}">启用</c:if>
    <c:if test="${record.state == 0}">禁用</c:if>
  </li>
  <li><label>创建时间：</label><fmt:formatDate value="${record.created}" pattern="yyyy-MM-dd HH:mm:ss"/></li>
  <li><label>更新时间：</label><fmt:formatDate value="${record.lastModified}" pattern="yyyy-MM-dd HH:mm:ss"/></li>
  <li><label>创建人：</label>${record.creator}</li>
</ul>
</body>