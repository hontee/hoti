<@override name="body">
<div class="toolbar">
  <div class="container">
    <div class="btn-group left">
      <#if user??>
      <a class="btn btn-outline <#if f=='my'>selected</#if>" href="/topics">我的主题</a>
      </#if>
      <a class="btn btn-outline <#if f=='pick'>selected</#if>" href="/topics/pick">精选</a>
      <a class="btn btn-outline <#if f=='newest'>selected</#if>" href="/topics/newest">最新</a>
      <a class="btn btn-outline <#if f=='popular'>selected</#if>" href="/topics/popular">最热</a>
    </div>

    <!-- <div class="right">
      <a class="btn btn-primary" href="/topic/new">创建主题</a>
    </div> -->
  </div>
</div>

<@extends name="topics.ftl"/>
<@extends name="pager.ftl"/>
</@override>

<@extends name="module/base.ftl"/>