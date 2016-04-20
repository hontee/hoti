<div class="container">
  <div class="card-columns">
    <#list bookmarks as r>
    <div class="card">
      <a href="/products/${r.id}/hit" target="_blank">
        <blockquote class="card-block">
          <h3 class="card-title">${r.title!}</h3>
          <p class="card-text">${r.description!}</p>
          <footer>
            <small class="text-muted"><span data-follow="star">${r.star}</span>人关注</small> ·
            <small class="text-muted">${r.hit}次点击</small>
          </footer>
        </blockquote>
      </a>
      <#if r.follow==1>
      <footer class="card-footer" data-follow="follow" data-href="/products/${r.id}/unfollow">取消关注</footer>
      <#else>
      <footer class="card-footer" data-follow="follow" data-href="/products/${r.id}/follow">关注</footer>
      </#if>
    </div>
    </#list>
  </div>
</div>