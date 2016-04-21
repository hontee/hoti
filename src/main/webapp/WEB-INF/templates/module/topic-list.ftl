<div class="container">
  <div class="card-columns">
    <#list topics as t>
    <div class="card">
      <a href="/topics/${t.id}">
        <blockquote class="card-block">
          <h3 class="card-title">${t.title!}</h3>
          <p class="card-text">${t.description!}</p>
          <footer>
            <small class="text-muted"><span data-follow="star">${t.star}</span>人关注</small> ·
            <small class="text-muted">${t.product}个产品</small>
          </footer>
        </blockquote>
      </a>
      <#if t.follow==1>
      <footer class="card-footer" data-follow="follow" data-href="/topics/${t.id}/unfollow">取消关注</footer>
      <#else>
      <footer class="card-footer" data-follow="follow" data-href="/topics/${t.id}/follow">关注</footer>
      </#if>
    </div>
    </#list>
  </div>
</div>

