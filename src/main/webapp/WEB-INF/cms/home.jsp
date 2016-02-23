<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="_header.jsp" %>
<title>快吧后台管理系统</title>
</head>
<body class="easyui-layout">
  <div data-options="region:'north'" style="height:80px;"></div>
  <div data-options="region:'west',title:'菜单管理'" style="width:200px;">
    <div id="menu-datalist"></div>
  </div>
  <div data-options="region:'center'">
    <div id="homeTabs" class="easyui-tabs">
	    <div title="首页" style="padding:10px">
	        
	    </div>
	</div>
  </div>
  
<script>
var homeTabs = $('#homeTabs').tabs({
  fit:true,
  border:false
});
	
var datalist = $("#menu-datalist").datalist({
    url: '/cms/menus/datalist',
    checkbox: false,
    lines: false,
    border:false,
    valueField: 'name',
    textField: 'title',
    groupField: 'organization',
    textFormatter: function(value, row, index) {
    	return "<span style='padding-left: 20px;'>" + value + "</span>"
    },
    onClickRow: function(index, row) {
    	if (homeTabs.tabs("exists", row.title)) {
    		homeTabs.tabs('select', row.title);
		} else {
			homeTabs.tabs('add', {
	          id: row.name,
	          title : row.title,
	          href: row.path,
	          closable : true
	        });
		}
    }
});
</script>
</body>
</html>