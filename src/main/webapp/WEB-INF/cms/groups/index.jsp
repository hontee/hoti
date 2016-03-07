<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../_header.jsp" %>
<title>群组</title>
</head>
<body>
<header id="groups-header" class="cms-dg-header">
	<button id="groups-add" class="easyui-linkbutton" data-options="iconCls:'icon-add'">新建</button>
	<button id="groups-edit" class="easyui-linkbutton" data-options="iconCls:'icon-edit',disabled:true">编辑</button>
	<button id="groups-remove" class="easyui-linkbutton" data-options="iconCls:'icon-remove',disabled:true">删除</button>
	<button id="groups-reload" class="easyui-linkbutton" data-options="iconCls:'icon-reload'">刷新</button>
	
	<span class="cms-dg-search">
	  <input class="easyui-searchbox" data-options="prompt:'输入标题', searcher:groupsEL.search" style="width:200px" />
	</span>
</header>
<table id="groups-dg"></table>
<footer>
    <div id="groups-add-win"></div>
    <div id="groups-edit-win"></div>
</footer>
<script>
// 变量取值要唯一
var groupsEL = {
	add: $("#groups-add"),
	edit: $("#groups-edit"),
	remove: $("#groups-remove"),
	reload: $("#groups-reload"),
	dg: $("#groups-dg"),
	addWin: $("#groups-add-win"),
	editWin: $("#groups-edit-win")
};

// DataGrid
groupsEL.dg.datagrid({
    url:'/cms/groups/list',
    fitColumns: true,
    border: false,
    idField: "id",
    rownumbers: true,
    pagination: true,
    pageSize: 20,
    pageList:[20, 50, 100],
    title:'群组',
    header: '#groups-header',
    fit: true,
    columns:[[
        {field:'id', checkbox: true},
        {field:'name',title:'名称',width:100, sortable: true},
        {field:'title',title:'标题',width:100, sortable: true},
        {field:'description',title:'描述',width:100},
        {field:'gtype',title:'类型',width:100, sortable: true},
        {field:'avatar',title:'头像',width:100},
        {field:'cover',title:'封面',width:100},
        {field:'stars',title:'关注',width:100, sortable: true},
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
        {field:'createBy',title:'创建人',width:100, sortable: true, formatter: function(value,row,index) {
        	return row.extCreateName;
        }},
        {field:'extCategoryTitle',title:'分类标题', hidden: true},
        {field:'extCreateName',title:'用户名', hidden: true},
        {field:'mtype',title:'媒体类型',width:100, sortable: true},
        {field:'category',title:'所属分类',width:100, sortable: true, formatter: function(value,row,index) {
        	return row.extCategoryTitle;
        }}
    ]],
 	// 当选择一行时触发
    onSelect: function(index,row) {
    	groupsEL.reset();
    },
 	// 当取消选择一行时触发
    onUnselect: function(index,row) {
    	groupsEL.reset();
    },
 	// 当全选时触发
    onSelectAll: function(rows) {
    	groupsEL.reset();
    },
 	// 当取消全选时触发
    onUnselectAll: function(rows) {
    	groupsEL.reset();
    },
    // 双击查看
    onDblClickRow: function(index,row) {
    	CMS.viewHandler("/cms/groups/" + row.id);
    }
});

// 根据选择记录触发: 重置按钮状态
groupsEL.reset = function() {
	var length = groupsEL.dg.datagrid("getSelections").length;
	if (length == 0) { // 全部禁用
		groupsEL.linkButton(true, true, true);
	} else if (length == 1) { // 可编辑和删除
		groupsEL.linkButton(false, false, true);
	} else { // 可批量操作
		groupsEL.linkButton(true, true, false);
	}
}

// 设置按钮是否可用
groupsEL.linkButton = function(a, b, c) {
	groupsEL.edit.linkbutton({disabled: a});
	groupsEL.remove.linkbutton({disabled: b});
}

// 搜索
groupsEL.search = function(value){
	groupsEL.dg.datagrid('load',{
		title: value
	});
}

// 新建
groupsEL.add.click(function() {
	groupsEL.addWin.window({
		width: 480,
		height: 650,
		modal: true,
		title: '新建群组',
		collapsible: false,
		minimizable: false,
		maximizable: false,
		href: '/cms/groups/new',
		method: 'get',
		cache: false
	});
});

// 编辑
groupsEL.edit.click(function() {
	var row = groupsEL.dg.datagrid('getSelected');
	if (row) {
		groupsEL.editWin.window({
			width: 480,
			height: 650,
			modal: true,
			title: '编辑群组',
			collapsible: false,
			minimizable: false,
			maximizable: false,
			href: '/cms/groups/' + row.id + '/edit',
			method: 'get',
			cache: false
		});
	}
});

// 删除
groupsEL.remove.click(function() {
	CMS.removeSubmitHandler(groupsEL, 'groups');
});

// 重载
groupsEL.reload.click(function() {
	groupsEL.dg.datagrid('reload',{});
});
</script>
</body>
</html>