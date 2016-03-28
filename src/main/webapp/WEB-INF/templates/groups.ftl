<div class="container">
<div class="card-columns column-4" style="margin-top: 20px;">
  <#list records as g>
  <div class="card card-block">
    <h4 class="card-title"><a href="/groups/${g.id}">${g.title}</a></h4>
    <blockquote class="card-blockquote">
      <p>${g.description}</p>
      <footer>
      	<small class="text-muted">${g.count} 关注</small> · 
        <small class="text-muted">${g.count} 站点</small> · 
        <small class="text-muted">已关注</small>
      </footer>
    </blockquote>
  </div>
  </#list>
</div>
</div>