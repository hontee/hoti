<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../_header.jsp" %>
<title>推荐管理</title>
</head>
<body>
<header id="recmds-header" class="cms-dg-header">
	<button id="recmds-add" class="easyui-linkbutton" data-options="iconCls:'icon-add'">新建</button>
	<button id="recmds-edit" class="easyui-linkbutton" data-options="iconCls:'icon-edit',disabled:true">编辑</button>
	<button id="recmds-audit-ok" class="easyui-linkbutton" data-options="iconCls:'icon-edit',disabled:true">审核通过</button>
	<button id="recmds-audit-not" class="easyui-linkbutton" data-options="iconCls:'icon-edit',disabled:true">审核拒绝</button>
	<button id="recmds-remove" class="easyui-linkbutton" data-options="iconCls:'icon-remove',disabled:true">删除</button>
	<button id="recmds-reload" class="easyui-linkbutton" data-options="iconCls:'icon-reload'">刷新</button>
	
	<span class="cms-dg-search">
	  <select class="easyui-combobox" id="recmds-state" data-options="panelHeight:'auto',editable: false" style="width:100px;">
        <option value="-1" selected>全部状态</option>
        <option value="1">未审核</option>
        <option value="2">审核通过</option>
        <option value="3">审核拒绝</option>
      </select>
	  <input class="easyui-searchbox" data-options="prompt:'输入标题', searcher:recmdsEL.search" style="width:200px" />
	</span>
</header>
<table id="recmds-dg"></table>
<footer id="recmds-win"></footer>
<script>
// 变量取值要唯一
var recmdsEL = {
	add: $("#recmds-add"),
	edit: $("#recmds-edit"),
	auditOk: $("#recmds-audit-ok"),
	auditNot: $("#recmds-audit-not"),
	remove: $("#recmds-remove"),
	reload: $("#recmds-reload"),
	dg: $("#recmds-dg"),
	win: $("#recmds-win")
};

// DataGrid
recmdsEL.dg.datagrid({
    url:'/cms/recmds/list',
    fitColumns: true,
    border: false,
    idField: "id",
    rownumbers: true,
    pagination: true,
    pageSize: 20,
    pageList:[20, 50, 100],
    header: '#recmds-header',
    fit: true,
    columns:[[
        {field:'id', checkbox: true},
        {field:'name',title:'名称',width:100, hidden: true},
        {field:'title',title:'标题',width:200, sortable: true},
        {field:'keywords',title:'关键词',width:100, hidden: true},
        {field:'description',title:'描述',width:100, hidden: true},
        {field:'url',title:'网址',width:100, sortable: true},
        {field:'state',title:'状态',width:50, sortable: true, formatter: function(value,row,index) {
        	if (value == '2') {
				return '审核通过';
			} if (value == '3') {
				return '审核拒绝';
			} else {
				return '待审核';
			}
        }},
        {field:'created',title:'创建时间',width:80, sortable: true, formatter: function(value,row,index) {
        	return new Date(value).format();  
        }},
        {field:'lastModified',title:'最后更新时间',width:80, sortable: true, formatter: function(value,row,index) {
        	return new Date(value).format();  
        }},
        {field:'creator',title:'推荐人',width:50, sortable: true},
        {field:'remark',title:'备注',width:100}
    ]],
 	// 当选择一行时触发
    onSelect: function(index,row) {
    	recmdsEL.reset();
    },
 	// 当取消选择一行时触发
    onUnselect: function(index,row) {
    	recmdsEL.reset();
    },
 	// 当全选时触发
    onSelectAll: function(rows) {
    	recmdsEL.reset();
    },
 	// 当取消全选时触发
    onUnselectAll: function(rows) {
    	recmdsEL.reset();
    },
    // 双击查看
    onDblClickRow: function(index,row) {
    	CMS.viewHandler("/cms/recmds/" + row.id);
    }
});

// 根据选择记录触发: 重置按钮状态
recmdsEL.reset = function() {
	var length = recmdsEL.dg.datagrid("getSelections").length;
	if (length == 0) { // 全部禁用
		recmdsEL.linkButton(true);
	} else if (length == 1) { // 可编辑和删除
		recmdsEL.linkButton(false);
	} else { // 可批量操作
		recmdsEL.linkButton(true);
	}
}

// 设置按钮是否可用
recmdsEL.linkButton = function(a) {
	recmdsEL.edit.linkbutton({disabled: a});
	recmdsEL.auditOk.linkbutton({disabled: a});
	recmdsEL.auditNot.linkbutton({disabled: a});
	recmdsEL.remove.linkbutton({disabled: a});
}

// 搜索
recmdsEL.search = function(value){
	recmdsEL.dg.datagrid('load',{
		title: value,
		state: $('#recmds-state').combobox('getValue')
	});
}

// 新建
recmdsEL.add.click(function() {
	recmdsEL.win.window({
		width: 480,
		height: 200,
		modal: true,
		title: '新建推荐',
		collapsible: false,
		minimizable: false,
		maximizable: false,
		href: '/cms/recmds/new',
		method: 'get',
		cache: false
	});
});

// 编辑
recmdsEL.edit.click(function() {
	var row = recmdsEL.dg.datagrid('getSelected');
	if (row) {
		recmdsEL.win.window({
			width: 480,
			height: 450,
			modal: true,
			title: '编辑推荐',
			collapsible: false,
			minimizable: false,
			maximizable: false,
			href: '/cms/recmds/' + row.id + '/edit',
			method: 'get',
			cache: false
		});
	}
});

//审核通过
recmdsEL.auditOk.click(function() {
	var row = recmdsEL.dg.datagrid('getSelected');
	if (row) {
		recmdsEL.win.window({
			width: 480,
			height: 500,
			modal: true,
			title: '审核',
			collapsible: false,
			minimizable: false,
			maximizable: false,
			href: '/cms/recmds/' + row.id + '/ok',
			method: 'get',
			cache: false
		});
	}
});

//审核拒绝
recmdsEL.auditNot.click(function() {
	var row = recmdsEL.dg.datagrid('getSelected');
	if (row) {
		recmdsEL.win.window({
			width: 480,
			height: 380,
			modal: true,
			title: '审核',
			collapsible: false,
			minimizable: false,
			maximizable: false,
			href: '/cms/recmds/' + row.id + '/refuse',
			method: 'get',
			cache: false
		});
	}
});

// 删除
recmdsEL.remove.click(function() {
	CMS.removeSubmitHandler(recmdsEL, 'recmds');
});

// 重载
recmdsEL.reload.click(function() {
	recmdsEL.dg.datagrid('reload',{});
});
</script>
</body>
</html>