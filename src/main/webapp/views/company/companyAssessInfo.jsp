<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>客户反馈列表</title>
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
</head>
<body style="margin: 1px;" id="ff">

<div id="tb">
	<div>
		&nbsp;客户名称：&nbsp;<input type="text" id="schusername" size="20"
								onkeydown="if(event.keyCode==13) searchUserJob()" />&nbsp;
		设备名称：&nbsp;<input type="text" id="schdevicename" size="20"
						  onkeydown="if(event.keyCode==13) searchUserJob()" />&nbsp;&nbsp;<a
			onclick="searchUserJob()" class="easyui-linkbutton"
			iconCls="icon-search" plain="true">搜索</a>
	</div>
</div>
<table id="dg"></table>
</body>
<script type="text/javascript">
    var urls;
    $(function() {
        if(getCookie("usertype")=="company"){
           urls  = "${pageContext.request.contextPath}/userDevices/datagrid?companyId="+getCookie("id");
		}else{
            urls ="${pageContext.request.contextPath}/userDevices/datagrid";
		}
        dataGrid = $('#dg').datagrid({
            url : urls,
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
//            queryParams : {
//                companyId : getCookie("id")
//            },
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
                field : 'user.username',
                title : '客户账号',
                width : 150,
                sortable : true
            }, {
                field : 'user.realname',
                title : '客户名称',
                width : 150,
                sortable : true
            }, {
                field : 'device.name',
                title : '设备名称',
                width : 150,
                sortable : true
            }, {
                field : 'action',
                title : '操作',
                width : 100,
                formatter : function(value, row, index) {
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
        return "<a href='${pageContext.request.contextPath}/views/assess.jsp?userid="
            + row.user.id + "' target='_blank'>反馈</a>";
    }

    function searchUserJob() {
        $("#dg").datagrid('load', {
            "user.username" : $("#schusername").val(),
            "device.name" : $("#schdevicename").val()
        });
    }


</script>

</html>