<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../_header.jsp" %>
<title>站点管理</title>
</head>
<body>
<header id="bookmarks-header" class="cms-dg-header">
	<button id="bookmarks-add" class="easyui-linkbutton" data-options="iconCls:'icon-add'">新建</button>
	<button id="bookmarks-edit" class="easyui-linkbutton" data-options="iconCls:'icon-edit',disabled:true">编辑</button>
	<button id="bookmarks-remove" class="easyui-linkbutton" data-options="iconCls:'icon-remove',disabled:true">删除</button>
	<button id="bookmarks-reload" class="easyui-linkbutton" data-options="iconCls:'icon-reload'">刷新</button>
	
	<span class="cms-dg-search">
	  <input class="easyui-searchbox" data-options="prompt:'输入标题', searcher:bookmarksEL.search" style="width:200px" />
	</span>
</header>
<table id="bookmarks-dg"></table>
<footer id="bookmarks-win"></footer>
<script>
// 变量取值要唯一
var bookmarksEL = {
	add: $("#bookmarks-add"),
	edit: $("#bookmarks-edit"),
	remove: $("#bookmarks-remove"),
	reload: $("#bookmarks-reload"),
	dg: $("#bookmarks-dg"),
	win: $("#bookmarks-win")
};

// DataGrid
bookmarksEL.dg.datagrid({
    url:'/cms/bookmarks/list',
    fitColumns: true,
    border: false,
    idField: "id",
    rownumbers: true,
    pagination: true,
    pageSize: 20,
    pageList:[20, 50, 100],
    header: '#bookmarks-header',
    fit: true,
    columns:[[
        {field:'id', checkbox: true},
        {field:'name',title:'名称',width:100, hidden: true},
        {field:'title',title:'标题',width:100, sortable: true},
        {field:'url',title:'网址',width:100},
        {field:'description',title:'描述',width:100},
        {field:'avatar',title:'头像',width:100},
        {field:'reffer',title:'来源',width:100},
        {field:'star',title:'星',width:100, sortable: true},
        {field:'hit',title:'点击数',width:100, sortable: true},
        {field:'category',title:'所属分类',width:100, sortable: true, formatter: function(value,row,index) {
        	return row.extCategoryTitle;
        }},
        {field:'state',title:'状态',width:100, sortable: true, formatter: function(value,row,index) {
        	if (value == '1') {
				return '启用';
			} else {
				return '禁用';
			}
        }},
        {field:'created',title:'创建时间',width:100, sortable: true, formatter: function(value,row,index) {
        	return new Date(value).format();  
        }},
        {field:'lastModified',title:'更新时间',width:100, sortable: true, formatter: function(value,row,index) {
        	return new Date(value).format();  
        }},
        {field:'createBy',title:'创建人',width:100, sortable: true, formatter: function(value,row,index) {
        	return row.extCreateName;
        }},
        {field:'extCategoryTitle',title:'分类标题', hidden: true},
        {field:'extCreateName',title:'用户名', hidden: true},
    ]],
 	// 当选择一行时触发
    onSelect: function(index,row) {
    	bookmarksEL.reset();
    },
 	// 当取消选择一行时触发
    onUnselect: function(index,row) {
    	bookmarksEL.reset();
    },
 	// 当全选时触发
    onSelectAll: function(rows) {
    	bookmarksEL.reset();
    },
 	// 当取消全选时触发
    onUnselectAll: function(rows) {
    	bookmarksEL.reset();
    },
    // 双击查看
    onDblClickRow: function(index,row) {
    	CMS.viewHandler("/cms/bookmarks/" + row.id);
    }
});

// 根据选择记录触发: 重置按钮状态
bookmarksEL.reset = function() {
	var length = bookmarksEL.dg.datagrid("getSelections").length;
	if (length == 0) { // 全部禁用
		bookmarksEL.linkButton(true, true, true);
	} else if (length == 1) { // 可编辑和删除
		bookmarksEL.linkButton(false, false, true);
	} else { // 可批量操作
		bookmarksEL.linkButton(true, true, false);
	}
}

// 设置按钮是否可用
bookmarksEL.linkButton = function(a, b, c) {
	bookmarksEL.edit.linkbutton({disabled: a});
	bookmarksEL.remove.linkbutton({disabled: b});
}

// 搜索
bookmarksEL.search = function(value){
	bookmarksEL.dg.datagrid('load',{
		title: value
	});
}

// 新建
bookmarksEL.add.click(function() {
	bookmarksEL.win.window({
		width: 480,
		height: 500,
		modal: true,
		title: '新建站点',
		collapsible: false,
		minimizable: false,
		maximizable: false,
		href: '/cms/bookmarks/new',
		method: 'get',
		cache: false
	});
});

// 编辑
bookmarksEL.edit.click(function() {
	var row = bookmarksEL.dg.datagrid('getSelected');
	if (row) {
		bookmarksEL.win.window({
			width: 480,
			height: 600,
			modal: true,
			title: '编辑站点',
			collapsible: false,
			minimizable: false,
			maximizable: false,
			href: '/cms/bookmarks/' + row.id + '/edit',
			method: 'get',
			cache: false
		});
	}
});

// 删除
bookmarksEL.remove.click(function() {
	CMS.removeSubmitHandler(bookmarksEL, 'bookmarks');
});

// 重载
bookmarksEL.reload.click(function() {
	bookmarksEL.dg.datagrid('reload',{});
});
</script>
</body>
</html>