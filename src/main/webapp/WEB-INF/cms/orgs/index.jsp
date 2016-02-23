<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../_header.jsp" %>
<title>分组管理</title>
</head>
<body>
<header id="orgs-header" class="cms-dg-header">
	<button id="orgs-add" class="easyui-linkbutton" data-options="iconCls:'icon-add'">新建</button>
	<button id="orgs-edit" class="easyui-linkbutton" data-options="iconCls:'icon-edit',disabled:true">编辑</button>
	<button id="orgs-remove" class="easyui-linkbutton" data-options="iconCls:'icon-remove',disabled:true">删除</button>
	<button id="orgs-reload" class="easyui-linkbutton" data-options="iconCls:'icon-reload'">刷新</button>
	
	<span class="cms-dg-search">
	  <input class="easyui-searchbox" data-options="prompt:'输入标题', searcher:orgsEL.search" style="width:200px" />
	</span>
</header>
<table id="orgs-dg"></table>
<footer>
    <div id="orgs-add-win"></div>
    <div id="orgs-edit-win"></div>
</footer>
<script>
// 变量取值要唯一
var orgsEL = {
	add: $("#orgs-add"),
	edit: $("#orgs-edit"),
	remove: $("#orgs-remove"),
	reload: $("#orgs-reload"),
	dg: $("#orgs-dg"),
	addWin: $("#orgs-add-win"),
	editWin: $("#orgs-edit-win")
};

// DataGrid
orgsEL.dg.datagrid({
    url:'/cms/orgs/list',
    fitColumns: true,
    border: false,
    idField: "id",
    rownumbers: true,
    pagination: true,
    title:'菜单管理',
    header: '#orgs-header',
    fit: true,
    columns:[[
        {field:'id', checkbox: true},
        {field:'name',title:'名称',width:100, sortable: true},
        {field:'title',title:'标题',width:100, sortable: true},
        {field:'description',title:'描述',width:100},
        {field:'weight',title:'权重',width:100, sortable: true},
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
        {field:'lastModified',title:'最后更新时间',width:100, sortable: true, formatter: function(value,row,index) {
        	return new Date(value).format();  
        }},
        {field:'creator',title:'创建人',width:100, sortable: true}
    ]],
 	// 当选择一行时触发
    onSelect: function(index,row) {
    	orgsEL.reset();
    },
 	// 当取消选择一行时触发
    onUnselect: function(index,row) {
    	orgsEL.reset();
    },
 	// 当全选时触发
    onSelectAll: function(rows) {
    	orgsEL.reset();
    },
 	// 当取消全选时触发
    onUnselectAll: function(rows) {
    	orgsEL.reset();
    },
    // 双击查看
    onDblClickRow: function(index,row) {
    	console.log("view detail");
    }
});

// 根据选择记录触发: 重置按钮状态
orgsEL.reset = function() {
	var length = orgsEL.dg.datagrid("getSelections").length;
	if (length == 0) { // 全部禁用
		orgsEL.linkButton(true, true, true);
	} else if (length == 1) { // 可编辑和删除
		orgsEL.linkButton(false, false, true);
	} else { // 可批量操作
		orgsEL.linkButton(true, true, false);
	}
}

// 设置按钮是否可用
orgsEL.linkButton = function(a, b, c) {
	orgsEL.edit.linkbutton({disabled: a});
	orgsEL.remove.linkbutton({disabled: b});
}

// 搜索
orgsEL.search = function(value){
	orgsEL.dg.datagrid('load',{
		title: value
	});
}

// 新建
orgsEL.add.click(function() {
	orgsEL.addWin.window({
		width: 480,
		height: 650,
		modal: true,
		title: '新建分组',
		collapsible: false,
		minimizable: false,
		maximizable: false,
		href: '/cms/orgs/new',
		method: 'get',
		cache: false
	});
});

// 编辑
orgsEL.edit.click(function() {
	var row = orgsEL.dg.datagrid('getSelected');
	if (row) {
		orgsEL.editWin.window({
			width: 480,
			height: 650,
			modal: true,
			title: '编辑分组',
			collapsible: false,
			minimizable: false,
			maximizable: false,
			href: '/cms/orgs/' + row.id + '/edit',
			method: 'get',
			cache: false
		});
	}
});

// 删除
orgsEL.remove.click(function() {
	CMS.removeSubmitHandler(orgsEL, 'orgs');
});

// 重载
orgsEL.reload.click(function() {
	orgsEL.dg.datagrid('reload',{});
});
</script>
</body>
</html>