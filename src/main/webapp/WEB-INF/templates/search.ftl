<@override name="body">
<div class="toolbar">
  <div class="container">
    <div class="btn-group left">
      <a class="btn btn-outline <#if f=='product'>selected</#if>" href="/search?q=${q}">产品 / ${product}</a>
      <a class="btn btn-outline <#if f=='topic'>selected</#if>" href="/search/topic?q=${q}">主题 / ${topic}</a>
    </div>
  </div>
</div>

<#if f == 'product'>
  <@extends name="product-list.ftl"/>
</#if>
<#if f == 'topic'>
  <@extends name="topic-list.ftl"/>
</#if>

<@extends name="pager.ftl"/>

</@override>

<@extends name="module/header.ftl"/>