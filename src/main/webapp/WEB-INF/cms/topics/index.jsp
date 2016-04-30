<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../_header.jsp" %>
<title>主题管理</title>
</head>
<body>
<header id="groups-header" class="cms-dg-header">
	<button id="groups-add" class="easyui-linkbutton" data-options="iconCls:'icon-add'">新建</button>
	<button id="groups-edit" class="easyui-linkbutton" data-options="iconCls:'icon-edit',disabled:true">编辑</button>
	<button id="groups-bookmarks" class="easyui-linkbutton" data-options="iconCls:'icon-add',disabled:true">添加产品</button>
	<button id="groups-manager" class="easyui-linkbutton" data-options="iconCls:'icon-edit',disabled:true">管理产品</button>
	<button id="groups-remove" class="easyui-linkbutton" data-options="iconCls:'icon-remove',disabled:true">删除</button>
	<button id="groups-follow" class="easyui-linkbutton" data-options="iconCls:'icon-tip',disabled:true">关注</button>
	<button id="groups-pick" class="easyui-linkbutton" data-options="iconCls:'icon-add',disabled:true">精选</button>
	<button id="groups-unpick" class="easyui-linkbutton" data-options="iconCls:'icon-remove',disabled:true">取消精选</button>
	<button id="groups-reload" class="easyui-linkbutton" data-options="iconCls:'icon-reload'">刷新</button>
	
	<span class="cms-dg-search">
	  <select class="easyui-combobox" id="groups-type" data-options="panelHeight:'auto',editable: false" style="width:100px;">
        <option value="all" selected>全部类型</option>
        <option value="user">用户主题</option>
        <option value="topic">官方主题</option>
        <option value="org">组织主题</option>
      </select>
	  <select class="easyui-combobox" id="groups-state" data-options="panelHeight:'auto',editable: false" style="width:100px;">
        <option value="-1" selected>全部状态</option>
        <option value="1">启用</option>
        <option value="0">禁用</option>
      </select>
	  <input class="easyui-searchbox" data-options="prompt:'输入标题', searcher:groupsEL.search" style="width:200px" />
	</span>
</header>
<table id="groups-dg"></table>
<footer id="groups-win"></footer>
<script>
// 变量取值要唯一
var groupsEL = {
	add: $("#groups-add"),
	edit: $("#groups-edit"),
	remove: $("#groups-remove"),
	bookmarks: $("#groups-bookmarks"),
	manager: $("#groups-manager"),
	follow: $("#groups-follow"),
	pick: $("#groups-pick"),
	unpick: $("#groups-unpick"),
	reload: $("#groups-reload"),
	dg: $("#groups-dg"),
	win: $("#groups-win")
};

