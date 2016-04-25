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
  <li><label>所属类别：</label>${record.parent}</li>
  <li><label>类别数：</label>${record.category}</li>
  <li><label>产品数：</label>${record.product}</li>
  <li><label>主题数：</label>${record.topic}</li>
  <li><label>权重：</label>${record.weight}</li>
  <li><label>状态：</label>${record.state}
    <c:if test="${record.state == 1}">启用</c:if>
    <c:if test="${record.state == 0}">禁用</c:if>
  </li>
  <li><label>创建时间：</label><fmt:formatDate value="${record.created}" pattern="yyyy-MM-dd HH:mm:ss"/></li>
  <li><label>更新时间：</label><fmt:formatDate value="${record.lastModified}" pattern="yyyy-MM-dd HH:mm:ss"/></li>
  <li><label>创建人：</label>${record.creator}</li>
</ul>
</body>