<!DOCTYPE html>
<html lang="zh-CN" class="no-js">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="renderer" content="webkit">
<meta property="ht:user" content="${user!}">
<meta name="keywords" content="${keywords!"红提,HOTI,开发者,开发者服务,资源,文档,开发工具,开源项目,SDK,API,数据平台"}">
<meta name="description" content="${description!"红提 HOTI.io 专注为开发者服务！汇聚国内外最优质资源，打造最有影响力的开发者服务中心。"}">

<title>${title!"专注为开发者服务  · 红提 Hoti.io"}</title>
<link rel="shortcut icon" href="/favicon.ico">
<@block name="resources">
<link href="/assets/css/primer.css" rel="stylesheet">
<script src="/assets/js/jquery.min.js"></script>
<script src="/assets/js/jquery.mmenu.all.min.js"></script>
<script src="/assets/js/layout.js"></script>
</@block>
</head>

<body>
<@block name="navbar">
<nav class="navbar">
    <ul class="navbar-ul">
      <li><a class="link" href="/">发现</a></li>
      <li><a class="link" href="/topics">主题</a></li>
      <li><a class="link" href="/about">关于</a></li>
      <#if user??>
      <li class="right"><a class="link" href="/${user!}/dashbord">${user!}</a></li>
      <#else>
      <li class="right"><a class="link" href="/login">登录</a></li>
      </#if>
    </ul>
    <form class="left" action="/search" method="get">
      <input class="form-control navbar-search" name="q" value="${q!}" placeholder="搜索 产品与主题" autocomplete="off">
    </form>
</nav>
</@block>

<@block name="mmenu">
<nav id="mmenu">
  <ul class="listview-icons">
    <#list categories as a>
    <#if (a.parent == 0) && (a.category > 0)>
    <li>
      <span>${a.title}</span>
      <ul>
        <li>
          <a href="/categories/${a.id}">${a.title}</a>
          <em class="mm-counter mm-badge">${a.product}</em>
        </li>
        <#list categories as b>
        <#if (b.parent == a.id)  && (b.category > 0)>
        <li>
          <span>${b.title}</span>
          <ul>
            <li>
              <a href="/categories/${b.id}">${b.title}</a>
              <em class="mm-counter mm-badge">${b.product}</em>
            </li>
            <#list categories as c>
            <#if (c.parent == b.id)>
            <li>
              <a href="/categories/${c.id}">${c.title}</a>
              <em class="mm-counter mm-badge">${c.product}</em>
            </li>
            </#if>
            </#list>
          </ul>
        </li>
        <#elseif (b.parent == a.id)>
	    <li>
	      <a href="/categories/${b.id}">${b.title}</a>
	      <em class="mm-counter mm-badge">${b.product}</em>
	    </li>
        </#if>
        </#list>
      </ul>
    </li>
    <#elseif (a.parent == 0)>
    <li>
      <a href="/categories/${a.id}">${a.title}</a>
      <em class="mm-counter mm-badge">${a.product}</em>
    </li>
    </#if>
    </#list>
  </ul>
</nav>
</@block>

<@block name="body"></@block>

<@block name="footer"></@block>

</body>
</html>