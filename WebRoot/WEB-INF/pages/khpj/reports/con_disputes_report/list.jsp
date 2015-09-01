<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/taglib.jsp"%>
<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags'%>
<html lang="zh-cn">
<head>
<title>矛盾纠纷表统计</title>
<%@ include file="/WEB-INF/pages/common/styles.jsp"%>
</head>
<body>
	<div class="container mainbody">
		<%@ include file="/WEB-INF/pages/common/head.jsp"%>
		
		<div class="row-fluid">
			<div class="span10">
				<ul class="breadcrumb">
					<li><span class="divider">|</span></li>
					<li><a href="${ctx}/khpj/score/assess.html">控制面板</a> <span class="divider">|</span></li>
					<li class="active">矛盾纠纷报表</li>
				</ul>
			</div>
			<div class="btn-group span2">
				<sec:authorize ifAllGranted="LABEL_SYS_ASSESS">
					<!-- 考核管理员 -->
					<a class="btn btn-primary" href="${ctx}/khpj/score/assess.html"><i class="icon-backward icon-white"></i> 返回 </a>
				</sec:authorize>
				<sec:authorize ifAnyGranted="LABEL_SYS_DEPUTY_AUDIT">
					<!-- 副书记 -->
					<a class="btn btn-primary" href="${ctx}/khpj/score/audit.html"><i class="icon-backward icon-white"></i> 返回 </a>
				</sec:authorize>
				<sec:authorize ifAllGranted="LABEL_SYS_AUDIT">
					<!-- 书记 -->
					<a class="btn btn-primary" href="${ctx}/khpj/score/audit.html"><i class="icon-backward icon-white"></i> 返回 </a>
				</sec:authorize>
			</div>
		</div>
		
		<div class="row">
			<div class="span7"></div>
			<div class="span5">
				<form action="${ctx}/khpj/reports/con_disputes_report/list.html" method="post">
					<div class="input-append">
						<select class="span2" name="reportDate">
							<c:forEach var="year" items="${years}">
								<option value="${year}" <c:if test="${year == queryMonth}">selected="selected"</c:if>>${year}</option>
							</c:forEach>
						</select>
						<button type="submit" class="btn btn-primary">月数据查询</button>
						<a class="btn btn-primary" href="${ctx}/khpj/print_report/cd_report.html?month=${queryMonth}"><i class="icon-print icon-white"></i> 生成Word </a>
					</div>
				</form>
			</div>
		</div>
		
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
									<p>${datas[type.key].countyCount}</p></td>
								<td>
									<p>${datas[type.key].townCount}</p></td>
								<td>
									<p>${datas[type.key].villageCount}</p></td>
							</tr>
							<tr>
								<td>
									<p>调处数</p></td>
								<td>
									<p>${datas[type.key].couDisCount}</p></td>
								<td>
									<p>${datas[type.key].townDisCount}</p></td>
								<td>
									<p>${datas[type.key].vilDisCount}</p></td>
							</tr>
						</tbody>
					</table>
				</div>
				<c:set var="countyCount" value="${datas[type.key].countyCount+countyCount}"></c:set>
				<c:set var="townCount" value="${datas[type.key].townCount+townCount}"></c:set>
				<c:set var="villageCount" value="${datas[type.key].villageCount+villageCount}"></c:set>
				<c:set var="couDisCount" value="${datas[type.key].couDisCount+couDisCount}"></c:set>
				<c:set var="townDisCount" value="${datas[type.key].townDisCount+townDisCount}"></c:set>
				<c:set var="vilDisCount" value="${datas[type.key].vilDisCount+vilDisCount}"></c:set>
			</c:forEach>
			<div class="span4">
					<table class="table table-bordered table-condensed">
						<tbody>
							<tr>
								<td colspan="4" height="50">
									<p>合计</p>
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
									<p>${countyCount}</p></td>
								<td>
									<p>${townCount}</p></td>
								<td>
									<p>${villageCount}</p></td>
							</tr>
							<tr>
								<td>
									<p>调处数</p></td>
								<td>
									<p>${couDisCount}</p></td>
								<td>
									<p>${townDisCount}</p></td>
								<td>
									<p>${vilDisCount}</p></td>
							</tr>
						</tbody>
					</table>
				</div>
		</div>
		
		<%@ include file="/WEB-INF/pages/common/foot.jsp"%>
	</div>
	<%@ include file="/WEB-INF/pages/common/scripts.jsp"%>
</body>
</html>