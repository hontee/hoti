<@override name="body">
<div class="blankslate">
  <div class="container">
  <h1>${topic.title!}</h1>
  <p>${topic.description!}</p>
  <footer>
    <small><span id="star">${topic.star}</span>人关注</small> · 
    <small>${topic.product}个产品</small>
  </footer>
  </div>
</div>

<div class="toolbar">
  <div class="container">
    <div class="btn-group left">
      <a class="btn btn-outline <#if f=="newest">selected</#if>" href="/topics/${topic.id}">最新</a>
      <a class="btn btn-outline <#if f=="popular">selected</#if>" href="/topics/${topic.id}/popular">最热</a>
      <a class="btn btn-outline <#if f=="pick">selected</#if>" href="/topics/${topic.id}/pick">精选</a>
    </div>

    <div class="form-actions">
	  <a href="/recommend?tid=${topic.id}" class="btn btn-primary">添加新产品</a>
	  <#if topic.follow==1>
      <button class="btn" id="follow" data-href="/topics/${topic.id}/unfollow">取消关注</button>
      <#else>
      <button class="btn" id="follow" data-href="/topics/${topic.id}/follow">关注</button>
      </#if>
	</div>
  </div>
</div>

<@extends name="products.ftl"/>
<@extends name="pager.ftl"/>
</@override>

<@extends name="module/base.ftl"/>