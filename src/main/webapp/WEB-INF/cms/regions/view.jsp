<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<ul class="cms-view-ul">
  <li><label>ID：</label>${record.id}</li>
  <li><label>名称：</label>${record.name}</li>
  <li><label>标题：</label>${record.title}</li>
  <li><label>父ID：</label>${record.parent}</li>
  <li><label>代码：</label>${record.code}</li>
  <li><label>级别：</label>${record.level}</li>
  <li><label>权重：</label>${record.weight}</li>
  <li><label>状态：</label>${record.state}
    <c:if test="${record.state == 1}">启用</c:if>
    <c:if test="${record.state == 0}">禁用</c:if>
  </li>
</ul>
</body>