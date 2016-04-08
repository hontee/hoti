<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="../_header.jsp" %>
<title>操作日志</title>
</head>
<body>
<header id="activities-header" class="cms-dg-header">
	<button id="activities-remove" class="easyui-linkbutton" data-options="iconCls:'icon-remove',disabled:true">删除</button>
	<button id="activities-remove-batch" class="easyui-linkbutton" data-options="iconCls:'icon-remove',disabled:true">批量删除</button>
	<button id="activities-reload" class="easyui-linkbutton" data-options="iconCls:'icon-reload'">刷新</button>
	
	<span class="cms-dg-search">
	  <select class="easyui-combobox" id="activities-tbl" data-options="panelHeight:'auto',editable: false" style="width:150px;">
        <option selected>全部对象</option>
        <c:forEach items="${records}" var="t">
        <option value="${t}">${t}</option>
        </c:forEach>
      </select>
	  <input class="easyui-searchbox" data-options="prompt:'操作名称', searcher:activitiesEL.search" style="width:200px" />
	</span>
</header>
<table id="activities-dg"></table>
<script>
// 变量取值要唯一
var activitiesEL = {
	remove: $("#activities-remove"),
	batchRemove: $("#activities-remove-batch"),
	reload: $("#activities-reload"),
	dg: $("#activities-dg")
};

// DataGrid
activitiesEL.dg.datagrid({
    url:'/cms/activities/list',
    fitColumns: true,
    border: false,
    idField: "id",
    rownumbers: true,
    pagination: true,
    pageSize: 20,
    pageList:[20, 50, 100],
    title:'操作日志',
    header: '#activities-header',
    fit: true,
    columns:[[
        {field:'id', checkbox: true},
        {field:'name',title:'操作',width:100},
        {field:'description',title:'描述',width:200},
        {field:'tbl',title:'对象',width:100},
        {field:'ip',title:'IP地址',width:100},
        {field:'created',title:'创建时间',width:100, sortable: true, formatter: function(value,row,index) {
        	return new Date(value).format();  
        }},
        {field:'creator',title:'操作人',width:100},
        {field:'userType',title:'用户类型',width:100}
    ]],
 	// 当选择一行时触发
    onSelect: function(index,row) {
    	activitiesEL.reset();
    },
 	// 当取消选择一行时触发
    onUnselect: function(index,row) {
    	activitiesEL.reset();
    },
 	// 当全选时触发
    onSelectAll: function(rows) {
    	activitiesEL.reset();
    },
 	// 当取消全选时触发
    onUnselectAll: function(rows) {
    	activitiesEL.reset();
    },
    // 双击查看
    onDblClickRow: function(index,row) {
    	CMS.viewHandler("/cms/activities/" + row.id);
    }
});

// 根据选择记录触发: 重置按钮状态
activitiesEL.reset = function() {
	var length = activitiesEL.dg.datagrid("getSelections").length;
	if (length == 0) { // 全部禁用
		activitiesEL.linkButton(true, true, true);
	} else if (length == 1) { // 可编辑和删除
		activitiesEL.linkButton(false, false, true);
	} else { // 可批量操作
		activitiesEL.linkButton(true, true, false);
	}
}

// 设置按钮是否可用
activitiesEL.linkButton = function(a, b, c) {
	activitiesEL.remove.linkbutton({disabled: b});
	activitiesEL.batchRemove.linkbutton({disabled: c});
}

// 搜索
activitiesEL.search = function(value){
	activitiesEL.dg.datagrid('load',{
		name: value,
		tbl: $('#activities-tbl').combobox('getValue')
	});
}

// 删除
activitiesEL.remove.click(function() {
	CMS.removeSubmitHandler(activitiesEL, 'activities');
});

// 批量删除
activitiesEL.batchRemove.click(function() {
	CMS.batchDeleteSubmitHandler(activitiesEL, 'activities');
});

// 重载
activitiesEL.reload.click(function() {
	activitiesEL.dg.datagrid('clearSelections');
	activitiesEL.dg.datagrid('reload',{});
});
</script>
</body>
</html>