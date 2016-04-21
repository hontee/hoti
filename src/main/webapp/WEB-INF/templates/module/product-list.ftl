<div class="container">
  <div class="card-columns">
    <#list products as p>
    <div class="card">
      <a href="/products/${p.id}/hit" target="_blank">
        <blockquote class="card-block">
          <h3 class="card-title">${p.title!}</h3>
          <p class="card-text">${p.description!}</p>
          <footer>
            <small class="text-muted"><span data-follow="star">${p.star}</span>人关注</small> ·
            <small class="text-muted">${p.hit}次点击</small>
          </footer>
        </blockquote>
      </a>
      <#if p.follow==1>
      <footer class="card-footer" data-follow="follow" data-href="/products/${p.id}/unfollow">取消关注</footer>
      <#else>
      <footer class="card-footer" data-follow="follow" data-href="/products/${p.id}/follow">关注</footer>
      </#if>
    </div>
    </#list>
  </div>
</div>