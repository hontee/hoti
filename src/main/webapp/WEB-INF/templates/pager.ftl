<div class="toolbar">
  <div class="container">
    <div class="columns text-center">
      <div class="btn-group">
        <a class="btn btn-outline <#if !page.previous??>disabled</#if>" <#if page.previous??>href="${page.previous!}"</#if>>上一页</a>
        <a class="btn btn-outline <#if !page.next??>disabled</#if>" <#if page.next??>href="${page.next!}"</#if>">下一页</a>
      </div>
    </div>
  </div>
</div>