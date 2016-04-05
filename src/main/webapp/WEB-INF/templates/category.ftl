<div class="blankslate text-center">
  <div class="container">
  <h1>${record.title!}</h1>
  <p>${record.description!}</p>
  </div>
</div>

<div class="toolbar">
  <div class="container">
    <div class="btn-group left">
      <a class="btn btn-outline <#if f=="site">selected</#if>" href="/cates/${record.id}">站点 / ${record.count}</a>
      <a class="btn btn-outline <#if f!="site">selected</#if>" href="/cates/${record.id}/?f=group">主题 / ${record.groupCount}</a>
    </div>
  </div>
</div>