<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../_header.jsp" %>
<title>群组管理</title>
</head>
<body>
<header id="groups-header" class="cms-dg-header">
	<button id="groups-add" class="easyui-linkbutton" data-options="iconCls:'icon-add'">新建</button>
	<button id="groups-edit" class="easyui-linkbutton" data-options="iconCls:'icon-edit',disabled:true">编辑</button>
	<button id="groups-bookmarks" class="easyui-linkbutton" data-options="iconCls:'icon-add',disabled:true">添加站点</button>
	<button id="groups-manager" class="easyui-linkbutton" data-options="iconCls:'icon-edit',disabled:true">管理站点</button>
	<button id="groups-remove" class="easyui-linkbutton" data-options="iconCls:'icon-remove',disabled:true">删除</button>
	<button id="groups-follow" class="easyui-linkbutton" data-options="iconCls:'icon-tip',disabled:true">关注</button>
	<button id="groups-pick" class="easyui-linkbutton" data-options="iconCls:'icon-add',disabled:true">精选</button>
	<button id="groups-unpick" class="easyui-linkbutton" data-options="iconCls:'icon-remove',disabled:true">取消精选</button>
	<button id="groups-count" class="easyui-linkbutton" data-options="iconCls:'icon-reload'">更新统计</button>
	<button id="groups-reload" class="easyui-linkbutton" data-options="iconCls:'icon-reload'">刷新</button>
	
	<span class="cms-dg-search">
	  <input class="easyui-combobox" id="groups-category"
    	data-options="required:true, value:'-1', valueField:'id',textField:'title',url:'/cms/cates/datalist?q=all'" 
    	style="width:100px;">
	  <select class="easyui-combobox" id="groups-gtype" data-options="panelHeight:'auto',editable: false" style="width:100px;">
        <option value="all" selected>全部机构</option>
        <option value="user">用户</option>
        <option value="topic">主题</option>
        <option value="org">组织</option>
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
	count: $("#groups-count"),
	reload: $("#groups-reload"),
	dg: $("#groups-dg"),
	win: $("#groups-win")
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
    header: '#groups-header',
    fit: true,
    columns:[[
        {field:'id', checkbox: true},
        {field:'name',title:'名称',width:100, hidden: true},
        {field:'title',title:'标题',width:100, sortable: true},
        {field:'description',title:'描述',width:100},
        {field:'gtype',title:'机构',width:100, sortable: true},
        {field:'avatar',title:'头像',width:100},
        {field:'cover',title:'封面',width:100},
        {field:'star',title:'关注',width:100, sortable: true},
        {field:'count',title:'计数',width:100, sortable: true},
        {field:'state',title:'状态',width:100, sortable: true, formatter: function(value,row,index) {
        	if (value == '1') {
				return '启用';
			} else {
				return '禁用';
			}
        }},
        {field:'pick',title:'精选',width:100, sortable: true, formatter: function(value,row,index) {
        	if (value == '1') {
				return '是';
			} else {
				return '否';
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
        }},
        {field:'category',title:'所属分类',width:100, sortable: true, formatter: function(value,row,index) {
        	return row.cateTitle;
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
		groupsEL.pick.linkbutton({disabled: false});
		groupsEL.unpick.linkbutton({disabled: false});
	} else { // 可批量操作
		groupsEL.linkButton(true, true, false);
		groupsEL.pick.linkbutton({disabled: false});
		groupsEL.unpick.linkbutton({disabled: false});
	}
}

// 设置按钮是否可用
groupsEL.linkButton = function(a, b, c) {
	groupsEL.edit.linkbutton({disabled: a});
	groupsEL.bookmarks.linkbutton({disabled: a});
	groupsEL.manager.linkbutton({disabled: a});
	groupsEL.follow.linkbutton({disabled: a});
	groupsEL.remove.linkbutton({disabled: b});
}

// 搜索
groupsEL.search = function(value){
	groupsEL.dg.datagrid('load',{
		title: value,
		state: $('#groups-state').combobox('getValue'),
		gtype: $('#groups-gtype').combobox('getValue'),
		category: $('#groups-category').combobox('getValue')
	});
}

// 新建
groupsEL.add.click(function() {
	groupsEL.win.window({
		width: 480,
		height: 500,
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
		groupsEL.win.window({
			width: 480,
			height: 500,
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

//精选
groupsEL.pick.click(function() {
	CMS.pickSubmitHandler(groupsEL, 'groups');
});

// 取消精选
groupsEL.unpick.click(function() {
	CMS.unpickSubmitHandler(groupsEL, 'groups');
});

// 添加站点
groupsEL.bookmarks.click(function() {
	var row = groupsEL.dg.datagrid('getSelected');
	if (row) {
		groupsEL.win.window({
			width: 980,
			height: 640,
			modal: true,
			title: '添加站点',
			collapsible: false,
			minimizable: false,
			maximizable: false,
			href: '/cms/groups/' + row.id + '/bookmark',
			method: 'get',
			cache: false
		});
	}
});

// 管理站点
groupsEL.manager.click(function() {
	var row = groupsEL.dg.datagrid('getSelected');
	if (row) {
		groupsEL.win.window({
			width: 980,
			height: 640,
			modal: true,
			title: '管理站点',
			collapsible: false,
			minimizable: false,
			maximizable: false,
			href: '/cms/groups/' + row.id + '/manager',
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
			title: '关注',
			collapsible: false,
			minimizable: false,
			maximizable: false,
			href: '/cms/groups/' + row.id + '/follow',
			method: 'get',
			cache: false
		});
	}
});


// 更新统计
groupsEL.count.click(function() {
	CMS.countSubmitHandler(groupsEL, 'groups');
});

// 重载
groupsEL.reload.click(function() {
	groupsEL.dg.datagrid('clearSelections');
	groupsEL.dg.datagrid('reload',{});
});
</script>
</body>
</html>