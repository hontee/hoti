<@override name="body">
<nav id="mmenu">
  <ul class="listview-icons">
    <#list domains as r>
    <li>
      <span>${r.title!}</span>
      <ul>
        <#list r.cates as c>
        <li>
          <a href="/category/${c.id}">${c.title!}</a>
          <em class="mm-counter mm-badge">${c.count}</em>
        </li>
        </#list>
      </ul>
    </li>
    </#list>
  </ul>
</nav>
</@override>

<@extends name="module/header.ftl"/>