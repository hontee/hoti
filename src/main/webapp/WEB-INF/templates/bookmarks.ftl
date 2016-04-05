<div class="container">
  <div class="card-columns" data-card="columns">
    <#list records as r>
    <div class="card">
      <a href="/bookmarks/${r.id}/hit" target="_blank">
        <blockquote class="card-block">
          <h3 class="card-title">${r.title!}</h3>
          <p class="card-text">${r.description!}</p>
          <footer>
            <small class="text-muted"><span data-card="star">${r.star}</span>人关注</small> ·
            <small class="text-muted">${r.hit}次点击</small>
          </footer>
        </blockquote>
      </a>
      <footer class="card-footer" data-card="follow" onclick="javascript:alert('follow');">已关注</footer>
    </div>
    </#list>
  </div>
</div>