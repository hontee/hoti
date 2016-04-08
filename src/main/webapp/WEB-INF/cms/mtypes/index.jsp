<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../_header.jsp" %>
<title>类型管理</title>
</head>
<body>
<header id="mtypes-header" class="cms-dg-header">
	<button id="mtypes-add" class="easyui-linkbutton" data-options="iconCls:'icon-add'">新建</button>
	<button id="mtypes-edit" class="easyui-linkbutton" data-options="iconCls:'icon-edit',disabled:true">编辑</button>
	<button id="mtypes-remove" class="easyui-linkbutton" data-options="iconCls:'icon-remove',disabled:true">删除</button>
	<button id="mtypes-reload" class="easyui-linkbutton" data-options="iconCls:'icon-reload'">刷新</button>
	
	<span class="cms-dg-search">
	  <select class="easyui-combobox" id="mtypes-state" data-options="panelHeight:'auto',editable: false" style="width:100px;">
        <option value="-1" selected>全部状态</option>
        <option value="1">启用</option>
        <option value="0">禁用</option>
      </select>
	  <input class="easyui-searchbox" data-options="prompt:'输入标题', searcher:mtypesEL.search" style="width:200px" />
	</span>
</header>
<table id="mtypes-dg"></table>
<footer id="mtypes-win"></footer>
<script>
// 变量取值要唯一
var mtypesEL = {
	add: $("#mtypes-add"),
	edit: $("#mtypes-edit"),
	remove: $("#mtypes-remove"),
	reload: $("#mtypes-reload"),
	dg: $("#mtypes-dg"),
	win: $("#mtypes-win")
};

// DataGrid
mtypesEL.dg.datagrid({
    url:'/cms/mtypes/list',
    fitColumns: true,
    border: false,
    idField: "id",
    rownumbers: true,
    pagination: true,
    pageSize: 20,
    pageList:[20, 50, 100],
    header: '#mtypes-header',
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
    	mtypesEL.reset();
    },
 	// 当取消选择一行时触发
    onUnselect: function(index,row) {
    	mtypesEL.reset();
    },
 	// 当全选时触发
    onSelectAll: function(rows) {
    	mtypesEL.reset();
    },
 	// 当取消全选时触发
    onUnselectAll: function(rows) {
    	mtypesEL.reset();
    },
    // 双击查看
    onDblClickRow: function(index,row) {
    	CMS.viewHandler("/cms/mtypes/" + row.id);
    }
});

// 根据选择记录触发: 重置按钮状态
mtypesEL.reset = function() {
	var length = mtypesEL.dg.datagrid("getSelections").length;
	if (length == 0) { // 全部禁用
		mtypesEL.linkButton(true, true, true);
	} else if (length == 1) { // 可编辑和删除
		mtypesEL.linkButton(false, false, true);
	} else { // 可批量操作
		mtypesEL.linkButton(true, true, false);
	}
}

// 设置按钮是否可用
mtypesEL.linkButton = function(a, b, c) {
	mtypesEL.edit.linkbutton({disabled: a});
	mtypesEL.remove.linkbutton({disabled: b});
}

// 搜索
mtypesEL.search = function(value){
	mtypesEL.dg.datagrid('load',{
		title: value,
		state: $('#cates-state').combobox('getValue')
	});
}

// 新建
mtypesEL.add.click(function() {
	mtypesEL.win.window({
		width: 480,
		height: 500,
		modal: true,
		title: '新建类型',
		collapsible: false,
		minimizable: false,
		maximizable: false,
		href: '/cms/mtypes/new',
		method: 'get',
		cache: false
	});
});

// 编辑
mtypesEL.edit.click(function() {
	var row = mtypesEL.dg.datagrid('getSelected');
	if (row) {
		mtypesEL.win.window({
			width: 480,
			height: 500,
			modal: true,
			title: '编辑类型',
			collapsible: false,
			minimizable: false,
			maximizable: false,
			href: '/cms/mtypes/' + row.id + '/edit',
			method: 'get',
			cache: false
		});
	}
});

// 删除
mtypesEL.remove.click(function() {
	CMS.removeSubmitHandler(mtypesEL, 'mtypes');
});

// 重载
mtypesEL.reload.click(function() {
	mtypesEL.dg.datagrid('clearSelections');
	mtypesEL.dg.datagrid('reload',{});
});
</script>
</body>
</html>