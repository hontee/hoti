<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="author" content="www.kuaiba.me" />
<link href="/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<link href="/assets/kuaiba/jquery.mmenu.all.css" rel="stylesheet" />
<script src="//cdn.bootcss.com/jquery/2.1.4/jquery.min.js"></script>
<script src="/bootstrap/js/bootstrap.min.js"></script>
<script src="/assets/kuaiba/layout.js"></script>
<script src="/assets/kuaiba/jquery.mmenu.all.min.js"></script>
</head>
<body>
<div class="navbar navbar-light bg-faded">
<div class="container">
  <a class="navbar-brand" href="#menu">快吧</a>
  <ul class="nav navbar-nav pull-left">
    <li class="nav-item">
      <form class="form-inline navbar-form" action="/search" method="get">
	    <input name="q" value="${q}" class="form-control" placeholder="搜索" autocomplete="off">
	  </form>
    </li>
    <li class="nav-item">
      <a class="nav-link" href="/cates">类别</a>
    </li>
    <li class="nav-item">
      <a class="nav-link" href="/groups">群组</a>
    </li>
    <li class="nav-item">
      <a class="nav-link" href="/share">推荐</a>
    </li>
  </ul>
  <ul class="nav navbar-nav pull-right">
    <li class="nav-item">
      <a class="btn btn-primary" href="/login">登录</a>
    </li>
    <c:if test="${admin}">
    <li class="nav-item">
      <a class="btn btn-warning" href="/cms" target="_blank">后台管理</a>
    </li>
    </c:if>
  </ul>
</div>
</div>

