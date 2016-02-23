<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../_header.jsp" %>
<title>站点管理</title>
</head>
<body>
<header id="websites-header" class="cms-dg-header">
	<button id="websites-add" class="easyui-linkbutton" data-options="iconCls:'icon-add'">新建</button>
	<button id="websites-edit" class="easyui-linkbutton" data-options="iconCls:'icon-edit',disabled:true">编辑</button>
	<button id="websites-remove" class="easyui-linkbutton" data-options="iconCls:'icon-remove',disabled:true">删除</button>
	<button id="websites-reload" class="easyui-linkbutton" data-options="iconCls:'icon-reload'">刷新</button>
	
	<span class="cms-dg-search">
	  <input class="easyui-searchbox" data-options="prompt:'输入标题', searcher:websitesEL.search" style="width:200px" />
	</span>
</header>
<table id="websites-dg"></table>
<footer>
    <div id="websites-add-win"></div>
    <div id="websites-edit-win"></div>
</footer>
<script>
// 变量取值要唯一
var websitesEL = {
	add: $("#websites-add"),
	edit: $("#websites-edit"),
	remove: $("#websites-remove"),
	reload: $("#websites-reload"),
	dg: $("#websites-dg"),
	addWin: $("#websites-add-win"),
	editWin: $("#websites-edit-win")
};

// DataGrid
websitesEL.dg.datagrid({
    url:'/cms/websites/list',
    fitColumns: true,
    border: false,
    idField: "id",
    rownumbers: true,
    pagination: true,
    title:'站点管理',
    header: '#websites-header',
    fit: true,
    columns:[[
        {field:'id', checkbox: true},
        {field:'name',title:'名称',width:100, sortable: true},
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
    	websitesEL.reset();
    },
 	// 当取消选择一行时触发
    onUnselect: function(index,row) {
    	websitesEL.reset();
    },
 	// 当全选时触发
    onSelectAll: function(rows) {
    	websitesEL.reset();
    },
 	// 当取消全选时触发
    onUnselectAll: function(rows) {
    	websitesEL.reset();
    },
    // 双击查看
    onDblClickRow: function(index,row) {
    	console.log("view detail");
    }
});

// 根据选择记录触发: 重置按钮状态
websitesEL.reset = function() {
	var length = websitesEL.dg.datagrid("getSelections").length;
	if (length == 0) { // 全部禁用
		websitesEL.linkButton(true, true, true);
	} else if (length == 1) { // 可编辑和删除
		websitesEL.linkButton(false, false, true);
	} else { // 可批量操作
		websitesEL.linkButton(true, true, false);
	}
}

// 设置按钮是否可用
websitesEL.linkButton = function(a, b, c) {
	websitesEL.edit.linkbutton({disabled: a});
	websitesEL.remove.linkbutton({disabled: b});
}

// 搜索
websitesEL.search = function(value){
	websitesEL.dg.datagrid('load',{
		title: value
	});
}

// 新建
websitesEL.add.click(function() {
	websitesEL.addWin.window({
		width: 480,
		height: 500,
		modal: true,
		title: '新建站点',
		collapsible: false,
		minimizable: false,
		maximizable: false,
		href: '/cms/websites/new',
		method: 'get',
		cache: false
	});
});

// 编辑
websitesEL.edit.click(function() {
	var row = websitesEL.dg.datagrid('getSelected');
	if (row) {
		websitesEL.editWin.window({
			width: 480,
			height: 500,
			modal: true,
			title: '编辑站点',
			collapsible: false,
			minimizable: false,
			maximizable: false,
			href: '/cms/websites/' + row.id + '/edit',
			method: 'get',
			cache: false
		});
	}
});

// 删除
websitesEL.remove.click(function() {
	CMS.removeSubmitHandler(websitesEL, 'websites');
});

// 重载
websitesEL.reload.click(function() {
	websitesEL.dg.datagrid('reload',{});
});
</script>
</body>
</html>