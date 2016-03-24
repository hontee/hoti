<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<ul class="cms-view-ul">
  <li><label>ID：</label>${record.id}</li>
  <li><label>名称：</label>${record.name}</li>
  <li><label>标题：</label>${record.title}</li>
  <li><label>描述：</label>${record.description}</li>
  <li><label>类型：</label>${record.gtype}</li>
  <li><label>头像：</label>${record.avatar}</li>
  <li><label>封面：</label>${record.cover}</li>
  <li><label>关注：</label>${record.stars}</li>
  <li><label>计数：</label>${record.count}</li>
  <li><label>状态：</label>${record.state}
    <c:if test="${record.state == 1}">启用</c:if>
    <c:if test="${record.state == 0}">禁用</c:if>
  </li>
  <li><label>创建时间：</label><fmt:formatDate value="${record.created}" pattern="yyyy-MM-dd HH:mm:ss"/></li>
  <li><label>更新时间：</label><fmt:formatDate value="${record.lastModified}" pattern="yyyy-MM-dd HH:mm:ss"/></li>
  <li><label>创建人：</label>${record.creator}</li>
  <li><label>媒体类型：</label>${record.mt.title}</li>
  <li><label>所属分类：</label>${record.cateTitle}</li>
</ul>
</body>