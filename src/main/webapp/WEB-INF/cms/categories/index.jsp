<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../_header.jsp" %>
<title>类别管理</title>
</head>
<body>
<header id="categories-header" class="cms-dg-header">
	<button id="categories-add" class="easyui-linkbutton" data-options="iconCls:'icon-add'">新建</button>
	<button id="categories-edit" class="easyui-linkbutton" data-options="iconCls:'icon-edit',disabled:true">编辑</button>
	<button id="categories-remove" class="easyui-linkbutton" data-options="iconCls:'icon-remove',disabled:true">删除</button>
	<button id="categories-count" class="easyui-linkbutton" data-options="iconCls:'icon-reload'">更新统计</button>
	<button id="categories-reload" class="easyui-linkbutton" data-options="iconCls:'icon-reload'">刷新</button>
	
	<span class="cms-dg-search">
	  <select class="easyui-combobox" id="categories-state" data-options="panelHeight:'auto',editable: false" style="width:100px;">
        <option value="-1" selected>全部状态</option>
        <option value="1">启用</option>
        <option value="0">禁用</option>
      </select>
	  <input class="easyui-searchbox" data-options="prompt:'输入标题', searcher:categoriesEL.search" style="width:200px" />
	</span>
</header>
<table id="categories-dg"></table>
<footer id="categories-win"></footer>
<script>
// 变量取值要唯一
var categoriesEL = {
	add: $("#categories-add"),
	edit: $("#categories-edit"),
	remove: $("#categories-remove"),
	count: $("#categories-count"),
	reload: $("#categories-reload"),
	dg: $("#categories-dg"),
	win: $("#categories-win")
};

// DataGrid
categoriesEL.dg.datagrid({
    url:'/cms/categories/list',
    fitColumns: true,
    border: false,
    idField: "id",
    rownumbers: true,
    pagination: true,
    pageSize: 20,
    pageList:[20, 50, 100],
    header: '#categories-header',
    fit: true,
    columns:[[
        {field:'id', checkbox: true},
        {field:'name',title:'名称',width:100, sortable: true},
        {field:'title',title:'标题',width:100, sortable: true},
        {field:'description',title:'描述',width:100},
        {field:'avatar',title:'头像',width:100},
        {field:'cover',title:'封面',width:100},
        {field:'parent',title:'父节点',width:100, sortable: true},
        {field:'product',title:'产品数',width:100, sortable: true},
        {field:'topic',title:'主题数',width:100, sortable: true},
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
        {field:'lastModified',title:'更新时间',width:100, sortable: true, formatter: function(value,row,index) {
        	return new Date(value).format();  
        }},
        {field:'creator',title:'创建人',width:100, sortable: true}
    ]],
 	// 当选择一行时触发
    onSelect: function(index,row) {
    	categoriesEL.reset();
    },
 	// 当取消选择一行时触发
    onUnselect: function(index,row) {
    	categoriesEL.reset();
    },
 	// 当全选时触发
    onSelectAll: function(rows) {
    	categoriesEL.reset();
    },
 	// 当取消全选时触发
    onUnselectAll: function(rows) {
    	categoriesEL.reset();
    },
    // 双击查看
    onDblClickRow: function(index,row) {
    	CMS.viewHandler("/cms/categories/" + row.id);
    }
});

// 根据选择记录触发: 重置按钮状态
categoriesEL.reset = function() {
	var length = categoriesEL.dg.datagrid("getSelections").length;
	if (length == 0) { 
		/* 编辑和删除按钮 禁用 */
		categoriesEL.edit.linkbutton({disabled: true});
		categoriesEL.remove.linkbutton({disabled: true});
	} else if (length == 1) { 
		/* 编辑和删除按钮 启用 */
		categoriesEL.edit.linkbutton({disabled: false});
		categoriesEL.remove.linkbutton({disabled: false});
	} else {
		/* 编辑和删除按钮 禁用 */
		categoriesEL.edit.linkbutton({disabled: true});
		categoriesEL.remove.linkbutton({disabled: true});
	}
}

// 搜索
categoriesEL.search = function(value){
	categoriesEL.dg.datagrid('load',{
		title: value, 
		state: $('#categories-state').combobox('getValue')
	});
}

// 新建
categoriesEL.add.click(function() {
	categoriesEL.win.window({
		width: 480,
		height: 580,
		modal: true,
		title: '新建类别',
		collapsible: false,
		minimizable: false,
		maximizable: false,
		href: '/cms/categories/new',
		method: 'get',
		cache: false
	});
});

// 编辑
categoriesEL.edit.click(function() {
	var row = categoriesEL.dg.datagrid('getSelected');
	if (row) {
		categoriesEL.win.window({
			width: 480,
			height: 580,
			modal: true,
			title: '编辑类别',
			collapsible: false,
			minimizable: false,
			maximizable: false,
			href: '/cms/categories/' + row.id + '/edit',
			method: 'get',
			cache: false
		});
	}
});

// 删除
categoriesEL.remove.click(function() {
	CMS.removeSubmitHandler(categoriesEL, 'categories');
});

// 更新统计
categoriesEL.count.click(function() {
	CMS.countSubmitHandler(categoriesEL, 'categories');
});

// 重载
categoriesEL.reload.click(function() {
	categoriesEL.dg.datagrid('clearSelections');
	categoriesEL.dg.datagrid('reload',{});
});
</script>
</body>
</html>