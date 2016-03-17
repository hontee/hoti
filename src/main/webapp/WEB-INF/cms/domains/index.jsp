<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../_header.jsp" %>
<title>业务领域</title>
</head>
<body>
<header id="domains-header" class="cms-dg-header">
	<button id="domains-add" class="easyui-linkbutton" data-options="iconCls:'icon-add'">新建</button>
	<button id="domains-edit" class="easyui-linkbutton" data-options="iconCls:'icon-edit',disabled:true">编辑</button>
	<button id="domains-remove" class="easyui-linkbutton" data-options="iconCls:'icon-remove',disabled:true">删除</button>
	<button id="domains-reload" class="easyui-linkbutton" data-options="iconCls:'icon-reload'">刷新</button>
	
	<span class="cms-dg-search">
	  <input class="easyui-searchbox" data-options="prompt:'输入标题', searcher:domainsEL.search" style="width:200px" />
	</span>
</header>
<table id="domains-dg"></table>
<footer>
    <div id="domains-add-win"></div>
    <div id="domains-edit-win"></div>
</footer>
<script>
// 变量取值要唯一
var domainsEL = {
	add: $("#domains-add"),
	edit: $("#domains-edit"),
	remove: $("#domains-remove"),
	reload: $("#domains-reload"),
	dg: $("#domains-dg"),
	addWin: $("#domains-add-win"),
	editWin: $("#domains-edit-win")
};

// DataGrid
domainsEL.dg.datagrid({
    url:'/cms/domains/list',
    fitColumns: true,
    border: false,
    idField: "id",
    rownumbers: true,
    pagination: true,
    pageSize: 20,
    pageList:[20, 50, 100],
    header: '#domains-header',
    fit: true,
    columns:[[
        {field:'id', checkbox: true},
        {field:'name',title:'名称',width:100, sortable: true},
        {field:'title',title:'标题',width:100, sortable: true},
        {field:'description',title:'描述',width:100},
        {field:'weight',title:'权重',width:100, sortable: true},
        {field:'count',title:'计数',width:100, sortable: true},
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
    	domainsEL.reset();
    },
 	// 当取消选择一行时触发
    onUnselect: function(index,row) {
    	domainsEL.reset();
    },
 	// 当全选时触发
    onSelectAll: function(rows) {
    	domainsEL.reset();
    },
 	// 当取消全选时触发
    onUnselectAll: function(rows) {
    	domainsEL.reset();
    },
    // 双击查看
    onDblClickRow: function(index,row) {
    	CMS.viewHandler("/cms/domains/" + row.id);
    }
});

// 根据选择记录触发: 重置按钮状态
domainsEL.reset = function() {
	var length = domainsEL.dg.datagrid("getSelections").length;
	if (length == 0) { // 全部禁用
		domainsEL.linkButton(true, true, true);
	} else if (length == 1) { // 可编辑和删除
		domainsEL.linkButton(false, false, true);
	} else { // 可批量操作
		domainsEL.linkButton(true, true, false);
	}
}

// 设置按钮是否可用
domainsEL.linkButton = function(a, b, c) {
	domainsEL.edit.linkbutton({disabled: a});
	domainsEL.remove.linkbutton({disabled: b});
}

// 搜索
domainsEL.search = function(value){
	domainsEL.dg.datagrid('load',{
		title: value
	});
}

// 新建
domainsEL.add.click(function() {
	domainsEL.addWin.window({
		width: 480,
		height: 650,
		modal: true,
		title: '新建领域',
		collapsible: false,
		minimizable: false,
		maximizable: false,
		href: '/cms/domains/new',
		method: 'get',
		cache: false
	});
});

// 编辑
domainsEL.edit.click(function() {
	var row = domainsEL.dg.datagrid('getSelected');
	if (row) {
		domainsEL.editWin.window({
			width: 480,
			height: 650,
			modal: true,
			title: '编辑领域',
			collapsible: false,
			minimizable: false,
			maximizable: false,
			href: '/cms/domains/' + row.id + '/edit',
			method: 'get',
			cache: false
		});
	}
});

// 删除
domainsEL.remove.click(function() {
	CMS.removeSubmitHandler(domainsEL, 'domains');
});

// 重载
domainsEL.reload.click(function() {
	domainsEL.dg.datagrid('reload',{});
});
</script>
</body>
</html>