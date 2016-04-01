<div class="blankslate text-center">
  <div class="container">
  <h1>${record.title!}</h1>
  <p>${record.description!}</p>
  <footer>
    <small>${record.stars}人关注</small> · 
    <small>${record.count}个站点</small>
  </footer>
  </div>
</div>

<div class="toolbar">
  <div class="container">
    <div class="btn-group left">
      <a class="btn btn-outline <#if f=="hot">selected</#if>" href="/groups/${record.id}?f=hot">最热</a>
      <a class="btn btn-outline <#if f=="new">selected</#if>" href="/groups/${record.id}?f=new">最新</a>
      <a class="btn btn-outline <#if f=="pick">selected</#if>" href="/groups/${record.id}?f=pick">精选</a>
    </div>

    <div class="right">
      <a class="btn" href="/share?group=${record.id}">添加新站点</a>
      <a class="btn" href="/groups/${record.id}/manager">管理</a>
      <button class="btn btn-primary">关注</button>
    </div>
  </div>
</div>