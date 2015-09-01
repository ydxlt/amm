<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/taglib.jsp"%>
<html lang="zh-cn">
<head>
<title>考核数据打分</title>
<%@ include file="/WEB-INF/pages/common/styles.jsp"%>
</head>
<body data-spy="scroll">
	<div class="container">
		<div class="container mainbody">
			<%@ include file="/WEB-INF/pages/common/head.jsp"%>
			<ul class="breadcrumb">
				<li>功能导航 <span class="divider">/</span></li>
				<li><a href="${ctx}/khpj/score/audit.html">月考核数据审核</a> <span class="divider">/</span></li>
				<li>月考核数据审核记录</li>
			</ul>
			<div class="row-fluid">
				<div class="span6">
					<form action="${ctx}/khpj/score/list.html" method="post" id="queryForm">
					<select name="type" onchange="javascript:$('#queryForm').submit();">
						<option value="A" <c:if test="${type == 'A'}">selected="selected"</c:if>>A类单位</option>
						<option value="B" <c:if test="${type == 'B'}">selected="selected"</c:if>>B类单位</option>
						<option value="C" <c:if test="${type == 'C'}">selected="selected"</c:if>>C类单位</option>
					</select>
					</form>
				</div>
				<div class="span6">
					<p class="text-right">
						当前考核月份${submitDay[0]}
					</p>
				</div>
			</div>
			<!--Body content-->
			
			<c:set var="quotaLength1" value="${fn:length(deptQuota1)}"></c:set>
			<c:set var="quotaLength2" value="${fn:length(deptQuota2)}"></c:set>
			<div class="row-fluid">
				<div class="span10">
					<table class="table table-bordered table-condensed">
						<thead>
							<tr>
								<th width="40">序号</th>
								<th width="40">单位</th>
								<th colspan="2">考核指标</th>
								<th width="40">分值</th>
								<th width="80">得分</th>
								<th width="60">审核状态</th>
								<th width="80">数据包</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="dept" items="${departments}" varStatus="statue">
								<tr>
									<td rowspan="${quotaLength1 + quotaLength2}">${statue.index+1}</td>
									<td rowspan="${quotaLength1 + quotaLength2}"><a href="javascript:tips('${dept.departmentId}')">${dept.departmentName}</a></td>
									<td rowspan="${quotaLength1}" width="40">固定指标</td>
									<td>${deptQuota1[0].quota.quotaName}</td>
									<td>${deptQuota1[0].quota.quotaScore}</td>
									<td>
										<c:set var="val" value="${dept.departmentId}-${deptQuota1[0].quota.quotaId}"></c:set>
										<c:if test="${not empty scores[val]}">
											${scores[val].scoreValue}
											<c:if test="${unaudited == scores[val].auditStat && scores[val].scoreValue > 0}">
												<p id="${scores[val].scoreId}panel" class="text-right">
												<button class="btn btn-mini btn-success" onclick="auditPass('${scores[val].scoreId}')" type="button">通过</button>
												<button class="btn btn-mini btn-warning" onclick="auditRefuse('${scores[val].scoreId}')" type="button">拒绝</button>
												</p>
											</c:if>
										</c:if>
									</td>
									<td id="${scores[val].scoreId}statue">
										<c:if test="${not empty scores[val]}">
											${scoreState[scores[val].auditStat]}
										</c:if>
										<c:if test="${empty scores[val]}">
											未提交
										</c:if>
									</td>
									<td rowspan="${quotaLength1 + quotaLength2}">
										<c:forEach var="i" items="${yearMonth}">
											<a class="btn" href="javascript:open('${ctx}/khpj/sysData/assess_list.html?month=${i}&dept=${dept.departmentId}');">${i}月份</a>
										</c:forEach>
									</td>
								</tr>
								<c:forEach var="quota1" items="${deptQuota1}" begin="1">
									<tr>
										<td>${quota1.quota.quotaName}</td>
										<td>${quota1.quota.quotaScore}</td>
										<td>
											<c:set var="val" value="${dept.departmentId}-${quota1.quota.quotaId}"></c:set>
											<c:if test="${not empty scores[val]}">
												${scores[val].scoreValue}
												<c:if test="${unaudited ==  scores[val].auditStat && scores[val].scoreValue > 0}">
												<p id="${scores[val].scoreId}panel" class="text-right">
													<button class="btn btn-mini btn-success" onclick="auditPass('${scores[val].scoreId}')" type="button">通过</button>
													<button class="btn btn-mini btn-warning" onclick="auditRefuse('${scores[val].scoreId}')" type="button">拒绝</button>
												</p>
												</c:if>
											</c:if>
										</td>
										<td id="${scores[val].scoreId}statue">
											<c:if test="${not empty scores[val]}">
												${scoreState[scores[val].auditStat]}
											</c:if>
											<c:if test="${empty scores[val]}">
												未提交
											</c:if>
										</td>
									</tr>
								</c:forEach>
								<c:if test="${not empty deptQuota2}">
									<tr>
										<td rowspan="${quotaLength2}">动态指标</td>
										<td>${deptQuota2[0].quota.quotaName}</td>
										<td>${deptQuota2[0].quota.quotaScore}</td>
										<td>
											<input type="hidden" name="quotaIds" value="${deptQuota2[0].quota.quotaId}"/>
											<c:if test="${not empty scores[val]}">
												${scores[val].scoreValue}
												<c:if test="${unaudited ==  scores[val].auditStat && scores[val].scoreValue > 0}">
													<p id="${scores[val].scoreId}panel" class="text-right">
													<button class="btn btn-mini btn-success" onclick="auditPass('${scores[val].scoreId}')" type="button">通过</button>
													<button class="btn btn-mini btn-warning" onclick="auditRefuse('${scores[val].scoreId}')" type="button">拒绝</button>
													</p>
												</c:if>
											</c:if>
										</td>
										<td id="${scores[val].scoreId}statue">
											<c:if test="${not empty scores[val]}">
												${scoreState[scores[val].auditStat]}
											</c:if>
											<c:if test="${empty scores[val]}">
												未提交
											</c:if>
										</td>
									</tr>
									<c:forEach var="quota2" items="${deptQuota2}" begin="1">
										<tr>
											<td>${quota2.quota.quotaName}</td>
											<td>${quota2.quota.quotaScore}</td>
											<td>
												<input type="hidden" name="quotaIds" value="${quota2.quota.quotaId}"/>
												<c:if test="${not empty scores[val]}">
													${scores[val].scoreValue}
													<c:if test="${unaudited ==  scores[val].auditStat && scores[val].scoreValue > 0}">
													<p id="${scores[val].scoreId}panel" class="text-right">
													<button class="btn btn-mini btn-success" onclick="auditPass('${scores[val].scoreId}')" type="button">通过</button>
													<button class="btn btn-mini btn-warning" onclick="auditRefuse('${scores[val].scoreId}')" type="button">拒绝</button>
													</p>
												</c:if>
												</c:if>
											</td>
											<td id="${scores[val].scoreId}statue">
											<c:if test="${not empty scores[val]}">
												${scoreState[scores[val].auditStat]}
											</c:if>
											<c:if test="${empty scores[val]}">
												未提交
											</c:if>
										</td>
										</tr>
									</c:forEach>
								</c:if>
							</c:forEach>
						</tbody>
					</table>
				</div>
				<div class="span2">
					<table class="table table-bordered text-center" data-spy="affix" data-offset-top="350" style="width: 120px; top: 10px;">
						<thead>
							<tr>
								<th>固定指标 <br /> 数据包</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td><c:forEach var="dept" items="${fixedQuotaDept}">
										<a class="btn btn-primary" href="javascript:open('${ctx}/khpj/sysData/viewFixedQuota.html?month=${submitDay[0]}&deptId=${dept.departmentId}');">${dept.departmentName}</a>
									</c:forEach>
								</td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
			<!-- /body content -->
		</div>
		<!-- /container-fluid -->
		<%@ include file="/WEB-INF/pages/common/foot.jsp"%>
	</div>
	<%@ include file="/WEB-INF/pages/common/scripts.jsp"%>
	<script type="text/javascript" src="${ctx}/resource/layer/layer.min.js"></script>
	<script type="text/javascript" src="${ctx}/resource/pages/score/list.js"></script>
	<input type="hidden" id="ctx" value="${ctx}"/>
</body>
</html>