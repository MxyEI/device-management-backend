<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/recruitDetail.css">
<script type="text/javascript"
	src="${pageContext.request.contextPath}/jquery-easyui-1.3.3/jquery.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/jquery-easyui-1.3.3/jquery.easyui.min.js"></script>
<script charset="utf-8"
	src="${pageContext.request.contextPath}/js/common.js"></script>
<script charset="utf-8"
	src="${pageContext.request.contextPath}/kindeditor-4.1.10/kindeditor-all.js"></script>
</head>
<body class="device-details"
	style="display: flex; flex-direction: column; height: auto; min-height: 100%;">
	<div class="container" style="flex: 1 1 auto;">
		<div class="inner">
			<div class="pub-details" id="data_details">
				<div class="main">
					<div class="main-inner">
						<div class="details-head">
							<h1 class="dh-tit" style="font-weight: normal;">
								<p id="company_name"></p>
								<p id="job_name"></p>
							</h1>
							<h1 style="float: right; font-size: 20px" id="spzt">
								设备状态：<span style="color: red" id="success"></span>
							</h1>
							<div class="tag-list">
								<span class="tag-item pub-orange-text" id="job_money"></span> <span
									class="tag-item" id="job_address"></span> <span
									class="tag-item" id="job_degree"></span>

								<button class="pub-btn btn-normal btn-primary fr"
									onclick="apply()" id="apply" style="letter-spacing: 0">申请维修</button>

							</div>
							<p class="publish-time" id="recruit_gmtCreate"></p>
						</div>
						<div class="detail-module">
							<div class="dm-tit">设备描述</div>
							<div class="dm-cont">
								<textarea id="deviceDescribe"
									style="font-size: 16px; line-height: 175%; font-family: 宋体; visibility: hidden;"></textarea>
							</div>
						</div>

					</div>
				</div>

			</div>
		</div>
	</div>
</body>
<script type="text/javascript">
	var detailDate;
	var deviceDescribe_editor;
	var about_editor;
	//岗位id
	var jId = getQueryStringByName("jId");
    console.log("jId "+jId);
	//用户设备关联表的主键
    //var vId = getQueryStringByName("vId");
    var vId;

	$(function() {
		deviceDescribe_editor = KindEditor.create('textarea[id="deviceDescribe"]', {
			items : []
		});
		about_editor = KindEditor.create('textarea[id="company_about"]', {
			items : []
		});
		checkIsSelected();
		if (!checkId()) {
			return;
		}
		getDate();
	});
	/* 检验是否已经申请维修 */
	function checkIsSelected() {

		$.ajax({
			type : "GET",
			async : false,
			url : "${pageContext.request.contextPath}/userDevices/"
					+ getCookie("id")+"/"+jId,
			success : function(result) {
				if (result.resultCode == 200 && result.data != null
						&& result.data.data != null
						&& result.data.data.deviceId != null) {
				    		vId=result.data.data.id;
							if (isNull(jId) || result.data.data.deviceId == jId) {
							jId = result.data.data.deviceId + "";
							setSuccess(result.data.data.success);
							$("#apply").text("取消申请");
						} else if (isNotNull(jId)) {
						//$("#apply").hide();
						$("#spzt").hide();
					}
				} else {
					$("#spzt").hide();
				}
			},
			error : function() {
				$("#spzt").hide();
			}
		});
	};

	/* 检验是否获取到岗位的id */
	function checkId() {
		if (isNull(jId)) {
			alert("未申请任何工单,请先申请");
			window.location.href = "${pageContext.request.contextPath}/views/companyRec.jsp";
			return false;
		}
		return true;
	}
	/* 获取设备信息 */
	function getDate() {
		$.ajax({
			type : "GET",
			url : "${pageContext.request.contextPath}/device/" + jId,
			success : function(result) {
				if (result.resultCode == 200) {
					detailDate = result.data;
					setDate();
				} else {
					alert("提示：" + result.message);
				}
			},
			error : function() {
				alert("异常！请刷新后重试");
			}
		});
	};

	/* 填充页面数据 */
	function setDate() {

	    console.log(detailDate)
		deviceDescribe_editor.html(detailDate.deviceDescribe);
		deviceDescribe_editor.edit
				.setHeight(deviceDescribe_editor.edit.doc.body.scrollHeight);

	}


	/* 申请，取消申请 */
	function apply() {
		var method = "POST";
		var url = new StringBuffer();
		url.append("${pageContext.request.contextPath}/userDevices");
		if ($("#apply").text() != "申请维修") {
			method = "DELETE";
			url.append("/").append(vId);
		}
		$.ajax({
			type : method,
			url : url,
			data : {
				"userid" : getCookie("id"),
				"deviceId" : jId
			},
			success : function(result) {
				if (result.resultCode == 200) {
					if ($("#apply").text() != "申请维修") {
						$("#apply").text("申请维修");
					} else {
						$("#apply").text("取消申请");
					}
				} else {
					alert("申请维修单已被企业批准，不能取消申请");
				}
			},
			error : function() {
				alert("异常！请刷新后重试");
			}
		});

		//刷新页面
        location.reload();
	}

	function setSuccess(success) {
		if (success == null) {
			$("#success").text("未处理");
		} else if (success) {
			$("#success").text("已维修");
		} else {
			$("#success").text("拒绝");
		}
	}
</script>
</html>