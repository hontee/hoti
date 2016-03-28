<div class="container">
<div class="card-columns" style="margin-top: 20px;">
<#list records as r>
  <div class="card card-block">
  	<h4 class="card-title"><a href="/bookmarks/${r.id}/hit" target="_blank">${r.title}</a></h4>
    <blockquote class="card-blockquote">
      <footer>
        <small class="text-muted">${r.star} 关注</small> · 
        <small class="text-muted">${r.hit} 点击</small> · 
        <small class="text-muted">已关注</small>
      </footer>
    </blockquote>
  </div>
</#list>
</div>
</div>