<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/recruitDetail.css">
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
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/kindeditor-4.1.10/themes/default/default.css" />
<script charset="utf-8"
	src="${pageContext.request.contextPath}/kindeditor-4.1.10/kindeditor-all.js"></script>
<script charset="utf-8"
	src="${pageContext.request.contextPath}/kindeditor-4.1.10/lang/zh_CN.js"></script>
<script charset="utf-8"
	src="${pageContext.request.contextPath}/js/common.js"></script>
<link
	href="${pageContext.request.contextPath}/css/bootstrap.min14ed.css"
	rel="stylesheet">
<link
	href="${pageContext.request.contextPath}/css/font-awesome.min93e3.css"
	rel="stylesheet">

<link href="${pageContext.request.contextPath}/css/animate.min.css"
	rel="stylesheet">
<link href="${pageContext.request.contextPath}/css/style.min862f.css"
	rel="stylesheet">
</head>
<body class="career-details"
	style="display: flex; flex-direction: column; height: auto; min-height: 100%;">
	<div class="container" style="flex: 1 1 auto;">
		<div class="inner">
			<div class="pub-details" id="data_details">
				<div class="main">
					<div class="main-inner">
						
						<div class="detail-module">
							<div class="dm-tit">设备列表</div>
							<div class="dm-cont pub-list">
								<div id="deviceInfo"></div>
							</div>
						</div>

					</div>
				</div>
				<div class="side">
					<div class="side-inner">
						<div class="company-infor clearfix">
							<a class="ci-link">
								<p class="ci-text" id="rcompany_name"></p>
							</a>
							<dl class="ci-module">
								<dd class="ci-cont">
									<p class="ci-text" id="company_world"></p>
									<p class="ci-text" id="company_scale"></p>
									<p class="ci-text" id="company_address"></p>
								</dd>
							</dl>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript">
	var detailDate;
	var about;
	var motoley;
	var id = getQueryStringByName("id");
	$(function() {
	    about = KindEditor.create('textarea[id="about"]', {
			items : []
		});
	    motoley = KindEditor.create('textarea[id="motoley"]', {
			items : []
		});
		getDate();
	});

	/* 获取数据 */
	function getDate() {
		$.ajax({
			type : "GET",
			url : "${pageContext.request.contextPath}/companys/detail/" + id,
			success : function(result) {
				if (result.resultCode == 200&&result.data!=null&&result.data.data!=null) {
					detailDate = result.data.data;
					setdeviceInfo();
				} else {
					alert("提示：此公司无在用设备 " + result.message);
				}
			},
			error : function() {
				alert("异常！");
			}
		});
	};



	/* 设置设备列表信息 */
	function setdeviceInfo() {
		var deviceInfos=new StringBuffer();
		/* of报错不用管 */
		for(var device of detailDate.devices){
			deviceInfos.append("<div style=\"border-bottom: 1px dashed #ddd; padding: 10px 0; line-height: 2;\" class=\"clearfix dm-text\">");
			deviceInfos.append("<div style=\"width: 360px; float: left;\"> <p> <a class=\"item-link\" style=\"font-size: 16px;\"");
			deviceInfos.append("href=").append("${pageContext.request.contextPath}/views/company/deviceDetail.jsp?cId=").append(id).append("&jId=").append(device.id);
			deviceInfos.append( " target=\"_blank\">");
			deviceInfos.append(device.name);
			deviceInfos.append("</a></p>");
			deviceInfos.append("<p style=\"padding-right: 20px; width: 650px; overflow: hidden; text-overflow: ellipsis; white-space: nowrap;\">");

			deviceInfos.append("</p></div>");
			deviceInfos.append("<div style=\"float: left; color: #ff9900;\">");
			deviceInfos.append(device.devicecode);
			deviceInfos.append("</div>");
			deviceInfos.append("<div style=\"float: right;\">");
			deviceInfos.append("<p style=\"width: 140px; height: 32px; overflow: hidden; white-space: pre; text-overflow: ellipsis;\">");
			deviceInfos.append(device.address);
			deviceInfos.append("</p>");
			deviceInfos.append("</div></div>");
		}; 
		$("#deviceInfo").append(deviceInfos.toString());
	}
</script>
</html>