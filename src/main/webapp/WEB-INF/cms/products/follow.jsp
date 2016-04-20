<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../_header.jsp" %>
<title>关注产品的用户列表</title>
</head>
<body>
<header id="products-users-header" class="cms-dg-header">
	<button id="products-users-reload" class="easyui-linkbutton" data-options="iconCls:'icon-reload'">刷新</button>
	
	<span class="cms-dg-search">
	  <select class="easyui-combobox" id="products-users-type" data-options="panelHeight:'auto',editable: false" style="width:100px;">
        <option value="-1" selected>全部类型</option>
        <option value="1">普通用户</option>
        <option value="2">管理员</option>
      </select>
      <select class="easyui-combobox" id="products-users-state" data-options="panelHeight:'auto',editable: false" style="width:100px;">
        <option value="-1" selected>全部状态</option>
        <option value="0">禁用</option>
        <option value="1">启用</option>
        <option value="2">锁定</option>
        <option value="3">已删除</option>
      </select>
	  <input class="easyui-searchbox" data-options="prompt:'搜索用户名', searcher:productsUsersEL.search" style="width:200px" />
	</span>
</header>
<table id="products-users-dg"></table>
<footer id="products-users-win"></footer>
<script>
// 变量取值要唯一
var productsUsersEL = {
	reload: $("#products-users-reload"),
	dg: $("#products-users-dg"),
	win: $("#products-users-win")
};

// DataGrid
productsUsersEL.dg.datagrid({
    url:'/cms/products/${id}/users',
    fitColumns: true,
    border: false,
    idField: "id",
    rownumbers: true,
    pagination: true,
    pageSize: 20,
    pageList:[20, 50, 100],
    header: '#products-users-header',
    fit: true,
    columns:[[
        {field: 'id', checkbox: true},
        {field:'name',title:'用户名',width:100, sortable: true},
        {field:'title',title:'昵称',width:100, sortable: true},
        {field:'type',title:'用户类型',width:60, sortable: true, formatter: function(value,row,index) {
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
        {field:'ftime',title:'关注时间',width:60, sortable: true, formatter: function(value,row,index) {
        	return new Date(value).format();
        }}
    ]]
});

// 搜索
productsUsersEL.search = function(value){
	productsUsersEL.dg.datagrid('load',{
		name: value,
		type: $('#products-users-type').combobox('getValue'),
		state: $('#products-users-state').combobox('getValue')
	});
}

// 重载
productsUsersEL.reload.click(function() {
	productsUsersEL.dg.datagrid('clearSelections');
	productsUsersEL.dg.datagrid('reload',{});
});
</script>
</body>
</html>