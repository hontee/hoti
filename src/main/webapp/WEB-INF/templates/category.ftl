<@override name="body">
<nav id="mmenu">
  <ul class="listview-icons">
    <#list cates as r>
    <li>
      <a href="/category/${r.id}">${r.title!}</a>
      <em class="mm-counter mm-badge">${r.count}</em>
    </li>
    </#list>
  </ul>
</nav>
</@override>

<@extends name="module/header.ftl"/>