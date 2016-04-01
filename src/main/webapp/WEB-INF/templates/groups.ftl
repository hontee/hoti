<div class="container">
  <div class="card-columns">
    <#list records as g>
    <div class="card">
      <a href="/groups/${g.id}">
        <blockquote class="card-block">
          <h3 class="card-title">${g.title!}</h3>
          <p class="card-text">${g.description!}</p>
          <footer>
            <small class="text-muted">${g.stars}人关注</small> ·
            <small class="text-muted">${g.count}个站点</small>
          </footer>
        </blockquote>
      </a>
      <footer class="card-footer" onclick="javascript:alert('follow');">添加关注</footer>
    </div>
    </#list>
  </div>
</div>

