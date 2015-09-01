<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/taglib.jsp"%>
<html lang="zh-cn">
<head>
<title>矛盾纠纷表维护</title>
<%@ include file="/WEB-INF/pages/common/styles.jsp"%>
</head>
<body>
	<div class="container mainbody">
		<%@ include file="/WEB-INF/pages/common/head.jsp"%>
		
		<div class="row-fluid">
			<div class="span10">
				<ul class="breadcrumb">
					<li><span class="divider">|</span></li>
					<li><a href="${ctx}/khpj/sysData/list.html">控制面板</a> <span class="divider">|</span></li>
					<li class="active">新增矛盾纠纷报表</li>
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
		
		<form action="${ctx}/khpj/reports/con_disputes_report/save.html" method="post" onsubmit="return confirm('是否提交数据？')">
		<div class="row">
			<c:set var="countyCount" value="0"></c:set>
			<c:set var="townCount" value="0"></c:set>
			<c:set var="villageCount" value="0"></c:set>
			<c:set var="couDisCount" value="0"></c:set>
			<c:set var="townDisCount" value="0"></c:set>
			<c:set var="vilDisCount" value="0"></c:set>
			<c:forEach var="type" items="${types}" varStatus="statue">
				<div class="span4">
					<table class="table table-bordered table-condensed">
						<tbody>
							<tr>
								<td width="50" height="50">
									<p>类别</p>
								</td>
								<td colspan="3">
									<p>${type.value}</p>
									<input type="hidden" name="${type.key}_id" value="${data[type.key].cDReportId}"/>
								</td>
							</tr>
							<tr>
								<td>
									<p>层级</p></td>
								<td>
									<p>县区</p></td>
								<td>
									<p>乡镇</p></td>
								<td>
									<p>村居</p></td>
							</tr>
							<tr>
								<td>
									<p>排查数</p></td>
								<td>
									<p><input type="text" class="input-mini" data-rule="integer;" name="${type.key}_countyCount" value="${data[type.key].countyCount}"/></p></td>
								<td>
									<p><input type="text" class="input-mini" data-rule="integer;" name="${type.key}_townCount" value="${data[type.key].townCount}"/></p></td>
								<td>
									<p><input type="text" class="input-mini" data-rule="integer;" name="${type.key}_villageCount" value="${data[type.key].villageCount}"/></p></td>
							</tr>
							<tr>
								<td>
									<p>调处数</p></td>
								<td>
									<p><input type="text" class="input-mini" data-rule="integer;" name="${type.key}_couDisCount" value="${data[type.key].couDisCount}"/></p></td>
								<td>
									<p><input type="text" class="input-mini" data-rule="integer;" name="${type.key}_townDisCount" value="${data[type.key].townDisCount}"/></p></td>
								<td>
									<p><input type="text" class="input-mini" data-rule="integer;" name="${type.key}_villageDisCount" value="${data[type.key].vilDisCount}"/></p></td>
							</tr>
						</tbody>
					</table>
				</div>
			</c:forEach>
		</div>
		
		<div class="row">
			<p class="text-right">
				<button class="btn btn-primary" type="submit">提 交</button>&nbsp;&nbsp;
			</p>
		</div>
		</form>
		<%@ include file="/WEB-INF/pages/common/foot.jsp"%>
	</div>
	<%@ include file="/WEB-INF/pages/common/scripts.jsp"%>
</body>
</html>