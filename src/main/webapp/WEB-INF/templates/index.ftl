<@extends name="module/header.ftl"/>

<div class="toolbar">
  <div class="container">
    <div class="btn-group left">
      <#if user??>
      <a class="btn btn-outline <#if f=='my'>selected</#if>" href="/?f=my">我的站点</a>
      </#if>
      <a class="btn btn-outline <#if f=='like'>selected</#if>" href="/?f=like">猜你喜欢</a>
      <a class="btn btn-outline <#if f=='new'>selected</#if>" href="/?f=new">最新</a>
      <a class="btn btn-outline <#if f=='hot'>selected</#if>" href="/?f=hot">最热</a>
      <a class="btn btn-outline <#if f=='pick'>selected</#if>" href="/?f=pick">精选</a>
    </div>

    <div class="right">
      <a class="btn btn-primary" href="/share">分享新站点</a>
    </div>
  </div>
</div>

<@extends name="module/bookmark-list.ftl"/>
<@extends name="module/pager.ftl"/>
</body>
</html>