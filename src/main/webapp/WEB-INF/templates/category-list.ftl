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
      <a class="btn btn-outline <#if f=='site'>selected</#if>" href="/category/${category.id}/?f=site">产品 / ${category.count}</a>
      <a class="btn btn-outline <#if f!='site'>selected</#if>" href="/category/${category.id}/?f=group">主题 / ${category.groupCount}</a>
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