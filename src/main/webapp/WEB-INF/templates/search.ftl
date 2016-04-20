<@override name="body">
<div class="toolbar">
  <div class="container">
    <div class="btn-group left">
      <a class="btn btn-outline <#if f=='site'>selected</#if>" href="/search?q=${q}">产品 / ${bcount}</a>
      <a class="btn btn-outline <#if f!='site'>selected</#if>" href="/search?f=group&q=${q}">主题 / ${gcount}</a>
    </div>
  </div>
</div>

<#if f == 'site'>

  <@extends name="product-list.ftl"/>
  
<#else>

  <@extends name="topic-list.ftl"/>
  
</#if>

<@extends name="pager.ftl"/>

</@override>

<@extends name="module/header.ftl"/>