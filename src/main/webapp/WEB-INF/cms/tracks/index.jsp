<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../_header.jsp" %>
<title>异常管理</title>
</head>
<body>
<header id="tracks-header" class="cms-dg-header">
	<button id="tracks-remove" class="easyui-linkbutton" data-options="iconCls:'icon-remove',disabled:true">删除</button>
	<button id="tracks-remove-batch" class="easyui-linkbutton" data-options="iconCls:'icon-remove',disabled:true">批量删除</button>
	<button id="tracks-reload" class="easyui-linkbutton" data-options="iconCls:'icon-reload'">刷新</button>
	
	<span class="cms-dg-search">
	  <input class="easyui-searchbox" data-options="prompt:'异常类型', searcher:tracksEL.search" style="width:200px" />
	</span>
</header>
<table id="tracks-dg"></table>
<script>
// 变量取值要唯一
var tracksEL = {
	remove: $("#tracks-remove"),
	batchRemove: $("#tracks-remove-batch"),
	reload: $("#tracks-reload"),
	dg: $("#tracks-dg")
};

// DataGrid
tracksEL.dg.datagrid({
    url:'/cms/tracks/list',
    fitColumns: true,
    border: false,
    idField: "id",
    rownumbers: true,
    pagination: true,
    pageSize: 20,
    pageList:[20, 50, 100],
    header: '#tracks-header',
    fit: true,
    columns:[[
        {field:'id', checkbox: true},
        {field:'name',title:'异常类型',width:200},
        {field:'description',title:'异常信息',width:200},
        {field:'created',title:'发生时间',width:100, sortable: true, formatter: function(value,row,index) {
        	return new Date(value).format();  
        }}
    ]],
 	// 当选择一行时触发
    onSelect: function(index,row) {
    	tracksEL.reset();
    },
 	// 当取消选择一行时触发
    onUnselect: function(index,row) {
    	tracksEL.reset();
    },
 	// 当全选时触发
    onSelectAll: function(rows) {
    	tracksEL.reset();
    },
 	// 当取消全选时触发
    onUnselectAll: function(rows) {
    	tracksEL.reset();
    },
    // 双击查看
    onDblClickRow: function(index,row) {
    	CMS.viewHandler("/cms/tracks/" + row.id);
    }
});

// 根据选择记录触发: 重置按钮状态
tracksEL.reset = function() {
	var length = tracksEL.dg.datagrid("getSelections").length;
	if (length == 0) { // 全部禁用
		tracksEL.linkButton(true, true, true);
	} else if (length == 1) { // 可编辑和删除
		tracksEL.linkButton(false, false, true);
	} else { // 可批量操作
		tracksEL.linkButton(true, true, false);
	}
}

// 设置按钮是否可用
tracksEL.linkButton = function(a, b, c) {
	tracksEL.remove.linkbutton({disabled: b});
	tracksEL.batchRemove.linkbutton({disabled: c});
}

// 搜索
tracksEL.search = function(value){
	tracksEL.dg.datagrid('load',{
		name: value
	});
}

// 删除
tracksEL.remove.click(function() {
	CMS.removeSubmitHandler(tracksEL, 'tracks');
});

//批量删除
tracksEL.batchRemove.click(function() {
	CMS.batchDeleteSubmitHandler(tracksEL, 'tracks');
});

// 重载
tracksEL.reload.click(function() {
	tracksEL.dg.datagrid('clearSelections');
	tracksEL.dg.datagrid('reload',{});
});
</script>
</body>
</html>