<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../_header.jsp" %>
<title>推荐站点</title>
</head>
<body>
<header id="recmds-header" class="cms-dg-header">
	<button id="recmds-add" class="easyui-linkbutton" data-options="iconCls:'icon-add'">新建</button>
	<button id="recmds-edit" class="easyui-linkbutton" data-options="iconCls:'icon-edit',disabled:true">编辑</button>
	<button id="recmds-remove" class="easyui-linkbutton" data-options="iconCls:'icon-remove',disabled:true">删除</button>
	<button id="recmds-reload" class="easyui-linkbutton" data-options="iconCls:'icon-reload'">刷新</button>
	
	<span class="cms-dg-search">
	  <input class="easyui-searchbox" data-options="prompt:'输入标题', searcher:recmdsEL.search" style="width:200px" />
	</span>
</header>
<table id="recmds-dg"></table>
<footer>
    <div id="recmds-add-win"></div>
    <div id="recmds-edit-win"></div>
</footer>
<script>
// 变量取值要唯一
var recmdsEL = {
	add: $("#recmds-add"),
	edit: $("#recmds-edit"),
	remove: $("#recmds-remove"),
	reload: $("#recmds-reload"),
	dg: $("#recmds-dg"),
	addWin: $("#recmds-add-win"),
	editWin: $("#recmds-edit-win")
};

// DataGrid
recmdsEL.dg.datagrid({
    url:'/cms/recmds/list',
    fitColumns: true,
    border: false,
    idField: "id",
    rownumbers: true,
    pagination: true,
    title:'推荐站点',
    header: '#recmds-header',
    fit: true,
    columns:[[
        {field:'id', checkbox: true},
        {field:'name',title:'名称',width:100, hidden: true},
        {field:'title',title:'标题',width:100, sortable: true},
        {field:'keywords',title:'关键词',width:100},
        {field:'description',title:'描述',width:100},
        {field:'url',title:'网址',width:100, sortable: true},
        {field:'state',title:'状态',width:100, sortable: true, formatter: function(value,row,index) {
        	if (value == '2') {
				return '审核通过';
			} if (value == '3') {
				return '审核未通过';
			} else {
				return '待审核';
			}
        }},
        {field:'created',title:'创建时间',width:100, sortable: true, formatter: function(value,row,index) {
        	return new Date(value).format();  
        }},
        {field:'lastModified',title:'最后更新时间',width:100, sortable: true, formatter: function(value,row,index) {
        	return new Date(value).format();  
        }},
        {field:'creator',title:'创建人',width:100, sortable: true},
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
		recmdsEL.linkButton(true, true, true);
	} else if (length == 1) { // 可编辑和删除
		recmdsEL.linkButton(false, false, true);
	} else { // 可批量操作
		recmdsEL.linkButton(true, true, false);
	}
}

// 设置按钮是否可用
recmdsEL.linkButton = function(a, b, c) {
	recmdsEL.edit.linkbutton({disabled: a});
	recmdsEL.remove.linkbutton({disabled: b});
}

// 搜索
recmdsEL.search = function(value){
	recmdsEL.dg.datagrid('load',{
		title: value
	});
}

// 新建
recmdsEL.add.click(function() {
	recmdsEL.addWin.window({
		width: 480,
		height: 650,
		modal: true,
		title: '新建',
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
		recmdsEL.editWin.window({
			width: 480,
			height: 650,
			modal: true,
			title: '编辑',
			collapsible: false,
			minimizable: false,
			maximizable: false,
			href: '/cms/recmds/' + row.id + '/edit',
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