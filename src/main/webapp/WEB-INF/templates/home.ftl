<@override name="body">
<div class="toolbar">
  <div class="container">
    <div class="btn-group left">
      <#if user??>
      <a class="btn btn-outline <#if f=='my'>selected</#if>" href="/">我的产品</a>
      </#if>
      <a class="btn btn-outline <#if f=='pick'>selected</#if>" href="/pick">精选</a>
      <a class="btn btn-outline <#if f=='newest'>selected</#if>" href="/newest">最新</a>
      <a class="btn btn-outline <#if f=='popular'>selected</#if>" href="/popular">最热</a>
    </div>

    <div class="right">
      <a class="btn btn-primary" href="/recommend">推荐新产品</a>
    </div>
  </div>
</div>

<@extends name="products.ftl"/>
<@extends name="pager.ftl"/>
</@override>

<@extends name="module/base.ftl"/>