<@override name="body">
<div class="blankslate text-center">
  <div class="container">
  <h1>${category.title!}</h1>
  <p>${category.description!}</p>
  </div>
</div>

<div class="toolbar">
  <div class="container">
    <div class="btn-group left">
      <a class="btn btn-outline <#if f=='product'>selected</#if>" href="/categories/${category.id}">产品 / ${category.product}</a>
      <a class="btn btn-outline <#if f=='topic'>selected</#if>" href="/categories/${category.id}/topic">主题 / ${category.topic}</a>
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