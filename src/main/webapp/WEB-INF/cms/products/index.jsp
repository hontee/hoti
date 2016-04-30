<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../_header.jsp" %>
<title>产品管理</title>
</head>
<body>
<header id="products-header" class="cms-dg-header">
	<button id="products-add" class="easyui-linkbutton" data-options="iconCls:'icon-add'">新建</button>
	<button id="products-edit" class="easyui-linkbutton" data-options="iconCls:'icon-edit',disabled:true">编辑</button>
	<button id="products-remove" class="easyui-linkbutton" data-options="iconCls:'icon-remove',disabled:true">删除</button>
	<button id="products-follow" class="easyui-linkbutton" data-options="iconCls:'icon-tip',disabled:true">关注</button>
	<button id="products-pick" class="easyui-linkbutton" data-options="iconCls:'icon-add',disabled:true">精选</button>
	<button id="products-unpick" class="easyui-linkbutton" data-options="iconCls:'icon-remove',disabled:true">取消精选</button>
	<button id="products-reload" class="easyui-linkbutton" data-options="iconCls:'icon-reload'">刷新</button>
	
	<span class="cms-dg-search">
	  <select class="easyui-combobox" id="products-state" data-options="panelHeight:'auto',editable: false" style="width:100px;">
        <option value="-1" selected>全部状态</option>
        <option value="1">启用</option>
        <option value="0">禁用</option>
      </select>
	  <input class="easyui-searchbox" data-options="prompt:'输入标题', searcher:productsEL.search" style="width:200px" />
	</span>
</header>
<table id="products-dg"></table>
<footer id="products-win"></footer>
<script>
// 变量取值要唯一
var productsEL = {
	add: $("#products-add"),
	edit: $("#products-edit"),
	remove: $("#products-remove"),
	follow: $("#products-follow"),
	pick: $("#products-pick"),
	unpick: $("#products-unpick"),
	reload: $("#products-reload"),
	dg: $("#products-dg"),
	win: $("#products-win")
};

// DataGrid
productsEL.dg.datagrid({
    url:'/cms/products/list',
    fitColumns: true,
    border: false,
    idField: "id",
    rownumbers: true,
    pagination: true,
    pageSize: 20,
    pageList:[20, 50, 100],
    header: '#products-header',
    fit: true,
    columns:[[
        {field:'id', checkbox: true},
        {field:'name',title:'名称',width:100, hidden: true},
        {field:'title',title:'标题',width:100, sortable: true},
        {field:'url',title:'网址',width:100},
        {field:'description',title:'描述',width:100},
        {field:'tags',title:'标签',width:100},
        {field:'avatar',title:'头像',width:100},
        {field:'reffer',title:'来源',width:100},
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
        {field:'created',title:'创建时间',width:100, sortable: true, formatter: function(value,row,index) {
        	return new Date(value).format();  
        }},
        {field:'lastModified',title:'更新时间',width:100, sortable: true, formatter: function(value,row,index) {
        	return new Date(value).format();  
        }},
        {field:'creator',title:'创建人',width:100, sortable: true}
    ]],
 	// 当选择一行时触发
    onSelect: function(index,row) {
    	productsEL.reset();
    },
 	// 当取消选择一行时触发
    onUnselect: function(index,row) {
    	productsEL.reset();
    },
 	// 当全选时触发
    onSelectAll: function(rows) {
    	productsEL.reset();
    },
 	// 当取消全选时触发
    onUnselectAll: function(rows) {
    	productsEL.reset();
    },
    // 双击查看
    onDblClickRow: function(index,row) {
    	CMS.viewHandler("/cms/products/" + row.id);
    }
});

// 根据选择记录触发: 重置按钮状态
productsEL.reset = function() {
	var length = productsEL.dg.datagrid("getSelections").length;
	if (length == 0) {
		/* 编辑，删除，关注，精选，取消精选 按钮禁用 */
		productsEL.edit.linkbutton({disabled: true});
		productsEL.follow.linkbutton({disabled: true});
		productsEL.remove.linkbutton({disabled: true});
		productsEL.pick.linkbutton({disabled: true});
		productsEL.unpick.linkbutton({disabled: true});
	} else if (length == 1) {
		/* 编辑，删除，关注，精选，取消精选 按钮启用 */
		productsEL.edit.linkbutton({disabled: false});
		productsEL.follow.linkbutton({disabled: false});
		productsEL.remove.linkbutton({disabled: false});
		productsEL.pick.linkbutton({disabled: false});
		productsEL.unpick.linkbutton({disabled: false});
	} else {
		/* 编辑，删除，关注按钮禁用，精选，取消精选 按钮启用  */
		productsEL.edit.linkbutton({disabled: true});
		productsEL.follow.linkbutton({disabled: true});
		productsEL.remove.linkbutton({disabled: true});
		productsEL.pick.linkbutton({disabled: false});
		productsEL.unpick.linkbutton({disabled: false});
	}
}

// 搜索
productsEL.search = function(value){
	productsEL.dg.datagrid('load',{
		title: value,
		state: $('#products-state').combobox('getValue')
	});
}

// 新建
productsEL.add.click(function() {
	productsEL.win.window({
		width: 480,
		height: 580,
		modal: true,
		title: '新建产品',
		collapsible: false,
		minimizable: false,
		maximizable: false,
		href: '/cms/products/new',
		method: 'get',
		cache: false
	});
});

// 编辑
productsEL.edit.click(function() {
	var row = productsEL.dg.datagrid('getSelected');
	if (row) {
		productsEL.win.window({
			width: 480,
			height: 650,
			modal: true,
			title: '编辑产品',
			collapsible: false,
			minimizable: false,
			maximizable: false,
			href: '/cms/products/' + row.id + '/edit',
			method: 'get',
			cache: false
		});
	}
});

// 删除
productsEL.remove.click(function() {
	CMS.removeSubmitHandler(productsEL, 'products');
});

// 精选
productsEL.pick.click(function() {
	CMS.pickSubmitHandler(productsEL, 'products');
});

// 取消精选
productsEL.unpick.click(function() {
	CMS.unpickSubmitHandler(productsEL, 'products');
});

// 关注
productsEL.follow.click(function() {
	var row = productsEL.dg.datagrid('getSelected');
	if (row) {
		productsEL.win.window({
			width: 980,
			height: 600,
			modal: true,
			title: '用户列表',
			collapsible: false,
			minimizable: false,
			maximizable: false,
			href: '/cms/products/' + row.id + '/follow',
			method: 'get',
			cache: false
		});
	}
});

// 刷新
productsEL.reload.click(function() {
	productsEL.dg.datagrid('clearSelections');
	productsEL.dg.datagrid('reload',{});
});
</script>
</body>
</html>