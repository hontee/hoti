<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../_header.jsp" %>
<title>管理主题关联的产品</title>
</head>
<body>
<header id="groups-mgt-header" class="cms-dg-header">
	<button id="groups-mgt-remove" class="easyui-linkbutton" data-options="iconCls:'icon-no'">移除</button>
	<button id="groups-mgt-reload" class="easyui-linkbutton" data-options="iconCls:'icon-reload'">刷新</button>
	
	<span class="cms-dg-search">
	  <select class="easyui-combobox" id="groups-mgt-state" data-options="panelHeight:'auto',editable: false" style="width:100px;">
        <option value="-1" selected>全部状态</option>
        <option value="1">启用</option>
        <option value="0">禁用</option>
      </select>
	  <input class="easyui-searchbox" data-options="prompt:'输入标题', searcher:groupsMgtEL.search" style="width:200px" />
	</span>
</header>
<table id="groups-mgt-dg"></table>
<footer id="groups-mgt-win"></footer>
<script>
// 变量取值要唯一
var groupsMgtEL = {
	remove: $("#groups-mgt-remove"),
	reload: $("#groups-mgt-reload"),
	dg: $("#groups-mgt-dg"),
	win: $("#groups-mgt-win")
};

// DataGrid
groupsMgtEL.dg.datagrid({
    url:'/cms/topics/${id}/products',
    fitColumns: true,
    border: false,
    idField: "id",
    rownumbers: true,
    pagination: true,
    pageSize: 20,
    pageList:[20, 50, 100],
    header: '#groups-mgt-header',
    fit: true,
    columns:[[
        {field:'id', checkbox: true},
        {field:'name',title:'名称',width:100, hidden: true},
        {field:'title',title:'标题',width:400, sortable: true},
        {field:'star',title:'星',width:100, sortable: true},
        {field:'hit',title:'点击数',width:100, sortable: true},
        {field:'pick',title:'精选',width:100, sortable: true, formatter: function(value,row,index) {
        	if (value == '1') {
				return '是';
			} else {
				return '否';
			}
        }},
        {field:'audit',title:'审核',width:100, sortable: true, formatter: function(value,row,index) {
        	if (value == '1') {
				return '已认证';
			} else {
				return '未认证';
			}
        }},
        {field:'state',title:'状态',width:100, sortable: true, formatter: function(value,row,index) {
        	if (value == '1') {
				return '启用';
			} else {
				return '禁用';
			}
        }},
        {field:'created',title:'创建时间',width:120, sortable: true, formatter: function(value,row,index) {
        	return new Date(value).format();  
        }}
    ]]
});

// 搜索
groupsMgtEL.search = function(value){
	groupsMgtEL.dg.datagrid('load',{
		title: value,
		state: $('#groups-mgt-state').combobox('getValue')
	});
}

// 移除
groupsMgtEL.remove.click(function() {
	var rows = groupsMgtEL.dg.datagrid('getSelections');
	  if (rows.length > 0) {
	    var ids = new Array(rows.length);
	    for (var i = 0; i < rows.length; i++) {
	      ids[i] = rows[i].id;
	    }
		$.messager.confirm("信息提示", "确定移除吗？", function(ok){
	      if (ok){
	        var url = '/cms/topics/${id}/manager?ids=' + ids;
	        $.post(url, function(data) {
	          var r = $.parseJSON(data);
	          if (r.success) {
	        	CMS.showMsg("信息提示", "移除成功");
	        	CMS.reload(groupsMgtEL);
	        	CMS.reload(groupsEL);
	          } else {
	            $.messager.alert("错误提示", r.error.message, "error");
	          }
	        });
	      } 
	    });
	  }
});

// 重载
groupsMgtEL.reload.click(function() {
	groupsMgtEL.dg.datagrid('clearSelections');
	groupsMgtEL.dg.datagrid('reload',{});
});
</script>
</body>
</html>