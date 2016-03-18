<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../_header.jsp" %>
<title>用户管理</title>
</head>
<body>
<header id="users-header" class="cms-dg-header">
	<button id="users-add" class="easyui-linkbutton" data-options="iconCls:'icon-add'">新建</button>
	<button id="users-edit" class="easyui-linkbutton" data-options="iconCls:'icon-edit',disabled:true">编辑</button>
	<button id="users-edit-pw" class="easyui-linkbutton" data-options="iconCls:'icon-edit',disabled:true">修改密码</button>
	<button id="users-remove" class="easyui-linkbutton" data-options="iconCls:'icon-remove',disabled:true">删除</button>
	<button id="users-reload" class="easyui-linkbutton" data-options="iconCls:'icon-reload'">刷新</button>
	
	<span class="cms-dg-search">
	  <input class="easyui-searchbox" data-options="prompt:'搜索用户', searcher:usersEL.search" style="width:200px" />
	</span>
</header>
<table id="users-dg"></table>
<footer>
    <div id="users-add-win"></div>
    <div id="users-edit-win"></div>
</footer>
<script>
// 变量取值要唯一
var usersEL = {
	add: $("#users-add"),
	edit: $("#users-edit"),
	editPw: $("#users-edit-pw"),
	remove: $("#users-remove"),
	reload: $("#users-reload"),
	dg: $("#users-dg"),
	addWin: $("#users-add-win"),
	editWin: $("#users-edit-win")
};

// DataGrid
usersEL.dg.datagrid({
    url:'/cms/users/list',
    fitColumns: true,
    border: false,
    idField: "id",
    rownumbers: true,
    pagination: true,
    pageSize: 20,
    pageList:[20, 50, 100],
    header: '#users-header',
    fit: true,
    columns:[[
        {field: 'id', checkbox: true},
        {field:'name',title:'用户名',width:100, sortable: true},
        {field:'title',title:'昵称',width:100, sortable: true},
        {field:'description',title:'签名',width:200},
        {field:'password',title:'密码',width:100, hidden: true},
        {field:'salt',title:'盐',width:100, hidden: true},
        {field:'userType',title:'用户类型',width:60, sortable: true, formatter: function(value,row,index) {
        	if (value == '2') {
				return '管理员';
			} else {
				return '普通用户';
			}
        }},
        {field:'state',title:'状态',width:60, sortable: true, formatter: function(value,row,index) {
        	if (value == '1') {
				return '启用';
			} else if (value == '2') {
				return '已锁定';
			} else if (value == '3') {
				return '已删除';
			} else {
				return '禁用';
			}
        }},
        {field:'created',title:'创建时间',width:60, sortable: true, formatter: function(value,row,index) {
        	return new Date(value).format();  
        }},
        {field:'lastModified',title:'最后更新时间',width:60, sortable: true, formatter: function(value,row,index) {
        	return new Date(value).format();  
        }}
    ]],
 	// 当选择一行时触发
    onSelect: function(index,row) {
    	usersEL.reset();
    },
 	// 当取消选择一行时触发
    onUnselect: function(index,row) {
    	usersEL.reset();
    },
 	// 当全选时触发
    onSelectAll: function(rows) {
    	usersEL.reset();
    },
 	// 当取消全选时触发
    onUnselectAll: function(rows) {
    	usersEL.reset();
    },
    // 双击查看
    onDblClickRow: function(index,row) {
    	CMS.viewHandler("/cms/users/" + row.id);
    }
});

// 根据选择记录触发: 重置按钮状态
usersEL.reset = function() {
	var length = usersEL.dg.datagrid("getSelections").length;
	if (length == 0) { // 全部禁用
		usersEL.linkButton(true, true, true);
	} else if (length == 1) { // 可编辑和删除
		usersEL.linkButton(false, false, true);
	} else { // 可批量操作
		usersEL.linkButton(true, true, false);
	}
}

// 设置按钮是否可用
usersEL.linkButton = function(a, b, c) {
	usersEL.edit.linkbutton({disabled: a});
	usersEL.editPw.linkbutton({disabled: a});
	usersEL.remove.linkbutton({disabled: b});
}

// 搜索
usersEL.search = function(value){
	usersEL.dg.datagrid('load',{
		title: value
	});
}

// 新建
usersEL.add.click(function() {
	usersEL.addWin.window({
		width: 480,
		height: 420,
		modal: true,
		title: '新建用户',
		collapsible: false,
		minimizable: false,
		maximizable: false,
		href: '/cms/users/new',
		method: 'get',
		cache: false
	});
});

// 编辑
usersEL.edit.click(function() {
	var row = usersEL.dg.datagrid('getSelected');
	if (row) {
		usersEL.editWin.window({
			width: 480,
			height: 520,
			modal: true,
			title: '编辑用户',
			collapsible: false,
			minimizable: false,
			maximizable: false,
			href: '/cms/users/' + row.id + '/edit',
			method: 'get',
			cache: false
		});
	}
});

// 修改密码
usersEL.editPw.click(function() {
	var row = usersEL.dg.datagrid('getSelected');
	if (row) {
		usersEL.editWin.window({
			width: 480,
			height: 280,
			modal: true,
			title: '修改密码',
			collapsible: false,
			minimizable: false,
			maximizable: false,
			href: '/cms/users/' + row.id + '/password',
			method: 'get',
			cache: false
		});
	}
});

// 删除
usersEL.remove.click(function() {
	CMS.removeSubmitHandler(usersEL, 'users');
});

// 重载
usersEL.reload.click(function() {
	usersEL.dg.datagrid('reload',{});
});
</script>
</body>
</html>