// DataGrid
groupsEL.dg.datagrid({
    url:'/cms/topics/list',
    fitColumns: true,
    border: false,
    idField: "id",
    rownumbers: true,
    pagination: true,
    pageSize: 20,
    pageList:[20, 50, 100],
    header: '#groups-header',
    fit: true,
    columns:[[
        {field:'id', checkbox: true},
        {field:'name',title:'名称',width:100, hidden: true},
        {field:'title',title:'标题',width:100, sortable: true},
        {field:'description',title:'描述',width:100},
        {field:'avatar',title:'头像',width:100},
        {field:'cover',title:'封面',width:100},
        {field:'star',title:'关注数',width:100, sortable: true},
        {field:'product',title:'产品数',width:100, sortable: true},
        {field:'audit',title:'审核',width:100, sortable: true, formatter: function(value,row,index) {
        	if (value == '1') {
				return '已认证';
			} else {
				return '未认证';
			}
        }},
        {field:'pick',title:'精选',width:100, sortable: true, formatter: function(value,row,index) {
        	if (value == '1') {
				return '是';
			} else {
				return '否';
			}
        }},
        {field:'type',title:'类型',width:100, sortable: true, formatter: function(value,row,index) {
        	if (value == '1') {
				return '用户主题';
			} else if(value == '2') {
				return '官方主题';
			} else if(value == '3') {
				return '组织主题';
			}
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
        	return row.creator;
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
    	CMS.viewHandler("/cms/topics/" + row.id);
    }
});

// 根据选择记录触发: 重置按钮状态
groupsEL.reset = function() {
	var length = groupsEL.dg.datagrid("getSelections").length;
	if (length == 0) { // 全部禁用
		groupsEL.edit.linkbutton({disabled: true});
		groupsEL.bookmarks.linkbutton({disabled: true});
		groupsEL.manager.linkbutton({disabled: true});
		groupsEL.follow.linkbutton({disabled: true});
		groupsEL.remove.linkbutton({disabled: true});
		groupsEL.pick.linkbutton({disabled: true});
		groupsEL.unpick.linkbutton({disabled: true});
	} else if (length == 1) { // 可编辑和删除
		groupsEL.edit.linkbutton({disabled: false});
		groupsEL.bookmarks.linkbutton({disabled: false});
		groupsEL.manager.linkbutton({disabled: false});
		groupsEL.follow.linkbutton({disabled: false});
		groupsEL.remove.linkbutton({disabled: false});
		groupsEL.pick.linkbutton({disabled: false});
		groupsEL.unpick.linkbutton({disabled: false});
	} else { // 可批量操作
		groupsEL.edit.linkbutton({disabled: true});
		groupsEL.bookmarks.linkbutton({disabled: true});
		groupsEL.manager.linkbutton({disabled: true});
		groupsEL.follow.linkbutton({disabled: true});
		groupsEL.remove.linkbutton({disabled: true});
		groupsEL.pick.linkbutton({disabled: false});
		groupsEL.unpick.linkbutton({disabled: false});
	}
}

// 搜索
groupsEL.search = function(value){
	groupsEL.dg.datagrid('load',{
		title: value,
		state: $('#groups-state').combobox('getValue'),
		type: $('#groups-type').combobox('getValue')
	});
}

// 新建
groupsEL.add.click(function() {
	groupsEL.win.window({
		width: 480,
		height: 500,
		modal: true,
		title: '新建主题',
		collapsible: false,
		minimizable: false,
		maximizable: false,
		href: '/cms/topics/new',
		method: 'get',
		cache: false
	});
});

// 编辑
groupsEL.edit.click(function() {
	var row = groupsEL.dg.datagrid('getSelected');
	if (row) {
		groupsEL.win.window({
			width: 480,
			height: 500,
			modal: true,
			title: '编辑主题',
			collapsible: false,
			minimizable: false,
			maximizable: false,
			href: '/cms/topics/' + row.id + '/edit',
			method: 'get',
			cache: false
		});
	}
});

// 删除
groupsEL.remove.click(function() {
	CMS.removeSubmitHandler(groupsEL, 'topics');
});

//精选
groupsEL.pick.click(function() {
	CMS.pickSubmitHandler(groupsEL, 'topics');
});

// 取消精选
groupsEL.unpick.click(function() {
	CMS.unpickSubmitHandler(groupsEL, 'topics');
});

// 添加产品
groupsEL.bookmarks.click(function() {
	var row = groupsEL.dg.datagrid('getSelected');
	if (row) {
		groupsEL.win.window({
			width: 980,
			height: 640,
			modal: true,
			title: '添加产品',
			collapsible: false,
			minimizable: false,
			maximizable: false,
			href: '/cms/topics/' + row.id + '/product',
			method: 'get',
			cache: false
		});
	}
});

// 管理产品
groupsEL.manager.click(function() {
	var row = groupsEL.dg.datagrid('getSelected');
	if (row) {
		groupsEL.win.window({
			width: 980,
			height: 640,
			modal: true,
			title: '管理产品',
			collapsible: false,
			minimizable: false,
			maximizable: false,
			href: '/cms/topics/' + row.id + '/manager',
			method: 'get',
			cache: false
		});
	}
});

// 关注
groupsEL.follow.click(function() {
	var row = groupsEL.dg.datagrid('getSelected');
	if (row) {
		groupsEL.win.window({
			width: 980,
			height: 600,
			modal: true,
			title: '用户列表',
			collapsible: false,
			minimizable: false,
			maximizable: false,
			href: '/cms/topics/' + row.id + '/follow',
			method: 'get',
			cache: false
		});
	}
});

// 重载
groupsEL.reload.click(function() {
	groupsEL.dg.datagrid('clearSelections');
	groupsEL.dg.datagrid('reload',{});
});
</script>
</body>
</html>