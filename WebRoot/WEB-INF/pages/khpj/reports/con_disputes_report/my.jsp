<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/taglib.jsp"%>
<html lang="zh-cn">
<head>
<title>我的矛盾纠纷表维护</title>
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
					<li><span class="divider">|</span> 矛盾纠纷报表</li>
				</ul>
			</div>
			<div class="btn-group span2">
				<a class="btn btn-primary dropdown-toggle" data-toggle="dropdown" href="#"><i class="icon-list icon-white"></i> 功能菜单 <span class="caret"></span> </a>
				<ul class="dropdown-menu">
					<li><a href="${ctx}/khpj/sysData/list.html">控制面板</a></li>
					<li class="divider"></li>
					<li><a href="${ctx}/khpj/sysData/new.html">新增本月数据</a></li>
					<li class="divider"></li>
					<li><a href="${ctx}/khpj/reports/con_disputes_report/my.html"><i class="i"></i> 矛盾纠纷报表</a></li>
					<li><a href="${ctx}/khpj/reports/register_report/my.html"><i class="i"></i> 政绩档案登记表</a></li>
					<li><a href="${ctx}/khpj/reports/appraisal_report/my.html"><i class="i"></i> 政绩档案鉴定表</a></li>
					<li><a href="${ctx}/khpj/reports/bonus_penalty_report/my.html"><i class="i"></i> 政绩档案奖罚表</a></li>
				</ul>
			</div>
		</div>
		<div class="row-fluid">
			<div class="span9">
				<a class="btn btn-primary" href="${ctx}/khpj/reports/con_disputes_report/new.html">新增</a>
			</div>
			<div class="span3">
				<form action="${ctx}/khpj/reports/con_disputes_report/my.html" method="post">
					<div class="input-append">
						<select class="span6" name="reportDate">
							<c:forEach var="year" items="${years}">
								<option value="${year}" <c:if test="${year == queryMonth}">selected="selected"</c:if>>${year}</option>
							</c:forEach>
						</select>
						<button type="submit" class="btn btn-primary">历史数据查询</button>
					</div>
				</form>
			</div>
		</div>
		<div class="row-fluid">
			<c:if test="${empty data}">
				暂无数据
			</c:if>
			<c:forEach var="d" items="${data}">
				<div class="span2">
					<table class="table table-bordered table-condensed">
						<tbody>
							<tr>
								<td rowspan="2">

									<p>类别</p>

									<p>层级</p>

									<p>项目</p>
								</td>
								<td colspan="4">
									<p>因土地、山林、水利等问题引发的矛盾纠纷</p>
								</td>
							</tr>
							<tr>
								<td>
									<p>县区</p></td>
								<td>
									<p>乡镇</p></td>
								<td>
									<p>村居</p></td>
								<td>
									<p>小计</p></td>
							</tr>
							<tr>
								<td>
									<p>排查数</p></td>
								<td>
									<p>&nbsp;</p></td>
								<td>
									<p>&nbsp;</p></td>
								<td>
									<p>&nbsp;</p></td>
								<td>
									<p>&nbsp;</p></td>
							</tr>
							<tr>
								<td>
									<p>调处数</p></td>
								<td>
									<p>&nbsp;</p></td>
								<td>
									<p>&nbsp;</p></td>
								<td>
									<p>&nbsp;</p></td>
								<td>
									<p>&nbsp;</p></td>
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