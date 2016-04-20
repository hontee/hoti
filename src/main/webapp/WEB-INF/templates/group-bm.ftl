<@override name="body">
<div class="blankslate text-center">
  <div class="container">
  <h1>${group.title!}</h1>
  <p>${group.description!}</p>
  <footer>
    <small><span id="star">${group.star}</span>人关注</small> · 
    <small>${group.count}个产品</small>
  </footer>
  </div>
</div>

<div class="toolbar">
  <div class="container">
    <div class="btn-group left">
      <a class="btn btn-outline <#if f=="hot">selected</#if>" href="/groups/${group.id}?f=hot">最热</a>
      <a class="btn btn-outline <#if f=="new">selected</#if>" href="/groups/${group.id}?f=new">最新</a>
      <a class="btn btn-outline <#if f=="pick">selected</#if>" href="/groups/${group.id}?f=pick">精选</a>
    </div>

    <div class="right">
      <!--a class="btn" href="/share?group=${group.id}">添加新产品</a>
      <a class="btn" href="/groups/${group.id}/manager">管理</a-->
      <#if group.follow==1>
      <button class="btn btn-primary" id="follow" data-href="/groups/${group.id}/unfollow">取消关注</button>
      <#else>
      <button class="btn btn-primary" id="follow" data-href="/groups/${group.id}/follow">关注</button>
      </#if>
    </div>
  </div>
</div>

<@extends name="bookmark-list.ftl"/>

<@extends name="pager.ftl"/>

</@override>

<@extends name="module/header.ftl"/>