<@override name="body">
<div class="blankslate">
  <div class="container">
  <h1>${u.title!}</h1>
  <p>${u.description!}</p>
  </div>
</div>

<div class="toolbar">
  <div class="container">
    <div class="btn-group left">
      <a class="btn btn-outline <#if f=='product'>selected</#if>" href="/${u.name}/dashbord">产品</a>
      <a class="btn btn-outline <#if f=='topic'>selected</#if>" href="/${u.name}/topics">主题</a>
    </div>
    
    <#if (user?? & u.name == user)>
    <div class="right">
      <a class="btn btn-primary" href="/${u.name}/settings">设置</a>
    </div>
    </#if>
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