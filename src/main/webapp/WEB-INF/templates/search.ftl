<@override name="body">
<div class="toolbar">
  <div class="container">
    <div class="btn-group left">
      <a class="btn btn-outline <#if f=='site'>selected</#if>" href="/search?q=${q}">站点 / ${bcount}</a>
      <a class="btn btn-outline <#if f!='site'>selected</#if>" href="/search?f=group&q=${q}">主题 / ${gcount}</a>
    </div>
  </div>
</div>

<#if f == 'site'>

  <@extends name="bookmark-list.ftl"/>
  
<#else>

  <@extends name="group-list.ftl"/>
  
</#if>

<@extends name="pager.ftl"/>

</@override>

<@extends name="module/header.ftl"/>