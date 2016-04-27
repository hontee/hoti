<@override name="body">
<div class="blankslate">
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
  <@extends name="products.ftl"/>
<#elseif f == 'topic'>
  <@extends name="topics.ftl"/>
</#if>
<@extends name="pager.ftl"/>
	
</@override>

<@extends name="module/base.ftl"/>