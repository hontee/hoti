<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../_header.jsp" %>
<title>群组用户</title>
</head>
<body>
<header id="groups-users-header" class="cms-dg-header">
	<button id="groups-users-reload" class="easyui-linkbutton" data-options="iconCls:'icon-reload'">刷新</button>
	
	<span class="cms-dg-search">
	  <select class="easyui-combobox" id="groups-users-type" data-options="panelHeight:'auto',editable: false" style="width:100px;">
        <option value="-1" selected>全部类型</option>
        <option value="1">普通用户</option>
        <option value="2">管理员</option>
      </select>
      <select class="easyui-combobox" id="groups-users-state" data-options="panelHeight:'auto',editable: false" style="width:100px;">
        <option value="-1" selected>全部状态</option>
        <option value="0">禁用</option>
        <option value="1">启用</option>
        <option value="2">锁定</option>
        <option value="3">已删除</option>
      </select>
	  <input class="easyui-searchbox" data-options="prompt:'搜索用户名', searcher:groupsUsersEL.search" style="width:200px" />
	</span>
</header>
<table id="groups-users-dg"></table>
<footer id="groups-users-win"></footer>
<script>
// 变量取值要唯一
var groupsUsersEL = {
	reload: $("#groups-users-reload"),
	dg: $("#groups-users-dg"),
	win: $("#groups-users-win")
};

// DataGrid
groupsUsersEL.dg.datagrid({
    url:'/cms/groups/${id}/follows',
    fitColumns: true,
    border: false,
    idField: "id",
    rownumbers: true,
    pagination: true,
    pageSize: 20,
    pageList:[20, 50, 100],
    header: '#groups-users-header',
    fit: true,
    columns:[[
        {field: 'id', checkbox: true},
        {field:'name',title:'用户名',width:100, sortable: true},
        {field:'title',title:'昵称',width:100, sortable: true},
        {field:'description',title:'签名',width:200},
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
        {field:'created',title:'关注时间',width:60, sortable: true, formatter: function(value,row,index) {
        	return new Date(value).format();
        }}
    ]]
});

// 搜索
groupsUsersEL.search = function(value){
	groupsUsersEL.dg.datagrid('load',{
		name: value,
		userType: $('#groups-users-type').combobox('getValue'),
		state: $('#groups-users-state').combobox('getValue')
	});
}

// 重载
groupsUsersEL.reload.click(function() {
	groupsUsersEL.dg.datagrid('clearSelections');
	groupsUsersEL.dg.datagrid('reload',{});
});
</script>
</body>
</html>