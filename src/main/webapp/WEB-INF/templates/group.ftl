<@extends name="module/header.ftl"/>

<div class="toolbar">
  <div class="container">
    <div class="btn-group left">
      <#if user??>
      <a class="btn btn-outline <#if f=='my'>selected</#if>" href="/groups?f=my">我的主题</a>
      </#if>
      <a class="btn btn-outline <#if f=='like'>selected</#if>" href="/groups?f=like">猜你喜欢</a>
      <a class="btn btn-outline <#if f=='new'>selected</#if>" href="/groups?f=new">最新</a>
      <a class="btn btn-outline <#if f=='hot'>selected</#if>" href="/groups?f=hot">最热</a>
      <a class="btn btn-outline <#if f=='pick'>selected</#if>" href="/groups?f=pick">精选</a>
    </div>

    <!-- <div class="right">
      <a class="btn btn-primary" href="/group/new">创建主题</a>
    </div> -->
  </div>
</div>

<@extends name="module/group-list.ftl"/>
<@extends name="module/pager.ftl"/>
</body>
</html>
