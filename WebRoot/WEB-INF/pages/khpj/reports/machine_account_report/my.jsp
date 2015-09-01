<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/taglib.jsp"%>
<html lang="zh-cn">
<head>
<title>我的突出矛盾纠纷排查调处工作台帐维护</title>
<meta http-equiv="X-UA-Compatible" content="IE=8" />
<%@ include file="/WEB-INF/pages/common/styles.jsp"%>
</head>
<body>
	<div class="container mainbody">
		<%@ include file="/WEB-INF/pages/common/head.jsp"%>
		<div class="row-fluid">
			<div class="span10">
				<ul class="breadcrumb">
					<li><span class="divider">|</span></li>
					<li class="active">控制面板</li>
					<li><span class="divider">|</span> 突出矛盾纠纷排查调处工作台帐</li>
				</ul>
			</div>
			<div class="btn-group span2">
				<a class="btn btn-primary dropdown-toggle" data-toggle="dropdown" href="#"><i class="icon-list icon-white"></i> 功能菜单 <span class="caret"></span> </a>
				<ul class="dropdown-menu">
					<li><a href="${ctx}/khpj/sysData/list.html">控制面板</a></li>
					<li class="divider"></li>
					<li><a href="${ctx}/khpj/sysData/new.html">新增本月数据</a></li>
				</ul>
			</div>
		</div>

		<div class="row-fluid" style="min-height:400px; overflow: auto;">
			<form action="${ctx}/khpj/reports/machine_account_report/my.html" method="post">
				<div class="row-fluid">
					<div class="span8">当前为：${queryMonth} 月份数据</div>
					<div class="span4">
						<div class="input-append">
							<select class="span4" name="month">
								<c:forEach var="year" items="${years}">
									<option value="${year}" <c:if test="${year == queryMonth}">selected="selected"</c:if>>${year}</option>
								</c:forEach>
							</select>
							<button type="submit" class="btn btn-primary">历史数据查询</button>
							<a class="btn btn-primary" href="${ctx}/khpj/sysData/new.html">新增台帐</a>
						</div>
					</div>
				</div>
			</form>
			<c:if test="${empty datas}">
				暂无数据
			</c:if>
			<c:if test="${not empty datas}">
				<table class="table table-striped table-bordered table-condensed">
					<thead>
						<tr>
							<th class="span1">序号</th>
							<th class="span2">单位</th>
							<th>矛盾纠纷简要情况</th>
							<th>责任单位<br/>责任人</th>
							<th>化解时限</th>
							<th>化解情况</th>
							<th>交办督办情况</th>
							<th>销号情况</th>
							<c:if test="${not empty delete}">
								<th>操作</th>
							</c:if>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="d" items="${datas.result}" varStatus="statue">
							<tr>
								<td>${statue.index + 1}</td>
								<td>${d.department.departmentName}</td>
								<td>${d.cdDescription}</td>
								<td>${d.dutyPerson}</td>
								<td>${d.resoleTime}</td>
								<td>${d.resoleDescription}</td>
								<td>${d.supervision}</td>
								<td>${d.xiaoHao}</td>
								<c:if test="${not empty delete}">
									<td>
										<a href="${ctx}/khpj/reports/machine_account_report/delete/${d.machineAccountId}.html" onclick="return confirm('是否删除？');">删除</a>
									</td>
								</c:if>
							</tr>
						</c:forEach>
						</tbody>
					</table>
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