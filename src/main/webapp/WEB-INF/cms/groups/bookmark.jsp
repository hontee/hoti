<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../_header.jsp" %>
<title>群组管理-添加站点</title>
</head>
<body>
<header id="groups-bm-header" class="cms-dg-header">
	<button id="groups-bm-add" class="easyui-linkbutton" data-options="iconCls:'icon-ok'">提交</button>
	<button id="groups-bm-reload" class="easyui-linkbutton" data-options="iconCls:'icon-reload'">刷新</button>
	
	<span class="cms-dg-search">
	  <input class="easyui-combobox" id="groups-bm-category"
    	data-options="required:true, value:'-1', valueField:'id',textField:'title',url:'/cms/categories/datalist?q=all'" 
    	style="width:100px;">
	  <select class="easyui-combobox" id="groups-bm-state" data-options="panelHeight:'auto',editable: false" style="width:100px;">
        <option value="-1" selected>全部状态</option>
        <option value="1">启用</option>
        <option value="0">禁用</option>
      </select>
	  <input class="easyui-searchbox" data-options="prompt:'输入标题', searcher:groupsBmEL.search" style="width:200px" />
	</span>
</header>
<table id="groups-bm-dg"></table>
<footer id="groups-bm-win"></footer>
<script>
// 变量取值要唯一
var groupsBmEL = {
	add: $("#groups-bm-add"),
	reload: $("#groups-bm-reload"),
	dg: $("#groups-bm-dg"),
	win: $("#groups-bm-win")
};

// DataGrid
groupsBmEL.dg.datagrid({
    url:'/cms/products/list',
    fitColumns: true,
    border: false,
    idField: "id",
    rownumbers: true,
    pagination: true,
    pageSize: 20,
    pageList:[20, 50, 100],
    header: '#groups-bm-header',
    fit: true,
    columns:[[
        {field:'id', checkbox: true},
        {field:'name',title:'名称',width:100, hidden: true},
        {field:'title',title:'标题',width:400, sortable: true},
        {field:'star',title:'星',width:100, sortable: true},
        {field:'hit',title:'点击数',width:100, sortable: true},
        {field:'category',title:'所属分类',width:100, sortable: true, formatter: function(value,row,index) {
        	return row.cateTitle;
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
        }},
        {field:'extCategoryTitle',title:'分类标题', hidden: true}
    ]]
});

// 搜索
groupsBmEL.search = function(value){
	groupsBmEL.dg.datagrid('load',{
		title: value,
		state: $('#groups-bm-state').combobox('getValue'),
		category: $('#groups-bm-category').combobox('getValue')
	});
}

// 新建
groupsBmEL.add.click(function() {
	var rows = groupsBmEL.dg.datagrid('getSelections');
	  if (rows.length > 0) {
	    var ids = new Array(rows.length);
	    for (var i = 0; i < rows.length; i++) {
	      ids[i] = rows[i].id;
	    }
		$.messager.confirm("信息提示", "确定添加吗？", function(ok){
	      if (ok){
	        var url = '/cms/topics/${id}/bookmark?ids=' + ids;
	        $.post(url, function(data) {
	        	console.log(data);
	          var r = $.parseJSON(data);
	          if (r.success) {
	        	CMS.showMsg("信息提示", "添加成功");
	        	groupsEL.win.window('close'); // 关闭窗口
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
groupsBmEL.reload.click(function() {
	groupsBmEL.dg.datagrid('clearSelections');
	groupsBmEL.dg.datagrid('reload',{});
});
</script>
</body>
</html>