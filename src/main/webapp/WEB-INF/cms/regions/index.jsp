<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../_header.jsp" %>
<title>城市管理</title>
</head>
<body>
<header id="regions-header" class="cms-dg-header">
	<button id="regions-add" class="easyui-linkbutton" data-options="iconCls:'icon-add'">添加</button>
	<button id="regions-edit" class="easyui-linkbutton" data-options="iconCls:'icon-edit',disabled:true">编辑</button>
	<button id="regions-remove" class="easyui-linkbutton" data-options="iconCls:'icon-remove',disabled:true">删除</button>
	<button id="regions-reload" class="easyui-linkbutton" data-options="iconCls:'icon-reload'">刷新</button>
	
	<span class="cms-dg-search">
	  <select class="easyui-combobox" id="regions-state" data-options="panelHeight:'auto',editable: false" style="width:100px;">
        <option value="-1" selected>全部状态</option>
        <option value="1">启用</option>
        <option value="0">禁用</option>
      </select>
      <select class="easyui-combobox" id="regions-level" data-options="panelHeight:'auto',editable: false" style="width:100px;">
        <option value="-1" selected>全部级别</option>
        <option value="1">省/区/直辖市</option>
        <option value="2">市/自治州</option>
        <option value="3">县/区</option>
      </select>
	  <input class="easyui-searchbox" data-options="prompt:'输入标题', searcher:regionsEL.search" style="width:200px" />
	</span>
</header>
<table id="regions-dg"></table>
<footer id="regions-win"></footer>
<script>
// 变量取值要唯一
var regionsEL = {
	add: $("#regions-add"),
	edit: $("#regions-edit"),
	remove: $("#regions-remove"),
	reload: $("#regions-reload"),
	dg: $("#regions-dg"),
	win: $("#regions-win")
};

// DataGrid
regionsEL.dg.datagrid({
    url:'/cms/regions/list',
    fitColumns: true,
    border: false,
    idField: "id",
    rownumbers: true,
    pagination: true,
    pageSize: 20,
    pageList:[20, 50, 100],
    header: '#regions-header',
    fit: true,
    columns:[[
        {field:'id', checkbox: true},
        {field:'name',title:'名称',width:100, sortable: true},
        {field:'title',title:'标题',width:100, sortable: true},
        {field:'parent',title:'父ID',width:100},
        {field:'code',title:'代码',width:100},
        {field:'level',title:'级别',width:100, sortable: true},
        {field:'weight',title:'权重',width:100, sortable: true},
        {field:'state',title:'状态',width:100, sortable: true, formatter: function(value,row,index) {
        	if (value == '1') {
				return '启用';
			} else {
				return '禁用';
			}
        }}
    ]],
 	// 当选择一行时触发
    onSelect: function(index,row) {
    	regionsEL.reset();
    },
 	// 当取消选择一行时触发
    onUnselect: function(index,row) {
    	regionsEL.reset();
    },
 	// 当全选时触发
    onSelectAll: function(rows) {
    	regionsEL.reset();
    },
 	// 当取消全选时触发
    onUnselectAll: function(rows) {
    	regionsEL.reset();
    },
    // 双击查看
    onDblClickRow: function(index,row) {
    	CMS.viewHandler("/cms/regions/" + row.id);
    }
});

// 根据选择记录触发: 重置按钮状态
regionsEL.reset = function() {
	var length = regionsEL.dg.datagrid("getSelections").length;
	if (length == 0) { // 全部禁用
		regionsEL.linkButton(true, true, true);
	} else if (length == 1) { // 可编辑和删除
		regionsEL.linkButton(false, false, true);
	} else { // 可批量操作
		regionsEL.linkButton(true, true, false);
	}
}

// 设置按钮是否可用
regionsEL.linkButton = function(a, b, c) {
	regionsEL.edit.linkbutton({disabled: a});
	regionsEL.remove.linkbutton({disabled: b});
}

// 搜索
regionsEL.search = function(value){
	regionsEL.dg.datagrid('load',{
		name: value,
		state: $('#regions-state').combobox('getValue'),
		level: $('#regions-level').combobox('getValue')
	});
}

// 新建
regionsEL.add.click(function() {
	regionsEL.win.window({
		width: 480,
		height: 650,
		modal: true,
		title: '添加城市',
		collapsible: false,
		minimizable: false,
		maximizable: false,
		href: '/cms/regions/new',
		method: 'get',
		cache: false
	});
});

// 编辑
regionsEL.edit.click(function() {
	var row = regionsEL.dg.datagrid('getSelected');
	if (row) {
		regionsEL.win.window({
			width: 480,
			height: 650,
			modal: true,
			title: '编辑城市',
			collapsible: false,
			minimizable: false,
			maximizable: false,
			href: '/cms/regions/' + row.id + '/edit',
			method: 'get',
			cache: false
		});
	}
});

// 删除
regionsEL.remove.click(function() {
	CMS.removeSubmitHandler(regionsEL, 'regions');
});

// 重载
regionsEL.reload.click(function() {
	regionsEL.dg.datagrid('clearSelections');
	regionsEL.dg.datagrid('reload',{});
});
</script>
</body>
</html>