<nav id="menu">
			<ul class="listview-icons">
				<li><a href="/">Home</a></li>
				<li><a href="/wordpress-plugin.html">WordPress plugin</a></li>
				<li><a href="/whats-new.html">What's new</a></li>
				<li><a href="/examples.html">Examples Playground</a></li>
		 		<li>
		 			<span>Tutorials</span>
					<ul>
						<li>
							<span>Basic off-canvas menu</span>
							<div>
								<p>This tutorial guides you through the first steps of creating an app look-alike sliding menu for your website and webapp.</p>
								<ul>
									<li><a href="/tutorials/off-canvas">Getting started</a></li>
									<li><a href="/tutorials/off-canvas/the-page.html">The page</a></li>
									<li><a href="/tutorials/off-canvas/the-menu.html">The menu</a></li>
									<li><a href="/tutorials/off-canvas/styling-lists.html">Styling lists</a></li>
									<li><a href="/tutorials/off-canvas/fire-the-plugin.html">Fire the plugin</a></li>
									<li><a href="/tutorials/off-canvas/open-and-close-the-menu.html">Open and close the menu</a></li>
									<li><a href="/tutorials/off-canvas/learn-more.html">Learn more</a></li>
									<li><a href="/tutorials/off-canvas/video.html">Video tutorial</a></li>
								</ul>
							</div>
						</li>
						<li>
							<span>Advanced menu with rich content</span>
							<div>
								<p>This tutorial shows you how you can create an advanced menu with rich content.</p>
								<ul>
									<li><a href="/tutorials/advanced">Getting started</a></li>
									<li><a href="/tutorials/advanced/setting-up-the-html.html">Setting up the HTML</a></li>
									<li><a href="/tutorials/advanced/styling-lists.html">Styling lists</a></li>
									<li><a href="/tutorials/advanced/thumbnail-grid.html">Thumbnail grid</a></li>
									<li><a href="/tutorials/advanced/learn-more.html">Learn more</a></li>
								</ul>
							</div>
						</li>
						<li><a href="/tutorials/hamburger-css.html">Animated hamburger icon</a></li>
						<li><a href="/tutorials/dynamic-content.html">Dynamic content</a></li>
					</ul>
				</li>
				<li>
					<span>Documentation</span>
					<ul>
						<li>
							<span>Options</span>
							<ul>
								<li><a href="/documentation/options">Options</a></li>
								<li><a href="/documentation/options/configuration.html">Configuration</a></li>
							</ul>
						</li>
						<li><a href="/documentation/api.html">API</a></li>
						<li>
							<span>Extensions</span>
							<div>
								<p>With these extensions, you can easily extend the look and feel of your menu.</p>
								<ul>
									<li><a href="/documentation/extensions">Introduction</a></li>
									<li><a href="/documentation/extensions/border-style.html">Border style</a></li>
									<li><a href="/documentation/extensions/effects.html">Effects</a></li>
									<li><a href="/documentation/extensions/fullscreen.html">Fullscreen</a></li>
									<li><a href="/documentation/extensions/iconbar.html">Iconbar</a></li>
									<li><a href="/documentation/extensions/justified-listview.html">Justified listview</a><em class="Counter badge">NEW</em></li>
									<li><a href="/documentation/extensions/multiline.html">Multiline</a></li>
									<li><a href="/documentation/extensions/page-dim.html">Page dim</a></li>
									<li><a href="/documentation/extensions/page-shadow.html">Page shadow</a></li>
									<li><a href="/documentation/extensions/popup.html">Popup</a><em class="Counter badge">NEW</em></li>
									<li><a href="/documentation/extensions/positioning.html">Positioning</a></li>
									<li><a href="/documentation/extensions/themes.html">Themes</a></li>
									<li><a href="/documentation/extensions/tileview.html">Tileview</a></li>
									<li><a href="/documentation/extensions/widescreen.html">Widescreen</a></li>
									<li><a href="/documentation/extensions/3rdparty.html">Introduction</a></li>
									<li><a href="/documentation/extensions/left-subpanels.html">Left subpanels</a></li>
									<li><a href="/documentation/extensions/panel-shadow.html">Panel shadow</a></li>
								</ul>
							</div>
						</li>
						<li>
							<span>Add-ons</span>
							<div>
								<p>With these add-ons, you can easily add additional behavior to your menu.</p>
								<ul>
									<li><a href="/documentation/addons">Introduction</a></li>
									<li><a href="/documentation/addons/auto-height.html">Auto height</a></li>
									<li><a href="/documentation/addons/back-button.html">Back button</a></li>
									<li><a href="/documentation/addons/columns.html">Columns</a><em class="Counter badge">NEW</em></li>
									<li><a href="/documentation/addons/counters.html">Counters</a></li>
									<li><a href="/documentation/addons/dividers.html">Dividers</a></li>
									<li><a href="/documentation/addons/drag-open.html">Drag open</a></li>
									<li><a href="/documentation/addons/dropdown.html">Dropdown</a><em class="Counter badge">NEW</em></li>
									<li><a href="/documentation/addons/fixed-elements.html">Fixed elements</a></li>
									<li><a href="/documentation/addons/icon-panels.html">Icon panels</a></li>
									<li><a href="/documentation/addons/navbars.html">Navbars</a></li>
									<li><a href="/documentation/addons/off-canvas.html">Off-canvas</a></li>
									<li><a href="/documentation/addons/screen-reader.html">Screen reader</a><em class="Counter badge">NEW</em></li>
									<li><a href="/documentation/addons/scroll-bug-fix.html">Scroll bug fix</a><em class="Counter badge">NEW</em></li>
									<li><a href="/documentation/addons/searchfield.html">Searchfield</a></li>
									<li><a href="/documentation/addons/section-indexer.html">Section indexer</a></li>
									<li><a href="/documentation/addons/set-selected.html">Set selected</a><em class="Counter badge">NEW</em></li>
									<li><a href="/documentation/addons/toggles.html">Toggles</a></li>
									<li><a href="/documentation/addons/3rdparty.html">Introduction</a></li>
									<li><a href="/documentation/addons/current-item.html">Current item</a></li>
									<li><a href="/documentation/addons/drag-close.html">Drag close</a></li>
								</ul>
							</div>
						</li>
						<li><a href="/documentation/framework-wrappers.html">Framework wrappers</a></li>
						<li>
							<span>Custom CSS</span>
							<div>
								<p>With the usage of SCSS, you can easily create customized .css files for your menu.</p>
								<ul>
									<li><a href="/documentation/custom-css">Introduction</a></li>
									<li><a href="/documentation/custom-css/concatenate-files.html">Concatenate .css files</a></li>
									<li><a href="/documentation/custom-css/override-variables.html">Override variable values</a></li>
									<li><a href="/documentation/custom-css/custom-colors.html">Custom colors</a></li>
									<li><a href="/documentation/custom-css/custom-sizes.html">Custom sizes</a></li>
								</ul>
							</div>
						</li>
						<li><a href="/documentation/changelog.html">Changelog</a></li>
					</ul>
				</li>
				<li><a href="/on-and-off-canvas.html">On- and Off-canvas</a></li>
				<li><a href="/download.html">Download &amp; License information</a></li>
				<li><a href="https://github.com/FrDH/jQuery.mmenu" target="_blank">Fork us on GitHub</a></li>
				<li>
					<span>Support</span>
					<ul>
						<li><a href="/support/tips-and-tricks.html">Tips and tricks</a></li>
						<li><a href="/support/problem-solving.html">Problem solving</a></li>
						<li><a href="/support/contact.html">Contact</a></li>
					</ul>
				</li>			
			</ul>
		</nav>