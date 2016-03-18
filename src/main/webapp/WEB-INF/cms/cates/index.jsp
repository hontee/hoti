<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../_header.jsp" %>
<title>分类管理</title>
</head>
<body>
<header id="cates-header" class="cms-dg-header">
	<button id="cates-add" class="easyui-linkbutton" data-options="iconCls:'icon-add'">新建</button>
	<button id="cates-edit" class="easyui-linkbutton" data-options="iconCls:'icon-edit',disabled:true">编辑</button>
	<button id="cates-remove" class="easyui-linkbutton" data-options="iconCls:'icon-remove',disabled:true">删除</button>
	<button id="cates-count" class="easyui-linkbutton" data-options="iconCls:'icon-edit'">更新统计</button>
	<button id="cates-reload" class="easyui-linkbutton" data-options="iconCls:'icon-reload'">刷新</button>
	
	<span class="cms-dg-search">
	  <input class="easyui-searchbox" data-options="prompt:'输入标题', searcher:catesEL.search" style="width:200px" />
	</span>
</header>
<table id="cates-dg"></table>
<footer id="cates-win"></footer>
<script>
// 变量取值要唯一
var catesEL = {
	add: $("#cates-add"),
	edit: $("#cates-edit"),
	remove: $("#cates-remove"),
	count: $("#cates-count"),
	reload: $("#cates-reload"),
	dg: $("#cates-dg"),
	win: $("#cates-win")
};

// DataGrid
catesEL.dg.datagrid({
    url:'/cms/cates/list',
    fitColumns: true,
    border: false,
    idField: "id",
    rownumbers: true,
    pagination: true,
    pageSize: 20,
    pageList:[20, 50, 100],
    header: '#cates-header',
    fit: true,
    columns:[[
        {field:'id', checkbox: true},
        {field:'name',title:'名称',width:100, sortable: true},
        {field:'title',title:'标题',width:100, sortable: true},
        {field:'description',title:'描述',width:100},
        {field:'avatar',title:'头像',width:100},
        {field:'cover',title:'封面',width:100},
        {field:'count',title:'计数',width:100, sortable: true},
        {field:'domain',title:'业务领域',width:100, sortable: true, formatter: function(value,row,index) {
        	return row.extDomainTitle;
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
        {field:'lastModified',title:'最后更新时间',width:100, sortable: true, formatter: function(value,row,index) {
        	return new Date(value).format();  
        }},
        {field:'createBy',title:'创建人',width:100, sortable: true, formatter: function(value,row,index) {
        	return row.extCreateName;
        }},
        {field:'extDomainTitle',title:'业务领域', hidden: true},
        {field:'extCreateName',title:'用户名', hidden: true},
    ]],
 	// 当选择一行时触发
    onSelect: function(index,row) {
    	catesEL.reset();
    },
 	// 当取消选择一行时触发
    onUnselect: function(index,row) {
    	catesEL.reset();
    },
 	// 当全选时触发
    onSelectAll: function(rows) {
    	catesEL.reset();
    },
 	// 当取消全选时触发
    onUnselectAll: function(rows) {
    	catesEL.reset();
    },
    // 双击查看
    onDblClickRow: function(index,row) {
    	CMS.viewHandler("/cms/cates/" + row.id);
    }
});

// 根据选择记录触发: 重置按钮状态
catesEL.reset = function() {
	var length = catesEL.dg.datagrid("getSelections").length;
	if (length == 0) { // 全部禁用
		catesEL.linkButton(true, true, true);
	} else if (length == 1) { // 可编辑和删除
		catesEL.linkButton(false, false, true);
	} else { // 可批量操作
		catesEL.linkButton(true, true, false);
	}
}

// 设置按钮是否可用
catesEL.linkButton = function(a, b, c) {
	catesEL.edit.linkbutton({disabled: a});
	catesEL.remove.linkbutton({disabled: b});
}

// 搜索
catesEL.search = function(value){
	catesEL.dg.datagrid('load',{
		title: value
	});
}

// 新建
catesEL.add.click(function() {
	catesEL.win.window({
		width: 480,
		height: 500,
		modal: true,
		title: '新建分类',
		collapsible: false,
		minimizable: false,
		maximizable: false,
		href: '/cms/cates/new',
		method: 'get',
		cache: false
	});
});

// 编辑
catesEL.edit.click(function() {
	var row = catesEL.dg.datagrid('getSelected');
	if (row) {
		catesEL.win.window({
			width: 480,
			height: 500,
			modal: true,
			title: '编辑分类',
			collapsible: false,
			minimizable: false,
			maximizable: false,
			href: '/cms/cates/' + row.id + '/edit',
			method: 'get',
			cache: false
		});
	}
});

// 删除
catesEL.remove.click(function() {
	CMS.removeSubmitHandler(catesEL, 'cates');
});

// 更新统计
catesEL.count.click(function() {
	CMS.countSubmitHandler(catesEL, 'cates');
});

// 重载
catesEL.reload.click(function() {
	catesEL.dg.datagrid('reload',{});
});
</script>
</body>
</html>