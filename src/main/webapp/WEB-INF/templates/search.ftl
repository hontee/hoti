<@extends name="module/header.ftl"/>

<div class="toolbar">
  <div class="container">
    <div class="btn-group left">
      <a class="btn btn-outline <#if f=='site'>selected</#if>" href="/search?q=${q}">站点 / ${bcount}</a>
      <a class="btn btn-outline <#if f!='site'>selected</#if>" href="/search?f=group&q=${q}">主题 / ${gcount}</a>
    </div>
  </div>
</div>

<#if f == 'site'>
  <@extends name="module/bookmark-list.ftl"/>
<#else>
  <@extends name="module/group-list.ftl"/>
</#if>
<@extends name="module/pager.ftl"/>
</body>
</html>