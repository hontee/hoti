<@override name="body">
<div class="toolbar">
  <div class="container">
    <div class="btn-group left">
      <#if user??>
      <a class="btn btn-outline <#if f=='my'>selected</#if>" href="/?f=my">我的产品</a>
      </#if>
      <a class="btn btn-outline <#if f=='pick'>selected</#if>" href="/?f=pick">精选</a>
      <a class="btn btn-outline <#if f=='hot'>selected</#if>" href="/?f=hot">最热</a>
      <a class="btn btn-outline <#if f=='new'>selected</#if>" href="/?f=new">最新</a>
    </div>

    <div class="right">
      <a class="btn btn-primary" href="/share">分享新产品</a>
    </div>
  </div>
</div>

<@extends name="bookmark-list.ftl"/>

<@extends name="pager.ftl"/>

</@override>

<@extends name="module/header.ftl"/>