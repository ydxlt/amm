<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/taglib.jsp"%>
<html lang="zh-cn">
<head>
<title>阶段考核数据打分</title>
<%@ include file="/WEB-INF/pages/common/styles.jsp"%>
</head>
<body data-spy="scroll">
	<div class="container">
		<div class="container mainbody">
			<%@ include file="/WEB-INF/pages/common/head.jsp"%>
			<div class="row-fluid">
			<div class="span10">
				<ul class="breadcrumb">
					<li><a href="${ctx}/">首页</a> <span class="divider">/</span></li>
					<li class="active">阶段考核数据维护</li>
				</ul>
				</div>
				<div class="btn-group span2">
					<a class="btn btn-primary dropdown-toggle" data-toggle="dropdown" href="#"><i class="icon-list icon-white"></i> 功能菜单 <span class="caret"></span> </a>
					<ul class="dropdown-menu">
						<li><a href="${ctx}/khpj/score/assess.html">控制面板</a></li>
						<li class="divider"></li>
						<li><a href="${ctx}/khpj/auditScore/list/1.html">半年考核</a></li>
						<li><a href="${ctx}/khpj/auditScore/list/2.html">年度考核</a></li>
						<li class="divider"></li>
						<li><a href="${ctx}/khpj/reports/con_disputes_report/list.html"><i class="i"></i> 矛盾纠纷报表</a></li>
						<li><a href="${ctx}/khpj/print_report/list.html"><i class="i"></i> 政绩档案报表</a></li>
					</ul>
				</div>
			</div>
			<div class="row-fluid">
				<div class="span3">
					<p>&nbsp;&nbsp;&nbsp;&nbsp;${type}类管理员</p>
				</div>
				<div class="span6">
					<span class="text-info lead">
						<c:if test="${scoreType == '1'}">
							${year}半年考核
						</c:if>
						<c:if test="${scoreType == '2'}">
							${year}年度考核
						</c:if>
					</span>
				</div>
				<div class="span3">
					<form action="${ctx}/khpj/auditScore/list/${scoreType}.html" method="post">
						<div class="input-append">
							<select class="span9" name="year">
								<c:forEach var="y" items="${years}">
									<option value="${y}" <c:if test="${year == y}">selected="selected"</c:if>>${y}</option>
								</c:forEach>
							</select>
							<button type="submit" class="btn btn-primary">数据查询</button>
						</div>
					</form>
				</div>
			</div>
			<!--Body content-->

			<c:if test="${not empty tips}">
				<div class="alert alert-success">
					<button type="button" class="close" data-dismiss="alert">&times;</button>
					<h4>系统提示</h4>
					${tips}!
				</div>
			</c:if>
			<div class="row-fluid">
				<form action="${ctx}/khpj/auditScore/save.html" method="post" onsubmit="return confirm('是否提交数据？')">
					<table class="table table-bordered table-condensed">
						<thead>
							<tr>
								<th width="40">序号</th>
								<th>单位</th>
								<th width="120">得分</th>
								<th width="280">审核状态</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="dept" items="${departments}" varStatus="statue">
								<tr>
									<td>${statue.index+1}</td>
									<td><a href="javascript:tips('${dept.departmentId}')">${dept.departmentName}</a></td>
									<td>
										<c:if test="${vpass!=scores[dept.departmentId].auditStat && pass!=scores[dept.departmentId].auditStat}">
											<input type="hidden" name="departmentIds" value="${dept.departmentId}"/>
											<input type="text" name="${dept.departmentId}score" class="input-block-level" placeholder="输入分值" value="${scores[dept.departmentId].scoreValue}"/>
										</c:if>
										<c:if test="${vpass==scores[dept.departmentId].auditStat || pass==scores[dept.departmentId].auditStat}">
											${scores[dept.departmentId].scoreValue}
										</c:if>
									</td>
									<td>
										<c:if test="${not empty scores[dept.departmentId]}">
											${auditScoreState[scores[dept.departmentId].auditStat]}
											<span style="color: red; font-weight: bold;">${scores[dept.departmentId].refuseInfo}</span>
										</c:if>
									</td>
								</tr>
							</c:forEach>
							<tr>
								<td colspan="4">
									<p class="text-right">
										<input type="hidden" name="year" value="${year}"/>
										<input type="hidden" name="scoreType" value="${scoreType}"/>
										<input type="hidden" name="deptType" value="${type}"/>
										<button class="btn btn-primary" type="submit">阶段数据提交审核</button>&nbsp;&nbsp;&nbsp;&nbsp;
									</p>
								</td>
							</tr>
						</tbody>
					</table>
				</form>
			</div>

			<!-- /body content -->
		</div>
		<!-- /container-fluid -->
		<%@ include file="/WEB-INF/pages/common/foot.jsp"%>
	</div>
	<%@ include file="/WEB-INF/pages/common/scripts.jsp"%>
	<script type="text/javascript" src="${ctx}/resource/layer/layer.min.js"></script>
	<script type="text/javascript" src="${ctx}/resource/pages/auditScore/list.js"></script>
	<input type="hidden" id="ctx" value="${ctx}"/>
</body>
</html>