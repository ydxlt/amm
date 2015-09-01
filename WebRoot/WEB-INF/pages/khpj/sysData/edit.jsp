<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/taglib.jsp"%>
<html lang="zh-cn">
<head>
<title>考核数据修改</title>
<meta http-equiv="X-UA-Compatible" content="IE=8" />
<%@ include file="/WEB-INF/pages/common/styles.jsp"%>
</head>
<body>
	<div class="container mainbody">
		<%@ include file="/WEB-INF/pages/common/head.jsp"%>

		<div class="row-fluid">
			<div class="span10">
				<ul class="breadcrumb">
					<li><span class="divider">|</span>
					</li>
					<li><a href="${ctx}/khpj/sysData/list.html">控制面板</a> <span class="divider">|</span>
					</li>
					<li class="active">修改考核数据</li>
				</ul>
			</div>
			<div class="btn-group span2">
				<a class="btn btn-primary" href="${ctx}/khpj/sysData/list.html"><i class="icon-backward icon-white"></i> 返回 </a>
			</div>
		</div>

		<c:if test="${not empty tips}">
			<div class="alert alert-success">
				<button type="button" class="close" data-dismiss="alert">&times;</button>
				<h4>系统提示</h4>
				${tips}!
			</div>
		</c:if>

		<div class="row-fluid">
			<c:if test="${empty sysData}">
				<br/>
				<p class="text-center">未提交</p>
			</c:if>
			<c:if test="${not empty sysData}">
			<form action="${ctx}/khpj/sysData/save.html" method="post" onsubmit="return confirm('是否提交数据？')">
				<table class="table table-bordered table-condensed">
					<thead>
						<tr>
							<th>${sysDataType[sysData.contentType]}</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td>考核数据标题<input type="text" name="title" data-rule="required;"  class="span11" value="${sysData.title}" />
							</td>
						</tr>
						<tr>
							<td id="kindeditor"><textarea id="content" name="content" style="width:100%;height:400px;visibility:hidden;">${sysData.content}</textarea>
							</td>
						</tr>
						<tr>
							<td>创建时间：${sysData.createDate}<input type="hidden" name="createDate" value="${sysData.createDate}" />
							</td>
						</tr>
						<tr>
							<td>
								<p class="text-center">
									<input type="hidden" name="sysDataId" value="${sysData.sysDataId}" /> 
									<input type="hidden" name="contentType" value="${sysData.contentType}" />
									<input type="hidden" name="scoreMonth" value="${sysData.scoreMonth}" />
									<button class="btn btn-primary" type="submit">提 交</button>
								</p>
							</td>
						</tr>
					</tbody>
				</table>
			</form>
			</c:if>
		</div>
		<%@ include file="/WEB-INF/pages/common/foot.jsp"%>
	</div>
	<%@ include file="/WEB-INF/pages/common/scripts.jsp"%>
	<script type="text/javascript" src="${ctx}/resource/kindeditor/kindeditor-min.js"></script>
	<script type="text/javascript" src="${ctx}/resource/kindeditor/zh_CN.js"></script>
	<script type="text/javascript">
	var editor;
	KindEditor.ready(function(K) {
		editor = K.create('textarea', {
			resizeType : 1,
			allowPreviewEmoticons : false,
			urlType : 'absolute',
			allowImageUpload : true,
			allowImageRemote : false,
			uploadJson : '${ctx}/khpj/sysData/upload.html', //服务端上传图片处理URI
			items : [
				'fontsize', '|', 'forecolor', 'hilitecolor', 'bold', 'italic', 'underline',
				'removeformat', '|', 'justifyleft', 'justifycenter', 'justifyright', 'insertorderedlist',
				'insertunorderedlist', '|','image', 'link']
		});
	});
	</script>
</body>
</html>