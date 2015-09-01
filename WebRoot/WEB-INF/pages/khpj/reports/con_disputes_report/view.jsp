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
					<li class="active">查看矛盾纠纷报表</li>
				</ul>
			</div>
			<div class="btn-group span2">
				<a class="btn btn-primary" href="${ctx}/khpj/sysData/list.html"><i class="icon-backward icon-white"></i> 返回 </a>
			</div>
		</div>
		
		<div class="row">
			<c:forEach var="data" items="${datas}" varStatus="statue">
				<div class="span4">
					<table class="table table-bordered table-condensed">
						<tbody>
							<tr>
								<td width="50" height="50">
									<p>类别</p>
								</td>
								<td colspan="3">
									<p>${types[data.conDisClass]}</p>
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
									<p>${data.countyCount}</p></td>
								<td>
									<p>${data.townCount}</p></td>
								<td>
									<p>${data.villageCount}</p></td>
							</tr>
							<tr>
								<td>
									<p>调处数</p></td>
								<td>
									<p>${data.couDisCount}</p></td>
								<td>
									<p>${data.townDisCount}</p></td>
								<td>
									<p>${data.vilDisCount}</p></td>
							</tr>
						</tbody>
					</table>
				</div>
			</c:forEach>
		</div>
		
		<%@ include file="/WEB-INF/pages/common/foot.jsp"%>
	</div>
	<%@ include file="/WEB-INF/pages/common/scripts.jsp"%>
</body>
</html>