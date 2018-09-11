<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>设备信息</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/jquery-easyui-1.3.3/themes/default/easyui.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/jquery-easyui-1.3.3/themes/icon.css">
<script type="text/javascript"
	src="${pageContext.request.contextPath}/jquery-easyui-1.3.3/jquery.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/jquery-easyui-1.3.3/jquery.easyui.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/jquery-easyui-1.3.3/locale/easyui-lang-zh_CN.js"></script>
<script charset="utf-8"
	src="${pageContext.request.contextPath}/js/common.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/My97DatePicker/WdatePicker.js"
	charset="utf-8"></script>
</head>
<body style="margin: 1px;" id="ff">
	<div id="tb">
		<div>
			&nbsp;公司名称：&nbsp;<input type="text" id="startTime" size="20"
				readonly="readonly" />&nbsp; &nbsp;设备名称：&nbsp;<input type="text"
				id="endTime" size="20" readonly="readonly" />&nbsp;<a
				onclick="searchJob()" class="easyui-linkbutton"
				iconCls="icon-search">搜索</a>
		</div>
	</div>
	<table id="dg"></table>
</body>
<script type="text/javascript">
	$(function() {
		dataGrid = $('#dg').datagrid({
			url : '${pageContext.request.contextPath}/companys/datagrid',
			method : 'GET',
			fit : false,
			fitColumns : true,
			border : false,
			pagination : true,
			idField : 'id',
			pageSize : 10,
			pageList : [ 10, 20, 30, 40, 50 ],
			sortName : 'id',
			sortOrder : 'desc',
			checkOnSelect : false,
			selectOnCheck : false,
			nowrap : false,
			striped : true,
			rownumbers : true,
			singleSelect : true,
			queryParams : {

			},
			frozenColumns : [ [ {
				field : 'dd',
				title : '',
				width : 150,
				hidden : true
			}, {
				field : 'id',
				title : '编号',
				width : 150,
				hidden : true
			} ] ],
			columns : [ [ {
				field : 'name',
				title : '企业名称',
				width : 150,
				sortable : true
			},{
                field : 'about',
                title : '企业简介',
                width : 150,
                sortable : true
            },{
				field : 'address',
				title : '企业地址',
				width : 150,
				sortable : true
			},{
				field : 'gmtModify',
				title : '修改时间',
				width : 200,
				sortable : true
			}, {
				field : 'action',
				title : '操作',
				width : 100,
				formatter : function(value, row, index) {
				    console.log(row);
					return formatHref(value, row);
				}
			} ] ],
			onLoadSuccess : function() {
				parent.$.messager.progress('close');
			},
			onRowContextMenu : function(e, rowIndex, rowData) {
				e.preventDefault();
				$(this).datagrid('unselectAll');
				$(this).datagrid('selectRow', rowIndex);
				$('#menu').menu('show', {
					left : e.pageX,
					top : e.pageY
				});
			}
		});

	});


	function formatHref(val, row) {
	    //传参 企业id
		return "<a href='${pageContext.request.contextPath}/views/recruitDetail.jsp?id="
				+ row.id + "' target='_blank'>查看该公司设备列表</a>";
	}
</script>
</html>