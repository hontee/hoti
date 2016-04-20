<@override name="body">
<nav id="mmenu">
  <ul class="listview-icons">
    <#list categories as c>
    <li>
      <a href="/categories/${c.id}">${c.title!}</a>
      <em class="mm-counter mm-badge">${c.product}</em>
    </li>
    </#list>
  </ul>
</nav>
</@override>

<@extends name="module/header.ftl"/>